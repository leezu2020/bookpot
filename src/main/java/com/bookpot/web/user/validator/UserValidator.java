package com.bookpot.web.user.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bookpot.web.user.vo.UserRegVo;
import com.bookpot.web.user.vo.UserVo;

public class UserValidator implements Validator{

	// 비밀번호 패턴
	private static final String pwRegExp = "^[A-Za-z0-9+]{8,16}$"; 
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("signUp 유효성 검사 시작");
		UserRegVo userRegVo = (UserRegVo) target;
		
		// 닉네임이 비어있을때
		if(userRegVo.getNickname() == null || userRegVo.getNickname().trim().isEmpty()) {
			errors.rejectValue("nickname", "required", "닉네임을 입력해주세요.");
		}
		
		// 아이디가 비어있을때
		if(userRegVo.getUserID() == null || userRegVo.getUserID().trim().isEmpty()) {
			errors.rejectValue("userID", "required", "아이디를 입력해주세요.");
			
		} else {
			// 아이디 정규식 입력하기
			
			
		}
		
		// 비밀번호가 비어있을때
		if(userRegVo.getPassword() == null || userRegVo.getPassword().trim().isEmpty()) {
			errors.rejectValue("password", "required", "비밀번호를 입력해주세요.");
		} else {
			//비밀번호 정규식 확인 (영 소/대문자 + 숫자, 8~16자리)
			Matcher matcher = Pattern.compile(pwRegExp).matcher(userRegVo.getPassword());
			// 비밀번호 패턴이 일치하지않을때
			if(!matcher.matches()) {
				errors.rejectValue("password", "bad", "올바르지 않은 형식입니다.");
			}
			
			// 비밀번호 입력시 비밀번호 확인
			if(!userRegVo.passwordEqual()) {
				errors.rejectValue("passwordCheck", "notmatch", "비밀번호가 일치하지 않습니다.");
			}
		}
		
		
		System.out.println("signUp 유효성 검사 끝");
	}

}