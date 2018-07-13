package com.kataria.springboot.rest.practice.manager.user.beans;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kataria.springboot.rest.practice.core.beans.RestResponse;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User extends RestResponse implements Cloneable {
	@PositiveOrZero(message = "User Id must be positive.")
	private int id;
	@NotBlank(message = "First name cannot be empty.")
	private String firstName;
	@NotNull(message = "Date of birth cannot be null.")
	@Past(message = "Date of birth must be in Past.")
	private Date dateOfBirth;
	@NotNull(message = "Address cannot be null.")
	@Valid
	private Address address;
	@NotEmpty(message = "Atleat one Junior is required.")
	private List<@NotBlank(message = "Junior Name cannot be null or empty.") String> juniors;
	@NotEmpty(message = "Atleast one corressponding address is required.")
	private List<@NotNull(message = "Corresponding address cannot be null.") @Valid Address> correspondingAddresses;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getJuniors() {
		return juniors;
	}

	public void setJuniors(List<String> juniors) {
		this.juniors = juniors;
	}

	public List<Address> getCorrespondingAddresses() {
		return correspondingAddresses;
	}

	public void setCorrespondingAddresses(List<Address> correspondingAddresses) {
		this.correspondingAddresses = correspondingAddresses;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, firstName=%s, dateOfBirth=%s, getResponseStatus()=%s]", id, firstName,
				dateOfBirth);
	}

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private static class Address {
		@NotBlank(message = "Address cannot be null or blank.")
		@Size(min = 10, max = 100, message = "Address must be given in 10 to 100 characters.")
		private String address;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
