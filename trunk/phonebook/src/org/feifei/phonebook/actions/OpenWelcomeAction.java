package org.feifei.phonebook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.welcome.WelcomeEditor;
import org.feifei.phonebook.welcome.WelcomeEditorInput;

/**
 * 
 * @author ��ƽ��
 * 
 */
public class OpenWelcomeAction extends Action {
	IWorkbenchWindow window;

	public OpenWelcomeAction(final IWorkbenchWindow pWindow) {
		this.window = pWindow;
		setText("��ӭ����");
		setId(getClass().getName());
	}

	public void run() {
		WelcomeEditorInput input = new WelcomeEditorInput();
		try {
			window.getActivePage().openEditor(input, WelcomeEditor.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
