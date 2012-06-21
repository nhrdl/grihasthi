package com.niranjan.grihasthi.location;

import java.util.Properties;

import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

public class LocationViewAction implements IIntroAction {

	@Override
	public void run(final IIntroSite site, final Properties arg1) {

		new LocationDialog(site.getWorkbenchWindow().getShell()).open();
	}

}
