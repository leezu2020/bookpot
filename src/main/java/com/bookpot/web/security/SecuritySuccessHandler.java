package com.bookpot.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class SecuritySuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		/*
		 * String url = "/"; // 로그인 성공 if(authentication.isAuthenticated()) { url =
		 * "/user/signupCompleted"; } else { url = "user/login"; }
		 * 
		 * response.sendRedirect(url);
		 */
		
	}

}
