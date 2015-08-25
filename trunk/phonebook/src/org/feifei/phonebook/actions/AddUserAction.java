package org.feifei.phonebook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.adduser.AddUserEditor;
import org.feifei.phonebook.adduser.AddUserEditorInput;

/**
 * �����ϵ�˵Ĳ���
 * 
 * @author ��ƽ��
 * 
 */
public class AddUserAction extends Action {

	private IWorkbenchWindow window;

	public AddUserAction(final IWorkbenchWindow pWindow) {
		window = pWindow;
		setId(getClass().getName());
		setText("�����ϵ��");
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
