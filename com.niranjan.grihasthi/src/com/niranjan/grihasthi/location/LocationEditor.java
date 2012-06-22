package com.niranjan.grihasthi.location;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.niranjan.grihasthi.data.LocationDBO;
import com.richclientgui.toolbox.validation.IFieldErrorMessageHandler;
import com.richclientgui.toolbox.validation.ValidatingField;
import com.richclientgui.toolbox.validation.string.StringValidationToolkit;
import com.richclientgui.toolbox.validation.validator.IFieldValidator;

public class LocationEditor implements IFieldErrorMessageHandler {

	private StringValidationToolkit strValToolkit = null;
	private ValidatingField<String> nameField;
	private static final int DECORATOR_POSITION = SWT.TOP | SWT.LEFT;
	private static final int DECORATOR_MARGIN_WIDTH = 1;
	private final LocationDBO dbo;
	private Text addressText;
	private Text notesText;

	public LocationEditor(final Composite composite, final LocationDBO dbo) {

		this.dbo = dbo;
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

		this.addressText = new Text(composite, SWT.BORDER | SWT.WRAP
				| SWT.MULTI);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		addressText.setLayoutData(gridData);

		final Label notesLabel = new Label(composite, SWT.NONE);
		notesLabel.setText("Notes:");
		gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		addressLabel.setLayoutData(gridData);

		this.notesText = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		notesText.setLayoutData(gridData);

		initDataBindings();
	}

	private DataBindingContext initDataBindings() {
		final DataBindingContext bindingContext = new DataBindingContext();
		final Text name = (Text) nameField.getControl();
		addBinding(bindingContext, name, "name");
		addBinding(bindingContext, addressText, "address");
		addBinding(bindingContext, notesText, "notes");
		return bindingContext;

	}

	private void addBinding(final DataBindingContext bindingContext,
			final Text field, final String property) {
		final IObservableValue fieldObs = SWTObservables.observeText(field,
				SWT.FocusOut);
		final IObservableValue propObs = BeansObservables.observeValue(dbo,
				property);
		bindingContext.bindValue(fieldObs, propObs);
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
