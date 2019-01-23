package com.crm.qa.testcases;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.exceltestdata.ExcelUtils;

public class LoginTest extends BaseTest{
	
	String atitle;
	Object loginDetail[][];
	HashMap<String, String> testData = new HashMap<String,String>();

	private static Logger Log = Logger.getLogger(LoginTest.class.getName());
		
	  @Test(description="Login To Application.",dataProvider = "LoginData")
	  public void basicTest(String username, String password) throws InterruptedException {
		
		  Log.info("-----------Execution started for Method basicTest.-------------");
		  
		  atitle = loginPage_Object.loginToApplication(username, password);
		  loginPage_Object.verifyTitle(atitle, "Address Book");
		 
		  Log.info("-----------Execution Completed for Method basicTest.-------------");
	  }
	  
	  @Test(description="Enter Address,Verify Address,Delete Address,Verify Deleted Address.",dependsOnMethods = {"basicTest"})
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
	  
	  @DataProvider (name = "LoginData")
	  public Object[][] loginData () {
		  
		  testData = ExcelUtils.loadData("ExcelTestDataFile.xlsx", "LoginDetails", 1);
		  loginDetail = new Object[1][2];
		  loginDetail[0][0] = ExcelUtils.getData(testData, "UserName");
		  loginDetail[0][1] = ExcelUtils.getData(testData, "Password");

		  return loginDetail;
	  }
		  
}
