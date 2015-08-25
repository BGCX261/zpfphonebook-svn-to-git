package org.feifei.phonebook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.adduser.AddUserEditor;
import org.feifei.phonebook.adduser.AddUserEditorInput;

/**
 * 添加联系人的操作
 * 
 * @author 赵平飞
 * 
 */
public class AddUserAction extends Action {

	private IWorkbenchWindow window;

	public AddUserAction(final IWorkbenchWindow pWindow) {
		window = pWindow;
		setId(getClass().getName());
		setText("添加联系人");
	}

	public void run() {
		AddUserEditorInput input = new AddUserEditorInput();
		try {
			window.getActivePage().openEditor(input, AddUserEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
