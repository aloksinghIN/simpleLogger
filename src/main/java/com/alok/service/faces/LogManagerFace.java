package com.alok.service.faces;

/**
 * Interface for LogMannager 
 * @author Alok
 *
 */
public interface LogManagerFace {

	public void info(String comment);
	public void debug(String comment, Exception...ex);
	public void error(String comment, Exception...ex);
}
