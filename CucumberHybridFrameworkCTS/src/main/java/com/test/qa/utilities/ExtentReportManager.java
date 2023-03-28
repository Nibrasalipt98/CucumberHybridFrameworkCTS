package com.test.qa.utilities;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import com.test.qa.libraries.FunctionalLibrary;



public class ExtentReportManager extends FunctionalLibrary {

	public static ExtentReports invokeExtentReport() {
		FunctionalLibrary libs = new FunctionalLibrary();
		String path = System.getProperty("user.dir") + "\\Reports\\" +applicationName + "\\"
				+ libs.dateStamp() + "\\" + applicationName  + "_" + BrowserName
				+ "_TestReport_" + libs.dateStamp() + ".html";
		System.out.println("Path of the HTML Report: " + path);
		report = new ExtentReports(path, false);
		report.addSystemInfo("Report Name", "Cucumber Framework Report").addSystemInfo("Report Type", "Automation Report");
		return report;
	}
	
// **********************************************************************************************************************

		public void invokeReport(String testCaseName) {
			try {
				/*****Handled a report validation to generate separate test case report(Even Executing
				 * multiple scenarios with multiple iteration)*
				 * *******/
				if(report==null)
				{
				testcasename_for_report=testCaseName;
				report = invokeExtentReport();
				test = report.startTest(testCaseName);
				test.assignAuthor( getProperty("Author_Name"));
				test.assignCategory(getProperty("Test_Category"));
				test.setDescription("Travel and Expense Automation");
				}
				else if(report !=null && testcasename_for_report !=testCaseName)
				{
					testcasename_for_report=testCaseName;
					tearReport();
					report = invokeExtentReport();
					test = report.startTest(testCaseName);
					test.assignAuthor( getProperty("Author_Name"));
					test.assignCategory(getProperty("Test_Category"));
					test.setDescription("Travel and Expense Automation");
				}
								
			} catch (Exception e) {
				System.out.println("Reporting has not been created");
			}
		}
		
// **********************************************************************************************************************************************
		public void componenttHeading(String Componentname) {
			test.log(LogStatus.INFO,"<b style=background-color:yellow;>"+ Componentname);
		}		

// ******************************************************************************************************************

		public void tearReport() throws Exception {
			report.endTest(test);
			report.flush();
			

		}
		
}