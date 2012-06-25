package com.niranjan.grihasthi.location;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.niranjan.grihasthi.data.LocationDBO;

public class LocationEditorView extends EditorPart {

	public static final String ID = "com.niranjan.grihasthi.location.LocationEditorView";
	private LocationEditor locationEditor;
	private LocationDBO dbo;

	@Override
	public void createPartControl(final Composite parent) {
		dbo = new LocationDBO();
		final String user = System.getProperty("user.name");

		dbo.setName(user + "'s home");
		dbo.setAddress("This is address");
		dbo.setNotes("These are notes");

		locationEditor = new LocationEditor(parent, dbo);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		locationEditor.toString();
		dbo.toString();
	}

	@Override
	public void doSave(final IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(final IEditorSite site, final IEditorInput input)
			throws PartInitException {
		setInput(input);
		setSite(site);
		setPartName("Editing location:");
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

}
