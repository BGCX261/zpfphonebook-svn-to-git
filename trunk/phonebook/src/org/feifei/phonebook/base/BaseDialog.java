package org.feifei.phonebook.base;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * dialog的基类
 * 
 * @author 赵平飞
 * 
 */
public class BaseDialog extends Dialog {

	protected BaseDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createContents(Composite parent) {

		return super.createContents(parent);
	}

	protected Control createButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		return super.createButtonBar(parent);
	}

}
