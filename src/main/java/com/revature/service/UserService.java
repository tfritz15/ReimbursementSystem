package com.revature.service;

import com.revature.model.User;
import com.revature.repo.UserDao;

public class UserService {
	private UserDao ud;

	public UserService(UserDao ud) {
		super();
		this.ud = ud;
	}

	public UserService() {
		this(new UserDao());
	}

	public int verifyLoginCredentials(String uname, String pass) {
		return ud.verifyLoginCredentials(uname, pass);
	}

	public int registerUser(User user) {
		ud.create(user);
		return 1;
	}

	public User findByUsername(String username) {
		return ud.findByUsername(username);
	}
}
