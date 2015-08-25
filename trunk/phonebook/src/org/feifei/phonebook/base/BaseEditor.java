package org.feifei.phonebook.base;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class BaseEditor extends EditorPart {

	public static final String ID = "org.feifei.phonebook.base.BaseEditor"; //$NON-NLS-1$

	/**
	 * Create contents of the editor part
	 * 
	 * @param parent
	 */
	public void createPartControl(Composite parent) {
	}

	public void setFocus() {
		// Set the focus
	}

	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	public void doSaveAs() {
		// Do the Save As operation
	}

	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}

	public boolean isDirty() {
		return false;
	}

	public boolean isSaveAsAllowed() {
		return false;
	}

}
