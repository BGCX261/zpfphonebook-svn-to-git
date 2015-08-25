package org.feifei.phonebook.welcome;

import org.eclipse.jface.window.Window;
import org.eclipse.ui.IWorkbenchWindow;
import org.feifei.phonebook.base.BaseAction;
import org.feifei.phonebook.util.Co;

public class ExitAction extends BaseAction {
	/**
	 * 构造函数
	 * 
	 * @param pWindow
	 *            窗体
	 */
	public ExitAction(IWorkbenchWindow pWindow) {
		super(pWindow);
		setId(getClass().getName());
		setText("退出");
	}

	public void run() {
		int returnCode = Co.alert(getWindow().getShell(), null, "确定要关闭吗？");
		if (returnCode == Window.OK) {
			getWindow().close();
		}
	}

}
