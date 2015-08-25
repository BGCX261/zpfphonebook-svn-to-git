package org.feifei.phonebook;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.feifei.phonebook.welcome.AllContactorView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editor = layout.getEditorArea();
		layout.addView(AllContactorView.ID, IPageLayout.LEFT, 0.2f, editor);
		layout.setFixed(true);
		layout.getViewLayout(AllContactorView.ID).setCloseable(false);
	}
}
