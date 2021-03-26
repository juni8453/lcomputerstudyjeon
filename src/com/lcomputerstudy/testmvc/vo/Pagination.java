package com.lcomputerstudy.testmvc.vo;

public class Pagination {
	int count;			// 
	int page;			// 페이지 갯수
	int pageNum;		// 페이지 숫자
	int startPage;		// 시작 페이지
	int endPage;		// 끝 페이지
	int lastPage;		// 맨 끝 페이지
	int prevPage;		// 이전 페이지
	int nextPage;		// 다음 페이지
	public static final int pageUnit=5;
	public static final int perPage=6;
	private Search search = null;
	
	public Pagination(int page, int count) {
		this.page = page;
		this.count = count;
		startPage = ((page-1)/pageUnit)*pageUnit+1;
		lastPage = (int)Math.ceil(count/(float)perPage);
		endPage = startPage+pageUnit-1;
		endPage = endPage < lastPage ? endPage : lastPage;
		prevPage = startPage - 1;
		nextPage = (startPage+pageUnit);
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum() {
		this.pageNum = pageNum;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public int getPerPage() {
		return perPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}
}



	
	
	

