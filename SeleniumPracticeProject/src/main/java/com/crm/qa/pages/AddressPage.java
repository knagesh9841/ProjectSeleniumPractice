package com.crm.qa.pages;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import static com.crm.qa.objectrepository.ObjectRepository.*;

import com.crm.qa.exceltestdata.ExcelUtils;
import com.crm.qa.listeners.TestListener;
import com.crm.qa.util.Utilities;

public class AddressPage {
	
	//*********Page Variables*********
	
	private WebDriver driver;
    private static Logger Log = Logger.getLogger(AddressPage.class.getName());
    String eMessage = "Address was successfully created.";
	HashMap<String, String> testData = new HashMap<String,String>();
	String testDataHolder;
	
	//*********Constructor*********
	
	public AddressPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//*********Web Elements*********
	
	@FindBy(how=How.XPATH,using="//a[text()='Addresses']")
	@CacheLookup
	public WebElement addressBtn;
	
	
	@FindBy(how=How.ID,using="address_last_name")
	@CacheLookup
	public WebElement lname;
	
	@FindBy(how=How.ID,using="address_street_address")
	@CacheLookup
	public WebElement street;
	
	@FindBy(how=How.ID,using="address_secondary_address")
	@CacheLookup
	public WebElement secondAddrs;
	
	@FindBy(how=How.ID,using="address_city")
	@CacheLookup
	public WebElement city;
	
	@FindBy(how=How.ID,using="address_state")
	@CacheLookup
	public WebElement state;
	
	@FindBy(how=How.ID,using="address_zip_code")
	@CacheLookup
	public WebElement zipCode;
	
	@FindBy(how=How.XPATH,using="//input[@name='address[country]']")
	@CacheLookup
	public List<WebElement> country;
	
	@FindBy(how=How.ID,using="address_birthday")
	@CacheLookup
	public WebElement birthDate;
	
	@FindBy(how=How.ID,using="address_age")
	@CacheLookup
	public WebElement age;
	
	@FindBy(how=How.ID,using="address_website")
	@CacheLookup
	public WebElement website;
	
	@FindBy(how=How.ID,using="address_phone")
	@CacheLookup
	public WebElement phone;
	
	@FindBy(how=How.ID,using="address_interest_climb")
	@CacheLookup
	public WebElement interestClimb;
	
	@FindBy(how=How.NAME,using="address[note]")
	@CacheLookup
	public WebElement note;
	
	@FindBy(how=How.XPATH,using="//input[@name='commit']")
	@CacheLookup
	public WebElement submitBtn;

	@FindBy(how=How.XPATH,using="//a[text()='List']")
	@CacheLookup
	public WebElement listButton;
	
	
	//*********Page Methods*********
	
	/**
	 * This method will fill address details and verify success message.
	 */
	
