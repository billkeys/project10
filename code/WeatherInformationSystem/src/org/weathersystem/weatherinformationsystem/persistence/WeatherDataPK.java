package org.weathersystem.weatherinformationsystem.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the weather_data database table.
 * 
 */
@Embeddable
public class WeatherDataPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="station_id", unique=true, nullable=false, length=16)
	private String stationId;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(unique=true, nullable=false)
	private java.util.Date time;

    public WeatherDataPK() {
    }
	public String getStationId() {
		return this.stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public java.util.Date getTime() {
		return this.time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WeatherDataPK)) {
			return false;
		}
		WeatherDataPK castOther = (WeatherDataPK)other;
		return 
			this.stationId.equals(castOther.stationId)
			&& this.time.equals(castOther.time);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.stationId.hashCode();
		hash = hash * prime + this.time.hashCode();
		
		return hash;
    }
}