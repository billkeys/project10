/**
 * 
 */
package persistence.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.ws.Action;

import persistence.HealthData;

/**
 * @author deyaa.abuelsaad1
 *
 */
@SuppressWarnings("unchecked")
public class HealthDataManager {

	public HealthDataManager() {
	
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("DataArchivingSystem");
		return emf.createEntityManager();
	}

	public String createHealthData(HealthData healthData) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(healthData);
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

	public String deleteHealthData(HealthData healthData) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			healthData = em.merge(healthData);
			em.remove(healthData);
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

	public String updateHealthData(HealthData healthData) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			healthData = em.merge(healthData);
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

	public HealthData findHealthDataByStationId(int stationId) {
		HealthData healthData = null;
		EntityManager em = getEntityManager();
		try {
			healthData = (HealthData) em.find(HealthData.class, stationId);
		} finally {
			em.close();
		}
		return healthData;
	}

	public HealthData getNewHealthData() {
		HealthData healthData = new HealthData();
		return healthData;
	}

	public List<HealthData> getHealthData() {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthData");
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataOrdered() {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthDataOrdered");
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByStationStatus(String stationStatus) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthDataByStationStatus");
			query.setParameter("stationStatus", stationStatus);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByBarometerSensor(byte barometerSensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthDataByBarometerSensor");
			query.setParameter("barometerSensor", barometerSensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByAnemometerSensor(
			byte anemometerSensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em
					.createNamedQuery("getHealthDataByAnemometerSensor");
			query.setParameter("anemometerSensor", anemometerSensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByGroundTempSensor(
			byte groundTempSensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em
					.createNamedQuery("getHealthDataByGroundTempSensor");
			query.setParameter("groundTempSensor", groundTempSensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByAirTempSensor(byte airTempSensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthDataByAirTempSensor");
			query.setParameter("airTempSensor", airTempSensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByPrecipitionSensor(
			byte precipitionSensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em
					.createNamedQuery("getHealthDataByPrecipitionSensor");
			query.setParameter("precipitionSensor", precipitionSensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataBySunshineSensor(byte sunshineSensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthDataBySunshineSensor");
			query.setParameter("sunshineSensor", sunshineSensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByVisibilitySensor(
			byte visibilitySensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em
					.createNamedQuery("getHealthDataByVisibilitySensor");
			query.setParameter("visibilitySensor", visibilitySensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByClockSensor(byte clockSensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthDataByClockSensor");
			query.setParameter("clockSensor", clockSensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByDewpointSensor(byte dewpointSensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthDataByDewpointSensor");
			query.setParameter("dewpointSensor", dewpointSensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	public List<HealthData> getHealthDataByBatterySensor(byte batterySensor) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthDataByBatterySensor");
			query.setParameter("batterySensor", batterySensor);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	} 
 }