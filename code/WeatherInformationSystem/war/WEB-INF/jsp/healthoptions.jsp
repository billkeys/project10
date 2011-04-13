<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="styles/Map.css" rel="stylesheet" type="text/css" />
<title>Health Options Page</title>

<style type="text/css">
#dek {POSITION:absolute;VISIBILITY:hidden;Z-INDEX:200;}
</style>

</head>

<body bgcolor="#F8F8F8">

<center>
<img src="img/HealthOptions.jpg" width="1191" height="724" border="0" usemap="#Map" />
<map name="Map" id="Map">
  <!--Icon top left of page- Africa with WWS.  Takes user back to home page."-->
  <area shape="rect" coords="13,12,114,111" href="/home" title="Go to Home Page"/>
  <!--Text Link - All Weather Stations-->
  <area shape="rect" coords="170,479,410,505" href="/main?page=health&option=all" title="View Data for All Weather Stations"/>
  <!--Text Link - View Data for Ghana-->
  <area shape="rect" coords="177,526,253,550" href="/main?page=health&option=ghana" title="View Data"/>
  <!--Text Link - View Data for South Africa-->
  <area shape="rect" coords="176,555,317,579" href="/main?page=health&option=southafrica" title="View Data"/>
  <!--Text Link - View Data for Kenya-->
  <area shape="rect" coords="177,585,248,607" href="/main?page=health&option=kenya" title="View Data"/>
  <!--Below are links/circles on the map where these stations are located.-->
  <area shape="circle" coords="369,348,7" href="/main?page=health&option=ghana" title="View Data for Ghana"/>
  <area shape="circle" coords="598,672,7" href="/main?page=health&option=southafrica" title="View Data for South Africa"/>
  <area shape="circle" coords="706,392,7" href="/main?page=health&option=kenya" title="View Data for Kenya"/>
</map>
</center>
</body>
</html>