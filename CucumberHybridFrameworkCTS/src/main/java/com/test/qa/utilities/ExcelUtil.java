package com.test.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.test.qa.globalvariables.GlobalVariables;
import com.test.qa.libraries.FunctionalLibrary;

public class ExcelUtil extends GlobalVariables{
	public File file = null;
	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;
	
	String inputExcelPath = System.getProperty("user.dir") + "\\Data\\DataSheet.xlsx";

	
	
	public int getRowNum(String sheetName, String value) {
		int rowNum = 0;
		try {
			fis = new FileInputStream(inputExcelPath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			int firstRow = sheet.getFirstRowNum();
			int lastRow = sheet.getLastRowNum();
			System.out.println("Test Case Name");
			for (int i = firstRow; i <= lastRow; i++) {
						
				if (sheet.getRow(i).getCell(0).getStringCellValue().trim().equalsIgnoreCase(value.trim()))
				{
					System.out.println(sheet.getRow(i).getCell(0).getStringCellValue().trim());
					rowNum = i;
					break;
				}
				else if(i==lastRow)
				{
					test.log(LogStatus.FAIL, value+" not found in the Excel");
					Assert.fail(value+" not found in the Excel");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rowNum;
	}
	

	public String getCellData(String sheetName, int rowNum, String colName) {
		try {
			fis = new FileInputStream(inputExcelPath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			int col_Num = 0;
			row = sheet.getRow(0);
			int lastrownum =row.getLastCellNum();
			for (int i = 0; i <=lastrownum; i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				{
					col_Num = i;
					break;
				}
				else if(i==lastrownum-1)
				{
					test.log(LogStatus.FAIL, "Column name "+colName+" not found in the Excel");
					Assert.fail("Column name "+colName+" not found in the Excell");
				}
				
			}

			row = sheet.getRow(rowNum);
			cell = row.getCell(col_Num);
			if(cell!=null)
			{
				if (cell.getCellType() == CellType.STRING) {
					System.out.println("Test Data: "+cell.getStringCellValue());
					return cell.getStringCellValue();
				} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
					String cellValue = String.valueOf(cell.getNumericCellValue());
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						Date date = cell.getDateCellValue();
						cellValue = df.format(date);
					}
					System.out.println("Test Data: "+cellValue);
					return cellValue;
				} else if (cell.getCellType() == CellType.BLANK) {
					System.out.println("Test Data: Cell is Empty");
					return "";
				} else {
					return String.valueOf(cell.getBooleanCellValue());
				}
			}
			else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			return "row " + rowNum + " or column " + colName + " does not exist  in Excel";
		}
	}
}