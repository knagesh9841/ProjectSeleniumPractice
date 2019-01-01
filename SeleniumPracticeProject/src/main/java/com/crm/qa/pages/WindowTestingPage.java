package com.crm.qa.pages;

import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.listeners.TestListener;
import com.crm.qa.util.PropertyManager;
import com.crm.qa.util.Utilities;

public class WindowTestingPage {
	
			//*********Page Variables*********
	
			private WebDriver driver;
		    private static Logger Log = Logger.getLogger(WindowTestingPage.class.getName());
			HashMap<String, String> testData = new HashMap<String,String>();
			String testDataHolder;
			
			//*********Constructor*********
			
			public WindowTestingPage(WebDriver driver)
			{
				this.driver = driver;
				PageFactory.initElements(driver, this);
			}
			
			//*********URL = https://chercher.tech/python/windows-selenium-python
			
			//*********Web Elements*********
			
			@FindBy(how=How.XPATH,using="//a[@id='two-window']")
			@CacheLookup
			public WebElement newWindowBtn;
			
			//*********Page Methods*********
			
			/**
			 * This method will return true if it is Instance of Chrome Driver
			 * @return
			 */
			
			public boolean isChrome()
			{
				return driver instanceof ChromeDriver;
			}
			
			/**
			 * This method will return true if it is Instance of IE Driver
			 * @return
			 */
			
			public boolean isIE()
			{
				return driver instanceof InternetExplorerDriver;
			}
			
			/**
			 * This method will return true if it is Instance of Firefox Driver
			 * @return
			 */
			
			public boolean isFirefox()
			{
				return driver instanceof FirefoxDriver;
			}
			
			/**
			 * This method will return true if it is Instance of Edge Driver
			 * @return
			 */
			
			public boolean isEdge()
			{
				return driver instanceof EdgeDriver;
			}
			
			/**
			 * This method is used to switch window using Title.
			 * @param titleName
			 */
			
			public void switchToWindowUsingTitle(String titleName)
			{
				Set<String> windowhandle = driver.getWindowHandles();
				
				for(String handle:windowhandle)
				{
					WebDriver windowDriver = driver.switchTo().window(handle);
					String aTitle = windowDriver.getTitle();
					
					if(aTitle.equals(titleName))
					{
						Log.info("----------Switched to Window using Title Successfull.-----------");
						TestListener.info("New opened Window Title should be "+titleName+".", "New opened Window Title is "+aTitle+".", windowDriver, true);
						driver.close();
					}
				}
			}
			
			/**
			 * This method is used to switch to Tab using title.
			 * @param titleName
			 */
			
			public void switchToTabUsingTitle(String titleName)
			{
				
				Set<String> windowhandle = driver.getWindowHandles();
				
				for(String handle:windowhandle)
				{
					WebDriver windowDriver = driver.switchTo().window(handle);
					String aTitle = windowDriver.getTitle();
					
					if(aTitle.equals(titleName))
					{
						
						Log.info("--------------Switched to Tab using Title Successfull.---------------------");
						TestListener.info("New opened Tab Title should be "+titleName+".", "New opened tab Title is "+aTitle+".", windowDriver, true);
						driver.close();
					}
				}
			}
			
			/**
			 * This method is used to Window Switch testing
			 * @param switchTab
			 */
			
			public void windowSwitchTest(boolean switchTab)
			{
				try {
					
					driver.get(PropertyManager.getInstance().getConfigTimeData("url"));
					
					Log.info("-----------Navigating to URL-------------");
					
					Utilities.waitForPageTitleIs(driver, "SwitchTo to Windows in selenium python", 30);
					
					String mainWindow = driver.getWindowHandle();
					
					if(isChrome())
					{
						if(switchTab)
						{
							newWindowBtn.click();
							Utilities.waitForPageTitleIs(driver, "Google", 30);
							switchToTabUsingTitle("Google");
							
						}else
						{
							newWindowBtn.click();
							Utilities.waitForPageTitleIs(driver, "Google", 30);
							switchToWindowUsingTitle("Google");
						}
						
					}else if(isIE())
					{
						if(switchTab)
						{
							newWindowBtn.click();
							Utilities.waitForPageTitleIs(driver, "Google", 30);
							switchToTabUsingTitle("Google");
							
						}else
						{
							newWindowBtn.click();
							Utilities.waitForPageTitleIs(driver, "Google", 30);
							switchToWindowUsingTitle("Google");
						}
						
					}else if(isFirefox())
					{
						if(switchTab)
						{
							newWindowBtn.click();
							Utilities.waitForPageTitleIs(driver, "Google", 30);
							switchToTabUsingTitle("Google");
							
						}else
						{
							newWindowBtn.click();
							Utilities.waitForPageTitleIs(driver, "Google", 30);
							switchToWindowUsingTitle("Google");
						}
					}else if(isEdge())
					{
						if(switchTab)
						{
							newWindowBtn.click();
							Utilities.waitForPageTitleIs(driver, "Google", 30);
							switchToTabUsingTitle("Google");
							
						}else
						{
							newWindowBtn.click();
							Utilities.waitForPageTitleIs(driver, "Google", 30);
							switchToWindowUsingTitle("Google");
						}

					}
					
					driver.switchTo().window(mainWindow);
					Log.info("-----------Switched to Main Window.----------------");
					TestListener.info("should be Switch to Main window.", "Successfully Switch to main window.", driver, true);
					
				} catch (Exception e) {
					Log.error("----------Exception Occured while Switching to Window/Tab---------", e);
				}
					
			}
			
}
