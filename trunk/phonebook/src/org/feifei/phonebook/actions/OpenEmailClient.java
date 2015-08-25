package org.feifei.phonebook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.emails.EmailClient;
import org.feifei.phonebook.emails.EmailInput;

public class OpenEmailClient extends Action {
	IWorkbenchWindow window;

	public OpenEmailClient(IWorkbenchWindow window) {
		this.window = window;
		setId(getClass().getName());
		setText("���ʼ��ͻ���");
	}

	@Override
	public void run() {
		EmailInput input = new EmailInput();
		try {
			window.getActivePage().openEditor(input, EmailClient.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
