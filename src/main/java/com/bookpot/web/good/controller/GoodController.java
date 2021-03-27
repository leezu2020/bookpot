package com.bookpot.web.good.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookpot.web.good.entity.Good;
import com.bookpot.web.good.service.IGoodService;
import com.bookpot.web.security.SecurityUser;

@RestController
@RequestMapping("/writing/")
public class GoodController {
	
	@Autowired
	private IGoodService goodService;

	// (userNo, writingNo)을 받아서 db 체크 후 -> 있으면 -1 없으면 +1 ?
	// 프론트에서 좋아요 여부가 체크된 상태라면 -> get / post / delete 나눠서 처리
	@PostMapping("good/{writingNo}")
	public ResponseEntity<HashMap<String, String>> goodUP(@PathVariable long writingNo){

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		
		Good good = new Good();
		good.setUserNo(user.getNo());
		good.setWritingNo(writingNo);
		
		System.out.println("좋아요 아이디 : " + good.getUserNo());
		System.out.println("좋아요 번호 : " + good.getWritingNo());
		
		HashMap<String, String> map = new HashMap<String, String>();
		// 좋아요 누른적이 없다면
		if(!goodService.isExistGood(good)) {
			// 정상적으로 좋아요가 됐다면
			if(goodService.goodUp(good)) {
				map.put("message", "successGoodUp");
				return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.OK);
			} else {
				// 좋아요가 이루어지지 않았을 때
				map.put("message", "failGoodUp");
				return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.SERVICE_UNAVAILABLE);
			}
		} else {
			// 이미 좋아요를 누른 상태
			map.put("message", "alreadyGoodUp");
			return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("good/{writingNo}")
	public ResponseEntity<HashMap<String, String>> goodDown(@PathVariable long writingNo){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		SecurityUser user = (SecurityUser) authentication.getPrincipal();
		
		Good good = new Good();
		good.setUserNo(user.getNo());
		good.setWritingNo(writingNo);		

		HashMap<String, String> map = new HashMap<String, String>();
		
		// 좋아요를 누른 상태라면
		if(goodService.isExistGood(good)) {
			// 좋아요 취소가 됐다면
			if(goodService.goodDown(good)) {
				map.put("message", "successGoodDown");
				return new ResponseEntity<HashMap<String,String>>(map, HttpStatus.OK);
			} else {
				// 좋아요 취소가 이루어지지 않았을 때
				map.put("message", "failGoodDown");
				return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.SERVICE_UNAVAILABLE);
				
			}
		} else {
			// 좋아요가 눌린 상태가 아닐 때
			map.put("message", "alreadyGoodDown");
			return new ResponseEntity<HashMap<String, String>>(map, HttpStatus.BAD_REQUEST);
		}
		
	}
}
