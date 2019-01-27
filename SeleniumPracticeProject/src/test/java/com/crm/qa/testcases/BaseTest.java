package com.crm.qa.testcases;

import org.testng.annotations.Parameters;
import com.crm.qa.pages.AddressPage;
import com.crm.qa.pages.DownloadPage;
import com.crm.qa.pages.FrameTestingPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.MenuSelectionPage;
import com.crm.qa.pages.WindowTestingPage;
import com.crm.qa.util.BrowserFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
 
	WebDriver driver;
	LoginPage loginPage_Object;
	HomePage homePage_Object;
	AddressPage addrsPage_Object;
	FrameTestingPage framepage_Object;
	WindowTestingPage windowpage_Object;
	MenuSelectionPage menuSelectPage_Object;
	DownloadPage downloadPage_Object;
	private static Logger Log = Logger.getLogger(BaseTest.class.getName());
	
  @BeforeClass
  @Parameters({ "browser" })
  public synchronized void setUp(String browser) {
	  
	  
	  BrowserFactory.setDriver(browser);
	  driver = BrowserFactory.getDriver();
	  loginPage_Object = new LoginPage(driver);
	  homePage_Object = new HomePage(driver);
	  addrsPage_Object = new AddressPage(driver);
	  framepage_Object = new FrameTestingPage(driver);
	  windowpage_Object = new WindowTestingPage(driver);
	  menuSelectPage_Object = new MenuSelectionPage(driver);
	  downloadPage_Object = new DownloadPage(driver);
	  Log.info("-----------Page object is initialized.-------------");
	  
	  
  }

  @AfterClass
  public synchronized void tearDown() {
	  driver.quit();
	  Log.info("-----------Browser is Closed.-------------");
  }

}
