package com.woori_bank.be.murchandise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.woori_bank.be.model.Loan;
import com.woori_bank.be.util.init.ConnectionPool;

public class LoanDao {
	public Loan save(Loan l) throws SQLException {
		final String sql = "INSERT INTO loan (loan_id, loan_amount, loan_status, loan_date, guarantee_valid, account_id, Item_id, cl_number) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, l.getLoanId());
			if (l.getLoanAmount() == 0L)
				ps.setNull(2, Types.BIGINT);
			else
				ps.setLong(2, l.getLoanAmount());
			ps.setString(3, l.getLoanStatus()); // "ONGOING"/"PAID"/"DEFAULT"
			if (l.getLoanDate() != null)
				ps.setTimestamp(4, Timestamp.valueOf(l.getLoanDate()));
			else
				ps.setNull(4, Types.TIMESTAMP);
			if (l.getGuaranteeValid() == null)
				ps.setNull(5, Types.BOOLEAN);
			else
				ps.setBoolean(5, l.getGuaranteeValid());
			ps.setString(6, l.getAccountId());
			ps.setLong(7, l.getItemId());
			if (l.getClNumber() == null || l.getClNumber().isBlank())
				ps.setNull(8, Types.CHAR);
			else
				ps.setString(8, l.getClNumber());

			ps.executeUpdate();
		}
		return l;
	}

	public List<Loan> findByAccountId(String accountId) throws SQLException {
		final String sql = "SELECT * FROM loan WHERE account_id = ? ORDER BY loan_date DESC";
		List<Loan> out = new ArrayList<>();
		try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, accountId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next())
					out.add(map(rs));
			}
		}
		return out;
	}

	private Loan map(ResultSet rs) throws SQLException {
		Loan l = new Loan();
		l.setLoanId(rs.getString("loan_id"));
		l.setLoanAmount(rs.getLong("loan_amount"));
		l.setLoanStatus(rs.getString("loan_status"));
		Timestamp ts = rs.getTimestamp("loan_date");
		l.setLoanDate(ts != null ? ts.toLocalDateTime() : null);
		l.setGuaranteeValid((Boolean) rs.getObject("guarantee_valid")); // getBoolean은 NULL 구분 어려워서 getObject 사용
		l.setAccountId(rs.getString("account_id"));
		l.setItemId(rs.getLong("Item_id"));
		l.setClNumber(rs.getString("cl_number"));
		return l;
	}
}
