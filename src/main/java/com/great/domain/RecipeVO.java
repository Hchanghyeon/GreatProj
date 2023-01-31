package com.great.domain;

public class RecipeVO {
	private String recipe_id;
	private String recipe_nm_ko;
	private String sumry;
	private String nation_code;
	private String nation_nm;
	private String ty_code;
	private String ty_nm;
	private String cooking_time;
	private String calorie;
	private String qnt;
	private String level_nm;
	private String irdnt_code;
	private String pc_nm;
	private String img_path;
	
	
	public String getRecipe_id() {
		return recipe_id;
	}
	public void setRecipe_id(String recipe_id) {
		this.recipe_id = recipe_id;
	}
	public String getRecipe_nm_ko() {
		return recipe_nm_ko;
	}
	public void setRecipe_nm_ko(String recipe_nm_ko) {
		this.recipe_nm_ko = recipe_nm_ko;
	}
	public String getSumry() {
		return sumry;
	}
	public void setSumry(String sumry) {
		this.sumry = sumry;
	}
	public String getNation_code() {
		return nation_code;
	}
	public void setNation_code(String nation_code) {
		this.nation_code = nation_code;
	}
	public String getNation_nm() {
		return nation_nm;
	}
	public void setNation_nm(String nation_nm) {
		this.nation_nm = nation_nm;
	}
	public String getTy_code() {
		return ty_code;
	}
	public void setTy_code(String ty_code) {
		this.ty_code = ty_code;
	}
	public String getTy_nm() {
		return ty_nm;
	}
	public void setTy_nm(String ty_nm) {
		this.ty_nm = ty_nm;
	}
	public String getCooking_time() {
		return cooking_time;
	}
	public void setCooking_time(String cooking_time) {
		this.cooking_time = cooking_time;
	}
	public String getCalorie() {
		return calorie;
	}
	public void setCalorie(String calorie) {
		this.calorie = calorie;
	}
	public String getQnt() {
		return qnt;
	}
	public void setQnt(String qnt) {
		this.qnt = qnt;
	}
	public String getLevel_nm() {
		return level_nm;
	}
	public void setLevel_nm(String level_nm) {
		this.level_nm = level_nm;
	}
	public String getIrdnt_code() {
		return irdnt_code;
	}
	public void setIrdnt_code(String irdnt_code) {
		this.irdnt_code = irdnt_code;
	}
	public String getPc_nm() {
		return pc_nm;
	}
	public void setPc_nm(String pc_nm) {
		this.pc_nm = pc_nm;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	
	@Override
	public String toString() {
		return "RecipeVO [recipe_id=" + recipe_id + ", recipe_nm_ko=" + recipe_nm_ko + ", sumry=" + sumry
				+ ", nation_code=" + nation_code + ", nation_nm=" + nation_nm + ", ty_code=" + ty_code + ", ty_nm="
				+ ty_nm + ", cooking_time=" + cooking_time + ", calorie=" + calorie + ", qnt=" + qnt + ", level_nm="
				+ level_nm + ", irdnt_code=" + irdnt_code + ", pc_nm=" + pc_nm + ", img_path=" + img_path + "]";
	}
	
}
