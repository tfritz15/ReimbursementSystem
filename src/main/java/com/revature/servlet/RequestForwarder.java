package com.revature.servlet;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.UserController;

public class RequestForwarder {

	public String routes(HttpServletRequest req) {
		switch (req.getRequestURI()) {
		case "/ReimbursementSystem/login.html":
			return new UserController().login(req);
		default:
			return "html/login.html";
		}
	}
}
