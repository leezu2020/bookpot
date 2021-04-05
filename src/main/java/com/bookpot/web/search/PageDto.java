package com.bookpot.web.search;

import java.util.ArrayList;
import java.util.HashMap;

public class PageDto {

	private int startPage;
	private int endPage;
	private ArrayList<Integer> pageList;
	private HashMap<String, Boolean> btn;
	
	private int total;
	// 현재 페이지
	private int page;
	// 한번에 보여줄 페이지 수
	private int pageCnt = 5;
	
	public PageDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PageDto(int total, int page, int perPage) {
		btn = new HashMap<String, Boolean>();
		this.total = total;
		this.page = page;
		
		this.endPage = (int) (Math.ceil(page/ (double)pageCnt) * pageCnt);
		this.startPage = this.endPage - pageCnt + 1;
		
		int realEnd =(int) (Math.ceil((total * 1.0) / perPage));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.btn.put("preBtn", this.startPage > 1);
		this.btn.put("nextBtn", this.endPage < realEnd);
		
		this.pageList = new ArrayList<Integer>();
		for(int i=0; i<this.pageCnt; i++) {
			if(i + this.startPage <= this.endPage)
				pageList.add(i + this.startPage);
		}
	}


	public HashMap<String, Boolean> getBtn() {
		return btn;
	}
	public ArrayList<Integer> getPageList() {
		return pageList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	
	
	
}
