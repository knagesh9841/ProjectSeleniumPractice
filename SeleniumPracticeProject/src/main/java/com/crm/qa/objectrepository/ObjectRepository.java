package com.crm.qa.objectrepository;

import org.openqa.selenium.By;

public class ObjectRepository {
	
	public static By byAddressPage_newAddressBtn = By.xpath("//a[text()='New Address']");
	public static By byAddressPage_firstName = By.name("address[first_name]");
	public static By byAddressPage_successMsg = By.xpath("//div[@class='alert alert-notice']");
	public static By byAddressPage_addrsTableRow = By.xpath("//table[@class='table']//tbody/tr");
	public static By byLoginPage_userName = By.id("session_email");
	public static By byHomePage_signoutBtn = By.xpath("//a[text()='Sign out']");
	public static By byFramePageTopicname = By.xpath("//input[@type='text']");
	public static By byFramePageCheckBox = By.xpath("//input[@type='checkbox']");
	public static By byFramePageAnimals =By.id("animals");
	public static By byFrameTopicHeading = By.xpath("//label/span");
	public static By byFrame1 = By.id("frame1");
	public static By byFrame2 = By.id("frame2");
	public static By byFrame3 = By.id("frame3");
	public static By byFrameElement = By.tagName("iframe");
	public static By bytutorialMenu = By.xpath("//nav[@class='navigation']//span[text()='Tutorial']");
	public static By bywebAutoToolMenu = By.xpath("//nav[@class='navigation']//span[text()='Web Automation Tools']");
	public static By bycucumberTutMenu = By.xpath("//nav[@class='navigation']//span[text()='Cucumber Tutorial']");
	public static By bybootstratLoginUserName = By.xpath("//input[@type='text']");
	
}
