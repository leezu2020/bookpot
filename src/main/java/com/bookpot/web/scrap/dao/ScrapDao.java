package com.bookpot.web.scrap.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookpot.web.scrap.entity.Scrap;

@Repository
public class ScrapDao {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "ScrapMapper";

	public boolean insert(Scrap scrap) {
		int count = sqlSession.insert(namespace + ".insert", scrap);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean delete(Scrap scrap) {
		int count = sqlSession.delete(namespace + ".delete", scrap);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean get(Scrap scrap) {
		Integer count = sqlSession.selectOne(namespace + ".get", scrap);
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}

}
