package com.niranjan.grihasthi.location;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class LocationEditor {

	public LocationEditor(final Composite composite) {
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;

		composite.setLayout(gridLayout);

		final Label label = new Label(composite, SWT.NONE);
		label.setText("Name");

		final Text name = new Text(composite, SWT.BORDER);
		final GridData nameGridData = new GridData();
		nameGridData.horizontalAlignment = SWT.FILL;
		nameGridData.grabExcessHorizontalSpace = true;
		name.setLayoutData(nameGridData);

		final Label addressLabel = new Label(composite, SWT.NONE);
		addressLabel.setText("Address:");
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		addressLabel.setLayoutData(gridData);

		final Text addressText = new Text(composite, SWT.BORDER | SWT.WRAP
				| SWT.MULTI);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		addressText.setLayoutData(gridData);
		addressText
				.setText("This text field and the List\nbelow share any excess space.");

		final Label notesLabel = new Label(composite, SWT.NONE);
		notesLabel.setText("Address:");
		gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		addressLabel.setLayoutData(gridData);

		final Text notesText = new Text(composite, SWT.BORDER | SWT.WRAP
				| SWT.MULTI);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		notesText.setLayoutData(gridData);
		notesText
				.setText("This text field and the List\nbelow share any excess space.");

	}

}
