package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controller.UserController;
import com.revature.model.User;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet(name = "account", urlPatterns = { "/account" }, loadOnStartup = 1)
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserController uc = new UserController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doPost(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath() +
		// "/account");
		request.getRequestDispatcher("/html/account.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.isNew()) {
			// response.sendRedirect("/html/error.html");
			request.getRequestDispatcher("/html/error.html").forward(request, response);
		}
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		if (uc.verifyLoginCredentials(uname, pass) != 1) {
			// response.sendRedirect("/html/error.html");
			request.getRequestDispatcher("/html/error.html").forward(request, response);
		} else {
			String username = request.getParameter("acctName");
			String password = request.getParameter("pass");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			int userrole = Integer.parseInt(request.getParameter("userrole"));
			User user = new User(0, username, password, firstname, lastname, email, userrole);
			uc.registerUser(user);
		}

	}

}
