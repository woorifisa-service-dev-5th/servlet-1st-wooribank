package com.woori_bank.be.murchandise.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.util.*;
import com.woori_bank.be.model.Product;
import com.woori_bank.be.model.ProductType;

@WebServlet("/products/deposit")
public class DepositPageController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/WEB-INF/views/deposit.jsp").forward(req, resp);
	}
}
