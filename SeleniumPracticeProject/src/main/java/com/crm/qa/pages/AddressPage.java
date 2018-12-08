package com.crm.qa.pages;

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

import com.crm.qa.listeners.TestListener;
import com.crm.qa.util.Utilities;

public class AddressPage {
	
	//*********Page Variables*********
	
	private WebDriver driver;
    private static Logger Log = Logger.getLogger(AddressPage.class.getName());
    String eMessage = "Address was successfully created.";
	
	
	//*********Constructor*********
	
	public AddressPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//*********Web Elements*********
	
	@FindBy(how=How.XPATH,using="//a[text()='Addresses']")
	@CacheLookup
	WebElement addressBtn;
	
	@FindBy(how=How.XPATH,using="//a[text()='New Address']")
	@CacheLookup
	WebElement newAddressBtn;
	
	@FindBy(how=How.ID,using="address_first_name")
	@CacheLookup
	WebElement fname;
	
	@FindBy(how=How.ID,using="address_last_name")
	@CacheLookup
	WebElement lname;
	
	@FindBy(how=How.ID,using="address_street_address")
	@CacheLookup
	WebElement street;
	
	@FindBy(how=How.ID,using="address_secondary_address")
	@CacheLookup
	WebElement secondAddrs;
	
	@FindBy(how=How.ID,using="address_city")
	@CacheLookup
	WebElement city;
	
	@FindBy(how=How.ID,using="address_state")
	@CacheLookup
	WebElement state;
	
	@FindBy(how=How.ID,using="address_zip_code")
	@CacheLookup
	WebElement zipCode;
	
	@FindBy(how=How.XPATH,using="//input[@name='address[country]']")
	@CacheLookup
	List<WebElement> country;
	
	@FindBy(how=How.ID,using="address_birthday")
	@CacheLookup
	WebElement birthDate;
	
	@FindBy(how=How.ID,using="address_age")
	@CacheLookup
	WebElement age;
	
	@FindBy(how=How.ID,using="address_website")
	@CacheLookup
	WebElement website;
	
	@FindBy(how=How.ID,using="address_phone")
	@CacheLookup
	WebElement phone;
	
	@FindBy(how=How.ID,using="address_interest_climb")
	@CacheLookup
	WebElement interestClimb;
	
	@FindBy(how=How.NAME,using="address[note]")
	@CacheLookup
	WebElement note;
	
	@FindBy(how=How.XPATH,using="//input[@name='commit']")
	@CacheLookup
	WebElement submitBtn;

	@FindBy(how=How.XPATH,using="//div[@class='alert alert-notice']")
	@CacheLookup
	WebElement successMessage;
	
	@FindBy(how=How.XPATH,using="//a[text()='List']")
	@CacheLookup
	WebElement listButton;
	
	@FindBy(how=How.XPATH,using="//table[@class='table']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRowAfterAdding;
	
	//*********Page Methods*********
	
	/**
	 * This method will fill address details and verify success message.
	 */
	
	public void fillAddressDetails()
	{
		try {
			
			addressBtn.click();
			
			Log.info("-----------Clicked on Address button from login Home Page-------------");
			
			Utilities.waitForElementPresence(driver, By.xpath("//a[text()='New Address']"), 30);
			
			newAddressBtn.click();
			
			Log.info("-----------Clicked on New Address button from login Home Page-------------");
			
			Utilities.waitForElementPresence(driver, By.name("address[first_name]"), 30);
			
			fname.sendKeys("Nagesh");
			lname.sendKeys("Kadam");
			street.sendKeys("Datta Mandir Road");
			secondAddrs.sendKeys("Wanjarkheda");
			city.sendKeys("Tq & Dist:-  Latur");
			
			Select stateToSelect = new Select(state);
			
			stateToSelect.selectByValue("GA");
			
			zipCode.sendKeys("413 511");
			
			if(!country.get(0).isSelected())
			{
				country.get(0).click();
			}
			
			age.sendKeys("28");

			website.sendKeys("http://a.testaddressbook.com/addresses/new");
			
			phone.sendKeys("7972535940");
			
			if(!interestClimb.isSelected())
			{
				interestClimb.click();
			}
			
			note.sendKeys("Address Added Successfully");
			
			submitBtn.click();
			
			Log.info("-----------Clicked on Submit Button-------------");
			
			Utilities.waitForElementPresence(driver, By.xpath("//div[@class='alert alert-notice']"), 30);
			
			String aMessage = successMessage.getText();
			
			if(aMessage.equals(eMessage))
			{
				Assert.assertTrue(true);
				TestListener.pass("Address should be successfully created.", "Address is successfully created.", driver);
				
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
			
			listButton.click();
			
			Log.info("-----------Clicked on List Button-------------");
			
			Utilities.waitForElementPresence(driver, By.xpath("//a[text()='New Address']"), 30);
			
			List<WebElement> tabledata = tableRowAfterAdding.get(0).findElements(By.tagName("td"));
			
			if(tabledata.get(0).getText().equals("Nagesh"))
			{
				Assert.assertTrue(true);
				TestListener.pass("First Name should be Nagesh.", "First Name is "+tabledata.get(0).getText()+".", driver);
				
				Log.info("-----------First Name is Nagesh.-------------");
			}else
			{
				TestListener.fail("First Name should be Nagesh.", "First Name is "+tabledata.get(0).getText()+".", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------First Name is Not Matched.-------------");
			}
			
			if(tabledata.get(1).getText().equals("Kadam"))
			{
				Assert.assertTrue(true);
				TestListener.pass("Last Name should be Kadam.", "Last Name is "+tabledata.get(1).getText()+".", driver);
				
				Log.info("-----------Last Name is Kadam.-------------");
			}else
			{
				TestListener.fail("Last Name should be Kadam.", "Last Name is "+tabledata.get(1).getText()+".", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------Last Name is Not Matched.-------------");
			}
			
		
			if(tabledata.get(2).getText().equals("Tq & Dist:- Latur"))
			{
				Assert.assertTrue(true);
				TestListener.pass("City Name should be Tq & Dist:-  Latur.", "City Name is "+tabledata.get(2).getText()+".", driver);
				
				Log.info("-----------City Name is Tq & Dist:-  Latur.-------------");
			}else
			{
				TestListener.fail("City Name should be Tq & Dist:-  Latur.", "City Name is "+tabledata.get(2).getText()+".", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------City Name is Not Matched.-------------");
			}
			
			if(tabledata.get(3).getText().equals("GA"))
			{
				Assert.assertTrue(true);
				TestListener.pass("State Name should be GA.", "State Name is "+tabledata.get(2).getText()+".", driver);
				
				Log.info("-----------City Name is GA.-------------");
			}else
			{
				TestListener.fail("State Name should be GA.", "State Name is "+tabledata.get(2).getText()+".", driver);
				Assert.assertTrue(false);
				
				Log.info("-----------State Name is Not Matched.-------------");
			}
			
			
			
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
				TestListener.pass("Address wih Name Nagesh should be deleted successfully.", "Address wih Name Nagesh is deleted successfully..", driver);
				
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
			
			Utilities.waitForElementPresence(driver, By.xpath("//div[@class='alert alert-notice']"), 30);
			
			List<WebElement> row = driver.findElements(By.xpath("//table[@class='table']//tbody/tr"));
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
				TestListener.pass("Address wih Name Nagesh should not be present in table.", "Address wih Name Nagesh is not present in table.", driver);
				
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
