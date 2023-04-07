package com.test.qa.stepDefintion;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.Status;
//import com.relevantcodes.extentreports.LogStatus;
import com.test.qa.pagefactory.TestFeaturePageClass;

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
				test.log(Status.PASS, "PASS");
		} else {
		  	test.log(Status.FAIL, "FAIL");
			FAILreport("SC_Name",
					"Issue details");
			
			Assert.fail();    // used to mark test as failed in console and Junit tab of tsetcase
		}
	}

}
