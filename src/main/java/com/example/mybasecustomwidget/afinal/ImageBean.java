package com.example.mybasecustomwidget.afinal;

public class ImageBean {

	private String userName;
	private String url;
	public ImageBean(String userName, String url) {
		super();
		this.userName = userName;
		this.url = url;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
