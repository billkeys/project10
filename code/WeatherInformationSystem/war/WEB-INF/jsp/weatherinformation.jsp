<%@page import="javax.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="org.weathersystem.weatherinformationsystem.persistence.controller.WeatherDataManager"
	import="org.weathersystem.weatherinformationsystem.persistence.WeatherData"
	import="java.sql.Timestamp" import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
	href="WeatherInformationSystem.css">
<title>Weather Information</title>
<script type="text/javascript" language="javascript" src="/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="/js/dataTables.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var wTable = $('#weather-table').dataTable({
			"bPaginate": false,
			"bLengthChange": false,
			"bFilter": true,
			"bSort": true,
			"bInfo": false,
			"bAutoWidth": true
			 } );
	
		$("tfoot input").keyup( function () {
			/* Filter on the column (the index) of this element */
			wTable.fnFilter( this.value, $("tfoot input").index(this) );
		} );
		
		/*
		 * Support functions to provide a little bit of 'user friendlyness' to the textboxes in 
		 * the footer
		 */
		$("tfoot input").each( function (i) {
			asInitVals[i] = this.value;
		} );
		
		$("tfoot input").focus( function () {
			if ( this.className == "search_init" )
			{
				this.className = "";
				this.value = "";
			}
		} );
		
		$("tfoot input").blur( function (i) {
			if ( this.value == "" )
			{
				this.className = "search_init";
				this.value = asInitVals[$("tfoot input").index(this)];
			}
		} );
} );
	
	 function setValue(handle, defaultValue) {
	        if(handle.value.length == 0)
	        {
	        	handle.value = defaultValue;
	        }
	      }
</script>

</head>
<body>
<center>
<h1>Weather Information System</h1>
</center>
<center>
<table rules=rows frame=box cellpadding=3 cellspacing=1>
	<tr>
		<td>

		<table id="weather-table">
			<thead>
			<tr
				style="color: white;  background-color: blue;">
				<th>Station</th>
				<th>Time</th>
				<th>Air Pressure (hPa)</th>
				<th>Wind Speed (mph)</th>
				<th>Wind Direction</th>
				<th>Air Temperature (F)</th>
				<th>Ground Temperature (F)</th>
				<th>Precipitation (in.)</th>
				<th>Humidity (%)</th>
				<th>Solar Radiation (kWh/m)</th>
				<th>Dew Point (F)</th>
			</tr>
			</thead>

			<%! private String parseStationId(String stationId) {
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
			} %>

			<%
				try {
					List<WeatherData> weatherList;

					WeatherDataManager manager = new WeatherDataManager();
					if (request.getParameter("option").equals("all")) {
						weatherList = manager.getWeatherData();
					} else {
						weatherList = manager.getWeatherDataByStationId(request
								.getParameter("option"));
					}

					if (weatherList.isEmpty()) {
			%>
			<tr>
				<td align="center" colspan="12">There currently isn't any
				weather data in the archiving system.</td>
			</tr>
			<%
				} else {
						int i = 0;
						%>
						<tbody>
						<%
						while (i < weatherList.size()) {
							WeatherData weather = weatherList.get(i);
							Double airPressure = weather.getAirPressure();
							Integer windSpeed = weather.getWindSpeed();
							String windDirection = weather.getWindDirection();
							Integer airTemperature = weather.getAirTemperature();
							Integer groundTemperature = weather.getGroundTemperature();
							Integer precipition = weather.getPrecipition();
							Integer humidity = weather.getHumidity();
							Integer solarRadiation = weather.getSolarRadiation();
							Integer dewPoint = weather.getDewPoint();
							String notAvailable = "N/A";
			%>
			
			<tr>
				<td align=center><%=parseStationId(weather.getPk().getStationId())%></td>
				<td align=center><%=weather.getPk().getTime()%></td>
				
				<% if(airPressure == null){ %>
					<td align=center><%=notAvailable%></td>
				<%} else { %>
					<td align=center><%=airPressure%></td>
				<%} %>
				
				<% if(windSpeed == null){ %>
					<td align=center><%=notAvailable%></td>
				<%} else { %>
					<td align=center><%=windSpeed%></td>
				<%} %>
				
				<% if(windDirection == null){ %>
					<td align=center><%=notAvailable%></td>
				<%} else { %>
					<td align=center><%=windDirection%></td>
				<%} %>
				
				<% if(airTemperature == null){ %>
					<td align=center><%=notAvailable%></td>
				<%} else { %>
					<td align=center><%=airTemperature%></td>
				<%} %>
				
				<% if(groundTemperature == null){ %>
					<td align=center><%=notAvailable%></td>
				<%} else { %>
					<td align=center><%=groundTemperature%></td>
				<%} %>
				
				<% if(precipition == null){ %>
					<td align=center><%=notAvailable%></td>
				<%} else { %>
					<td align=center><%=precipition%></td>
				<%} %>
				
				<% if(humidity == null){ %>
					<td align=center><%=notAvailable%></td>
				<%} else { %>
					<td align=center><%=humidity%></td>
				<%} %>
				
				<% if(solarRadiation == null){ %>
					<td align=center><%=notAvailable%></td>
				<%} else { %>
					<td align=center><%=solarRadiation%></td>
				<%} %>
				
				<% if(dewPoint == null){ %>
					<td align=center><%=notAvailable%></td>
				<%} else { %>
					<td align=center><%=dewPoint%></td>
				<%} %>
			</tr>
			

			<%
				i++;
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
			</tbody>
			<tfoot>
 				<tr>
 					 <th><input type="text" name="search_station" value="Search stationID" class="search_init" onblur="setValue(this, 'Search stationID')" onclick="this.value=''"/></th>
 					 <th><input type="text" name="search_time" value="Search time" class="search_init" onblur="setValue(this, 'Search time')" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_airpressure" value="Search air pressure" class="search_init" onblur="setValue(this, 'Search air pressure')" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_windspeed" value="Search wind speed" class="search_init" onblur="setValue(this, 'Search wind speed')" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_winddirection" value="Search wind direction" class="search_init" onblur="setValue(this, 'Search wind direction')" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_airtemperature" value="Search air temperature" class="search_init" onblur="setValue(this, 'Search temperature')" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_groundtemperature" value="Search ground temperature" class="search_init" onblur="setValue(this, 'Search ground temperature')" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_precipition" value="Search precipition" class="search_init" onblur="setValue(this, 'Search precipition')" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_humidity" value="Search humidity" class="search_init" onblur="setValue(this, 'Search humidity')" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_solarradiation" value="Search solar radiation" class="search_init" onblur="setValue(this, 'Search solar radiation')" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_dewpoint" value="Search dew point" class="search_init" onblur="setValue(this, 'Search dew point')" onclick="this.value=''"/></th>
 				</tr>
 			</tfoot> 
		</table>
		</td>
		</tr>
</table>
</center>
</body>
</html>