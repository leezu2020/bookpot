package com.bookpot.web.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	// 중복 로그인시, alert후 메인
	@GetMapping("/login/duplication")
	public String duplicatedLogin() {
		return "login/duplication";
	}
	
	@RequestMapping("/login")
	public String loginpage(HttpServletRequest request, HttpServletResponse response) {
		
		return "index";
	}
}
