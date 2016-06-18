/**
 * 
 */
package com.alok.test;

import com.alok.service.facesImpl.LogManager;

/**
 * Testing the logger functionality
 * 
 * @author Alok
 * 
 */
public class LoggerDemo {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
			LogManager logManager = LogManager.getLogger(LoggerDemo.class);

		System.out.println("in MMMAAIINN " + LoggerDemo.class.getName());
		logManager.info("hi info has been called");
		logManager.debug("in Debug and exception is : ", new RuntimeException(" debugging purpose "));
		logManager.debug("in Debug and without exception  is : ");
		logManager.error("in Error and exception is : ", new RuntimeException(" Error purpose "));
		logManager.error("in Error and without exception  is : ");
		new Test().display();
		
		 long stopTime = System.currentTimeMillis();
	     long elapsedTime = stopTime - startTime;
	     System.out.println("Elasped Times is :- "+elapsedTime);

	}
}
