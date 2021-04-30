package com.bookpot.web.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookpot.web.security.SecurityUser;
import com.bookpot.web.user.entity.User;
import com.bookpot.web.user.service.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;	
	
	@Autowired
	private IUserService userService;
	
	// 정보 조회
	// 결과값 수정
	@GetMapping("")
	public String userInfo() {
		
		System.out.println("유저 정보 조회");
		
		return "user/Mypage";
	}
	
	// 정보 수정
	@PutMapping("")
	@ResponseBody
	public ResponseEntity<String> modUserInfo(User info){
		// 로그인 유저 정보
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		
		// 닉네임 중복확인하기
		// 비밀번호 유효성 체크하기
		
		// 로그인 정보 확인용 이메일
		info.setEmail(user.getName());
		// 비밀번호 암호화
		info.setPassword(passwordEncoder.encode(info.getPassword()));
		
		if(userService.updateUser(info)) {
			// 로그인 정보 갱신 추가하기
			
			System.out.println("정보 수정 성공");
		}
		else
			System.out.println("수정 실패");
		return new ResponseEntity<String>("check", HttpStatus.OK);
	}
	
	// 정보 삭제
	@DeleteMapping("")
	@ResponseBody
	public ResponseEntity<String> delUserInfo(){
		// 로그인 유저 정보
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		
		// 정보 삭제
		if(userService.deleteUser(user.getUsername()))
			return new ResponseEntity<String>("delete", HttpStatus.OK);
		else
			return new ResponseEntity<String>("failToDelete", HttpStatus.SERVICE_UNAVAILABLE);
		
		// 강제 로그아웃 시키기
	}
	
	// 비밀번호 재확인
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
