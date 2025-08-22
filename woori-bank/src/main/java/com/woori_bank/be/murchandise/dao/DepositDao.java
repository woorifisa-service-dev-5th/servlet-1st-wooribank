package com.woori_bank.be.murchandise.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.woori_bank.be.model.Deposit;
import com.woori_bank.be.util.init.ConnectionPool;

public class DepositDao {
	// INSERT
	public Deposit save(Deposit d) throws SQLException {
		final String sql = "INSERT INTO Deposit (deposit_key, Item_id, account_id, amount, maturityDate, valid) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, d.getKey()); // PK (예: UUID)
			ps.setLong(2, d.getItemId()); // 지금은 0L 써도 됨
			ps.setString(3, d.getAccountId());
			if (d.getAmount() == 0L)
				ps.setNull(4, Types.BIGINT);
			else
				ps.setLong(4, d.getAmount());

			if (d.getMaturityDate() != null)
				ps.setTimestamp(5, Timestamp.valueOf(d.getMaturityDate())); // DATETIME
			else
				ps.setNull(5, Types.TIMESTAMP);

			ps.setBoolean(6, d.isValidDate()); // TINYINT(1) ↔ boolean

			ps.executeUpdate();
		}
		return d;
	}

	// Find account_id
	public List<Deposit> findByAccountId(String accountId) throws SQLException {
		final String sql = "SELECT * FROM Deposit WHERE account_id = ?";
		List<Deposit> out = new ArrayList<>();

		try (Connection conn = ConnectionPool.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, accountId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next())
					out.add(map(rs));
			}
		}
		return out;
	}

	private Deposit map(ResultSet rs) throws SQLException {
		Deposit d = new Deposit();
		d.setKey(rs.getString("Key"));
		d.setItemId(rs.getLong("Item_id"));
		d.setAccountId(rs.getString("account_id"));
		d.setAmount(rs.getLong("amount"));
		Timestamp ts = rs.getTimestamp("Field2");
		if (ts != null)
			d.setMaturityDate(ts.toLocalDateTime());
		d.setValidDate(rs.getBoolean("valid_date"));
		return d;
	}
}