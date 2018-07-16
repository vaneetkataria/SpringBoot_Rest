package com.kataria.springboot.rest.practice.web.user.helper.resource.support;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kataria.springboot.rest.practice.manager.user.beans.User.Address;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressResource extends ResourceSupport {

	private Address address;

	public AddressResource(Address address) {
		super();
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
