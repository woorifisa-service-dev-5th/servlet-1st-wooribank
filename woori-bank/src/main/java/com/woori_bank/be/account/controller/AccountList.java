package com.woori_bank.be.account.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.woori_bank.be.account.dao.AccountDAO;
import com.woori_bank.be.account.model.Account;
import com.woori_bank.be.user.model.User;
import com.woori_bank.be.util.AuthUtil;
import com.woori_bank.be.util.validator.LoginValidator;

@WebServlet("/accounts")
public class AccountList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 예시: 특정 고객 ID의 계좌 목록 조회
    	
        if(LoginValidator.isLogin(req)) {
        	User user = AuthUtil.getUser(req);
        	
        	List<Account> accounts = AccountDAO.findByClientId(user.getId());
            
            req.setAttribute("accountList", accounts);
            req.getRequestDispatcher("/WEB-INF/views/accountList.jsp")
               .forward(req, resp);
        }
        
    }
}
