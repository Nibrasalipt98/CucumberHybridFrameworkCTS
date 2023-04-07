package com.test.qa.utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.test.qa.libraries.FunctionalLibrary;

public class ExtentReportManager2 extends FunctionalLibrary {


//	private static ExtentHtmlReporter htmlReporter;
	private static ExtentSparkReporter sparkReporter; 
	// private static ExtentReports report;
	// private static ExtentTest test;

	public static ExtentReports invokeExtentReport() {
		
		 if(report == null) {
		 	String path = System.getProperty("user.dir") + "\\Reports\\" +applicationName + "\\"
					+ FunctionalLibrary.dateStamp() + "\\" + applicationName  + "_" + BrowserName
							+ "_TestReport_" + FunctionalLibrary.timestamp() + ".html";
		System.out.println("Path of the HTML Report: " + path);
		
		   sparkReporter = new ExtentSparkReporter(path);
		   sparkReporter.config().setEncoding("utf-8");
		   sparkReporter.config().setDocumentTitle("Cucumber Framework Report");
		   sparkReporter.config().setReportName("Automation Report");
		   sparkReporter.config().setTheme(Theme.STANDARD);

			report = new ExtentReports();
			report.attachReporter(sparkReporter);
		
		 }		
		return report;
	}

	public void invokeReport(String testCaseName) {
		try {
			/*****Handled a report validation to generate separate test case report(Even Executing
			 * multiple scenarios with multiple iteration)*
			 * *******/		
			if(report==null)
			{
			testcasename_for_report = testCaseName;
			report = invokeExtentReport();
			test = report.createTest(testCaseName);
			test.assignAuthor(getProperty("Author_Name"));
			test.assignCategory(getProperty("Test_Category"));
			test.log(Status.INFO, "Test Case Execution Started");
			}
			
			if (testcasename_for_report !=testCaseName ) {
	            // Close the previous report
	            if (report != null) {
	                report.flush();
	            	test.log(Status.INFO, "Test Case Execution Completed");
	            }	
			
	        	testcasename_for_report = testCaseName;
	        	report = invokeExtentReport();
	        	test = report.createTest(testCaseName);
				test.assignAuthor(getProperty("Author_Name"));
				test.assignCategory(getProperty("Test_Category"));
				test.log(Status.INFO, "Test Case Execution Started");
				}
		} catch (Exception e) {
			System.out.println("Reporting has not been created");
		}
	}

	public void componenttHeading(String Componentname) {
		test.log(Status.INFO, "<b style=background-color:yellow;/>" + Componentname);
	}

	public void tearReport() throws Exception {
		report.flush();
		test.log(Status.INFO, "Test Case Execution Completed");
	}
}



/*			if(report==null)
{
testcasename_for_report = testCaseName;
report = invokeExtentReport();
test = report.createTest(testCaseName);
test.assignAuthor(getProperty("Author_Name"));
test.assignCategory(getProperty("Test_Category"));
test.log(Status.INFO, "Test Case Execution Started");
}
else if(report !=null && testcasename_for_report !=testCaseName)
{
testcasename_for_report=testCaseName;
tearReport();
report = invokeExtentReport();
test = report.createTest(testCaseName);
test.assignAuthor(getProperty("Author_Name"));
test.assignCategory(getProperty("Test_Category"));
test.log(Status.INFO, "Test Case Execution Started");
*/