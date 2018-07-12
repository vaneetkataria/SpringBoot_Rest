package com.kataria.springboot.rest.practice.manager.user.beans;

import java.util.Date;

import com.kataria.springboot.rest.practice.core.beans.RestResponse;

public class User {

	private int id;
	private String firstName;
	private Date dateOfBirth;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String firstName, Date dateOfBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, firstName=%s, dateOfBirth=%s, getResponseStatus()=%s]", id, firstName,
				dateOfBirth);
	}

}
