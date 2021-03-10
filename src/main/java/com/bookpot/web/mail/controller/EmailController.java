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

import com.bookpot.web.user.service.UserService;

@Controller
@RequestMapping("/")
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("checkEmail")
	@ResponseBody
	public String checkEmail(String email) {
		System.out.println("checkEmail 진입 email : " + email);
		
		// 이메일 중복 체크후 코드 전송 진행

		// 이메일이 중복되지 않을때
		if(!userService.existEmail(email)) {
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
				
				System.out.println("인증 메일 발송");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return Integer.toString(num);
			}
		else {
			// 이메일 중복될때 exist 전송
			return "exist";
		}
	}
}
