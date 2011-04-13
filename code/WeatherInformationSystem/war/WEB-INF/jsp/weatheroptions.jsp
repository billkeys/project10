<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Weather Station Options</title>

<style type="text/css">
	a:link {color:#07DFF9; text-decoration:none}    /* unvisited link */
	a:visited {color:#07DFF9;} /* visited link */
	a:hover {color:white; text-decoration:underline}   /* mouse over link */
	a:active {color:white; text-decoration:underline}  /* selected link */
</style>

<style type="text/javascript">
	.myclass { color:#FFF; text-decoration: none }
	.myclass:hover { color:#FFF; text-decoration: underline }
</style>

</head>


<body bgcolor="#F8F8F8">
<center>
<img src="img/WeatherOptions.jpg" width="1191" height="724" border="0" usemap="#Map" />

<map name="Map" id="Map">
  <!--Icon top left of page- Africa with WWS.  Takes user back to home page."-->
  <area shape="rect" coords="13,12,114,113" href="/home" title="Go to Home Page"/>
  <!--Text Link - All Weather Stations-->  
  <area shape="rect" coords="172,480,409,506" class="myclass" href="/main?page=weather&option=all"  alt="hi" title="View Data for All Weather Stations" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'" style="text-decoration:underline"/>
  <!--Text Link - View Data for Ghana-->
  <area shape="rect" coords="176,526,252,549" href="/main?page=weather&option=ghana" title="View Data"/>
  <!--Text Link - View Data for South Africa-->
  <area shape="rect" coords="178,556,318,577" href="/main?page=weather&option=southafrica" title="View Data"/>
  <!--Text Link - View Data for Kenya-->
  <area shape="rect" coords="176,585,249,607" href="/main?page=weather&option=kenya" title="View Data"/>
  
  <!--Below are links/circles on the map where these stations are located.-->  
  <area shape="circle" coords="369,350,8" href="/main?page=weather&option=ghana" title="View Data for Ghana" />
  <area shape="circle" coords="597,672,6" href="/main?page=weather&option=southafrica" title="View Data for South Africa"/>
  <area shape="circle" coords="706,392,7" href="/main?page=weather&option=kenya" title="View Data for Kenya"/>
</map>

</center>
</body>
</html>