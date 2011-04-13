package persistence.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.persistence.RollbackException;

import json.JSONHealthData;
import json.JSONWeatherData;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import persistence.HealthData;
import persistence.WeatherData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DBController {
	public static void main(String[] args) {
		try {
			
			/* Create a HashMap containing the HealthURLs as the keys and the WeatherURLs as the values */
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("http://weatherstationsouthafrica.appspot.com/currenthealthdata", "http://weatherstationsouthafrica.appspot.com/weatherdata");
			map.put("http://weatherstationghana.appspot.com/currenthealthdata", "http://weatherstationghana.appspot.com/weatherdata");
			map.put("http://weatherstationkenya.appspot.com/currenthealthdata", "http://weatherstationkenya.appspot.com/weatherdata");
			
			while(true) {
				//Retrieve the set of keys (healthURLs) from the HashMap
				Set<String> healthURLs = map.keySet();
				
				//Get an Iterator over the set of keys
				Iterator<String> healthItr = healthURLs.iterator();
				
				while(healthItr.hasNext())
				{
					try {
						String healthURL = healthItr.next(); //Get each HealthURL from the Set
						System.out.println("Retrieving health information from:\n" + healthURL);
						String hdResult = "";
						String wdResult = "";
						
						//Retrieve a JSON string using the healthURL
						ClientResource healthResource = new ClientResource(healthURL);
						Gson gson = new Gson();
						Type collectionType = new TypeToken<JSONHealthData>() {}.getType();
						String jsonStr = healthResource.get(MediaType.APPLICATION_JSON).getText();
						System.out.println("Health Data: " + jsonStr);
						
						//Parse the JSON object into JSONHEALTHDATA
						JSONHealthData jwd = gson.fromJson(jsonStr, collectionType);
						
						if(jwd.getStatus().equalsIgnoreCase("Powered On (Low Battery Power)") || jwd.getStatus().equalsIgnoreCase("Powered On"))
						{ //Only collect the Health Data for the station if the status of the station is up
							
							hdResult = collectHealthData(jwd); //Collect/store the health information into the db
							
							//Check if the data was stored in the database
							if (hdResult.equals("success"))
								System.out.println("Health Data was successfully inserted into the database!!!");
							else
								System.out.println("ERROR: HEALTH DATA COULD NOT BE INSERTED INTO THE DATABASE!!!");
							
							//Only collect the Weather Data for the station if the station is powered on
							String weatherURL = map.get(healthURL);
							System.out.println("Retrieving weather information from:\n" + weatherURL);
							wdResult = collectWeatherData(weatherURL);
							
							//Check if the data was stored in the database
							if (wdResult.equals("success"))
								System.out.println("Weather Data was successfully inserted into the database!!!");
							else
								System.out.println("ERROR: WEATHER DATA COULD NOT BE INSERTED INTO THE DATABASE!!!");
						}
						else //Condition reached if station is powered off
						{
							/* Write a NULL row in the health_data table
							 * The row will only contain the time and the status of the station,
							 * however all sensor information will be NULL since it cannot be 
							 * retrieved from a powered off station */
							HealthData healthData = jwd.convertToHealthData();
							healthData.setAnemometerSensor(null);
							healthData.setBarometerSensor(null);
							healthData.setBatteryCharging(null);
							healthData.setBatteryLevel(null);
							healthData.setBatterySensor(null);
							healthData.setClockSensor(null);
							healthData.setHumiditySensor(null);
							healthData.setPrecipitionSensor(null);
							healthData.setSunshineSensor(null);
							healthData.setTemperatureSensor(null);
							healthData.setVisibilitySensor(null);
							healthData.setWindDirectionSensor(null);
							HealthDataManager hdManager = new HealthDataManager();
							String result = hdManager.createHealthData(healthData);
							if (result.equals("success"))
								System.out.println("STATION " + jwd.getStationId() + " IS CURRENTLY DOWN!!! ERROR: WEATHER DATA COULD NOT BE COLLECTED" +
										"\nNULL HEALTH DATA INSERTED INTO DATABASE.");
							else
								System.out.println("ERROR: NO DATA HAS BEEN STORED IN THE DATABASE!!!");
						}
					} catch (RollbackException e) {
						System.out.println("Data is already in the database please try again!");
					}
				}
				
				// Put the thread to sleep allowing the archiver to repeat the above collection operation
				// Every 1 minute
				Thread.currentThread();
				Thread.sleep(60000);
			}
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			System.out.println("Data is already in the database please try again!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used to convert a JSONHealthData object into a HealthData object and store it in the
	 * health_data table in the database
	 * @param jwd - Parsed JSON object to be converted into HealthData object for persistence
	 * @return String with success or failure message for the database write operation
	 * @throws Exception
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static String collectHealthData(JSONHealthData jwd) throws Exception, ResourceException, IOException
	{
		HealthData healthData = jwd.convertToHealthData();

		System.out.println("HEALTH JSON OBJECT: \b" + jwd.toString());
		System.out.println("HEALTH DATA OBJECT: \b"
				+ healthData.toString());

		//INSERT THE NEWLY COLLECTED HEALTHDATA INTO THE health_data TABLE IN THE DB
		HealthDataManager hdManager = new HealthDataManager();
		String result = hdManager.createHealthData(healthData);
		return result;
	}
	
	/**
	 * Method used to retrieve a JSON object using the weatherDataURL passed in and
	 * parse it into a JSONWeatherData object which is then converted into a WeatherData 
	 * object and stored in the wealth_data table in the database
	 * @param weatherDataURL - URL used to retrieve weather data
	 * @return String with success or failure message for the database write operation
	 * @throws Exception
	 * @throws ResourceException
	 * @throws IOException
	 */
	public static String collectWeatherData(String weatherDataURL) throws Exception, ResourceException, IOException
	{
		ClientResource weatherResource = new ClientResource(weatherDataURL);
		Gson gson = new Gson();
		Type collectionType = new TypeToken<JSONWeatherData>() {}.getType();
		String jsonStr = weatherResource.get(MediaType.APPLICATION_JSON).getText();
		System.out.println("Weather data: " + jsonStr);

		//Parse the JSON object into JSONWEATHERDATA
		JSONWeatherData jwd = gson.fromJson(jsonStr, collectionType);
		WeatherData weatherData = jwd.convertToWeatherData();

		System.out.println("WEATHER JSON OBJECT: \b" + jwd.toString());
		System.out.println("WEATHER DATA OBJECT: \b"
				+ weatherData.toString());

		//INSERT THE NEWLY COLLECTED WEATHERDATA INTO THE weather_data TABLE IN THE DB
		WeatherDataManager wdManager = new WeatherDataManager();
		String result = wdManager.createWeatherData(weatherData);
		return result;
	}
	
}
