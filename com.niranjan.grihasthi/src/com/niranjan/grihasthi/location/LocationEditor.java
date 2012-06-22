package com.niranjan.grihasthi.location;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.richclientgui.toolbox.validation.IFieldErrorMessageHandler;
import com.richclientgui.toolbox.validation.ValidatingField;
import com.richclientgui.toolbox.validation.string.StringValidationToolkit;
import com.richclientgui.toolbox.validation.validator.IFieldValidator;

public class LocationEditor implements IFieldErrorMessageHandler {

	private StringValidationToolkit strValToolkit = null;
	private ValidatingField<String> nameField;
	private static final int DECORATOR_POSITION = SWT.TOP | SWT.LEFT;
	private static final int DECORATOR_MARGIN_WIDTH = 1;

	public LocationEditor(final Composite composite) {

		strValToolkit = new StringValidationToolkit(DECORATOR_POSITION,
				DECORATOR_MARGIN_WIDTH, true);
		strValToolkit.setDefaultErrorMessageHandler(this);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;

		composite.setLayout(gridLayout);

		final Label label = new Label(composite, SWT.NONE);
		label.setText("Name");

		this.nameField = strValToolkit.createTextField(composite,
				new IFieldValidator<String>() {

					@Override
					public boolean warningExist(final String arg0) {
						return false;
					}

					@Override
					public boolean isValid(final String contents) {
						return !(contents.length() == 0);
					}

					@Override
					public String getWarningMessage() {

						return "";
					}

					@Override
					public String getErrorMessage() {
						return "Name may not be empty";
					}
				}, true, "");
		final Text name = (Text) nameField.getControl();

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

	@Override
	public void handleErrorMessage(final String message, final String input) {
		// setMessage(null, DialogPage.WARNING);
		// setErrorMessage(message);
	}

	@Override
	public void handleWarningMessage(final String message, final String input) {
		// setErrorMessage(null);
		// setMessage(message, DialogPage.WARNING);
	}

	@Override
	public void clearMessage() {
		// setErrorMessage(null);
		// setMessage(null, DialogPage.WARNING);
	}

	public boolean isValid() {

		return nameField.isValid();
	}

}
