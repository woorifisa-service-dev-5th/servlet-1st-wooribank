package com.woori_bank.be.account.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.woori_bank.be.account.model.AccountType;
import com.woori_bank.be.account.service.AccountService;
import com.woori_bank.be.user.model.User;

@WebServlet("/account/create")
public class accountCreate extends HttpServlet {
	private static Map<AccountType, String> routes = Map.of(
		    AccountType.LOAN, "/loan/join",
		    AccountType.SAVING, "/deposit/join"
		);
	
	
	private static AccountService accountService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		String typeParam = req.getParameter("type");
		AccountType type;
		try {
	        type = AccountType.valueOf(typeParam.toUpperCase());
	    } catch (Exception e) {
	        // 잘못된 접근 방어
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid account type");
	        return;
	    }
	    req.setAttribute("type", type);
	    req.getRequestDispatcher("/WEB-INF/views/accountCreate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		String typeParam = req.getParameter("type");
        String agree = req.getParameter("agree");
        if (!"Y".equals(agree)) {
            // 동의 안하면 다시 폼으로
            req.setAttribute("type", typeParam);
            req.setAttribute("errorMsg", "약관에 동의해야 계좌를 생성할 수 있습니다.");
            req.getRequestDispatcher("/WEB-INF/views/accountCreate.jsp").forward(req, resp);
            return;
        }
		
        AccountType type = AccountType.valueOf(typeParam.toUpperCase());
        
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("user") == null) {
        	resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        
        User loginUser = (User) session.getAttribute("user");
        long clientId = loginUser.getId();
        
        long accountId = AccountService.createAccount(clientId, type);
        
        String base = routes.getOrDefault(type, "/main");
        String q = (type == AccountType.CHECKING) ? ("") : ("?accountId=" + accountId);
        resp.sendRedirect(req.getContextPath() + base + q);
	}
	
}
