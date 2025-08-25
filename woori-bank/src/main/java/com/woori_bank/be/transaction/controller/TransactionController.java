package com.woori_bank.be.transaction.controller;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.woori_bank.be.transaction.service.TransactionService;
import com.woori_bank.be.util.validator.LoginValidator;

@WebServlet("/transaction")
public class TransactionController extends HttpServlet {

	private final TransactionService service = new TransactionService();
	private final Logger log = LoggerFactory.getLogger("trx-controller");

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (LoginValidator.isLogin(req)) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/transaction.jsp");
			rd.forward(req, resp);
		}

		else {
			req.setAttribute("errorMsg", "로그인이 필요합니다.");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (LoginValidator.isLogin(req)) {
			String from = req.getParameter("id");
			String to = req.getParameter("password");
			BigInteger amount = new BigInteger(req.getParameter("amount"));
			String description = req.getParameter("description");

			log.info("{} {} {} {}", from, to, amount, description);

			service.deposit(from, to, amount, description);

			req.setAttribute("successMsg", "이체를 완료하였습니다.");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/transaction.jsp");
			rd.forward(req, resp);
		} else {
			req.setAttribute("errorMsg", "로그인이 필요합니다.");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

}
