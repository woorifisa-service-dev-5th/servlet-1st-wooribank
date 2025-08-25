package com.woori_bank.be.transaction.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.woori_bank.be.util.constant.Const;
import com.woori_bank.be.util.init.ConnectionPool;

public class TransactionCommandDAO {
	
	public static final TransactionCommandDAO dao = new TransactionCommandDAO(); 

	private TransactionCommandDAO() {}

	public static void save(Connection conn, Transaction tx) throws SQLException {
	    String sql = "INSERT INTO transaction (account_id, type, description, amount, date) VALUES (?, ?, ?, ?, ?)";
	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, tx.getAccountId());
	        ps.setString(2, tx.getType());
	        ps.setString(3, tx.getDescription());
	        ps.setLong(4, tx.getAmount().longValueExact());
	        ps.setTimestamp(5, java.sql.Timestamp.valueOf(tx.getDate()));
	        ps.executeUpdate();
	    }
	}
	
	public void insert(String accountId, String type, String description, BigInteger amount, LocalDateTime date) {
		try(Connection connection = ConnectionPool.getConnection();) {
			String INSERT_SQL = "INSERT INTO TRANSACTION(account_id, t_type, description, amount, t_date) "
					+ "VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
			setPrepared(ps, accountId, type, description, amount, date);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void setPrepared(PreparedStatement ps, String accountId, String type, String description,
			BigInteger amount, LocalDateTime date) throws SQLException {
		ps.setString(0, accountId);
		ps.setString(1, type);
		ps.setString(2, description);
		ps.setInt(3, amount.intValue());
		ps.setTimestamp(4, Timestamp.from(date.atZone(Const.KST).toInstant()));
	}

}
