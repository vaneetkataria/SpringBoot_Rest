package com.kataria.springboot.rest.practice.manager.user.beans;

import java.util.List;

import com.kataria.springboot.rest.practice.core.beans.RestResponse;

public class UserList extends RestResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<User> users;

	public UserList() {
		super();
	}

	public UserList(List<User> users) {
		super();
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
