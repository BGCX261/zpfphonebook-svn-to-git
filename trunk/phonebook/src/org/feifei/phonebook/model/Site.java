package org.feifei.phonebook.model;

/**
 * 
 * @author shichong
 * 
 * @date 2009-7-9
 * @version $Id:Site.java 2009-7-9 ÉÏÎç11:25:18 shichong
 */
public class Site {
	//ÍøÖ·
	String url;

	//ÃèÊö
	String description;
	//Ãû×Ö
	String name;
	//
	String tag;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String site) {
		this.url = site;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
