package com.crm.qa.util;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class ScreenshotCapture {
	
	/**
	 * This method will take screenshot,and return the path of image.
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws Exception
	 */
	
	synchronized public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		
		/*
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot) driver; File source =
		  ts.getScreenshotAs(OutputType.FILE);
		  
		  String destination = System.getProperty("user.dir") +
		  "\\test-output\\"+screenshotName+dateName+".png"; File finalDestination = new
		  File(destination); FileUtils.copyFile(source, finalDestination);
		 
		*/
		
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		String destination = System.getProperty("user.dir") + "\\test-output\\"+screenshotName+dateName+".png"; 
		File finalDestination = new File(destination);
		ImageIO.write(image, "png", finalDestination); 
		
		
		
		/*
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		Screenshot screenshot = new AShot().shootingStrategy(new ViewportPastingStrategy(1000)).takeScreenshot(driver);
		String destination = System.getProperty("user.dir") + "\\test-output\\"+screenshotName+dateName+".png"; 
		File finalDestination = new File(destination);
		ImageIO.write(screenshot.getImage(), "PNG",finalDestination);
		*/
		
		return destination;
		
	}

}
