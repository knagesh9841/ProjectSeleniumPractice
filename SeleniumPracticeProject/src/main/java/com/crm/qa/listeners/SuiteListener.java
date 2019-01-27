package com.crm.qa.listeners;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener{
	
	static
	{
		 DOMConfigurator.configure(System.getProperty("user.dir") + "\\src\\main\\java\\com\\crm\\qa\\resources\\config\\log4j.xml");
	}
	
	private static Logger Log = Logger.getLogger(SuiteListener.class.getName());

	@Override
	public void onStart(ISuite suite) {
		Log.info("-----------About to begin executing Suite "+suite.getName()+".-----------");
	}

	@Override
	public void onFinish(ISuite suite) {
		Log.info("-----------About to end executing Suite "+suite.getName()+".-----------");
	}

}
