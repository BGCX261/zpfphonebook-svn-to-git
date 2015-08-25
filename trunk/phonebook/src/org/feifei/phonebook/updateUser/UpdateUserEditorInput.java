package org.feifei.phonebook.updateUser;

import org.eclipse.swt.widgets.Table;
import org.feifei.phonebook.base.BaseEditorInput;
import org.feifei.phonebook.model.Contactor;

public class UpdateUserEditorInput extends BaseEditorInput {
	private Table table;

	private Contactor contactor;

	public Contactor getContactor() {
		return contactor;
	}

	public void setContactor(Contactor contactor) {
		this.contactor = contactor;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public boolean equals(Object obj) {
		if (obj instanceof UpdateUserEditorInput) {
			return true;
		} else {
			return false;
		}
	}
}
