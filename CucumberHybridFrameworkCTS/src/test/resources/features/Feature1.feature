# User Story    :64362
# Designed Date :04th Jan'23
# Designed By   : Nibras Ali
Feature: Navigate to google
# Test case ID :TC_01
# Test Priority : P1
# Test Severity : High
# Feasible for Automation : Y
# TC01_Checking More Options link in Budget Approver Pending screen


Scenario Outline: TC01_Verify the google navigation Pass
Given This test case should execute with the sheet "<SheetName>" and testcase to be executed is "<TestCaseName>" with given scenario "<TestCaseDescription>" 
Then I open browser and navigate to "https://www.google.com"
And do an asssert fail "PassFail"
Examples:
|SheetName|TestCaseName|TestCaseDescription|
|Sample|TC01_P| Verfiy google1|
|Sample|TC01_F| Verfiy google2|

Scenario Outline: TC02_Verify the google navigation Fail
Given This test case should execute with the sheet "<SheetName>" and testcase to be executed is "<TestCaseName>" with given scenario "<TestCaseDescription>" 
Then I open browser and navigate to "https://www.google.com"
And do an asssert fail "PassFail"
Examples:
|SheetName|TestCaseName|TestCaseDescription|
|Sample|TC01_F| Verfiy google2|

#@test
#Scenario:  Navigate to google
#Given I open bbrowser and navigate to "https://www.google.com"
