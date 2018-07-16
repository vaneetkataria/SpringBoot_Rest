package com.kataria.springboot.rest.practice.web.user.helper.resource.support;

import org.springframework.hateoas.Link;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kataria.springboot.rest.practice.manager.user.beans.User.Address;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;
import com.kataria.springboot.rest.practice.web.user.UserResourceService;

public class AddressResourceAssembler extends ResourceAssemblerSupport<Address, AddressResource> {

	private static final String GET_ALL_USERS_LINK = "GET_ALL_USERS";

	public AddressResourceAssembler(Class<?> controllerClass, Class<AddressResource> resourceType) {
		super(controllerClass, resourceType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AddressResource toResource(Address entity) {
		AddressResource resource = new AddressResource(entity);
		try {
			resource.add(linkTo(methodOn(UserResourceService.class).getAllUsers()).withRel(GET_ALL_USERS_LINK));
			resource.add(new Link(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(entity.getAddress()).toUriString()));
		} catch (UserResourceException e) {
		}
		return resource;
	}

	public AddressResource instantiateResource(Address entity) {
		AddressResource address = new AddressResource(entity);
		return address;
	}

}
