package com.alok.service.facesImpl;

import java.util.concurrent.Callable;

import com.alok.utilities.MyConstants;


/**
 * Asynchronous writer
 * @author Alok
 *
 */
public class AsyncWrite implements Callable<Boolean> {

	String content;
	String fileNameWithPath;
	String dateTime;
	String logLevel;
	String className;

	public AsyncWrite() {
		super();
	}

	public AsyncWrite(String fileNameWithPath, String dateTime,
			String logLevel, String className, String content) {
		this.content = content;
		this.fileNameWithPath = fileNameWithPath;
		this.dateTime = dateTime;
		this.logLevel = logLevel;
		this.className = className;
	}

	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		Boolean status = false;
		if (this.fileNameWithPath.endsWith(MyConstants.FILE_TYPE_TEXT)) {
			status = new Writer().textWriter(this.fileNameWithPath, this.dateTime,
					this.logLevel, this.className, this.content);
		} else if (this.fileNameWithPath.endsWith(MyConstants.FILE_TYPE_XML)) {
			status = new Writer().xmlWriter(this.fileNameWithPath, this.dateTime,
					this.logLevel, this.className, this.content);
		} else {
			System.out.println("Logger file type mismatch !!"
					+ this.fileNameWithPath);
		}
		return status;
	}

	
}
