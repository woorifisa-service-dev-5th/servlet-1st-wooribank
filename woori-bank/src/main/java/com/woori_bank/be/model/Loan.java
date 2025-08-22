package com.woori_bank.be.model;

import java.time.LocalDateTime;

public class Loan {
	private String loanId; // loan_id (PK)
	private Long loanAmount; // loan_amount (nullable → Long)
	private String loanStatus; // loan_status: ONGOING / PAID / DEFAULT
	private LocalDateTime loanDate; // loan_date
	private Boolean guaranteeValid; // guarantee_valid
	private String accountId; // account_id
	private Long itemId; // item_id (NOT NULL)
	private String clNumber; // cl_number (최대 13자리)

	// --- Getter / Setter ---
	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getClNumber() {
		return clNumber;
	}

	public void setClNumber(String clNumber) {
		this.clNumber = clNumber;
	}
}
