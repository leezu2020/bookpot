package com.bookpot.web.mail.controller;

import java.util.Random;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bookpot.web.join.service.JoinService;
import com.bookpot.web.mail.service.MailService;

@Controller
@EnableAsync
@RequestMapping("/")
public class EmailController {

	@Autowired
	private MailService mailService;
	
	@Autowired
	private JoinService joinService;
	
	@GetMapping("email/{email:.+}")
	@ResponseBody
	public String checkEmail(@PathVariable String email) {
		System.out.println("checkEmail 진입 email : " + email);
		
		// 이메일 중복 체크후 코드 전송 진행

		// 이메일이 중복되지 않을때
		if(!joinService.existEmail(email)) {
			Random random = new Random();
			int num = random.nextInt(888888) + 111111;
			
			String setFrom = "bookpot@bookpot.com";
			String toMail = email;
			String title = "회원가입 인증 이메일 입니다.";
			String content = "인증 번호는 " + num + "입니다.";
			
			
			try {
				mailService.sendMessage(setFrom, toMail, title, content);
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
	
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);	// 기본 스레드 수
		executor.setMaxPoolSize(5);		// 최대 스레드 수 
		executor.setQueueCapacity(50);	// Queue 사이즈
		executor.initialize();
		return executor;
	}
}
