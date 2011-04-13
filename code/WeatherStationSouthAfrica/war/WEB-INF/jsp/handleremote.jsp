<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="org.weathersystem.weatherstation.southafrica.server.jpo.PMF;"
        import="javax.jdo.PersistenceManager;"
        import="org.weathersystem.weatherstation.southafrica.common.Health;"
		import="org.weathersystem.weatherstation.southafrica.common.HealthResource;"
		import="org.weathersystem.weatherstation.southafrica.server.jpo.HealthRecord;"
		import="org.weathersystem.weatherstation.southafrica.server.jpo.PMF;"
		import="java.util.List;"
        import="org.weathersystem.weatherstation.southafrica.server.jpo.StateRecord;"
%>
 
<%
       if (request.getMethod().equals("POST") && request.getParameter("remote") != null){
            String returnUrl = request.getParameter("returnUrl");
            String change = request.getParameter("remote");
        	String station = "South Africa";
        	
            PersistenceManager pm = PMF.get().getPersistenceManager();
    		String query = "select from " + HealthRecord.class.getName() + " where id == 1";
    		List<HealthRecord> states = (List<HealthRecord>) pm.newQuery(query).execute();
    	
            if (change.equals("start")){
            	boolean error = false;
                
            	for(HealthRecord item : states ) {
            	
            		if (Integer.parseInt(item.getBatterylevel().trim())>20 ){
        				item.setStatus("Powered On");
            		}
            		else {
            			error= true;
            		}
        		}
            	if(error){
            	  	response.sendRedirect(returnUrl+"?responce=unsuccess&type=Start&station="+station);		
            	}
            	else {
            	  	response.sendRedirect(returnUrl+"?responce=success&type=Start&station="+station);
            	}
            	pm.close();
            }
            else if(change.equals("stop")){
            	boolean error = false;
          
            	for(HealthRecord item : states ) {
            	
            		if (Integer.parseInt(item.getBatterylevel().trim()) >0){
        				item.setStatus("Administratively Shutdown");
            		}
            		else {
            			error= true;
            		}
        		}
            	if(error){
            	  	response.sendRedirect(returnUrl+"?responce=unsuccess&type=Stop&station="+station);		
            	}
            	else {
            	  	response.sendRedirect(returnUrl+"?responce=success&type=Stop&station="+station);
            	}
            		
                pm.close();
            }
 			else if(change.equals("restart")){
 				response.sendRedirect(returnUrl+"?responce=success&type=Restart&station="+station);
            	
 				for(HealthRecord item : states ){
        			item.setStatus("Administratively Shutdown");
        			
        		}
 				pm.close();
 				
 				Thread.sleep(10000);
 				
 				pm = PMF.get().getPersistenceManager();
 	    		states = (List<HealthRecord>) pm.newQuery(query).execute();
 	    		
 				for(HealthRecord item : states ){
        			item.setStatus("Powered On");	
        		}
 				pm.close();	 	
            }
			else if(change.equals("recharge")){
				response.sendRedirect(returnUrl+"?responce=success&type=Recharge&station="+station);

 				for(HealthRecord item : states ) {
 					if(item.getBatterylevel()=="0"){
 						item.setBatterylevel("1");
 					}
        			item.setCharging("yes");
        			item.setChargingStartTime(System.currentTimeMillis());
        		}
 				pm.close();
            }
			else if(change.equals("ping")){
				for(HealthRecord item : states ) {
					if (item.getStatus().equals("Administratively Shutdown") ||item.getStatus().equals("Powered Off (Battery Depleted)") ){
						response.sendRedirect(returnUrl+"?responce=ussuccess&type=Ping&station="+station);
					}
					else{
						response.sendRedirect(returnUrl+"?responce=success&type=Ping&station="+station);
					}	
				}	
            }	
       }
%>


