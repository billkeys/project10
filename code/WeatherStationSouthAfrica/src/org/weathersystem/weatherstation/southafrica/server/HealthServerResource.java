package org.weathersystem.weatherstation.southafrica.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;

import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.weathersystem.weatherstation.southafrica.common.Health;
import org.weathersystem.weatherstation.southafrica.common.HealthResource;
import org.weathersystem.weatherstation.southafrica.server.jpo.HealthRecord;
import org.weathersystem.weatherstation.southafrica.server.jpo.PMF;
import org.weathersystem.weatherstation.southafrica.server.jpo.StateRecord;

/**
 * The server side implementation of the Restlet resource.
 */
public class HealthServerResource extends ServerResource implements
HealthResource {
	
	private static volatile Health health =  createHealth();
	
	@Override
	@Delete
	public void remove() {
		health = null;
	}

	@Override
	@Get
	public Health retrieve() {
		return createHealth();
	}

	@Override
	@Put
	public void store(Health health) {
		HealthServerResource.health = health;
	}
	
	/*
	 *  Helper function
	 */
	private static  Health createHealth() {
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("healthdata.prop"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int state = 1;

		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + StateRecord.class.getName() + " where id == 2";
		List<StateRecord> states = (List<StateRecord>) pm.newQuery(query).execute();
		for(StateRecord item : states ){
			state = item.getState();
			pm.deletePersistent(item);
		}

		//get the property value and print it out
		String status = prop.getProperty("southafrica.status."+state);
		String stationId = prop.getProperty("southafrica.stationId");
		String	barometer = prop.getProperty("southafrica.barometer.sensor1."+ state );
		String	anemometer = prop.getProperty("southafrica.anemometer.sensor1."+ state );
		String	temperature = prop.getProperty("southafrica.temperature.sensor1."+ state );
		String	winddirection = prop.getProperty("southafrica.winddirection.sensor1."+ state );
		String	precipitation = prop.getProperty("southafrica.precipitation.sensor1."+ state );
		String	sunshine = prop.getProperty("southafrica.sunshine.sensor1."+ state );
		String	visibility = prop.getProperty("southafrica.visibility.sensor1."+ state );
		String	clock = prop.getProperty("southafrica.clock.sensor1."+ state );
		String	humidity = prop.getProperty("southafrica.humidity.sensor1."+ state );
		String	battery = prop.getProperty("southafrica.battery.sensor1."+ state );
		String	batterylevel = prop.getProperty("southafrica.batterylevel."+ state );
		long  time =  System.currentTimeMillis();
		
		Health health = new Health(state,barometer,anemometer,temperature,stationId,precipitation,visibility,sunshine,humidity,clock, status,battery,batterylevel,Long.toString(time),winddirection);

		try {

			if (state == 3){
				state = 0;
			}
			StateRecord.add(pm,2,state+1);
			
			HealthRecord ci = new HealthRecord(1, state+1);
			ci.setAnemometer(anemometer);
			ci.setBarometer(barometer);
			ci.setBatterylevel(batterylevel);
			ci.setBattery(battery);
			ci.setClock(clock);
			ci.setHumidity(humidity);
			ci.setPrecipitation(precipitation);
			ci.setStationId(stationId);
			ci.setStatus(status);
			ci.setSunshine(sunshine);
			ci.setTemperature(temperature);
			ci.setTime(Long.toString(time));
			ci.setVisibility(visibility);
			ci.setWinddirection(winddirection);
			
			pm.makePersistent(ci);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pm.close();
		}

		return health;
	}
}