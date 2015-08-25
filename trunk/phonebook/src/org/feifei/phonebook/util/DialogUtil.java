package org.feifei.phonebook.util;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class DialogUtil {

	public static void alert(final Shell shell, final String pTitle,
			final String message) {
		String title = pTitle;
		if (title == null) {
			title = "提示";
		}
		MessageDialog.openInformation(shell, title, message);
	}

	public static void confirm(final Shell shell, final String pTitle,
			final String message) {
		String title = pTitle;
		if (title == null) {
			title = "提示";
		}
		MessageDialog.openConfirm(shell, title, message);
	}

}
