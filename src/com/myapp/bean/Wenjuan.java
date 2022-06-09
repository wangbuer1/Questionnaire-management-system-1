package com.myapp.bean;

import java.sql.Date;

public class Wenjuan {
	private int w_id;
	private String w_title;
	private String w_type;
	private Date w_endtime;
	private String u_id;

    public Wenjuan(String w_title, String w_type, Date w_endtime, String u_id ) {
    	this.w_title = w_title;
    	this.w_type = w_type;
    	this.w_endtime = w_endtime;
    	this.u_id = u_id;
    }

    public Date getW_endtime() {
		return w_endtime;
	}
	public void setW_endtime(Date w_endtime) {
		this.w_endtime = w_endtime;
	}

	public String getW_title() {
		return w_title;
	}
	public void setW_title(String w_title) {

		this.w_title = w_title;
	}
	public String getW_type() {
		return w_type;
	}
	public void setW_type(String w_type) {
		this.w_type = w_type;
	}
	public int getW_id() {
		return w_id;
	}
	public void setW_id(int w_id) {
		this.w_id = w_id;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public Wenjuan(){

	}
}
