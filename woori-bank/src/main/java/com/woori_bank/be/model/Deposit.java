package com.woori_bank.be.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deposit {
	private String key; // PK (예적금 ID)
	private long itemId; // 상품 ID (무시 가능)
	private String accountId; // 계좌 ID
	private long amount; // 금액
	private LocalDateTime maturityDate; // Field2 만기일
	private boolean validDate; // 만기 여부

	public Deposit() {
	}

	public Deposit(String key, long itemId, String accountId, long amount, LocalDateTime maturityDate,
			boolean validDate) {
		this.key = key;
		this.itemId = itemId;
		this.accountId = accountId;
		this.amount = amount;
		this.maturityDate = maturityDate;
		this.validDate = validDate;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public LocalDateTime getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(LocalDateTime maturityDate) {
		this.maturityDate = maturityDate;
	}

	public boolean isValidDate() {
		return validDate;
	}

	public void setValidDate(boolean validDate) {
		this.validDate = validDate;
	}
}