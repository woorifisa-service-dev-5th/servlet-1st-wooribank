package com.woori_bank.be.account.model;

import java.math.BigInteger;
import java.time.OffsetDateTime;

public class Account {
	private long id;
	private String number;
	private AccountType type;
	private OffsetDateTime createAt;
	private BigInteger balance;
	private long clientId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public OffsetDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(OffsetDateTime createAt) {
		this.createAt = createAt;
	}
	public BigInteger getBalance() {
		return balance;
	}
	public void setBalance(BigInteger balance) {
		this.balance = balance;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	
	
}
