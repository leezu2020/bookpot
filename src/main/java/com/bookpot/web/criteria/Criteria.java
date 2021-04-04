package com.bookpot.web.criteria;

import java.util.List;

public class Criteria {
	
	// 페이징을 위한 변수 추가
	
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


	
	
}
