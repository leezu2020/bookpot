package com.bookpot.web.writing.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookpot.web.search.Criteria;
import com.bookpot.web.writing.dto.WritingDto;
import com.bookpot.web.writing.entity.Writing;
import com.bookpot.web.writing.view.WritingView;

@Repository
public class WritingDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "writingMapper";
	
	public Boolean insert(WritingDto writingDto) {
		int count = sqlSession.insert(namespace + ".insert", writingDto);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean update(Writing writing) {
		int count = sqlSession.update(namespace + ".update", writing);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}

	public Boolean delete(long no) {
		int count = sqlSession.delete(namespace + ".delete", no);
		if(count == 1) {
			return true;
		} else {
			// 삭제 오류
			return false;
		}
	}
	

	// 글 detail 가져오기
	public WritingView get(long id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".getDetail", id);
	}
	
	// 검색 해당 글 개수
	public int searchNum(Criteria cri) {
		return sqlSession.selectOne(namespace + ".getSearchNum", cri);
	}
//////////////////// 구현 완료 ///////////////////////////////////////////////
	public List<String> getTitles(Criteria srch) {
		System.out.println("검색어 : " + srch.getKeyword());
		return sqlSession.selectList(namespace + ".getTitles", srch);
	}

	// 검색 결과 출력 테스트용
	public List<WritingView> search(Criteria cri){
		return sqlSession.selectList(namespace + ".get", cri);
	}

}
