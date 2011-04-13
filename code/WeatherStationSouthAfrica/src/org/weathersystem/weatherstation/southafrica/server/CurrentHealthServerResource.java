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

/**
 * The server side implementation of the Restlet resource.
 */
public class CurrentHealthServerResource extends ServerResource implements
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
		CurrentHealthServerResource.health = health;
	}
	
	/*
	 *  Helper function
	 */
	private static  Health createHealth() {
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream("healthdata.prop"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		int state = 1;

		PersistenceManager pm = PMF.get().getPersistenceManager();
		String query = "select from " + HealthRecord.class.getName() + " where id == 1";
		List<HealthRecord> states = (List<HealthRecord>) pm.newQuery(query).execute();
	
		Health health = null;

		if (states.isEmpty()) {
			
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
			String  time = Long.toString(System.currentTimeMillis());;
			
			health = new Health(state, barometer, anemometer, temperature,
					stationId, precipitation, visibility, sunshine, humidity,
					clock, status, battery, batterylevel, time, winddirection);
			health.setRecharging("No");
			
			HealthRecord ci = new HealthRecord(1, state + 1);
			ci.setAnemometer(anemometer);
			ci.setBarometer(barometer);
			ci.setBattery(battery);
			ci.setBatterylevel(batterylevel);
			ci.setClock(clock);
			ci.setHumidity(humidity);
			ci.setPrecipitation(precipitation);
			ci.setStationId(stationId);
			ci.setStatus(status);
			ci.setSunshine(sunshine);
			ci.setTemperature(temperature);
			ci.setTime(time);
			ci.setVisibility(visibility);
			ci.setWinddirection(winddirection);
			ci.setChargingStartTime(System.currentTimeMillis());
			ci.setCharging("No");
			pm.makePersistent(ci);
			pm.close();
		}
		else {
			
			for(HealthRecord item : states ){
				long batteryLevel;
				long currentTime = System.currentTimeMillis();
				long startCharge = item.getChargingStartTime();
				
				if(item.getCharging().equals("yes")) {
					long secDiff = (currentTime - startCharge)/3000;
					
					batteryLevel = Integer.parseInt(item.getBatterylevel().trim()) + secDiff;
					
					if(batteryLevel>=100){
						batteryLevel = 100;
						item.setCharging("no");
						item.setChargingStartTime(System.currentTimeMillis());
					}
					if (batteryLevel < 20 && batteryLevel !=0){
						item.setStatus("Powered On (Low Battery Power)");
					}
					else if(batteryLevel>=20 && batteryLevel<=30 ){
						item.setStatus("Powered On");
					}
					else if (batteryLevel == 0){
						item.setStatus("Powered Off (Battery Depleted)");
					}
				}
				else {
					long secDiff = (currentTime - startCharge)/10000;
					batteryLevel = Integer.parseInt(item.getBatterylevel().trim()) - secDiff;
					if (batteryLevel <= 0) {
						batteryLevel = 0;
						item.setStatus("Powered Off (Battery Depleted)");
					}
					else if (batteryLevel < 20 ){
						item.setStatus("Powered On (Low Battery Power)");
			
					}
				}
				
				health = new Health(item.getState(), item.getBarometer(), item.getAnemometer(), item.getTemperature(),
			                    	item.getStationId(), item.getPrecipitation(), item.getVisibility(), item.getSunshine(), item.getHumidity(),
				                    item.getClock(), item.getStatus(), item.getBattery(), Long.toString(batteryLevel),Long.toString(currentTime), item.getWinddirection());			
				health.setRecharging(item.getCharging());
				item.setBatterylevel(Long.toString(batteryLevel));
			}
			pm.close();
		}

		return health;
	}
}