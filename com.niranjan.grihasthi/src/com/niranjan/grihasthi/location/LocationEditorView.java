package com.niranjan.grihasthi.location;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.niranjan.grihasthi.data.LocationDBO;

public class LocationEditorView extends ViewPart {

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

}
