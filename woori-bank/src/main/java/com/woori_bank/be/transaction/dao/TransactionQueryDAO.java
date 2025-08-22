package com.woori_bank.be.transaction.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.woori_bank.be.util.init.ConnectionPool;

public class TransactionQueryDAO {
	
	public static final TransactionQueryDAO dao = new TransactionQueryDAO();
	
	private TransactionQueryDAO() {}
	
	public List<Transaction> getransactionByAccountId(String accountId){
		try(Connection connection = ConnectionPool.getConnection()){
			String sql = "SELECT * FROM Transaction t WHERE t.account_id=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(0, accountId);
			
			return toList(ps.executeQuery());
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<Transaction> toList(ResultSet rs) throws SQLException {
		List<Transaction> result = new ArrayList<>();
		while(rs.next()) {
			Long id = rs.getLong(0);
			String accountId = rs.getString(1);
			String type = rs.getString(2);
			BigInteger amount = BigInteger.valueOf(rs.getLong(3));
			LocalDateTime date = rs.getTimestamp(4).toLocalDateTime();
			String description = rs.getString(5);
			
			result.add(Transaction.create(accountId, type, description, amount, date));
		}
		
		return result;
	}
	
	

}
