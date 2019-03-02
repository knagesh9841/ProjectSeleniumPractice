package com.crm.qa.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	 private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	 private static Logger Log = Logger.getLogger(BrowserFactory.class.getName());
	 
	 /**
	  * Set the Driver as per Browser Name passed
	  * @param browsername
	  */
	
	public synchronized static void setDriver(String browsername)
	{
		
		
		try {
			
			Utilities.killBrowserAndDriver(browsername);
			
			if(browsername.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				tlDriver = ThreadLocal.withInitial(() -> new FirefoxDriver(OptionsManager.getFirefoxOptions()));
				
				/*//For Grid Usage
				try { 
					tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), OptionsManager.getFirefoxOptions())); }
				 catch (MalformedURLException e) 
				 { 
				 	e.printStackTrace(); 
				 }
				 */
				
				Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");
				 
			}
			else if(browsername.equalsIgnoreCase("chrome"))
			{
				 WebDriverManager.chromedriver().setup();
				 tlDriver = ThreadLocal.withInitial(() -> new ChromeDriver(OptionsManager.getChromeOptions()));
				 
				  /*//For Grid Usage
		            try {
		                tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), OptionsManager.getChromeOptions()));
		            } catch (MalformedURLException e) {
		                e.printStackTrace();
		            }*/
				 
				 Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");
				 
			}
			else if(browsername.equalsIgnoreCase("ie"))
			{
				//WebDriverManager.iedriver().setup();
				WebDriverManager.iedriver().architecture(io.github.bonigarcia.wdm.Architecture.X32).setup();
				tlDriver = ThreadLocal.withInitial(() -> new InternetExplorerDriver(OptionsManager.getIEOptions()));
				
				 /*//For Grid Usage
	            try {
	                tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), OptionsManager.getIEOptions()));
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            }*/
				
				Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");
			}
			else if(browsername.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				tlDriver = ThreadLocal.withInitial(() -> new EdgeDriver(OptionsManager.getEdgeOptions()));
				
				 /*//For Grid Usage
	            try {
	                tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), OptionsManager.getEdgeOptions()));
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            }*/
				
				Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");
				
			}else
			{
				Log.info("-----------Wrong browser Name.-------------");
			}
		} catch (Exception e) {
		
			Log.error("----------Exception occured when "+browsername+" Browser is initialized.---------", e);
		}
		
		
		
	}
	
	
	/**
	 * Get driver from tlDriver
	 * @return
	 */
	
    public synchronized static WebDriver getDriver () {
        return tlDriver.get();
    }
	
}
