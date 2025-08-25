package com.woori_bank.be.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.woori_bank.be.user.model.User;

public class AuthUtil {
	
	public static User getUser(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		
		if(session==null) {
			return null;
		}
		
		Object obj = session.getAttribute("user");
		
		if(obj instanceof User ) {
			return (User) obj;
		}
		
		return null;
	}

}
