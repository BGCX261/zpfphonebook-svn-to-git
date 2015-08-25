package org.feifei.phonebook.base;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

public class BaseAction extends Action {
	private IWorkbenchWindow window;

	/**
	 * 构造函数
	 * 
	 * @param pWindow
	 */
	public BaseAction(final IWorkbenchWindow pWindow) {
		this.window = pWindow;
	}

	/**
	 * 缺省构造函数
	 */
	public BaseAction() {

	}

	public IWorkbenchWindow getWindow() {
		return window;
	}

	public void setWindow(IWorkbenchWindow window) {
		this.window = window;
	}
}
