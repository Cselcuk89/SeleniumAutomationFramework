@smoke @login
Feature: OrangeHRM Login Functionality
  As a user of OrangeHRM
  I want to be able to login to the application
  So that I can access the HR management features

  Background:
    Given I am on the OrangeHRM login page

  @positive
  Scenario: Successful login with valid credentials
    When I enter username "Admin"
    And I enter password "admin123"
    And I click on the login button
    Then I should be logged in successfully

  @positive
  Scenario: Verify page title on login page
    Then I should see the page title as "OrangeHRM"

  @positive
  Scenario: Login and logout successfully
    When I enter username "Admin"
    And I enter password "admin123"
    And I click on the login button
    Then I should be logged in successfully
    When I click on the logout button
    Then I should be logged out successfully

  @positive
  Scenario Outline: Login with different valid credentials
    When I enter username "<username>"
    And I enter password "<password>"
    And I click on the login button
    Then I should be logged in successfully

    Examples:
      | username | password |
      | Admin    | admin123 |
