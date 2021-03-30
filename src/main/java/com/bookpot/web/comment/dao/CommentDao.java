package com.bookpot.web.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookpot.web.comment.dto.CommentDto;
import com.bookpot.web.comment.vo.CommentVo;

@Repository
public class CommentDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "commentMapper";
	
	
	public Boolean insert(CommentDto commentDto) {
		int count = sqlSession.insert(namespace + ".insert", commentDto);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}


	public List<CommentVo> getList(long writingNo) {
		return sqlSession.selectList(namespace + ".getList", writingNo);
	}


	public Boolean update(CommentDto commentDto) {
		int count = sqlSession.update(namespace + ".update", commentDto);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}


	public boolean delete(long commentNo) {
		int count = sqlSession.delete(namespace + ".delete", commentNo);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}	
}
