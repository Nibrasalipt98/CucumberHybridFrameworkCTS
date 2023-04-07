package com.test.qa.utilities;

import java.io.File;
import java.util.*;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import com.test.qa.globalvariables.GlobalVariables;
import com.test.qa.libraries.FunctionalLibrary;

public class DriverManager extends FunctionalLibrary{
	
	public void getDriver() throws Exception {
		
		applicationURL = getProperty("URL");//Uncomment it  when execute your script
		//applicationURL = System.getProperty("user.dir") +"\\Application\\Registration_form.html");//comment it  when execute your script
		applicationName = getProperty("ApplicationName");
		BrowserName = getProperty("BROWSER");
		

		File reportDirectory = new File(
				System.getProperty("user.dir") + "\\Reports\\" + applicationName + "\\" + dateStamp());
		reportDirectory.mkdirs();
		
		ScreenshotFolderPath = System.getProperty("user.dir") + "\\Reports\\" + applicationName + "\\" + dateStamp()
				+ "\\Screenshots\\";
		File Screenpathdir = new File(ScreenshotFolderPath);
		Screenpathdir.mkdirs();
		
		
	
		
		browserOpen();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		
		//driver.get(applicationURL);
		//driver.manage().deleteAllCookies();
	}

	public WebDriver browserOpen() throws Exception {
		if(BrowserName==null || BrowserName=="")
		{
			/****Setting Chrome as default browser****/
			BrowserName="CHROME";
		}
		switch (BrowserName) {
		case "EDGE":
		
			System.setProperty("webdriver.ie.driver", getProperty("IE_BROWSER_PATH"));
			InternetExplorerOptions e_options = new InternetExplorerOptions();
	        e_options.introduceFlakinessByIgnoringSecurityDomains();
	        driver = new InternetExplorerDriver(e_options);
			driver.manage().window().maximize();
			break;

		case "FIREFOX":

			if(getProperty("MFA").equalsIgnoreCase("True"))
			{
				
				System.out.println("Please make sure Profile setting in Firefox browser "
						+ "is completed and MFA login Process verified successfully");
				System.setProperty("webdriver.gecko.driver", getProperty("Firefox_BROWSER_PATH"));
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("DineshProfile");
				FirefoxOptions f_options = new FirefoxOptions();
				f_options.setProfile(myprofile);
				driver = new FirefoxDriver(f_options);
			}
			else
			{
				
				System.setProperty("webdriver.gecko.driver", getProperty("Firefox_BROWSER_PATH"));
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}
			
			break;

		case "CHROME":
	//		System.setProperty("webdriver.chrome.driver", getProperty("CHROME_BROWSER_PATH"));

			ChromeOptions options = new ChromeOptions();
			if(getProperty("MFA").equalsIgnoreCase("True"))
			{
				options.setExperimentalOption("debuggerAddress", "localhost:5656");
				System.out.println("Please make sure you have launched the driver with port number 5656 and completed MFA login Process");
				options.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(options);
			}
			else
			{
				       					
				options.addArguments("test-type");
				options.addArguments("start-maximized");
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");
				options.addArguments("--enable-precise-memory-info");
				options.addArguments("--disable-popup-blocking");
				options.addArguments("--remote-allow-origins=*");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				
				options.setCapability("goog:chromeOptions", ImmutableMap.of("w3c", true, "detach", false));
				//options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				 options.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
				 options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
			//	System.out.println(options.getVersion());
			Runtime.getRuntime().exec("TASKKILL /IM chrome.exe /F"); 
			    Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f "); 
				Thread.sleep(5000);
			    driver = new ChromeDriver(options);
			//    Object version = ((ChromeDriver) driver).executeCdpCommand("Browser.getVersion", null).get("productVersion");
			  //  System.out.println(version);
			    
			  //  driver.manage().window().maximize();
				//Thread.sleep(5000);
			}
			break;
			
		default:
			System.out.println("The executables for drivers is not available in the path");
		}
		return driver;
	}
}