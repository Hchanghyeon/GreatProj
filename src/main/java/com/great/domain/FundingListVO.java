package com.great.domain;

public class FundingListVO {
	
	private String id;
	private String corp;
	private int count;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCorp() {
		return corp;
	}
	public void setCorp(String corp) {
		this.corp = corp;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "FundingListVO [id=" + id + ", corp=" + corp + ", count=" + count + "]";
	}
}
