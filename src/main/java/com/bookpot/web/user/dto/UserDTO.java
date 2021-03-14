package com.bookpot.web.user.dto;

// 회원가입 유효성 검사를 위한 임시 entity
public class UserDTO {
	
	private String nickname;
	private String email;
	private String password;
	private String passwordCheck;

	// 패스워드 확인
	public boolean passwordEqual() {
		return passwordCheck.equals(password);
	}
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	
}
