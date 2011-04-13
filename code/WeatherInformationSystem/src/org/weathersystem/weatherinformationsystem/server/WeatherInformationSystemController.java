package org.weathersystem.weatherinformationsystem.server;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WeatherInformationSystemController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		if ( request.getParameter("option") !=null && request.getParameter("page").equals("weather")){
			 response.sendRedirect("/weatherinformation?option="+request.getParameter("option"));
		}
		else if( request.getParameter("page").equals("weatheroptions")){
			 response.sendRedirect("/weatheroptions");
		}
		else if ( request.getParameter("option") !=null && request.getParameter("page").equals("health")){
			 response.sendRedirect("/healthinformation?option="+request.getParameter("option"));
		}
		else if( request.getParameter("page").equals("healthinformation")){
			 response.sendRedirect("/healthinformation");
		}
		else if( request.getParameter("page").equals("healthoptions")){
			 response.sendRedirect("/healthoptions");
		}
		else if( request.getParameter("page").equals("remotemanagement")){
			 response.sendRedirect("/remotemanagement");
		}
		else{
			response.sendRedirect("/home");
		}
	}
}
