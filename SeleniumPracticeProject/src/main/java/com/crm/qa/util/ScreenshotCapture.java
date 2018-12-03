package com.crm.qa.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotCapture {
	
	/**
	 * This method will take screenshot,and return the path of image.
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws Exception
	 */
	
	synchronized public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir") + "\\test-output\\"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		
		
		return destination;
		
	}

}
