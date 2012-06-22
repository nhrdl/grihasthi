package com.niranjan.grihasthi.location;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class LocationDialog extends Dialog {

	protected LocationDialog(final Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected boolean isResizable() {
		return true;
	};

	LocationEditor locationEditor;

	@Override
	protected Control createDialogArea(final Composite parent) {
		final LocationDBO dbo = new LocationDBO();
		final String user = System.getProperty("user.name");

		dbo.setName(user + "'s home");
		dbo.setAddress("This is address");
		dbo.setNotes("These are notes");
		locationEditor = new LocationEditor(parent, dbo);

		return super.createDialogArea(parent);
	}

	@Override
	protected void configureShell(final Shell newShell) {
		// TODO Auto-generated method stub
		super.configureShell(newShell);
		newShell.setText("Edit location");
	}

	@Override
	protected void okPressed() {
		if (locationEditor.isValid()) {
			super.okPressed();
		}
	}
}
