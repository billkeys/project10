package org.weathersystem.weatherstation.southafrica.common;

import java.io.Serializable;


public class Weather implements Serializable {

	private static final long serialVersionUID = 1L;

	private String airtemperature;
	private String windspeed;
	private String airpressure;
	private int state;
	private String  groundtemperature;
	private String  precipition;
	private String  humidity;	
	private String  solarradiation;	
	private String  dewpoint;
	private String  winddirection;	
	private String  time;
	private String  stationId;
	public Weather() {
	}

	public Weather(int state, String airpressure, String airtemperature,String windspeed,String groundtemperature,String precipition,String humidity,String solarradiation,String dewpoint, String winddirection,String time,String stationId) {
		super();
		this.stationId = stationId;
		this.state = state;
		this.airpressure = airpressure;
		this.airtemperature = airtemperature;
		this.windspeed = windspeed;
		this.precipition= precipition;
		this.humidity = humidity;	
		this.solarradiation= solarradiation;	
		this.dewpoint = dewpoint;
		this.winddirection= winddirection;	
		this.time= time;
		this.groundtemperature = groundtemperature;
		
	}

	public String getAirtemperature() {
		return airtemperature;
	}

	public void setAirtemperature(String airtemperature) {
		this.airtemperature = airtemperature;
	}

	public String getWindspeed() {
		return windspeed;
	}

	public void setWindspeed(String windspeed) {
		this.windspeed = windspeed;
	}

	public String getAirpressure() {
		return airpressure;
	}

	public void setAirpressure(String airpressure) {
		this.airpressure = airpressure;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getGroundtemperature() {
		return groundtemperature;
	}

	public void setGroundtemperature(String groundtemperature) {
		this.groundtemperature = groundtemperature;
	}

	public String getPrecipition() {
		return precipition;
	}

	public void setPrecipition(String precipition) {
		this.precipition = precipition;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getSolarradiation() {
		return solarradiation;
	}

	public void setSolarradiation(String solarradiation) {
		this.solarradiation = solarradiation;
	}

	public String getDewpoint() {
		return dewpoint;
	}

	public void setDewpoint(String dewpoint) {
		this.dewpoint = dewpoint;
	}

	public String getWinddirection() {
		return winddirection;
	}

	public void setWinddirection(String winddirection) {
		this.winddirection = winddirection;
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
	
}

