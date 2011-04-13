package main;

import org.eclipse.persistence.sessions.Session;

import persistence.HealthData;
import persistence.controller.HealthDataManager;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HealthData healthData = new HealthData();
			HealthDataManager manager = new HealthDataManager();
			healthData.setStationId(1);
			healthData.setStationStatus("up");
			healthData.setAirTempSensor((byte) 0);
			healthData.setAnemometerSensor((byte) 1);
			healthData.setBarometerSensor((byte) 1);
			healthData.setBatterySensor((byte) 1);
			healthData.setClockSensor((byte) 1);
			healthData.setDewpointSensor((byte) 1);
			healthData.setGroundTempSensor((byte) 0);
			healthData.setPrecipitionSensor((byte) 0);
			healthData.setSunshineSensor((byte) 0);
			healthData.setVisibilitySensor((byte) 0);

			manager.createHealthData(healthData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
