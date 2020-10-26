package com.revature.model;

import java.sql.Timestamp;

public class Reimbursement {
	private final int reimbursementID;
	private final int amount;
	private final Timestamp reimbursementSubmitted;
	private final Timestamp reimbursementResolved;
	private final String description;
	private final byte[] receipt;
	private final User author;
	private final User resolver;
	private final int reimbursementStatus;
	private final int reimbursementType;

	public Reimbursement(Builder builder) {
		this.reimbursementID = builder.reimbursementID;
		this.amount = builder.amount;
		this.reimbursementSubmitted = builder.reimbursementSubmitted;
		this.reimbursementResolved = builder.reimbursementResolved;
		this.description = builder.description;
		this.receipt = builder.receipt;
		this.author = builder.author;
		this.resolver = builder.resolver;
		this.reimbursementStatus = builder.reimbursementStatus;
		this.reimbursementType = builder.reimbursementType;
	}

	public static class Builder {
		private int reimbursementID;
		private int amount;
		private Timestamp reimbursementSubmitted;
		private Timestamp reimbursementResolved;
		private String description;
		private byte[] receipt;
		private User author;
		private User resolver;
		private int reimbursementStatus;
		private int reimbursementType;

		public static Builder newInstance() {
			return new Builder();
		}

		public Builder setReimbursementID(int reimbursementID) {
			this.reimbursementID = reimbursementID;
			return this;
		}

		public Builder setAmount(int amount) {
			this.amount = amount;
			return this;
		}

		public Builder setReimbursementSubmitted(Timestamp reimbursementSubmitted) {
			this.reimbursementSubmitted = reimbursementSubmitted;
			return this;
		}

		public Builder setReimbursementResolved(Timestamp reimbursementResolved) {
			this.reimbursementResolved = reimbursementResolved;
			return this;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public Builder setReceipt(byte[] receipt) {
			this.receipt = receipt;
			return this;
		}

		public Builder setAuthor(User author) {
			this.author = author;
			return this;
		}

		public Builder setResolver(User resolver) {
			this.resolver = resolver;
			return this;
		}

		public Builder setReimbursementStatus(int reimbursementStatus) {
			this.reimbursementStatus = reimbursementStatus;
			return this;
		}

		public Builder setReimbursementType(int reimbursementType) {
			this.reimbursementType = reimbursementType;
			return this;
		}

		public Reimbursement build() {
			return new Reimbursement(this);
		}
	}

	public int getReimbursementID() {
		return reimbursementID;
	}

	public int getAmount() {
		return amount;
	}

	public Timestamp getReimbursementSubmitted() {
		return reimbursementSubmitted;
	}

	public Timestamp getReimbursementResolved() {
		return reimbursementResolved;
	}

	public String getDescription() {
		return description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public User getAuthor() {
		return author;
	}

	public User getResolver() {
		return resolver;
	}

	public int getReimbursementStatus() {
		return reimbursementStatus;
	}

	public int getReimbursementType() {
		return reimbursementType;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementID=" + reimbursementID + ", amount=" + amount + ", reimbursementSubmitted="
				+ reimbursementSubmitted + ", reimbursementResolved=" + reimbursementResolved + ", description="
				+ description + ", receipt=" + receipt + ", author=" + author + ", resolver=" + resolver
				+ ", reimbursementStatus=" + reimbursementStatus + ", reimbursementType=" + reimbursementType + "]";
	}
}
