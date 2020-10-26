package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

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

	public String login(HttpServletRequest req) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		int isUser = us.verifyLoginCredentials(username, password);
		if (isUser == 0) {
			return "html/reimbursement.html";
		} else if (isUser == 1) {
			return "html/pending.html";
		}
		return "User not found";
	}
}
