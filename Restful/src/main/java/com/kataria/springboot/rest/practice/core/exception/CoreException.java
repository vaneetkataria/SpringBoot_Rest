package com.kataria.springboot.rest.practice.core.exception;

import org.springframework.http.HttpStatus;

public class CoreException extends Exception {

	private static final long serialVersionUID = 1L;
	private String responseCode;
	private HttpStatus httpStatus;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public CoreException() {
		super();
	}

	public CoreException(String message, Throwable cause, String responseCode, HttpStatus httpStatus) {
		super(message, cause);
		this.responseCode = responseCode;
		this.httpStatus = httpStatus;
	}

	public CoreException(String message, Throwable cause, String responseCode) {
		super(message, cause);
		this.responseCode = responseCode;
	}

}
