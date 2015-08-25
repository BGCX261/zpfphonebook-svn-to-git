package org.feifei.phonebook.util;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class Co {
	private Co() {

	}

	/**
	 * ��һ���������������һ����Ϊnull���ַ��� ��Ϊ����Ƶ�����������ƺ��������Ƚϼ���
	 * 
	 * @param o
	 * @return
	 */
	public static String getStr(Object o) {
		if (o != null) {
			return o.toString();
		} else {
			return "";
		}
	}

	public static void info(final Shell shell, String title,
			final String content) {
		if (title == null) {
			title = "";
		}
		MessageDialog.openInformation(shell, title, content);
	}

	public static int alert(final Shell shell, String title,
			final String content) {
		if (title == null) {
			title = "��ʾ";
		}
		MessageDialog dialog = new MessageDialog(shell, title, null, // accept
				content, MessageDialog.QUESTION, new String[] {
						IDialogConstants.OK_LABEL,
						IDialogConstants.CANCEL_LABEL }, 0);
		return dialog.open();
	}
}
