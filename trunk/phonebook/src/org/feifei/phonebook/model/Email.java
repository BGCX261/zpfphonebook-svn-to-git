package org.feifei.phonebook.model;

public class Email {
	private String id;

	private String emailAddress;

	private String type;

	private String comment;

	/**
	 * Email相关的联系人
	 */
	private Contactor contactor;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Contactor getContactor() {
		return contactor;
	}

	public void setContactor(Contactor contactor) {
		this.contactor = contactor;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
