package com.test.qa.stepDefintion;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.test.qa.pagefactory.TestFeaturePageClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TestFeature extends TestFeaturePageClass {

	public TestFeature() {
		PageFactory.initElements(driver, this);
	}

	@Then("I open browser and navigate to {string}")
	public void i_open_browser_and_navigate_to_google(String string) {
		driver.get(string);
	}

	@Then("do an asssert fail {string}")
	public void do_an_asssert_fail(String string) {

		String status = excelUtil.getCellData(testcase_sheetName, testcase_rowNumber, string);
		System.out.println("Current Status " + status);

		if (status.contentEquals("Pass")) {
			test.log(LogStatus.PASS, "PASS");
		} else {
			test.log(LogStatus.FAIL, "FAILED");   // used for failure in eclipse
			Assert.fail();    // used to mark test as failed in console and Junit tab of tsetcase
		}
	}

}
