package com.test.qa.pagefactory;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
import com.test.qa.libraries.FunctionalLibrary;

public class PendingBudgetApprover extends FunctionalLibrary {

	//WebDriverWait wait = new WebDriverWait(driver, 30);
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	static Logger log = Logger.getLogger(PendingBudgetApprover.class.getName());

	int mapcontsize = 0;
	String MapContract = null;

	@FindBy(id = "hypViewApvForm")
	public static WebElement BA_Pendingscreenlink;

	@FindBy(xpath = "//div[@id='ctl00_ContentPlaceHolder1_UpdatePanel2']//h1")
	public static WebElement Pageheader;

	@FindBy(xpath = "//*[@id='ctl00_ContentPlaceHolder1_btnSearch']")
	public static WebElement searhcbtn;

	@FindBy(xpath = "//div[@id='ctl00_ContentPlaceHolder1_UpdatePanel2']//td[5]/a")
	public static WebElement morelink;

	@FindBy(id = "ctl00_ContentPlaceHolder1_ddl_RequestType")
	public static WebElement RequestTypedrpdwn;

	@FindBy(id = "ctl00_ContentPlaceHolder1_ddl_TripType")
	public static WebElement TripTypedrpdwn;

	@FindBy(id = "ctl00_ContentPlaceHolder1_ddl_TravelType")
	public static WebElement TravelTypedrpdwn;
	
	
	@FindBy(id = "ctl00_ContentPlaceHolder1_lbl_RequestType")
	public static WebElement RequestTypeUILabel;

	@FindBy(id = "ctl00_ContentPlaceHolder1_lbl_TripType")
	public static WebElement TripTypeUILabel;

	@FindBy(id = "ctl00_ContentPlaceHolder1_lbl_TravelType")
	public static WebElement TravelTypeUILabel;

	public void ClickBAPendingHyperlink() {
		try {
		 elementTobeClickable(BA_Pendingscreenlink,15,"Approve Pre-Travel Approval Request(s)hyperlink");
				
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", BA_Pendingscreenlink);
			// BA_Pendingscreenlink.click();
			test.log(LogStatus.PASS, "Clicked on Approve Pre-Travel Approval Request(s)hyperlink successfully");
			PASSreport("Budget Approver Pending Screen", "On Budget Approver Pending Screen");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click on on Approve Pre-Travel Approval Request(s)hyperlink" + e);
			FAILreport("Approve Pre-Travel Approval Request(s) hyperlink",
					"Unable to click on on Approve Pre-Travel Approval Request(s)hyperlink");
		}

	}

