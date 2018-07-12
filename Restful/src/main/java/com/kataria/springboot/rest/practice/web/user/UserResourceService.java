package com.kataria.springboot.rest.practice.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kataria.springboot.rest.practice.manager.user.beans.User;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;
import com.kataria.springboot.rest.practice.web.user.helper.UserResourceAccessor;

@RestController
public class UserResourceService {

	@Autowired
	private UserResourceAccessor userResourceAccessor;

	@GetMapping(path = "/users")
	public ResponseEntity<UserList> getAllUsers() throws UserResourceException {
		try {
			return userResourceAccessor.getAllUsers();
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@GetMapping(path = "/users/{userId}")
	public ResponseEntity<User> getAllUsers(@PathVariable Integer userId) throws UserResourceException {
		try {
			return userResourceAccessor.getUser(userId);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> addUser(@RequestBody User user) throws UserResourceException {
		try {
			return userResourceAccessor.addUser(user);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@DeleteMapping(path = "/users/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer userId) throws UserResourceException {
		try {
			return userResourceAccessor.deleteUser(userId);
		} catch (UserResourceException e) {
			throw e;
		}
	}

}
