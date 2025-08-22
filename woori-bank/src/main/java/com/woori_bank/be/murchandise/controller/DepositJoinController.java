package com.woori_bank.be.murchandise.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori_bank.be.model.Deposit;
import com.woori_bank.be.murchandise.dao.DepositDao;

@WebServlet("/deposit/join")
public class DepositJoinController extends HttpServlet {
	private final DepositDao depositDao = new DepositDao();

	// GET: 가입 폼
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String accountId = req.getParameter("accountId");
		if (accountId == null || accountId.isBlank()) {
			String next = req.getContextPath() + "/deposit/join";
			resp.sendRedirect(req.getContextPath() + "/account/create?type=SAVING&next="
					+ java.net.URLEncoder.encode(next, java.nio.charset.StandardCharsets.UTF_8));
			return;
		}
		req.setAttribute("accountId", accountId);
		req.getRequestDispatcher("/WEB-INF/views/depositJoin.jsp").forward(req, resp);
	}

	// POST: 가입 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String accountId = req.getParameter("accountId");
		long amount = 0L;
		try {
			amount = Long.parseLong(req.getParameter("amount"));
		} catch (Exception ignored) {
		}

		String maturity = req.getParameter("maturityDate"); // yyyy-MM-dd
		LocalDateTime maturityDate = (maturity == null || maturity.isBlank()) ? null
				: LocalDate.parse(maturity).atStartOfDay();

		Deposit d = new Deposit();
		d.setKey(UUID.randomUUID().toString()); // PK
		d.setItemId(0L); // 상품ID 미사용
		d.setAccountId(accountId);
		d.setAmount(amount);
		d.setMaturityDate(maturityDate);
		d.setValidDate(false); // 가입 시 기본 false
		System.out.println(d.getKey());
		try {
			depositDao.save(d);
		} catch (SQLException e) {
			throw new ServletException("예/적금 가입 저장 실패", e);
		}

		req.setAttribute("deposit", d);
		req.getRequestDispatcher("/WEB-INF/views/depositJoinSuccess.jsp").forward(req, resp);
	}
}