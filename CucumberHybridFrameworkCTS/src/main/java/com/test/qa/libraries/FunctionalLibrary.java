package com.test.qa.libraries;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;
//-import com.relevantcodes.extentreports.LogStatus;
//-import com.relevantcodes.extentreports.model.Log;
import com.test.qa.globalvariables.GlobalVariables;
//-import com.test.qa.utilities.ExtentReportManager;
import com.test.qa.utilities.ExtentReportManager2;
public class FunctionalLibrary extends GlobalVariables {
	
	@FindBy(xpath="//body")
	public WebElement bodytag;
	static Logger log = Logger.getLogger(GlobalVariables.class.getName());
	
/****************************Date Code***************************/

	/**
	 * @author So-Redesign
	 * 
	 *         This method will return the date in the format of (yyyy-MM-dd)
	 * 
	 */

	public static String dateStamp() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		} catch (Exception e) {
		//-	test.log(LogStatus.FAIL, "Error while returning current time and date","Failed to return current date and time");
			test.log(Status.FAIL, "Error while returning current time and date");	
			
		}
		return null;
	}
// ***********************************************************************************************************************************************************
	/**
	 * @author So-Redesign
	 * 
	 *         This method will return the date and the time in the format of
	 *         (yyyy-MM-dd HH-mm-ss)
	 * 
	 */

	public static String timestamp() 
	{
		try {
			//return new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
		 return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
		} catch (Exception e) {
			//-		test.log(LogStatus.FAIL, "Error while returning current time_Date", "FAILED to return date_time");
			test.log(Status.FAIL, "Error while returning current time and date");	
		}
			return null;
	}
	
// ***********************************************************************************************************************
	public String AddingdaysTocureentDate(int NumberofDays) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, NumberofDays); // Adding days
		String output = dateFormat.format(c.getTime());
		return dateFormat.format(c.getTime());

	}
	
// ***********************************************************************************************************************
	public String DateDifference(int NoOfDays, SimpleDateFormat formatter) throws Exception {
		String str = null;
		try {
			Date dt = new Date();
			Calendar cl = Calendar.getInstance();
			cl.setTime(dt);
			cl.add(Calendar.DAY_OF_YEAR, NoOfDays);
			dt = cl.getTime();
			str = formatter.format(dt);
		} catch (Exception e) {
			System.out.println("Invalid Date formate error: DateDifference");
		}

		return str;
	}
	
// ***********************************************************************************************************************
	public String DateDifference(int NoOfDays, String dateformate) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat(dateformate);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, NoOfDays);
		String output = formatter.format(c.getTime());

		return output;
	}
	
	public void DateComparision(String startdate,String enddate) throws Exception
	{
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	     Date start_date = sdf.parse(startdate);
	     Date end_date = sdf.parse(enddate);

	     if (start_date.compareTo(end_date) > 0) {
	         System.out.println("start_date  is after end_date");
	     } else if (start_date.compareTo(end_date) < 0) {
	         System.out.println("start_date is before end_date");
	     } else if (start_date.compareTo(end_date) == 0) {
	         System.out.println("start_date is equal to end_date");
	     } 
	}
	
	public void select_date(String date_field, String date)throws Exception
	{
	WebElement datefield = driver.findElement(By.xpath(date_field));
	JavascriptExecutor myExecutor = ((JavascriptExecutor) driver);
	myExecutor.executeScript("arguments[0].value='"+date+"';", datefield);
	}

	public static String getCurrentTimeStamp() {
	       SimpleDateFormat formDate = new SimpleDateFormat("MM/dd/yyyy");
	       String strDate = formDate.format(new Date());
	       return strDate;
	  } 

