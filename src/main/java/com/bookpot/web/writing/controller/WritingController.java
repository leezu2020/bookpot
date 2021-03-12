package com.bookpot.web.writing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/writing/")
public class WritingController {

	// REST에 따른 수정 -> db를 어떻게 구성할지?
	@GetMapping("{no}")
	public String detail(@PathVariable int no) {
		return "writing/detail";
	}
	
	@GetMapping("reg")
	public String reg() {
		return "writing/reg";
	}
}
