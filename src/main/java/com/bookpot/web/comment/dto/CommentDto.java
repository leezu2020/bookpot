package com.bookpot.web.comment.dto;

public class CommentDto {
	private long no;
	private long writingNo;
	private long userNo;
	private String content;
	
	public CommentDto() {
	}
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getWritingNo() {
		return writingNo;
	}
	public void setWritingNo(long writingNo) {
		this.writingNo = writingNo;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
