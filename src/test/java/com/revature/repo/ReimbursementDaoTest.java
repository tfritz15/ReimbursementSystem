package com.revature.repo;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoTest {
	private ReimbursementDao rd = new ReimbursementDao();
	private Reimbursement reimbursement;
	private Connection conn;

	@Before
	public void setup() {
		try {
			// Establish database connection and set auto commit to false
			conn = ConnectionUtil.getInstance().getConnection();
			conn.setAutoCommit(false);
			long currentTime = System.currentTimeMillis();
			// Create a known state of the test database before each test
			reimbursement = Reimbursement.Builder.newInstance()
					.setReimbursementID(1)
					.setAmount(100)
					.setReimbursementSubmitted(new Timestamp(currentTime))
					.setReimbursementResolved(new Timestamp(currentTime))
					.setDescription("lodging")
					.setReceipt(null)
					.setAuthor(new User(1, "username", "pass", "first", "last", "e@email.com", 1))
					.setResolver(new User(1, "username2", "pass2", "first2", "last2", "e2@email.com", 1))
					.setReimbursementStatus(1)
					.setReimbursementType(1)
					.build();
			rd.create(reimbursement);
			reimbursement = Reimbursement.Builder.newInstance()
					.setReimbursementID(2)
					.setAmount(500)
					.setReimbursementSubmitted(new Timestamp(currentTime))
					.setReimbursementResolved(new Timestamp(currentTime))
					.setDescription("lodging")
					.setReceipt(null)
					.setAuthor(new User(1, "username", "pass", "first", "last", "e@email.com", 1))
					.setResolver(new User(1, "username2", "pass2", "first2", "last2", "e2@email.com", 1))
					.setReimbursementStatus(2)
					.setReimbursementType(2)
					.build();
			rd.create(reimbursement);
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
		List<Reimbursement> twoReimbursements = new ArrayList<>();
		twoReimbursements = rd.findAll();
		assertEquals(2, twoReimbursements.size());
	}

	@Test
	public void findReimbursementAmountWithFindByIDTest() {
		reimbursement = rd.findById(1);
		assertEquals(100, reimbursement.getAmount());
	}

	@Test
	public void updateReimbursementTest() {
		long currentTime = System.currentTimeMillis();
		reimbursement = Reimbursement.Builder.newInstance()
				.setReimbursementID(2)
				.setAmount(1000)
				.setReimbursementSubmitted(new Timestamp(currentTime))
				.setReimbursementResolved(new Timestamp(currentTime))
				.setDescription("lodging")
				.setReceipt(null)
				.setAuthor(new User(1, "username", "pass", "first", "last", "e@email.com", 1))
				.setResolver(new User(1, "username2", "pass2", "first2", "last2", "e2@email.com", 1))
				.setReimbursementStatus(2)
				.setReimbursementType(2)
				.build();
		rd.update(reimbursement);
		assertEquals(reimbursement.getAmount(), rd.findById(2).getAmount());

	}

	@Test
	public void deleteReimbursementTest() {
		rd.delete(1);
		assertEquals(1, rd.findAll().size());
	}

}
