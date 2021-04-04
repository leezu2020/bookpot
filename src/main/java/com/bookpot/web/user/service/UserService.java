package com.bookpot.web.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookpot.web.user.dao.UserDao;
import com.bookpot.web.user.entity.User;

public class UserService implements IUserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public User getUser(User userVo) {
		return userDao.get(userVo);
	}
	
	@Override
	public boolean updateUser(User userVo) {
		return userDao.update(userVo);
	}
}
