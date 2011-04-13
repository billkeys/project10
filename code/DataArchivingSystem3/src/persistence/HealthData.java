package persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the health_data database table.
 * 
 */
@Entity
@Table(name="health_data")
@NamedQueries({@NamedQuery(name="getHealthData", query = "SELECT h FROM HealthData h"),
@NamedQuery(name="getHealthDataByPk_stationId", query = "SELECT h FROM HealthData h WHERE h.pk.stationId = :pk_stationId"),
@NamedQuery(name="getHealthDataByPk_time", query = "SELECT h FROM HealthData h WHERE h.pk.time = :pk_time"),
@NamedQuery(name="getHealthDataByBatteryLevel", query = "SELECT h FROM HealthData h WHERE h.batteryLevel = :batteryLevel"),
@NamedQuery(name="getHealthDataByStationStatus", query = "SELECT h FROM HealthData h WHERE h.stationStatus = :stationStatus"),
@NamedQuery(name="getHealthDataByBatteryCharging", query = "SELECT h FROM HealthData h WHERE h.batteryCharging = :batteryCharging"),
@NamedQuery(name="getHealthDataByBarometerSensor", query = "SELECT h FROM HealthData h WHERE h.barometerSensor = :barometerSensor"),
@NamedQuery(name="getHealthDataByAnemometerSensor", query = "SELECT h FROM HealthData h WHERE h.anemometerSensor = :anemometerSensor"),
@NamedQuery(name="getHealthDataByTemperatureSensor", query = "SELECT h FROM HealthData h WHERE h.temperatureSensor = :temperatureSensor"),
@NamedQuery(name="getHealthDataByHumiditySensor", query = "SELECT h FROM HealthData h WHERE h.humiditySensor = :humiditySensor"),
@NamedQuery(name="getHealthDataByPrecipitionSensor", query = "SELECT h FROM HealthData h WHERE h.precipitionSensor = :precipitionSensor"),
@NamedQuery(name="getHealthDataBySunshineSensor", query = "SELECT h FROM HealthData h WHERE h.sunshineSensor = :sunshineSensor"),
@NamedQuery(name="getHealthDataByVisibilitySensor", query = "SELECT h FROM HealthData h WHERE h.visibilitySensor = :visibilitySensor"),
@NamedQuery(name="getHealthDataByWindDirectionSensor", query = "SELECT h FROM HealthData h WHERE h.windDirectionSensor = :windDirectionSensor"),
@NamedQuery(name="getHealthDataByClockSensor", query = "SELECT h FROM HealthData h WHERE h.clockSensor = :clockSensor"),
@NamedQuery(name="getHealthDataByBatterySensor", query = "SELECT h FROM HealthData h WHERE h.batterySensor = :batterySensor")})
public class HealthData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HealthDataPK pk;

	@Column(name="anemometer_sensor")
	private Boolean anemometerSensor;

	@Column(name="barometer_sensor")
	private Boolean barometerSensor;

	@Column(name="battery_charging", length=8)
	private String batteryCharging;

	@Column(name="battery_level")
	private Integer batteryLevel;

	@Column(name="battery_sensor")
	private Boolean batterySensor;

	@Column(name="clock_sensor")
	private Boolean clockSensor;

	@Column(name="humidity_sensor")
	private Boolean humiditySensor;

	@Column(name="precipition_sensor")
	private Boolean precipitionSensor;

	@Column(name="station_status", length=32)
	private String stationStatus;

	@Column(name="sunshine_sensor")
	private Boolean sunshineSensor;

	@Column(name="temperature_sensor")
	private Boolean temperatureSensor;

	@Column(name="visibility_sensor")
	private Boolean visibilitySensor;

	@Column(name="wind_direction_sensor")
	private Boolean windDirectionSensor;

    public HealthData() {
    }

	public HealthDataPK getPk() {
		return this.pk;
	}

	public void setPk(HealthDataPK pk) {
		this.pk = pk;
	}
	
	public Boolean getAnemometerSensor() {
		return this.anemometerSensor;
	}

	public void setAnemometerSensor(Boolean anemometerSensor) {
		this.anemometerSensor = anemometerSensor;
	}

	public Boolean getBarometerSensor() {
		return this.barometerSensor;
	}

	public void setBarometerSensor(Boolean barometerSensor) {
		this.barometerSensor = barometerSensor;
	}

	public String getBatteryCharging() {
		return this.batteryCharging;
	}

	public void setBatteryCharging(String batteryCharging) {
		this.batteryCharging = batteryCharging;
	}

	public Integer getBatteryLevel() {
		return this.batteryLevel;
	}

	public void setBatteryLevel(Integer batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public Boolean getBatterySensor() {
		return this.batterySensor;
	}

	public void setBatterySensor(Boolean batterySensor) {
		this.batterySensor = batterySensor;
	}

	public Boolean getClockSensor() {
		return this.clockSensor;
	}

	public void setClockSensor(Boolean clockSensor) {
		this.clockSensor = clockSensor;
	}

	public Boolean getHumiditySensor() {
		return this.humiditySensor;
	}

	public void setHumiditySensor(Boolean humiditySensor) {
		this.humiditySensor = humiditySensor;
	}

	public Boolean getPrecipitionSensor() {
		return this.precipitionSensor;
	}

	public void setPrecipitionSensor(Boolean precipitionSensor) {
		this.precipitionSensor = precipitionSensor;
	}

	public String getStationStatus() {
		return this.stationStatus;
	}

	public void setStationStatus(String stationStatus) {
		this.stationStatus = stationStatus;
	}

	public Boolean getSunshineSensor() {
		return this.sunshineSensor;
	}

	public void setSunshineSensor(Boolean sunshineSensor) {
		this.sunshineSensor = sunshineSensor;
	}

	public Boolean getTemperatureSensor() {
		return this.temperatureSensor;
	}

	public void setTemperatureSensor(Boolean temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	public Boolean getVisibilitySensor() {
		return this.visibilitySensor;
	}

	public void setVisibilitySensor(Boolean visibilitySensor) {
		this.visibilitySensor = visibilitySensor;
	}

	public Boolean getWindDirectionSensor() {
		return this.windDirectionSensor;
	}

	public void setWindDirectionSensor(Boolean windDirectionSensor) {
		this.windDirectionSensor = windDirectionSensor;
	}

	@Override
	public String toString() {
		return "HealthData [id=" + pk + ", anemometerSensor="
				+ anemometerSensor + ", barometerSensor=" + barometerSensor
				+ ", batteryCharging=" + batteryCharging + ", batteryLevel="
				+ batteryLevel + ", batterySensor=" + batterySensor
				+ ", clockSensor=" + clockSensor + ", humiditySensor="
				+ humiditySensor + ", precipitionSensor=" + precipitionSensor
				+ ", stationStatus=" + stationStatus + ", sunshineSensor="
				+ sunshineSensor + ", temperatureSensor=" + temperatureSensor
				+ ", visibilitySensor=" + visibilitySensor
				+ ", windDirectionSensor=" + windDirectionSensor + "]";
	}
}