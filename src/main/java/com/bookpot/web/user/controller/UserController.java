package com.bookpot.web.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookpot.web.security.SecurityUser;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;	
	
	// 정보 조회
	// 결과값 수정
	@GetMapping("/{userNo}")
	public String userInfo(@PathVariable long userNo) {
		
		System.out.println("유저 정보 조회");
		
		return "user/Mypage";
	}
	
	@PostMapping("/checkPwd")
	@ResponseBody
	public ResponseEntity<String> checkPwd(@RequestParam(name = "pwd") String pwd) {
		
		// 로그인 정보 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		
		// 입력한 비밀번호와 인코딩된 비밀번호 비교
		if(passwordEncoder.matches(pwd, user.getPassword()))
			return new ResponseEntity<String>("match", HttpStatus.OK);
		else
			return new ResponseEntity<String>("notmatch", HttpStatus.BAD_REQUEST);
	}
}
