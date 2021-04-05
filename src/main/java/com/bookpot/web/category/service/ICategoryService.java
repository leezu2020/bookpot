package com.bookpot.web.category.service;

import java.util.HashMap;

public interface ICategoryService {
	
	// 카테고리 내용 유무 확인
	public Boolean existByName(String name);
	
	// 카테고리와 글 연결
	public Boolean cateToWriting(HashMap<String, Object> map);
	
}
