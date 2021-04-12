package com.bookpot.web.security;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginFailureHandler implements AuthenticationFailureHandler{
	// 실패시 돌아갈 주소
//	private final String DEFAULT_FAILURE_URL = "/login?error=true";
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMsg = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		// 오류 메세지 
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			errorMsg = "test.";
		} else {
			errorMsg = "알 수 없는 이유로 로그인에 실패하였습니다.";
		}

		Map<String, Object> map = new HashMap<>();
		map.put("success", false);
		map.put("message", errorMsg);
		
		String json = mapper.writeValueAsString(map);
		
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(json.getBytes());
	}

}
