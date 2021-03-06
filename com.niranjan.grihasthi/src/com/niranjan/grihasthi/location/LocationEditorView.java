package com.niranjan.grihasthi.location;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.niranjan.grihasthi.data.OrientDal;

public class LocationEditorView extends EditorPart {

	public static final String ID = "com.niranjan.grihasthi.location.LocationEditorView";
	private LocationEditor locationEditor;

	boolean dirty = false;
	ModifyListener modifyListener = new ModifyListener() {

		@Override
		public void modifyText(final ModifyEvent e) {
			dirty = true;
			firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	};

	@Override
	public void createPartControl(final Composite parent) {
		final LocationEditorInput ip = (LocationEditorInput) getEditorInput();
		// dbo = new LocationDBO();
		// final String user = System.getProperty("user.name");
		//
		// dbo.setName(user + "'s home");
		// dbo.setAddress("This is address");
		// dbo.setNotes("These are notes");

		locationEditor = new LocationEditor(parent, ip.getLocation(),
				modifyListener);
		dirty = false;
	}

	@Override
	public void setFocus() {
		locationEditor.setFocus();
	}

	@Override
	public void doSave(final IProgressMonitor monitor) {
		final LocationEditorInput ip = (LocationEditorInput) getEditorInput();
		OrientDal.save(ip.getLocation());
		dirty = false;
		firePropertyChange(IEditorPart.PROP_DIRTY);
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
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

}
