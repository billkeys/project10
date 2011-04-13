package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Temporal;

@Entity
@Table(name="weather_data")
@NamedQueries({@NamedQuery(name="getWeatherData", query = "SELECT w FROM WeatherData w"),@NamedQuery(name="getWeatherDataOrdered", query = "SELECT w FROM WeatherData w ORDER BY w.stationId"),
@NamedQuery(name="getWeatherDataByAirPressure", query = "SELECT w FROM WeatherData w WHERE w.airPressure = :airPressure"),
@NamedQuery(name="getWeatherDataByWindSpeed", query = "SELECT w FROM WeatherData w WHERE w.windSpeed = :windSpeed"),
@NamedQuery(name="getWeatherDataByAirTemperature", query = "SELECT w FROM WeatherData w WHERE w.airTemperature = :airTemperature"),
@NamedQuery(name="getWeatherDataByGroundTemperature", query = "SELECT w FROM WeatherData w WHERE w.groundTemperature = :groundTemperature"),
@NamedQuery(name="getWeatherDataByPrecipition", query = "SELECT w FROM WeatherData w WHERE w.precipition = :precipition"),
@NamedQuery(name="getWeatherDataByHumidity", query = "SELECT w FROM WeatherData w WHERE w.humidity = :humidity"),
@NamedQuery(name="getWeatherDataBySolarRadiation", query = "SELECT w FROM WeatherData w WHERE w.solarRadiation = :solarRadiation"),
@NamedQuery(name="getWeatherDataByDewPoint", query = "SELECT w FROM WeatherData w WHERE w.dewPoint = :dewPoint"),
@NamedQuery(name="getWeatherDataByWindDirection", query = "SELECT w FROM WeatherData w WHERE w.windDirection = :windDirection"),
@NamedQuery(name="getWeatherDataByTime", query = "SELECT w FROM WeatherData w WHERE w.time = :time")})
public class WeatherData implements Serializable {
	@Id
	@Column(name="station_id")
	private int stationId;

	@Column(name="air_pressure")
	private String airPressure;

	@Column(name="wind_speed")
	private String windSpeed;

	@Column(name="air_temperature")
	private String airTemperature;

	@Column(name="ground_temperature")
	private String groundTemperature;

	private String precipition;

	private String humidity;

	@Column(name="solar_radiation")
	private String solarRadiation;

	@Column(name="dew_point")
	private String dewPoint;

	@Column(name="wind_direction")
	private String windDirection;

	private Temporal time;

	private static final long serialVersionUID = 1L;

	public WeatherData() {
		super();
	}

	public int getStationId() {
		return this.stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getAirPressure() {
		return this.airPressure;
	}

	public void setAirPressure(String airPressure) {
		this.airPressure = airPressure;
	}

	public String getWindSpeed() {
		return this.windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getAirTemperature() {
		return this.airTemperature;
	}

	public void setAirTemperature(String airTemperature) {
		this.airTemperature = airTemperature;
	}

	public String getGroundTemperature() {
		return this.groundTemperature;
	}

	public void setGroundTemperature(String groundTemperature) {
		this.groundTemperature = groundTemperature;
	}

	public String getPrecipition() {
		return this.precipition;
	}

	public void setPrecipition(String precipition) {
		this.precipition = precipition;
	}

	public String getHumidity() {
		return this.humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getSolarRadiation() {
		return this.solarRadiation;
	}

	public void setSolarRadiation(String solarRadiation) {
		this.solarRadiation = solarRadiation;
	}

	public String getDewPoint() {
		return this.dewPoint;
	}

	public void setDewPoint(String dewPoint) {
		this.dewPoint = dewPoint;
	}

	public String getWindDirection() {
		return this.windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public Temporal getTime() {
		return this.time;
	}

	public void setTime(Temporal time) {
		this.time = time;
	}

}
