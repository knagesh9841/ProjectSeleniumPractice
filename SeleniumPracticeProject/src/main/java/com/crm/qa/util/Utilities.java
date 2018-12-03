package com.crm.qa.util;

import org.openqa.selenium.Dimension;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Utilities {
	
	private static Logger Log = Logger.getLogger(Utilities.class.getName());
	
	/**
	 * Wait for checking that an element is present on the DOM of a page and visible.Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementisVisibleOrNot
	 */
	
	public static boolean waitForElementVisible(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
			WebElement element = driver.findElement(byElement);
			
			 Log.info("-----------Element is found in "+timeOutInSeconds+" seconds.-------------");
			 
			return element.isDisplayed();
			
		} catch (Exception e) {
			
			Log.error("----------Element is not found in "+timeOutInSeconds+" seconds.---------", e);
			return false;
		}
	
	}
	
	/**
	 * Wait for checking that an element is either invisible or not present on the DOM.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementIsInvisibleOrNot
	 */
	
	public static boolean waitForElementInvisible(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byElement));
			WebElement element = driver.findElement(byElement);
			
			 Log.info("-----------Element is found in "+timeOutInSeconds+" seconds.-------------");
			 
			return element.isDisplayed();
			
		} catch (Exception e) {
			
			Log.error("----------Element is not found in "+timeOutInSeconds+" seconds.---------", e);
			
			return false;
		}
	
	}
	
	
	/**
	 * Wait for checking that an element is present on the DOM of a page. This does not necessarily mean that the element is visible.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementisPresentOrNot
	 */
	
	public static boolean waitForElementPresence(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
			WebElement element = driver.findElement(byElement);
		    Log.info("-----------Element is found in "+timeOutInSeconds+" seconds.-------------");
			return element.isDisplayed();
			
		} catch (Exception e) {
			
			Log.error("----------Element is not found in "+timeOutInSeconds+" seconds.---------", e);
			return false;
		}
	
	}
	
	
	/**
	 * Wait for checking an element is visible and enabled such that you can click it.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementisClickableOrNot
	 */
	
	
	public static boolean waitForElementisClickable(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(byElement));
			WebElement element = driver.findElement(byElement);
			Log.info("-----------Element is found in "+timeOutInSeconds+" seconds.-------------");
			return element.isDisplayed();
			
		} catch (Exception e) {
			
			Log.error("----------Element is not found in "+timeOutInSeconds+" seconds.---------", e);
			
			return false;
		}
	
	}
	
	
	/**
	 * Wait for checking the title of a page.
	 * @param driver
	 * @param pageTitle
	 * @param timeOutInSeconds
	 * @return PageWithTitle
	 */
	
	
	public static boolean waitForPageTitleIs(WebDriver driver,String pageTitle,int timeOutInSeconds)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.titleIs(pageTitle));
			 Log.info("-----------Page with Title "+pageTitle+" is found in "+timeOutInSeconds+" seconds.-------------");
			return true;
			
		} catch (Exception e) {
			
			Log.error("-----------Page with Title \"+pageTitle+\" is not found in \"+timeOutInSeconds+\" seconds.-------------", e);
			
			return false;
		}
	
	}
	
	/**
	 * This will maximize the window
	 */
	
	public static void maximizeWindow()
	{
		BrowserFactory.getDriver().manage().window().maximize();
		Dimension dim = new Dimension(Integer.parseInt(PropertyManager.getInstance().getConfigTimeData("window-width")),Integer.parseInt(PropertyManager.getInstance().getConfigTimeData("window-height")));
		Point pnt = new Point(0, 0);
		BrowserFactory.getDriver().manage().window().setSize(dim);
		BrowserFactory.getDriver().manage().window().setPosition(pnt);
		BrowserFactory.getDriver().manage().timeouts().implicitlyWait(Long.valueOf(PropertyManager.getInstance().getConfigTimeData("implicitlywait")), TimeUnit.SECONDS);
		BrowserFactory.getDriver().manage().deleteAllCookies();
		Log.info("-----------Window is Maximized.-------------");
	}
	
	
	/**
	 * This method will kill Browser error window.
	 */
	protected static void killBrowserErrorWindow()
	{
		try {
			
			Process processErrWin = Runtime.getRuntime().exec("taskkill /F /T /IM WerFault.exe");
			processErrWin.waitFor();
			
			processErrWin = Runtime.getRuntime().exec("taskkill /F /T /IM WerFault.exe *32");
			processErrWin.waitFor();
			
		} catch (Exception e) {
			
			Log.warn("------Failed to kill Browser error window.------");
		}
	}
	
	
	/**
	 * This method will kill Browser and Driver Process.
	 * @param browserName 
	 */
	
	protected static void killBrowserAndDriver(String browserName)
	{
		String browserProcess = null,driverProcess = null;
		
		switch(browserName)
		{
		    case "ie":
		    	browserProcess = "iexplore";
		    	driverProcess = "IEDriverServer.exe";
		    	break;
		    case "chrome":
		    	browserProcess = "chrome";
		    	driverProcess = "chromedriver.exe";
		    	break;
		    case "edge":
		    	browserProcess = "MicrosoftEdge";
		    	driverProcess = "MicrosoftWebDriver.exe";
		    	break;
		    case "firefox":
		    	browserProcess = "firefox";
		    	driverProcess = "GeckoDriver.exe";
		    	break;
		    default:
		    	Log.warn("------Entered Wrong Browser Name.------");
		    	break;
		}
		
		try {
			
			killBrowserErrorWindow();
			
			Process processDriver = Runtime.getRuntime().exec("taskkill /F /T /IM "+ driverProcess);
			Process processBrowser = Runtime.getRuntime().exec("taskkill /F /T /IM "+ browserProcess + ".exe");
			
			processDriver.waitFor();
			processBrowser.waitFor();
			
			Log.info("Driver process "+driverProcess+" and Browser process "+browserProcess+" is killed successfully.");
			
		} catch (Exception e) {
			
			Log.warn("------Failed to kill Browser or Driver.------");
		}
		
	}

}