	public void VerifyMoreFilterLink(String obj) throws IOException {

		try {
			if (morelink.isDisplayed()) {
				String testData = morelink.getText().trim();
				System.out.println(testData);
				// Assert.assertEquals(inputData.trim(), testData.trim());
				if (obj.trim().equalsIgnoreCase(testData.trim())) {
					PASSreport(obj, obj + " is displayed as expected");

				} else {
					FAILreport(obj, obj + " is not displayed as expected");

				}

			}

		} catch (Exception exception) {
			ScreenshotFilePath = ScreenshotFolderPath + obj + "_" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
			test.log(LogStatus.FAIL,
					obj + " having an issue" + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));

		}
	}

	public void ClickonMoreFilters(String linkname)
	{
		try {
			clickOnButton( morelink,linkname );
			test.log(LogStatus.PASS, "Clicked on "+linkname+" successfully");
			
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to click on more filters" + e);
			FAILreport("More Filter link",
					"Unable to click on more filters");
		}	
	}
	
	
	public void VerifyAdditionaldropdownlabels(String labels, String linkname) throws IOException 
	{

		try {
		
			String labels_excel = excelUtil.getCellData(testcase_sheetName, testcase_rowNumber, labels);

			ArrayList<String> seperateField = new ArrayList<String>();
			String[] strSplit = labels_excel.split(",");
			seperateField = new ArrayList<String>(Arrays.asList(strSplit));
			 elementTobeClickable(TravelTypeUILabel,15,seperateField.get(2).replace("\n", ""));
				
			if (RequestTypeUILabel.isDisplayed() && TripTypeUILabel.isDisplayed() && TravelTypeUILabel.isDisplayed())
			{
				
				
				String label1 = RequestTypeUILabel.getText().trim();
				String label2 = TripTypeUILabel.getText().trim();
				String label3 = TravelTypeUILabel.getText().trim();
			if (label1.contentEquals(seperateField.get(0).replace("\n", "")) && label2.contentEquals(seperateField.get(1).replace("\n", ""))
						&& label3.contentEquals(seperateField.get(2).replace("\n", ""))) 
				{
					PASSreport(labels, seperateField.get(0) + "," + seperateField.get(1) + "," + seperateField.get(2)
							+ "," + " label are displayed as expected");

				} else {
					FAILreport(labels, seperateField.get(0) + "," + seperateField.get(1) + "," + seperateField.get(2)
							+ "," + " label are not displayed as expected");
				}

			}else 
			{
				FAILreport(labels, "labels are not displayed");

			}
		}catch (Exception e) {
			test.log(LogStatus.FAIL, "Unable to VerifyAdditionaldropdownlabels" + e);
			FAILreport("Additionaldropdownlabels",
					"Unable to VerifyAdditionaldropdownlabels");
		}
	}

	
	public void VerifyAdditionaldropdowndisplay() 
	{	
	try {
		if (RequestTypedrpdwn.isDisplayed() && TripTypedrpdwn.isDisplayed() && TravelTypedrpdwn.isDisplayed())
		{		
			PASSreport("Dropdowns","Dropdowns are displayed as expected");

		} 
		else
		{
	FAILreport("Dropdowns","Dropdowns are not displayed as expected");
		}
			
			
	}catch (Exception e) {
		test.log(LogStatus.FAIL, "Unable to VerifyAdditionaldropdown display" + e);
		FAILreport("Additionaldropdownlabels",
				"Unable to VerifyAdditionaldropdown display");
	}	
	
	}
	
	
	public void verifyDropdownValues(String values,String dropdownname)
	{
		WebElement drpdwnXpath=null;
		boolean boolval=false;
		switch (dropdownname) {
		case "Request Type":
			drpdwnXpath=RequestTypedrpdwn;
			break;
		case "Trip Type":
			drpdwnXpath=TripTypedrpdwn;	
			break;
		case "Travel Type":
			drpdwnXpath=TravelTypedrpdwn;	
			break;
		}
		
	try {
		Select s = new Select(drpdwnXpath);
		 List<String> valuesfromUI=new ArrayList<>();
	      // getting the list of options in the dropdown with getOptions()
	      List <WebElement> op = s.getOptions();
	      int size = op.size();
	      for(int i =0; i<size ; i++){
	         String options = op.get(i).getText();
	         valuesfromUI.add(options);
	         System.out.println(options);
	      }
	  	
		String drpdwnvalues_excel = excelUtil.getCellData(testcase_sheetName, testcase_rowNumber, values);

			ArrayList<String> seperateField = new ArrayList<String>();
			String[] strSplit = drpdwnvalues_excel.split(",");
			seperateField = new ArrayList<String>(Arrays.asList(strSplit));
			
			boolval = valuesfromUI.equals(seperateField); //returns true because lists are equal  
			//System.out.println(boolval); 
	      if(boolval==true)
	      {
	      	Thread.sleep(1000);
	    	  PASSreport(dropdownname+ "dropdown",dropdownname+ " dropdown values displayed "+seperateField+ " are as expected");
	      }else
	      {
	    		FAILreport(dropdownname+ "dropdown",dropdownname+ " dropdown values are not as expected");
	    		  	  
	      }
	    	  
	    
			
			
			
		}catch (Exception e) {
	     		test.log(LogStatus.FAIL, "Unable to fetch dropdown values" + e);
	     		FAILreport("Dropdown Values",
	     				"Unable to fetch dropdown values");
	     	}    
	      
	     }
	
	
	
	
	
}



/** public void Pageheaderverification(WebElement element, String inputData) {
 * try {
 * 
 * String testData = element.getText().trim(); System.out.println(testData); //
 * Assert.assertEquals(inputData.trim(), testData.trim());
 * 
 * if(inputData.trim().equalsIgnoreCase(testData.trim())) {
 * test.log(LogStatus.PASS, "Page header is as expected"); PASSreport(
 * "Page header", "Page header is as expected");
 * 
 * }else { test.log(LogStatus.FAIL, "Page header is not as expected");
 * FAILreport("Page header", "Page header is not as expected");
 * 
 * }
 * 
 * } catch (Exception e) {
 * 
 * test.log(LogStatus.FAIL, "Error fetching page header" + e); FAILreport(
 * "Page header", "Error fetching page header");
 * 
 * }
 * 
 * }
 * 
 * 
 * public void searchbuttonverification() { try {
 * 
 * 
 * if(searhcbtn.isDisplayed()) { test.log(LogStatus.PASS,
 * "Search button is present"); PASSreport("Search button",
 * "Search button is present");
 * 
 * }else { test.log(LogStatus.FAIL, "Search button is not present"); FAILreport(
 * "Search button", "Search button is not present");
 * 
 * }
 * 
 * } catch (Exception e) {
 * 
 * test.log(LogStatus.FAIL, "Error on finding Search button"); FAILreport(
 * "Search button", "Error on finding Search button");
 * 
 * }
 * 
 * }
 * 
 * }
 */