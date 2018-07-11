package com.kataria.springboot.rest.practice.core.exception;

public class CoreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String responseCode;
	private String httpCode;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

	public CoreException() {
		super();
	}

	public CoreException(String message, Throwable cause, String responseCode, String httpCode) {
		super(message, cause);
		this.responseCode = responseCode;
		this.httpCode = httpCode;
	}

}
