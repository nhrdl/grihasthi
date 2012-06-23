package com.niranjan.grihasthi.location;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import com.niranjan.grihasthi.ICommandIds;

public class LocationEditorViewAction extends Action {

	private final IWorkbenchWindow window;
	private final String viewId;
	private int instanceNum = 0;

	public LocationEditorViewAction(final IWorkbenchWindow window, final String text,
			final String viewId) {
		this.window = window;
		this.viewId = viewId;
		setText(text);
		setId(ICommandIds.CMD_OPEN);
	}

	@Override
	public void run() {
		if (window != null) {
			try {
				window.getActivePage().showView(viewId,
						Integer.toString(instanceNum++),
						IWorkbenchPage.VIEW_ACTIVATE);
			} catch (final PartInitException e) {
				e.printStackTrace();
				MessageDialog.openError(window.getShell(), "Error",
						"Error opening view:" + e.getMessage());
			}
		}
	}


}
