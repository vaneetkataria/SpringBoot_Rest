package com.kataria.springboot.rest.practice.web.user.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kataria.springboot.rest.practice.manager.user.UserResourceManager;
import com.kataria.springboot.rest.practice.manager.user.beans.User;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;
import com.kataria.springboot.rest.practice.web.user.helper.resource.support.AddressResource;

@Component
public class HttpResponsePrepratorUserResourceAccessor {

	@Autowired
	private HATEOSDecoratorUserResourceAccessor userResourceManager;

	public ResponseEntity<Resource<UserList>> getAllUsers() throws UserResourceException {
		try {
			Resource<UserList> userList = userResourceManager.getAllUsers();
			return ResponseEntity.ok().body(userList);
		} catch (UserResourceException e) {
			HttpStatus httpStatus = null;
			switch (e.getMessage()) {
			case UserResourceManager.NO_USERS_EXIST:
				httpStatus = HttpStatus.NOT_FOUND;
				break;
			default:
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			}
			e.setHttpStatus(httpStatus);
			throw e;
		}
	}

	public ResponseEntity<Resource<User>> getUser(Integer userId) throws UserResourceException {
		try {
			Resource<User> userResource = userResourceManager.getUser(userId);
			return ResponseEntity.ok().body(userResource);
		} catch (UserResourceException e) {
			HttpStatus httpStatus = null;
			switch (e.getMessage()) {
			case UserResourceManager.NO_USER_EXIST:
				httpStatus = HttpStatus.NOT_FOUND;
				break;
			default:
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			e.setHttpStatus(httpStatus);
			throw e;
		}
	}

	public ResponseEntity<Resource<User>> addUser(User user) throws UserResourceException {
		try {
			Resource<User> createduser = userResourceManager.addUser(user);
			return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(createduser.getContent().getId()).toUri()).body(createduser);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	public ResponseEntity<Resource<User>> deleteUser(Integer userId) throws UserResourceException {
		try {
			Resource<User> userResource = userResourceManager.deleteUser(userId);
			return ResponseEntity.ok().body(userResource);
		} catch (UserResourceException e) {
			HttpStatus httpStatus = null;
			switch (e.getMessage()) {
			case UserResourceManager.NO_USER_EXIST:
				httpStatus = HttpStatus.NOT_FOUND;
				break;
			default:
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			e.setHttpStatus(httpStatus);
			throw e;
		}

	}

	public ResponseEntity<List<AddressResource>> getAllCorresspondingAddress(Integer userId)
			throws UserResourceException {
		try {
			List<AddressResource> addressResources = userResourceManager.getAllCorresspondingAddress(userId);
			return ResponseEntity.ok().body(addressResources);
		} catch (UserResourceException e) {
			HttpStatus httpStatus = null;
			switch (e.getMessage()) {
			case UserResourceManager.NO_USER_EXIST:
				httpStatus = HttpStatus.NOT_FOUND;
				break;
			default:
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			e.setHttpStatus(httpStatus);
			throw e;
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

}
