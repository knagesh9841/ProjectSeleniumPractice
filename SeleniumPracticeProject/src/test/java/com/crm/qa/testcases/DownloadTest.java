package com.crm.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class DownloadTest extends BaseTest{
	
	private static Logger Log = Logger.getLogger(BaseTest.class.getName());
	
	  @Test(description="Download Functionality.")
	  
	  public void downloadTest() {
		
		  Log.info("-----------Execution started for Method downloadTest.-------------");
		  
		  downloadPage_Object.verifyDownloadedFile();
		  
		  Log.info("-----------Execution Completed for Method downloadTest.-------------");
	  }

}
