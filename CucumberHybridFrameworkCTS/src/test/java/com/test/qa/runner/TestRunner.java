
package com.test.qa.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.test.qa.libraries.FunctionalLibrary;
import com.test.qa.utilities.ExtentReportManager;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features ="src/test/resources/features/Feature1.feature",
				glue ="com.test.qa.stepDefintion",
			dryRun = false, 
		 monochrome = true
		 ,plugin = {"pretty","html:target/cucumber-reports/cucumber-report.html"})

public class TestRunner {
	@AfterClass()
	public static void tearReport() throws Exception {
		ExtentReportManager extentreportmanager = new ExtentReportManager();
		FunctionalLibrary libs = new FunctionalLibrary();
		extentreportmanager.tearReport();
		if (libs.getProperty("MFA") != "True") {
			libs.quitDriver();
		}
	}

}