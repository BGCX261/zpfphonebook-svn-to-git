package org.feifei.phonebook.favorate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.base.BaseEditor;

public class XuncangluEditor extends BaseEditor {

	public static final String ID = "org.feifei.phonebook.favorate.XuncangluEditor"; //$NON-NLS-1$
	
	private XuncangluEditorInput editInput;

	public XuncangluEditorInput getEditInput() {
		return editInput;
	}


	public void setEditInput(XuncangluEditorInput editInput) {
		this.editInput = editInput;
	}


	/**
	 * Create contents of the editor part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		this.setPartName(editInput.getName());
		container.setLayout(new FillLayout());
		Browser browser=new Browser(container,SWT.NONE);
		browser.setUrl(editInput.getUrl());
		browser.forward();
		//
	}


	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
		setEditInput((XuncangluEditorInput) input);
	}

}
