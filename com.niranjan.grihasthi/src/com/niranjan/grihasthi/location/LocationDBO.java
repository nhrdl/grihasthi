package com.niranjan.grihasthi.location;

import com.niranjan.grihasthi.data.GrihasthiDBO;

public class LocationDBO extends GrihasthiDBO {

	public static final String NAME_KEY = "name", ADDRESS_KEY = "address",
			NOTES_KEY = "notes";

	public String getName() {
		return getStringValue(NAME_KEY);
	}

	public void setName(final String Name) {

		setValue(NAME_KEY, Name);
	}

	public String getAddress() {
		return getStringValue(ADDRESS_KEY);
	}

	public void setAddress(final String Address) {
		setValue(ADDRESS_KEY, Address);
	}

	public String getNotes() {
		return getStringValue(NOTES_KEY);
	}

	public void setNotes(final String Notes) {
		setValue(NOTES_KEY, Notes);
	}
}
