package com.bookpot.web.comment.service;

import java.util.List;

import com.bookpot.web.comment.dto.CommentDto;

public interface ICommentService {
	
	// 글 번호로 댓글 가져오기
	public List<CommentDto> getByWritingNo(long no);
	
	// 댓글 삭제하기
	
}
