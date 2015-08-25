package org.feifei.phonebook.adduser;

import org.feifei.phonebook.base.BaseEditorInput;

public class AddUserEditorInput extends BaseEditorInput {
	public boolean equals(Object obj) {
		if (obj instanceof AddUserEditorInput) {
			return true;
		} else {
			return false;
		}
	}
}
