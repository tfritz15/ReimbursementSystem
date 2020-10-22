package com.revature.model;

public class ReimbursementType {
	private int reimbursementTypeID;
	private String reimbursementTypeDesc;
	public int getReimbursementTypeID() {
		return reimbursementTypeID;
	}
	public void setReimbursementTypeID(int reimbursementTypeID) {
		this.reimbursementTypeID = reimbursementTypeID;
	}
	public String getReimbursementTypeDesc() {
		return reimbursementTypeDesc;
	}
	public void setReimbursementTypeDesc(String reimbursementTypeDesc) {
		this.reimbursementTypeDesc = reimbursementTypeDesc;
	}
	public ReimbursementType(int reimbursementTypeID, String reimbursementTypeDesc) {
		super();
		this.reimbursementTypeID = reimbursementTypeID;
		this.reimbursementTypeDesc = reimbursementTypeDesc;
	}
	public ReimbursementType() {
		super();
	}
	
}
