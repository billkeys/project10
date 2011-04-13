package org.weathersystem.weatherstation.southafrica.server.jpo;

import java.sql.Timestamp;
import java.util.Date;
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
public class WeatherRecord {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.unencoded-pk", value="true")	
	private Key key;

	@Persistent
	private int id;
	
	@Persistent
	private String stationId;
	
	@Persistent
	private Date time;
	
	@Persistent
	private Double airPressure;
	
	@Persistent
	private Integer airTemperature;
	
	@Persistent
	private Integer dewPoint;
	
	@Persistent
	private Integer groundTemperature;
	
	@Persistent
	private Integer humidity;
	
	@Persistent
	private Integer precipition;
	
	@Persistent
	private Integer solarRadiation;
	
	@Persistent
	private String windDirection;
	
	@Persistent
	private Integer windSpeed;
	
	public WeatherRecord( ){
				
	}
	
	public static WeatherRecord get(PersistenceManager pm, String resourceId) {
		try {
			return pm.getObjectById(WeatherRecord.class, resourceId);
		} catch (JDOObjectNotFoundException e){
	
		}
		
		return null;
	}
	
	public static void add(PersistenceManager pm, int id, int state) throws IOException{
		WeatherRecord ci = new WeatherRecord();
		
		pm.makePersistent(ci);
	}
	
	public static void delete(PersistenceManager pm, String resourceId){
		delete(pm, resourceId);
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
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}