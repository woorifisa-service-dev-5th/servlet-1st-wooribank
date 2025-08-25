package com.woori_bank.be.account.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.woori_bank.be.account.model.Account;
import com.woori_bank.be.account.model.AccountType;
import com.woori_bank.be.util.init.ConnectionPool;

public class AccountDAO {
	
	public static Account findByAccountNumber(String number) {
	    final String sql = 
	        "SELECT account_id, account_number, account_type, date, balance, cl_id, cl_job " +
	        "FROM account WHERE account_number = ?";

	    try (Connection conn = ConnectionPool.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, number);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Account account = new Account();
	                account.setId(rs.getLong("account_id")); // PK
	                account.setNumber(rs.getString("account_number"));
	                account.setType(AccountType.valueOf(rs.getString("account_type"))); // enum 변환
	                Timestamp ts = rs.getTimestamp("date");
	                if (ts != null) {
	                    account.setCreateAt(ts.toInstant().atOffset(java.time.ZoneOffset.UTC));
	                }
	                account.setBalance(BigInteger.valueOf(rs.getLong("balance")));
	                account.setClientId(rs.getLong("cl_id"));
	                return account;
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("계좌 조회 실패", e);
	    }

	    return null; // 못 찾으면 null 반환
	}
	
	public static void updateBalance(Connection conn, Account account) throws SQLException {
	    String sql = "UPDATE account SET balance=? WHERE account_number=?";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setLong(1, account.getBalance().longValueExact());
	        ps.setString(2, account.getNumber());
	        ps.executeUpdate();
	    }
	}
	
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
	
	public static List<Account> findByClientId(long clientId) {
	    List<Account> list = new ArrayList<>();
	    String sql = "SELECT * FROM account WHERE cl_id = ?";
	    try (Connection conn = ConnectionPool.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setLong(1, clientId);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Account acc = new Account();
	                acc.setId(rs.getLong("id"));
	                acc.setNumber(rs.getString("account_number"));
	                acc.setType(AccountType.valueOf(rs.getString("account_type")));
	                acc.setCreateAt(rs.getTimestamp("date").toInstant().atOffset(ZoneOffset.UTC));
	                acc.setBalance(BigInteger.valueOf(rs.getLong("balance")));
	                acc.setClientId(rs.getLong("cl_id"));
	                list.add(acc);
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("계좌 조회 실패", e);
	    }
	    return list;
	}



}
