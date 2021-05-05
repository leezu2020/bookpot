package com.bookpot.web.search;

import java.util.List;

public class Criteria {
	
	// 현재 페이지
	private int page;
	// 한 페이지에 출력할 글 수 
	private int perPage = 9;
	private int offset;
	// 책제목, 해시태그 검색 키워드
	private String keyword;
	// 국내/해외
	private String division;
	// 분야
	private List<String> categories;
	// 트렌드/최신
	private String sort;
	// 로그인한 사용자 번호
	private Long userNo;
	
	public Criteria() {
		this.page = 1;
	}
	
	public int getPerPage() {
		return perPage;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		//offset 계산
		this.offset = (page - 1) * this.perPage;
		this.page = page;
	}

	public int getOffset() {
		return offset;
	}
	
	public String categoryToString() {
		StringBuilder sb = new StringBuilder();
		System.out.println("criteria.카테고리 : " + categories);
		for(int i=0; i<categories.size(); i++) {
			if(i==0) {
				sb.append(this.categories.get(0));
			} else {
				sb.append("," + this.categories.get(i));
			}
		}
		return sb.toString();
	}
	
}
