
package com.test.qa.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.test.qa.libraries.FunctionalLibrary;

public class Screenshot {
	
	
	public void getScreenshot(WebDriver driver, String Path) throws IOException {
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(Path));
	}
	

}