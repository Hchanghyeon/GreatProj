package com.great.domain;

public class FundingVO {
	
	private String corp;
	private int count;
	
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
		return "FundingVO [corp=" + corp + ", count=" + count + "]";
	}
}
