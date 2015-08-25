package org.feifei.phonebook.leftMenu;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.part.ViewPart;
import org.feifei.phonebook.Activator;

import com.swtdesigner.SWTResourceManager;

public class LeftMenu extends ViewPart {

	private Composite container;
	private Browser browser;
	public static final String ID = "org.feifei.phonebook.leftMenu.LeftMenu"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		container.setBackground(SWTResourceManager.getColor(255, 255, 255));

		/*final Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(255, 255, 255));
		label.setText("自定义视图");
		label.setBounds(10, 73, 61, 13);

		txtMyLeftUrl = new Text(container, SWT.BORDER);
		txtMyLeftUrl.setBounds(77, 70, 195, 25);

		final Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if(!txtMyLeftUrl.getText().trim().equals("")){
					String url=txtMyLeftUrl.getText();
					for(Control c:container.getChildren()){
						c.dispose();
					}
					container.setLayout(new FillLayout());
					Browser b=new Browser(container,SWT.NONE);
					container.layout(true);
					b.setUrl(url);
					b.forward();
					
				}
			}
		});
		
		button.setText("保存");
		button.setBounds(279, 73, 44, 23);*/
		//
		createActions();
		initializeToolBar();
		initializeMenu();
		initLeftMenu();
	}

	private void initLeftMenu() {
		IEclipsePreferences preferences = new ConfigurationScope().getNode(Activator.PLUGIN_ID);
		String url=preferences.get("URL", "www.g.cn");
		browser=new Browser(container,SWT.NONE);
		browser.setUrl(url);
		browser.forward();
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
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		RefreshAction refresh=new RefreshAction(getSite());
		toolbarManager.add(refresh);
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
	
	public class RefreshAction extends Action {

		IWorkbenchSite site;
		public RefreshAction(IWorkbenchSite site) {
			this.site=site;
			setId(getClass().getName());
			setText("刷新");
		}
		
		public void run() {
			Browser b=LeftMenu.this.getBrowser();
			IEclipsePreferences preferences = new ConfigurationScope().getNode(Activator.PLUGIN_ID);
			String url=preferences.get("URL", "www.g.cn");
			b.setUrl(url);
			b.forward();			
		}
		
	}

	public Browser getBrowser() {
		return browser;
	}

}
