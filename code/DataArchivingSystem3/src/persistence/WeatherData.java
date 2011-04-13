package persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the weather_data database table.
 * 
 */
@Entity
@Table(name="weather_data")
@NamedQueries({@NamedQuery(name="getWeatherData", query = "SELECT w FROM WeatherData w"),
@NamedQuery(name="getWealthDataByPk_stationId", query = "SELECT w FROM WeatherData w WHERE w.pk.stationId = :pk_stationId"),
@NamedQuery(name="getWeatherDataByPk_time", query = "SELECT w FROM WeatherData w WHERE w.pk.time = :pk_time"),
@NamedQuery(name="getWeatherDataByAirPressure", query = "SELECT w FROM WeatherData w WHERE w.airPressure = :airPressure"),
@NamedQuery(name="getWeatherDataByWindSpeed", query = "SELECT w FROM WeatherData w WHERE w.windSpeed = :windSpeed"),
@NamedQuery(name="getWeatherDataByAirTemperature", query = "SELECT w FROM WeatherData w WHERE w.airTemperature = :airTemperature"),
@NamedQuery(name="getWeatherDataByGroundTemperature", query = "SELECT w FROM WeatherData w WHERE w.groundTemperature = :groundTemperature"),
@NamedQuery(name="getWeatherDataByPrecipition", query = "SELECT w FROM WeatherData w WHERE w.precipition = :precipition"),
@NamedQuery(name="getWeatherDataByHumidity", query = "SELECT w FROM WeatherData w WHERE w.humidity = :humidity"),
@NamedQuery(name="getWeatherDataBySolarRadiation", query = "SELECT w FROM WeatherData w WHERE w.solarRadiation = :solarRadiation"),
@NamedQuery(name="getWeatherDataByDewPoint", query = "SELECT w FROM WeatherData w WHERE w.dewPoint = :dewPoint"),
@NamedQuery(name="getWeatherDataByWindDirection", query = "SELECT w FROM WeatherData w WHERE w.windDirection = :windDirection")})
public class WeatherData implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WeatherDataPK pk;

	@Column(name="air_pressure", precision=10, scale=5)
	private Double airPressure;

	@Column(name="air_temperature")
	private Integer airTemperature;

	@Column(name="dew_point")
	private Integer dewPoint;

	@Column(name="ground_temperature")
	private Integer groundTemperature;

	private Integer humidity;

	private Integer precipition;

	@Column(name="solar_radiation")
	private Integer solarRadiation;

	@Column(name="wind_direction", length=16)
	private String windDirection;

	@Column(name="wind_speed")
	private Integer windSpeed;

    public WeatherData() {
    }

	public WeatherDataPK getPk() {
		return this.pk;
	}

	public void setPk(WeatherDataPK pk) {
		this.pk = pk;
	}
	
	public Double getAirPressure() {
		return this.airPressure;
	}

	public void setAirPressure(Double airPressure) {
		this.airPressure = airPressure;
	}

	public Integer getAirTemperature() {
		return this.airTemperature;
	}

	public void setAirTemperature(Integer airTemperature) {
		this.airTemperature = airTemperature;
	}

	public Integer getDewPoint() {
		return this.dewPoint;
	}

	public void setDewPoint(Integer dewPoint) {
		this.dewPoint = dewPoint;
	}

	public Integer getGroundTemperature() {
		return this.groundTemperature;
	}

	public void setGroundTemperature(Integer groundTemperature) {
		this.groundTemperature = groundTemperature;
	}

	public Integer getHumidity() {
		return this.humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Integer getPrecipition() {
		return this.precipition;
	}

	public void setPrecipition(Integer precipition) {
		this.precipition = precipition;
	}

	public Integer getSolarRadiation() {
		return this.solarRadiation;
	}

	public void setSolarRadiation(Integer solarRadiation) {
		this.solarRadiation = solarRadiation;
	}

	public String getWindDirection() {
		return this.windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public Integer getWindSpeed() {
		return this.windSpeed;
	}

	public void setWindSpeed(Integer windSpeed) {
		this.windSpeed = windSpeed;
	}

	@Override
	public String toString() {
		return "WeatherData [pk=" + pk + ", airPressure=" + airPressure
				+ ", airTemperature=" + airTemperature + ", dewPoint="
				+ dewPoint + ", groundTemperature=" + groundTemperature
				+ ", humidity=" + humidity + ", precipition=" + precipition
				+ ", solarRadiation=" + solarRadiation + ", windDirection="
				+ windDirection + ", windSpeed=" + windSpeed + "]";
	}

}