package com.great.domain;

public class FamilyVO {
	private String id;
	private String pw;
	private String salt;
	private String nickname;
	private String email;
	private String address1;
	private String address2;
	private String address3;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	@Override
	public String toString() {
		return "FamilyVO [id=" + id + ", pw=" + pw + ", salt=" + salt + ", nickname=" + nickname + ", email=" + email
				+ ", address1=" + address1 + ", address2=" + address2 + ", address3=" + address3 + "]";
	}
	
	

}
