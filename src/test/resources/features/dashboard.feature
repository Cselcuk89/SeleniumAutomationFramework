@smoke @dashboard
Feature: OrangeHRM Dashboard Functionality
  As a logged in user of OrangeHRM
  I want to access dashboard features
  So that I can navigate to different modules

  Background:
    Given I am logged into OrangeHRM as "Admin" with password "admin123"

  @positive
  Scenario: Verify dashboard is displayed after login
    Then I should see the dashboard page

  @positive
  Scenario: Verify dashboard page title
    Then I should see the dashboard page title contains "OrangeHRM"

  @navigation
  Scenario: Navigate to Assign Leave from dashboard
    When I click on Assign Leave link
    Then I should be on the leave page

  @navigation
  Scenario: Navigate to Leave List from dashboard
    When I click on Leave List link
    Then I should be on the leave page

  @navigation
  Scenario: Navigate to Timesheets from dashboard
    When I click on Timesheets link
    Then I should be on the timesheet page
