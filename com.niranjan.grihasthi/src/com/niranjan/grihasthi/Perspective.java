package com.niranjan.grihasthi;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	/**
	 * The ID of the perspective as specified in the extension.
	 */
	public static final String ID = "com.niranjan.grihasthi.perspective";

	@Override
	public void createInitialLayout(final IPageLayout layout) {
		final String editorArea = layout.getEditorArea();
		// layout.setEditorAreaVisible(false);

		layout.addStandaloneView(NavigationView.ID, false, IPageLayout.LEFT,
				0.25f, editorArea);
		// final IFolderLayout folder = layout.createFolder("messages",
		// IPageLayout.TOP, 0.5f, editorArea);
		// folder.addPlaceholder(View.ID + ":*");
		// folder.addView(View.ID);
		//
		// folder.addPlaceholder(LocationEditorView.ID + ":*");

		layout.getViewLayout(NavigationView.ID).setCloseable(false);
	}
}
