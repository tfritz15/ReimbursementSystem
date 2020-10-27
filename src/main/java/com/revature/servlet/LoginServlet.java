package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controller.UserController;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserController uc = new UserController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Post for sending secure user login info
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create session object
		HttpSession session = request.getSession(true);
		// Get login info from form data and pass it to the controller
		int userValue = uc.verifyLoginCredentials(request.getParameter("username"),
				request.getParameter("password"));
		// Switch on userValue which is the role of the user, -1 if user doesn't exist
		try {
			switch (userValue) {
			case 0: // send employee to pending page to create or view pending reimbursements
				if (session.isNew()) {
					session.setAttribute("username", request.getParameter("username"));
					session.setAttribute("user_role", 0);
				}
				response.sendRedirect("/html/pending.html");
				break;
			case 1: // send finance manager to reimbursement page to manage reimbursements
				if (session.isNew()) {
					session.setAttribute("username", request.getParameter("username"));
					session.setAttribute("user_role", 1);
				}
				response.sendRedirect("/html/reimbursement.html");
				break;
			case -1: // user account not found, send to account page to create account
				response.sendRedirect("/html/reimbursement.html");
				break;
			default:
				response.sendRedirect("/html/error.html");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
