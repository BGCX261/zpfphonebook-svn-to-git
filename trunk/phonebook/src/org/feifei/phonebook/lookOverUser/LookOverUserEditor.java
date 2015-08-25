package org.feifei.phonebook.lookOverUser;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.feifei.phonebook.base.BaseEditor;
import org.feifei.phonebook.model.Contactor;
import org.feifei.phonebook.model.Email;
import org.feifei.phonebook.model.PhoneNum;
import org.feifei.phonebook.util.Co;

public class LookOverUserEditor extends BaseEditor {

	private Table tablePhoneNum;

	private Table tableEmails;

	private Text txtPostNum;

	private Text txtAddress;

	private Text txtQQNum;

	private Text txtNation;

	private Text txtAge;

	private Text txtName;

	public static final String ID = "org.feifei.phonebook.lookOverUser.LookOverUserEditor"; //$NON-NLS-1$

	private FormToolkit toolkit;

	private LookOverUserEditorInput editorInput;

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
		gridLayout_2.verticalSpacing = 30;
		body.setLayout(gridLayout_2);
		toolkit.paintBordersFor(body);

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

		txtName = toolkit.createText(composite, null, SWT.READ_ONLY);
		final GridData gd_txtName = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		txtName.setLayoutData(gd_txtName);

		toolkit.createLabel(composite, "年     龄", SWT.NONE);

		txtAge = toolkit.createText(composite, null, SWT.READ_ONLY);
		final GridData gd_txtAge = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		txtAge.setLayoutData(gd_txtAge);

		toolkit.createLabel(composite, "民       族", SWT.NONE);

		txtNation = toolkit.createText(composite, null, SWT.READ_ONLY);
		final GridData gd_txtNation = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		txtNation.setLayoutData(gd_txtNation);

		toolkit.createLabel(composite, "QQ号码", SWT.NONE);

		txtQQNum = toolkit.createText(composite, null, SWT.READ_ONLY);
		txtQQNum.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		toolkit.createLabel(composite, "通信地址", SWT.NONE);

		txtAddress = toolkit.createText(composite, null, SWT.READ_ONLY);
		final GridData gd_txtAddress = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		txtAddress.setLayoutData(gd_txtAddress);

		toolkit.createLabel(composite, "邮     编", SWT.NONE);

		txtPostNum = toolkit.createText(composite, null, SWT.READ_ONLY);
		final GridData gd_txtPostNum = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		txtPostNum.setLayoutData(gd_txtPostNum);

		final Section section_1 = toolkit.createSection(body, Section.TWISTIE
				| Section.EXPANDED | Section.TITLE_BAR);
		final GridData gd_section_1 = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		gd_section_1.heightHint = 276;
		section_1.setLayoutData(gd_section_1);
		section_1.setText("联系方式");

		final Composite composite_1 = toolkit.createComposite(section_1,
				SWT.NONE);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.verticalSpacing = 10;
		composite_1.setLayout(gridLayout_1);
		toolkit.paintBordersFor(composite_1);
		section_1.setClient(composite_1);

		toolkit.createLabel(composite_1, "电子邮件列表", SWT.NONE);

		tableEmails = toolkit.createTable(composite_1, SWT.FULL_SELECTION);
		tableEmails.setLinesVisible(true);
		tableEmails.setHeaderVisible(true);
		final GridData gd_tableEmails = new GridData(SWT.FILL, SWT.FILL, true,
				true);
		gd_tableEmails.heightHint = 51;
		tableEmails.setLayoutData(gd_tableEmails);

		final TableColumn newColumnTableColumn = new TableColumn(tableEmails,
				SWT.NONE);
		newColumnTableColumn.setWidth(100);
		newColumnTableColumn.setText("地址");

		final TableColumn newColumnTableColumn_1 = new TableColumn(tableEmails,
				SWT.NONE);
		newColumnTableColumn_1.setWidth(100);
		newColumnTableColumn_1.setText("类型");

