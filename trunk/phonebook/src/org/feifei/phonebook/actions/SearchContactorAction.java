package org.feifei.phonebook.actions;

import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.base.BaseAction;
import org.feifei.phonebook.base.BaseEditorInput;
import org.feifei.phonebook.searchuser.SearchUser;

public class SearchContactorAction extends BaseAction {
	private IWorkbenchWindow window;

	public SearchContactorAction(IWorkbenchWindow pWindow) {
		this.window = pWindow;
		setId(this.getClass().getName());
		setText("������ϵ������");
	}

	public void run() {
		BaseEditorInput input=new BaseEditorInput();
		try {
			window.getActivePage().openEditor(input, SearchUser.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
