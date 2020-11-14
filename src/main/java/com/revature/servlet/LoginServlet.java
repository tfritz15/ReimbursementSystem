package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controller.UserController;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "login", urlPatterns = { "/login" }, loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserController uc = new UserController();

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
		request.getRequestDispatcher("/html/login.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Create session object
		// Create session object
		HttpSession session = request.getSession(true);
		// Get login info from form data and pass it to the controller
		String password = request.getParameter("password");
		int userValue = uc.verifyLoginCredentials(request.getParameter("username"),
				request.getParameter("password"));
		session.setAttribute("username", request.getParameter("username"));
		// Switch on userValue which is the role of the user, -1 if user doesn't exist
		try {
			switch (userValue) {
			case 0: // send employee to pending page to create or view pending reimbursements
				session.setAttribute("user_role", 0);
				request.getRequestDispatcher("html/pending.html").forward(request, response);
				break;
			case 1: // send finance manager to reimbursement page to manage reimbursements
				session.setAttribute("user_role", 1);
				request.getRequestDispatcher("html/reimbursement.html").forward(request, response);
				break;
			case -1: // user account not found
				request.getRequestDispatcher("html/error.html").forward(request, response);
				break;
			default:
				request.getRequestDispatcher("html/error.html").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
