package com.revature.model;

import java.time.LocalDateTime;

public class Reimbursement {
	private int reimbursementID;
	private int number;
	private LocalDateTime reimbursementSubmitted;
	private LocalDateTime reimbursementResolved;
	private String description;
	private Byte[] receipt;
	private User author;
	private User resolver;
	private ReimbursementStatus reimbursementStatus;
	private ReimbursementType reimbursementType;

	public int getReimbursementID() {
		return reimbursementID;
	}

	public void setReimbursementID(int reimbursementID) {
		this.reimbursementID = reimbursementID;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public LocalDateTime getReimbursementSubmitted() {
		return reimbursementSubmitted;
	}

	public void setReimbursementSubmitted(LocalDateTime reimbursementSubmitted) {
		this.reimbursementSubmitted = reimbursementSubmitted;
	}

	public LocalDateTime getReimbursementResolved() {
		return reimbursementResolved;
	}

	public void setReimbursementResolved(LocalDateTime reimbursementResolved) {
		this.reimbursementResolved = reimbursementResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(Byte[] receipt) {
		this.receipt = receipt;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getReimbursementStatus() {
		return reimbursementStatus;
	}

	public void setReimbursementStatus(ReimbursementStatus reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}

	public ReimbursementType getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(ReimbursementType reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	public Reimbursement(int reimbursementID, int number, LocalDateTime reimbursementSubmitted,
			LocalDateTime reimbursementResolved, String description, Byte[] receipt, User author, User resolver,
			ReimbursementStatus reimbursementStatus, ReimbursementType reimbursementType) {
		super();
		this.reimbursementID = reimbursementID;
		this.number = number;
		this.reimbursementSubmitted = reimbursementSubmitted;
		this.reimbursementResolved = reimbursementResolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.reimbursementStatus = reimbursementStatus;
		this.reimbursementType = reimbursementType;
	}

	public Reimbursement() {
		super();
	}

}
