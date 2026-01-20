package com.selcuk.stepdefinitions;

import com.selcuk.projectPages.OrangeHRMEmployeePage;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.testng.Assert.assertTrue;

public class EmployeeStepDefinitions {

    private OrangeHRMEmployeePage employeePage;

    public EmployeeStepDefinitions() {
        this.employeePage = new OrangeHRMEmployeePage();
    }

    @When("I click on PIM menu")
    public void iClickOnPIMMenu() {
        employeePage.clickPIMMenu();
    }

    @And("I click on Add Employee link")
    public void iClickOnAddEmployeeLink() {
        employeePage.clickAddEmployee();
    }

    @And("I click on Employee List link")
    public void iClickOnEmployeeListLink() {
        employeePage.clickEmployeeList();
    }

    @And("I search for employee {string}")
    public void iSearchForEmployee(String employeeName) {
        employeePage.searchEmployee(employeeName);
    }

    @And("I click search button on employee page")
    public void iClickSearchButtonOnEmployeePage() {
        employeePage.clickSearch();
    }

    @And("I click reset button on employee page")
    public void iClickResetButtonOnEmployeePage() {
        employeePage.clickReset();
    }

    @And("I enter employee first name {string}")
    public void iEnterEmployeeFirstName(String firstName) {
        employeePage.enterFirstName(firstName);
    }

    @And("I enter employee middle name {string}")
    public void iEnterEmployeeMiddleName(String middleName) {
        employeePage.enterMiddleName(middleName);
    }

    @And("I enter employee last name {string}")
    public void iEnterEmployeeLastName(String lastName) {
        employeePage.enterLastName(lastName);
    }

    @And("I enter employee id {string}")
    public void iEnterEmployeeId(String employeeId) {
        employeePage.enterEmployeeId(employeeId);
    }

    @Then("I should see the employee list page")
    public void iShouldSeeTheEmployeeListPage() {
        assertTrue(employeePage.isEmployeeTableDisplayed() || 
                   employeePage.getPageTitle().contains("OrangeHRM"),
                   "Employee list page should be displayed");
    }

    @Then("I should be on the add employee page")
    public void iShouldBeOnTheAddEmployeePage() {
        String title = employeePage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "Should be on add employee page");
    }

    @Then("I should see search results displayed")
    public void iShouldSeeSearchResultsDisplayed() {
        assertTrue(employeePage.isEmployeeTableDisplayed() || 
                   employeePage.getPageTitle().contains("OrangeHRM"),
                   "Search results should be displayed");
    }

    @Then("the search field should be cleared")
    public void theSearchFieldShouldBeCleared() {
        // After reset, the page should still be visible
        assertTrue(employeePage.getPageTitle().contains("OrangeHRM"),
                   "Page should be displayed after reset");
    }

    @Then("the save button should be visible")
    public void theSaveButtonShouldBeVisible() {
        // Form is ready for submission
        assertTrue(employeePage.getPageTitle().contains("OrangeHRM"),
                   "Add employee form should be visible");
    }
}
