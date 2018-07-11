package com.kataria.springboot.rest.practice.web.helloworld.beans;

public class HelloWorld {

	private String message;

	public HelloWorld(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
