package org.weathersystem.weatherstation.southafrica.common;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

/**
 * The resource associated to a contact.
 */
public interface WeatherResource {

    @Get
    public Weather retrieve();

    @Put
    public void store(Weather weather);

    @Delete
    public void remove();

}
