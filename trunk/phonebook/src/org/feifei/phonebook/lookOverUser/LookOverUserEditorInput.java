package org.feifei.phonebook.lookOverUser;

import org.feifei.phonebook.base.BaseEditorInput;
import org.feifei.phonebook.model.Contactor;

/**
 * 查看用户信息的editorInput
 * 
 * @author 赵平飞
 * 
 */
public class LookOverUserEditorInput extends BaseEditorInput {
	private Contactor currContactor;
	

	public Contactor getCurrContactor() {
		return currContactor;
	}

	public void setCurrContactor(final Contactor currContactor) {
		this.currContactor = currContactor;
	}

	public boolean equals(Object obj) {
		if (obj instanceof LookOverUserEditorInput) {
			return true;
		} else {
			return false;
		}
	}
}
