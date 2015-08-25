package org.feifei.phonebook.welcome;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.Activator;
import org.feifei.phonebook.base.BaseEditor;

import com.swtdesigner.ResourceManager;

public class WelcomeEditor extends BaseEditor {

	public static final String ID = "org.feifei.phonebook.welcome.WelcomeEditor";

	/**
	 * Create contents of the editor part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		final Composite composite = new Composite(container, SWT.NONE);
		composite.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		composite.setLayout(new GridLayout());

		final Group group = new Group(composite, SWT.NONE);
		group.setBackgroundImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/welcome.jpg"));
		final GridData gd_group = new GridData(SWT.CENTER, SWT.CENTER, true,
				true);
		gd_group.heightHint = 519;
		gd_group.widthHint = 543;
		group.setLayoutData(gd_group);
		//
	}

	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}

}
