package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementDao implements DaoContract<Reimbursement, Integer> {
	private UserDao ud = new UserDao();

	@Override
	public List<Reimbursement> findAll() {
		Reimbursement reimbursement;
		List<Reimbursement> reimbursements = new LinkedList<>();
		String sql = "select * from ers_reimbursement";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbursement = Reimbursement.Builder.newInstance()
						.setReimbursementID(rs.getInt(1))
						.setAmount(rs.getInt(2))
						.setReimbursementSubmitted(rs.getTimestamp(3))
						.setReimbursementResolved(rs.getTimestamp(4))
						.setDescription(rs.getString(5))
						.setReceipt(rs.getBytes(6))
						.setAuthor(ud.findById(rs.getInt(7)))
						.setResolver(ud.findById(rs.getInt(8)))
						.setReimbursementStatus(rs.getInt(9))
						.setReimbursementType(rs.getInt(10))
						.build();
				reimbursements.add(reimbursement);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public Reimbursement findById(Integer i) {
		Reimbursement r = null;
		String sql = "select * from ers_reimbursement where reimb_id = ?";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = Reimbursement.Builder.newInstance()
						.setReimbursementID(rs.getInt(1))
						.setAmount(rs.getInt(2))
						.setReimbursementSubmitted(rs.getTimestamp(3))
						.setReimbursementResolved(rs.getTimestamp(4))
						.setDescription(rs.getString(5))
						.setReceipt(rs.getBytes(6))
						.setAuthor(ud.findById(rs.getInt(7)))
						.setResolver(ud.findById(rs.getInt(8)))
						.setReimbursementStatus(rs.getInt(9))
						.setReimbursementType(rs.getInt(10))
						.build();
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public int update(Reimbursement t) {
		String sql = "update ers_reimbursement" +
				" set reimb_amount = ?, reimb_submitted = ?, reimb_resolved = ?," +
				" reimb_description = ?, reimb_receipt = ?, reimb_author = ?, reimb_resolver = ?," +
				" reimb_status_id = ?, reimb_type_id = ?" +
				" where reimb_id = ?";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, t.getAmount());
			ps.setTimestamp(2, t.getReimbursementSubmitted());
			ps.setTimestamp(3, t.getReimbursementResolved());
			ps.setString(4, t.getDescription());
			ps.setBytes(5, t.getReceipt());
			ps.setInt(6, t.getAuthor().getPrimaryKey());
			ps.setInt(7, t.getResolver().getPrimaryKey());
			ps.setInt(8, t.getReimbursementStatus());
			ps.setInt(9, t.getReimbursementType());
			ps.setInt(10, t.getReimbursementID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int create(Reimbursement t) {
		String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted," +
				" reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver," +
				" reimb_status_id, reimb_type_id) values (?,?,?,?,?,?,?,?,?)";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, t.getAmount());
			ps.setTimestamp(2, t.getReimbursementSubmitted());
			ps.setTimestamp(3, t.getReimbursementResolved());
			ps.setString(4, t.getDescription());
			ps.setBytes(5, t.getReceipt());
			ps.setInt(6, t.getAuthor().getPrimaryKey());
			if (t.getResolver() != null)
				ps.setInt(7, t.getResolver().getPrimaryKey());
			else
				ps.setNull(7, Types.INTEGER);
			ps.setInt(8, t.getReimbursementStatus());
			ps.setInt(9, t.getReimbursementType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int delete(Integer i) {
		String sql = "delete from ers_reimbursement where reimb_id = ?";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, i);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public List<Reimbursement> retrieveUserReimbursements(User user) {
		Reimbursement reimbursement;
		List<Reimbursement> reimbursements = new LinkedList<>();
		String sql = "select * from ers_reimbursement where reimb_author = ?";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, user.getPrimaryKey());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbursement = Reimbursement.Builder.newInstance()
						.setReimbursementID(rs.getInt(1))
						.setAmount(rs.getInt(2))
						.setReimbursementSubmitted(rs.getTimestamp(3))
						.setReimbursementResolved(rs.getTimestamp(4))
						.setDescription(rs.getString(5))
						.setReceipt(rs.getBytes(6))
						.setAuthor(ud.findById(rs.getInt(7)))
						.setResolver(ud.findById(rs.getInt(8)))
						.setReimbursementStatus(rs.getInt(9))
						.setReimbursementType(rs.getInt(10))
						.build();
				reimbursements.add(reimbursement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	public List<Reimbursement> getPendingReimbursements() {
		Reimbursement reimbursement;
		List<Reimbursement> reimbursements = new LinkedList<>();
		String sql = "select * from ers_reimbursement where reimb_status_id = ?";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, 0);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbursement = Reimbursement.Builder.newInstance()
						.setReimbursementID(rs.getInt(1))
						.setAmount(rs.getInt(2))
						.setReimbursementSubmitted(rs.getTimestamp(3))
						.setReimbursementResolved(rs.getTimestamp(4))
						.setDescription(rs.getString(5))
						.setReceipt(rs.getBytes(6))
						.setAuthor(ud.findById(rs.getInt(7)))
						.setResolver(ud.findById(rs.getInt(8)))
						.setReimbursementStatus(rs.getInt(9))
						.setReimbursementType(rs.getInt(10))
						.build();
				reimbursements.add(reimbursement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}
}
