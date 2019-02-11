package com.crm.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import static com.crm.qa.objectrepository.ObjectRepository.*;
import com.crm.qa.util.PropertyManager;
import com.crm.qa.util.Reporter;
import com.crm.qa.util.Utilities;
import com.crm.qa.util.WaitUtilities;

public class BootStrapLoginWindowPage {
	
	//*********Page Variables*********

	private WebDriver driver;

	private static Logger Log = Logger.getLogger(BootStrapLoginWindowPage.class.getName());


	//*********Constructor*********

	public BootStrapLoginWindowPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	//*********Web Elements*********

	@FindBy(how=How.XPATH,using="//button[text()='Click here to Login']")
	@CacheLookup
	public WebElement loginBtn;
	
	@FindBy(how=How.XPATH,using="//input[@type='text']")
	@CacheLookup
	public WebElement userName;
	
	@FindBy(how=How.XPATH,using="//input[@type='password']")
	@CacheLookup
	public WebElement pwd;
	
	@FindBy(how=How.XPATH,using="//button[text()='Close']")
	@CacheLookup
	public WebElement closeBtn;


	//*********Page Methods*********


	/**
	 * This method is used to Login to Bootstrap Login Window
	 * @param uname TODO
	 * @param password TODO
	 */

	public void verifyBootstrapLoginWindow(String uname, String password)
	{
		try {
			driver.get(PropertyManager.getInstance().getConfigTimeData("bootstraploginurl"));

			Log.info("-----------Navigating to URL-------------");

			Utilities.maximizeWindow();
			WaitUtilities.waitForPageToBeLoad(driver);

			WaitUtilities.waitForPageTitleIs(driver, "Selenium Practise: Handle BootStrap Model Dialog in Selenium Webdriver", 30);
			
			loginBtn.click();
			
			Log.info("-----------Clicked on Login Window button-------------");
			
			WaitUtilities.waitForElementVisible(driver, bybootstratLoginUserName, 20);
			
			userName.sendKeys(uname);
			
			pwd.sendKeys(password);
			
			String aUserName = userName.getAttribute("value");
			
			if(aUserName.equals(uname))
			{
				
				Reporter.pass("UserName should be "+uname+".", "Username is "+aUserName+".", driver, true);
				Log.info("-----------Username is matched-------------");
				
			}else
			{
				Reporter.fail("UserName should be "+uname+".", "Username is "+aUserName+".", driver, false);
				Log.info("-----------Username is not matched-------------");
			}
			
			closeBtn.click();
			
			Log.info("-----------Clicked on Close Window button-------------");
		} catch (Exception e) {
		
			Log.warn("-----------Exception Occured while working on Bootstrap Login Window.------------");
		}
		
	}
	
}
