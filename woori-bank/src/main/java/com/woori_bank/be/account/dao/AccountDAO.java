package com.woori_bank.be.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.woori_bank.be.account.model.Account;
import com.woori_bank.be.util.init.ConnectionPool;

public class AccountDAO {
	
	public static long save(Account account) {

	    final String sql =
	        "INSERT INTO account (account_number, account_type, date, balance, cl_id) " +
	        "VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = ConnectionPool.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setString(1, account.getNumber());
	        ps.setString(2, account.getType().name());
	        // created_at 이 DATETIME이면 Timestamp 사용
	        ps.setTimestamp(3, java.sql.Timestamp.from(account.getCreateAt().toInstant()));
	        ps.setLong(4, account.getBalance().longValueExact());
	        ps.setLong(5, account.getClientId());

	        int updated = ps.executeUpdate();
	        if (updated == 0) throw new SQLException("insert failed");

	        try (ResultSet keys = ps.getGeneratedKeys()) {
	            if (keys.next()) return keys.getLong(1);
	            throw new SQLException("no id returned");
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("계좌 저장 실패", e);
	    }
	}



}
