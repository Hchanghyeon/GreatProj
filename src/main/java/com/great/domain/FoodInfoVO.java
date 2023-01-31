package com.great.domain;

import java.util.Date;

public class FoodInfoVO {
	private String foodname;
	private String foodexp;
	
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public String getFoodexp() {
		return foodexp;
	}
	public void setFoodexp(String foodexp) {
		this.foodexp = foodexp;
	}
	
	@Override
	public String toString() {
		return "FoodInfoVO [foodname=" + foodname + ", foodexp=" + foodexp + "]";
	}
}
