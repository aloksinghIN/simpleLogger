package com.alok.service.faces;

/**
 * Interface to write in various type of files
 * @author Alok
 *
 */
public interface Writable {
	
	Boolean textWriter(String fileNameWithPath,String dateTime, String logLevel, String className,String content);
	Boolean xmlWriter(String fileNameWithPath,String dateTime, String logLevel, String className,String content);
	Boolean jSONWriter(String fileNameWithPath,String dateTime, String logLevel, String className,String content);
}
