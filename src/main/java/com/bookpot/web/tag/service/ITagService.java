package com.bookpot.web.tag.service;

public interface ITagService {
	
	// 태그 내용 유무 확인
	public Boolean existByName(String name);
	
	// 태그 추가
	public Boolean regTag(String name);
	
	// 태그 내용으로 List<writing no> 반환?
	
}
