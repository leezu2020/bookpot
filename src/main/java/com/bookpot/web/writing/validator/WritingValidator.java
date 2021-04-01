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
		
		WritingDto writing = (WritingDto) target;
		
		if(writing.getTitle() == null || writing.getTitle().trim().isEmpty()) {
			errors.rejectValue("title", "required", "emptyTitle");
		}
		
		if(writing.getContent() == null || writing.getContent().trim().isEmpty()) {
			errors.rejectValue("content", "required", "emptyContent");
		}
		
		if(writing.getBooktitle() == null || writing.getBooktitle().trim().isEmpty()) {
			errors.rejectValue("booktitle", "required", "emptyBooktitle");
		}
		
		if(writing.getDivision() == null || writing.getDivision().trim().isEmpty()) {
			errors.rejectValue("division", "required", "emptyDivision");
		}
	}

}
