package com.woori_bank.be.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.woori_bank.be.user.model.User;
import com.woori_bank.be.user.service.userService;

@WebServlet("/user/login")
public class userLogin extends HttpServlet {
	
	private static final userService userService = new userService();
	
	Logger log = LoggerFactory.getLogger(userLogin.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		log.info("userLogin: 입장");
		
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		User user = userService.loginService(id, password);
		
		if(user != null) {
			log.info("userLogin: user: {} 로그인 성공", user.getUserId());
			
			HttpSession session = req.getSession();
			
			if (session.getAttribute("user") != null) {
				System.out.println("userLogin: userSession 불러오기");
				
	            // 세션에 저장된 user 꺼내기
	            User loginUser = (User) session.getAttribute("user");

	            // JSP에서 사용하도록 request에 담기
	            req.setAttribute("user", loginUser);

	            // main.jsp로 forward
	            RequestDispatcher rd = req.getRequestDispatcher("/main.jsp");
	            rd.forward(req, resp);
	            return;
	        }
			System.out.println("userLogin: userSession 생성");
			
			// 새로 로그인 한 경우 세션에 user 저장
			session.setAttribute("user", user);
			
			 // 로그인 후 메인 페이지 이동
			resp.sendRedirect("main.jsp");
			
		} else {
			log.info("userLogin: user: {} 로그인 실패", user.getUserId());
			 // 로그인 실패 처리
	        req.setAttribute("errorMsg", "아이디 또는 비밀번호가 잘못되었습니다.");
	        RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
	        rd.forward(req, resp);
		}

		
	}
	
}
