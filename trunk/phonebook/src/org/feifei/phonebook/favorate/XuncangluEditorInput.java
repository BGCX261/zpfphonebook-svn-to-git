package org.feifei.phonebook.favorate;

import org.feifei.phonebook.base.BaseEditorInput;
/**
 * 
 * @author shichong
 *
 * @date 2009-7-9
 * @version $Id:XuncangluEditorInput.java 2009-7-9 ����09:10:30  shichong
 */
public class XuncangluEditorInput extends BaseEditorInput {
	//Ŀ���ַ��url
	String url;
	
	String name="Ѱ��·";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
