package org.feifei.phonebook.adduser.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.feifei.phonebook.Activator;
import org.feifei.phonebook.adduser.AddUserEditor;
import org.feifei.phonebook.base.BaseDialog;
import org.feifei.phonebook.model.PhoneNum;

import com.swtdesigner.ResourceManager;

public class AddPhoneNumDialog extends BaseDialog {

	private Text text_2;

	private Text text_1;

	private Text text;

	/**
	 * 添加联系人的editor
	 */
	private AddUserEditor editor;

	private PhoneNum phoneNum;

	/**
	 * Create the dialog
	 * 
	 * @param parentShell
	 */
	public AddPhoneNumDialog(Shell parentShell, AddUserEditor pEditor) {
		super(parentShell);
		this.editor = pEditor;
	}

	/**
	 * Create contents of the dialog
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginBottom = 10;
		gridLayout.marginTop = 20;
		container.setLayout(gridLayout);

		final Group group = new Group(container, SWT.NONE);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 2;
		group.setLayout(gridLayout_1);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		group.setText("添加号码");

		final Label label = new Label(group, SWT.NONE);
		label.setText("号   码");

		text = new Text(group, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("类   型");

		text_1 = new Text(group, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Label label_2 = new Label(group, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false,
				2, 1));
		label_2.setText("备   注");

		text_2 = new Text(group, SWT.V_SCROLL | SWT.MULTI | SWT.BORDER
				| SWT.WRAP);
		final GridData gd_text_2 = new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1);
		gd_text_2.heightHint = 199;
		text_2.setLayoutData(gd_text_2);
		//
		return container;
	}

	/**
	 * Create contents of the button bar
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "完成并继续", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "完成", false);
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(444, 424);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("添加号码");
		newShell.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/announce-32.png"));
	}

	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.CANCEL_ID) {
			composePhoneNum();
			if (phoneNum == null) {
				this.close();
				return;
			}
			editor.addPhoneNum(phoneNum);
			this.close();
			return;
		}
		if (buttonId == IDialogConstants.OK_ID) {
			composePhoneNum();
			if (phoneNum == null) {
				return;
			}
			editor.addPhoneNum(phoneNum);
			clear();
			return;
		}
	}

	private void composePhoneNum() {
		if (text.getText().trim().equals("")) {
			phoneNum = null;
			return;
		}
		phoneNum = new PhoneNum();
		phoneNum.setPhoneNum(text.getText());
		phoneNum.setType(text_1.getText());
		phoneNum.setComment(text_2.getText());
	}

	private void clear() {
		text.setText("");
		text_1.setText("");
		text_2.setText("");
	}
}
