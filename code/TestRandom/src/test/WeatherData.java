package test;

import java.util.Date;

public class WeatherData {
	private String stationId;
	
	private Date time;

	private Double airPressure;

	private Integer airTemperature;

	private Integer dewPoint;

	private Integer groundTemperature;

	private Integer humidity;

	private Integer precipition;

	private Integer solarRadiation;

	private String windDirection;

	private Integer windSpeed;

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
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the airPressure
	 */
	public Double getAirPressure() {
		return airPressure;
	}

	/**
	 * @param airPressure the airPressure to set
	 */
	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}

	/**
	 * @return the airTemperature
	 */
	public Integer getAirTemperature() {
		return airTemperature;
	}

	/**
	 * @param airTemperature the airTemperature to set
	 */
	public void setAirTemperature(Integer airTemperature) {
		this.airTemperature = airTemperature;
	}

	/**
	 * @return the dewPoint
	 */
	public Integer getDewPoint() {
		return dewPoint;
	}

	/**
	 * @param dewPoint the dewPoint to set
	 */
	public void setDewPoint(Integer dewPoint) {
		this.dewPoint = dewPoint;
	}

	/**
	 * @return the groundTemperature
	 */
	public Integer getGroundTemperature() {
		return groundTemperature;
	}

	/**
	 * @param groundTemperature the groundTemperature to set
	 */
	public void setGroundTemperature(Integer groundTemperature) {
		this.groundTemperature = groundTemperature;
	}

	/**
	 * @return the humidity
	 */
	public Integer getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	/**
	 * @return the precipition
	 */
	public Integer getPrecipition() {
		return precipition;
	}

	/**
	 * @param precipition the precipition to set
	 */
	public void setPrecipition(Integer precipition) {
		this.precipition = precipition;
	}

	/**
	 * @return the solarRadiation
	 */
	public Integer getSolarRadiation() {
		return solarRadiation;
	}

	/**
	 * @param solarRadiation the solarRadiation to set
	 */
	public void setSolarRadiation(Integer solarRadiation) {
		this.solarRadiation = solarRadiation;
	}

	/**
	 * @return the windDirection
	 */
	public String getWindDirection() {
		return windDirection;
	}

	/**
	 * @param windDirection the windDirection to set
	 */
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	/**
	 * @return the windSpeed
	 */
	public Integer getWindSpeed() {
		return windSpeed;
	}

	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(Integer windSpeed) {
		this.windSpeed = windSpeed;
	}

	@Override
	public String toString() {
		return "WeatherData [stationId=" + stationId + ", time=" + time
				+ ", airPressure=" + airPressure + ", airTemperature="
				+ airTemperature + ", dewPoint=" + dewPoint
				+ ", groundTemperature=" + groundTemperature + ", humidity="
				+ humidity + ", precipition=" + precipition
				+ ", solarRadiation=" + solarRadiation + ", windDirection="
				+ windDirection + ", windSpeed=" + windSpeed + "]";
	}
}