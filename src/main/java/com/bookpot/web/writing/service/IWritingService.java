package com.bookpot.web.writing.service;

import java.util.List;

import com.bookpot.web.criteria.Criteria;
import com.bookpot.web.writing.entity.Writing;
import com.bookpot.web.writing.view.WritingView;

public interface IWritingService {
	// 글쓰기
	public boolean add(Writing writing);

	// 글 수정
	// 인자를 어떻게 받을지? no + writing? or writing?
	
	// 글 삭제
	public void delWriting(long no);
	
	// 글 목록 가져오기(메인 및 검색)
	// Search -> List<writing>
	public List<WritingView> getWritingList(Criteria searchDto);
	
	// 상세 글 가져오기
	// long id -> writing
	public WritingView get(long id);
	
	// 스크랩한 글 가져오기
	public List<WritingView> getScrapList(long userNo);
	
////////////////////////////////////// 구현 완료 ////////////////////////////////////////////////	
	// 등록된 책 제목 가져오기
	public List<String> getTitleList(Criteria srch);
}
