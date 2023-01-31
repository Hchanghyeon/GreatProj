package com.great.domain;

public class RecipeimgVO {
	private String foodname;
	private String img;
	
	public String getFoodname() {
		return foodname;
	}
	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	@Override
	public String toString() {
		return "RecipeimgVO [foodname=" + foodname + ", img=" + img + "]";
	}
}
