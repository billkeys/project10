/**
 * 
 */
package persistence.controller;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.ws.Action;

import persistence.WeatherData;

/**
 * @author deyaa.abuelsaad1
 *
 */
@SuppressWarnings("unchecked")
public class WeatherDataManager {

	public WeatherDataManager() {
	
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("DataArchivingSystem");
		return emf.createEntityManager();
	}

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
		return "";
	}

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
		return "";
	}

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
		return "";
	}

	public WeatherData findWeatherDataByStationId(int stationId) {
		WeatherData weatherData = null;
		EntityManager em = getEntityManager();
		try {
			weatherData = (WeatherData) em.find(WeatherData.class, stationId);
		} finally {
			em.close();
		}
		return weatherData;
	}

	public WeatherData getNewWeatherData() {
		WeatherData weatherData = new WeatherData();
		return weatherData;
	}

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

	public List<WeatherData> getWeatherDataOrdered() {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataOrdered");
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataByAirPressure(String airPressure) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataByAirPressure");
			query.setParameter("airPressure", airPressure);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataByWindSpeed(String windSpeed) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataByWindSpeed");
			query.setParameter("windSpeed", windSpeed);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataByAirTemperature(
			String airTemperature) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataByAirTemperature");
			query.setParameter("airTemperature", airTemperature);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataByGroundTemperature(
			String groundTemperature) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em
					.createNamedQuery("getWeatherDataByGroundTemperature");
			query.setParameter("groundTemperature", groundTemperature);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataByPrecipition(String precipition) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataByPrecipition");
			query.setParameter("precipition", precipition);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataByHumidity(String humidity) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataByHumidity");
			query.setParameter("humidity", humidity);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataBySolarRadiation(
			String solarRadiation) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataBySolarRadiation");
			query.setParameter("solarRadiation", solarRadiation);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataByDewPoint(String dewPoint) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataByDewPoint");
			query.setParameter("dewPoint", dewPoint);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataByWindDirection(String windDirection) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataByWindDirection");
			query.setParameter("windDirection", windDirection);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<WeatherData> getWeatherDataByTime(Date time) {
		EntityManager em = getEntityManager();
		List<WeatherData> results = null;
		try {
			Query query = em.createNamedQuery("getWeatherDataByTime");
			query.setParameter("time", time);
			results = (List<WeatherData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	} 
 }