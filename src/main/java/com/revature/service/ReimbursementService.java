package com.revature.service;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.repo.ReimbursementDao;

public class ReimbursementService {
	private ReimbursementDao rd;

	public ReimbursementService(ReimbursementDao rd) {
		super();
		this.rd = rd;
	}

	public ReimbursementService() {
		this(new ReimbursementDao());
	}

	public List<Reimbursement> retrieveUserReimbursements(User user) {
		return rd.retrieveUserReimbursements(user);
	}

	public List<Reimbursement> getPendingReimbursements() {
		return rd.getPendingReimbursements();
	}

	public int addReimbursementRequest(Reimbursement r) {
		return rd.create(r);
	}

	public int updateReimbursement(Reimbursement r) {
		return rd.update(r);
	}

	public Reimbursement findById(int id) {
		return rd.findById(id);
	}

}
