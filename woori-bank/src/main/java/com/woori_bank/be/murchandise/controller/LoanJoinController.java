package com.woori_bank.be.murchandise.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori_bank.be.model.Loan;
import com.woori_bank.be.murchandise.dao.LoanDao;

@WebServlet("/loan/join")
public class LoanJoinController extends HttpServlet {
	private final LoanDao loanDao = new LoanDao();

	// GET: 가입 폼
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String accountId = req.getParameter("accountId");
		if (accountId == null || accountId.isBlank()) {
			String next = req.getContextPath() + "/loan/join";
			resp.sendRedirect(req.getContextPath() + "/account/create?type=LOAN&next="
					+ java.net.URLEncoder.encode(next, java.nio.charset.StandardCharsets.UTF_8));
			return;
		}
		req.setAttribute("accountId", accountId);
		req.getRequestDispatcher("/WEB-INF/views/loanJoin.jsp").forward(req, resp);
	}

	// POST: 가입 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String accountId = req.getParameter("accountId");
		long amount = parseLong(req.getParameter("loanAmount"));
		String clNumber = nv(req.getParameter("clNumber"), null);
		boolean guarantee = "on".equalsIgnoreCase(req.getParameter("guaranteeValid")); // 체크박스

		if (accountId == null || accountId.isBlank()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "accountId가 필요합니다.");
			return;
		}

		Loan l = new Loan();
		l.setLoanId(UUID.randomUUID().toString());
		l.setLoanAmount(amount);
		l.setLoanStatus("ONGOING"); // 초기 상태
		l.setLoanDate(LocalDateTime.now()); // 신청 시각
		l.setGuaranteeValid(guarantee); // 보증 유효 여부
		l.setAccountId(accountId);
		l.setItemId(0L); // 상품ID 미사용이면 0
		l.setClNumber(clNumber);

		try {
			loanDao.save(l);
		} catch (SQLException e) {
			throw new ServletException("대출 신청 저장 실패", e);
		}

		req.setAttribute("loan", l);
		req.getRequestDispatcher("/WEB-INF/views/loanJoinSuccess.jsp").forward(req, resp);
	}

	private long parseLong(String s) {
		try {
			return Long.parseLong(s);
		} catch (Exception e) {
			return 0L;
		}
	}

	private String nv(String s, String d) {
		return (s == null || s.isBlank()) ? d : s;
	}
}
