package com.niranjan.grihasthi.location;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;

import com.niranjan.grihasthi.ICommandIds;
import com.niranjan.grihasthi.data.LocationDBO;

public class LocationEditorViewAction extends Action {

	private final IWorkbenchWindow window;

	public LocationEditorViewAction() {
		final IWorkbench bench = PlatformUI.getWorkbench();
		window = bench.getActiveWorkbenchWindow();

	}

	public LocationEditorViewAction(final IWorkbenchWindow window,
			final String text, final String viewId) {
		this.window = window;
		setText(text);
		setId(ICommandIds.CMD_OPEN);
	}

	@Override
	public void run() {
		if (window != null) {
			try {
				// window.getActivePage().showView(viewId,
				// Integer.toString(instanceNum++),
				// IWorkbenchPage.VIEW_ACTIVATE);
				final IIntroPart introPart = PlatformUI.getWorkbench()
						.getIntroManager().getIntro();
				PlatformUI.getWorkbench().getIntroManager()
						.closeIntro(introPart);
				final LocationEditorInput input = new LocationEditorInput(
						new LocationDBO());
				window.getActivePage().openEditor(input, LocationEditorView.ID);
			} catch (final Exception e) {
				e.printStackTrace();
				MessageDialog.openError(window.getShell(), "Error",
						"Error opening view:" + e.getMessage());
			}
		}
	}

}
