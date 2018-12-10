package com.crm.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import static com.crm.qa.objectrepository.ObjectRepository.*;
import com.crm.qa.listeners.TestListener;
import com.crm.qa.util.PropertyManager;
import com.crm.qa.util.Utilities;

public class LoginPage {
	
	//*********Page Variables*********
	
	private WebDriver driver;
    private static Logger Log = Logger.getLogger(LoginPage.class.getName());
	
	
	//*********Constructor*********
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//*********Web Elements*********
	
	@FindBy(how=How.ID,using="session_email")
	WebElement uname;

	@FindBy(how=How.ID,using="session_password")
	WebElement pwd;
	
	@FindBy(how=How.XPATH,using=".//input[@name='commit']")
	WebElement submit;
	
	@FindBy(how=How.XPATH,using=".//a[text()='Sign in']")
	WebElement signIn;
	
	
	
	//*********Page Methods*********
	
	/**
	 * This method will use to Login to Application
	 * @param userName
	 * @param password
	 * @return Title of login Page
	 * @throws InterruptedException
	 */
	
	public String loginToApplication(String userName,String password) throws InterruptedException
	{
		try {
			
			

			driver.get(PropertyManager.getInstance().getConfigTimeData("url"));

			Log.info("-----------Navigating to URL-------------");
			Utilities.waitForPageTitleIs(driver, "Address Book", 30);
			signIn.click();
			Utilities.waitForElementVisible(driver, byLoginPage_userName, 30);
			uname.clear();
			uname.sendKeys(userName);
			pwd.clear();
			pwd.sendKeys(password);
			submit.click();
			Log.info("-----------Clicked on Sign In button-------------");
			Utilities.waitForPageTitleIs(driver, "Address Book", 30);
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while loginto Application---------", e);
		}
		return driver.getTitle();
	}
	
	/**
	 * This method will verify Title of Login Page
	 * @param aTitle
	 * @param eTitle
	 */
	
	public void verifyTitle(String aTitle,String eTitle)
	{
		try {
			if(aTitle.equals(eTitle))
			{
				Assert.assertTrue(true);
				TestListener.pass("Home page should be Displayed Successfully after Login.", "Home page is Displayed Successfully after Login.", driver, true);
				
				Log.info("-----------Login Title Page is matched-------------");
				
			}else
			{
				TestListener.fail("Home page should be Displayed Successfully after Login.", "Home page is not Displayed Successfully after Login.", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------Login Title Page is not matched-------------");
			}
		} catch (Exception e) {
				
			Log.error("----------Exception Occured while Verifying Login Page title---------", e);
			
		}
	}
	
	

}
