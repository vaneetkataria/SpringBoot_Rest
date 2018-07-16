package com.kataria.springboot.rest.practice.web.user;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kataria.springboot.rest.practice.manager.user.beans.User;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;
import com.kataria.springboot.rest.practice.web.user.helper.HttpResponsePrepratorUserResourceAccessor;
import com.kataria.springboot.rest.practice.web.user.helper.resource.support.AddressResource;

@RestController
@Validated
public class UserResourceService {

	@Autowired
	private HttpResponsePrepratorUserResourceAccessor userResourceAccessor;

	@GetMapping(path = "/users")
	public ResponseEntity<Resource<UserList>> getAllUsers() throws UserResourceException {
		try {
			return userResourceAccessor.getAllUsers();
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@GetMapping(path = "/users/{userId}")
	public ResponseEntity<Resource<User>> getUser(
			@PathVariable @Positive(message = "UserID must be positive.") Integer userId) throws UserResourceException {
		try {
			return userResourceAccessor.getUser(userId);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Resource<User>> addUser(@RequestBody @Valid User user) throws UserResourceException {
		try {
			return userResourceAccessor.addUser(user);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@DeleteMapping(path = "/users/{userId}")
	public ResponseEntity<Resource<User>> deleteUser(
			@PathVariable @Positive(message = "UserID must be positive.") Integer userId) throws UserResourceException {
		try {
			return userResourceAccessor.deleteUser(userId);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@GetMapping(path = "/users/{userId}/corrAddresses")
	public ResponseEntity<List<AddressResource>> getAllCorresspondingAddress(
			@PathVariable @Positive(message = "UserID must be positive.") Integer userId) throws UserResourceException {
		try {
			return userResourceAccessor.getAllCorresspondingAddress(userId);
		} catch (UserResourceException e) {
			throw e;
		}
	}

}
