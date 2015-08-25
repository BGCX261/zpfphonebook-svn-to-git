package org.feifei.phonebook;

import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.WorkbenchPlugin;

import com.swtdesigner.ResourceManager;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor{

	public ApplicationWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		configurer.setInitialSize(new Point(toolkit.getScreenSize().width,
				toolkit.getScreenSize().height));
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true); 
		configurer.setShowPerspectiveBar(true);
		configurer.setTitle("联系人列表");
	}

	public void postWindowCreate() {
		WorkbenchPlugin.DEBUG = true;
		final IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.getWindow().getShell().setMaximized(true);
		configurer.getWindow().getShell().forceFocus();
		final Menu menu=new Menu(configurer.getWindow().getShell());
		MenuItem mi1=new MenuItem(menu,SWT.PUSH);
		mi1.setText("退出");
		mi1.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		
		createMenuItem(menu);
		
		final Tray tray = Display.getCurrent().getSystemTray();
		TrayItem item=new TrayItem(tray,SWT.NONE);
		item.setToolTipText("超强电话本"); 
		item.setImage(ResourceManager.getPluginImage(Activator
				.getDefault(), "icons/tableItem.png"));
		item.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(configurer.getWindow().getShell().isVisible()){
					configurer.getWindow().getShell().setVisible(false);
				}else{
					configurer.getWindow().getShell().setVisible(true);
					configurer.getWindow().getShell().forceActive();
				}
			}
		});
		item.addListener(SWT.MenuDetect, new Listener(){

			public void handleEvent(Event event) {
				menu.setLocation(Display.getCurrent().getCursorLocation());
				menu.setVisible(true);
			}
			
		});
	}

	private void createMenuItem(Menu menu) {
		try {
			FileInputStream fis=new FileInputStream(new File("config.txt"));
			InputStreamReader isr=new InputStreamReader(fis);
			BufferedReader br=new BufferedReader(isr);
			String str=null;
			while((str=br.readLine())!=null){
				final String[] parameters=str.split(" ");
				final MenuItem mi2=new MenuItem(menu,SWT.PUSH);
				mi2.setText(parameters[0]);
				mi2.setData("path",parameters[1]);
				mi2.addSelectionListener(new SelectionAdapter(){
					@Override
					public void widgetSelected(SelectionEvent e) {
						try {
							String path=(String) mi2.getData("path");
							if(path.lastIndexOf(".bat")!=-1){
								Runtime.getRuntime().exec(path);
							}else{
							Runtime.getRuntime().exec("explorer "+path);
							}
						} catch (IOException e1) {
							
						}
					}
				});
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
