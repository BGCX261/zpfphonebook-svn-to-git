package org.feifei.phonebook.searchuser;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Hits;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.feifei.phonebook.base.BaseEditor;
import org.feifei.phonebook.searchuser.index.SearchIndex;

public class SearchUser extends BaseEditor {

	private Text text;
	public static final String ID = "org.feifei.phonebook.searchuser.SearchUser"; //$NON-NLS-1$
	
	private FormToolkit toolkit;
	private Button button;

	/**
	 * Create contents of the editor part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		toolkit=new FormToolkit(container.getDisplay());
		ScrolledForm form=toolkit.createScrolledForm(container);
		final Composite body = form.getBody();
		final GridLayout gridLayout = new GridLayout();
		body.setLayout(gridLayout);

		final Composite composite = toolkit.createComposite(body, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.marginRight = 600;
		gridLayout_1.numColumns = 2;
		composite.setLayout(gridLayout_1);
		toolkit.paintBordersFor(composite);

		text = toolkit.createText(composite, null, SWT.NONE);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		button = toolkit.createButton(composite, "หั ห๗", SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				searchUser();
			}
		});
		composite.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {
				 if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR){
					 
				 }
				 button.setFocus();
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

		final Composite composite_1 = toolkit.createComposite(body, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite_1.setLayout(new GridLayout());
		toolkit.paintBordersFor(composite_1);

		toolkit.createHyperlink(composite_1, "New Hyperlink", SWT.NONE);
		//
	}
	protected void searchUser() {
		Hits hit=SearchIndex.searchUsrByName(text.getText());
		for(int i=0;i<hit.length();i++){
			try {
				Document d=hit.doc(i);
				d.get("name");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}
}
