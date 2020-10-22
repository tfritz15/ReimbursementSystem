package com.revature.model;

public class UserRole {
	private int userRoleID;
	private String userRoleDesc;

	public int getUserRoleID() {
		return userRoleID;
	}

	public void setUserRoleID(int userRoleID) {
		this.userRoleID = userRoleID;
	}

	public String getUserRoleDesc() {
		return userRoleDesc;
	}

	public void setUserRoleDesc(String userRoleDesc) {
		this.userRoleDesc = userRoleDesc;
	}

	public UserRole(int userRoleID, String userRoleDesc) {
		super();
		this.userRoleID = userRoleID;
		this.userRoleDesc = userRoleDesc;
	}

	public UserRole() {
		super();
	}

}
