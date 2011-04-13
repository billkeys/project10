package org.weathersystem.weatherinformationsystem.json;

import java.io.Serializable;
import java.sql.Timestamp;

import org.weathersystem.weatherinformationsystem.persistence.HealthData;
import org.weathersystem.weatherinformationsystem.persistence.HealthDataPK;

public class JSONHealthData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer state;
	private String stationId;
	private Long time;
	private String batterylevel;
	private String status;
	private String barometer;
	private String anemometer;
	private String temperature;
	private String humidity;
	private String precipitation;
	private String sunshine;
	private String visibility;
	private String winddirection;
	private String clock;
	private String battery;
	private String recharging;

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
	 * @return the batterylevel
	 */
	public String getBatterylevel() {
		return batterylevel;
	}

	/**
	 * @param batterylevel the batterylevel to set
	 */
	public void setBatterylevel(String batterylevel) {
		this.batterylevel = batterylevel;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the barometer
	 */
	public String getBarometer() {
		return barometer;
	}

	/**
	 * @param barometer the barometer to set
	 */
	public void setBarometer(String barometer) {
		this.barometer = barometer;
	}

	/**
	 * @return the anemometer
	 */
	public String getAnemometer() {
		return anemometer;
	}

	/**
	 * @param anemometer the anemometer to set
	 */
	public void setAnemometer(String anemometer) {
		this.anemometer = anemometer;
	}

	/**
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the humidity
	 */
	public String getHumidity() {
		return humidity;
	}

	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	/**
	 * @return the precipitation
	 */
	public String getPrecipitation() {
		return precipitation;
	}

	/**
	 * @param precipitation the precipitation to set
	 */
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}

	/**
	 * @return the sunshine
	 */
	public String getSunshine() {
		return sunshine;
	}

	/**
	 * @param sunshine the sunshine to set
	 */
	public void setSunshine(String sunshine) {
		this.sunshine = sunshine;
	}

	/**
	 * @return the visibility
	 */
	public String getVisibility() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(String visibility) {
		this.visibility = visibility;
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
	 * @return the clock
	 */
	public String getClock() {
		return clock;
	}

	/**
	 * @param clock the clock to set
	 */
	public void setClock(String clock) {
		this.clock = clock;
	}

	/**
	 * @return the battery
	 */
	public String getBattery() {
		return battery;
	}

	/**
	 * @param battery the battery to set
	 */
	public void setBattery(String battery) {
		this.battery = battery;
	}

	/**
	 * @return the recharging
	 */
	public String getRecharging() {
		return recharging;
	}

	/**
	 * @param recharging the recharging to set
	 */
	public void setRecharging(String recharging) {
		this.recharging = recharging;
	}

	public HealthData convertToHealthData()
	{
		HealthData healthData = new HealthData();
		HealthDataPK pk = new HealthDataPK();
		
		/* Set the attributes of the primary key */
		pk.setStationId(this.getStationId()); 
		Timestamp time = convertToTimestamp(this.getTime());
		pk.setTime(time);
		healthData.setPk(pk);  //Now set the Primary Key of the class
		healthData.setBatteryLevel(convertToInteger(this.getBatterylevel()));
		healthData.setStationStatus(checkIfNull(this.getStatus()));
		healthData.setBatteryCharging(checkIfNull(this.getRecharging()));

		Boolean value = null;
		value = convertToBoolean(this.getBarometer());
		healthData.setBarometerSensor(value);
		
		value = convertToBoolean(this.getAnemometer());
		healthData.setAnemometerSensor(value);
		
		value = convertToBoolean(this.getTemperature());
		healthData.setTemperatureSensor(value);
		
		value = convertToBoolean(this.getHumidity());
		healthData.setHumiditySensor(value);
		
		value = convertToBoolean(this.getPrecipitation());
		healthData.setPrecipitionSensor(value);
		
		value = convertToBoolean(this.getSunshine());
		healthData.setSunshineSensor(value);
		
		value = convertToBoolean(this.getVisibility());
		healthData.setVisibilitySensor(value);
		
		value = convertToBoolean(this.getWinddirection());
		healthData.setWindDirectionSensor(value);
		
		value = convertToBoolean(this.getClock());
		healthData.setClockSensor(value);
		
		value = convertToBoolean(this.getBattery());
		healthData.setBatterySensor(value);
		return healthData;
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
	
	/*
	 *Convert the Strings (up or down from JSON string) into booleans and set in HealthData object 
	 */
	public Boolean convertToBoolean(String s)
	{
		Boolean b = null;
		if(s == null)
		{
			return b;
		}
		else if(s.equals("down"))
		{
			b = new Boolean(false);
		}
		else if(s.equals("up"))
		{
			b = new Boolean(true);
		}
		return b;
	}
	
	public Integer convertToInteger(String s) {
		try {
			return Integer.parseInt(s);
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
		return "JSONHealthData [state=" + state + ", stationId=" + stationId
				+ ", time=" + time + ", batterylevel=" + batterylevel
				+ ", status=" + status + ", barometer=" + barometer
				+ ", anemometer=" + anemometer + ", temperature=" + temperature
				+ ", humidity=" + humidity + ", precipitation=" + precipitation
				+ ", sunshine=" + sunshine + ", visibility=" + visibility
				+ ", winddirection=" + winddirection + ", clock=" + clock
				+ ", battery=" + battery + ", recharging=" + recharging + "]";
	}
}