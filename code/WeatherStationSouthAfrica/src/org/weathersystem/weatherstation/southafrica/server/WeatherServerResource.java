
package org.weathersystem.weatherstation.southafrica.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.jdo.PersistenceManager;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.weathersystem.weatherstation.southafrica.common.Weather;
import org.weathersystem.weatherstation.southafrica.common.WeatherResource;
import org.weathersystem.weatherstation.southafrica.server.jpo.HealthRecord;
import org.weathersystem.weatherstation.southafrica.server.jpo.PMF;
import org.weathersystem.weatherstation.southafrica.server.jpo.WeatherRecord; 

/**
 * The server side implementation of the Restlet resource.
 */
public class WeatherServerResource extends ServerResource implements
WeatherResource {

	private static volatile Weather weather =  createWeather();

	@Override
	@Delete
	public void remove() {
		weather = null;
	}

	@Override
	@Get
	public Weather retrieve() {
		return createWeather();
	}

	@Override
	@Put
	public void store(Weather weather) {
		WeatherServerResource.weather = weather;
	}

	/*
	 *  Helper function
	 */
	private static  Weather createWeather() {
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("weatherdata.prop"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int state = 1;

		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + WeatherRecord.class.getName() + " where id == 2";
		List<WeatherRecord> states = (List<WeatherRecord>) pm.newQuery(query).execute();

		int s = 0;
		WeatherRecord generatedData = new WeatherRecord();
		
		for(WeatherRecord item : states ){
			if(item.getAirTemperature() != null){
			generatedData = generateWeatherData(item);
			}
		}

		PersistenceManager pm1 = PMF.get().getPersistenceManager();
		String query1 = "select from " + HealthRecord.class.getName() + " where id == 1";
		List<HealthRecord> health = (List<HealthRecord>) pm1.newQuery(query1).execute();
		
		String  airpressure;
		String  windspeed;
		String  airtemperature;
		String  groundtemperature;
		String  precipition;
		String  humidity;
		String  solarradiation;
		String  dewpoint;
		String  winddirection;
		String  time;
		String  stationId;
		
		if (states.isEmpty()){
			//get the property value and print it out
			airpressure		= prop.getProperty("southafrica.airpressure."+ state );
			windspeed		= prop.getProperty("southafrica.windspeed."+ state );
			airtemperature	= prop.getProperty("southafrica.airtemperature."+ state );
			groundtemperature = prop.getProperty("southafrica.groundtemperature."+ state);
			precipition		= prop.getProperty("southafrica.precipition."+ state );
			humidity		= prop.getProperty("southafrica.humidity."+ state );
			solarradiation	= prop.getProperty("southafrica.solarradiation."+ state );
			dewpoint		= prop.getProperty("southafrica.dewpoint."+ state );
			winddirection	= prop.getProperty("southafrica.winddirection."+ state );
			time			= Long.toString(System.currentTimeMillis());
			stationId		= prop.getProperty("southafrica.stationId");

			generatedData.setStationId(stationId);

			generatedData.setAirPressure(Double.parseDouble(airpressure));

			generatedData.setWindSpeed(Integer.parseInt(windspeed));
			generatedData.setAirTemperature(Integer.parseInt(airtemperature));
			generatedData.setGroundTemperature(Integer.parseInt(groundtemperature));
			generatedData.setPrecipition(Integer.parseInt(precipition));
			generatedData.setHumidity(Integer.parseInt(humidity));
			generatedData.setSolarRadiation(Integer.parseInt(solarradiation));
			generatedData.setDewPoint(Integer.parseInt(dewpoint));
			generatedData.setWindDirection(winddirection);
			generatedData.setId(2);
			
			pm.makePersistent(generatedData);
			pm.close();
		}
		else {	
			airpressure		= ""+generatedData.getAirPressure();
			windspeed		= ""+generatedData.getWindSpeed();
			airtemperature	= ""+generatedData.getAirTemperature();
			groundtemperature = ""+generatedData.getGroundTemperature();
			precipition		= ""+generatedData.getPrecipition();
			humidity		= ""+generatedData.getHumidity();
			solarradiation	= ""+generatedData.getSolarRadiation();
			dewpoint		= ""+generatedData.getDewPoint();
			winddirection	= ""+generatedData.getWindDirection();
			time			= Long.toString(System.currentTimeMillis());
			stationId		= ""+generatedData.getStationId();
		}
		
		for(HealthRecord item : health ) {

			if(item.getAnemometer().equals("down")||item.getStatus().equals("down")) {
				windspeed = "null";
			}

			if(item.getWinddirection().equals("down")||item.getStatus().equals("down")) {
				winddirection = "null";
			}

			if(item.getBarometer().equals("down")||item.getStatus().equals("down")) {
				airpressure = "null";
			}

			if(item.getClock().equals("down")||item.getStatus().equals("down")) {
				time = "null";
			}	

			if(item.getHumidity().equals("down")||item.getStatus().equals("down")) {
				humidity= "null";
			}

			if(item.getPrecipitation().equals("down")||item.getStatus().equals("down")) {
				precipition = "null";
			}	

			if(item.getSunshine().equals("down")||item.getStatus().equals("down")) {
				solarradiation = "null";
			}	

			if(item.getTemperature().equals("down")||item.getStatus().equals("down")) {
				groundtemperature = "null";
				airtemperature = "null";
			}	

			if(item.getVisibility().equals("down")||item.getStatus().equals("down")) {
				dewpoint = "null";
			}	
		}

		Weather weather = new Weather(state,airpressure,airtemperature,windspeed,groundtemperature,precipition,humidity,solarradiation,dewpoint,winddirection,time,stationId);


	
		pm1.close();
		return weather;
	}

	public static WeatherRecord generateWeatherData(WeatherRecord previousData)
	{
		WeatherRecord newData = new WeatherRecord();

		//Set the StationID using the old StationID
		previousData.setStationId(previousData.getStationId());

		//Set the time using the current time
		Long currentTime = System.currentTimeMillis();
		Date newDate = new Date(currentTime);
		previousData.setTime(newDate);

		//Set the airPressure using a range based on the old airPressure
		Double airPressure = generateAirPressure(previousData.getAirPressure());
		previousData.setAirPressure(airPressure);

		//Set the airTemperature using a range based on the old airTemperature
		Integer airTemperature = generateAirTemperature(previousData.getAirTemperature());
		previousData.setAirTemperature(airTemperature);

		//Set the dewPoint using a range based on the old dewPoint
		Integer dewPoint = generateDewPoint(previousData.getDewPoint());
		previousData.setDewPoint(dewPoint);

		//Set the groundTemperature using a range based on the old groundTemperature
		Integer groundTemperature = generateGroundTemperature(previousData.getGroundTemperature());
		previousData.setGroundTemperature(groundTemperature);

		//Set the humidity using a range based on the old humidity
		Integer humidity = generateHumidity(previousData.getHumidity());
		previousData.setHumidity(humidity);

		//Set the precipition using a range based on the old precipition
		Integer precipition = generatePrecipition(previousData.getPrecipition());
		previousData.setPrecipition(precipition);

		//Set the solarRadiation using a range based on the old solarRadiation
		Integer solarRadiation = generateSolarRadiation(previousData.getSolarRadiation());
		previousData.setSolarRadiation(solarRadiation);

		//Set the windDirection randomly from a list of choices
		String windDirection = generateWindDirection();
		previousData.setWindDirection(windDirection);

		//Set the windSpeed using a range based on the old windSpeed
		Integer windSpeed = generateWindSpeed(previousData.getWindSpeed());
		previousData.setWindSpeed(windSpeed);

		return previousData;
	}

	private static Double generateAirPressure(Double prevAirPressure)
	{
		Double min = prevAirPressure-2;
		Double max = prevAirPressure+2;
		Double random = min + (Double)(Math.random() * ((max - min) + 1));
		
		boolean valueReady = false;
		while(! valueReady)
		{

			//Keep the air pressure in the normal range so it does not drop too low or raise too high
			if(random >= 20 && random <= 40)
			{
				valueReady = true;
			}
			else
			{
				random = min + (Double)(Math.random() * ((max - min) + 1));
			}
		}
		return random;
	}
	
	private static Integer generateAirTemperature(Integer previousAirTemperature)
	{
		Integer min = previousAirTemperature-2;
		Integer max = previousAirTemperature+2;
		Integer random = min + (int)(Math.random() * ((max - min) + 1));
		
		boolean valueReady = false;
		while(! valueReady)
		{
			//Keep the air temperature in the normal range so it does not drop too low or raise too high
			if(random >= 25 && random <= 100)
			{
				valueReady = true;
			}
			else
			{
				random = min + (int)(Math.random() * ((max - min) + 1));
			}
		}
		return random;
	}
	
	private static Integer generateDewPoint(Integer previousDewPoint)
	{
		Integer min = previousDewPoint-1;
		Integer max = previousDewPoint+1;
		Integer random = min + (int)(Math.random() * ((max - min) + 1));
		
		boolean valueReady = false;
		while(! valueReady)
		{
			//Keep the dew point in the normal range so it does not drop too low or raise too high
			if(random >= 25 && random <= 90)
			{
				valueReady = true;
			}
			else
			{
				random = min + (int)(Math.random() * ((max - min) + 1));
			}
		}
		return random;
	}
	
	private static Integer generateGroundTemperature(Integer previousGroundTemperature)
	{
		Integer min = previousGroundTemperature-1;
		Integer max = previousGroundTemperature+1;
		Integer random = min + (int)(Math.random() * ((max - min) + 1));
		
		boolean valueReady = false;
		while(! valueReady)
		{
			//Keep the ground temperature in the normal range so it does not drop too low or raise too high
			if(random >= 25 && random <= 90)
			{
				valueReady = true;
			}
			else
			{
				random = min + (int)(Math.random() * ((max - min) + 1));
			}
		}
		return random;
	}
	
	private static Integer generateHumidity(Integer previousHumidity)
	{
		Integer min = previousHumidity-4;
		Integer max = previousHumidity+4;
		Integer random = min + (int)(Math.random() * ((max - min) + 1));
		
		boolean valueReady = false;
		while(! valueReady)
		{
			//Keep the humidity in the normal range so it does not drop too low or raise too high
			if(random >= 0 && random <= 100)
			{
				valueReady = true;
			}
			else
			{
				random = min + (int)(Math.random() * ((max - min) + 1));
			}
		}
		return random;
	}
	
	private static Integer generatePrecipition(Integer previousPrecipition)
	{
		Integer min = previousPrecipition-1;
		Integer max = previousPrecipition+1;
		Integer random = min + (int)(Math.random() * ((max - min) + 1));
		
		boolean valueReady = false;
		while(! valueReady)
		{
			//Keep the precipition in the normal range so it does not drop too low or raise too high
			if(random >= 0 && random <= 5)
			{
				valueReady = true;
			}
			else
			{
				random = min + (int)(Math.random() * ((max - min) + 1));
			}
		}
		return random;
	}
	
	private static Integer generateSolarRadiation(Integer previousSolarRadiation)
	{
		Integer min = previousSolarRadiation-2;
		Integer max = previousSolarRadiation+2;
		Integer random = min + (int)(Math.random() * ((max - min) + 1));
		
		boolean valueReady = false;
		while(! valueReady)
		{
			//Keep the solar radiation in the normal range so it does not drop too low or raise too high
			if(random >= 1800 && random <= 2200)
			{
				valueReady = true;
			}
			else
			{
				random = min + (int)(Math.random() * ((max - min) + 1));
			}
		}
		return random;
	}
	
	private static String generateWindDirection()
	{
		Vector<String> directions = new Vector<String>();
		directions.add("North");
		directions.add("NorthEast");
		directions.add("East");
		directions.add("SouthEast");
		directions.add("South");
		directions.add("SouthWest");
		directions.add("West");
		directions.add("NorthWest");
		
		//Min and max here are indices for directions Vector
		int min = 0;
		int max = directions.size()-1;
		int random = min + (int)(Math.random() * ((max - min) + 1));
		return directions.get(random);
	}
	
	private static Integer generateWindSpeed(Integer previousWindSpeed)
	{
		Integer min = previousWindSpeed-1;
		Integer max = previousWindSpeed+1;
		Integer random = min + (int)(Math.random() * ((max - min) + 1));
		
		boolean valueReady = false;
		while(! valueReady)
		{
			//Keep the Wind Speed in the normal range so it does not drop too low or raise too high
			if(random >= 0 && random <= 65)
			{
				valueReady = true;
			}
			else
			{
				random = min + (int)(Math.random() * ((max - min) + 1));
			}
		}
		return random;
	}

}