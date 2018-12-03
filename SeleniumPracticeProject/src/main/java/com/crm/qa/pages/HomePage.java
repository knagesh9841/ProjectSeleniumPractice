package com.crm.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.qa.listeners.TestListener;
import com.crm.qa.util.Utilities;

public class HomePage {
	
	//*********Page Variables*********
	
		private WebDriver driver;
		
		private static Logger Log = Logger.getLogger(HomePage.class.getName());
		
		
		//*********Constructor*********
		
		public HomePage(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		
		//*********Web Elements*********
		
		@FindBy(how=How.XPATH,using=".//a[text()='Sign out']")
		WebElement signOut;
		

				
		//*********Page Methods*********
		
		/** 
		 * This Method will use to logout from application
		 * @return Title of logout Page
		 */
		
		public String loginOutFromApplication() 
		{
			try {
				Utilities.waitForElementVisible(driver, By.xpath("//a[text()='Sign out']"), 30);
				signOut.click();
				Log.info("-----------Clicked on Signout button-------------");
				Utilities.waitForPageTitleIs(driver, "Address Book - Sign In", 30);
				
			} catch (Exception e) {
				
				Log.error("----------Exception Occured while logout Application---------", e);
			}
			
			return driver.getTitle();
		}
		
		/**
		 * This method will verify title of Logout Page
		 * @param aTitle
		 * @param eTitle
		 */
		
		public void verifyTitle(String aTitle,String eTitle)
		{
			try {
				if(aTitle.equals(eTitle))
				{
					Assert.assertTrue(true);
					TestListener.pass("Home page should be Displayed Successfully after Logout.", "Home page is Displayed Successfully after Logout.", driver);
					Log.info("-----------Logout Title Page is matched-------------");
					
				}else
				{
					TestListener.fail("Home page should be Displayed Successfully after Logout.", "Home page is not Displayed Successfully after Logout.", driver);
					Assert.assertTrue(false);
					 
					Log.info("-----------Logout Title Page is not matched-------------");
				}
			} catch (Exception e) {
					
				Log.error("----------Exception Occured while Verifying Logout Page title---------", e);
				
			}
		}
		

}
