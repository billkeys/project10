package org.weathersystem.weatherstation.southafrica.common;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;

/**
 * The resource associated to a contact.
 */
public interface HealthResource {

    @Get
    public Health retrieve();

    @Put
    public void store(Health health);

    @Delete
    public void remove();

}