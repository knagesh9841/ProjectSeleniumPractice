package com.crm.qa.util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class Utilities {
	
	static Logger Log = Logger.getLogger(Utilities.class.getName());
	
	/**
	 * This method will scroll Page using Robot class.
	 * @param isUpScroll TODO
	 */
	
	public static void scrollPageUsingRobotClass(boolean isUpScroll)
	{
		try {
			Robot robot = new Robot();

			if(isUpScroll)
			{
				robot.keyPress(KeyEvent.VK_PAGE_UP);
				robot.keyRelease(KeyEvent.VK_PAGE_UP);
			}else
			{
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			}
			
			Log.info("-----------Page is Scrolled Successfully.-------------");
			
		} catch (Exception e) {
			Log.error("----------Page is Not Scrolled Successfully..---------");
		}
	}
	
	/**
	 * This method will scroll Page using Robot class.
	 * @param driver TODO
	 * @param value TODO
	 * @param noOfLines TODO
	 */
	
	public static void scrollPageUsingJavaScript(WebDriver driver, int value, boolean noOfLines)
	{
		try {
			

			JavascriptExecutor js=(JavascriptExecutor) driver;
			
			if(noOfLines)
			{
				js.executeScript("window.scrollByLines("+value+")", "");
			}else
			{
				js.executeScript("window.scrollBy(0,"+value+")", "");
			}
			
			
			Log.info("-----------Page is Scrolled Successfully.-------------");
			
		} catch (Exception e) {
			Log.error("----------Page is Not Scrolled Successfully..---------");
		}
	}
	
	
	/**
	 * This method is used to click by JavaScriptExecutor.
	 * @param driver TODO
	 * @param elementToClick TODO
	 */
	
	public static void clickByJavaScriptExecutor(WebDriver driver, WebElement elementToClick)
	{
		try {
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", elementToClick);
			
		} catch (Exception e) {
			Log.error("---------- Exception Occured while Click by JavaScriptExecutor.---------");
		}
	}
	
	
	
	/**
	 * This method is used to ScrollInto View By JavaScriptExecutor.
	 * @param driver TODO
	 * @param elementToScroll TODO
	 */
	
	public static void scrollIntoViewByJavaScriptExecutor(WebDriver driver, WebElement elementToScroll)
	{
		try {
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);", elementToScroll);
			
		} catch (Exception e) {
			Log.error("---------- Exception Occured while ScrollInto View By JavaScriptExecutor.---------");
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
		Log.info("-----------Window is Maximized.-------------");
		BrowserFactory.getDriver().manage().timeouts().implicitlyWait(Long.valueOf(PropertyManager.getInstance().getConfigTimeData("implicitlywait")), TimeUnit.SECONDS);
		Log.info("-----------implicitly Wait is set to "+PropertyManager.getInstance().getConfigTimeData("implicitlywait")+" Seconds.-------------");
		BrowserFactory.getDriver().manage().timeouts().pageLoadTimeout(Long.valueOf(PropertyManager.getInstance().getConfigTimeData("pageLoadTimeout")), TimeUnit.SECONDS);
		Log.info("-----------Page Load Timeout is set to "+PropertyManager.getInstance().getConfigTimeData("pageLoadTimeout")+" Seconds.-------------");
		BrowserFactory.getDriver().manage().timeouts().setScriptTimeout(Long.valueOf(PropertyManager.getInstance().getConfigTimeData("scriptTimeOut")), TimeUnit.SECONDS);
		Log.info("-----------Script Timeout is set to "+PropertyManager.getInstance().getConfigTimeData("scriptTimeOut")+" Seconds.-------------");
		BrowserFactory.getDriver().manage().deleteAllCookies();
		Log.info("-----------Cookies Deleted.-------------");
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
