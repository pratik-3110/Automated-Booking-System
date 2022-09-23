/**
 * 
 */
package com.hsbc.system.exceptions;


import java.io.FileReader;
import java.util.logging.*;



/**
 * @author The Xceptionals
 *
 */
public class LoggerFile {
	
	private String message;
	
	public LoggerFile(String message) {	
		this.message = message;
		System.out.println("called");
		 
	}
	
	public static  void LogHandle(String message) { 
		
		try {

			FileHandler f = new FileHandler("JsonLogFile.log",true);
			
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			Logger logger = Logger.getLogger("JsonLog"); 
			logger.addHandler(f);
			
			logger.info(message);
		
			f.close();
	    } catch(Exception e) {
	    	System.out.println("Error in Log file");
	    	} 

	}
}
