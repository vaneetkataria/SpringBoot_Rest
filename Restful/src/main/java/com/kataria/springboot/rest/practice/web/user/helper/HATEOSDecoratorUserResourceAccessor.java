package com.kataria.springboot.rest.practice.web.user.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;

import com.kataria.springboot.rest.practice.manager.user.UserResourceManager;
import com.kataria.springboot.rest.practice.manager.user.beans.User;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;
import com.kataria.springboot.rest.practice.web.user.UserResourceService;
import com.kataria.springboot.rest.practice.web.user.helper.resource.support.AddressResource;
import com.kataria.springboot.rest.practice.web.user.helper.resource.support.AddressResourceAssembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

@Component
public class HATEOSDecoratorUserResourceAccessor {

	private static final String GET_ALL_USERS_LINK = "GET_ALL_USERS";
	private static final String GET_USER_LINK = "GET_USER";
	private static final String DELETE_USER_LINK = "DELETE_USER";

	@Autowired
	private UserResourceManager userResourceManager;

	private AddressResourceAssembler addressResourceAssembler = new AddressResourceAssembler(UserResourceService.class,
			AddressResource.class);

	public Resource<UserList> getAllUsers() throws UserResourceException {
		try {
			UserList userList = userResourceManager.getAllUsers();
			userList.setSuccess();
			Resource<UserList> resource = new Resource<>(userList,
					(linkTo(methodOn(UserResourceService.class).getAllUsers()).withSelfRel()));
			return resource;
		} catch (UserResourceException e) {
			throw e;
		}
	}

	public Resource<User> getUser(Integer userId) throws UserResourceException {
		try {
			User user = userResourceManager.getUser(userId);
			User responseUser = (User) user.clone();
			responseUser.setSuccess();
			Resource<User> userResource = new Resource<>(responseUser);
			userResource.add(linkTo(methodOn(UserResourceService.class).getUser(userId)).withSelfRel());
			userResource.add(linkTo(methodOn(UserResourceService.class).deleteUser(userId)).withRel(DELETE_USER_LINK));
			return userResource;
		} catch (CloneNotSupportedException e) {
			throw new UserResourceException(e.getMessage(), e);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	public Resource<User> addUser(User user) throws UserResourceException {
		try {
			User createduser = userResourceManager.addUser(user);
			User responseUser = (User) createduser.clone();
			responseUser.setSuccess();
			Resource<User> userResource = new Resource<User>(responseUser,
					linkTo(methodOn(UserResourceService.class).getAllUsers()).withRel(GET_ALL_USERS_LINK),
					linkTo(methodOn(UserResourceService.class).getUser(responseUser.getId())).withRel(GET_USER_LINK),
					linkTo(methodOn(UserResourceService.class).deleteUser(responseUser.getId()))
							.withRel(DELETE_USER_LINK));
			return userResource;
		} catch (CloneNotSupportedException e) {
			throw new UserResourceException(e.getMessage(), e);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	public Resource<User> deleteUser(Integer userId) throws UserResourceException {
		try {
			userResourceManager.deleteUser(userId);
			User user = new User();
			user.setId(userId);
			Resource<User> userResource = new Resource<>(new User(),
					linkTo(methodOn(UserResourceService.class).getAllUsers()).withRel(GET_ALL_USERS_LINK));
			return userResource;
		} catch (UserResourceException e) {
			throw e;
		}

	}

	public List<AddressResource> getAllCorresspondingAddress(Integer userId) throws UserResourceException {
		try {
			return addressResourceAssembler.toResources(userResourceManager.getAllCorresspondingAddress(userId));
		} catch (UserResourceException e) {
			throw e;
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

}
