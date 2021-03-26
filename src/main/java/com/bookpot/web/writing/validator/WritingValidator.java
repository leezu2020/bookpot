package com.bookpot.web.writing.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.bookpot.web.writing.dto.WritingDto;

public class WritingValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return WritingDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("writing 유효성 검사 시작");
		
	}

}
