package com.example.mybasecustomwidget.baseadpter;

public class Bean {

	private String name;
	private String zsznc;
	private String zszn_time;
	private String head_iv;
	
	

	public Bean(String name, String zsznc, String zszn_time, String head_iv) {
		super();
		this.name = name;
		this.zsznc = zsznc;
		this.zszn_time = zszn_time;
		this.head_iv = head_iv;
	}

	public String getHead_iv() {
		return head_iv;
	}

	public void setHead_iv(String head_iv) {
		this.head_iv = head_iv;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZsznc() {
		return zsznc;
	}

	public void setZsznc(String zsznc) {
		this.zsznc = zsznc;
	}

	public String getZszn_time() {
		return zszn_time;
	}

	public void setZszn_time(String zszn_time) {
		this.zszn_time = zszn_time;
	}

}