/*********************************Alert Handling***************************************************************/
	/**
	 * @author So-Redesign
	 * 
	 *         Check if the alert is present
	 */
	public boolean isAlertPresent(WebDriver driver) {
		try {
			Thread.sleep(2000);
			Alert alert=driver.switchTo().alert();
			System.out.println("alert message : "+alert.getText());
			alert.accept();
			return true;
		} catch (Exception e) {
			System.out.println("alert not present");
			return false;
		}
	}

	/** 
	 *         TO Trigger Javascript alert Manually
	 */
	public void Javascript_alert_manual(String alert_message) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			Thread.sleep(1000);
			js.executeScript("alert('"+alert_message+"')"); 
			isAlertPresent(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	

/***************************************Waits*******************************************************************/
	/**
	 * @author So-Redesign
	 * @param WebDriver
	 * @param WebElement
	 * 
	 *                   Fluent Wait function until the object is displayed
	 */

	public void fluentWait(WebDriver driver, final WebElement element) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(Duration.ofMillis(250));
		wait.withTimeout(Duration.ofSeconds(500));
		wait.ignoring(NoSuchElementException.class);
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				WebElement this_element = element;

				if (this_element.isDisplayed()) {
					return true;
				}
				return false;
			}
		};
		wait.until(function);
	}
	
	// Wait For Element to be Clickable
	public static void elementTobeClickable(WebElement element, int WaitSeconds, String FieldName) throws Exception {
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitSeconds));
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			ScreenshotFilePath = ScreenshotFolderPath + "Not displayed" + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
		//-	test.log(LogStatus.FAIL, FieldName + " having a problem due to " + e.getMessage()
		//			+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			
		//	test.log(Status.FAIL, FieldName + " having a problem due to " + e.getMessage() +
		//			   test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath))); or use media entry where it is more customizable 
		
			test.log(Status.FAIL,FieldName + " having a problem due to " + e.getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)).build());
			

			
			
     		extentreportmanager.tearReport();
			Assert.fail(e.getMessage());
		}
	}
	
	// ***********************************************************************************************************************
	public boolean WaitForElement(WebElement element, String elementName, int WaitTime) 
	{
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitTime));
		wait.until(ExpectedConditions.visibilityOf(element));
		return true;
	}
	
	// ***********************************************************************************************************************
		public void pageloader(int Totalcount) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
	
			for(int i=0; i<Totalcount; i++)
			{
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					   System.out.println("Page loaded successfully.");
					   break;
		
				} else {
//					pageloader(Totalcount);
					System.out.println("Page not loaded successfully."+i);
				}
			}
			
			pageCount = 0;
		}

		/***waitForTextToAppear***/
		public static void waitForTextToAppear(WebDriver newDriver, String textToAppear, WebElement element) {
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		    wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
		}


// **********************************************************************************************************************************************************
	/**
	 * 
	 * @author So-Redesign
	 * 
	 * @param expected
	 * @param actual
	 * @param test
	 * @param driver
	 * @throws Exception
	 * 
	 *                   This method checks whether the expected and actual values
	 *                   are equal
	 */

	public void assertionHandling(String expected, String actual, WebDriver driver)
			throws Exception {
		ScreenshotFilePath = null;
		ScreenshotFilePath = ScreenshotFolderPath + "screen" + timestamp() + ".jpg";
		try {
			if (expected.equalsIgnoreCase(actual)) {
			//-	test.log(LogStatus.PASS, "Assertion is success for the String " + expected + " & " + actual);
				test.log(Status.PASS,"Assertion is success for the String " + expected + " & " + actual);
			
			
			}

		} catch (Exception e) {
			ScreenshotFilePath = ScreenshotFolderPath + expected + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
	//-		test.log(LogStatus.FAIL, "Assertion failed for the String " + expected + " & " + actual
	//				+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			
	//-		test.log(Status.FAIL, "Assertion failed for the String " + expected + " & " + actual +
	//-				   test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath))); or use media entry where it is more customizable 
			test.log(Status.FAIL, "Assertion failed for the String " + expected + " & " + actual,
					MediaEntityBuilder.createScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)).build());
					
			extentreportmanager.tearReport();
			Assert.fail(e.getMessage());

		}

	}
	
	// ************************************************************************************************************
	// Test Validation using Assert
	public void validateText(WebElement element, String inputData) throws Exception {
		try {
			String testData = element.getText().trim();
			System.out.println(testData);
			Assert.assertEquals(inputData.trim(), testData.trim());
		} catch (Exception e) {
			System.out.println("VALIDATION FAILED");
			ScreenshotFilePath = null;
			ScreenshotFilePath = ScreenshotFolderPath + "inputData" + "_" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
		//-	test.log(LogStatus.FAIL,
		//			"The validation got failed due to_" + e.getMessage() + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));		
			
		test.log(Status.FAIL,"The validation got failed due to_" + e.getMessage() +test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));
			
			
			
			extentreportmanager.tearReport();
			Assert.fail(e.getMessage());
		}

	}

