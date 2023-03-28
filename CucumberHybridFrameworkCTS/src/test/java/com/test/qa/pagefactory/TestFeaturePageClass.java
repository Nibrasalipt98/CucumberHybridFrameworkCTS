package com.test.qa.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.test.qa.libraries.FunctionalLibrary;

public class TestFeaturePageClass extends FunctionalLibrary {

	@FindBy(id ="logo")
	public static WebElement googleIcon;


	
}
