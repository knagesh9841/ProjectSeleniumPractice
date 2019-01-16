package com.crm.qa.util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;



public class Utilities {
	
	static Logger Log = Logger.getLogger(Utilities.class.getName());
	
	/**
	 * This method will upload file using Robot class.
	 * @param filePath
	 * @param uploadBtn
	 * @param driver
	 */
	
	public static void uploadFileWithRobot (String filePath, WebElement uploadBtn, WebDriver driver) {

		try {



			if(driver instanceof ChromeDriver)
			{
				uploadBtn.click();

				StringSelection stringSelection = new StringSelection(filePath);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);

				Robot robot = new Robot();

				robot.delay(250);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.delay(150);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}else
			{
				uploadBtn.sendKeys(filePath);
			}

			Log.info("-----------File is Uploaded Successfully.-------------");

		} catch (Exception e) {

			Log.error("----------File is Not Uploaded Successfully.---------");

		}
	}
	
	/**
	 * This Method will Upload file using Autoit.
	 * @param filePath
	 * @param uploadBtn
	 * @param driver
	 */
	
	public static void uploadFileWithAutoIt (String filePath, WebElement uploadBtn, WebDriver driver) {

		try {



			if(driver instanceof ChromeDriver)
			{
				uploadBtn.click();

				Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\uploadFile.exe"+" "+filePath);
				
				Log.info("-----------File is Uploaded Successfully.-------------");
				
			}else
			{
				uploadBtn.sendKeys(filePath);
			}

			Log.info("-----------File is Uploaded Successfully.-------------");

		} catch (Exception e) {

			Log.error("----------File is Not Uploaded Successfully.---------");

		}
	}
	
	/**
	 * This Method will Upload file using Sikuli.
	 * @param filePath
	 * @param uploadBtn
	 * @param driver
	 * @param inputBox TODO
	 * @param openBtn TODO
	 */
	
	public static void uploadFileWithSikuli (String filePath, WebElement uploadBtn, WebDriver driver, String inputBox, String openBtn) {

		try {



			if(driver instanceof ChromeDriver)
			{
				Screen screen = new Screen();
		        Pattern fileInputTextBox = new Pattern(inputBox);
		        Pattern openButton = new Pattern(openBtn);
		        
				uploadBtn.click();

				screen.wait(fileInputTextBox, 20);
				screen.type(fileInputTextBox, filePath);
				screen.click(openButton);
				
				Log.info("-----------File is Uploaded Successfully.-------------");
				
			}else
			{
				uploadBtn.sendKeys(filePath);
				Log.info("-----------File is Uploaded Successfully.-------------");
			}

			

		} catch (Exception e) {

			Log.error("----------File is Not Uploaded Successfully.---------");

		}
	}
	
	
	/**
	 * This Method will Download file using Sikuli.
	 * @param downloadBtn
	 * @param driver
	 * @param saveBtn TODO
	 * @param closeBtn TODO
	 */
	
	public static void downloadFileWithSikuli (WebElement downloadBtn, WebDriver driver, String saveBtn, String closeBtn) {

		try {


				Screen screen = new Screen();
		        Pattern saveButton = new Pattern(saveBtn);
		        Pattern closeButton = new Pattern(closeBtn);
		        
				//downloadBtn.click();
		        Utilities.clickByJavaScriptExecutor(driver, downloadBtn);
		        WaitUtilities.waitForSleep(5000L);
		        
				screen.wait(saveButton, 20);
				screen.click(saveButton);
				screen.wait(closeButton, 20);
				screen.click(closeButton);
				
				Log.info("-----------File is Downloaded Successfully.-------------");
				
			
			

		} catch (Exception e) {

			Log.error("----------File is Not Downloaded Successfully.---------");

		}
	}
	
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
