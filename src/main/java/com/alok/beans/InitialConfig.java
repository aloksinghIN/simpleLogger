package com.alok.beans;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * To load initial configuration file
 * @author Alok
 * 
 *
 */
@Component("loggerConfig")
public final class InitialConfig {

	@Value("${log.loggertype}")
	private String loggerType;
	
	@Value("${log.filename}")
	private String fileName;
	
	@Value("${log.filetype}")
	private String fileType;
	
	@Value("#{'${log.logLevel}'.split(',')}") 
	private List<String> logLevel;
	
	@Value("${log.filepath}")
	private String filePath;
	
	private String fileNameWithPath;

	public String getFileNameWithPath() {
		return fileNameWithPath;
	}

	public void setFileNameWithPath(String fileNameWithPath) {
		this.fileNameWithPath = fileNameWithPath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getLoggerType() {
		return loggerType;
	}

	public void setLoggerType(String loggerType) {
		this.loggerType = loggerType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public List<String> getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(List<String> logLevel) {
		this.logLevel = logLevel;
	}

	@Override
	public String toString() {
		return "InitialConfig [loggerType=" + loggerType + ", fileName="
				+ fileName + ", fileType=" + fileType + ", logLevel="
				+ logLevel + ", filePath=" + filePath + ", fileNameWithPath="
				+ fileNameWithPath + "]";
	}  

}
