package com.woori_bank.be.transaction.service;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.woori_bank.be.account.dao.AccountDAO;
import com.woori_bank.be.account.model.Account;
import com.woori_bank.be.transaction.dao.Transaction;
import com.woori_bank.be.transaction.dao.TransactionCommandDAO;
import com.woori_bank.be.util.init.ConnectionPool;

public class TransactionService {
	
	public TransactionService() {}
	
	 public void deposit(String from, String to, BigInteger amount, String description) {
	        try (Connection conn = ConnectionPool.getConnection()) {
	            try {
	                conn.setAutoCommit(false);

	                // 1. 계좌 조회
	                Account fromAccount = AccountDAO.findByAccountNumber(from);
	                Account toAccount   = AccountDAO.findByAccountNumber(to);

	                if (fromAccount == null || toAccount == null) {
	                    throw new IllegalArgumentException("계좌를 찾을 수 없습니다.");
	                }

	                // 2. 잔액 검증
	                if (fromAccount.getBalance().compareTo(amount) < 0) {
	                    throw new IllegalArgumentException("잔액 부족");
	                }

	                // 3. 잔액 차감 & 증가
	                fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
	                toAccount.setBalance(toAccount.getBalance().add(amount));

	                AccountDAO.updateBalance(conn, fromAccount);
	                AccountDAO.updateBalance(conn, toAccount);

	                // 4. 거래내역 저장
	                Transaction debitTx = Transaction.create(
	                        from, "DEBIT", description, amount, LocalDateTime.now());
	                Transaction creditTx = Transaction.create(
	                        to, "CREDIT", description, amount, LocalDateTime.now());

	                TransactionCommandDAO.save(conn, debitTx);
	                TransactionCommandDAO.save(conn, creditTx);

	                // 5. 커밋
	                conn.commit();
	            } catch (Exception e) {
	                conn.rollback();
	                throw new RuntimeException("송금 실패", e);
	            } finally {
	                conn.setAutoCommit(true);
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("DB 연결 실패", e);
	        }
	    }

}
