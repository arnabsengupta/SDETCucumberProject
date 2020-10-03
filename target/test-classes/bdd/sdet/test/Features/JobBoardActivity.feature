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
@JobBoardActivity
Feature: Job board page
  User want to  Goal: Visit the site’s backend and create a new User
  
 Scenario: Searching for jobs and applying to them using XPath
    Given User on the Jobs page
    When User types in keywords for jobs and change job type
    Then User filters the job type to show only Full Time jobs
    Then User clicks on a job from the list to see job details
    Then User finds the title of the job listing and prints it to console
    Then User clicks on Apply for job
    
    Scenario: Post a job using details passed from the Feature file
    Given User is on Job page
    When User goes to Post page
    Then User enters "test" and "testTitle" and "India" and selects "Full Time" and "Test" and "test" and "TestCompany"
    Then User navigates to job page and verifies job "testTitle" listing on page
    
  Scenario Outline: Visit the site’s backend and create a new User
    Given User is on Login page
    When User enter "<username>" and "<password>" and login
    Then User click on the Users link and Add New button
    Then User enters "<Email>" and "<FirstName>" and "<LastName>" and "<Website>" 
    Then User verify that the user is created

    Examples: 
      | username  | password |Email           |FirstName |LastName  |Website       |
      | root      | pa$$w0rd |test   |test1fname|test1lname|www.google.com|
        
    Scenario Outline: Post a job using details passed from examples table in feature file
    Given User on the Job page
    When User navigates to Post page
    Then User enters "<YourEmail>" and "<JobTitle>" and "<Location>" and "<JobType>" and "<Description>" and "<ApplicationEmail>" and "<CompanyName>"
    Then User goes to job page and verifies job "<JobTitle>" listing on page


    Examples: 
      | YourEmail  | JobTitle | Location  |JobType   |Description|ApplicationEmail|CompanyName|
      | test       |testTitle | India     |Full Time | Test      |  test          |TestCompany|  
