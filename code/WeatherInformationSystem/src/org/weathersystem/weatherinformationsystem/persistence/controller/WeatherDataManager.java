package org.weathersystem.weatherinformationsystem.persistence.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.weathersystem.weatherinformationsystem.persistence.WeatherData;

public class WeatherDataManager {

public WeatherDataManager() {
		
	}

	/*
	 * Creates an EntityManagerFactory and gets an EntityManager from it
	 */
	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("WeatherInformationSystem", System.getProperties());
		return emf.createEntityManager();
	}
	
	/*
	 * Inserts a WeatherData object into the weather_data table in the database
	 * Returns a success message if the row was successfully created
	 */
	public String createWeatherData(WeatherData weatherData) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(weatherData);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "success";
	}
	
	/*
	 * Retrieves all rows in from the weather_data table with the specified stationId
	 * Returns the data in a list of WeatherData objects
	 */
	public List<WeatherData> getWeatherDataByStationId(String stationId) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWealthDataByPk_stationId");
			query.setParameter("pk_stationId", stationId);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}
	
	/*
	 * Retrieves every row in the weather_data table and returns the data
	 * in a list of WeatherData objects
	 */
	public List<WeatherData> getWeatherData() {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherData");
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}
	
	/*
	 * Deletes the specified WeatherData from the weather_data table
	 * Returns a success message if the operation was successful
	 */
	public String deleteWeatherData(WeatherData weatherData) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			weatherData = em.merge(weatherData);
			em.remove(weatherData);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "success";
	}
	
	/*
	 *  Updates the specified WeatherData in the weather_data table
	 *  Returns a success message if the operation was successful
	 */
	public String updateWeatherData(WeatherData weatherData) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			weatherData = em.merge(weatherData);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "success";
	}
}
