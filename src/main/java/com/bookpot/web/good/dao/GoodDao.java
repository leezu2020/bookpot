package com.bookpot.web.good.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookpot.web.good.entity.Good;

@Repository
public class GoodDao {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "goodMapper";

	public boolean insert(Good good) {
		int count = sqlSession.insert(namespace + ".insert", good);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delete(Good good) {
		int count = sqlSession.delete(namespace + ".delete", good);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean get(Good good) {
		Integer count = sqlSession.selectOne(namespace + ".get", good);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

}
