package com.crm.qa.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

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
				Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");
				 
			}
			else if(browsername.equalsIgnoreCase("chrome"))
			{
				 WebDriverManager.chromedriver().setup();
				 tlDriver = ThreadLocal.withInitial(() -> new ChromeDriver(OptionsManager.getChromeOptions()));
				 Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");
				 
			}
			else if(browsername.equalsIgnoreCase("ie"))
			{
				//WebDriverManager.iedriver().setup();
				WebDriverManager.iedriver().architecture(io.github.bonigarcia.wdm.Architecture.X32).setup();
				tlDriver = ThreadLocal.withInitial(() -> new InternetExplorerDriver(OptionsManager.getIEOptions()));
				Log.info("-----------"+browsername+" Browser is initialized successfully.-------------");
			}
			else if(browsername.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				tlDriver = ThreadLocal.withInitial(() -> new EdgeDriver(OptionsManager.getEdgeOptions()));
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
