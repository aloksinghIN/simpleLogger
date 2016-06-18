package com.alok.service.facesImpl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alok.beans.InitialConfig;
import com.alok.service.faces.LoggerInitiation;
import com.alok.utilities.MyConstants;
/**
 * Use to initialize the logger components
 * @author Alok
 *
 */
@Service
public class LoggerInitiationImpl implements LoggerInitiation {
	
	@Autowired
	InitialConfig initialConfig;

	public LoggerInitiationImpl() {
		// TODO Auto-generated constructor stub
	}

	
	public void afterPropertiesSet() throws Exception {
		
		// TODO Auto-generated method stub
		System.out.println(" 888 initialConfig 888  : " +initialConfig);
		Boolean CreateLoggerByLogggerType= CreateLoggerByLogggerType(initialConfig);
		System.out.println("Logger Creation Status : " + CreateLoggerByLogggerType);
		Boolean fileNameWithPathafterInitialConfigStatus=setFileNameWithPathafterInitialConfig(initialConfig);
		System.out.println("fileNameWithPathafterInitialConfigStatus : " +fileNameWithPathafterInitialConfigStatus);
		Boolean directoryStatus = CreatFilePath(initialConfig);
		System.out.println("Directory creation : " +directoryStatus);
		Boolean creatFileStatus = CreatFile(initialConfig);
		System.out.println("Log file Is created  : " +creatFileStatus);
	}

	
	public Boolean CreateLoggerByLogggerType(InitialConfig initialConfig) {
		// TODO Auto-generated method stub
		Boolean status=true;
		if(initialConfig.getLoggerType().equals("async")){
			
		}
		else{
			
			
		}
		return status;
	}

	
	
	public Boolean CreatFile(InitialConfig initialConfig) {
		// TODO Auto-generated method stub
		Boolean status=false;
		if(initialConfig.getFileNameWithPath().endsWith(MyConstants.FILE_TYPE_TEXT) || initialConfig.getFileNameWithPath().endsWith(MyConstants.FILE_TYPE_XML)){
		//System.out.println("initialConfig.getFileNameWithPath()  :-  "+initialConfig.getFileNameWithPath());
				File file = new File(initialConfig.getFileNameWithPath());
		if (!file.exists()) {
			try {
				file.createNewFile();
				status=true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		else{
			throw new RuntimeException("Logger file type mismatch !!");
		}
		/*File file = new File(initialConfig.getFilePath(), fileNameWithType);
		try {
			FileWriter newFile = new FileWriter(file);
			status= true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}*/
		return status;
	}

	
	public Boolean CreatFilePath(InitialConfig initialConfig) {
		// TODO Auto-generated method stub
		return new File(initialConfig.getFilePath()).mkdirs();
		
	}

	private Boolean  setFileNameWithPathafterInitialConfig(InitialConfig initialConfig){
		Boolean fileNameWithPathafterInitialConfigStatus;
		String fileName=initialConfig.getFileName();
		String fileType=initialConfig.getFileType();
		String fileNameWithType=fileName+"."+fileType;
		String fileNameWithPath2 =initialConfig.getFilePath()+File.separator+fileNameWithType;
		System.out.println("fileNameWithPath2 :- " +fileNameWithPath2);
		initialConfig.setFileNameWithPath(fileNameWithPath2);
		fileNameWithPathafterInitialConfigStatus=true;
		return fileNameWithPathafterInitialConfigStatus;
		
	}
}
