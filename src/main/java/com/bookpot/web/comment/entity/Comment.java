package com.bookpot.web.comment.entity;

import java.util.Date;

public class Comment {
	private long no;
	private String content;
	private Date regDate;
	private long writingNo;
	private long userNo;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
	
	
}
