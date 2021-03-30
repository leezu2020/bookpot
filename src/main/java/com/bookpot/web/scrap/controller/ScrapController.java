package com.bookpot.web.scrap.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookpot.web.scrap.entity.Scrap;
import com.bookpot.web.scrap.service.IScrapService;
import com.bookpot.web.security.SecurityUser;

@Controller
@RequestMapping("/writing/")
public class ScrapController {

	@Autowired
	private IScrapService scrapService;
	
	@PostMapping("{writingNo}/scrap")
	public ResponseEntity<HashMap<String, String>> addScrap(@PathVariable long writingNo){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		
		Scrap scrap = new Scrap();
		scrap.setUserNo(user.getNo());
		scrap.setWritingNo(writingNo);
		
		HashMap<String, String> map = new HashMap<String, String>();
		// 스크랩 한적이 없다면
		if(!scrapService.isExistScrap(scrap)) {
			// 정상적으로 스크랩 추가
			if(scrapService.addScrap(scrap)) {
				map.put("message", "successAddScrap");
				return new ResponseEntity<HashMap<String,String>>(map, HttpStatus.OK);
			} else {
				// 스크랩 추가가 이루어지지 않았을때
				map.put("message", "failAddScrap");
				return new ResponseEntity<HashMap<String,String>>(map, HttpStatus.SERVICE_UNAVAILABLE);
			}	
		} else {
			// 이미 스크랩한 글일때
			map.put("message", "alreadyAddScrap");
			return new ResponseEntity<HashMap<String,String>>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("{writingNo}/scrap")
	public ResponseEntity<HashMap<String, String>> delScrap(@PathVariable long writingNo){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		
		Scrap scrap = new Scrap();
		scrap.setUserNo(user.getNo());
		scrap.setWritingNo(writingNo);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		// 스크랩을 한 상태라면
		if(scrapService.isExistScrap(scrap)) {
			// 스크랩 취소가 됐다면
			if(scrapService.delScrap(scrap)) {
				map.put("message", "successDelScrap");
				return new ResponseEntity<HashMap<String,String>>(map, HttpStatus.OK);
			} else {
				// 스크랩 취소가 이루어지지 않았을때
				map.put("message", "failDelScrap");
				return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.SERVICE_UNAVAILABLE);
				
			}
		} else {
			// 이미 스크랩 취소한 글일때
			map.put("message", "alreadyDelScrap");
			return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.BAD_REQUEST);
		}		
	}
}
