package com.bookpot.web.join.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.bookpot.web.join.service.JoinService;
import com.bookpot.web.join.validator.JoinValidator;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/join")
public class JoinController {

	// 닉네임 정규식
	private static final String nickNameRegexp = "[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+";

	@Autowired
	private JoinService joinService;

	// 회원 가입 페이지 이동
	@GetMapping("/signup")
	public String signUp(@ModelAttribute JoinDto joinDto) {

		return "join/signup";
	}

	// 회원 등록
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<HashMap<String,String>> signUp(@ModelAttribute @Valid JoinDto joinDto, BindingResult result,
			Model model) {
		System.out.println("signup 실행");
		// 유효성 검사
		new JoinValidator().validate(joinDto, result);

		if (result.hasErrors()) {
			HashMap<String, String> errorlist = new HashMap<String, String>();
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				errorlist.put(error.getObjectName(),error.getCode());
				System.out.println("회원가입 오류 : " + error.getObjectName() + " " + error.getCode());
			}
			return new ResponseEntity<>(errorlist, HttpStatus.BAD_REQUEST);
		} else {
			HashMap<String, String> regResult = new HashMap<String, String>();
			if(joinService.regUser(joinDto)) {
				System.out.println("회원가입 성공");
				regResult.put("returnUrl", "/");
				return new ResponseEntity<HashMap<String,String>>(regResult, HttpStatus.OK);
			}
			else {
				regResult.put("error", "failToReg");
				return new ResponseEntity<HashMap<String,String>>(regResult, HttpStatus.SERVICE_UNAVAILABLE);
			}
		}
	}

	@GetMapping("")
	public String joinSuccess() {
		return "join/signupCompleted";
	}

	// 닉네임 조회
	@ResponseBody
	@GetMapping("/nickname/{nickname}")
	public String checkNickname(@PathVariable String nickname) {
		System.out.println("닉네임 중복확인 시작" + nickname);

		// 닉네임 조건 체크하기 _ 한글 영문대소문자
		Matcher matcher = Pattern.compile(nickNameRegexp).matcher(nickname);

		if (!matcher.matches()) {
			return "notmatch";
		} else {
			// 존재하면 fail
			// 생성가능하면 success
			if (joinService.existNickname(nickname)) {
				System.out.println("닉네임 중복");
				return "exist";
			} else {
				System.out.println("닉네임 생성가능");
				return "possible";
			}
		}
	}
}
