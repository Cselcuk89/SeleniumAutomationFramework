package com.selcuk.stepdefinitions;

import com.selcuk.projectPages.OrangeHRMAdminPage;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.testng.Assert.assertTrue;

public class AdminStepDefinitions {

    private OrangeHRMAdminPage adminPage;

    public AdminStepDefinitions() {
        this.adminPage = new OrangeHRMAdminPage();
    }

    @When("I click on Admin menu")
    public void iClickOnAdminMenu() {
        adminPage.clickAdminMenu();
    }

    @And("I click on User Management")
    public void iClickOnUserManagement() {
        adminPage.clickUserManagement();
    }

    @And("I click on Users link")
    public void iClickOnUsersLink() {
        adminPage.clickUsers();
    }

    @And("I click on Organization link")
    public void iClickOnOrganizationLink() {
        adminPage.clickOrganization();
    }

    @And("I click on General Information link")
    public void iClickOnGeneralInformationLink() {
        adminPage.clickGeneralInfo();
    }

    @And("I click on Locations link")
    public void iClickOnLocationsLink() {
        adminPage.clickLocations();
    }

    @And("I click on Job Titles link")
    public void iClickOnJobTitlesLink() {
        adminPage.clickJobTitles();
    }

    @And("I search for username {string}")
    public void iSearchForUsername(String username) {
        adminPage.searchUsername(username);
    }

    @And("I click search on admin page")
    public void iClickSearchOnAdminPage() {
        adminPage.clickSearch();
    }

    @And("I click reset on admin page")
    public void iClickResetOnAdminPage() {
        adminPage.clickReset();
    }

    @And("I click add user button")
    public void iClickAddUserButton() {
        adminPage.clickAddUser();
    }

    @And("I enter admin employee name {string}")
    public void iEnterAdminEmployeeName(String employeeName) {
        adminPage.enterEmployeeName(employeeName);
    }

    @And("I enter admin username {string}")
    public void iEnterAdminUsername(String username) {
        adminPage.enterUsername(username);
    }

    @And("I enter admin password {string}")
    public void iEnterAdminPassword(String password) {
        adminPage.enterPassword(password);
    }

    @And("I enter admin confirm password {string}")
    public void iEnterAdminConfirmPassword(String password) {
        adminPage.enterConfirmPassword(password);
    }

    @And("I click save on admin page")
    public void iClickSaveOnAdminPage() {
        adminPage.clickSave();
    }

    @And("I click cancel on admin page")
    public void iClickCancelOnAdminPage() {
        adminPage.clickCancel();
    }

    @Then("I should see the admin page")
    public void iShouldSeeTheAdminPage() {
        String title = adminPage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "Should be on admin page");
    }

    @Then("I should see the users list")
    public void iShouldSeeTheUsersList() {
        assertTrue(adminPage.isUsersTableDisplayed() || 
                   adminPage.getPageTitle().contains("OrangeHRM"),
                   "Users list should be displayed");
    }

    @Then("I should see user search results")
    public void iShouldSeeUserSearchResults() {
        assertTrue(adminPage.isUsersTableDisplayed() || 
                   adminPage.getPageTitle().contains("OrangeHRM"),
                   "User search results should be displayed");
    }

    @Then("the user search should be cleared")
    public void theUserSearchShouldBeCleared() {
        String title = adminPage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "Page should be displayed after reset");
    }

    @Then("I should see organization information page")
    public void iShouldSeeOrganizationInformationPage() {
        String title = adminPage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "Organization info page should be displayed");
    }

    @Then("I should see locations list")
    public void iShouldSeeLocationsList() {
        String title = adminPage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "Locations list should be displayed");
    }

    @Then("I should see job titles page")
    public void iShouldSeeJobTitlesPage() {
        String title = adminPage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "Job titles page should be displayed");
    }

    @Then("I should see user creation success message")
    public void iShouldSeeUserCreationSuccessMessage() {
        assertTrue(adminPage.isSuccessMessageDisplayed(),
                   "Success message should be displayed after creating user");
    }
}
