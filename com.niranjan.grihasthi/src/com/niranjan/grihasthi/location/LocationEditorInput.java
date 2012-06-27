package com.niranjan.grihasthi.location;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import com.niranjan.grihasthi.data.LocationDBO;

public class LocationEditorInput implements IEditorInput {

	private final LocationDBO location;

	public LocationEditorInput(final LocationDBO location) {
		this.location = location;

	}

	@Override
	public Object getAdapter(final Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {

		final String name = location.getName();
		return name != null ? name : "New location";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		return "Edit location";
	}

	public LocationDBO getLocation() {
		return location;
	}
}
