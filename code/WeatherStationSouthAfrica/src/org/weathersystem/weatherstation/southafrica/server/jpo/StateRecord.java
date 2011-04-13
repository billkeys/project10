package org.weathersystem.weatherstation.southafrica.server.jpo;
import java.io.IOException;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class StateRecord {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.unencoded-pk", value="true")	
	private Key key;
	
	
	@Persistent
	private int id;
	
	@Persistent
	private int state;
	
	public StateRecord( int id, int state){
		setID(id);
		setState(state);
	
		
	}
	
	public static StateRecord get(PersistenceManager pm, String resourceId) {
		try {
			return pm.getObjectById(StateRecord.class, resourceId);
		} catch (JDOObjectNotFoundException e){
	
		}
		
		return null;
	}
	
	public static void add(PersistenceManager pm, int id, int state) throws IOException{
		StateRecord ci = new StateRecord(id, state);
		
		pm.makePersistent(ci);
	}
	
	public static void delete(PersistenceManager pm, String resourceId){
		delete(pm, resourceId);
	}

	public void setState(int state) {
		this.state = state;
	}


	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public int getState() {
		return state;
	}
}