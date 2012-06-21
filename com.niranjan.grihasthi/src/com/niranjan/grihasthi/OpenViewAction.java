package com.niranjan.grihasthi;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

public class OpenViewAction extends Action {

	private final IWorkbenchWindow window;
	private int instanceNum = 0;
	private final String viewId;

	public OpenViewAction(final IWorkbenchWindow window, final String label,
			final String viewId) {
		this.window = window;
		this.viewId = viewId;
		setText(label);
		// The id is used to refer to the action in a menu or toolbar
		setId(ICommandIds.CMD_OPEN);
		// Associate the action with a pre-defined command, to allow key
		// bindings.
		setActionDefinitionId(ICommandIds.CMD_OPEN);
		setImageDescriptor(com.niranjan.grihasthi.Activator
				.getImageDescriptor("/icons/sample2.gif"));
	}

	@Override
	public void run() {
		if (window != null) {
			try {
				window.getActivePage().showView(viewId,
						Integer.toString(instanceNum++),
						IWorkbenchPage.VIEW_ACTIVATE);
			} catch (final PartInitException e) {
				MessageDialog.openError(window.getShell(), "Error",
						"Error opening view:" + e.getMessage());
			}
		}
	}

	// @Override
	// public void run(final IIntroSite arg0, final Properties arg1) {
	// viewId = View.ID;
	// window = arg0.getWorkbenchWindow();
	// run();
	//
	// }

}
