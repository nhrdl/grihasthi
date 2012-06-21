package com.niranjan.grihasthi;

import java.io.File;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	com.orientechnologies.orient.server.OServer server;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	@Override
	public Object start(final IApplicationContext context) {
		final Display display = PlatformUI.createDisplay();
		try {

			try {
				// server = com.orientechnologies.orient.server.OServerMain
				// .create();
				// server.startup(getOrientConfiguration());
				// server.activate();
				// initializeDatabase();

			} catch (final Exception e) {
				server = null;
				e.printStackTrace();
			}

			final int returnCode = PlatformUI.createAndRunWorkbench(display,
					new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	}

	static final String PRODUCT_DIR = "grihasthiData";
	static final String PRODUCT_DATA = "grihasthiDB";

	protected void initializeDatabase() {
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
		}

		db.open("admin", "admin");
		final ODocument doc = new ODocument("Person");
		doc.field("name", "Luke");
		doc.field("surname", "Skywalker");
		doc.field(
				"city",
				new ODocument("City").field("name", "Rome").field("country",
						"Italy"));

		// SAVE THE DOCUMENT
		doc.save();

	}

	private void createSchemas() {
		// TODO Auto-generated method stub

	}

	ODatabaseDocumentTx db;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	@Override
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning()) {
			return;
		}
		if (server != null) {
			db.close();
			server.shutdown();
			server = null;
		}
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				if (!display.isDisposed()) {
					workbench.close();
				}
			}
		});
	}

	public String getOrientConfiguration() {
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
}
