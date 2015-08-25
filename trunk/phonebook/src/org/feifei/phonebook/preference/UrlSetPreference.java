package org.feifei.phonebook.preference;

import java.io.IOException;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.feifei.phonebook.Activator;

public class UrlSetPreference extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	public static final String URL = "http://www.google.com/notebook/ig?hl=__MSG_locale__";

	private ScopedPreferenceStore preferences;

	/**
	 * Create the preference page
	 */
	public UrlSetPreference() {
		super(FLAT);
		preferences = new ScopedPreferenceStore(new ConfigurationScope(),
				Activator.PLUGIN_ID);
		setPreferenceStore(preferences);

	}


	/**
	 * Create contents of the preference page
	 */
	@Override
	protected void createFieldEditors() {
		{
			final StringFieldEditor stringFieldEditor = new StringFieldEditor("URL", "≤‡¿∏µÿ÷∑", getFieldEditorParent());
			stringFieldEditor.setStringValue("http://www.google.com/notebook/ig?hl=__MSG_locale__");
			addField(stringFieldEditor);
		}
		// Create the field editors
	}

	/**
	 * Initialize the preference page
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	public boolean performOk() {
		try {
			preferences.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.performOk();
	}


	@Override
	protected void performDefaults() {
		super.performDefaults();
	}

}
