package com.revature.controller;

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

}
