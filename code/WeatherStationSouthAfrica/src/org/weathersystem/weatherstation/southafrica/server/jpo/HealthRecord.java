package org.weathersystem.weatherstation.southafrica.server.jpo;
import java.io.IOException;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class HealthRecord {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.unencoded-pk", value="true")	
	private Key key;
	
	@Persistent
	private int id;
	
	@Persistent
	private int state;
	
	@Persistent
	private String temperature;
	
	@Persistent
	private String anemometer;
	
	@Persistent
	private String barometer;
	
	@Persistent
	private String  precipitation;
	
	@Persistent
	private String  humidity;	
	
	@Persistent
	private String  sunshine;	
	
	@Persistent
	private String  clock;
	
	@Persistent
	private String  status;
	
	@Persistent
	private String  battery;
	
	@Persistent
	private String batterylevel;
	
	@Persistent
	private String  time;
	
	@Persistent
	private String  stationId;
	
	@Persistent
	private String winddirection;
	
	@Persistent
	private String visibility;
	
	@Persistent
	private String charging;
	
	@Persistent
	private long chargingStartTime;
	
	public HealthRecord( int id, int state){
		setID(id);
		setState(state);		
	}
	
	public static HealthRecord get(PersistenceManager pm, String resourceId) {
		try {
			return pm.getObjectById(HealthRecord.class, resourceId);
		} catch (JDOObjectNotFoundException e){
	
		}
		
		return null;
	}
	
	public static void add(PersistenceManager pm, int id, int state) throws IOException{
		HealthRecord ci = new HealthRecord(id, state);
		
		pm.makePersistent(ci);
	}
	
	public static void delete(PersistenceManager pm, String resourceId){
		delete(pm, resourceId);
	}

	public void setState(int state) {
		this.state = state;
	}


	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public int getState() {
		return state;
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
	
	public String getCharging() {
		return charging;
	}

	public void setCharging(String charging) {
		this.charging = charging;
	}
	
	public long getChargingStartTime() {
		return chargingStartTime;
	}

	public void setChargingStartTime(long chargingStartTime) {
		this.chargingStartTime = chargingStartTime;
	}
}