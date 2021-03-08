package com.bookpot.web.writing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/writing/")
public class WritingController {

	@GetMapping("detail")
	public String detail() {
		return "writing/detail";
	}
	
	@GetMapping("reg")
	public String reg() {
		return "writing/reg";
	}
}
