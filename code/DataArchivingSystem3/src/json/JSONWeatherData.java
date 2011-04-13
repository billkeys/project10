package json;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;

import persistence.WeatherData;
import persistence.WeatherDataPK;


/**
 * The persistent class for the weather_data database table.
 * 
 */
public class JSONWeatherData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer state;
	private String stationId;
	private Long time;
	private String windspeed;
	private String airtemperature;
	private String groundtemperature;
	private String airpressure;
	private String winddirection;
	private String dewpoint;
	private String solarradiation;
	private String humidity;
	private String precipition;
	
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the stationId
	 */
	public String getStationId() {
		return stationId;
	}

	/**
	 * @param stationId the stationId to set
	 */
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	/**
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Long time) {
		this.time = time;
	}
	
	/**
	 * @return the windspeed
	 */
	public String getWindspeed() {
		return windspeed;
	}

	/**
	 * @param windspeed the windspeed to set
	 */
	public void setWindspeed(String windspeed) {
		this.windspeed = windspeed;
	}

	/**
	 * @return the airtemperature
	 */
	public String getAirtemperature() {
		return airtemperature;
	}

	/**
	 * @param airtemperature the airtemperature to set
	 */
	public void setAirtemperature(String airtemperature) {
		this.airtemperature = airtemperature;
	}

	/**
	 * @return the groundtemperature
	 */
	public String getGroundtemperature() {
		return groundtemperature;
	}

	/**
	 * @param groundtemperature the groundtemperature to set
	 */
	public void setGroundtemperature(String groundtemperature) {
		this.groundtemperature = groundtemperature;
	}

	/**
	 * @return the airpressure
	 */
	public String getAirpressure() {
		return airpressure;
	}

	/**
	 * @param airpressure the airpressure to set
	 */
	public void setAirpressure(String airpressure) {
		this.airpressure = airpressure;
	}

	/**
	 * @return the winddirection
	 */
	public String getWinddirection() {
		return winddirection;
	}

	/**
	 * @param winddirection the winddirection to set
	 */
	public void setWinddirection(String winddirection) {
		this.winddirection = winddirection;
	}

	/**
	 * @return the dewpoint
	 */
	public String getDewpoint() {
		return dewpoint;
	}

	/**
	 * @param dewpoint the dewpoint to set
	 */
	public void setDewpoint(String dewpoint) {
		this.dewpoint = dewpoint;
	}

	/**
	 * @return the solarradiation
	 */
	public String getSolarradiation() {
		return solarradiation;
	}

	/**
	 * @param solarradiation the solarradiation to set
	 */
	public void setSolarradiation(String solarradiation) {
		this.solarradiation = solarradiation;
	}

	/**
	 * @return the precipition
	 */
	public String getPrecipition() {
		return precipition;
	}

	/**
	 * @param precipition the precipition to set
	 */
	public void setPrecipition(String precipition) {
		this.precipition = precipition;
	}

	/**
	 * @return the humidity
	 */
	public String getHumidity() {
		return humidity;
	}

	public WeatherData convertToWeatherData()
	{
		WeatherData weatherData = new WeatherData();
		WeatherDataPK pk = new WeatherDataPK();
		/* Set the attributes of the primary key */
		pk.setStationId(this.getStationId());
		Timestamp time = convertToTimestamp(this.getTime());
		pk.setTime(time);
		weatherData.setPk(pk); //Now set the Primary Key of the class
		
		//Convert the variables from Strings to Double(s) or Integer(s) as required. This will handle nullability. 
		weatherData.setAirPressure(convertToDouble(this.getAirpressure()));
		weatherData.setAirTemperature(convertToInteger(this.getAirtemperature()));
		weatherData.setDewPoint(convertToInteger(this.getDewpoint()));
		weatherData.setGroundTemperature(convertToInteger(this.getGroundtemperature()));
		weatherData.setHumidity(convertToInteger(this.getHumidity()));
		weatherData.setPrecipition(convertToInteger(this.getPrecipition()));
		weatherData.setSolarRadiation(convertToInteger(this.getSolarradiation()));
		weatherData.setWindDirection(checkIfNull(this.getWinddirection()));
		weatherData.setWindSpeed(convertToInteger(this.getWindspeed()));
		return weatherData;
	}
	
	/*
	 * Check if the time is null before converting it into a Timestamp. If null, then a null Timestamp is returned.
	 * This is required to avoid a nullpointer exception.
	 */
	public Timestamp convertToTimestamp(Long time) {
		Timestamp timestamp = null;
		if(time != null)
		{
			timestamp = new Timestamp(time);
			return timestamp;
		}
		else
			return timestamp;
	}
	
	public Integer convertToInteger(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Double convertToDouble(String s) {
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			return null;
		}
	}
	
	public String checkIfNull(String s) {
		if(s.equals("null") || s.equals("Null") || s == null) {
			return null;
		}
		else 
			return s;
	}

	@Override
	public String toString() {
		return "JSONWeatherData [state=" + state + ", stationId=" + stationId
				+ ", time=" + time + ", windspeed=" + windspeed
				+ ", airtemperature=" + airtemperature + ", groundtemperature="
				+ groundtemperature + ", airpressure=" + airpressure
				+ ", winddirection=" + winddirection + ", dewpoint=" + dewpoint
				+ ", solarradiation=" + solarradiation + ", humidity="
				+ humidity + ", precipition=" + precipition + "]";
	}
	
}