package com.revature.repo;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDaoTest {
	private UserDao ud = new UserDao();
	private User user;
	private Connection conn;

	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		try {
			// Establish database connection and set auto commit to false
			conn = ConnectionUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			long currentTime = System.currentTimeMillis();
			// Create a known state of the test database before each test
			user = new User(1, "username", "password", "John", "Doe", "email@gmail.com", 0);
			ud.create(user);
			user = new User(2, "username2", "password2", "Jane", "Doe", "asdf@gmail.com", 0);
			ud.create(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void cleanup() {
		try {
			conn.rollback();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findAllTest() {
		List<User> twoUsers = new ArrayList<>();
		twoUsers = ud.findAll();
		assertEquals(2, twoUsers.size());
	}

	@Test
	public void findUsernameWithFindByIDTest() {
		user = ud.findById(1);
		assertEquals("username", user.getUsername());
	}

	@Test
	public void updateUserTest() {
		user = new User(1, "username", "wasspord", "John", "Doe", "email@gmail.com", 0);
		ud.update(user);
		assertEquals(user.getPassword(), ud.findById(1).getPassword());
	}

	@Test
	public void deleteUserTest() {
		ud.delete(1);
		assertEquals(1, ud.findAll().size());
	}
}
