package com.bookpot.web.user.service;

import com.bookpot.web.user.entity.User;

public interface IUserService {
	
	// 회원 정보 가져오기
	public User getUser(User userVo);
	
	// 회원 정보 수정
	public boolean updateUser(User userVo);

}
