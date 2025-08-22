package com.woori_bank.be.model;

import java.time.LocalDateTime;

public class Loan {
	private String loanId; // loan_id (PK)
	private long loanAmount; // loan_amount
	private String loanStatus; // loan_status: ONGOING/PAID/DEFAULT
	private LocalDateTime loanDate; // loan_date
	private Boolean guaranteeValid; // guarantee_valid
	private String accountId; // account_id
	private long itemId; // Item_id (상품ID, 지금은 0 사용)
	private String clNumber; // cl_number (13자, 선택)

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public LocalDateTime getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDateTime loanDate) {
		this.loanDate = loanDate;
	}

	public Boolean getGuaranteeValid() {
		return guaranteeValid;
	}

	public void setGuaranteeValid(Boolean guaranteeValid) {
		this.guaranteeValid = guaranteeValid;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getClNumber() {
		return clNumber;
	}

	public void setClNumber(String clNumber) {
		this.clNumber = clNumber;
	}
}