package com.bookpot.web.category.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookpot.web.category.dao.CategoryDao;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	private CategoryDao cateDao;
	
	@Override
	public Boolean existByName(String name) {
		return (cateDao.getByName(name) != null);
	}

	@Override
	public Boolean cateToWriting(HashMap<String, Object> map) {
		return cateDao.insertCateRel(map);
	}

}
