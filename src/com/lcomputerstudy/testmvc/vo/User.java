package com.lcomputerstudy.testmvc.vo;

public class User {
	
	private int u_idx;
	private String u_id;
	private String u_pw;
	private String u_name;
	private String u_tel;
	private String u_age;
	private int rownum;
	
	public int getU_idx() {
		return u_idx;
	}
	public void setU_idx(int u_idx){
		this.u_idx = u_idx;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	 
	public String getU_age() {
		return u_age;
	}
	public void setU_age(String u_age) {
		this.u_age = u_age;
	}
	public String getU_tel() {
		return u_tel;
	}
	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	@Override
	public String toString() {
		return "User [u_idx=" + u_idx + ", u_id=" + u_id + ", u_pw=" + u_pw + ", u_name=" + u_name + ", u_tel=" + u_tel
				+ ", u_age=" + u_age + ", rownum=" + rownum + "]";
	}
	
	
}