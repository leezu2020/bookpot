package com.bookpot.web.mail.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("checkEmail")
	@ResponseBody
	public String checkEmail(String email) {
		System.out.println("email : " + email);
		
		Random random = new Random();
		int num = random.nextInt(888888) + 111111;
		
		String setFrom = "bookpot@bookpot.com";
		String toMail = email;
		String title = "회원가입 인증 이메일 입니다.";
		String content = "인증 번호는 " + num + "입니다.";
		
		
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Integer.toString(num);
	}
}
