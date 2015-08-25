package org.feifei.phonebook.emails;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.Activator;
import org.feifei.phonebook.base.BaseEditor;

import com.swtdesigner.ResourceManager;
import com.swtdesigner.SWTResourceManager;

public class EmailClient extends BaseEditor {

	private Text text_3;
	private Text txtpath;

	private Text text_2;

	private Text text_1;

	private Text text;

	public static final String ID = "org.feifei.phonebook.emails.EmailClient"; //$NON-NLS-1$

	/**
	 * Create contents of the editor part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		container.setLayout(gridLayout);
		container.setBackground(SWTResourceManager.getColor(255, 255, 255));

		final CLabel label_4 = new CLabel(container, SWT.NONE);
		label_4.setLayoutData(new GridData());
		label_4.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label_4.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/email2.png"));
		label_4.setText("收件人");

		text = new Text(container, SWT.BORDER);
		text
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
						2, 1));

		final Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("抄送");

		text_1 = new Text(container, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2,
				1));

		final Label label_2 = new Label(container, SWT.NONE);
		label_2.setText("主题");

		text_2 = new Text(container, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2,
				1));

		final Label label_3 = new Label(container, SWT.NONE);
		label_3.setText("附件");

		txtpath = new Text(container, SWT.BORDER);
		final GridData gd_txtpath = new GridData(SWT.FILL, SWT.CENTER, true,
				false);
		txtpath.setLayoutData(gd_txtpath);

		final Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				FileDialog f = new FileDialog(button.getShell(), SWT.NONE);
				String str = f.open();
				if (str != null && str.length() != 0) {
					txtpath.setText(str);
				}
			}
		});
		button.setText("选取");
		new Label(container, SWT.NONE);

		text_3 = new Text(container, SWT.WRAP | SWT.BORDER);
		final GridData gd_text_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_text_3.widthHint = 887;
		gd_text_3.heightHint = 444;
		text_3.setLayoutData(gd_text_3);
		//
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}

}
