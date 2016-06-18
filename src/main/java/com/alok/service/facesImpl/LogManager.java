package com.alok.service.facesImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.alok.beans.InitialConfig;
import com.alok.beans.LogManagerBean;
import com.alok.service.faces.LogManagerFace;
import com.alok.utilities.MyConstants;
import com.alok.utilities.MyUtility;

/**
 * Acts as a Controller for sync and async logging
 * @author Alok
 *
 */
@Service
@Scope("prototype")
@Configuration

public class LogManager implements LogManagerFace {

	@Autowired
	InitialConfig initialConfig;
	String className = null;
	private static int objCount = 0;
	private static ApplicationContext context = null;
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public LogManager() {
		super();
	}

	@Autowired
	LogManagerBean logManagerBean;

	public LogManager(Object ob) {
		System.out.println(ob.getClass().getName());
		System.out.println(logManagerBean);
		logManagerBean.setClassName(ob.getClass().getName());
	}

	@Override
	public void info(String content) {
		// TODO Auto-generated method stub
		String filename=initialConfig.getFileNameWithPath();
		if(initialConfig.getLogLevel().contains(MyConstants.LOG_LEVEL_INFO)){
				
		if (initialConfig.getLoggerType().equalsIgnoreCase("async")) {
			asyncCaller(MyUtility.getDate(),MyConstants.LOG_LEVEL_INFO,this.getClassName(),content);
			System.out.println("Done");
			
		}
		else{
			Writer writer=new Writer();
			
			if(filename.endsWith(".txt")){
			writer.textWriter(filename,MyUtility.getDate(),MyConstants.LOG_LEVEL_INFO,this.getClassName(),content);
			}
			else if(filename.endsWith(".xml")){
				
				
				writer.xmlWriter(filename,MyUtility.getDate(),MyConstants.LOG_LEVEL_INFO,this.getClassName(),content);
			}
			else{
				System.out.println("Logger file type mismatch !!"
						+ filename);
				
			}
			System.out.println("Sync Writing Done");
			}
		}else{
			System.out.println("Info log level is not defined in current configuration");
		}
	}

	@Override
	public void debug(String content, Exception... ex) {
		// TODO Auto-generated method stub
		if(initialConfig.getLogLevel().contains(MyConstants.LOG_LEVEL_DEBUG)){
		String output="";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		for (Exception exception : ex) {
			exception.printStackTrace(ps);
			try {
				 output = os.toString("UTF8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		content=content+output;
		String filename=initialConfig.getFileNameWithPath();
		if (initialConfig.getLoggerType().equalsIgnoreCase("async")) {
			asyncCaller(MyUtility.getDate(),MyConstants.LOG_LEVEL_DEBUG,this.getClassName(),content);
			System.out.println("Async Done");
			
		}
		else{
			Writer writer=new Writer();
			
			if(filename.endsWith(".txt")){
			writer.textWriter(filename,MyUtility.getDate(),MyConstants.LOG_LEVEL_DEBUG,this.getClassName(),content);
			}
			else if(filename.endsWith(".xml")){
				
				
				writer.xmlWriter(filename,MyUtility.getDate(),MyConstants.LOG_LEVEL_DEBUG,this.getClassName(),content);
			}
			else{
				System.out.println("Logger file type mismatch !!"
						+ filename);
				
			}
			System.out.println("Sync Writing Done");
			}
		}else{
			System.out.println("Debug level is not defined in current configuration");
		}
		
	}

	@Override
	public void error(String content, Exception... ex) {
		if(initialConfig.getLogLevel().contains(MyConstants.LOG_LEVEL_ERROR)){
		String output="";
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		for (Exception exception : ex) {
			exception.printStackTrace(ps);
			try {
				 output = os.toString("UTF8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
		content=content+output;
		String filename=initialConfig.getFileNameWithPath();
		if (initialConfig.getLoggerType().equalsIgnoreCase("async")) {
			asyncCaller(MyUtility.getDate(),MyConstants.LOG_LEVEL_ERROR,this.getClassName(),content);
			System.out.println("Async Done");
			
		}
		else{
			Writer writer=new Writer();
			
			if(filename.endsWith(".txt")){
			writer.textWriter(filename,MyUtility.getDate(),MyConstants.LOG_LEVEL_ERROR,this.getClassName(),content);
			}
			else if(filename.endsWith(".xml")){
				
				
				writer.xmlWriter(filename,MyUtility.getDate(),MyConstants.LOG_LEVEL_ERROR,this.getClassName(),content);
			}
			else{
				throw new RuntimeException("Logger file type mismatch !!"
						+ filename);
			}
			System.out.println("sync Done");
			}
		}else{
			System.out.println("Error log level is not defined in current configuration");
		}
		
		
	}

	public static LogManager getLogger(Class<?> ob) {
		//System.out.println("  INNNNNN geeeeeeeet " + ob);
		ApplicationContext context = getContext();
		LogManager logManager = (LogManager) context.getBean("logManager");
		logManager.setClassName(ob.getName());
		return logManager;
	}

	private static ApplicationContext getContext() {
		String confFile = "application-context.xml";
		//System.out.println(" ########## OBJ COunt ############### " + objCount);
		if (objCount == 0) {
			context = new ClassPathXmlApplicationContext(confFile);
			objCount++;
		}

		return context;
	}

	private void asyncCaller(String dateTime, String logLevel, String className,String content) {
		ExecutorService executorService = Executors
				.newSingleThreadExecutor();
		try {
			
			AsyncWrite asyncWrite = new AsyncWrite(
					initialConfig.getFileNameWithPath(), dateTime, logLevel,className, content);
			Future<Boolean> future = executorService.submit(asyncWrite);
			while (!future.isDone()) {
				System.out.println("Writing process is not completed yet....");
				Thread.sleep(1);

			}
			if (future.get()) {
				System.out.println("Writing process done successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally{
			executorService.shutdown();
		}
	}
}
