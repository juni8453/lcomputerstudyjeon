package com.lcomputerstudy.testmvc.vo;

public class Board {

	private int b_idx;
	private String b_title;
	private String b_content;
	private String b_date;
	private String b_writer;
	private int b_views;
	private int u_idx;
	private int u_id;
	private int rownum;
	private String keyWord;
	private User user;
	private int b_group;
	private int b_order;
	private int b_depth;
	private int b_step;


	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public String getB_writer() {
		return b_writer;
	}
	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}
	public int getB_views() {
		return b_views;
	}
	public void setB_views(int b_views) {
		this.b_views = b_views;
	}
	public int getU_idx() {
		return u_idx;
	}
	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public int getB_group() {
		return b_group;
	}
	public void setB_group(int b_group) {
		this.b_group = b_group;
	}
	public int getB_order() {
		return b_order;
	}
	public void setB_order(int b_order) {
		this.b_order = b_order;
	}
	public int getB_depth() {
		return b_depth;
	}
	public void setB_depth(int b_depth) {
		this.b_depth = b_depth;
	}
	public int getB_step() {
		return b_step;
	}
	public void setB_step(int b_step) {
		this.b_step = b_step;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
