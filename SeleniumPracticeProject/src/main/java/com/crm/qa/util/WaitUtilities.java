package com.crm.qa.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WaitUtilities {
	
	static Logger Log = Logger.getLogger(WaitUtilities.class.getName());

	/**
	 * Wait for an element is present on the DOM of a page and visible.Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementisVisibleOrNot
	 */
	
	public static boolean waitForElementVisible(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		boolean sFlag =false;
		try {
	
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
	
			if(element!=null)
			{
				sFlag = true;
				Log.info("-----------Element is Visible in "+timeOutInSeconds+" seconds.-------------");
			}
			
			
			return sFlag;
	
		} catch (Exception e) {
	
				Log.error("----------Element is Not Visible in "+timeOutInSeconds+" seconds.---------");
			
			return sFlag;
		}
	
	}

	/**
	 * Wait for an element is either invisible or not present on the DOM.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementIsInvisibleOrNot
	 */
	
	public static boolean waitForElementInvisible(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			
			boolean elementIsPresent = wait.until(ExpectedConditions.invisibilityOfElementLocated(byElement));
			
			Utilities.Log.info("-----------Element is Invisible in "+timeOutInSeconds+" seconds.-------------");
			 
			return elementIsPresent;
			
		} catch (Exception e) {
			
			Utilities.Log.error("----------Element is not invisible in "+timeOutInSeconds+" seconds.---------");
			
			return false;
		}
	
	}

	/**
	 * Wait for an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementisPresentOrNot
	 */
	
	public static boolean waitForElementPresence(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		boolean sFlag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
			
			if(element!=null)
			{
				sFlag = true;
			}
			
		    Utilities.Log.info("-----------Element is found in DOM "+timeOutInSeconds+" seconds.-------------");
		    
			return sFlag;
			
		} catch (Exception e) {
			
			Utilities.Log.error("----------Element is not found in DOM "+timeOutInSeconds+" seconds.---------");
			
			return sFlag;
		}
	
	}

	/**
	 * Wait for  an element is visible and enabled such that you can click it.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementisClickableOrNot
	 */
	
	
	public static boolean waitForElementisClickable(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		boolean sFlag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byElement));
			
			if(element!=null)
			{
				sFlag = true;
			}
			
			Utilities.Log.info("-----------Element is found in "+timeOutInSeconds+" seconds.Click operation can be performed.-------------");
			
			return sFlag;
			
		} catch (Exception e) {
			
			Utilities.Log.error("----------Element is not found in "+timeOutInSeconds+" seconds.Click operation cannot be performed---------");
			
			return sFlag;
		}
	
	}

	/**
	 * Wait for the title of a page.
	 * @param driver
	 * @param pageTitle
	 * @param timeOutInSeconds
	 * @return PageWithTitle
	 */
	
	
	public static boolean waitForPageTitleIs(WebDriver driver,String pageTitle,int timeOutInSeconds)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			boolean titlePresent = wait.until(ExpectedConditions.titleIs(pageTitle));
			Utilities.Log.info("-----------Page with Title "+pageTitle+" is found in "+timeOutInSeconds+" seconds.-------------");
			return titlePresent;
	
		} catch (Exception e) {
	
			Utilities.Log.error("-----------Page with Title "+pageTitle+" is not found in "+timeOutInSeconds+" seconds.-------------");
	
			return false;
		}
	
	}

	/**
	 * Wait for alert is present or Not.
	 * @param driver
	 * @param timeOutInSeconds
	 * @return AlertIsPresentOrNot
	 */
	
	
	public static boolean waitForAlertIsPresent(WebDriver driver,int timeOutInSeconds)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Utilities.Log.info("-----------Alert is found in "+timeOutInSeconds+" seconds.-------------");
			return true;
			
		} catch (Exception e) {
			
			Utilities.Log.error("----------Alert  is not found in "+timeOutInSeconds+" seconds.---------");
			
			return false;
		}
	
	}

	/**
	 * Wait for an All element is present on the DOM of a page and visible.Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
	 * @param driver
	 * @param element
	 * @param timeOutInSeconds TODO
	 * @return
	 */
	public static boolean waitForAllElementVisible(WebDriver driver, By element, int timeOutInSeconds)
	{
		List<WebElement> Pageelements =null;
		boolean sFlag = false;
		try {
	
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			Pageelements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
	
			if(Pageelements!=null)
			{
				sFlag = true;
			}
			
			Utilities.Log.info("-----------All Elements are Visible in "+timeOutInSeconds+" seconds.-------------");
			
			return sFlag;
	
	
	
		} catch (Exception e) {
	
			Utilities.Log.error("----------All Elements are Not Visible in "+timeOutInSeconds+" seconds.---------");
	
			return sFlag;
		}
	
	}

	/**
	 * Wait for all element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * @param driver
	 * @param element
	 * @param timeOutInSeconds TODO
	 * @return
	 */
	public static boolean waitForAllElementsPresence(WebDriver driver, By element, int timeOutInSeconds)
	{
		List<WebElement> Pageelements =null;
		boolean sFlag = false;
	
		try {
	
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			Pageelements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
	
			if(Pageelements!=null)
			{
				sFlag = true;
			}
			
			Utilities.Log.info("-----------All Elements are Visible in DOM "+timeOutInSeconds+" seconds.-------------");
			
			return sFlag;
	
	
	
		} catch (Exception e) {
	
			Utilities.Log.error("----------All Elements are Not Visible in DOM "+timeOutInSeconds+" seconds.---------");
	
			return sFlag;
		}
	
	}

	/**
	 * This method use to wait for element using Fluentwait and it will return true if element is Displayed.
	 * @param driver TODO
	 * @param elementToFind TODO
	 * @return
	 */
	
	public static boolean checkForElementDisplayedusingFluentWait(WebDriver driver, By elementToFind)
	{
		boolean sFlag = false;
		 try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			            .withMessage("Timeout occured!") 
			            .ignoring(NoSuchElementException.class); 
			 
			 Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>()
				{
					public Boolean apply(WebDriver arg0) {
						WebElement element = arg0.findElement(elementToFind);
						
						if(element!=null)
						{
							return true;
						}
						return false;
					}
				};
	
				sFlag = wait.until(function);
				
				Utilities.Log.info("-----------Element is found.-------------");
				
		} catch (Exception e) {
			
			Utilities.Log.error("----------Element is Not Found "+e.getMessage()+".---------");
			
		}
		 
		 return sFlag;
	}

	/**
	 * This method use to wait for element using Fluentwait and it will return element if element is Displayed.
	 * @param driver TODO
	 * @param elementToFind TODO
	 * @return
	 */
	
	public static WebElement VerifyForElementDisplayedusingFluentWait(WebDriver driver, By elementToFind)
	{
		WebElement element =null;
		 try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			            .withMessage("Timeout occured!") 
			            .ignoring(NoSuchElementException.class); 
			 
			 Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>()
				{
					public WebElement apply(WebDriver arg0) {
						WebElement element = arg0.findElement(elementToFind);
						
						if(element!=null)
						{
							return element;
						}
						return element;
					}
				};
	
				element = wait.until(function);
				
				Utilities.Log.info("-----------Element is found.-------------");
				
		} catch (Exception e) {
			
			Utilities.Log.error("----------Element is Not Found "+e.getMessage()+".---------");
			
		}
		 
		 return element;
	}

	/**
	 * This method use to wait for page to load.
	 * @param driver TODO
	 * @return
	 */
	
	public static boolean waitForPageToBeLoad(WebDriver driver)
	{
		boolean sFlag = false;
		 try {
			 WebDriverWait wait = new WebDriverWait(driver, 30);
			 
			 ExpectedCondition<Boolean> pageLoadCondition = new
		                ExpectedCondition<Boolean>() {
		                    public Boolean apply(WebDriver driver) {
		                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
		                    }
		                };
		        
		        
				sFlag = wait.until(pageLoadCondition);
				
				Utilities.Log.info("-----------Page is Loaded Successfully.-------------");
				
		} catch (Exception e) {
			
			Utilities.Log.error("----------Page is Not Loaded Successfully..---------");
			
		}
		 
		 return sFlag;
	}

}
