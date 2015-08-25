package org.feifei.phonebook.adduser;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.feifei.phonebook.Activator;
import org.feifei.phonebook.adduser.dialogs.AddEmailsDialog;
import org.feifei.phonebook.adduser.dialogs.AddPhoneNumDialog;
import org.feifei.phonebook.base.BaseEditor;
import org.feifei.phonebook.dao.ContactorDAO;
import org.feifei.phonebook.model.Contactor;
import org.feifei.phonebook.model.Email;
import org.feifei.phonebook.model.PhoneNum;
import org.feifei.phonebook.util.Co;
import org.feifei.phonebook.welcome.AllContactorView;

import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;

public class AddUserEditor extends BaseEditor {

	private Table table_1;

	private Table table;

	private Text text_5;

	private Text text_4;

	private Text text_3;

	private Text text_2;

	private Text text_1;

	private Text text;

	private Label label;

	public static final String ID = "org.feifei.phonebook.adduser.AddUserEditor"; //$NON-NLS-1$

	private FormToolkit toolkit;

	/**
	 * Create contents of the editor part
	 * 
	 * @param parent
	 */
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		toolkit = new FormToolkit(container.getDisplay());

		final ScrolledForm scrolledForm = toolkit.createScrolledForm(container);
		final Composite body = scrolledForm.getBody();
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.marginLeft = 10;
		gridLayout_2.marginTop = 20;
		gridLayout_2.verticalSpacing = 30;
		body.setLayout(gridLayout_2);
		toolkit.paintBordersFor(body);

		label = new Label(body, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(255, 255, 255));
		final GridData gd_label = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		label.setLayoutData(gd_label);
		label.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
		toolkit.adapt(label, true, true);

		final Section section = toolkit.createSection(body, Section.TWISTIE
				| Section.EXPANDED | Section.TITLE_BAR);
		section.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		section.setText("基本信息");

