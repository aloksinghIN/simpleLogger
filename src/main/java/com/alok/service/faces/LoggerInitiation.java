package com.alok.service.faces;

import org.springframework.beans.factory.InitializingBean;

import com.alok.beans.InitialConfig;

/**
 * Interface to load initiate the logger
 * @author Alok
 * 
 *
 */
public interface LoggerInitiation extends InitializingBean{
	
	Boolean CreatFile(InitialConfig initialConfig);
	Boolean CreatFilePath(InitialConfig initialConfig);
	Boolean CreateLoggerByLogggerType(InitialConfig initialConfig);



}
