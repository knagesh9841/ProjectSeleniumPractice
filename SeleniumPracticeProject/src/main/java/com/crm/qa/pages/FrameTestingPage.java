package com.crm.qa.pages;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.crm.qa.util.PropertyManager;
import com.crm.qa.util.Reporter;
import com.crm.qa.util.Utilities;
import com.crm.qa.util.WaitUtilities;

import static com.crm.qa.objectrepository.ObjectRepository.*;

public class FrameTestingPage {
	
		//*********Page Variables*********
	
		private WebDriver driver;
	    private static Logger Log = Logger.getLogger(FrameTestingPage.class.getName());
		HashMap<String, String> testData = new HashMap<String,String>();
		String testDataHolder;
		
		//*********Constructor*********
		
		public FrameTestingPage(WebDriver driver)
		{
			this.driver = driver;
		}
		
		//*********URL = https://chercher.tech/practice/frames-example-selenium-webdriver
		
		//*********Page Methods*********
		
		/**
		 * This method will use to find out element in IFrame and Perform operation.
		 * @param topicaName TODO
		 * @param select TODO
		 * @param animalName TODO
		 * @param headingName TODO
		 */
		
		public void verifyElementInIFrame(String topicaName, boolean select, String animalName, String headingName)
		{
			WebDriver TopFrameDriver,BottomFrameDriver;
			
			try {
				
				driver.get(PropertyManager.getInstance().getConfigTimeData("frameurl"));
				
				Log.info("-----------Navigating to URL-------------");
				
				Utilities.maximizeWindow();
				WaitUtilities.waitForPageToBeLoad(driver);
				
				WaitUtilities.waitForPageTitleIs(driver, "Frames Example in Selenium webdriver", 30);
				
				TopFrameDriver = driver.switchTo().frame(driver.findElement(byFrame1));
				
				BottomFrameDriver = TopFrameDriver.switchTo().frame(TopFrameDriver.findElements(byFrameElement).get(0));
				
				WebElement checkBox = BottomFrameDriver.findElement(byFramePageCheckBox);
				
				if(select)
				{
					if(!checkBox.isSelected())
					{
						checkBox.click();
						
						Reporter.info("Checkbox should be checked.", "Checkbox is checked.", BottomFrameDriver, false);
						
					}else
					{
						Reporter.info("Checkbox should be checked.", "Checkbox is allready checked.", BottomFrameDriver, false);
					}
				}else
				{
					if(checkBox.isSelected())
					{
						checkBox.click();

						Reporter.info("Checkbox should be unchecked.", "Checkbox is unchecked.", BottomFrameDriver, false);
					}else
					{
						Reporter.info("Checkbox should be unchecked.", "Checkbox is allready unchecked.", BottomFrameDriver, false);
					}
				}
				
				
				TopFrameDriver = BottomFrameDriver.switchTo().parentFrame();
				
				WebElement topicNameElement = TopFrameDriver.findElement(byFramePageTopicname);
				
				topicNameElement.sendKeys(topicaName);
				
				Reporter.info("Topic Name should be Set to "+topicaName+".", "Topic Name should is Set to "+topicaName+".", TopFrameDriver, false);
				
				TopFrameDriver = TopFrameDriver.switchTo().parentFrame();
				
				String actualheadingName = TopFrameDriver.findElement(byFrameTopicHeading).getText();
				
				
				if(actualheadingName.equals(headingName))
				{
					Assert.assertTrue(true);
					Reporter.info("Heading Name should be "+headingName+".", "Heading Name is "+actualheadingName+".", TopFrameDriver, false);
					
					Log.info("-----------Heading Name is "+headingName+".-------------");
				}else
				{
					Reporter.fail("Heading Name should be "+headingName+".", "Heading Name is "+actualheadingName+".", TopFrameDriver);
					Assert.assertTrue(false);
					
					Log.info("-----------heading Name is Not Matched.-------------");
				}
				
				
				
				TopFrameDriver = driver.switchTo().frame(driver.findElement(byFrame2));
				
				Select selectElement = new Select(TopFrameDriver.findElement(byFramePageAnimals));
				
				selectElement.selectByVisibleText(animalName);
				
				Reporter.info("Animal Name should be Set to "+animalName+".", "Animal Name should is Set to "+animalName+".", TopFrameDriver, true);
				
				
			} catch (Exception e) {
				
				Log.error("----------Exception Occured while Finding Element in IFrame---------", e);
				
			}
			
			
		}
		

}
