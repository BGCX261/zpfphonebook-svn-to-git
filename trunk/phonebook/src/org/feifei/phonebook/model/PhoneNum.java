package org.feifei.phonebook.model;

/**
 * 电话号码类
 * 
 * @author 赵平飞
 * 
 */
public class PhoneNum {
	private String id;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 号码
	 */
	private String phoneNum;

	/**
	 * 备注
	 */
	private String comment;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(final String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