		final TableColumn newColumnTableColumn_2 = new TableColumn(tableEmails,
				SWT.NONE);
		newColumnTableColumn_2.setWidth(254);
		newColumnTableColumn_2.setText("备注");

		toolkit.createLabel(composite_1, "电话号码", SWT.NONE);

		tablePhoneNum = toolkit.createTable(composite_1, SWT.FULL_SELECTION);
		tablePhoneNum.setLinesVisible(true);
		tablePhoneNum.setHeaderVisible(true);
		final GridData gd_tablePhoneNum = new GridData(SWT.FILL, SWT.FILL,
				true, true);
		gd_tablePhoneNum.heightHint = 51;
		tablePhoneNum.setLayoutData(gd_tablePhoneNum);

		final TableColumn newColumnTableColumn_3 = new TableColumn(
				tablePhoneNum, SWT.NONE);
		newColumnTableColumn_3.setWidth(100);
		newColumnTableColumn_3.setText("号码");

		final TableColumn newColumnTableColumn_4 = new TableColumn(
				tablePhoneNum, SWT.NONE);
		newColumnTableColumn_4.setWidth(100);
		newColumnTableColumn_4.setText("类型");

		final TableColumn newColumnTableColumn_5 = new TableColumn(
				tablePhoneNum, SWT.NONE);
		newColumnTableColumn_5.setWidth(100);
		newColumnTableColumn_5.setText("备注");
		initEditor();
		// scrolledForm.setText("New ScrolledForm");
		//
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		setEditorInput((LookOverUserEditorInput) input);
	}

	/**
	 * 初始化页面
	 * 
	 */
	public void initEditor() {
		Contactor currContactor = getEditorInput().getCurrContactor();
		txtName.setText(Co.getStr(currContactor.getName()));
		txtAge.setText(Co.getStr(currContactor.getAge()));
		txtNation.setText(Co.getStr(currContactor.getNation()));
		txtAddress.setText(Co.getStr(currContactor.getAddress()));
		txtQQNum.setText(Co.getStr(currContactor.getQqNum()));
		txtPostNum.setText(Co.getStr(currContactor.getPostNum()));

		initEmails();
		initPhoneNums();
	}

	/**
	 * 初始化邮件列表
	 */
	public void initEmails() {
		tableEmails.removeAll();
		Set emails = this.editorInput.getCurrContactor().getEmails();
		if (emails == null) {
			return;
		}
		Iterator itEmail = emails.iterator();
		while (itEmail.hasNext()) {
			Email tempEmail = (Email) itEmail.next();
			TableItem tableItem = new TableItem(tableEmails, SWT.NONE);
			tableItem.setText(Co.getStr(tempEmail.getEmailAddress()));
			tableItem.setText(1, Co.getStr(tempEmail.getType()));
			tableItem.setText(2, Co.getStr(tempEmail.getComment()));
			tableItem.setData(tempEmail);
		}
	}

	/**
	 * 初始化电话号码
	 */
	public void initPhoneNums() {
		tablePhoneNum.removeAll();
		Set phoneNum = this.editorInput.getCurrContactor().getPhoneNums();
		if (phoneNum == null) {
			return;
		}
		TableItem tableItem;
		Iterator itPhoneNum = phoneNum.iterator();
		while (itPhoneNum.hasNext()) {
			PhoneNum phone = (PhoneNum) itPhoneNum.next();
			tableItem = new TableItem(tablePhoneNum, SWT.NONE);
			tableItem.setText(Co.getStr(phone.getPhoneNum()));
			tableItem.setText(1, Co.getStr(phone.getType()));
			tableItem.setText(2, Co.getStr(phone.getComment()));
			tableItem.setData(phone);
		}
	}

	public LookOverUserEditorInput getEditorInput() {
		return editorInput;
	}

	public void setEditorInput(LookOverUserEditorInput editorInput) {
		this.editorInput = editorInput;
	}

}
