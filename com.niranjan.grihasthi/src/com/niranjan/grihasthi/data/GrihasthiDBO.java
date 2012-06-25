package com.niranjan.grihasthi.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Date;

import com.orientechnologies.orient.core.record.impl.ODocument;

public class GrihasthiDBO implements PropertyChangeListener {

	public enum ObjectType {
		Location
	}
	ODocument data;
	public String CREATION_DATE_KEY = "creationDate",
			LAST_MODIFIED_DATE_KEY = "lastModifiedDate";

	protected final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);
	private final ObjectType objectType;

	public GrihasthiDBO(final String name, final ObjectType oType) {
		data = new ODocument(name);
		final Date dt = new Date();
		data.field(CREATION_DATE_KEY, dt);
		this.objectType = oType;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public GrihasthiDBO(final ODocument data2, final ObjectType oType) {
		data = data2;
		this.objectType = oType;
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

	public String getTreeName() {
		return "";
	}
}
