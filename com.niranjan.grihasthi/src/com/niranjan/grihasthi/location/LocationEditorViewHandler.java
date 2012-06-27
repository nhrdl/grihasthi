package com.niranjan.grihasthi.location;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.niranjan.grihasthi.NavigationView;
import com.niranjan.grihasthi.NavigationView.TreeObject;
import com.niranjan.grihasthi.data.LocationDBO;

public class LocationEditorViewHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		System.out.println("called");
		// Get the view
		final IWorkbenchWindow window = HandlerUtil
				.getActiveWorkbenchWindow(event);
		final IWorkbenchPage page = window.getActivePage();
		final NavigationView view = (NavigationView) page
				.findView(NavigationView.ID);
		// Get the selection
		final ISelection selection = view.getSite().getSelectionProvider()
				.getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			final Object obj = ((IStructuredSelection) selection)
					.getFirstElement();
			// If we had a selection lets open the editor
			if (obj != null) {
				final NavigationView.TreeObject node = (TreeObject) obj;
				final LocationDBO location = (LocationDBO) node.getDataObject();
				final LocationEditorInput input = new LocationEditorInput(
						location);
				try {
					page.openEditor(input, LocationEditorView.ID);

				} catch (final PartInitException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}

}