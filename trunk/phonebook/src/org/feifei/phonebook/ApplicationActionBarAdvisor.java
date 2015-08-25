package org.feifei.phonebook;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.feifei.phonebook.actions.AddUserAction;
import org.feifei.phonebook.actions.DelContactorAction;
import org.feifei.phonebook.actions.FavoriteAction;
import org.feifei.phonebook.actions.OpenEmailClient;
import org.feifei.phonebook.actions.OpenWelcomeAction;
import org.feifei.phonebook.actions.SearchContactorAction;
import org.feifei.phonebook.actions.UpdateContactorAction;
import org.feifei.phonebook.actions.XuncangluAction;
import org.feifei.phonebook.music.MusicAction;
import org.feifei.phonebook.welcome.ExitAction;

import com.swtdesigner.ResourceManager;

/**
 * action
 * 
 * @author 赵平飞
 * 
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private static IAction exitAction;

	private static IAction addUserAction;

	private static IAction musicAction;

	private static IAction helpAction;

	private static IAction openWelcomeAction;

	private static IAction removeContactorAction;

	private static IAction updateContactorAction;

	private static IAction searchContactorAction;

	private static IAction emailAction;
	
	private static IAction xuncangluAction;
	
	private static IAction favoriteAction;
	
	private static IAction openPreferenceAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		exitAction = new ExitAction(window);
		register(exitAction);

		addUserAction = new AddUserAction(window);
		addUserAction
				.setImageDescriptor(ResourceManager.getPluginImageDescriptor(
						Activator.getDefault(), "icons/+.png"));
		register(addUserAction);

		musicAction = new MusicAction(window);
		register(musicAction);

		helpAction = ActionFactory.HELP_CONTENTS.create(window);
		helpAction.setText("帮助");

		openWelcomeAction = new OpenWelcomeAction(window);
		register(openWelcomeAction);

		removeContactorAction = new DelContactorAction(window);
		register(removeContactorAction);

		updateContactorAction = new UpdateContactorAction(window);
		register(updateContactorAction);

		searchContactorAction = new SearchContactorAction(window);
		register(searchContactorAction);

		emailAction = new OpenEmailClient(window);
		register(emailAction);
		
		xuncangluAction=new XuncangluAction(window);
		register(xuncangluAction);
		
		favoriteAction=new FavoriteAction(window);
		register(favoriteAction);
		
		openPreferenceAction=ActionFactory.PREFERENCES.create(window);
		openPreferenceAction.setText("首选项");
		register(openPreferenceAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileManager = new MenuManager("文件");
		fileManager.add(exitAction);
		menuBar.add(fileManager);

		MenuManager contactManager = new MenuManager("联系人管理");
		contactManager.add(addUserAction);
		// contactManager.add(musicAction);
		contactManager.add(updateContactorAction);
		contactManager.add(removeContactorAction);
		menuBar.add(contactManager);

		MenuManager others = new MenuManager("杂七杂八");
		others.add(emailAction);
		menuBar.add(others);

		MenuManager searchManager = new MenuManager("搜索");
		searchManager.add(searchContactorAction);
		menuBar.add(searchManager);

		MenuManager musicManager = new MenuManager("娱乐");
		musicManager.add(musicAction);
		musicManager.add(xuncangluAction);
		musicManager.add(favoriteAction);
		menuBar.add(musicManager);
		
		MenuManager windowManage=new MenuManager("窗口");
		windowManage.add(openPreferenceAction);
		menuBar.add(windowManage);

		MenuManager helpManager = new MenuManager("帮助");
		helpManager.add(helpAction);
		helpManager.add(openWelcomeAction);
		menuBar.add(helpManager);
	}

	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager contactorManager = new ToolBarManager(SWT.NONE
				| SWT.RIGHT);
		contactorManager.add(addUserAction);
		contactorManager.add(updateContactorAction);
		contactorManager.add(removeContactorAction);
		coolBar.add(contactorManager);
		super.fillCoolBar(coolBar);
	}

	public static IAction getAddUserAction() {
		return addUserAction;
	}

	public static void setAddUserAction(final IAction addUserAction) {
		ApplicationActionBarAdvisor.addUserAction = addUserAction;
	}

	public static IAction getExitAction() {
		return exitAction;
	}

	public static void setExitAction(IAction exitAction) {
		ApplicationActionBarAdvisor.exitAction = exitAction;
	}

	public static IAction getRemoveContactorAction() {
		return removeContactorAction;
	}

	public static void setRemoveContactorAction(IAction removeContactorAction) {
		ApplicationActionBarAdvisor.removeContactorAction = removeContactorAction;
	}

	public static IAction getUpdateContactorAction() {
		return updateContactorAction;
	}

	public static void setUpdateContactorAction(IAction updateContactorAction) {
		ApplicationActionBarAdvisor.updateContactorAction = updateContactorAction;
	}

	public static IAction getSearchContactorAction() {
		return searchContactorAction;
	}

	public static void setSearchContactorAction(IAction searchContactorAction) {
		ApplicationActionBarAdvisor.searchContactorAction = searchContactorAction;
	}

}
