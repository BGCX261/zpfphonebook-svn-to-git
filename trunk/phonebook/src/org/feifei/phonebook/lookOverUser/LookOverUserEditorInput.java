package org.feifei.phonebook.lookOverUser;

import org.feifei.phonebook.base.BaseEditorInput;
import org.feifei.phonebook.model.Contactor;

/**
 * �鿴�û���Ϣ��editorInput
 * 
 * @author ��ƽ��
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
