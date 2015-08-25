package org.feifei.phonebook.actions;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.feifei.phonebook.base.BaseAction;
import org.feifei.phonebook.dao.ContactorDAO;
import org.feifei.phonebook.model.Contactor;
import org.feifei.phonebook.welcome.AllContactorView;

/**
 * ɾ����ϵ��
 * 
 * @author ��ƽ��
 * 
 */
public class DelContactorAction extends BaseAction {
	private IWorkbenchWindow window;

	public DelContactorAction(IWorkbenchWindow pWindow) {
		window = pWindow;
		setId(getClass().getName());
		setText("ɾ����ϵ��");
	}

	public void run() {
		Table table = getTable();
		if(table==null){
			return;
		}
		if (table.getItemCount() <= 0) {
			MessageDialog.openInformation(null, "ѡ��", "ѡ������ɾ������ϵ�ˣ�");
			return;
		}
		TableItem item = table.getSelection()[0];
		Contactor contactor = (Contactor) item.getData();
		boolean isCancel = MessageDialog.openConfirm(null, "ȷ��ɾ��",
				"��ȷ��ɾ������ϵ����");
		if (!isCancel) {
			return;
		}
		try {
			ContactorDAO.deleteContactor(contactor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		item.dispose();
	}

	private Table getTable() {
		AllContactorView view = null;
		IViewPart part = window.getActivePage().findView(AllContactorView.ID);
		if (part instanceof AllContactorView) {
			view = (AllContactorView) part;
			return view.getTable();
		}
		return null;
	}
}
