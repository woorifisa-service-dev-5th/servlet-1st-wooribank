package com.woori_bank.be.user.model;

import java.sql.Date;

public class User {
	private long id;
	private String userId; // 아이디
	private String userPw; // 비밀번호
	private String name; // 이름
	private String ssn; // 주민등록번호
	private String phone; // 전화번호
	private String brithday; // 생년월일
	private String address; // 주소
	private String email; // 이메일
	private String job; // 직업
	

	
	public User(String userId, String userPw, String name, String ssn, String phone, String brithday, String address,
			String email) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.ssn = ssn;
		this.phone = phone;
		this.brithday = brithday;
		this.address = address;
		this.email = email;
	}
	
	public User(long id, String userId, String userPw) {
		this.id = id;
		this.userId = userId;
		this.userPw = userPw;
	}
	
	public String getName() {
		return name;
	}

	public String getSsn() {
		return ssn;
	}

	public String getPhone() {
		return phone;
	}

	public String getBrithday() {
		return brithday;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
}
