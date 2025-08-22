package com.woori_bank.be.user.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori_bank.be.user.model.User;
import com.woori_bank.be.user.service.userService;

@WebServlet("/user/signup")
public class userSignup extends HttpServlet {
	
	private static final userService userService = new userService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("password");
		String ssn = req.getParameter("ssn");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String birth = req.getParameter("birth");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String job = req.getParameter("job");
		
		
		User user = new User(id, pw, name, ssn, phone, birth, address, email);
		if(job != null) {
			user.setJob(job);
		}
		
		if(userService.signupService(user)) {
			// 회원가입 성공 -> Login 페이지로
			req.setAttribute("successMsg", "회원가입이 완료되었습니다. 로그인 해주세요.");
			RequestDispatcher rd = req.getRequestDispatcher("views/login.jsp");
            rd.forward(req, resp);
		} else {
			// 회원가입 실패 -> 다시 회원가입 페이지로
			req.setAttribute("errorMsg", "회원가입 실패. 다시 입력해주세요");
	        RequestDispatcher rd = req.getRequestDispatcher("views/signup.jsp");
	        rd.forward(req, resp);
		}
	}
	
}
