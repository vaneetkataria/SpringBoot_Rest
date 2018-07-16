package com.kataria.springboot.rest.practice.manager.user;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.kataria.springboot.rest.practice.core.validate.Assert;
import com.kataria.springboot.rest.practice.manager.user.beans.User;
import com.kataria.springboot.rest.practice.manager.user.beans.UserList;
import com.kataria.springboot.rest.practice.manager.user.exception.UserResourceException;

@Component
public class UserResourceManager {

	public static final String NO_USERS_EXIST = "NO_USERS_EXIST";
	public static final String NO_USER_EXIST = "NO_USER_EXIST";
	public static final String INVALID_INPUT_PARAMS = "INVALID_INPUT_PARAMS";

	private static Map<Integer, User> useraMap = new HashMap<>();
	static {
		useraMap.put(1, new User(1, "Vaneet", new Date()));
		useraMap.put(2, new User(2, "Prakash", new Date()));
		useraMap.put(3, new User(3, "Deepak", new Date()));
	}

	public UserList getAllUsers() throws UserResourceException {
		try {
			Assert.isTrue(!useraMap.isEmpty(), NO_USERS_EXIST);
			return new UserList(Collections.list(Collections.enumeration(useraMap.values())));
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	public User getUser(Integer userId) throws UserResourceException {
		try {
			Assert.isTrue(useraMap.containsKey(userId), NO_USER_EXIST);
			return useraMap.get(userId);
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	public User addUser(User user) throws UserResourceException {
		try {
			user.setId(getMaxId());
			useraMap.put(user.getId(), user);
			return user;
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	public void deleteUser(Integer userId) throws UserResourceException {
		try {
			Assert.isTrue(useraMap.containsKey(userId), NO_USER_EXIST);
			useraMap.remove(userId);
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	private Integer getMaxId() {
		int maxId = 0;
		for (Integer i : useraMap.keySet()) {
			if (i > maxId)
				maxId = i;
		}
		return maxId + 1;

	}

	public List<User.Address> getAllCorresspondingAddress(Integer userId) throws UserResourceException {
		try {
			Assert.isTrue(useraMap.containsKey(userId), NO_USER_EXIST);
			return useraMap.get(userId).getCorrespondingAddresses();
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

	public List<String> getAllJuniors(Integer userId) throws UserResourceException {
		try {
			Assert.isTrue(useraMap.containsKey(userId), NO_USER_EXIST);
			return useraMap.get(userId).getJuniors();
		} catch (Exception e) {
			throw new UserResourceException(e.getMessage(), e);
		}
	}

}
