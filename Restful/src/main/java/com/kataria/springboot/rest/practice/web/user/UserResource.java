package com.kataria.springboot.rest.practice.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kataria.springboot.rest.practice.manager.user.UserResourceManager;
import com.kataria.springboot.rest.practice.manager.user.beans.User;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;

@RestController
public class UserResource {

	@Autowired
	private UserResourceManager userResourceManager;

	@GetMapping(path = "/users")
	public UserList getAllUsers() throws UserResourceException {
		try {
			return userResourceManager.getAllUsers();
		} catch (UserResourceException e) {
			throw e;
		}
	}

	@GetMapping(path = "/users/{userId}")
	public User getAllUsers(@PathVariable Integer userId) throws UserResourceException {
		try {
			return userResourceManager.getUser(userId);
		} catch (UserResourceException e) {
			throw e;
		}
	}

}
