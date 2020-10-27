package com.revature.controller;

import com.revature.model.User;
import com.revature.service.UserService;

public class UserController {

	private UserService us;

	public UserController(UserService us) {
		super();
		this.us = us;
	}

	public UserController() {
		this(new UserService());
	}

	public int verifyLoginCredentials(String uname, String pass) {
		return us.verifyLoginCredentials(uname, pass);
	}

	public int registerUser(User user) {
		return us.registerUser(user);
	}

	public User findbyUsername(String username) {
		return us.findByUsername(username);
	}
}
