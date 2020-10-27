package com.revature.controller;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimbursementService;

public class ReimbursementController {
	private ReimbursementService rs;

	public ReimbursementController(ReimbursementService rs) {
		super();
		this.rs = rs;
	}

	public ReimbursementController() {
		this(new ReimbursementService());
	}

	public List<Reimbursement> retrieveUserReimbursements(User user) {
		return rs.retrieveUserReimbursements(user);
	}

	public List<Reimbursement> getPendingReimbursements() {
		return rs.getPendingReimbursements();
	}

	public int addReimbursementRequest(Reimbursement r) {
		return rs.addReimbursementRequest(r);
	}

	public int updateReimbursement(Reimbursement r) {
		return rs.updateReimbursement(r);
	}

}
