package com.bookpot.web.user.controller;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookpot.web.user.service.UserService;
import com.bookpot.web.user.validator.UserValidator;
import com.bookpot.web.user.vo.UserRegVo;
import com.bookpot.web.user.vo.UserVo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	// 닉네임 정규식
	private static final String nickNameRegexp = "[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+";
	
	@Autowired
	private UserService userService;
	
	@GetMapping("signup")
	public String signUp(@ModelAttribute UserRegVo userRegVo) {
		
		return "user/signup";
	}
	
	@PostMapping("signup")
	public String signUp(@ModelAttribute @Valid UserRegVo userRegVo, BindingResult result, Model model) {
		System.out.println("signup 실행");
		
		//유효성 검사
		new UserValidator().validate(userRegVo, result);
		
		// 오류 있을때
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				System.out.println("에러발생 : " + error);
			}
			return "user/signup";
		} else {
		// userRegVo -> userVo
			UserVo userVo = new UserVo();
			userVo.setNickname(userRegVo.getNickname());
			userVo.setPassword(userRegVo.getPassword());
			userVo.setEmail(userRegVo.getEmail());
			
			userService.regUser(userVo);
			System.out.println("signup 종료");
			return "user/signupCompleted";
		}
	}
	
	@ResponseBody
	@PostMapping("checkNickname")
	public String checkNickname(String nickname) {
		System.out.println("닉네임 중복확인 시작" + nickname);
		
		// 닉네임 조건 체크하기 _ 한글 영문대소문자
		Matcher matcher = Pattern.compile(nickNameRegexp).matcher(nickname);
		
		if(!matcher.matches()) {
			return "notmatch";
		} else {
			// 존재하면 fail
			// 생성가능하면 success
			if(userService.existNickname(nickname)) {
				System.out.println("닉네임 중복");
				return "exist";
			} else {
				System.out.println("닉네임 생성가능");
				return "possible";
			}
		}
	}	

}
