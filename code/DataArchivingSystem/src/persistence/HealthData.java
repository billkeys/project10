package persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@Table(name="health_data")
@NamedQueries({@NamedQuery(name="getHealthData", query = "SELECT h FROM HealthData h"),@NamedQuery(name="getHealthDataOrdered", query = "SELECT h FROM HealthData h ORDER BY h.stationId"),
@NamedQuery(name="getHealthDataByStationStatus", query = "SELECT h FROM HealthData h WHERE h.stationStatus = :stationStatus"),
@NamedQuery(name="getHealthDataByBarometerSensor", query = "SELECT h FROM HealthData h WHERE h.barometerSensor = :barometerSensor"),
@NamedQuery(name="getHealthDataByAnemometerSensor", query = "SELECT h FROM HealthData h WHERE h.anemometerSensor = :anemometerSensor"),
@NamedQuery(name="getHealthDataByGroundTempSensor", query = "SELECT h FROM HealthData h WHERE h.groundTempSensor = :groundTempSensor"),
@NamedQuery(name="getHealthDataByAirTempSensor", query = "SELECT h FROM HealthData h WHERE h.airTempSensor = :airTempSensor"),
@NamedQuery(name="getHealthDataByPrecipitionSensor", query = "SELECT h FROM HealthData h WHERE h.precipitionSensor = :precipitionSensor"),
@NamedQuery(name="getHealthDataBySunshineSensor", query = "SELECT h FROM HealthData h WHERE h.sunshineSensor = :sunshineSensor"),
@NamedQuery(name="getHealthDataByVisibilitySensor", query = "SELECT h FROM HealthData h WHERE h.visibilitySensor = :visibilitySensor"),
@NamedQuery(name="getHealthDataByClockSensor", query = "SELECT h FROM HealthData h WHERE h.clockSensor = :clockSensor"),
@NamedQuery(name="getHealthDataByDewpointSensor", query = "SELECT h FROM HealthData h WHERE h.dewpointSensor = :dewpointSensor"),
@NamedQuery(name="getHealthDataByBatterySensor", query = "SELECT h FROM HealthData h WHERE h.batterySensor = :batterySensor")})
public class HealthData implements Serializable {
	@Id
	@Column(name="station_id")
	private int stationId;

	@Column(name="station_status")
	private String stationStatus;

	@Column(name="barometer_sensor")
	private byte barometerSensor;

	@Column(name="anemometer_sensor")
	private byte anemometerSensor;

	@Column(name="ground_temp_sensor")
	private byte groundTempSensor;

	@Column(name="air_temp_sensor")
	private byte airTempSensor;

	@Column(name="precipition_sensor")
	private byte precipitionSensor;

	@Column(name="sunshine_sensor")
	private byte sunshineSensor;

	@Column(name="visibility_sensor")
	private byte visibilitySensor;

	@Column(name="clock_sensor")
	private byte clockSensor;

	@Column(name="dewpoint_sensor")
	private byte dewpointSensor;

	@Column(name="battery_sensor")
	private byte batterySensor;

	private static final long serialVersionUID = 1L;

	public HealthData() {
		super();
	}

	public int getStationId() {
		return this.stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getStationStatus() {
		return this.stationStatus;
	}

	public void setStationStatus(String stationStatus) {
		this.stationStatus = stationStatus;
	}

	public byte getBarometerSensor() {
		return this.barometerSensor;
	}

	public void setBarometerSensor(byte barometerSensor) {
		this.barometerSensor = barometerSensor;
	}

	public byte getAnemometerSensor() {
		return this.anemometerSensor;
	}

	public void setAnemometerSensor(byte anemometerSensor) {
		this.anemometerSensor = anemometerSensor;
	}

	public byte getGroundTempSensor() {
		return this.groundTempSensor;
	}

	public void setGroundTempSensor(byte groundTempSensor) {
		this.groundTempSensor = groundTempSensor;
	}

	public byte getAirTempSensor() {
		return this.airTempSensor;
	}

	public void setAirTempSensor(byte airTempSensor) {
		this.airTempSensor = airTempSensor;
	}

	public byte getPrecipitionSensor() {
		return this.precipitionSensor;
	}

	public void setPrecipitionSensor(byte precipitionSensor) {
		this.precipitionSensor = precipitionSensor;
	}

	public byte getSunshineSensor() {
		return this.sunshineSensor;
	}

	public void setSunshineSensor(byte sunshineSensor) {
		this.sunshineSensor = sunshineSensor;
	}

	public byte getVisibilitySensor() {
		return this.visibilitySensor;
	}

	public void setVisibilitySensor(byte visibilitySensor) {
		this.visibilitySensor = visibilitySensor;
	}

	public byte getClockSensor() {
		return this.clockSensor;
	}

	public void setClockSensor(byte clockSensor) {
		this.clockSensor = clockSensor;
	}

	public byte getDewpointSensor() {
		return this.dewpointSensor;
	}

	public void setDewpointSensor(byte dewpointSensor) {
		this.dewpointSensor = dewpointSensor;
	}

	public byte getBatterySensor() {
		return this.batterySensor;
	}

	public void setBatterySensor(byte batterySensor) {
		this.batterySensor = batterySensor;
	}

}
