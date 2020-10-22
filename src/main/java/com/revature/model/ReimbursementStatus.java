package com.revature.model;

public class ReimbursementStatus {
	private int reimbursementStatusID;
	private String reimbursementStatusDesc;
	public int getReimbursementStatusID() {
		return reimbursementStatusID;
	}
	public void setReimbursementStatusID(int reimbursementStatusID) {
		this.reimbursementStatusID = reimbursementStatusID;
	}
	public String getReimbursementStatus() {
		return reimbursementStatusDesc;
	}
	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatusDesc = reimbursementStatus;
	}
	public ReimbursementStatus() {
		super();
	}
	public ReimbursementStatus(int reimbursementStatusID, String reimbursementStatus) {
		super();
		this.reimbursementStatusID = reimbursementStatusID;
		this.reimbursementStatusDesc = reimbursementStatus;
	}
	
	
}
