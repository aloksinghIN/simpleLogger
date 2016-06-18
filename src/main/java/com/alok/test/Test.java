/**
 * 
 */
package com.alok.test;

import com.alok.service.facesImpl.LogManager;

/**
 * @author Alok
 *
 */
public class Test {
	
	public Test(){
	super();	
	}
	
	 void display(){
			LogManager logManager = LogManager.getLogger(Test.class);
			logManager.info(" Info Called form Test  ");
			   
			
		}

}
