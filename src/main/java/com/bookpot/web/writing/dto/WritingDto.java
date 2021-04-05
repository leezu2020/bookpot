package com.bookpot.web.writing.dto;

import java.util.List;

public class WritingDto {
	// selectKey를 위한 임시 저장변수
	private long writingNo;
	private String booktitle;
	private String author;
	private String publisher;
	private String division;
	private String title;
	private String content;
	private String imgUrl;
	private long userNo;
	private String startDate;
	private String endDate;
	private String passage;
	
	private List<List<String>> tag;
	private List<List<String>> category;
	
	
	public long getWritingNo() {
		return writingNo;
	}
	public void setWritingNo(long writingNo) {
		this.writingNo = writingNo;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPassage() {
		return passage;
	}
	public void setPassage(String passage) {
		this.passage = passage;
	}
	public List<List<String>> getTag() {
		return tag;
	}
	public void setTag(List<List<String>> tag) {
		this.tag = tag;
	}
	public List<List<String>> getCategory() {
		return category;
	}
	public void setCategory(List<List<String>> category) {
		this.category = category;
	}

	
	
}
