package com.kataria.springboot.rest.practice.manager.user.exception;

import com.kataria.springboot.rest.practice.core.exception.CoreException;

public class UserResourceException extends CoreException {

	private static final long serialVersionUID = 1L;

	public UserResourceException() {
		super();
	}

	public UserResourceException(String message, Throwable cause, String responseCode, String httpCode) {
		super(message, cause, responseCode, httpCode);
	}

}
