package org.feifei.phonebook.model;

/**
 * �绰������
 * 
 * @author ��ƽ��
 * 
 */
public class PhoneNum {
	private String id;

	/**
	 * ����
	 */
	private String type;

	/**
	 * ����
	 */
	private String phoneNum;

	/**
	 * ��ע
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
