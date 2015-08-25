package org.feifei.phonebook.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 联系人
 * 
 * @author 赵平飞
 * 
 */
public class Contactor {
	private String id;

	private String name;

	private Integer age;

	private String sex;

	private String nation;

	private String address;

	private String postNum;

	private Set<PhoneNum> phoneNums = new HashSet<PhoneNum>();

	private Set<Email> emails = new HashSet<Email>();

	private String qqNum;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> email) {
		this.emails = email;
	}

	public void addEmail(final Email email) {
		emails.add(email);
		email.setContactor(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<PhoneNum> getPhoneNums() {
		return phoneNums;
	}

	public void setPhoneNums(Set<PhoneNum> phoneNum) {
		this.phoneNums = phoneNum;
	}

	public void addPhoneNum(final PhoneNum phoneNum) {
		this.phoneNums.add(phoneNum);
	}

	public String getPostNum() {
		return postNum;
	}

	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
