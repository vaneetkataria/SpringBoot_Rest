package com.kataria.springboot.rest.practice.web.user.helper;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kataria.springboot.rest.practice.manager.user.UserResourceManager;
import com.kataria.springboot.rest.practice.manager.user.beans.User;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;

@Component
public class UserResourceAccessor {

	@Autowired
	private UserResourceManager userResourceManager;

	public ResponseEntity<UserList> getAllUsers() throws UserResourceException {
		try {
			UserList userList = userResourceManager.getAllUsers();
			userList.setSuccess();
			return ResponseEntity.status(HttpStatus.FOUND).body(userList);
		} catch (UserResourceException e) {
			HttpStatus httpStatus = null;
			switch (e.getMessage()) {
			case UserResourceManager.NO_USERS_EXIST:
				httpStatus = HttpStatus.NO_CONTENT;
				break;
			default:
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				break;
			}
			e.setHttpStatus(httpStatus);
			throw e;
		}
	}

	public ResponseEntity<User> getUser(Integer userId) throws UserResourceException {
		try {
			User user = userResourceManager.getUser(userId);
			User responseUser = (User) user.clone();
			responseUser.setSuccess();
			return ResponseEntity.status(HttpStatus.FOUND).body(responseUser);
		} catch (CloneNotSupportedException e) {
			throw new UserResourceException(e.getMessage(), e);
		} catch (UserResourceException e) {
			HttpStatus httpStatus = null;
			switch (e.getMessage()) {
			case UserResourceManager.NO_USER_EXIST:
				httpStatus = HttpStatus.NO_CONTENT;
				break;
			default:
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			e.setHttpStatus(httpStatus);
			throw e;
		}
	}

	public ResponseEntity<User> addUser(User user) throws UserResourceException {
		try {
			User createduser = userResourceManager.addUser(user);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(createduser.getId()).toUri();
			User responseUser = (User) createduser.clone();
			responseUser.setSuccess();
			return ResponseEntity.created(location).body(responseUser);
		} catch (CloneNotSupportedException e) {
			throw new UserResourceException(e.getMessage(), e);
		} catch (UserResourceException e) {
			throw e;
		}
	}

	public ResponseEntity<User> deleteUser(Integer userId) throws UserResourceException {
		try {
			userResourceManager.deleteUser(userId);
			return ResponseEntity.noContent().build();
		} catch (UserResourceException e) {
			HttpStatus httpStatus = null;
			switch (e.getMessage()) {
			case UserResourceManager.NO_USER_EXIST:
				httpStatus = HttpStatus.FORBIDDEN;
				break;
			default:
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			e.setHttpStatus(httpStatus);
			throw e;
		}

	}

}
