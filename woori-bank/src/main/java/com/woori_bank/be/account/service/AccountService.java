package com.woori_bank.be.account.service;

import java.math.BigInteger;
import java.time.OffsetDateTime;

import com.woori_bank.be.account.dao.AccountDAO;
import com.woori_bank.be.account.model.Account;
import com.woori_bank.be.account.model.AccountType;
import com.woori_bank.be.account.util.AccountNumberGenerator;

public class AccountService {	
	private static AccountDAO acountDAO;
	
	private static AccountNumberGenerator accountNumberGenerator;
	
	public static long createAccount(long cliendId, AccountType type) {
		
		// 계좌 번호 생성 로직 -> 
		String accountNumber = accountNumberGenerator.generate(type);
		
		// account 객체 생성
		Account account = new Account();
		account.setNumber(accountNumber);
		account.setType(type);
		account.setCreateAt(OffsetDateTime.now());
		account.setBalance(new BigInteger("0"));
		account.setClientId(cliendId);
		
		
		// 계좌 데이터 초기화 및 DB 저장
		long accountId = acountDAO.save(account);
		
		return accountId;
	}
}
