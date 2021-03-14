package com.bookpot.web.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookpot.web.user.entity.User;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "userMapper";
	
	public User getByNickname(String nickname) {
		return sqlSession.selectOne(namespace + ".getByNickname", nickname);
	}
	
	public User getByEmail(String email) {
		return sqlSession.selectOne(namespace + ".getByEmail", email);
	}
	
	public User get(User userVo) {
		return sqlSession.selectOne(namespace + ".getByUserIDAndPassword", userVo);
	}
	
	public Boolean insert(User userVo) {
		int count = sqlSession.insert(namespace + ".insert", userVo);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean update(User userVo) {
		int count = sqlSession.update(namespace + ".update", userVo);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
}
