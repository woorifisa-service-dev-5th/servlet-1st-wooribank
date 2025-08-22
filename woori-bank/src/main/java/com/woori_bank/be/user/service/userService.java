package com.woori_bank.be.user.service;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.woori_bank.be.user.controller.userLogin;
import com.woori_bank.be.user.dao.userDAO;
import com.woori_bank.be.user.model.User;
import com.woori_bank.be.user.util.PasswordUtil;

public class userService {
	
	Logger log = LoggerFactory.getLogger(userService.class);
	
	private static final userDAO userDAO = new userDAO();
	
	// 회원가입 로직
	public boolean signupService(User user){
		// id 중복 확인
		if(!validateDuplicateId(user.getUserId())) {
			return false;
		}
		
		String hashedPw = PasswordUtil.hash(user.getUserPw());
		user.setUserPw(hashedPw);
		
		// DB 저장
		userDAO.saveUser(user);
		
		return true;
	}
	
	// id 중복 확인
	public boolean validateDuplicateId(String id) {
		if(userDAO.findDuplicateId(id)) {
			return false;
		}
		return true;
	}
	
	
	// 로그인 로직
	public User loginService(String id, String pw) {
		log.info("userLogin: LoginService 입장");
		
		User dbUser = userDAO.findById(id);
		
		if(dbUser != null && PasswordUtil.matches(pw, dbUser.getUserPw())){
			return dbUser;
		} else {
			return null;
		}
	}
}