		final Composite composite = toolkit.createComposite(section, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		composite.setLayout(gridLayout);
		toolkit.paintBordersFor(composite);
		section.setClient(composite);

		toolkit.createLabel(composite, "姓       名", SWT.NONE);

		text = toolkit.createText(composite, null, SWT.NONE);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		toolkit.createLabel(composite, "年     龄", SWT.NONE);

		text_1 = toolkit.createText(composite, null, SWT.NONE);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		toolkit.createLabel(composite, "民       族", SWT.NONE);

		text_2 = toolkit.createText(composite, null, SWT.NONE);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		toolkit.createLabel(composite, "QQ号码", SWT.NONE);

		text_3 = toolkit.createText(composite, null, SWT.NONE);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		toolkit.createLabel(composite, "通信地址", SWT.NONE);

		text_4 = toolkit.createText(composite, null, SWT.NONE);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		toolkit.createLabel(composite, "邮     编", SWT.NONE);

		text_5 = toolkit.createText(composite, null, SWT.NONE);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		final Section section_1 = toolkit.createSection(body, Section.TWISTIE
				| Section.EXPANDED | Section.TITLE_BAR);
		final GridData gd_section_1 = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		gd_section_1.heightHint = 285;
		section_1.setLayoutData(gd_section_1);
		section_1.setText("联系方式");

		final Composite composite_1 = toolkit.createComposite(section_1,
				SWT.NONE);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.verticalSpacing = 10;
		gridLayout_1.numColumns = 2;
		composite_1.setLayout(gridLayout_1);
		toolkit.paintBordersFor(composite_1);
		section_1.setClient(composite_1);

		toolkit.createLabel(composite_1, "电子邮件列表", SWT.NONE);

		final Button button = toolkit.createButton(composite_1, "添加", SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddEmailsDialog dialog = new AddEmailsDialog(getSite()
						.getShell(), AddUserEditor.this);
				dialog.open();
			}
		});
		button.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

		table = toolkit.createTable(composite_1, SWT.NONE);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		final GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true,
				2, 1);
		gd_table.heightHint = 51;
		table.setLayoutData(gd_table);

		final TableColumn newColumnTableColumn = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn.setWidth(100);
		newColumnTableColumn.setText("地址");

		final TableColumn newColumnTableColumn_1 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_1.setWidth(100);
		newColumnTableColumn_1.setText("类型");

		final TableColumn newColumnTableColumn_2 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_2.setWidth(257);
		newColumnTableColumn_2.setText("备注");

		toolkit.createLabel(composite_1, "电话号码", SWT.NONE);

		final Button button_1 = toolkit.createButton(composite_1, "添加",
				SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				AddPhoneNumDialog dialog = new AddPhoneNumDialog(getSite()
						.getShell(), AddUserEditor.this);
				dialog.open();
			}
		});
		button_1
				.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

		table_1 = toolkit.createTable(composite_1, SWT.NONE);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		final GridData gd_table_1 = new GridData(SWT.FILL, SWT.FILL, true,
				true, 2, 1);
		gd_table_1.heightHint = 51;
		table_1.setLayoutData(gd_table_1);

		final TableColumn newColumnTableColumn_3 = new TableColumn(table_1,
				SWT.NONE);
		newColumnTableColumn_3.setWidth(100);
		newColumnTableColumn_3.setText("号码");

		final TableColumn newColumnTableColumn_4 = new TableColumn(table_1,
				SWT.NONE);
		newColumnTableColumn_4.setWidth(100);
		newColumnTableColumn_4.setText("类型");

		final TableColumn newColumnTableColumn_5 = new TableColumn(table_1,
				SWT.NONE);
		newColumnTableColumn_5.setWidth(100);
		newColumnTableColumn_5.setText("备注");

		final Composite buttonBar = new Composite(body, SWT.NONE);
		buttonBar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		final GridLayout gridLayout_3 = new GridLayout();
		gridLayout_3.numColumns = 2;
		buttonBar.setLayout(gridLayout_3);
		toolkit.adapt(buttonBar);

		final Button button_2 = new Button(buttonBar, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addContactor();
			}
		});
		final GridData gd_button_2 = new GridData(SWT.CENTER, SWT.CENTER, true,
				false);
		gd_button_2.widthHint = 86;
		button_2.setLayoutData(gd_button_2);
		toolkit.adapt(button_2, true, true);
		button_2.setText("添加");

		final Button button_3 = new Button(buttonBar, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean isCancel = MessageDialog.openConfirm(getSite()
						.getShell(), "取消添加", "您确定取消添加吗？");
				if (isCancel) {
					IEditorPart part = getSite().getWorkbenchWindow()
							.getActivePage().getActiveEditor();
					if (part instanceof AddUserEditor) {
						getSite().getWorkbenchWindow().getActivePage()
								.closeEditor(part, false);
					}
				}
			}
		});
		final GridData gd_button_3 = new GridData(SWT.CENTER, SWT.CENTER, true,
				false);
		gd_button_3.widthHint = 86;
		button_3.setLayoutData(gd_button_3);
		toolkit.adapt(button_3, true, true);
		button_3.setText("取消");
		// scrolledForm.setText("New ScrolledForm");
		//
	}

	public void addEmail(final Email email) {
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(Co.getStr(email.getEmailAddress()));
		tableItem.setText(1, Co.getStr(email.getType()));
		tableItem.setText(2, Co.getStr(email.getComment()));
		tableItem.setData(email);
	}

	public void addPhoneNum(final PhoneNum phoneNum) {
		TableItem tableItem = new TableItem(table_1, SWT.NONE);
		tableItem.setText(Co.getStr(phoneNum.getPhoneNum()));
		tableItem.setText(1, Co.getStr(phoneNum.getType()));
		tableItem.setText(2, Co.getStr(phoneNum.getComment()));
		tableItem.setData(phoneNum);
	}

	private void addContactor() {
		if (text.getText().trim().equals("")) {
			label.setText("联系人的姓名不能为空！");
			text.setFocus();
			return;
		}
		Contactor contactor = new Contactor();
		contactor.setName(text.getText().trim());
		if (!text_1.getText().trim().equals("")) {
			contactor.setAge(new Integer(text_1.getText().trim()));
		}
		if (!text_2.getText().trim().equals("")) {
			contactor.setNation(text_2.getText().trim());
		}
		if (!text_3.getText().trim().equals("")) {
			contactor.setQqNum(text_3.getText().trim());
		}
		if (!text_4.getText().trim().equals("")) {
			contactor.setAddress(text_4.getText().trim());
		}
		if (!text_5.getText().trim().equals("")) {
			contactor.setPostNum(text_5.getText().trim());
		}
		TableItem[] tableItems = table.getItems();
		if (tableItems.length > 0) {
			for (int i = 0; i < tableItems.length; i++) {
				Email email = (Email) tableItems[i].getData();
				contactor.addEmail(email);
			}
		}
		tableItems = table_1.getItems();
		if (tableItems.length > 0) {
			for (int j = 0; j < tableItems.length; j++) {
				PhoneNum phoneNum = (PhoneNum) tableItems[j].getData();
				contactor.addPhoneNum(phoneNum);
			}
		}
		try {
			ContactorDAO.createContactor(contactor);
			MessageDialog.openInformation(null, "添加成功", "联系人添加成功！");
			addContactorTableItem(contactor);
			clear();
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openError(null, "添加失败", "联系人添加失败！");
		}
	}

	private void addContactorTableItem(final Contactor contactor) {
		AllContactorView view = null;
		IViewPart part = getSite().getWorkbenchWindow().getActivePage()
				.findView(AllContactorView.ID);
		if (part instanceof AllContactorView) {
			view = (AllContactorView) part;
			Table table = view.getTable();
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(contactor.getName());
			tableItem.setImage(0, ResourceManager.getPluginImage(Activator
					.getDefault(), "icons/tableItem.png"));
			tableItem.setData(contactor);
		}
	}

	private void clear() {
		text.setText("");
		text_1.setText("");
		text_2.setText("");
		text_3.setText("");
		text_4.setText("");
		text_5.setText("");
		table.removeAll();
		table_1.removeAll();
	}
}
