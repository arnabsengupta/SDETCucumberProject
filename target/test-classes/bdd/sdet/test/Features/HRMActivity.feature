#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@HRMActivity
Feature: Creating a job vacancy
  I want to Goal To create a job vacancy for DevOps Engineer

Background: User is in the login page
		Given User is in the login page
    When User logs in with credentials "orange","orangepassword123"
    Then User goes to the Recruitement page
    Then User goes to the Vacancies page
    
  Scenario: Creating a job vacancy
    Then User selects "DevOps Engineer" and enters "Testvacancy","Test Employee","2","Test"
    Then User verifies that the vacancy for "DevOps Engineer" is created

 Scenario: Add information about a candidate for recruitment
    Given User opens the HRM page and logs in with credentials "orange" and "orangepassword123"
    When User navigates to the Recruitment page and click on the Add button to add candidate information
    Then On the next page fill in the details of the candidate firstname "firsttestname1",last name "lasttestname1" and email "test"
    Then User upload a resume (docx or pdf) to the form
    Then User navigate back to the Recruitments page to confirm candidate entry
    Then Close the browser
    
    Scenario Outline: Add multiple employees using an the Examples table
    Given User opens the ​OrangeHRM​ page and login with credentials "orange" and "orangepassword123" provided
    When User find the PIM option in the menu and click it
    Then User click the Add button to add a new Employee
    Then User make sure the “Create Login Details” checkbox is checked
    Then User fill in the required fields "<firstName>","<lastName>","<employeeID>","<employeeUserName>" until all the employees and their accounts have been created and verify that the employee are created
    Then Close the Browser

    Examples: 
      | firstName      |lastName      |employeeID|employeeUserName| 
      | testfirstname  |testlastname  |1         |testempuname    |
      | testfirstname  |testlastname  |2         |testempuname    |
      | testfirstname  |testlastname  |3         |testempuname    |
 
 Scenario Outline: Creating multiple vacancies using data from an external excel spreadsheet
    Given User on login page
    When User logs in with "orange","orangepassword123"
    Then User goes to the Recruitement -- Vacancies page 
    Then User now selects "<jobTitle>" and enters "<vacancyName>","<HiringManager>","<NumOfPos>","<Desc>"
    Then User now verifies that the vacancy for "DevOps Engineer" is created
    Then Close browser

    Examples: 
      | jobTitle                 | vacancyName | HiringManager |NumOfPos|Desc|   
      | DevOps Engineer          |Testvacancy  | Test Employee |4       |Test|
      | Automation Test Engineer |Testvacancy2 | Test Employee |5       |Test|
      | Android Developer        |Testvacancy3 | Test Employee |1       |Test|