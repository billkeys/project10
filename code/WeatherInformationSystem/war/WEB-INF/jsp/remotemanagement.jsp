<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="org.weathersystem.weatherinformationsystem.persistence.HealthData"
	import="java.sql.Timestamp" 
	import="org.restlet.resource.ClientResource"
	import="java.lang.reflect.Type"
	import="com.google.gson.Gson"
	import="org.restlet.data.MediaType"
	import="com.google.gson.reflect.TypeToken"
	import="java.net.HttpURLConnection"
	import="java.net.URL"
	import="org.weathersystem.weatherinformationsystem.json.JSONHealthData"
	import="java.util.Vector"
	import="java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link href="../css/jquery-ui.css" rel="stylesheet" type="text/css"/>
 <script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
 <script type="text/javascript" src="../js/jquery-ui.min.js"></script>
 <script type="text/javascript" src="../js/jquery.progressbar.min.js"></script>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link type="text/css" rel="stylesheet" href="WeatherInformationSystem.css">

  <script>
  $(document).ready(function () {
	  var damageCounter = 0;
	  
	  <%
		//Add the current name of the stations to this Vector
		Vector<String> stations = new Vector<String>();
		stations.add("southafrica");
		stations.add("ghana");
		stations.add("kenya");
		
	  	for(int i = 0; i < stations.size(); i++)
		{ 
	  %>
	  		$("#pb<%=i+1%>").progressBar({ boxImage: '../img/progressbar/progressbar.gif', 
	  									   barImage: {
	  											0:  '../img/progressbar/progressbg_red.gif',
	  											20: '../img/progressbar/progressbg_yellow.gif',
	  											65: '../img/progressbar/progressbg_green.gif'
	  										},
	  										height: 13
	  		} );
	  <%
		}
	  %>
});
</script>

<script type="text/javascript" language="javascript"
	src="weatherinformationsystem/weatherinformationsystem.nocache.js"></script>
	
<title>Remote Management</title>
	
</head>

<body>
<center>
<h1>Remote Station Management</h1>
</center>
<table>
	<tr>
	<a href="/home">Home Page</a>
	</tr>
