package com.niranjan.grihasthi.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Hashtable;

public class GrihasthiDBO implements PropertyChangeListener {

	Hashtable<String, Object> data;
	protected final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public GrihasthiDBO() {
		data = new Hashtable<String, Object>();
	}

	public String getStringValue(final String key) {
		final Object obj = data.get(key);
		return obj != null ? obj.toString() : null;
	}

	protected void setValue(final String key, final Object value) {
		final Object oldValue = data.get(key);
		data.put(key, value);

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
}
