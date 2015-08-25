package org.feifei.phonebook.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.base.BaseEditorInput;
import org.feifei.phonebook.favorate.MyfavoriteEditor;
/**
 * 
 * @author shichong
 *
 * @date 2009-7-9
 * @version $Id:FavoriteAction.java 2009-7-9 ÏÂÎç05:39:13  shichong
 */
public class FavoriteAction extends Action {
	
	IWorkbenchWindow window;
	
	public FavoriteAction(IWorkbenchWindow window){
		this.window=window;
		setId(getClass().getName());
		setText("ÊÕ²Ø¼Ð");
	}

	@Override
	public void run() {
		BaseEditorInput input=new BaseEditorInput();
		try {
			window.getActivePage().openEditor(input, MyfavoriteEditor.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
