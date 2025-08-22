package com.woori_bank.be.user.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.woori_bank.be.user.model.User;
import com.woori_bank.be.util.init.ConnectionPool;
import com.zaxxer.hikari.HikariDataSource;

public class userDAO {

	public boolean saveUser(User user) {
		String sql = "INSERT INTO client "
				+ "(cl_userId, cl_userPw, cl_resi_num, cl_name, cl_number, cl_birthday, cl_address, cl_email, cl_job) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try (Connection conn = ConnectionPool.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserPw());
			ps.setString(3, user.getSsn());
			ps.setString(4, user.getName());
			ps.setString(5, user.getPhone());
			ps.setDate(6, Date.valueOf(user.getBrithday())); // c.getBirthday()가 LocalDate라고 가정
			ps.setString(7, user.getAddress());
			ps.setString(8, user.getEmail());
			ps.setString(9, user.getJob());

			int updated = ps.executeUpdate();
			if (updated > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next())
						return true; // 새로 생성된 cl_id
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean findDuplicateId(String userId) {
		String sql = "SELECT 1 FROM client WHERE cl_id = ?";
		try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next(); // 존재하면 true
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User findById(String checkId) {
		String sql = "SELECT cl_userId, cl_userPw, cl_id "
		           + "FROM client WHERE cl_userId = ?";

		try (Connection conn = ConnectionPool.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, checkId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String userId = rs.getString("cl_userId");
					String userPw = rs.getString("cl_userPw");
					long id = rs.getLong("cl_id");

					User user = new User(id, userId, userPw);
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
