package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String url = System.getenv("url");
	private String password = System.getenv("password");
	private String username = System.getenv("username");
	private static ConnectionUtil instance;

	private ConnectionUtil() {
	}

	public static ConnectionUtil getInstance() {
		if (instance == null) {
			instance = new ConnectionUtil();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		System.out.println(username);
		return DriverManager.getConnection(url, username, password);
	}

	public String callHash() {
		return password;
	}

}
