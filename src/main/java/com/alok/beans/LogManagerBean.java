package com.alok.beans;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 *To save logger configuration runtime
 * @author Alok
 * 
 */
@Component
@Scope("prototype")

public class LogManagerBean {

	private String className; 
	private Date date;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "LogManagerBean [className=" + className + ", date=" + date
				+ "]";
	}
	
	
	
}
