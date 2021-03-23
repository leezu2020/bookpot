package com.bookpot.web.join.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bookpot.web.application.BeanUtils;
import com.bookpot.web.join.dto.JoinDto;
import com.bookpot.web.join.service.IJoinService;
import com.bookpot.web.user.entity.User;

public class JoinValidator implements Validator {

	// 비밀번호 패턴 (영어대소문자 숫자 아무거나 8~16자리) -> 영문 숫자 혼합으로 수정필요
	private static final String pwRegExp = "(?=.*\\d)(?=.*[a-zA-Z]).{8,16}";


	private IJoinService joinService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("signUp 유효성 검사 시작");
		JoinDto joinDto = (JoinDto) target;

		joinService = (IJoinService) BeanUtils.getBean("joinService");

		// 닉네임이 비어있을때
		if (joinDto.getNickname() == null || joinDto.getNickname().trim().isEmpty()) {
			errors.reject("emptynickname");
		} else if (joinService.existNickname(joinDto.getNickname())) { // 닉네임 중복일때
			errors.reject("existnickname");
		}

		// 이메일 인증번호 기능 구현으로 사실상 필요 없음
		// 이메일 비어있을때
		if (joinDto.getEmail() == null || joinDto.getEmail().trim().isEmpty()) {
			// System.out.println("email : " + joinDto.getEmail());
			errors.reject("emptyemail");
		} else if (joinService.existEmail(joinDto.getEmail())) { // 이메일 중복일때
			errors.reject("existemail");
		}

		// 비밀번호가 비어있을때
		if (joinDto.getPassword() == null || joinDto.getPassword().trim().isEmpty()) {
			errors.reject("emptypassword");
		} else {
			// 비밀번호 정규식 확인 (영 소/대문자 + 숫자, 8~16자리)
			Matcher matcher = Pattern.compile(pwRegExp).matcher(joinDto.getPassword());
			// 비밀번호 패턴이 일치하지않을때
			if (!matcher.matches()) {
				errors.reject("wrongpassword");
			}

			// 비밀번호 입력시 비밀번호 확인
			if (!joinDto.passwordEqual()) {
				errors.reject("notmatchpassword");
			}
		}
		System.out.println("signUp 유효성 검사 끝");
	}

}
