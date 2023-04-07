package com.test.qa.globalvariables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//-import com.relevantcodes.extentreports.ExtentReports;
//-import com.relevantcodes.extentreports.ExtentTest;
//import com.test.qa.stepDefinition.SqlConnection;
import com.test.qa.utilities.DriverManager;
import com.test.qa.utilities.ExcelUtil;
//-import com.test.qa.utilities.ExtentReportManager;
import com.test.qa.utilities.ExtentReportManager2;
import com.test.qa.utilities.Screenshot;
import com.test.qa.utilities.SqlConnection;


public class GlobalVariables {
	
public static WebDriver driver;
public static DesiredCapabilities capabilities;

public static SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/YYYY");
public static String applicationName=null;
public static String BrowserName="";
public static String ScreenshotFolderPath,ScreenshotFilePath;

public static Screenshot Screenshot=new Screenshot();
public static ExcelUtil excelUtil = new ExcelUtil();
//public static ExtentReportManager extentreportmanager = new ExtentReportManager();
public static ExtentReportManager2 extentreportmanager = new ExtentReportManager2();

public static DriverManager drivermanager = new DriverManager();
public JavascriptExecutor js = (JavascriptExecutor) driver;

public static boolean executionStatus=true;
public static int waitCount=0;
public static String runClassName=null;
public static int pageCount=0;
public static String applicationURL=null;


public static ExtentReports report;
public static ExtentTest test;

protected String fieldValue = null;

public static int testcase_rowNumber;
public static String testcase_sheetName;

public static int ProjectID;
public String RowNumber="";

public static String testcasename_for_report="";
public static String userType;
public static  String Searchtype;
public static String tabName;
public static String SearchText;

public static SqlConnection sqlconect;
public static Connection con;
public static ResultSet resultset;
public static Statement stmt;

//@FindBy(xpath = "//*[@id='manage-contract']/div[1]/div[2]/button") 
//public static WebElement addcontracts_button;

public static int[] indexvalues = new int[2];

//*****************

public static int BeforedeletionSize;


}