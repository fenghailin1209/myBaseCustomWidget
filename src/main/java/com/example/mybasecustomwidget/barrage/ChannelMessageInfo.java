package com.example.mybasecustomwidget.barrage;

public class ChannelMessageInfo {

	private String message;
	private int type;
	private int channel_id;
	private int row;
	private UserData userData;

	/**
	 * 客服端的构�?函数
	 * @param channel_id
	 * @param type
	 * @param message
	 */
	public ChannelMessageInfo(int channel_id, int type, String message) {
		this.channel_id = channel_id;
		this.type = type;
		this.message = message;
	}

	public ChannelMessageInfo() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public int getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(int channel_id) {
		this.channel_id = channel_id;
	}

}
