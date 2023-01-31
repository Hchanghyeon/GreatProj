package com.great.domain;

public class MileageVO {
	
	private String id;
	private int mileage;
	private String date;
	private int time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "MileageVO [id=" + id + ", mileage=" + mileage + ", date=" + date + ", time=" + time + "]";
	}
}
