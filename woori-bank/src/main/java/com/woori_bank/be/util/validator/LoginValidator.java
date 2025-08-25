package com.woori_bank.be.util.validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.woori_bank.be.user.model.User;

public class LoginValidator {
	
	public static boolean isLogin(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		
		Object attribute = session.getAttribute("user");
		
		if(attribute instanceof User) {
			return true;
		}
		
		return false;
	}

}
