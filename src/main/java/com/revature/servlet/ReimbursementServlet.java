package com.revature.servlet;

import java.io.IOError;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controller.ReimbursementController;
import com.revature.controller.UserController;
import com.revature.model.Reimbursement;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReimbursementController rc = new ReimbursementController();
	private UserController uc = new UserController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReimbursementServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		if (session.isNew()) { // new user should not have access to this page
			response.sendRedirect("/html/error.html");
		} else if (uc.verifyLoginCredentials(uname, pass) != 1) {
			response.sendRedirect("/html/error.html");
		} else {
			List<Reimbursement> pending = rc.getPendingReimbursements();
			try {
				response.getWriter().println(new ObjectMapper().writeValueAsString(pending));
			} catch (IOError e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.isNew()) { // new user should not have access to this page
			response.sendRedirect("/html/error.html");
		}
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		if (uc.verifyLoginCredentials(uname, pass) != 1) {
			response.sendRedirect("/html/error.html");
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			int status = Integer.parseInt(request.getParameter("status"));
			String username = request.getParameter("username");
			Reimbursement r = rc.findById(id);
			if (r != null) {
				Reimbursement update = Reimbursement.Builder.newInstance()
						.setReimbursementID(r.getReimbursementID())
						.setAmount(r.getAmount())
						.setReimbursementSubmitted(r.getReimbursementSubmitted())
						.setReimbursementResolved(new Timestamp(System.currentTimeMillis()))
						.setDescription(r.getDescription())
						.setReceipt(null)
						.setAuthor(r.getAuthor())
						.setResolver(uc.findbyUsername(username))
						.setReimbursementStatus(status)
						.setReimbursementType(r.getReimbursementType())
						.build();
				rc.updateReimbursement(update);
			}
		}

	}
}
