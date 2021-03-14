package com.bookpot.web.user.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookpot.web.security.SecurityUser;
import com.bookpot.web.user.dao.UserDao;
import com.bookpot.web.user.entity.User;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean existNickname(String nickname) {
		
		return (userDao.getByNickname(nickname) != null);
	}
	
	public boolean existEmail(String email) {
		return (userDao.getByEmail(email)) != null;
	}
	
	@Transactional
	public boolean regUser(User userVo) {
		userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
		return userDao.insert(userVo);
	}
	
	public User getUser(User userVo) {
		return userDao.get(userVo);
	}
	
	public boolean updateUser(User userVo) {
		return userDao.update(userVo);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.getByEmail(username);
		
		SecurityUser securityUser = new SecurityUser();
		
		if(user != null) {
			securityUser.setNo(user.getNo());
			securityUser.setName(user.getEmail());
			securityUser.setUsername(user.getNickname());
			securityUser.setPassword(user.getPassword());
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
			
		}
		return securityUser;
	}
}
