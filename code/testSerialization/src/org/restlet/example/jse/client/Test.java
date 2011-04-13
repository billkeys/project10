package org.restlet.example.jse.client;

import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.example.common.Weather;
import org.restlet.example.common.WeatherResource;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class Test {

    public static void main(String[] args) throws ResourceException,
            IOException {
        ClientResource cr = new ClientResource(
                "http://weatherstationsouthafrica.appspot.com/weatherdata/temp");

        // Get the Weather object
        WeatherResource resource = cr.wrap(WeatherResource.class);
        Weather weather = resource.retrieve();
        if (weather != null) {
           System.out.println("firstname: " + weather.getTemp());
    
       }

        // In case the Weather object is not available
        // Get a JSON representation of the contact
        System.out.println("\nJSON representation");
        cr.get(MediaType.APPLICATION_JSON).write(System.out);
       
    }
}
