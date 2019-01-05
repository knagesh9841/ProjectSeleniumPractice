package com.crm.qa.util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;



public class Utilities {
	
	private static Logger Log = Logger.getLogger(Utilities.class.getName());
	
	/**
	 * Wait for an element is present on the DOM of a page and visible.Visibility means that the element is not only displayed but also has a height and width that isgreater than 0.
	 * @param driver
	 * @param byElement
	 * @param timeOutInSeconds
	 * @return ElementisVisibleOrNot
	 */
	
	public static boolean waitForElementVisible(WebDriver driver,By byElement,int timeOutInSeconds)
	{
		try {

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));

			Log.info("-----------Element is Visible in "+timeOutInSeconds+" seconds.-------------");
			
			return element.isDisplayed();

		} catch (Exception e) {

			Log.error("----------Element is Not Visible in "+timeOutInSeconds+" seconds.---------");
			
			return false;
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
			
			Log.info("-----------Element is Invisible in "+timeOutInSeconds+" seconds.-------------");
			 
			return elementIsPresent;
			
		} catch (Exception e) {
			
			Log.error("----------Element is not invisible in "+timeOutInSeconds+" seconds.---------");
			
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
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
			
		    Log.info("-----------Element is found in DOM "+timeOutInSeconds+" seconds.-------------");
		    
			return element.isDisplayed();
			
		} catch (Exception e) {
			
			Log.error("----------Element is not found in DOM "+timeOutInSeconds+" seconds.---------");
			return false;
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
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byElement));
			
			Log.info("-----------Element is found in "+timeOutInSeconds+" seconds.Click operation can be performed.-------------");
			
			return element.isDisplayed();
			
		} catch (Exception e) {
			
			Log.error("----------Element is not found in "+timeOutInSeconds+" seconds.Click operation cannot be performed---------");
			
			return false;
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
			Log.info("-----------Page with Title "+pageTitle+" is found in "+timeOutInSeconds+" seconds.-------------");
			return titlePresent;

		} catch (Exception e) {

			Log.error("-----------Page with Title "+pageTitle+" is not found in "+timeOutInSeconds+" seconds.-------------");

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
			
			Log.info("-----------Alert is found in "+timeOutInSeconds+" seconds.-------------");
			return true;
			
		} catch (Exception e) {
			
			Log.error("----------Alert  is not found in "+timeOutInSeconds+" seconds.---------");
			
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

		try {

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			Pageelements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));

			Log.info("-----------All Elements are Visible in "+timeOutInSeconds+" seconds.-------------");
			
			return Pageelements.get(0).isDisplayed();



		} catch (Exception e) {

			Log.error("----------All Elements are Not Visible in "+timeOutInSeconds+" seconds.---------");

			return false;
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

		try {

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			Pageelements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));

			Log.info("-----------All Elements are Visible in DOM "+timeOutInSeconds+" seconds.-------------");
			
			return Pageelements.get(0).isDisplayed();



		} catch (Exception e) {

			Log.error("----------All Elements are Not Visible in DOM "+timeOutInSeconds+" seconds.---------");

			return false;
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
				
				Log.info("-----------Element is found.-------------");
				
		} catch (Exception e) {
			
			Log.error("----------Element is Not Found "+e.getMessage()+".---------");
			
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
				
				Log.info("-----------Element is found.-------------");
				
		} catch (Exception e) {
			
			Log.error("----------Element is Not Found "+e.getMessage()+".---------");
			
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
				
				Log.info("-----------Page is Loaded Successfully.-------------");
				
		} catch (Exception e) {
			
			Log.error("----------Page is Not Loaded Successfully..---------");
			
		}
		 
		 return sFlag;
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
