@regression @admin
Feature: OrangeHRM Admin Module
  As a system administrator
  I want to manage users and system settings
  So that I can control access and configure the application

  Background:
    Given I am logged into OrangeHRM as "Admin" with password "admin123"

  @navigation
  Scenario: Navigate to Admin module
    When I click on Admin menu
    Then I should see the admin page

  @navigation @users
  Scenario: Navigate to System Users page
    When I click on Admin menu
    And I click on User Management
    And I click on Users link
    Then I should see the users list

  @search @users
  Scenario: Search for a system user
    When I click on Admin menu
    And I click on User Management
    And I click on Users link
    And I search for username "Admin"
    And I click search on admin page
    Then I should see user search results

  @search @users
  Scenario: Reset user search
    When I click on Admin menu
    And I click on User Management
    And I click on Users link
    And I search for username "Test"
    And I click reset on admin page
    Then the user search should be cleared

  @navigation @organization
  Scenario: Navigate to Organization General Info
    When I click on Admin menu
    And I click on Organization link
    And I click on General Information link
    Then I should see organization information page

  @navigation @organization
  Scenario: Navigate to Locations page
    When I click on Admin menu
    And I click on Organization link
    And I click on Locations link
    Then I should see locations list

  @navigation @job
  Scenario: Navigate to Job Titles page
    When I click on Admin menu
    And I click on Job Titles link
    Then I should see job titles page
