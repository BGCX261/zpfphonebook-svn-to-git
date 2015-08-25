package org.feifei.phonebook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.favorate.XuncangluEditor;
import org.feifei.phonebook.favorate.XuncangluEditorInput;
/**
 * 
 * @author shichong
 *
 * @date 2009-7-9
 * @version $Id:XuncangluAction.java 2009-7-9 ÉÏÎç10:17:21  shichong
 */
public class XuncangluAction extends Action {
	IWorkbenchWindow window;
	
	public XuncangluAction(IWorkbenchWindow window) {
		this.window = window;
		setId(getClass().getName());
		setText("Ñ°²ØÂ·");
	}

	@Override
	public void run() {
		XuncangluEditorInput input=new XuncangluEditorInput();
		input.setUrl("www.xuncanglu.com");
		try {
			this.window.getActivePage().openEditor(input, XuncangluEditor.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
