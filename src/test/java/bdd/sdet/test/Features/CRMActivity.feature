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
@CRMActivity
Feature: Counting dashlets and Create leads using parameterization 
  I want to Goal:​ ​Open the homepage and count the number of the dashlets on the page and ​Open the Leads page and add multiple lead accounts using values passed from  Feature file
  
  Background: User is Logged In
    Given User navigate to the login page
    When User submit username "admin" and password "pa$$w0rd"
    Then User should be logged in 
    
  Scenario: Counting dashlets 
    When User count the number of Dashlets on the homepage
    Then User print the number and title of each Dashlet into the console
    
   Scenario: Create leads using parameterization
    When User navigate to Sales -> Leads -> Create Lead
    Then User enters "firstName","lastName","test@test.com","09990909090" to create lead accounts
    Then user navigate to the View Leads page to see result "firstName lastName"
 

  Scenario Outline: Schedule a meeting and invite members
    Given User log in to website using username "<userName>" and password "<password>" and validate
    When User navigates to Activities -> Meetings -> Schedule a Meeting 
    Then User enters details "<Subject>" of the meeting
    Then User search for members and add them to the meeting
    Then User confirms creation of the meeting with subject "<Subject>"
    Then ends 

    Examples: 
      | userName| password  | Subject   |
      | admin   | pa$$w0rd  | test221   |
    
 Scenario Outline: To use an external Excel to add products
    Given User logs into CRM website using username "<userName>" and password "<password>" and validate
    When User navigates to All -> Products-> Create Product 
    Then User enters details "<productName>" product name and price "<price>"
    Then User to the View Products page to see the product listed "<productName>"
    Then end browser

    Examples: 
       | userName| password  | productName   | price |
       | admin   | pa$$w0rd  | test221       | 100   |

    