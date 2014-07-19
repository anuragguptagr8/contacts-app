package com.itechart.training.tsvilik.contactsapp.dal;

public class DataAccessException extends Exception {

	public DataAccessException() {
		super();
	}

	public DataAccessException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DataAccessException(Exception e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1026848613205784955L;

}
