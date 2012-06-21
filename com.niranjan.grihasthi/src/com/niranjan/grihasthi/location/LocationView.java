package com.niranjan.grihasthi.location;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class LocationView extends ViewPart {

	public static final String ID = "com.niranjan.grihasthi.location.LocationView";

	@Override
	public void createPartControl(final Composite parent) {
		new Composite(parent, SWT.NONE);
		final Label lbl = new Label(parent, SWT.NONE);
		lbl.setText("Hello view");
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
