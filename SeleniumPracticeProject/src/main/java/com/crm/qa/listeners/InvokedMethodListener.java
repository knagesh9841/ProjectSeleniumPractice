package com.crm.qa.listeners;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class InvokedMethodListener implements IInvokedMethodListener {
	
	private static Logger Log = Logger.getLogger(InvokedMethodListener.class.getName());

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		Log.info("-----------About to begin executing following method :  "+returnMethodName(method.getTestMethod())+".-----------");

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		Log.info("-----------Completed executing following method :  "+returnMethodName(method.getTestMethod())+".-----------");

	}

	// This will return method names to the calling function

	private String returnMethodName(ITestNGMethod method) {

		return method.getRealClass().getSimpleName() + "." + method.getMethodName();

	}

}
