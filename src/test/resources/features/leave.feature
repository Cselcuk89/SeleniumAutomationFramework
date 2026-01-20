@regression @leave
Feature: OrangeHRM Leave Management
  As an employee of the organization
  I want to manage my leave requests
  So that I can take time off when needed

  Background:
    Given I am logged into OrangeHRM as "Admin" with password "admin123"

  @navigation
  Scenario: Navigate to Leave module
    When I click on Leave menu
    Then I should see the leave page

  @navigation
  Scenario: Navigate to Apply Leave page
    When I click on Leave menu
    And I click on Apply Leave link
    Then I should be on the apply leave page

  @navigation
  Scenario: Navigate to My Leave page
    When I click on Leave menu
    And I click on My Leave link
    Then I should see my leave list

  @navigation
  Scenario: Navigate to Leave List page
    When I click on Leave menu
    And I click on Leave List link
    Then I should see the leave list page

  @search
  Scenario: Search leave records with date filter
    When I click on Leave menu
    And I click on Leave List link
    And I click search on leave page
    Then I should see leave search results

  @search
  Scenario: Reset leave search filters
    When I click on Leave menu
    And I click on Leave List link
    And I click reset on leave page
    Then the leave filters should be cleared
