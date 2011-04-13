package test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args)
	{
		//Test the random generation with a sample WeatherData object
		WeatherData weatherData = new WeatherData();
		weatherData.setStationId("South Africa");
		long t = System.currentTimeMillis();
		Timestamp time = new Timestamp(t);
		weatherData.setTime(time);
		weatherData.setAirPressure(979.21);
		weatherData.setAirTemperature(24);
		weatherData.setDewPoint(68);
		weatherData.setGroundTemperature(24);
		weatherData.setHumidity(-1);
		weatherData.setPrecipition(-1);
		weatherData.setSolarRadiation(1799);
		weatherData.setWindDirection("NorthWest");
		weatherData.setWindSpeed(-1);
		
		int i = 0;
		while(i < 500) {
			WeatherData generatedData = generateWeatherData(weatherData);
			System.out.println("Number " + i + ": " + generatedData.toString());
			weatherData = generatedData;
			i++;
		}
	}
	
	public static WeatherData generateWeatherData(WeatherData previousData)
	{
		WeatherData newData = new WeatherData();
		//Set the StationID using the old StationID
		newData.setStationId(previousData.getStationId());
		
		//Set the time using the current time
		Long currentTime = System.currentTimeMillis();
		Date newDate = new Date(currentTime);
		newData.setTime(newDate);
		
		//Set the airPressure using a range based on the old airPressure
		Double airPressure = generateAirPressure(previousData.getAirPressure());
		newData.setAirPressure(airPressure);
		
		//Set the airTemperature using a range based on the old airTemperature
		Integer airTemperature = generateAirTemperature(previousData.getAirTemperature());
		newData.setAirTemperature(airTemperature);
		
		//Set the dewPoint using a range based on the old dewPoint
		Integer dewPoint = generateDewPoint(previousData.getDewPoint());
		newData.setDewPoint(dewPoint);
		
		//Set the groundTemperature using a range based on the old groundTemperature
		Integer groundTemperature = generateGroundTemperature(previousData.getGroundTemperature());
		newData.setGroundTemperature(groundTemperature);
		
		//Set the humidity using a range based on the old humidity
		Integer humidity = generateHumidity(previousData.getHumidity());
		newData.setHumidity(humidity);
		
		//Set the precipition using a range based on the old precipition
		Integer precipition = generatePrecipition(previousData.getPrecipition());
		newData.setPrecipition(precipition);
		
		//Set the solarRadiation using a range based on the old solarRadiation
		Integer solarRadiation = generateSolarRadiation(previousData.getSolarRadiation());
		newData.setSolarRadiation(solarRadiation);
		
		//Set the windDirection randomly from a list of choices
		String windDirection = generateWindDirection();
		newData.setWindDirection(windDirection);
		
		//Set the windSpeed using a range based on the old windSpeed
		Integer windSpeed = generateWindSpeed(previousData.getWindSpeed());
		newData.setWindSpeed(windSpeed);
		
		return newData;
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
			if(random >= 980 && random <= 1050)
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
		Integer min = previousPrecipition-2;
		Integer max = previousPrecipition+2;
		Integer random = min + (int)(Math.random() * ((max - min) + 1));
		
		boolean valueReady = false;
		while(! valueReady)
		{
			//Keep the precipition in the normal range so it does not drop too low or raise too high
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

