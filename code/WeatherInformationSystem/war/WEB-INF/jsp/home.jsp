<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Wilderness Weather Station App</title>
<style type="text/css">

a:link {color:#000033; text-decoration:none}    /* unvisited link */
a:visited {color:#000033;} /* visited link */
a:hover {color:#000033; text-decoration:underline}   /* mouse over link */
a:active {color:#000033; text-decoration:underline}  /* selected link */

<!--Below code is for Displaying additional info. when the mouse hovers over 'big'. 'span' is the info that is then displayed'.-->
.about big {}/*this can be empty*/
.about a {font:normal 14px arial;text-decoration:none;color:#000000;}
.about a span {display:none;}
.about a:hover span {display:inherit;}

<!--Below code is for CSS Image Swapping-->
#WeatherDataIcon
{
	display: block;
	width: 96px;
	height: 96px;
	background: url("img/WIcon.jpg");
}
#WeatherDataIcon:hover
{
	background-position: bottom;
}
</style>

</head>

<body bgcolor="#F8F8F8">
<div id="container">
  <div id="banner"></div>
<!--Main Image-->  
  <div id="mainImage" align="center"><img src="img/1.jpg" width="100%" height="100%" border="0" usemap="#Map" />
    <map name="Map" id="Map">
      <area shape="rect" coords="1372,26,1476,126" href="Info.html" title="For more info. about this appl."/>
    </map>
  </div>
<!--Table-->
  <center>  
  <div class="about"> 
    <table width="90%" align="center">
    <tr>
    <!--Icon Links-->
     	<td height="96" width="300" align="left">
        	<a href="/main?page=weatheroptions"><big><img src="img/darkerclimate100px.jpg" width="97" height="96" /></big><span id="span">Viewed by Researchers</span></a>
        </td>
     	<td height="96" width="300" align="left">
  			<a href="/main?page=remotemanagement"><big><img src="img/darkerenv100px.jpg" width="97" height="96" /></big><span id="span2">Viewed by Maintenance</span></a>
  		</td>      
		<td height="96" width="300" align="left">
             <a href="/main?page=healthoptions"><big><img src="img/forecasts100px.jpg" width="97" height="96" /></big><span id="span3">Viewed by Maintenance</span></a>        
        </td>
     </tr>
     <tr>
     <!--Text Links-->
        <td align="left">
            <a href="/main?page=weatheroptions" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'"><font face="garamond" size="5" id="WD"><strong>Weather Data</strong></font> </a>
        </td>
        <td align="left">
            <a href="/main?page=remotemanagement" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'"><font face="garamond" size="5"><strong>Remote Management</strong></font></a>        
        </td>
        <td align="left">
            <a href="/main?page=healthoptions" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'"><font face="garamond" size="5"><strong>Health Management</strong></font></a>  
       </td>
     </tr>
  </table>
  </div>
  </center> 
<!--Bottom grass-->  
<center>
<img src="img/2.jpg" width="100%" height="100%"/>
</center>
</div>
</body>
</html>