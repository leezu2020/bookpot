package com.bookpot.web.writing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookpot.web.search.Criteria;
import com.bookpot.web.writing.dao.WritingDao;
import com.bookpot.web.writing.dto.WritingDto;
import com.bookpot.web.writing.entity.Writing;
import com.bookpot.web.writing.view.WritingView;

@Service
public class WritingService implements IWritingService{

	@Autowired
	private WritingDao writingDao;

	@Override
	public void delWriting(long no) {
		writingDao.delete(no);		
	}

	@Override
	public List<WritingView> getWritingList(Criteria cri) {
		// good :  좋아요 수 계산
		// tag : 태그 가져오기
		
		// 로그인했을때
		// srcap : 스크랩 여부
		// good : 좋아요 여부
		return writingDao.search(cri);
	}

	@Override
	public WritingView get(long id) {
		return writingDao.get(id);
	}

	@Override
	public List<WritingView> getScrapList(long userNo) {
		// 스크랩 db에서 userNO으로 조회해서 writingNo 가져와서 writing 반환
		return null;
	}

	@Override
	public boolean add(WritingDto writingDto) {
		return writingDao.insert(writingDto);
	}

	public int getWritingNum(Criteria cri) {
		return writingDao.searchNum(cri);
	}
/////////////////////////////////// 구현 완료 //////////////////////////////////////////////////
	
	@Override
	public List<String> getTitleList(Criteria srch) {
		return writingDao.getTitles(srch);
	}

}
