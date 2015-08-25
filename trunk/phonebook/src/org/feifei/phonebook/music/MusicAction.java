package org.feifei.phonebook.music;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

public class MusicAction extends Action {
	private IWorkbenchWindow window;

	public MusicAction(IWorkbenchWindow pWindow) {
		setId(getClass().getName());
		setText("´ò¿ª²¥·ÅÆ÷");
		this.window = pWindow;
	}

	public void run() {
		MusicEditorInput input = new MusicEditorInput();
		try {
			window.getActivePage().openEditor(input, MusicEditor.ID);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
