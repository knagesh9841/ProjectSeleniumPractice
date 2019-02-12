package com.crm.qa.pages;

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

public class TooltipPage {
	
	//*********Page Variables*********

		private WebDriver driver;

		private static Logger Log = Logger.getLogger(TooltipPage.class.getName());


		//*********Constructor*********

		public TooltipPage(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}


		//*********Web Elements*********

		@FindBy(how=How.XPATH,using="//div[@class='tooltip']")
		@CacheLookup
		public WebElement mouseOverBtn;
		
		@FindBy(how=How.XPATH,using="//span[@class='tooltiptext']")
		@CacheLookup
		public WebElement tooltipMsg;
		
	

		//*********Page Methods*********
		
		
		public void verifyTooltipText(String eMessage)
		{

			try {
				driver.get(PropertyManager.getInstance().getConfigTimeData("tooltipurl"));

				Log.info("-----------Navigating to URL-------------");

				Utilities.maximizeWindow();
				WaitUtilities.waitForPageToBeLoad(driver);
				

				WaitUtilities.waitForPageTitleIs(driver, "Selenium Practise: How to automate tooltip in Selenium webdriver", 30);
				
				Actions action = new Actions(driver);
				action.moveToElement(mouseOverBtn).clickAndHold().build().perform();
				
				Log.info("-----------Mouse over the button-------------");
				
				WaitUtilities.waitForElementVisible(driver, bytooltipText, 20);
				
				String aMessage = tooltipMsg.getText();
				
				if(aMessage.equals(eMessage))
				{
					
					Reporter.pass("Tooltip Message should be "+eMessage+".", "Tooltip Message is "+aMessage+".", driver, true);
					Log.info("-----------Tooltip Message is matched-------------");
					
				}else
				{
					Reporter.fail("Tooltip Message should be "+eMessage+".", "Tooltip Message is "+aMessage+".", driver, false);
					Log.info("-----------Tooltip Message is not matched-------------");
				}
				
				
			} catch (Exception e) {
			
				Log.warn("-----------Exception Occured while verifying tooltip text.------------");
			}
		}

}
