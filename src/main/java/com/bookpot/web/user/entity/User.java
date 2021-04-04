package com.bookpot.web.user.entity;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public class User {
	
	private Long no;
	private String nickname;
	private String email;
	private String password;
	private String role;
	private String regDate;
	private String imgUrl;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(@NotEmpty String email, @NotNull @Length(min = 4) String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public User(Long no, String nickname, String email, String password, String role, String regDate, String imgUrl) {
		super();
		this.no = no;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.regDate = regDate;
		this.imgUrl = imgUrl;
	}
	
}
