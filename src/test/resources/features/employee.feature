@regression @employee
Feature: OrangeHRM Employee Management
  As an HR administrator
  I want to manage employee information
  So that I can maintain accurate employee records

  Background:
    Given I am logged into OrangeHRM as "Admin" with password "admin123"

  @positive @pim
  Scenario: Navigate to PIM module
    When I click on PIM menu
    Then I should see the employee list page

  @positive @pim
  Scenario: Navigate to Add Employee page
    When I click on PIM menu
    And I click on Add Employee link
    Then I should be on the add employee page

  @positive @search
  Scenario: Search for an employee
    When I click on PIM menu
    And I click on Employee List link
    And I search for employee "Admin"
    And I click search button on employee page
    Then I should see search results displayed

  @positive @search
  Scenario: Reset employee search
    When I click on PIM menu
    And I click on Employee List link
    And I search for employee "Test"
    And I click reset button on employee page
    Then the search field should be cleared

  @positive @add
  Scenario Outline: Add new employee with basic information
    When I click on PIM menu
    And I click on Add Employee link
    And I enter employee first name "<firstName>"
    And I enter employee middle name "<middleName>"
    And I enter employee last name "<lastName>"
    Then the save button should be visible

    Examples:
      | firstName | middleName | lastName |
      | John      | William    | Doe      |
      | Jane      | Marie      | Smith    |
