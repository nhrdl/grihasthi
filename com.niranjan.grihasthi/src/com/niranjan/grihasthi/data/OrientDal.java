package com.niranjan.grihasthi.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;

public class OrientDal {

	public static void save(final GrihasthiDBO dbo) {
		dbo.getDocument().save();
	}

	public static <T extends GrihasthiDBO> Collection<T> readData(
			final String query, final IDBOInstanceCreator<T> creator) {
		final ArrayList<T> list = new ArrayList<T>();
		final List<ODocument> docList = db.query(new OSQLSynchQuery<T>(query));
		for (final ODocument oDocument : docList) {
			list.add(creator.createInstance(oDocument));
		}
		return list;
	}

	public static void stop() {
		if (server != null) {
			db.close();
			db = null;
			server.shutdown();
			server = null;
		}
	}

	public static void start() {
		try {
			server = com.orientechnologies.orient.server.OServerMain.create();
			server.startup(getOrientConfiguration());
			server.activate();
			initializeDatabase();

		} catch (final Exception e) {
			server = null;
			e.printStackTrace();
		}

	}

	static com.orientechnologies.orient.server.OServer server;
	static final String PRODUCT_DIR = "grihasthiData";
	static final String PRODUCT_DATA = "grihasthiDB";

	static void initializeDatabase() {
		final String userHome = System.getProperty("user.home");

		final String dbDir = userHome + File.separatorChar + PRODUCT_DIR
				+ File.separator + PRODUCT_DATA;
		db = new com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx(
				"local:" + dbDir);
		final File dbDirFile = new File(dbDir);
		if (dbDirFile.exists() == false) {
			dbDirFile.mkdirs();
			db.create();
			createSchemas();
		} else {
			db.open("admin", "admin");
		}
	}

	private static void createSchemas() {
		// TODO Auto-generated method stub

	}

	static ODatabaseDocumentTx db;

	public static String getOrientConfiguration() {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<orient-server>"
				+ "<network>"
				+ "<protocols>"
				+ "<protocol name=\"binary\" implementation=\"com.orientechnologies.orient.server.network.protocol.binary.ONetworkProtocolBinary\"/>"
				+ "<protocol name=\"http\" implementation=\"com.orientechnologies.orient.server.network.protocol.http.ONetworkProtocolHttpDb\"/>"
				+ "</protocols>"
				+ "<listeners>"
				+ "<listener ip-address=\"0.0.0.0\" port-range=\"2424-2430\" protocol=\"binary\"/>"
				+ "<listener ip-address=\"0.0.0.0\" port-range=\"2480-2490\" protocol=\"http\"/>"
				+ "</listeners>"
				+ "</network>"
				+ "<users>"
				+ "<user name=\"root\" password=\"ThisIsA_TEST\" resources=\"*\"/>"
				+ "</users>"
				+ "<properties>"
				+ "<entry name=\"orientdb.www.path\" value=\"C:/work/dev/orientechnologies/orientdb/releases/1.0rc1-SNAPSHOT/www/\"/>"
				+ "<entry name=\"orientdb.config.file\" value=\"C:/work/dev/orientechnologies/orientdb/releases/1.0rc1-SNAPSHOT/config/orientdb-server-config.xml\"/>"
				+ "<entry name=\"server.cache.staticResources\" value=\"false\"/>"
				+ "<entry name=\"log.console.level\" value=\"info\"/>"
				+ "<entry name=\"log.file.level\" value=\"fine\"/>"
				+ "</properties>" + "</orient-server>";

	}

	public static final IDBOInstanceCreator<LocationDBO> LOCATION_INSTANCE_CREATOR = new IDBOInstanceCreator<LocationDBO>() {

		@Override
		public LocationDBO createInstance(final ODocument data) {
			return new LocationDBO(data);
		}
	};
}