/************************************Add/Set Attribute***********************************************************************************************************************
	/**
	 * 
	 * @author So-Redesign
	 * 
	 * @param webElement
	 * @param AttributeName
	 * @param AttributeValue
	 * @throws Exception
	 */

	public void AddingAttributeValueAtRunTime(WebElement webElement, String AttributeName, String AttributeValue)
			throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('" + AttributeName + "','" + AttributeValue + "')", webElement);
		} catch (Exception e) {
			ScreenshotFilePath = null;
			ScreenshotFilePath = ScreenshotFolderPath + AttributeName + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
	//-		test.log(LogStatus.FAIL, "Add the Attribute Name : (" + AttributeName + ")",
	//				"Failed to add the Attribute Name" + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));

		
			
			
			test.log(Status.FAIL,"Failed to add the Attribute Name" + e.getMessage() +test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));
				
			
			
			extentreportmanager.tearReport();
			Assert.fail(e.getMessage());
		}
	}

// **************************************Highlighting Element**************************************************************************************************************************
	/**
	 * @author So-Redesign
	 * 
	 * @param driver
	 * @param element
	 * @throws Exception 
	 */
	public void HighlightElement( WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			Thread.sleep(1000);
			js.executeScript("arguments[0].style.border='2px groove yellow'", element);
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].style.border=''", element);
		
	}
	
	public void HighlightElement_red( WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			js.executeScript("arguments[0].setAttribute('style', 'background: red; border: 2px solid red;');",element);
			js.executeScript("arguments[0].setAttribute('style', 'background: ; border: ;');",element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void HighlightElement_green( WebElement element) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 2px solid red;');",element);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute('style', 'background: ; border: ;');",element);
		
	}
	


	

// **************************************************************************************************************************************************************
	/** 
	 * @author So-Redesign
	 * @param actual
	 * @param expected
	 * @throws Exception
	 */


	public void CompareIntegers(int actual, int expected) throws Exception {
		try {
			if ((actual >= (expected - 1)) && (actual <= (expected + 1))) {
			//-	test.log(LogStatus.PASS, "Values Verified Successfully");
				test.log(Status.PASS, "Values Verified Successfully");

			} else {
			//-	test.log(LogStatus.FAIL,  "Value Verification Failed");
				test.log(Status.FAIL,  "Value Verification Failed");
			}
		} catch (Exception exception) {
		//-	test.log(LogStatus.FAIL, "Value Verification Failed");
			test.log(Status.FAIL,  "Value Verification Failed");
				
			
		}

	}


/*********************************Passing Data to input field****************************************************/

	public void enterValueIntoTextField(WebElement element, String FieldName, String Value) throws Exception {
		try {
			elementTobeClickable(element, 60, FieldName);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if (element.isEnabled() == true && element.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				js.executeScript("arguments[0].style.border='2px groove red'", element);
				Thread.sleep(100);
				clearTextField(element, FieldName);
				element.sendKeys(Value);
				js.executeScript("arguments[0].style.border=''", element);
			} else {
				ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
				Screenshot.getScreenshot(driver, ScreenshotFilePath);
		//-		test.log(LogStatus.FAIL, FieldName + " having a problem due to "
		//				+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
				
				test.log(Status.FAIL, FieldName + " having a problem due to "
						+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));
			
				
				
				
				extentreportmanager.tearReport();
				Assert.fail();
			}

		} catch (Exception e) {
			ScreenshotFilePath = null;
			ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
		//-	test.log(LogStatus.FAIL, FieldName + " having a problem due to " + e.getMessage()
		//			+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			
			test.log(Status.FAIL, FieldName + " having a problem due to "
					+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));
			
			
			extentreportmanager.tearReport();
			Assert.fail(e.getMessage());

		}

	}



	
	// ***********************************************************************************************************************
	public void clearTextField(WebElement element, String FieldName) throws Exception {
		try {
			elementTobeClickable(element, 20, FieldName);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if (element.isEnabled() == true && element.isDisplayed()) {
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				element.clear();
			} else {
				ScreenshotFilePath = null;
				ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
				Screenshot.getScreenshot(driver, ScreenshotFilePath);
		
				//-	test.log(LogStatus.FAIL, FieldName + " having a problem due to " + e.getMessage()
				//			+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
					
					test.log(Status.FAIL, FieldName + " having a problem due to "
							+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

				
				
				extentreportmanager.tearReport();
				Assert.fail();
			}
		} catch (Exception e) {
			ScreenshotFilePath = null;
			ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
			//-	test.log(LogStatus.FAIL, FieldName + " having a problem due to " + e.getMessage()
			//			+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
				
				test.log(Status.FAIL, FieldName + " having a problem due to " + e.getMessage()
						+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

			Assert.fail(e.getMessage());
			extentreportmanager.tearReport();
			Assert.fail(e.getMessage());
		}
	}


	// ***********************************************************************************************************************
	public static void clickOnButton(WebElement element, String FieldName) throws Exception {

		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			elementTobeClickable(element, 60, FieldName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			if (element.isDisplayed() && element.isEnabled()){
				try {
					js.executeScript("arguments[0].style.border='2px groove red'", element);
					js.executeScript("arguments[0].style.border=''", element);
					js.executeScript("arguments[0].click();", element);
				} catch (Exception e) {
					js.executeScript("arguments[0].style.border='2px groove red'", element);
					js.executeScript("arguments[0].style.border=''", element);
					element.click();
				}

			} else {
				ScreenshotFilePath = null;
				ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
				Screenshot.getScreenshot(driver, ScreenshotFilePath);
				//-	test.log(LogStatus.FAIL, FieldName + " having a problem due to " + e.getMessage()
				//			+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
					
					test.log(Status.FAIL, FieldName + " having a problem due to "
							+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

				extentreportmanager.tearReport();
				Assert.fail();
			}
		} catch (Exception exception) {
			ScreenshotFilePath = null;
			ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
	//-		test.log(LogStatus.FAIL, FieldName + " having a problem due to " + exception.getMessage()
	//				+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
				
				test.log(Status.FAIL, FieldName + " having a problem due to " + exception.getMessage()
						+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

		
			
			extentreportmanager.tearReport();
			Assert.fail(exception.getMessage());
		}
	}
/***************************************Drop Down Code***********************************************************************/
	public static void selectValueFromDropDown(WebElement element, String dropDownvalue, String FieldName) throws Exception {

		try {
			if (element.isDisplayed() && element.isEnabled()) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				js.executeScript("arguments[0].style.border='2px groove red'", element);
				Select dropDown = new Select(element);
				dropDown.selectByVisibleText(dropDownvalue);
				js.executeScript("arguments[0].style.border=''", element);
			} else {
				ScreenshotFilePath = null;
				ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
				Screenshot.getScreenshot(driver, ScreenshotFilePath);
		//-		test.log(LogStatus.FAIL, FieldName + " with the drop down value as" + dropDownvalue
		//				+ " having a problem" + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
				
				test.log(Status.FAIL, FieldName + " with the drop down value as" + dropDownvalue
				+ " having a problem" + test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

				extentreportmanager.tearReport();
				Assert.fail();
			}
		} catch (Exception exception) {
			ScreenshotFilePath = null;
			ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
		//-	test.log(LogStatus.FAIL,
		//			FieldName + " with the drop down value as" + dropDownvalue + " having a problem due to "
		//					+ exception.getMessage() + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			
			test.log(Status.FAIL,
					FieldName + " with the drop down value as" + dropDownvalue + " having a problem due to "
							+ exception.getMessage() + test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

			
			
			
			extentreportmanager.tearReport();
			Assert.fail(exception.getMessage());
		}
	}
	

	public static void Selectdropdownvalue_using_List(WebElement element, String inputData, String FieldName) throws Exception {

		clickOnButton(element, FieldName);
		try {
			Select options = new Select(element);
			List<WebElement> getOptions = options.getOptions();
			for (WebElement option : getOptions) {
				String selectOption = option.getText().trim();
				System.out.println(selectOption);
				try {
				if (selectOption.equalsIgnoreCase(inputData.trim())) {
					option.click();
					break;
				} }catch(Exception e) {
					System.out.println("SELECTING OPTIONS FROM DROPDOWN HAVING PROBLEM");
					//ScreenshotFilePath = ScreenshotFolderPath + "DropdownOptions" + "_" + timestamp() + ".jpg";
					Screenshot.getScreenshot(driver, ScreenshotFilePath);
			//-		test.log(LogStatus.FAIL, FieldName + " having a problem"+e.getMessage()
			//				+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
					
					test.log(Status.FAIL, FieldName + " having a problem"+e.getMessage()
					+ test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));
	
					
					
					
					extentreportmanager.tearReport();
					Assert.fail();
				}
			}
		} catch (Exception e) {
			System.out.println("SELECING OPTIONS FROM DROPDOWN HAVING PROBLEM:" + e.getMessage());
			//ScreenshotFilePath = ScreenshotFolderPath + "DropdownOptions" + "_" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
	//-		test.log(LogStatus.FAIL, FieldName + " having a problem due to " + e.getMessage()
	//				+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			
			test.log(Status.FAIL, FieldName + " having a problem due to " + e.getMessage()
			+ test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

			
			extentreportmanager.tearReport();
			Assert.fail(e.getMessage());

		}
	}



//******************************************************************************************************************
	public static String gettingText(WebElement element, String FieldName) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='2px groove red'", element);

			if (element.isDisplayed() && element.isEnabled()) {

				FieldName = element.getText();
			} else {
				ScreenshotFilePath = null;
				ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
				Screenshot.getScreenshot(driver, ScreenshotFilePath);
	//-			test.log(LogStatus.FAIL,
		//				FieldName + " having a problem " + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
				
				test.log(Status.FAIL, FieldName + " having a problem " + test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

			
				
				
				extentreportmanager.tearReport();
				Assert.fail();
			}
		} catch (Exception exception) {
			ScreenshotFilePath = null;
			ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
		//-	test.log(LogStatus.FAIL, FieldName + " having a problem due to " + exception.getMessage()
		//			+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			
			test.log(Status.FAIL, FieldName + " having a problem due to " + exception.getMessage()
			+ test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

			
			
			
			extentreportmanager.tearReport();
			Assert.fail(exception.getMessage());
		}
		return FieldName;
	}

	// ***********************************************************************************************************************
	public void VerifyAttribute(WebElement element, String Expected, String FieldName, String attributename)
			throws Exception {
		try {
			if (element.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

//			 	 element.click();
				if (element.getAttribute(attributename).equalsIgnoreCase(Expected)) {
					js.executeScript("arguments[0].style.border='2px groove red'", element);
		//-			test.log(LogStatus.PASS, "Verify the  text/Number(" + Expected + ")",
		//					"Text/Number has been verified successfully.");
				
					test.log(Status.PASS, "Verify the text/Number(" + Expected + ")")
				    .log(Status.PASS, "Text/Number has been verified successfully.");

					
					
					
				} else {
					ScreenshotFilePath = null;
					ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
					Screenshot.getScreenshot(driver, ScreenshotFilePath);
				//-	test.log(LogStatus.FAIL, FieldName + " having a problem "
				//			+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
					
					test.log(Status.FAIL, FieldName + " having a problem due to "
							+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));
			
					
					
					extentreportmanager.tearReport();
					Assert.fail();
				}
			}
		} catch (Exception exception) {
			ScreenshotFilePath = null;
			ScreenshotFilePath = ScreenshotFolderPath + FieldName + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
		//-	test.log(LogStatus.FAIL, FieldName + " having a problem due to " + exception.getMessage()
		//			+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			
			
			
			test.log(Status.FAIL, FieldName + " having a problem due to " + exception.getMessage()
			+ test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

			
			
			extentreportmanager.tearReport();
			Assert.fail(exception.getMessage());
		}
	}

	// ***********************************************************************************************************************
//	public String getStringFromAlphaNumaric(String name, String CaseValue) {
//		try {
//			switch (CaseValue) {
//			case "string":
//				return name.replaceAll("[^a-z^A-Z?!\\.]", "");
//
//			case "number":
//				return name.replaceAll("[^-^0-9?!\\.]", "");
//
//			case "number1":
//				return name.replaceAll("[^0-9?!\\.]", "");
//
//			case "numberspl":
//				return name.replaceAll("[^\\w\\s]", "");
//
//			case "SubString":
//				return name.substring(name.indexOf("$"));
//
//			case "email":
//				return name.replaceAll("[^@^a-z^A-Z?!\\.]", "");
//
//			case "Split":
//				String[] str1 = name.split(" ");
//				return name = str1[str1.length - 1].trim();
//
//			}
//		} catch (Exception e) {
//			System.out.println("Problem is :" + CaseValue + ">>" + name);
//			return "-1";
//		}
//		return "worng input";
//	}
//	

	public int Stringtoint(String value) {
		try {
			if (value.equals(null) || value.equalsIgnoreCase("")) {
				return 0;
			} else {
				return Integer.parseInt(value);
			}
		} catch (Exception e) {
			System.out.println("Validation Un-successful!!" + e.getMessage());
			return 0;
		}
	}


	// ***********************************************************************************************************************
	public boolean PageTitle(String Expectdtitle) throws Exception {
		boolean flag = true;
		try {
			if (driver.getTitle().replaceAll("\\s+", "").equalsIgnoreCase(Expectdtitle.replaceAll("\\s+", ""))) {
				System.out.println("Page Title displayed as expected");
		//-		test.log(LogStatus.PASS, "Actual Title: "+driver.getTitle()+" And Expected Title: "+ Expectdtitle);
			
				test.log(Status.PASS, "Actual Title: " + driver.getTitle() + " and Expected Title: " + Expectdtitle);

			
			}
			else
			{
				System.out.println("Page Title Verification failed");
			//-	test.log(LogStatus.FAIL, "Actual Title: "+driver.getTitle()+" And Expected Title: "+ Expectdtitle);
				test.log(Status.FAIL, "Actual Title: " + driver.getTitle() + " and Expected Title: " + Expectdtitle);

			}
		} catch (Exception e) {
			ScreenshotFilePath = null;
			ScreenshotFilePath = ScreenshotFolderPath + "Pagetitle" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
		//-	test.log(LogStatus.FAIL, driver.getTitle(),
		//			"Page is not displayed as expected" + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			test.log(Status.FAIL,driver.getTitle() +
					"Page is not displayed as expected"+ test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

		
			
			
			executionStatus = flag = false;
		}

		return flag;
	}

	// ***********************************************************************************************************************
	public boolean VerifyElementstatus(WebElement element, String ObjName) throws Exception {
		boolean flag = true;
		try {
			if (element.isDisplayed()) {
		//-		test.log(LogStatus.PASS, ObjName+" displayed as expected"+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			
			
				test.log(Status.PASS,ObjName+" displayed as expected"+  test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

			
			
			
			}
		} catch (Exception exception) {
			flag = false;
			ScreenshotFilePath = ScreenshotFolderPath + ObjName + "_" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
	//-		test.log(LogStatus.FAIL,
	//				ObjName + " having an issue" + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
		
			test.log(Status.FAIL,ObjName + " having an issue" +  test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

		
		}
		return flag;
	}




	// ****************************************************************************************************************
	public void refreshPage() {
		driver.navigate().refresh();
	}

	public String getProperty(String key) throws IOException {
		String value = "";
		Properties properties = null;
		try {
			if (properties == null) {
				properties = new Properties();
				File file = new File("src//test//resources//settings.properties");
				InputStream inputStream = new FileInputStream(file);
				properties.load(inputStream);
			}
			value = (String) properties.get(key);

		} catch (Exception e) {
			ScreenshotFilePath = ScreenshotFolderPath + key + "_" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
	//-		test.log(LogStatus.FAIL, "Issues with key while retrieving from the property file "
	//				+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
		
      test.log(Status.FAIL, "Issues with key while retrieving from the property file "+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

		
		
		}
		return value;
	}

	// Get Title
	public String toGetTitle() throws IOException {
		String title = null;
		try {
			title = driver.getTitle().trim();
		} catch (Exception e) {
			ScreenshotFilePath = ScreenshotFolderPath + title + "_" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
	//-		test.log(LogStatus.FAIL,
	//				"Issues with getting the title " + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
		     test.log(Status.FAIL,
						"Issues with getting the title " +test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

		
		
		}
		return title;
	}

// **************************************************************************************************************

	public void switchToChildWindow() throws Exception {
		try {
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for (String childWindow : allWindows) {
				if (!parentWindow.equalsIgnoreCase(childWindow)) {
					driver.switchTo().window(childWindow);
				}
			}
			System.out.println(driver.getCurrentUrl());
			pageloader(2);
			driver.manage().window().maximize();
		} catch (Exception e) {
			ScreenshotFilePath = ScreenshotFolderPath + "window" + "_" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
	//-		test.log(LogStatus.FAIL,
	//				"Issues while switching the window " + test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			
			  test.log(Status.FAIL,
					  "Issues while switching the window " +test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

			
			
			extentreportmanager.tearReport();
			Assert.fail(e.getMessage());
		}
	}


//****************************************************************************************************************************************
	public void pageUp() {
		try {
			((JavascriptExecutor) driver).executeScript("scroll(0,-250);");
		} catch (Exception e) {
			System.out.println("ERROR WHILE SCROLLING PAGE UP: " + "due to:" + e.getMessage());
		}
	}


	// *************************************************************************************************************************************************

	// Scroll Bottom
	public void scrollBottom() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
					+ "document.body.scrollHeight,document.documentElement.clientHeight));");
		} catch (Exception e) {
			System.out.println("ERROR WHILE SCROLLING BOTTOM: " + "due to:" + e.getMessage());
		}
	}


	
	
/******************To Find the Row Number of Html table data*************************************/	
	
	public void ToFindRowNumber(String value, String table_xpath, int col_num) throws Exception
	{
		/*****Example table_xpath="//table[@class,'classname']/tbody/tr";*******/
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(table_xpath+"/td")));
	   List<WebElement> rows=driver.findElements(By.xpath(table_xpath));
	   for(int i=1;i<=rows.size();i++)
	   {
	     WebElement row = driver.findElement(By.xpath(table_xpath+"["+i+"]/td["+col_num+"]"));
	     if(row.getText().trim().contains(value))
	     {
	    	 RowNumber=Integer.toString(i);
	    	 HighlightElement_green(row);
	    	 break;
	     }
	     else
	     {
//	    	 HighlightElement_red(row);
	    	 RowNumber="";
	     }
	   }
	}

	
	public void List_Selection(String Field_data, WebElement Field_location, String list_xpath,String field_name) throws Exception 
	{
			enterValueIntoTextField(Field_location, field_name, Field_data);
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			/***Sample list_xpath= //ul[contains(@class,'classname')]/li**/
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(list_xpath)));
			List<WebElement> list = driver.findElements(By.xpath(list_xpath));
			System.out.println(" List size ::" + list.size());
			for(int i = 0 ;i<=list.size();i++)
			{
				System.out.println(list.get(i).getText());
				if(list.get(i).getText().trim().contains(Field_data.trim()))
				{
					elementTobeClickable(list.get(i), 30, field_name);
					list.get(i).click();
					System.out.println(field_name+" selected successfully");
					break;
				}
				else if(i==list.size())
				{
					System.out.println(field_name+" not selected");	
					
				}		
			}
		}

	
	/**********************PASS and FAIL report with Screenshot and catch code******************/
	public void PASSreport(String FieldName, String Reportdesc)
	{
		try
		{
			ScreenshotFilePath =ScreenshotFolderPath+FieldName + "_" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
			js.executeScript("arguments[0].style.border='1px groove black'", bodytag);
			js.executeScript("arguments[0].style.border=''", bodytag);
		//	test.log(LogStatus.PASS, Reportdesc	+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
			 test.log(Status.PASS,Reportdesc	+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

				test.log(Status.PASS,Reportdesc,
						MediaEntityBuilder.createScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)).build());
		
		
		}
		catch(Exception e)
		{
			System.out.println("PASS report Not completed properly "+e.getMessage());
		}
	}
	public void FAILreport(String FieldName, String Reportdesc)
	{
		try
		{
			ScreenshotFilePath =ScreenshotFolderPath+FieldName + "_" + timestamp() + ".jpg";
			Screenshot.getScreenshot(driver, ScreenshotFilePath);
			js.executeScript("arguments[0].style.border='1px groove black'", bodytag);
			js.executeScript("arguments[0].style.border=''", bodytag);
	//-		test.log(LogStatus.FAIL, Reportdesc	+ test.addScreenCapture(ReportScreenshot(ScreenshotFilePath)));
		
	// not working- test.log(Status.FAIL,Reportdesc	+test.addScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)));

			test.log(Status.FAIL,Reportdesc,
					MediaEntityBuilder.createScreenCaptureFromPath(ReportScreenshot(ScreenshotFilePath)).build());
		}
		catch(Exception e)
		{
			
			System.out.println("FAIL report Not completed properly"+e.getMessage());
		}
	}
	
	
	public void catch_code(Exception e, String FieldName, String Reportdesc) throws Exception
	{
		System.out.println(Reportdesc + e.getMessage());
		FAILreport(FieldName, Reportdesc);
		extentreportmanager.tearReport();
		Assert.fail(e.getMessage());
		
	}
	
	/*****
	 * Generating screenshot path to add in Extent Report(Ex:Screenshot\FileName.jpg, This path will help us 
	 * to access screenshot in report when sharing report folder to another user or different path
	 ******/
	
	public static String ReportScreenshot(String ScreenshotFilePath) {
		try {
	//		String[] path = ScreenshotFilePath.split(dateStamp());
	//		ScreenshotFilePath= path[1].substring(1);
			
			String[] pathComponents = ScreenshotFilePath.split("\\\\");
		    int index = 0;
		    for(int i=0; i<pathComponents.length; i++) {
		        if(pathComponents[i].equalsIgnoreCase("Screenshots")) {
		            index = i;
		            break;
		        }
		    }
		    String screenshotPath = String.join("\\", Arrays.copyOfRange(pathComponents, index, pathComponents.length));
		    ScreenshotFilePath=screenshotPath;
		} catch (Exception e) {
			System.out.println("Path of Screenhots=" + ScreenshotFilePath);
		}
		return ScreenshotFilePath;

	}
	
	public static String DateFormateConversion(String givenDate) throws ParseException
	{
		Date date = null;
		String requiredDate = null;
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mmm-dd");
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-mmm-yyyy");
		
		date = inputFormat.parse(givenDate);
		requiredDate = outputDateFormat.format(date);
		requiredDate = requiredDate.replaceAll("003", "Mar");
		requiredDate = requiredDate.replaceAll("001", "Jan");
		requiredDate = requiredDate.replaceAll("002", "Feb");
		requiredDate = requiredDate.replaceAll("004", "Apr");
		requiredDate = requiredDate.replaceAll("005", "May");
		requiredDate = requiredDate.replaceAll("006", "Jun");
		requiredDate = requiredDate.replaceAll("007", "Jul");
		requiredDate = requiredDate.replaceAll("008", "Aug");
		requiredDate = requiredDate.replaceAll("009", "Sep");
		requiredDate = requiredDate.replaceAll("010", "Oct");
		requiredDate = requiredDate.replaceAll("011", "Nov");
		requiredDate = requiredDate.replaceAll("012", "Dec");


		return requiredDate;
	}

	//******************************************************************************************
	
	public void quitDriver() {
		driver.quit();
	}
	
	public static boolean isElementPresent(WebElement element)
	{
	    try 
	    {
	      //System.out.println("element found 1");
	      //System.out.println(element);

	      if(element.isDisplayed())
	      {
	    	 // System.out.println("element found and displayed2");
	    	  return true;
	      }
	      else
	      {
	    	  System.out.println("element not found");
	    	  return false;
	      }
	    
	    }
	   
	    catch (org.openqa.selenium.NoSuchElementException e) 
	    {
	    	System.out.println("element not found");
	      return false;
	    }
			
	}

	
	public static String getJsAlertText()
	{
	Object txt = ((JavascriptExecutor)driver).executeScript("return window.alert.myAlertText;");
	return (String)txt;
	}
	
}