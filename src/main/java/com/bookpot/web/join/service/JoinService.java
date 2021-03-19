package com.bookpot.web.join.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookpot.web.security.SecurityUser;
import com.bookpot.web.user.dao.UserDao;
import com.bookpot.web.user.entity.User;

@Service
public class JoinService implements UserDetailsService, IJoinService{

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
	
	public boolean regUser(User userVo) {
		userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
		return userDao.insert(userVo);
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
