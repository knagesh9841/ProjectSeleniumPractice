package com.crm.qa.pages;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.util.PropertyManager;
import com.crm.qa.util.Utilities;
import com.crm.qa.util.WaitUtilities;

public class DownloadPage {
	
	//*********Page Variables*********
	
		private WebDriver driver;
	    private static Logger Log = Logger.getLogger(DownloadPage.class.getName());
		HashMap<String, String> testData = new HashMap<String,String>();
		String testDataHolder;
		
		//*********Constructor*********
		
		public DownloadPage(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		// url = https://www.toolsqa.com/automation-practice-form/
		
		//*********Web Elements*********
		
		@FindBy(how=How.XPATH,using="//a[text()='Test File to Download']")
		@CacheLookup
		public WebElement downloadBtn;
		
		//*********Page Methods*********
		
		/**
		 * This Method will download the file.
		 */
		
		public void verifyDownloadedFile()
		{
			driver.get(PropertyManager.getInstance().getConfigTimeData("downloadurl"));
			
			Log.info("-----------Navigating to URL-------------");
			
			Utilities.maximizeWindow();
			WaitUtilities.waitForPageToBeLoad(driver);
			
			WaitUtilities.waitForPageTitleIs(driver, "Demo Form for practicing Selenium Automation", 30);
			
			downloadBtn.click();
		}

}
