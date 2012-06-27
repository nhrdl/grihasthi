package com.niranjan.grihasthi.location;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class LocationEditorView extends EditorPart {

	public static final String ID = "com.niranjan.grihasthi.location.LocationEditorView";
	private LocationEditor locationEditor;

	@Override
	public void createPartControl(final Composite parent) {
		final LocationEditorInput ip = (LocationEditorInput) getEditorInput();
		// dbo = new LocationDBO();
		// final String user = System.getProperty("user.name");
		//
		// dbo.setName(user + "'s home");
		// dbo.setAddress("This is address");
		// dbo.setNotes("These are notes");

		locationEditor = new LocationEditor(parent, ip.getLocation());
	}

	@Override
	public void setFocus() {
		locationEditor.setFocus();
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
		final LocationEditorInput ip = (LocationEditorInput) getEditorInput();
		setPartName("Location:" + ip.getName());
	}

	@Override
	public boolean isDirty() {
		return true;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

}
