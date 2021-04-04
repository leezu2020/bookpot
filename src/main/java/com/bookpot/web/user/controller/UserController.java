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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookpot.web.join.dto.JoinDto;
import com.bookpot.web.join.service.IJoinService;
import com.bookpot.web.join.validator.JoinValidator;
import com.bookpot.web.user.entity.User;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
	


	
	// 정보 조회
	// 결과값 수정
	@GetMapping("/{userNo}")
	public String userInfo(@PathVariable long userNo) {
		
		System.out.println("유저 정보 조회");
		
		return "user/Mypage";
	}
}
