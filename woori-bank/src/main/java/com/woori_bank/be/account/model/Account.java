package com.woori_bank.be.account.model;

import java.math.BigInteger;
import java.util.UUID;

public class Account {
	private long id;
	private String number;
	private AccountType type;
	private String createAt;
	private BigInteger balance;
}
