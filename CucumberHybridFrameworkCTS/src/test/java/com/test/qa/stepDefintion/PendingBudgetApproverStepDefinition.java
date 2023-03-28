package com.test.qa.stepDefintion;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import com.test.qa.pagefactory.PendingBudgetApprover;
import io.cucumber.java.en.*;
//import cucumber.api.java.en.*;

public class PendingBudgetApproverStepDefinition extends PendingBudgetApprover {

	public PendingBudgetApproverStepDefinition() {
		PageFactory.initElements(driver, this);
	}

	@Then("^User click on Approve Pre-Travel Approval Request\\(s\\)hyperlink$")
	public void user_click_on_Approve_Pre_Travel_Approval_Request_s_hyperlink() throws Throwable {
		ClickBAPendingHyperlink();

	}

	@Then("^User verifies whether \"([^\"]*)\" link is added in the Pending queue$")
	public void user_verifies_whether_link_is_added_in_the_Pending_queue(String arg1) throws Throwable {
		VerifyMoreFilterLink(arg1);
	}

	@Then("Verify {string} displayed for dropdowns post clicking {string} link")
	public void verify_displayed_for_dropdowns_on_clicking_link(String string, String string2) throws IOException {

		VerifyAdditionaldropdownlabels(string,string2);
	}

	@Then("Verify additional filter dropdowns display post clicking {string} link")
	public void verify_additional_filter_dropdowns_display_on_clicking_link(String string) {
		VerifyAdditionaldropdowndisplay() ;
	}

	@Then("User click on {string} link")
	public void user_click_on_link(String string) {
	
		ClickonMoreFilters(string);
	
	}

	@Then("Verify {string} under {string} dropdown")
	public void verify_under_dropdown(String string, String string2) {
		verifyDropdownValues(string,string2 );
	}

	
	/*
	 * @And("^Verify the header of page$") public void
	 * verify_the_header_of_page() throws Throwable {
	 * 
	 * Pageheaderverification(Pageheader,"Pending Requests");
	 * 
	 * 
	 * }
	 * 
	 * 
	 * @Then("^Verify search button$") public void verify_search_button() throws
	 * Throwable { searchbuttonverification(); }
	 */
}