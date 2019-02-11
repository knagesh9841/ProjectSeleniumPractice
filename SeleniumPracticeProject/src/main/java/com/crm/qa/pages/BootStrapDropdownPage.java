package com.crm.qa.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.util.PropertyManager;
import com.crm.qa.util.Reporter;
import com.crm.qa.util.Utilities;
import com.crm.qa.util.WaitUtilities;

public class BootStrapDropdownPage {
	
	//*********Page Variables*********

		private WebDriver driver;

		private static Logger Log = Logger.getLogger(BootStrapDropdownPage.class.getName());


		//*********Constructor*********

		public BootStrapDropdownPage(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}


		//*********Web Elements*********

		@FindBy(how=How.XPATH,using="//button[@id='menu1']")
		@CacheLookup
		public WebElement menuBtn;
		
		@FindBy(how=How.XPATH,using="//ul[@class='dropdown-menu']//li/a")
		@CacheLookup
		public List<WebElement> menus;


		//*********Page Methods*********
		
		/**
		 * This method is used to select Bootstrap Dropdown
		 * @param menuOption
		 */
		
		public void verifyBootstrapDropdown(String menuOption)
		{
			try {
				driver.get(PropertyManager.getInstance().getConfigTimeData("bootstrapdropdownurl"));

				Log.info("-----------Navigating to URL-------------");

				Utilities.maximizeWindow();
				WaitUtilities.waitForPageToBeLoad(driver);
				
				WaitUtilities.waitForPageTitleIs(driver, "Selenium Practise: Bootstrap Dropdown Example for Selenium", 30);
				
				menuBtn.click();
				
				for(int cnt=0;cnt<menus.size();cnt++)
				{
					if(menus.get(cnt).getText().equals(menuOption))
					{
						menus.get(cnt).click();
						Log.info("-----------Selected "+menuOption+" option.-------------");
						Reporter.info(""+menuOption+" Menu should be selected.", ""+menuOption+" Menu is selected.", driver, true);
					}
				}
			} catch (Exception e) {
				Log.warn("-----------Exception Occured while Selecting Bootstrap Dropdown menu.----------");
			}
			
			
		}


}
