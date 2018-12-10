package com.crm.qa.objectrepository;

import org.openqa.selenium.By;

public class ObjectRepository {
	
	public static By byAddressPage_newAddressBtn = By.xpath("//a[text()='New Address']");
	public static By byAddressPage_firstName = By.name("address[first_name]");
	public static By byAddressPage_successMsg = By.xpath("//div[@class='alert alert-notice']");
	public static By byAddressPage_addrsTableRow = By.xpath("//table[@class='table']//tbody/tr");
	public static By byLoginPage_userName = By.id("session_email");
	public static By byHomePage_signoutBtn = By.xpath("//a[text()='Sign out']");
}
