package org.weathersystem.weatherstation.southafrica.client;

import org.restlet.client.resource.ClientProxy;
import org.restlet.client.resource.Delete;
import org.restlet.client.resource.Get;
import org.restlet.client.resource.Put;
import org.restlet.client.resource.Result;
import org.weathersystem.weatherstation.southafrica.common.Weather;

public interface WeatherResourceProxy extends ClientProxy {
    @Get
    public void retrieve(Result<Weather> callback);

    @Put
    public void store(Weather weather, Result<Void> callback);

    @Delete
    public void remove(Result<Void> callback);

}