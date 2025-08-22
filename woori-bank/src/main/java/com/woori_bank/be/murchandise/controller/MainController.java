package com.woori_bank.be.murchandise.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/")
public class MainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Object user = (session != null) ? session.getAttribute("SESSION_USER") : null;
		if (user == null && session != null) {
			user = session.getAttribute("user");
		}

		req.setAttribute("isLoggedIn", user != null);
		req.setAttribute("currentUser", user);

		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
	}

}
