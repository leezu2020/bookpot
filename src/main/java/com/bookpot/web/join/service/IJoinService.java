package com.bookpot.web.join.service;

import com.bookpot.web.join.dto.JoinDto;

public interface IJoinService {
	
	// 닉네임 존재 여부 확인
	public boolean existNickname(String nickname);
	
	// 이메일 존재 여부 확인
	public boolean existEmail(String email);
	
	// 회원 등록
	public boolean regUser(JoinDto joinDto);
}
