package org.feifei.phonebook.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.base.BaseAction;
import org.feifei.phonebook.base.BaseEditor;
import org.feifei.phonebook.model.Contactor;
import org.feifei.phonebook.updateUser.UpdateUserEditor;
import org.feifei.phonebook.updateUser.UpdateUserEditorInput;
import org.feifei.phonebook.welcome.AllContactorView;

public class UpdateContactorAction extends BaseAction {
	private IWorkbenchWindow window;

	private AllContactorView view;

	public UpdateContactorAction(IWorkbenchWindow pWindow) {
		this.window = pWindow;
		this.setId(this.getClass().getName());
		setText("修改联系人");
	}

	public void run() {
		view = (AllContactorView) window.getActivePage().findView(
				AllContactorView.ID);
		Table table = view.getTable();
		if (table.getItemCount() <= 0) {
			MessageDialog.openInformation(null, "选择", "请选择待修改的联系人！");
			return;
		}
		Contactor contactor = (Contactor) table.getSelection()[0].getData();
		boolean isUpdated = beforeOpenModifyEditor(contactor);
		if (isUpdated) {
			UpdateUserEditorInput input = new UpdateUserEditorInput();
			input.setContactor(contactor);
			try {
				window.getActivePage().openEditor(input, UpdateUserEditor.ID);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean beforeOpenModifyEditor(Contactor contactor) {
		IEditorReference[] references = window.getActivePage()
				.getEditorReferences();
		for (int i = 0; i < references.length; i++) {
			BaseEditor baseEditor = (BaseEditor) references[i].getEditor(false);
			if (baseEditor instanceof UpdateUserEditor) {
				UpdateUserEditor editor = (UpdateUserEditor) baseEditor;
				window.getActivePage().activate(editor);
				UpdateUserEditorInput input = editor.getEditorInput();
				input.setContactor(contactor);
				editor.initEditor();
				return false;
			}
		}
		return true;
	}
}
