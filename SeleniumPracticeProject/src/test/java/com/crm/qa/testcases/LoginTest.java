package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

	private static Logger Log = Logger.getLogger(LoginTest.class.getName());
		
	  @Test
	  public void basicTest() throws InterruptedException {
		
		  Log.info("-----------Execution started for Method basicTest.-------------");
		  
		  String atitle = loginPage_Object.loginToApplication("knagesh143s@gmail.com", "knagesh143s");
		  loginPage_Object.verifyTitle(atitle, "Address Book");
		  addrsPage_Object.fillAddressDetails();
		  addrsPage_Object.verifyAddressDetails();
		  addrsPage_Object.deleteAddressDetails();
		  addrsPage_Object.verifyDeletedAddressDetails();
		  atitle = homePage_Object.loginOutFromApplication();
		  homePage_Object.verifyTitle(atitle, "Address Book - Sign In");
		  
		  Log.info("-----------Execution Completed for Method basicTest.-------------");
	  }
		  
}
