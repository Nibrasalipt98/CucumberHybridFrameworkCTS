package com.test.qa.stepDefintion;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.test.qa.pagefactory.LaunchPageFactory;

import io.cucumber.java.en.*;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//import cucumber.api.java.en.And;
public class LaunchstepDefination extends LaunchPageFactory {
	
	
	@Given("^This test case should execute with the sheet \"([^\"]*)\" and testcase to be executed is \"([^\"]*)\" with given scenario \"([^\"]*)\"$")
	public void this_test_case_should_execute_with_the_sheet_and_testcase_to_be_executed_is_with_given_scenario(String sheetname, String testCaseName, String TestCaseDescription) throws Throwable {
		
		try 
		{
			if(driver!=null && getProperty("MFA")!="True") 
			{
				quitDriver();
			}
			drivermanager.getDriver();
			Thread.sleep(1000);
			System.out.println(driver);			
			extentreportmanager.invokeReport(testCaseName);
			extentreportmanager.componenttHeading(TestCaseDescription);
	
			if(getProperty("GET_DATA_FROM_EXCEL").equalsIgnoreCase("True")&& getProperty("GET_DATA_FROM_EXCEL")!="")
			{
				getTestCaseName(sheetname, testCaseName);	
			}
			else
			{
				System.out.println("Executing :"+testCaseName);
			}
					
			
			
		} catch (Exception e) {
			catch_code(e, "Launching Driver", "Launching driver and URL having Some problem");
			Assert.fail(e.getMessage());
			extentreportmanager.tearReport();
		}

	}
	
	@Then("^The user launches the Budget approver proxy URL with indian \"([^\"]*)\"$")
	public void the_user_launches_the_Budget_approver_proxy_URL_with_indian(String arg1) throws Throwable {
		try {
			
			String UserID = excelUtil.getCellData(testcase_sheetName, testcase_rowNumber,arg1);
			System.out.println(applicationURL+UserID);
			driver.get(applicationURL+UserID);
			System.out.println("URL: "+applicationURL+UserID+" is launched");
			test.log(LogStatus.PASS, "URL: "+applicationURL+UserID+" is launched");
						
			} catch (Exception e) {
			e.getMessage();
		}
		Thread.sleep(3000);
	}
}

