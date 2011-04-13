package test;

import java.util.List;

import persistence.WeatherData;
import persistence.controller.WeatherDataManager;
import java.sql.Timestamp;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WeatherDataManager manager = new WeatherDataManager();
			/*WeatherData weatherData = new WeatherData();
			weatherData.setStationId("Africa");
			long t = System.currentTimeMillis();
			Timestamp time = new Timestamp(t);
			System.out.println(t);
			System.out.print(time.toString());
			weatherData.setTime(time);
			weatherData.setAirPressure(1200.51);
			weatherData.setAirTemperature(59);
			weatherData.setDewPoint(40);
			weatherData.setGroundTemperature(65);
			weatherData.setHumidity(50);
			weatherData.setPrecipition(35);
			weatherData.setSolarRadiation(1900);
			weatherData.setWindDirection("northwest");
			weatherData.setWindSpeed(10);
			//String response = manager.createWeatherData(weatherData);
			//System.out.println(response);
			//manager.deleteWeatherData(weatherData);
			//manager.updateWeatherData(weatherData);*/
			
			System.out.println("Returned weather data object: ");
			List<WeatherData> weatherList = manager.getWeatherDataByStationId("southafrica");
			//List<WeatherData> weatherList = manager.getWeatherData();
			int i = 0;
			while(i < weatherList.size())
			{
				WeatherData weather = weatherList.get(i);
				System.out.println(weather.toString());
				i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
