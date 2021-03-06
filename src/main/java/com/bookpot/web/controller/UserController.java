package com.bookpot.web.controller;


import java.util.List;

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

import com.bookpot.web.Service.UserService;
import com.bookpot.web.entity.UserVo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("signUp")
	public String signUp(@ModelAttribute UserVo userVo) {
		
		return "user/signup";
	}
	
	@PostMapping("signUp")
	public String signUp(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		System.out.println("signUp 실행");
		
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				System.out.println("에러발생 : " + error);
			}
			model.addAttribute(result.getModel());
			return "user/signup";
		}
		userService.regUser(userVo);
		return "/";
	}
	
	@ResponseBody
	@PostMapping("checkNickname")
	public String checkNickname(String nickname) {
		System.out.println("닉네임 중복확인 시작");
		
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
	
	@ResponseBody
	@PostMapping("checkUserID")
	public String checkUserID(String userID) {
		System.out.println("아이디 중복체크");
		if(userService.existUserID(userID)) {
			return "exist";
		} else {
			return "possible";
		}
	}
}
