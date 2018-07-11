package com.kataria.springboot.rest.practice.manager.user;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.kataria.springboot.rest.practice.core.validate.Assert;
import com.kataria.springboot.rest.practice.manager.user.beans.User;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;

@Component
public class UserResourceManager {

	private static Map<Integer, User> useraMap = new HashMap<>();
	static {
		useraMap.put(1, new User(1, "Vaneet", new Date()));
		useraMap.put(2, new User(2, "Prakash", new Date()));
		useraMap.put(3, new User(3, "Deepak", new Date()));
	}

	public UserList getAllUsers() throws UserResourceException {
		try {
			Assert.isTrue(!useraMap.isEmpty(), "NO_USER_EXISTS");
			UserList userList = new UserList(Collections.list(Collections.enumeration(useraMap.values())));
			userList.setSuccess().setHttpStatus(HttpStatus.OK);
			return userList;
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
	}

	public User getUser(Integer userId) {
		Assert.isTrue(useraMap.containsKey(userId), "UserId does not exist.");
		return useraMap.get(userId);
	}
}
