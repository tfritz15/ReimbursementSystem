package com.revature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class UserDao implements DaoContract<User, Integer> {

	@Override
	public List<User> findAll() {
		User user;
		List<User> users = new LinkedList<>();
		String sql = "select * from ers_users";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
				users.add(user);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new LinkedList<>();
	}

	@Override
	public User findById(Integer i) {
		User user = null;
		String sql = "select * from ers_users where ers_users_id = ?";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, i);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findByUsername(String username) {
		User user = null;
		String sql = "select * from ers_users where ers_username = ?";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int update(User t) {
		String sql = "update ers_users set ers_username = ?, ers_password = ?," +
				" user_first_name = ?, user_last_name = ?, user_email = ?, user_role_id = ? where ers_users_id = ?";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getEmail());
			ps.setInt(6, t.getUserRole());
			ps.setInt(7, t.getPrimaryKey());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int create(User t) {
		String sql = "insert into ers_users (ers_users_id, ers_username, ers_password," +
				"user_first_name, user_last_name, user_email, user_role_id) values (?,?,?,?,?,?,?)";
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, t.getPrimaryKey());
			ps.setString(2, t.getUsername());
			ps.setString(3, t.getPassword());
			ps.setString(4, t.getFirstName());
			ps.setString(5, t.getLastName());
			ps.setString(6, t.getEmail());
			ps.setInt(7, t.getUserRole());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	@Override
	public int delete(Integer i) {
		String sql = "delete from ers_users where ers_users_id = ?";
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

	public int verifyLoginCredentials(String uname, String pass) {
		String sql = "select user_role_id from ers_users where ers_username = ? and ers_password = ?";
		int userRole = -1;
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, uname);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				userRole = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userRole;
	}

}
