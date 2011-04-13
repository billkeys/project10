package org.weathersystem.weatherstation.southafrica.common;
import java.io.Serializable;


public class Health implements Serializable {
	private static final long serialVersionUID = 1L;

	private String temperature;
	private String anemometer;
	private String barometer;
	private int state;
	private String precipitation;
	private String humidity;	
	private String sunshine;	
	private String clock;
	private String status;
	private String battery;
	private String batterylevel;
	private String time;
	private String stationId;
	private String winddirection;
	private String visibility;
	private String recharging;
	
	public Health() {
	}

	public Health(int state,String barometer,String anemometer,String temperature,String stationId,String precipitation,String visibility,String sunshine,String humidity,String clock,String status,String battery,String batterylevel,String time,String winddirection){
		super();
		this.state = state;
		this.barometer = barometer;
		this.temperature = temperature;
		this.anemometer = anemometer;
		this.precipitation= precipitation;
		this.humidity = humidity;	
		this.sunshine= sunshine;			
		this.clock = clock;
		this.status = status;
		this.battery = battery;
		this.batterylevel = batterylevel;
		this.time = time;
		this.stationId = stationId;
		this.winddirection = winddirection;
		this.visibility= visibility;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getAnemometer() {
		return anemometer;
	}

	public void setAnemometer(String anemometer) {
		this.anemometer = anemometer;
	}

	public String getBarometer() {
		return barometer;
	}

	public void setBarometer(String barometer) {
		this.barometer = barometer;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getSunshine() {
		return sunshine;
	}

	public void setSunshine(String sunshine) {
		this.sunshine = sunshine;
	}

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public String getBatterylevel() {
		return batterylevel;
	}

	public void setBatterylevel(String batterylevel) {
		this.batterylevel = batterylevel;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getWinddirection() {
		return winddirection;
	}

	public void setWinddirection(String winddirection) {
		this.winddirection = winddirection;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
	public String getRecharging() {
		return recharging;
	}

	public void setRecharging(String recharging) {
		this.recharging = recharging;
	}
}