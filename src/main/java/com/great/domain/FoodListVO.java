package com.great.domain;

import java.util.Date;

public class FoodListVO {
	private int num;
	private String id;
	private float LTX;
	private float LTY;
	private float RDX;
	private float RDY;
	private int X;
	private int Y;
	private String cls;
	private String img;
	private String inDate;
	private String endDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getLTX() {
		return LTX;
	}
	public void setLTX(float lTX) {
		LTX = lTX;
	}
	public float getLTY() {
		return LTY;
	}
	public void setLTY(float lTY) {
		LTY = lTY;
	}
	public float getRDX() {
		return RDX;
	}
	public void setRDX(float rDX) {
		RDX = rDX;
	}
	public float getRDY() {
		return RDY;
	}
	public void setRDY(float rDY) {
		RDY = rDY;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "FoodListVO [id=" + id + ", LTX=" + LTX + ", LTY=" + LTY + ", RDX=" + RDX + ", RDY=" + RDY + ", X=" + X
				+ ", Y=" + Y + ", cls=" + cls + ", img=" + img + ", inDate=" + inDate + ", endDate=" + endDate + "]";
	}
	
}
