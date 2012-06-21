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
		locationEditor = new LocationEditor(parent);

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
		// TODO Auto-generated method stub
		super.okPressed();
	}
}
