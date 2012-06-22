package com.niranjan.grihasthi.data;

import com.orientechnologies.orient.core.record.impl.ODocument;

public interface IDBOInstanceCreator<T extends GrihasthiDBO> {

	public T createInstance(ODocument data);

}
