package com.bookpot.web.comment.vo;

public class CommentVo {
	private long no;
	private String content;
	private String regDate;
	private String modDate;
	private long writingNo;
	private String nickname;
	
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
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	public long getWritingNo() {
		return writingNo;
	}
	public void setWritingNo(long writingNo) {
		this.writingNo = writingNo;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
