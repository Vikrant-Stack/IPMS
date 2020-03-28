package com.apogee.general;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

//import com.sits.conn.DBConnection;

public class General {
		
	
	public static String checknull(String n){
		if(n!=null) {
			return n;
		} else {
			return "";
		}
	}
	
	
	  
	  /**
	   * 
	   * 
	   * */
	  public static Logger getLogger(HttpServletRequest req) {
		  String requestUri = req.getRequestURI();
		  String jspName = requestUri.substring(requestUri.lastIndexOf('/'));
		  return Logger.getLogger(jspName);
	  }
	  
	 
	  /**
	   Checks if is null or blank.
      @param str
	   @return true/false
	 */
	public static boolean isNullOrBlank(final String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	static Logger log = Logger.getLogger("exceptionlog");
	 
}


