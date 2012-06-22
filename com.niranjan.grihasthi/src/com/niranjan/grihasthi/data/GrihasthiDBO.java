package com.niranjan.grihasthi.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrihasthiDBO implements PropertyChangeListener {

	ODocument data;
	public String CREATION_DATE_KEY = "creationDate",
			LAST_MODIFIED_DATE_KEY = "lastModifiedDate";

	protected final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public GrihasthiDBO() {
		data = new ODocument();
		final Date dt = new Date();
		data.field(CREATION_DATE_KEY, dt);
	}

	public String getStringValue(final String key) {
		return data.field(key);
	}

	protected void setValue(final String key, final Object value) {
		final Object oldValue = data.field(key);
		data.field(key, value);
		data.field(LAST_MODIFIED_DATE_KEY, new Date());
		this.propertyChangeSupport.firePropertyChange(key, oldValue, value);

	}

	public void addPropertyChangeListener(final String propertyName,
			final PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(
			final PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public ODocument getDocument() {
		return data;
	}
}
