package org.feifei.phonebook.favorate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Hits;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.feifei.phonebook.base.BaseEditor;
import org.feifei.phonebook.dao.SiteDAO;
import org.feifei.phonebook.model.Site;
import org.feifei.phonebook.searchuser.index.SearchIndex;
import org.feifei.phonebook.util.Co;

import com.swtdesigner.SWTResourceManager;

public class MyfavoriteEditor extends BaseEditor {

	private Text txtSearch;
	private Text txtName;
	private Text txtTag;
	private Text txtDesc;
	private Text txtAddress;
	public static final String ID = "org.feifei.phonebook.favorate.MyfavoriteEditor"; //$NON-NLS-1$
	private FormToolkit toolkit;
	private Composite composite_2;
	private Section secResult;
	private Composite composite_4;
	private Section section_1;
	private Button button_1;

	/**
	 * Create contents of the editor part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		toolkit=new FormToolkit(container.getDisplay());
		final ScrolledForm scrolledForm = toolkit.createScrolledForm(container);
		final Composite body = scrolledForm.getBody();
		body.setLayout(new GridLayout());

		final Section section = toolkit.createSection(body, Section.TITLE_BAR | Section.COMPACT | Section.DESCRIPTION | Section.FOCUS_TITLE | Section.EXPANDED | Section.TWISTIE);
		section.setToggleColor(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		section.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		section.setText("添加网址");

		final Composite composite = toolkit.createComposite(section, SWT.NONE);
		section.setClient(composite);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		composite.setLayout(gridLayout);
		toolkit.paintBordersFor(composite);

		toolkit.createLabel(composite, "名称", SWT.NONE);

		txtName = toolkit.createText(composite, null, SWT.NONE);
		txtName.setFont(SWTResourceManager.getFont("宋体", 22, SWT.NONE));
		final GridData gd_txtName = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_txtName.heightHint = 30;
		txtName.setLayoutData(gd_txtName);
		new Label(composite, SWT.NONE);

		toolkit.createLabel(composite, "网址", SWT.NONE);

		txtAddress = toolkit.createText(composite, null, SWT.NONE);
		txtAddress.setFont(SWTResourceManager.getFont("宋体", 22, SWT.NONE));
		final GridData gd_txtAddress = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_txtAddress.heightHint = 30;
		txtAddress.setLayoutData(gd_txtAddress);
		new Label(composite, SWT.NONE);

		toolkit.createLabel(composite, "描述", SWT.NONE);

		txtDesc = toolkit.createText(composite, null, SWT.NONE);
		txtDesc.setFont(SWTResourceManager.getFont("宋体", 22, SWT.NONE));
		final GridData gd_txtDesc = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_txtDesc.heightHint = 30;
		txtDesc.setLayoutData(gd_txtDesc);
		new Label(composite, SWT.NONE);

		final Label label = toolkit.createLabel(composite, "标签", SWT.NONE);
		label.setToolTipText("方便搜索");

		txtTag = toolkit.createText(composite, null, SWT.NONE);
		txtTag.setFont(SWTResourceManager.getFont("宋体", 22, SWT.NONE));
		final GridData gd_txtTag = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gd_txtTag.heightHint = 30;
		txtTag.setLayoutData(gd_txtTag);

		final Button button = toolkit.createButton(composite, "添加", SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				Site site=new Site();
				site.setName(Co.getStr(txtName.getText()));
				site.setDescription(Co.getStr(txtDesc.getText()));
				site.setUrl(Co.getStr(txtAddress.getText()));
				site.setTag(Co.getStr(txtTag.getText()));
				try {
					SiteDAO.create(site);
				} catch (Exception e1) {
					MessageDialog.openInformation(getSite().getShell(), "提示", "天啊，保存数据出错了");
				}
				createLink(site,composite_2);
				txtName.setText("");
				txtDesc.setText("");
				txtAddress.setText("");
				txtTag.setText("");
			}
		});
		button.setLayoutData(new GridData());

		final Section section_2 = toolkit.createSection(body, Section.TITLE_BAR | Section.FOCUS_TITLE | Section.EXPANDED | Section.TWISTIE);
		section_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
		section_2.setText("搜索网址");

		final Composite composite_3 = toolkit.createComposite(section_2, SWT.NONE);
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.marginRight = 600;
		gridLayout_2.numColumns = 2;
		composite_3.setLayout(gridLayout_2);
		toolkit.paintBordersFor(composite_3);
		section_2.setClient(composite_3);

		txtSearch = toolkit.createText(composite_3, null, SWT.NONE);
		final GridData gd_txtSearch = new GridData(SWT.FILL, SWT.CENTER, true, false);
		txtSearch.setLayoutData(gd_txtSearch);
		
		txtSearch.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent e) {
				
			}

			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

		button_1 = toolkit.createButton(composite_3, "查我所需", SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
                //查询
				String searchTxt=txtSearch.getText();
                List<Site> sites=new ArrayList<Site>();
				Hits h=SearchIndex.searchSiteByKeyword(searchTxt);
				for(int i=0;i<h.length();i++){
					try {
						Site s=new Site();
						Document d=h.doc(i);
						s.setName(Co.getStr(d.get("name")));
						s.setTag(Co.getStr(d.get("tag")));
						s.setUrl(Co.getStr(d.get("url")));
						sites.add(s);
					} catch (IOException e1) {
						MessageDialog.openInformation(getSite().getShell(), "提示", "搜索出错了");
						return;
					}
				}
				//显示查询结果
				secResult.setVisible(true);
				secResult.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
				Control[] children=composite_4.getChildren();
				for(Control c:children){
					c.dispose();
				}
				for(Site s:sites){
					createLink(s, composite_4);
				}
				body.layout(true);
				section_1.setExpanded(false);
			}
		});

		secResult = toolkit.createSection(body, SWT.NONE);
		final GridData gd_secResult = new GridData(SWT.FILL, SWT.FILL, true, true);
		secResult.setLayoutData(gd_secResult);
		secResult.setVisible(false);

		composite_4 = toolkit.createComposite(secResult, SWT.NONE);
		composite_4.setLayout(new GridLayout());
		toolkit.paintBordersFor(composite_4);
		secResult.setClient(composite_4);

		final Composite composite_1 = toolkit.createComposite(body, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite_1.setLayout(new GridLayout());
		toolkit.paintBordersFor(composite_1);

		section_1 = toolkit.createSection(composite_1, Section.TITLE_BAR | Section.EXPANDED | Section.TWISTIE);
		section_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		section_1.setText("已收录的网址");

		composite_2 = toolkit.createComposite(section_1, SWT.NONE);
		section_1.setClient(composite_2);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 14;
		composite_2.setLayout(gridLayout_1);
		toolkit.paintBordersFor(composite_2);
		initSite();
		
	}


	private void initSite() {
		try {
			List sites=SiteDAO.getAllSite();
			for(Object site:sites){
				final Site s=(Site) site;
				toolkit.createHyperlink(composite_2, s.getName(), SWT.NONE).addHyperlinkListener(new IHyperlinkListener(){
					public void linkActivated(HyperlinkEvent arg0) {
						XuncangluEditorInput input=new XuncangluEditorInput();
						input.setUrl(s.getUrl());
						input.setName(s.getName());
						try {
							getSite().getWorkbenchWindow().getActivePage().openEditor(input, XuncangluEditor.ID);
						} catch (PartInitException e) {
							MessageDialog.openInformation(getSite().getShell(), "提示", "打开网页出错了");
						}
					}
					public void linkEntered(HyperlinkEvent arg0) {
					}
					public void linkExited(HyperlinkEvent arg0) {
					}
				});
			}
			composite_2.layout(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void createLink(final Site site,Composite composite) {
		toolkit.createHyperlink(composite, site.getName(), SWT.NONE).addHyperlinkListener(new IHyperlinkListener(){
			public void linkActivated(HyperlinkEvent arg0) {
				XuncangluEditorInput input=new XuncangluEditorInput();
				input.setUrl(site.getUrl());
				input.setName(site.getName());
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, XuncangluEditor.ID);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			public void linkEntered(HyperlinkEvent arg0) {
			}
			public void linkExited(HyperlinkEvent arg0) {
			}
		});
		composite.layout(true);
	}


	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
	}



}
