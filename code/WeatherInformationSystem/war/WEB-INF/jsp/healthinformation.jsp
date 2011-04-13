<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="org.weathersystem.weatherinformationsystem.persistence.controller.HealthDataManager"
	import="org.weathersystem.weatherinformationsystem.persistence.HealthData"
	import="org.weathersystem.weatherinformationsystem.persistence.HealthDataPK"
	import="java.sql.Timestamp" 
	import="java.util.List"
	import="javax.persistence.EntityManagerFactory"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet"
	href="WeatherInformationSystem.css">
<title>Health Information</title>
<script type="text/javascript" language="javascript" src="/js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="/js/dataTables.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var hTable = $('#health-table').dataTable({
			"bPaginate": false,
			"bLengthChange": false,
			"bFilter": true,
			"bSort": true,
			"bInfo": false,
			"bAutoWidth": false } );
		
		$("tfoot input").keyup( function () {
			/* Filter on the column (the index) of this element */
			hTable.fnFilter( this.value, $("tfoot input").index(this) );
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
	});
	
 function setValue(target) {
        target.value = target.value;
        return target.value;
      }
</script>
</head>
<body>
<center>
<h1>Health Information System</h1>
</center>
<center>
<table rules=rows frame=box cellpadding=3 cellspacing=1>
	<tr>
		<td>

		<table id="health-table">
			<thead>
				<tr style="color: white; background-color: blue;">
					<th style="width: 85px;">Station</th>
					<th style="width: 220px;">Time</th>
					<th style="width: 180px;">Station Status</th>
					<th style="width: 100px;">Anemometer</th>
					<th style="width: 85px;">Barometer</th>
					<th style="width: 95px;">Temperature</th>
					<th style="width: 95px;">Precipitation</th>
					<th style="width: 70px;">Sunshine</th>
					<th style="width: 70px;">Visibility</th>
					<th style="width: 70px;">Humidity</th>
					<th style="width: 120px;">Wind Direction</th>
					<th style="width: 75px;">Clock</th>
					<th style="width: 70px;">Battery</th>
					<th style="width: 125px;">Battery Charging</th>
					<th style="width: 100px;">Battery Level</th>
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
				}
				
				private String convertToUpDown(Boolean value){
					if (value){
						return "Online";
					}
					else {
						return "Damaged";
					}
				}
			%>
			<%
				try {
					List<HealthData> healthList = null;
					HealthDataManager manager = new HealthDataManager();
					if (request.getParameter("option").equals("all")) {
						healthList = manager.getHealthData();
					} else {
						healthList = manager.getHealthDataByStationId(request
								.getParameter("option"));
					}

					if (healthList.isEmpty()) {
			%>
			<tr>
				<td align="center" colspan="12">There currently isn't any
				health data in the archiving system.</td>
			</tr>
			<%
				} else {
						int i = 0;
			%>
			<tbody>
				<%
					while (i < healthList.size()) {
								HealthData health = healthList.get(i);
				%>

				<tr>
					<td align="center"><%=parseStationId(health.getPk().getStationId())%></td>
					<td align="center"><%=health.getPk().getTime()%></td>
					<td align="center"><%=health.getStationStatus()%></td>
					<td align="center"><%=convertToUpDown(health.getAnemometerSensor())%></td>
					<td align="center"><%=convertToUpDown(health.getBarometerSensor())%></td>
					<td align="center"><%=convertToUpDown(health.getTemperatureSensor())%></td>
					<td align="center"><%=convertToUpDown(health.getPrecipitionSensor())%></td>
					<td align="center"><%=convertToUpDown(health.getSunshineSensor())%></td>
					<td align="center"><%=convertToUpDown(health.getVisibilitySensor())%></td>
					<td align="center"><%=convertToUpDown(health.getHumiditySensor())%></td>
					<td align="center"><%=convertToUpDown(health.getWindDirectionSensor())%></td>
					<td align="center"><%=convertToUpDown(health.getClockSensor())%></td>
					<td align="center"><%=convertToUpDown(health.getBatterySensor())%></td>
					<td align="center"><%=health.getBatteryCharging()%></td>
					<td align="center"><%=health.getBatteryLevel()%></td>
					</font>
				</tr>
				</td>
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
 					 <th><input type="text" name="search_station" value="Search Station" class="search_init" onchange="setValue(this.value='Search Station')" onclick="this.value=''"/></th>
 					 <th><input type="text" name="search_time" value="Search time" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_status" value="Search station status" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_anemometer" value="Search anemometer" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_barometer" value="Search barometer" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_temperature" value="Search temperature" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_precipition" value="Search precipition" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_sunshine" value="Search sunshine" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_visibility" value="Search visibility" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_humidity" value="Search humidity" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_winddirection" value="Search wind direction" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_clock" value="Search clock" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_battery" value="Search battery" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_batterycharging" value="Search battery charging" class="search_init" onclick="this.value=''"/></th>
					 <th><input type="text" name="search_batterylevel" value="Search battery level" class="search_init" onclick="this.value=''"/></th>
 				</tr>
 			</tfoot> 
		</table>
		</td>
	</tr>
</table>
</body>
</html>