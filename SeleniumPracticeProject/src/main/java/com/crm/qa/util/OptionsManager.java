package com.crm.qa.util;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

public class OptionsManager {
	
	 //Get Chrome Options
	
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        //options.addArguments("--incognito");
        return options;
    }

    //Get Firefox Options
    
    public static FirefoxOptions getFirefoxOptions () {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        
        //Accept Untrusted Certificates
        
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        
        //Use No Proxy Settings
        
        profile.setPreference("network.proxy.type", 0);
        
        //Set Firefox profile to capabilities
        
        options.setCapability(FirefoxDriver.PROFILE, profile);
        return options;
    }
    
    //Get IE Options
    
    public static InternetExplorerOptions getIEOptions()
    {
    	InternetExplorerOptions options = new InternetExplorerOptions();

    	options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    	options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true); 
    	options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);         
    	options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.DISMISS); //Accept unexpected alerts 
    	options.setCapability("requireWindowFocus", true); 
    	options.setCapability("enablePersistentHover", false); 
    	options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
    	return options;
    }
    
    
//Get Edge Options
    
    public static EdgeOptions getEdgeOptions()
    {
    	EdgeOptions options = new EdgeOptions();

    	options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    	options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true); 
    	options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);         
    	options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.DISMISS); //Accept unexpected alerts 
    	options.setCapability("requireWindowFocus", true); 
    	options.setCapability("enablePersistentHover", false); 
    	options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
    	return options;
    }

}
