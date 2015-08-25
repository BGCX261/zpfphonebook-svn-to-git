package org.feifei.phonebook.music;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.feifei.phonebook.Activator;
import org.feifei.phonebook.base.BaseEditor;

import com.swtdesigner.ResourceManager;

public class MusicEditor extends BaseEditor {

	public static final String ID = "org.feifei.phonebook.music.MusicEditor"; //$NON-NLS-1$

	private int[] rgdispid;

	private OleAutomation auto;

	/**
	 * Create contents of the editor part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout());
		parent.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		final Composite composite = new Composite(parent, SWT.NONE);
		final GridData gd_composite = new GridData(SWT.CENTER, SWT.CENTER,
				true, true);
		gd_composite.heightHint = 300;
		gd_composite.widthHint = 500;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new FillLayout());
		OleFrame frame = new OleFrame(composite, SWT.NONE);
		OleClientSite site = new OleClientSite(frame, SWT.NONE, "WMPlayer.OCX");
		// OleClientSite site=new
		// OleClientSite(frame,SWT.NONE,"MediaPlayer.MediaPlayer.1");
		site.setSize(frame.getSize());
		site.doVerb(OLE.OLEIVERB_SHOW);
		auto = new OleAutomation(site);

		final Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false));
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		composite_1.setLayout(gridLayout);

		final Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setImage(ResourceManager.getPluginImage(
				Activator.getDefault(), "icons/open-folder.png"));
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getSite().getShell());
				String url = dialog.open();
				if (url != null) {
					setUrl(url);
				}

			}
		});
		final GridData gd_button_1 = new GridData(SWT.CENTER, SWT.CENTER, true,
				false);
		gd_button_1.widthHint = 147;
		button_1.setLayoutData(gd_button_1);
		button_1.setText("´ò¿ª");

		final Button button = new Button(composite_1, SWT.NONE);
		button.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
				"icons/zoom+.png"));
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				fullScreen();
			}
		});
		final GridData gd_button = new GridData(SWT.CENTER, SWT.CENTER, true,
				false);
		gd_button.widthHint = 145;
		button.setLayoutData(gd_button);
		button.setText("È«ÆÁ");
		stretchToFit();
		uiMode();

	}

	public void stretchToFit() {
		rgdispid = auto.getIDsOfNames(new String[] { "stretchToFit" });
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant("True");
	}

	public void uiMode() {
		rgdispid = auto.getIDsOfNames(new String[] { "uiMode" });
		int dispIdMember = rgdispid[0];
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant("none");
		auto.setProperty(dispIdMember, rgvarg);
	}

	public void windowLessVideo() {
		rgdispid = auto.getIDsOfNames(new String[] { "windowlessVideo" });
		int dispIdMember = rgdispid[0];
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant("False");
		auto.setProperty(dispIdMember, rgvarg);
	}

	public void enable() {
		rgdispid = auto.getIDsOfNames(new String[] { "enabled" });
		int dispIdMember = rgdispid[0];
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant("True");
		auto.setProperty(dispIdMember, rgvarg);
	}

	public void enableContextMenu() {
		rgdispid = auto.getIDsOfNames(new String[] { "enableContextMenu" });
		System.out.println("2 rgdispid=" + rgdispid);
		int dispIdMember = rgdispid[0];
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant("True");
		auto.setProperty(dispIdMember, rgvarg);
	}

	public void fullScreen() {
		rgdispid = auto.getIDsOfNames(new String[] { "fullScreen" });
		int dispIdMember = rgdispid[0];
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant("true");
		auto.setProperty(dispIdMember, rgvarg);
	}

	public void setUrl(String url) {
		rgdispid = auto.getIDsOfNames(new String[] { "URL" });
		int dispIdMember = rgdispid[0];
		Variant[] rgvarg = new Variant[1];
		rgvarg[0] = new Variant(url);
		auto.setProperty(dispIdMember, rgvarg);
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}

}
