# User Story    :64362
# Designed Date :04th Jan'23
# Designed By   : Nibras Ali
Feature: UST 64362-Additional filter options on the PTA budget approver's pending view UI
# Test case ID :TC_01
# Test Priority : P1
# Test Severity : High
# Feasible for Automation : Y
# TC01_Checking More Options link in Budget Approver Pending screen

@Smoke
Scenario Outline: TC01_Verify the addition of More Filters in the BA Pending screen
Given This test case should execute with the sheet "<SheetName>" and testcase to be executed is "<TestCaseName>" with given scenario "<TestCaseDescription>" 
Then The user launches the Budget approver proxy URL with indian "User ID" 
Then User click on Approve Pre-Travel Approval Request(s)hyperlink
And User verifies whether "More Filters..." link is added in the Pending queue 


Examples:
|SheetName|TestCaseName|TestCaseDescription|
|UST64362|TC01_Verify the addition of More Filters in the BA Pending screen|Verify the addition of More Filters in the BA Pending screen|

# Test case ID :TC_02
# Test Priority : P2
# Test Severity : High
# Feasible for Automation : Y
# TC02_Verify the additional filter option on clicking More Filter… link

@Smoke
Scenario Outline: TC02_Verify the additional filter option on clicking More Filter link
Given This test case should execute with the sheet "<SheetName>" and testcase to be executed is "<TestCaseName>" with given scenario "<TestCaseDescription>" 
Then The user launches the Budget approver proxy URL with indian "User ID" 
Then User click on Approve Pre-Travel Approval Request(s)hyperlink
And User verifies whether "More Filters..." link is added in the Pending queue 
And User click on "More Filters..." link
Then Verify "Additional Filter Labels" displayed for dropdowns post clicking "More Filters..." link
And Verify additional filter dropdowns display post clicking "More Filters..." link
Examples:
|SheetName|TestCaseName|TestCaseDescription 		 	                                           |
|UST64362|TC02_Verify the additional filter option on clicking More Filter link|Verify the additional filter option on clicking More Filter link|


# Test case ID :TC_03
# Test Priority : P2
# Test Severity : High
# Feasible for Automation : Y
# TC03_Verify the values under "Request Type" dropdown
Scenario Outline: TC03_Verify the values under Request Type dropdown
Given This test case should execute with the sheet "<SheetName>" and testcase to be executed is "<TestCaseName>" with given scenario "<TestCaseDescription>" 
Then The user launches the Budget approver proxy URL with indian "User ID" 
Then User click on Approve Pre-Travel Approval Request(s)hyperlink
And User click on "More Filters..." link
Then Verify "Dropdown values" under "Request Type" dropdown

Examples:
|SheetName|TestCaseName|TestCaseDescription 		 	                                           |
|UST64362|TC03_Verify the values under Request Type dropdown|Verify the values under Request Type dropdown|



# Test case ID :TC_04
# Test Priority : P2
# Test Severity : High
# Feasible for Automation : Y
# TC03_Verify the values under "Trip Type" dropdown

@Regression
Scenario Outline: TC04_Verify the values under Trip Type dropdown
Given This test case should execute with the sheet "<SheetName>" and testcase to be executed is "<TestCaseName>" with given scenario "<TestCaseDescription>" 
Then The user launches the Budget approver proxy URL with indian "User ID" 
Then User click on Approve Pre-Travel Approval Request(s)hyperlink
And User click on "More Filters..." link
Then Verify "Dropdown values" under "Trip Type" dropdown

Examples:
|SheetName|TestCaseName|TestCaseDescription 		 	                                           |
|UST64362|TC04_Verify the values under Trip Type dropdown|Verify the values under Trip Type dropdown|



# Test case ID :TC_05
# Test Priority : P2
# Test Severity : High
# Feasible for Automation : Y
# TC05_Verify the values under "Travel Type" dropdown

@test
Scenario Outline: TC05_Verify the values under Travel Type dropdown
Given This test case should execute with the sheet "<SheetName>" and testcase to be executed is "<TestCaseName>" with given scenario "<TestCaseDescription>" 
Then The user launches the Budget approver proxy URL with indian "User ID" 
Then User click on Approve Pre-Travel Approval Request(s)hyperlink
And User click on "More Filters..." link
Then Verify "Dropdown values" under "Travel Type" dropdown

Examples:
|SheetName|TestCaseName|TestCaseDescription 		 	                                           |
|UST64362|TC05_Verify the values under Travel Type dropdown|Verify the values under Travel Type dropdown|