package com.revature.servlet;

import java.io.IOError;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "reimbursement", urlPatterns = { "/reimbursement" }, loadOnStartup = 1)
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
		if (session.isNew()) { // new user should not have access to this page
			request.getRequestDispatcher("/html/error.html").forward(request, response);
		} else if (Integer.parseInt(session.getAttribute("user_role").toString()) != 1) {
			request.getRequestDispatcher("/html/error.html").forward(request, response);
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
		int id = Integer.parseInt(request.getParameter("id"));
		int status = Integer.parseInt(request.getParameter("status"));
		// String username = request.getParameter("username");
		HttpSession session = request.getSession();
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
					.setResolver(uc.findbyUsername(session.getAttribute("username").toString()))
					.setReimbursementStatus(status)
					.setReimbursementType(r.getReimbursementType())
					.build();
			rc.updateReimbursement(update);
		}
		request.getRequestDispatcher("/html/reimbursement.html").forward(request, response);
	}
}
