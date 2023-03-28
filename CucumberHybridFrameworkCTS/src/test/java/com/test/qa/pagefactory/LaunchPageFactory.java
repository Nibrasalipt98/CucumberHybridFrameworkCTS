
package com.test.qa.pagefactory;


import com.test.qa.libraries.FunctionalLibrary;
import com.test.qa.utilities.ExcelUtil;

import org.junit.Assert;


public class LaunchPageFactory extends FunctionalLibrary {
//	ExcelUtil excelUtil=new ExcelUtil();

	
public void getTestCaseName( String sheetName, String testCaseName) throws Exception {
	testcase_sheetName= sheetName;
	testcase_rowNumber = excelUtil.getRowNum(sheetName,testCaseName);
	if(testcase_rowNumber!=0)
	{
		System.out.println("Test Case Row Number: " + testcase_rowNumber);
	}
	else
	{
		Assert.fail("Test case Name not found in the Excel sheet");
		extentreportmanager.tearReport();
		quitDriver();
	}
	
}
}