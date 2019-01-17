package com.crm.qa.pages;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.qa.listeners.TestListener;
import com.crm.qa.util.PropertyManager;
import com.crm.qa.util.Utilities;
import com.crm.qa.util.WaitUtilities;

public class DownloadPage {
	
	//*********Page Variables*********
	
		private WebDriver driver;
	    private static Logger Log = Logger.getLogger(DownloadPage.class.getName());
		HashMap<String, String> testData = new HashMap<String,String>();
		String testDataHolder;
		String downloadDir = System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles";
		String ieEdgeDownloadDir = System.getProperty("user.home")+ File.separator + "Downloads";
		
		//*********Constructor*********
		
		public DownloadPage(WebDriver driver)
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		// url = https://www.toolsqa.com/automation-practice-form/
		
		//*********Web Elements*********
		
		@FindBy(how=How.XPATH,using="//a[text()='Test File to Download']")
		@CacheLookup
		public WebElement downloadBtn;
		
		//*********Page Methods*********
		
		/**
		 * This Method will download the file.
		 */
		
		public void verifyDownloadedFile()
		{
			driver.get(PropertyManager.getInstance().getConfigTimeData("downloadurl"));
			
			Log.info("-----------Navigating to URL-------------");
			
			//Utilities.maximizeWindow();
			WaitUtilities.waitForPageToBeLoad(driver);
			
			WaitUtilities.waitForPageTitleIs(driver, "Demo Form for practicing Selenium Automation", 30);
			
			deleteDownloadFolderPath();
			String path = createFolderPath();
			
			//Firefox and Chrome Browser
			//downloadBtn.click();
			//WaitUtilities.waitForSleep(5000L);
			
			
			// Edge Browser
			//Utilities.downloadFileWithSikuli(downloadBtn, driver, System.getProperty("user.dir") + "\\downloadImages\\SaveBtn.PNG", System.getProperty("user.dir") + "\\downloadImages\\CloseBtn.PNG");
			//WaitUtilities.waitForSleep(10000L);
			
			// IE Browser
			
			Utilities.scrollIntoViewByJavaScriptExecutor(driver, downloadBtn);
			Utilities.downloadFileWithSikuli(downloadBtn, driver, System.getProperty("user.dir") + "\\downloadImages\\SaveBtnIE.PNG", System.getProperty("user.dir") + "\\downloadImages\\CloseBtnIE.PNG");
			WaitUtilities.waitForSleep(10000L);
			
			export(path);
			verifyFileisCopiedOrNot(path+ File.separator + "Test-File-to-Download.xlsx");
			
		}
		
		
		/**
		 * This method will create Existing folder and Create New Folder.
		 * @return
		 */
		
		public String createFolderPath()
		{
			String projectPath = null;
			try {
				projectPath = System.getProperty("user.dir");
				projectPath = projectPath.split(":")[0];
				projectPath = projectPath+":\\Export";
				File file = new File(projectPath);

				FileUtils.deleteDirectory(file);
				Log.info("-----------Folder Deleted Successfully.-------------");

				file.mkdir();
				Log.info("-----------Folder Created Successfully.-------------");
				return projectPath;
			} catch (Exception e) {
				Log.warn("-----------Exception Occured while Creating/Deleting Folder.-----------");
				return projectPath;
			}
		}
		
		/**
		 * This method will delete Download Directory 
		 */
		
		public void deleteDownloadFolderPath()
		{
			try {
				File file = new File(downloadDir);
				FileUtils.deleteDirectory(file);
				Log.info("-----------Downloaded Folder is Deleted Successfully.-------------");
			} catch (IOException e) {
				Log.warn("-----------Downloaded Folder is Not Deleted Successfully.------------------");
			}
		}
		
		
		/**
		 * This method will copy file from Downloaded Directory to Specified Directory.
		 * @param filePath
		 */
		
		public void export(String filePath)
		{
			try {
				if(driver instanceof ChromeDriver || driver instanceof FirefoxDriver)
				{
					File path = new File(downloadDir);
					List<File> sortedfiles = Arrays.asList(path.listFiles()).stream().sorted((file1,file2) -> {return file1.lastModified() > file2.lastModified() ? -1 : 1;}).collect(Collectors.toList());
					
					if(sortedfiles.size() > 0)
					{
						File downloadedFile = sortedfiles.get(0);
						File destPath = new File(filePath);
						try {
							FileUtils.copyFileToDirectory(downloadedFile, destPath);
							
							Log.info("-----------File Copied Successfully.-------------");
						} catch (IOException e) {
							Log.warn("-----------Unable to copy File.");
						}
					}
				}else
				{
					File path = new File(ieEdgeDownloadDir);
					List<File> sortedfiles = Arrays.asList(path.listFiles()).stream().sorted((file1,file2) -> {return file1.lastModified() > file2.lastModified() ? -1 : 1;}).collect(Collectors.toList());
					
					if(sortedfiles.size() > 0)
					{
						File downloadedFile = sortedfiles.get(0);
						File destPath = new File(filePath);
						try {
							FileUtils.copyFileToDirectory(downloadedFile, destPath);
							
							Log.info("-----------File Copied Successfully.-------------");
						} catch (IOException e) {
							Log.warn("-----------Unable to copy File.");
						}
					}
				}
			} catch (Exception e) {
				Log.warn("-----------Exception Occured while copying File.");
			}
		}
		
		
		public void verifyFileisCopiedOrNot(String path)
		{
			try {
				File file = new File(path);
				if(file.exists())
				{
					Assert.assertTrue(true);
					TestListener.pass("File should be exists at Location "+path+".", "File is exists at Location "+path+".", driver, true);
					Log.info("----------File is exists at Location "+path+".--------------");
					
				}else
				{
					TestListener.fail("File should be exists at Location "+path+".", "File is not exists at Location "+path+".", driver);
					Assert.assertTrue(false);
					 
					Log.info("-----------File is not exists at Location "+path+".-------------");
				}
			} catch (Exception e) {
				
				Log.error("----------Exception Occured while Verifying Copied File.---------", e);
			}
		}

}
