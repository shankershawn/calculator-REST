/**
 * 
 */
package com.shankarsan.exception;

/**
 * @author SHANKARSAN
 *
 */
public class ServiceException extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @param t
	 */
	public ServiceException(Throwable t) {
		super();
	}
	public ServiceException(String message) {
		super();
		this.message = message;
	}
}
