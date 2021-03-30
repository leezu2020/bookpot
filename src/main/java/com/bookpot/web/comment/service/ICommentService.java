package com.bookpot.web.comment.service;

import java.util.List;

import com.bookpot.web.comment.dto.CommentDto;
import com.bookpot.web.comment.vo.CommentVo;

public interface ICommentService {	
	// 댓글 추가하기
	public boolean regComments(CommentDto commentDto);

	// 댓글 불러오기
	public List<CommentVo> getList(long writingNo);

	// 댓글 수정하기
	public boolean modify(CommentDto commentDto);

	// 댓글 삭제하기
	public boolean delete(long commentNo);
	
}
