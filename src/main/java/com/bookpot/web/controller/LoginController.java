package com.bookpot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/")
public class LoginController {
	
	@GetMapping("signUp")
	public String signUp() {
		
		return "signup";
	}
}
