package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{
	
	String atitle;

	private static Logger Log = Logger.getLogger(LoginTest.class.getName());
		
	  @Test(description="Login To Application.",priority = 0,groups={"First"})
	  public void basicTest() throws InterruptedException {
		
		  Log.info("-----------Execution started for Method basicTest.-------------");
		  
		  atitle = loginPage_Object.loginToApplication("knagesh143s@gmail.com", "knagesh143s");
		  loginPage_Object.verifyTitle(atitle, "Address Book");
		 
		  Log.info("-----------Execution Completed for Method basicTest.-------------");
	  }
	  
	  @Test(description="Enter Address,Verify Address,Delete Address,Verify Deleted Address.",dependsOnMethods = {"basicTest"},priority = 1,groups={"Second"})
	  public void verifyAddressTest()
	  {
		  Log.info("-----------Execution started for Method verifyAddressTest.-------------");
		  addrsPage_Object.fillAddressDetails();
		  addrsPage_Object.verifyAddressDetails();
		  addrsPage_Object.deleteAddressDetails();
		  addrsPage_Object.verifyDeletedAddressDetails();
		  atitle = homePage_Object.loginOutFromApplication();
		  homePage_Object.verifyTitle(atitle, "Address Book - Sign In");
		  Log.info("-----------Execution Completed for Method verifyAddressTest.-------------");
		   
	  }
	  
	  @Test(priority = 2,retryAnalyzer=com.crm.qa.listeners.RetryAnalyzer.class,enabled=false)
	  public void verifyTitle()
	  {
		  Log.info("-----------Execution started for Method verifyTitle.-------------");
		  homePage_Object.verifyTitle(atitle, "Address Book - Sign I");
		  Log.info("-----------Execution Completed for Method verifyTitle.-------------");
	  }
		  
}
