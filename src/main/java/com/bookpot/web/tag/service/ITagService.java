package com.bookpot.web.tag.service;

import java.util.HashMap;

public interface ITagService {
	
	// 태그 내용 유무 확인
	public Boolean existByName(String name);
	
	// 태그 추가
	public Boolean regTag(String name);
	
	// 태그와 글 연결
	public Boolean tagToWriting(HashMap<String, Object> map);
	
}
