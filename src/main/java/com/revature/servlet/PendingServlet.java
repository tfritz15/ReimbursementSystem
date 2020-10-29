package com.revature.servlet;

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
import com.revature.model.User;

/**
 * Servlet implementation class PendingServlet
 */
@WebServlet(name = "pending", urlPatterns = { "/pending" }, loadOnStartup = 1)
public class PendingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserController uc = new UserController();
	private final ReimbursementController rc = new ReimbursementController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PendingServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.isNew()) {
			response.sendRedirect("/html/error.html"); // user must sign in first
		} else {
			User user = uc.findbyUsername(session.getAttribute("username").toString());
			List<Reimbursement> userReimbursements = rc.retrieveUserReimbursements(user);
			try { // return user's list of reimbursements
				response.getWriter().println(new ObjectMapper().writeValueAsString(userReimbursements));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// response.getWriter().append("Served at: ").append(request.getContextPath() +
		// "/pending");
		// request.getRequestDispatcher("/html/pending.html").forward(request,
		// response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Reimbursement reimbursement = Reimbursement.Builder.newInstance()
				.setReimbursementID(0) // TODO implement generator for reimbursement id
				.setAmount(Integer.parseInt(request.getParameter("amount")))
				.setReimbursementSubmitted(new Timestamp(System.currentTimeMillis()))
				.setReimbursementResolved(null)
				.setDescription(request.getParameter("description"))
				.setReceipt(null)
				.setAuthor(uc.findbyUsername(request.getSession().getAttribute("username").toString()))
				.setResolver(null)
				.setReimbursementStatus(0)
				.setReimbursementType(Integer.parseInt(request.getParameter("type")))
				.build();
		rc.addReimbursementRequest(reimbursement);
	}

}
