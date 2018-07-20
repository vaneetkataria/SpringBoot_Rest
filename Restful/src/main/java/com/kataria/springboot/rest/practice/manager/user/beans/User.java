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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ApiModel(description = "This Model represents the JSON data to be sent in request body for adding one user .")
public class User extends RestResponse implements Cloneable {

	@ApiModelProperty(value = "User ID. Not needed while adding a user.", required = false, position = 1,
			allowableValues = "range[1, infinity]")
	@PositiveOrZero(message = "User Id must be positive.")
	private int id;

	@ApiModelProperty(value = "User's First Name. It Cannot be null or empty .", required = true, position = 2,
			example = "Vaneet")
	@NotBlank(message = "First name cannot be empty.")
	private String firstName;

	@ApiModelProperty(
			value = "User's Date of birth. It Cannot be null , empty or a time instance in present or future . Format should be yyyy-mm-ddTHH:mm:ss.sssZ .",
			required = true, position = 3)
	@NotNull(message = "Date of birth cannot be null.")
	@Past(message = "Date of birth must be in Past.")
	private Date dateOfBirth;

	@ApiModelProperty(value = "User's Address . It Cannot be null . It should be a valid address as specified .",
			required = true, position = 4)
	@NotNull(message = "Address cannot be null.")
	@Valid
	private Address address;

	@ApiModelProperty(
			value = "User's Juniors . It Cannot be a null or empty list. Elements in the list cannot be null or empty . ",
			required = true, position = 5)
	@NotEmpty(message = "Atleast one Junior is required.")
	private List<@NotBlank(message = "Junior Name cannot be null or empty.") String> juniors;

	@ApiModelProperty(
			value = "User's Corressponding addresses . It Cannot be a null or empty list. All List elements must be a valid address.",
			required = true, position = 6)
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

	@ApiModel(description = "This represents user's address .")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class Address {

		@ApiModelProperty(
				value = "This feild cannot be null or empty . It should be a String having 10 to 100 characters.",
				required = true)
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
