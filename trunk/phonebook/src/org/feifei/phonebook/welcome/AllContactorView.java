package org.feifei.phonebook.welcome;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.feifei.phonebook.Activator;
import org.feifei.phonebook.ApplicationActionBarAdvisor;
import org.feifei.phonebook.base.BaseEditor;
import org.feifei.phonebook.dao.ContactorDAO;
import org.feifei.phonebook.lookOverUser.LookOverUserEditor;
import org.feifei.phonebook.lookOverUser.LookOverUserEditorInput;
import org.feifei.phonebook.model.Contactor;

import com.swtdesigner.ResourceManager;

/**
 * 在软件左边的用户列表
 * 
 * @author 赵平飞
 * 
 */
public class AllContactorView extends ViewPart {

	private Table table;

	public static final String ID = "org.feifei.phonebook.welcome.AllContactorView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());

		table = new Table(container, SWT.BORDER);
		table.setLinesVisible(false);
		table.setHeaderVisible(false);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				if (table.getSelectionCount() == 0) {
					return;
				}
				Contactor currentContactor = (Contactor) table.getSelection()[0]
						.getData();
				boolean isContinued = beforeOpenNewEditor(currentContactor);
				if (isContinued) {
					LookOverUserEditorInput input = new LookOverUserEditorInput();
					input.setCurrContactor(currentContactor);
					try {
						getSite().getWorkbenchWindow().getActivePage()
								.openEditor(input, LookOverUserEditor.ID);
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		final TableColumn newColumnTableColumn = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn.setWidth(180);
		newColumnTableColumn.setText("New column");
		//
		createActions();
		initializeToolBar();
		initializeMenu();
		try {
			initTable();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		initContextMenu();

		openWelcomeEditor();
	}

	/**
	 * 打开欢迎界面
	 * 
	 */
	public void openWelcomeEditor() {
		WelcomeEditorInput input = new WelcomeEditorInput();
		try {
			getSite().getWorkbenchWindow().getActivePage().openEditor(input,
					WelcomeEditor.ID);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	public void initTable() throws Exception {
		List allContactors = getContactorInfo();
		Iterator contactorIt = allContactors.iterator();
		TableItem item;
		while (contactorIt.hasNext()) {
			Contactor tempContactor = (Contactor) contactorIt.next();
			item = new TableItem(table, SWT.NONE);
			item.setImage(0, ResourceManager.getPluginImage(Activator
					.getDefault(), "icons/tableItem.png"));
			item.setText(tempContactor.getName());
			item.setData(tempContactor);
		}
		if (table.getItemCount() > 0) {
			table.select(0);
		}
	}

	/**
	 * 得到联系人信息
	 * 
	 * @throws Exception
	 */
	public List getContactorInfo() throws Exception {
		// List<Contactor> l = new ArrayList<Contactor>();
		// Contactor u;
		// for (int i = 0; i < 20; i++)
		// {
		// u = new Contactor();
		// u.setName("fei" + i);
		// u.setAge(20 + i);
		// l.add(u);
		// }
		// return l;
		List list = ContactorDAO.getAllContactor();
		return list;
	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {

	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	private boolean beforeOpenNewEditor(Contactor contactor) {
		IEditorReference[] references = getSite().getWorkbenchWindow()
				.getActivePage().getEditorReferences();
		for (int i = 0; i < references.length; i++) {
			BaseEditor baseEditor = (BaseEditor) references[i].getEditor(false);
			if (baseEditor instanceof LookOverUserEditor) {
				LookOverUserEditor editor = (LookOverUserEditor) baseEditor;
				getSite().getWorkbenchWindow().getActivePage().activate(editor);
				LookOverUserEditorInput input = editor.getEditorInput();
				input.setCurrContactor(contactor);
				editor.initEditor();
				return false;
			}
		}
		return true;
	}

	private void initContextMenu() {
		MenuManager manager = new MenuManager("菜单");
		manager.setRemoveAllWhenShown(true);

		manager.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(final IMenuManager manager) {
				AllContactorView.this.fillContextMenu(manager);
			}
		});
		Menu menu = manager.createContextMenu(table);
		table.setMenu(menu);
	}

	private void fillContextMenu(final IMenuManager manager) {
		manager.add(ApplicationActionBarAdvisor.getAddUserAction());
		manager.add(ApplicationActionBarAdvisor.getRemoveContactorAction());
		manager.add(ApplicationActionBarAdvisor.getUpdateContactorAction());
	}
}
