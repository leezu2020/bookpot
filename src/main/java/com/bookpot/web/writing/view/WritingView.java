package com.bookpot.web.writing.view;

import java.util.Date;

public class WritingView {
	// 회원 로그인 시
	// 좋아요 표시 여부
	// 스크랩 표시 여부
	private long no;
	private String booktitle;
	private String division;
	private String tag;
	private String category;
	private String title;
	private String content;
	private String bookimg;
	private Date regDate;
	private int goodCnt;
	private String nickname;
	private String userimg;
	private boolean isGood;
	private boolean isScrap;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBookimg() {
		return bookimg;
	}
	public void setBookimg(String bookimg) {
		this.bookimg = bookimg;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUserimg() {
		return userimg;
	}
	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}
	public int getGoodCnt() {
		return goodCnt;
	}
	public void setGoodCnt(int goodCnt) {
		this.goodCnt = goodCnt;
	}
	public Boolean getIsGood() {
		return isGood;
	}
	public void setIsGood(boolean isGood) {
		this.isGood = isGood;
	}
	public Boolean getIsScrap() {
		return isScrap;
	}
	public void setIsScrap(boolean isScrap) {
		this.isScrap = isScrap;
	}


	
}
