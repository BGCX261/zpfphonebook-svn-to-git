package org.feifei.phonebook.searchuser.dialogs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.search.Hits;
import org.eclipse.jface.dialogs.Dialog;
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
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.lookOverUser.LookOverUserEditor;
import org.feifei.phonebook.lookOverUser.LookOverUserEditorInput;
import org.feifei.phonebook.model.Contactor;
import org.feifei.phonebook.model.PhoneNum;
import org.feifei.phonebook.searchuser.index.SearchIndex;

public class SearchContactorDialog extends Dialog {

	private Text textPhoneNum;

	private Text textQQNum;

	private Text textEmailAddress;

	private Text textName;

	private LookOverUserEditor editor;

	private IWorkbenchWindow window;

	/**
	 * Create the dialog
	 * 
	 * @param parentShell
	 */
	public SearchContactorDialog(Shell parentShell, LookOverUserEditor pEditor,
			IWorkbenchWindow pWindow) {
		super(parentShell);
		this.editor = pEditor;
		this.window = pWindow;
	}

	/**
	 * Create contents of the dialog
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

		final Group group = new Group(container, SWT.NONE);
		group.setText("根据联系人姓名搜索");
		final GridData gd_group = new GridData(SWT.CENTER, SWT.CENTER, false,
				false);
		gd_group.heightHint = 41;
		gd_group.widthHint = 476;
		group.setLayoutData(gd_group);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		group.setLayout(gridLayout);

		final Label label = new Label(group, SWT.NONE);
		label.setLayoutData(new GridData());
		label.setText("姓       名:");

		textName = new Text(group, SWT.BORDER);
		final GridData gd_textName = new GridData(SWT.LEFT, SWT.CENTER, true,
				false);
		gd_textName.widthHint = 398;
		textName.setLayoutData(gd_textName);

		final Group group_2 = new Group(container, SWT.NONE);
		final GridData gd_group_2 = new GridData(SWT.CENTER, SWT.CENTER, false,
				false);
		gd_group_2.heightHint = 41;
		gd_group_2.widthHint = 476;
		group_2.setLayoutData(gd_group_2);
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 2;
		group_2.setLayout(gridLayout_2);
		group_2.setText("根据联系人QQ搜索");

		final Label label_2 = new Label(group_2, SWT.NONE);
		label_2.setLayoutData(new GridData());
		label_2.setText("QQ  号码:");

		textQQNum = new Text(group_2, SWT.BORDER);
		final GridData gd_textQQNum = new GridData(SWT.LEFT, SWT.CENTER, true,
				false);
		gd_textQQNum.widthHint = 398;
		textQQNum.setLayoutData(gd_textQQNum);

		final Group group_1 = new Group(container, SWT.NONE);
		final GridData gd_group_1 = new GridData(SWT.CENTER, SWT.CENTER, false,
				false);
		gd_group_1.heightHint = 41;
		gd_group_1.widthHint = 476;
		group_1.setLayoutData(gd_group_1);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 2;
		group_1.setLayout(gridLayout_1);
		group_1.setText("根据联系人邮箱搜索");

		final Label label_1 = new Label(group_1, SWT.NONE);
		label_1.setLayoutData(new GridData());
		label_1.setText("邮箱地址:");

		textEmailAddress = new Text(group_1, SWT.BORDER);
		final GridData gd_textEmailAddress = new GridData(SWT.LEFT, SWT.CENTER,
				true, false);
		gd_textEmailAddress.widthHint = 398;
		textEmailAddress.setLayoutData(gd_textEmailAddress);

		final Group group_2_1 = new Group(container, SWT.NONE);
		final GridData gd_group_2_1 = new GridData(SWT.CENTER, SWT.CENTER,
				false, false);
		gd_group_2_1.heightHint = 41;
		gd_group_2_1.widthHint = 476;
		group_2_1.setLayoutData(gd_group_2_1);
		final GridLayout gridLayout_3 = new GridLayout();
		gridLayout_3.numColumns = 2;
		group_2_1.setLayout(gridLayout_3);
		group_2_1.setText("根据联系人电话号码搜索");

		final Label label_2_1 = new Label(group_2_1, SWT.NONE);
		label_2_1.setLayoutData(new GridData());
		label_2_1.setText("电话号码:");

		textPhoneNum = new Text(group_2_1, SWT.BORDER);
		final GridData gd_textPhoneNum = new GridData(SWT.LEFT, SWT.CENTER,
				true, false);
		gd_textPhoneNum.widthHint = 398;
		textPhoneNum.setLayoutData(gd_textPhoneNum);
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
		createButton(parent, IDialogConstants.OK_ID, "搜索", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "取消", false);
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(500, 375);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("搜索联系人资料");
	}

	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			search();
			this.close();
			return;
		}
		super.buttonPressed(buttonId);
	}

	private void search() {
		String name = textName.getText().trim();
		Hits hits=SearchIndex.searchUsrByName(name);
		List<Contactor> conts=new ArrayList<Contactor>();
		for(int i=0;i<hits.length();i++){
			try {
				Document d=hits.doc(i);
				Contactor contactor=new Contactor();
				contactor.setName(d.get("name"));
				contactor.setAddress(d.get("address"));
				contactor.setPostNum(d.get("postNum"));
				contactor.setId(d.get("id"));
				contactor.setNation(d.get("nation"));
				contactor.setQqNum(d.get("qq"));
				Field[] fields=d.getFields("phone");
				for(Field f:fields){
					PhoneNum phone=new PhoneNum();
					phone.setPhoneNum(f.stringValue());
					contactor.addPhoneNum(phone);
				}
				conts.add(contactor);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conts != null&&conts.size()>0) {
			if (editor != null) {
				LookOverUserEditorInput input = editor.getEditorInput();
				input.setCurrContactor(conts.get(0));
				editor.initEditor();
				window.getActivePage().activate(editor);
			} else {
				LookOverUserEditorInput input = new LookOverUserEditorInput();
				input.setCurrContactor(conts.get(0));
				try {
					window.getActivePage().openEditor(input,
							LookOverUserEditor.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
