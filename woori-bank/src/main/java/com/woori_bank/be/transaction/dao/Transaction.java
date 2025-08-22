package com.woori_bank.be.transaction.dao;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Transaction {

	private Long id;
	private String accountId, type, description;
	private BigInteger amount;
	private LocalDateTime date;

	private Transaction(Long id, String accountId, String type, String description, BigInteger amount,
			LocalDateTime date) {
		this.id = id;
		this.accountId = accountId;
		this.type = type;
		this.description = description;
		this.amount = amount;
		this.date = date;
	}

	private Transaction(String accountId, String type, String description, BigInteger amount, LocalDateTime date) {
		this.accountId = accountId;
		this.type = type;
		this.description = description;
		this.amount = amount;
		this.date = date;
	}

	public static Transaction create(String accountId, String type, String description, BigInteger amount,
			LocalDateTime date) {
		return new Transaction(accountId, type, description, amount, date);
	}

	public static Transaction load(Long id, String accountId, String type, String description, BigInteger amount,
			LocalDateTime date) {
		return new Transaction(id, accountId, type, description, amount, date);
	}

}
