package persistence.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import persistence.HealthData;

public class HealthDataManager {
	
	public HealthDataManager() {
		
	}
	
	/*
	 * Creates an EntityManagerFactory and gets an EntityManager from it
	 */
	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("DataArchivingSystem3", System.getProperties());
		return emf.createEntityManager();
	}
	
	/*
	 * Inserts a HealthData object into the health_data table in the database
	 * Returns a success message if the row was successfully created
	 */
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
		return "success";
	}
	
	/*
	 * Retrieves all rows in from the health_data table with the specified stationId
	 * Returns the data in a list of HealthData objects
	 */
	public List<HealthData> getHealthDataByStationId(String stationId) {
		EntityManager em = getEntityManager();
		List<HealthData> results = null;
		try {
			Query query = em.createNamedQuery("getHealthDataByPk_stationId");
			query.setParameter("pk_stationId", stationId);
			results = (List<HealthData>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}
	
	/*
	 * Retrieves every row in the health_data table and returns the data
	 * in a list of HealthData objects
	 */
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
	
	/*
	 * Deletes the specified HealthData from the health_data table
	 * Returns a success message if the operation was successful
	 */
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
		return "success";
	}
	
	/*
	 *  Updates the specified HealthData in the health_data table
	 *  Returns a success message if the operation was successful
	 */
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
		return "success";
	}

}
