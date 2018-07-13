package com.kataria.springboot.rest.practice.core.exception;

import org.springframework.http.HttpStatus;

public class CoreException extends Exception {

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public CoreException() {
		super();
	}

	public CoreException(String message, Throwable cause, HttpStatus httpStatus) {
		super(message, cause);
		this.httpStatus = httpStatus;
	}

	public CoreException(String message, Throwable cause) {
		super(message, cause);
	}

}
