package com.bookpot.web.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookpot.web.comment.dao.CommentDao;
import com.bookpot.web.comment.dto.CommentDto;
import com.bookpot.web.comment.vo.CommentVo;

@Service
public class CommentService implements ICommentService{

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public boolean regComments(CommentDto commentDto) {
		return commentDao.insert(commentDto);
	}

	@Override
	public List<CommentVo> getList(long writingNo) {
		return commentDao.getList(writingNo);
	}

	@Override
	public boolean modify(CommentDto commentDto) {
		return commentDao.update(commentDto);
	}

	@Override
	public boolean delete(long commentNo) {
		return commentDao.delete(commentNo);
	}

}
