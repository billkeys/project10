package test;

import java.util.Date;

/**
 * The persistent class for the health_data database table.
 * 
 */
public class HealthData {
	private String stationId;

	private Date time;

	private Boolean anemometerSensor;

	private Boolean barometerSensor;

	private Integer batteryLevel;

	private Boolean batterySensor;

	private Boolean clockSensor;

	private Boolean humiditySensor;

	private Boolean precipitionSensor;

	private String stationStatus;

	private Boolean sunshineSensor;

	private Boolean temperatureSensor;

	private Boolean visibilitySensor;

	private Boolean windDirectionSensor;

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
	public java.util.Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(java.util.Date time) {
		this.time = time;
	}

	/**
	 * @return the anemometerSensor
	 */
	public Boolean getAnemometerSensor() {
		return anemometerSensor;
	}

	/**
	 * @param anemometerSensor the anemometerSensor to set
	 */
	public void setAnemometerSensor(Boolean anemometerSensor) {
		this.anemometerSensor = anemometerSensor;
	}

	/**
	 * @return the barometerSensor
	 */
	public Boolean getBarometerSensor() {
		return barometerSensor;
	}

	/**
	 * @param barometerSensor the barometerSensor to set
	 */
	public void setBarometerSensor(Boolean barometerSensor) {
		this.barometerSensor = barometerSensor;
	}

	/**
	 * @return the batteryLevel
	 */
	public Integer getBatteryLevel() {
		return batteryLevel;
	}

	/**
	 * @param batteryLevel the batteryLevel to set
	 */
	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	/**
	 * @return the batterySensor
	 */
	public Boolean getBatterySensor() {
		return batterySensor;
	}

	/**
	 * @param batterySensor the batterySensor to set
	 */
	public void setBatterySensor(Boolean batterySensor) {
		this.batterySensor = batterySensor;
	}

	/**
	 * @return the clockSensor
	 */
	public Boolean getClockSensor() {
		return clockSensor;
	}

	/**
	 * @param clockSensor the clockSensor to set
	 */
	public void setClockSensor(Boolean clockSensor) {
		this.clockSensor = clockSensor;
	}

	/**
	 * @return the humiditySensor
	 */
	public Boolean getHumiditySensor() {
		return humiditySensor;
	}

	/**
	 * @param humiditySensor the humiditySensor to set
	 */
	public void setHumiditySensor(Boolean humiditySensor) {
		this.humiditySensor = humiditySensor;
	}

	/**
	 * @return the precipitionSensor
	 */
	public Boolean getPrecipitionSensor() {
		return precipitionSensor;
	}

	/**
	 * @param precipitionSensor the precipitionSensor to set
	 */
	public void setPrecipitionSensor(Boolean precipitionSensor) {
		this.precipitionSensor = precipitionSensor;
	}

	/**
	 * @return the stationStatus
	 */
	public String getStationStatus() {
		return stationStatus;
	}

	/**
	 * @param stationStatus the stationStatus to set
	 */
	public void setStationStatus(String stationStatus) {
		this.stationStatus = stationStatus;
	}

	/**
	 * @return the sunshineSensor
	 */
	public Boolean getSunshineSensor() {
		return sunshineSensor;
	}

	/**
	 * @param sunshineSensor the sunshineSensor to set
	 */
	public void setSunshineSensor(Boolean sunshineSensor) {
		this.sunshineSensor = sunshineSensor;
	}

	/**
	 * @return the temperatureSensor
	 */
	public Boolean getTemperatureSensor() {
		return temperatureSensor;
	}

	/**
	 * @param temperatureSensor the temperatureSensor to set
	 */
	public void setTemperatureSensor(Boolean temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	/**
	 * @return the visibilitySensor
	 */
	public Boolean getVisibilitySensor() {
		return visibilitySensor;
	}

	/**
	 * @param visibilitySensor the visibilitySensor to set
	 */
	public void setVisibilitySensor(Boolean visibilitySensor) {
		this.visibilitySensor = visibilitySensor;
	}

	/**
	 * @return the windDirectionSensor
	 */
	public Boolean getWindDirectionSensor() {
		return windDirectionSensor;
	}

	/**
	 * @param windDirectionSensor the windDirectionSensor to set
	 */
	public void setWindDirectionSensor(Boolean windDirectionSensor) {
		this.windDirectionSensor = windDirectionSensor;
	}

	@Override
	public String toString() {
		return "HealthData [stationId=" + stationId + ", time=" + time
				+ ", anemometerSensor=" + anemometerSensor
				+ ", barometerSensor=" + barometerSensor + ", batteryLevel="
				+ batteryLevel + ", batterySensor=" + batterySensor
				+ ", clockSensor=" + clockSensor + ", humiditySensor="
				+ humiditySensor + ", precipitionSensor=" + precipitionSensor
				+ ", stationStatus=" + stationStatus + ", sunshineSensor="
				+ sunshineSensor + ", temperatureSensor=" + temperatureSensor
				+ ", visibilitySensor=" + visibilitySensor
				+ ", windDirectionSensor=" + windDirectionSensor + "]";
	}
}