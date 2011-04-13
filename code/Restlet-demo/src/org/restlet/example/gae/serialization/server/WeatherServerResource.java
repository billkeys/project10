package org.restlet.example.gae.serialization.server;

import org.restlet.example.common.Weather;
import org.restlet.example.common.WeatherResource;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 * The server side implementation of the Restlet resource.
 */
public class WeatherServerResource extends ServerResource implements
        WeatherResource {

	private static volatile Weather weather = new Weather(70,1);

	    @Delete
	    public void remove() {
	        weather = null;
	    }

	    @Get
	    public Weather retrieve() {
	        return weather;
	    }

	    @Put
	    public void store(Weather weather) {
	        WeatherServerResource.weather = weather;
	    }
	}