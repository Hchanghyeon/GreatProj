package com.great.domain;

public class FundingCorpVO {
	
	private String name;
	private String info;
	private String imgsrc;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	@Override
	public String toString() {
		return "FundingCorpVO [name=" + name + ", info=" + info + "]";
	}
}
