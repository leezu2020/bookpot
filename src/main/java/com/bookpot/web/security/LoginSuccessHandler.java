package com.bookpot.web.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	private String defaultUrl = "/";


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		clearAuthenticationAttributes(request);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		map.put("returnUrl", getReturnUrl(request, response));
		
		// {"success" : true, "returnUrl" : url}
		String json = mapper.writeValueAsString(map);
		
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(json.getBytes());
	}
	
	// 로그인 전 url
	private Object getReturnUrl(HttpServletRequest request, HttpServletResponse response) {
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest == null) {
			// 메인 화면에서 로그인 했을시
			return defaultUrl;
		}
		return savedRequest.getRedirectUrl();
	}

	// 로그인 과정에서 세션에 저장된에러 제거
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
