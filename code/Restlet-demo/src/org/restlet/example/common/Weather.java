package org.restlet.example.common;

import java.io.Serializable;


public class Weather implements Serializable {

	private static final long serialVersionUID = 1L;

	private int temp;

	private int wind;

	public Weather() {
	}

	public Weather(int temp, int wind) {
		super();
		this.temp = temp;
		this.wind = wind;
	}

	public int getWind() {
		return wind;
	}

	public int getTemp() {
		return temp;
	}

	public void setWind(int wind) {
		this.wind = wind;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	
}