	public void fillAddressDetails()
	{
		
		
		try {
			
			testData = ExcelUtils.loadData("ExcelTestDataFile.xlsx", "contacts", 1);
			
			addressBtn.click();
			
			Log.info("-----------Clicked on Address button from login Home Page-------------");
			
			Utilities.waitForElementPresence(driver, byAddressPage_newAddressBtn, 30);
			
			WebElement newAddressBtn = driver.findElement(byAddressPage_newAddressBtn);
			
			
			newAddressBtn.click();
			
			Log.info("-----------Clicked on New Address button from login Home Page-------------");
			
			Utilities.waitForElementPresence(driver, byAddressPage_firstName, 30);
			
			WebElement fname = driver.findElement(byAddressPage_firstName);
			
			testDataHolder = ExcelUtils.getData(testData, "First Name");
			fname.sendKeys(testDataHolder);
			
			testDataHolder = ExcelUtils.getData(testData, "Last Name");
			lname.sendKeys(testDataHolder);
			
			testDataHolder = ExcelUtils.getData(testData, "Street");
			street.sendKeys(testDataHolder);
			
			testDataHolder = ExcelUtils.getData(testData, "Secondary Address");
			secondAddrs.sendKeys(testDataHolder);
			
			testDataHolder = ExcelUtils.getData(testData, "City");
			city.sendKeys(testDataHolder);
			
			testDataHolder = ExcelUtils.getData(testData, "State");
			Select stateToSelect = new Select(state);
			stateToSelect.selectByValue(testDataHolder);
			
			testDataHolder = ExcelUtils.getData(testData, "ZipCode");
			zipCode.sendKeys(testDataHolder);
			
			testDataHolder = ExcelUtils.getData(testData, "Country");
			if(testDataHolder.equals("United State"))
			{
				if(!country.get(0).isSelected())
				{
					country.get(0).click();
				}
			}
			
			
			testDataHolder = ExcelUtils.getData(testData, "Age");
			age.sendKeys(testDataHolder);

			testDataHolder = ExcelUtils.getData(testData, "Website");
			website.sendKeys(testDataHolder);
			
			testDataHolder = ExcelUtils.getData(testData, "Phone");
			phone.sendKeys(testDataHolder);
			
			
			testDataHolder = ExcelUtils.getData(testData, "Interest");
			if(testDataHolder.equals("Climb"))
			{
				if(!interestClimb.isSelected())
				{
					interestClimb.click();
				}
			}
			
			testDataHolder = ExcelUtils.getData(testData, "Note");
			note.sendKeys(testDataHolder);
			
			submitBtn.click();
			
			Log.info("-----------Clicked on Submit Button-------------");
			
			Utilities.waitForElementPresence(driver, byAddressPage_successMsg, 30);
			
			WebElement successMessage = driver.findElement(byAddressPage_successMsg);
			
			
			String aMessage = successMessage.getText();
			
			if(aMessage.equals(eMessage))
			{
				Assert.assertTrue(true);
				TestListener.pass("Address should be successfully created.", "Address is successfully created.", driver, true);
				
				Log.info("-----------Address is successfully created-------------");
				
			}else
			{
				TestListener.fail("Address should be successfully created.", "Address is not successfully created.", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------Address is not successfully created.-------------");
			}
			
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while Creating Address---------", e);
		}
	}
	
	
	/**
	 * This method will verify address details.
	 */
	
	public void verifyAddressDetails()
	{
		
		
		try {
			
			testData = ExcelUtils.loadData("ExcelTestDataFile.xlsx", "contacts", 1);
			
			listButton.click();
			
			Log.info("-----------Clicked on List Button-------------");
			
			Utilities.waitForElementPresence(driver, byAddressPage_newAddressBtn, 30);
			
			List<WebElement> tableRowAfterAdding = driver.findElements(byAddressPage_addrsTableRow);
			
			List<WebElement> tabledata = tableRowAfterAdding.get(0).findElements(By.tagName("td"));
			
			testDataHolder = ExcelUtils.getData(testData, "First Name");
			
			if(tabledata.get(0).getText().equals(testDataHolder))
			{
				Assert.assertTrue(true);
				TestListener.info("First Name should be Nagesh.", "First Name is "+tabledata.get(0).getText()+".", driver, false);
				
				Log.info("-----------First Name is Nagesh.-------------");
			}else
			{
				TestListener.fail("First Name should be Nagesh.", "First Name is "+tabledata.get(0).getText()+".", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------First Name is Not Matched.-------------");
			}
			
			testDataHolder = ExcelUtils.getData(testData, "Last Name");
			
			if(tabledata.get(1).getText().equals(testDataHolder))
			{
				Assert.assertTrue(true);
				TestListener.info("Last Name should be Kadam.", "Last Name is "+tabledata.get(1).getText()+".", driver, false);
				
				Log.info("-----------Last Name is Kadam.-------------");
			}else
			{
				TestListener.fail("Last Name should be Kadam.", "Last Name is "+tabledata.get(1).getText()+".", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------Last Name is Not Matched.-------------");
			}
			
			testDataHolder = ExcelUtils.getData(testData, "City");
		
			if(tabledata.get(2).getText().equals(testDataHolder))
			{
				Assert.assertTrue(true);
				TestListener.info("City Name should be Tq & Dist:-  Latur.", "City Name is "+tabledata.get(2).getText()+".", driver, false);
				
				Log.info("-----------City Name is Tq & Dist:-  Latur.-------------");
			}else
			{
				TestListener.fail("City Name should be Tq & Dist:-  Latur.", "City Name is "+tabledata.get(2).getText()+".", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------City Name is Not Matched.-------------");
			}
			
			testDataHolder = ExcelUtils.getData(testData, "State");
			
			if(tabledata.get(3).getText().equals(testDataHolder))
			{
				Assert.assertTrue(true);
				TestListener.info("State Name should be GA.", "State Name is "+tabledata.get(2).getText()+".", driver, false);
				
				Log.info("-----------City Name is GA.-------------");
			}else
			{
				TestListener.fail("State Name should be GA.", "State Name is "+tabledata.get(2).getText()+".", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------State Name is Not Matched.-------------");
			}
			
			
			String colHeader [] = {"First Name","Last Name","City","State"};
			String colValue [] = {tabledata.get(0).getText(),tabledata.get(1).getText(),tabledata.get(2).getText(),tabledata.get(3).getText()};
			
			ExcelUtils.writeDataToExcelFile("ExcelWriteTestDataFile.xlsx", "contacts", 1, colHeader, colValue);
			
			
		} catch (Exception e) {
			
			Log.error("----------Exception Occured while Verifying Address Details---------", e);
		}
	}
	
	/** 
	 * This method will delete the address details.
	 */
	
	public void deleteAddressDetails()
	{
		
		
		try {
			
			List<WebElement> tableRowAfterAdding = driver.findElements(byAddressPage_addrsTableRow);
			
			int size  = tableRowAfterAdding.size();
			boolean flag = false;
			
			for(int cnt =0; cnt<size;cnt++)
			{
				if(tableRowAfterAdding.get(cnt).findElements(By.tagName("td")).get(0).getText().equals("Nagesh"))
				{
					Log.info("-----------Address wih Name Nagesh is selected for deletion.-------------");
					
					tableRowAfterAdding.get(cnt).findElements(By.tagName("td")).get(6).findElement(By.tagName("a")).click();
					
					Utilities.waitForAlertIsPresent(driver, 30);
					
					Alert alert = driver.switchTo().alert();
					
					alert.accept();
					
					flag = true;
					
					
				}
			}
			
			if(flag)
			{
				Assert.assertTrue(true);
				TestListener.pass("Address wih Name Nagesh should be deleted successfully.", "Address wih Name Nagesh is deleted successfully..", driver, true);
				
				Log.info("-----------Address wih Name Nagesh is deleted successfully.-------------");
				
			}else
			{
				TestListener.fail("Address wih Name Nagesh should be deleted successfully.", "Address wih Name Nagesh is not deleted successfully.", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------Address wih Name Nagesh is deleted successfully.-------------");
			}
			
			
		} catch (Exception e) {
			Log.error("----------Exception Occured while Deleting Address Details---------", e);
		}
	}
	
	/**
	 * This method will verify deleted Address details.
	 */
	
	
	public void verifyDeletedAddressDetails()
	{
		
		
		try {
			
			Utilities.waitForElementPresence(driver, byAddressPage_successMsg, 30);
			
			List<WebElement> tableRowAfterAdding = driver.findElements(byAddressPage_addrsTableRow);
			
			List<WebElement> row = driver.findElements(byAddressPage_addrsTableRow);
			int rowSize = row.size();
			boolean flag = true;
			if(rowSize==0)
			{
				flag = true;
				
			}else
			{
				for(int cnt =0; cnt<rowSize;cnt++)
				{
					if(tableRowAfterAdding.get(cnt).findElements(By.tagName("td")).get(0).getText().equals("Nagesh"))
					{
						Log.info("-----------Address wih Name Nagesh is Present in Table.-------------");
						flag = false;
					}
				}
			}
			
			if(flag)
			{
				Assert.assertTrue(true);
				TestListener.pass("Address wih Name Nagesh should not be present in table.", "Address wih Name Nagesh is not present in table.", driver, true);
				
				Log.info("-----------Address wih Name Nagesh is not  present in table.-------------");
			}else
			{
				TestListener.fail("Address wih Name Nagesh should not be present in table.", "Address wih Name Nagesh is present in table.", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------Address wih Name Nagesh is not deleted successfully.-------------");
			}
			
		} catch (Exception e) {
			Log.error("----------Exception Occured while verifying Deleted Address Details---------", e);
		}
	}
	

}
