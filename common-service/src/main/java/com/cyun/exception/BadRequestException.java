package com.cyun.exception;


public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1336592007798002172L;

	public BadRequestException(String msg) {
		super(msg);
	}
	
}
