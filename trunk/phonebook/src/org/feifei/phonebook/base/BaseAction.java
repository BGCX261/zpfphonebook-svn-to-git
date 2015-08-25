package org.feifei.phonebook.base;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;

public class BaseAction extends Action {
	private IWorkbenchWindow window;

	/**
	 * ���캯��
	 * 
	 * @param pWindow
	 */
	public BaseAction(final IWorkbenchWindow pWindow) {
		this.window = pWindow;
	}

	/**
	 * ȱʡ���캯��
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
