package com.bookpot.web.category.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookpot.web.category.entity.Category;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "categoryMapper";

	public Category getByName(String name) {
		return sqlSession.selectOne(namespace + ".getByName", name);
	}

	public Boolean insertCateRel(HashMap<String, Object> map) {
		int count = sqlSession.insert(namespace + ".insertCateRel", map);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
