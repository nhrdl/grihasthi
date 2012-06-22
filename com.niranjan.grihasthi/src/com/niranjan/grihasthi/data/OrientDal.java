package com.niranjan.grihasthi.data;

public class OrientDal {

	public static void save(final GrihasthiDBO dbo) {
		dbo.getDocument().save();
	}

}
