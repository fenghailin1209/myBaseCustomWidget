package com.example.mybasecustomwidget.barrage;

public class UserData {
	private int uid;
	private String nick;
	private String avatar;
	private int age;
	private int sex;
	private String signature;
	
	/**是否分享位置*/
	private boolean isShareGPS;
	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}
	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}
	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**
	 * @return the avatar
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * @param avatar the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Override
	public boolean equals(Object obj) {
		UserData userData = (UserData)obj;
		if (uid == userData.getUid()) {
			return true;
		}
		return false;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public boolean isShareGPS() {
		return isShareGPS;
	}
	public void setShareGPS(boolean isShareGPS) {
		this.isShareGPS = isShareGPS;
	}
}
