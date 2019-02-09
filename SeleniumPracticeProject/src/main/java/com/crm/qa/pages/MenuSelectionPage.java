package com.crm.qa.pages;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import static com.crm.qa.objectrepository.ObjectRepository.*;

import com.crm.qa.util.PropertyManager;
import com.crm.qa.util.Reporter;
import com.crm.qa.util.Utilities;
import com.crm.qa.util.WaitUtilities;

public class MenuSelectionPage {
	
	//*********Page Variables*********
	
	private WebDriver driver;
    private static Logger Log = Logger.getLogger(MenuSelectionPage.class.getName());
	HashMap<String, String> testData = new HashMap<String,String>();
	String testDataHolder;
	
	//*********Constructor*********
	
	public MenuSelectionPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//*********URL = https://www.toolsqa.com/
	
	//*********Web Elements*********
	
	@FindBy(how=How.XPATH,using="//nav[@class='navigation']//span[text()='Tutorial']")
	@CacheLookup
	public WebElement tutorialMenu;
	
	@FindBy(how=How.XPATH,using="//nav[@class='navigation']//span[text()='Web Automation Tools']")
	@CacheLookup
	public WebElement webAutoToolMenu;
	
	@FindBy(how=How.XPATH,using="//nav[@class='navigation']//span[text()='Cucumber Tutorial']")
	@CacheLookup
	public WebElement cucumberTutMenu;
	
	//*********Page Methods*********
	
	/**
	 * This method is used to Select Menu.
	 * @param titleName TODO
	 */
	
	public void verifyMenuSelection(String titleName)
	{
		try {
			
			driver.get(PropertyManager.getInstance().getConfigTimeData("menuselectionurl"));
			
			Log.info("-----------Navigating to URL-------------");
			
			Utilities.maximizeWindow();
			WaitUtilities.waitForPageToBeLoad(driver);
			
			WaitUtilities.waitForPageTitleIs(driver, "QA Automation Tools Tutorial", 30);
			
			Actions action = new Actions(driver);
			
			action.moveToElement(tutorialMenu).click().perform();
			
			WaitUtilities.waitForElementVisible(driver, bywebAutoToolMenu, 20);
			
			action.moveToElement(webAutoToolMenu).perform();
			
			WaitUtilities.waitForElementVisible(driver, bycucumberTutMenu, 20);
			
			action.moveToElement(cucumberTutMenu).click().perform();
			
			WaitUtilities.waitForPageTitleIs(driver, "Learn Cucumber | Cucumber Tutorial for Beginners", 30);
			
			String aTitleName = driver.getTitle();
			
			if(aTitleName.equals(titleName))
			{
				
				Reporter.pass("Cucumber Tutorial for Beginners page should be Displayed Successfully.", "Cucumber Tutorial for Beginners page is Displayed Successfully.", driver, true);
				
				Log.info("-----------Cucumber Tutorial for Beginners Page is Opened. -------------");
				
			}else
			{
				Reporter.fail("Cucumber Tutorial for Beginners page should be Displayed Successfully.", "Cucumber Tutorial for Beginners page is not Displayed Successfully.", driver, false);
				
				
				Log.info("-----------Cucumber Tutorial for Beginners Page is not Opened.------------");
			}
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while Selecting Menu.---------", e);
			
		}
		
		
	}

}
