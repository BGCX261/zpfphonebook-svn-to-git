package org.feifei.phonebook.welcome;

import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.feifei.phonebook.base.BaseAction;
import org.feifei.phonebook.util.Co;

public class ExitAction extends BaseAction {
	/**
	 * ���캯��
	 * 
	 * @param pWindow
	 *            ����
	 */
	public ExitAction(IWorkbenchWindow pWindow) {
		super(pWindow);
		setId(getClass().getName());
		setText("�˳�");
	}

	public void run() {
		int returnCode = Co.alert(getWindow().getShell(), null, "ȷ��Ҫ�ر���");
		if (returnCode == Window.OK) {
			getWindow().close();
		}
	}

}
