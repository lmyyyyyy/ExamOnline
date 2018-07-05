package com.exam.online.page;

import java.util.List;

public class Result {
	private Page page;
	@SuppressWarnings("rawtypes")
	private List list;
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}
	public void setList(@SuppressWarnings("rawtypes") List list) {
		this.list = list;
	}
	
}