</table>

	<%
		//response.setIntHeader("Refresh", 3);
		System.out.println("reponse = " + request.getParameter("responce"));
	    if (request.getParameter("responce") != null) {
	    	System.out.println("RESPONSE isnt NULL");
	    	String status = request.getParameter("responce");
	    	String station = request.getParameter("station");
	    	String type = request.getParameter("type");
	    	System.out.println("received station " + station);
	    	if(status.equals("success")){
	    		%>
	    	    <center>
	    		 <table width="420" rules=rows frame=box cellpadding=0 cellspacing=3>
				 <tr style="background: #FFCCCC; border:1px solid #DD7777; align:center;">
					<td><b><ul><li><font color="green"><%=type%> request for <%=station%> station was successful</font></li></ul></b></td>	
				 </tr>
			     </table>
			     <BR>
			    </center>
	    	<%
	    	}
	    	else {
	    		%>
				<center>
				  <table width="420" rules=rows frame=box cellpadding=0 cellspacing=3>
					<tr style="background: #FFCCCC; border:1px solid #DD7777; align:center;">
						<td><b><ul><li><font color="red"><%=type%> request for <%=station%> station was unsuccessful!</font></li></ul></b></td>
					</tr>
				   </table>
				   <BR>
				</center>
				<%	
	    		
	    	}
	    }
		 
	%>

			<%!
				private String convertToUpDown(Boolean value){
					if (value){
						return "Sensor Online";
					}
					else {
						return "Sensor Damaged";
					}
				}
			
				private String createAlerts(String stationId, String sensor) {
					String alert = "";
					if(sensor.equals("Battery Level"))
					{
						alert = "ALERT: " + parseStationId(stationId) + " Station has been shutdown due to insufficient battery level. Please recharge station's battery!";
						return alert;
					}
					else
					{
					alert = "ALERT: " + parseStationId(stationId) + " Station: " + sensor + " is damaged and currently not collecting data!";
					return alert;
					}
				}
				
				private String createWarning(String stationId) {
					String warning = "";
					warning = "WARNING: " + parseStationId(stationId) + " Station battery power low! Please recharge station!";
					return warning;
				}
				
				private String parseStationId(String stationId) {
					if(stationId.equalsIgnoreCase("southafrica"))
					{
						return stationId = "South Africa";
					}
					else if (stationId.equalsIgnoreCase("ghana")) {
						return stationId = "Ghana";
					}
					else if (stationId.equalsIgnoreCase("kenya")) {
						return stationId = "Kenya";
					}
					else
						return "Unknown Station ID";
				}
			%>
			<!--  SOUTH AFRICA WEATHER STATION -->
			<%
				//Iterate over the stations Vector and substitue the name of the station as required in the code below
				for(int i = 0; i < stations.size(); i++)
				{
			%>
			<center>
			<div style="float: left;  margin-left: 160px;">
			<%
					String stationName = stations.get(i);
					//Get the JSON object from the specified URL
					ClientResource healthResource = new ClientResource("http://weatherstation"+stationName+".appspot.com/currenthealthdata");
					Gson gson = new Gson();
					Type collectionType = new TypeToken<JSONHealthData>() {}.getType();
					String jsonStr = healthResource.get(MediaType.APPLICATION_JSON).getText();
					System.out.println("Health Data: " + jsonStr);
	
					//Parse the JSON object into JSONHEALTHDATA
					JSONHealthData jwd = gson.fromJson(jsonStr, collectionType);
					HealthData health = jwd.convertToHealthData();
					String stationId = health.getPk().getStationId();
					Date timeCollected = health.getPk().getTime();
					String stationStatus = health.getStationStatus();
					String parsedStationName = parseStationId(stationId);
					
					String anemometer = "";
					String barometer = "";
					String temperature = "";
					String precipitation = "";
					String sunshine = "";
					String visibility = "";
					String humidity = "";
					String windDirection = "";
					String clock = "";
					String battery = "";
					Integer batteryLevel = health.getBatteryLevel();
					String batteryCharing = "";
					if(stationStatus.equals("Administratively Shutdown") || stationStatus.equals("Powered Off (Battery Depleted)"))
					{
						anemometer = "Sensor Offline";
						barometer = "Sensor Offline";
						temperature = "Sensor Offline";
						precipitation = "Sensor Offline";
						sunshine = "Sensor Offline";
						visibility = "Sensor Offline";
						humidity = "Sensor Offline";
						windDirection = "Sensor Offline";
						clock = "Sensor Offline";
						battery = "Sensor Offline";
						batteryCharing = "Sensor Offline";
					}
					else
					{
						anemometer = convertToUpDown(health.getAnemometerSensor());
						barometer = convertToUpDown(health.getBarometerSensor());
						temperature = convertToUpDown(health.getTemperatureSensor());
						precipitation = convertToUpDown(health.getPrecipitionSensor());
						sunshine = convertToUpDown(health.getSunshineSensor());
						visibility = convertToUpDown(health.getVisibilitySensor());
						humidity = convertToUpDown(health.getHumiditySensor());
						windDirection = convertToUpDown(health.getWindDirectionSensor());
						clock = convertToUpDown(health.getClockSensor());
						battery = convertToUpDown(health.getBatterySensor());
						batteryCharing = health.getBatteryCharging();
					}
					
					int damageCounter = 0;
					Vector<String> alerts = new Vector<String>();
					Vector<String> warnings = new Vector<String>();
					
					if(anemometer.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Anemometer Sensor"));
					}
					if(barometer.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Barometer Sensor"));
					}
					if(temperature.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Temperature Sensor"));
					}
					if(precipitation.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Precipitation Sensor"));
					}
					if(sunshine.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Sunshine Sensor"));
					}
					if(visibility.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Visibility Sensor"));
					}
					if(humidity.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Humidity Sensor"));
					}
					if(windDirection.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Wind Direction Sensor"));
					}
					if(clock.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Clock Sensor"));
					}
					if(battery.equals("Sensor Damaged")) {
						damageCounter++;
						alerts.add(createAlerts(stationId, "Battery Sensor"));
					}
					if(batteryLevel == 0)
					{
						alerts.add(createAlerts(stationId, "Battery Level"));
					}
					else if(batteryLevel < 20 && batteryCharing.equals("no"))
					{
						warnings.add(createWarning(stationId));
					}
			%>
			
<center>			
<table width=345px rules=rows frame=box cellpadding=3 cellspacing=1>
	<tr>
		<td>
		<table width=340 cellpadding=3 cellspacing=1>
			<tr>
				<td COLSPAN=3
					style="color: white; style: bold; text-align: center; width: 250px; background-color: blue; cellpadding: 1">
					<B><%=parsedStationName%></B></td>
			</tr>
			<tr>
			<!--<td width=15px>
				<% 
				//TODO: Add this code in the first row of the table
				if(damageCounter == 0) { %>
				<img src="../img/green.gif" />
				<% } else if (damageCounter > 0 && damageCounter < 4) { %>
				<img src="../img/yellow.gif" />
				<% } else if (damageCounter >= 4) { %>
				<img src="../img/red.png" />
				<%} %>
			</td>-->
			<td width=15px>
				<% if(stationStatus.equals("Powered Off (Battery Depleted)")) { %>
				<img src="../img/red.gif" />
				<% } else if(stationStatus.equals("Administratively Shutdown")) { %>
				<img src="../img/red.gif" />
				<% } else if(stationStatus.equals("Powered On")) { %>
				<img src="../img/green.gif" />
				<% } else if(stationStatus.equals("Powered On (Low Battery Power)")) { %>
				<img src="../img/green.gif" />
				<% } %>
			</td>
			<td>Station Status</td>
			<td>
				<%=stationStatus%>
				<!-- <%if(stationStatus.equals("Powered Off (Battery Depleted)") || stationStatus.equals("Administratively Shutdown"))
				{%>
				(<a href="#sensors">1</a>) 
				<%} %> -->
			</td>
			</tr>
			<tr>
			<td><%if(anemometer.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Anemometer</td>
			<td><%=anemometer%></td>
			</tr>
			<tr>
			<td><%if(barometer.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Barometer</td>
			<td><%=barometer%></td>
			</tr>
			<tr>
			<td><%if(temperature.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Temperature</td>
			<td><%=temperature%></td>
			</tr>
			<tr>
			<td><%if(precipitation.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Precipitation</td>
			<td><%=precipitation%></td>
			</tr>
			<tr>
			<td><%if(sunshine.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Sunshine</td>
			<td><%=sunshine%></td>
			</tr>
			<tr>
			<td><%if(visibility.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Visibility</td>
			<td><%=visibility%></td>
			</tr>
			<tr>
			<td><%if(humidity.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Humidity</td>
			<td><%=humidity%></td>
			</tr>
			<tr>
			<td><%if(windDirection.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Wind Direction</td>
			<td><%=windDirection%></td>
			</tr>
			<tr>
			<td><%if(clock.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Clock</td>
			<td><%=clock%></td>
			</tr>
			<tr>
			<td><%if(battery.equals("Sensor Online")){ %><img src="../img/green.gif" /> <%}
			else {%><img src="../img/red.gif" /> <%} %></td>
			<td>Battery</td>
			<td><%=battery%></td>
			</tr>
			<tr>
			<td COLSPAN=2 align="center">Battery Level</td>
			<%if(stationStatus.equals("Administratively Shutdown") || stationStatus.equals("Powered Off (Battery Depleted)"))
			{%>
			<td>
				N/A
			</td>
			<%}
			else
			{%>
			<td>
				<span class="progressBar" id="pb<%=i+1%>"><%=batteryLevel%>%</span>
				<!--<div id="progressbar" class="ui-progressbar ui-widget ui-widget-content ui-corner-all" showtext="true" role="progressbar" 
				aria-valuemin="0" aria-valuemax="100" aria-valuenow="67">
					<div class="ui-progressbar-value ui-widget-header ui-corner-left" style="width: ;"></div>
				</div>
			--></td>
			<%} %>
			</tr>
			<tr>
			<td COLSPAN=2 align="center">Battery Charging</td>
			<td><%=batteryCharing%></td>
			</tr>
			<tr>
			<td COLSPAN=2 align="center">Time Collected</td>
			<td><%=timeCollected%></td>
			</tr>
			<tr>
			<td COLSPAN=3>
			<table>
				<tr>
					<td>
						<form action="http://weatherstation<%=stationId%>.appspot.com/remote" method=POST>
					    	<input type="hidden" name="remote" value="start">
					    	<input type="hidden" name="returnUrl" value="http://127.0.0.1:8888/remotemanagement">
					    	<%if(stationStatus.equals("Powered On") || stationStatus.equals("Powered On (Low Battery Power)")
					    			|| stationStatus.equals("Powered Off (Battery Depleted)"))
							{%>
					    	<input type="submit" value="Start" disabled>
					    	<%}
					    	else {
					    	%>
					    	<input type="submit" value="Start">
					    	<% } %>
					   </form>
					</td>
					<td>
						<form action="http://weatherstation<%=stationId%>.appspot.com/remote" method=POST>
					    	<input type="hidden" name="remote" value="stop">
					    	<input type="hidden" name="returnUrl" value="http://127.0.0.1:8888/remotemanagement">
					    	<%if(stationStatus.equals("Powered Off (Battery Depleted)") || stationStatus.equals("Administratively Shutdown"))
							{%>
					    	<input type="submit" value="Stop" disabled>
					    	<%}
					    	else {
					    	%>
					    	<input type="submit" value="Stop">
					    	<%}%>
					   </form>
					</td>
					<td>
						<form action="http://weatherstation<%=stationId%>.appspot.com/remote" method=POST>
					    	<input type="hidden" name="remote" value="restart">
					    	<input type="hidden" name="returnUrl" value="http://127.0.0.1:8888/remotemanagement">
					    	<%if(stationStatus.equals("Powered Off (Battery Depleted)") || stationStatus.equals("Administratively Shutdown"))
							{%>
					    	<input type="submit" value="Restart" disabled>
					    	<%}
					    	else {
					    	%>
					    	<input type="submit" value="Restart">
					    	<%}%>
					   </form>
					</td>
					<td>
						<form action="http://weatherstation<%=stationId%>.appspot.com/remote" method=POST>
					    	<input type="hidden" name="remote" value="ping">
					    	<input type="hidden" name="returnUrl" value="http://127.0.0.1:8888/remotemanagement">
					    	<input type="submit" value="Ping">
					   </form>
					</td>
					<td>
						<form action="http://weatherstation<%=stationId%>.appspot.com/remote" method=POST>
					    	<input type="hidden" name="remote" value="recharge">
					    	<input type="hidden" name="returnUrl" value="http://127.0.0.1:8888/remotemanagement">
					    	<%if(batteryCharing.equals("yes"))
							{%>
							<input type="submit" value="Recharge" disabled>
							<%}
					    	else {
					    	%>
					    	<input type="submit" value="Recharge">
					    	<%}%>
					   </form>
					</td>
				</tr>
			</table>
			</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</center>
<br/>

<%		
					//Iterate over the alerts and display them on the page if any exist
					Iterator<String> alertsItr = alerts.iterator();
					if(alertsItr.hasNext())
					{
			%>
					<center>
					<table width=400px rules=rows cellpadding=3 cellspacing=1>
			<%
					while(alertsItr.hasNext())
					{
						String alert = alertsItr.next();
						System.out.println("Alert = " + alert);
			%>		
						<tr style="background:#EEEEE4; color: red; border:1px solid black; align:center; style: bold; width:345">
						<td>
						<img src="../img/Alert-Icon2.png" />
						<%=alert%>
						</td>
						</tr>
						<tr><td><br/></td></tr>
			<%		}
				}%>
					</table>
					</center>
			<%		
					//Iterate over the warnings and display them on the page if any exist
					Iterator<String> warningsItr = warnings.iterator();
					if(warningsItr.hasNext())
					{
			%>
					<center>
					<table width=400px rules=rows frame=box cellpadding=3 cellspacing=1>
			<%
					while(warningsItr.hasNext())
					{
						String warning = warningsItr.next();
						System.out.println("Warning = " + warning);
			%>			
						<tr style="background:#EEEEE4; color: black; border:1px solid black; align:center; style: bold; width:345 ">
						<td>
						<img src="../img/warning-icon2.png" />
						<%=warning%>
						</td>
						</tr>
						<tr></tr>
						
			<%		}
				}%>
					</table>
					</center>
</div>
</center>
		<% } %>
		
</body>
<br/>
<div>
<!-- <a name="sensors"><B>1:</B> When a station is down the sensors are not collecting data, but will remain online
and can be accessed for their health status</a>  -->
</div>
</html>