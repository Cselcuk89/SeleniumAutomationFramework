package com.selcuk.stepdefinitions;

import com.selcuk.projectPages.OrangeHRMHomePage;
import com.selcuk.projectPages.OrangeHRMLoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.testng.Assert.assertTrue;

public class LoginStepDefinitions {

    private OrangeHRMLoginPage loginPage;
    private OrangeHRMHomePage homePage;

    @Given("I am on the OrangeHRM login page")
    public void iAmOnTheOrangeHRMLoginPage() {
        loginPage = new OrangeHRMLoginPage();
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUserName(username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("I click on the login button")
    public void iClickOnTheLoginButton() {
        homePage = loginPage.clickLogin();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        // Verify login by checking for welcome element - clickWelcomePage will fail if not logged in
        // The explicit wait in the page object will throw an exception if element is not found
        homePage.clickWelcomePage();
        // If we reach here without exception, the login was successful
        assertTrue(true, "User logged in successfully - welcome element found");
    }

    @Then("I should see the page title as {string}")
    public void iShouldSeeThePageTitleAs(String expectedTitle) {
        String actualTitle = loginPage.getTitle();
        assertTrue(actualTitle.contains(expectedTitle), 
                "Expected title to contain: " + expectedTitle + " but was: " + actualTitle);
    }

    @When("I click on the logout button")
    public void iClickOnTheLogoutButton() {
        homePage.clickWelcomePage();
        loginPage = homePage.clickLogout();
    }

    @Then("I should be logged out successfully")
    public void iShouldBeLoggedOutSuccessfully() {
        // User is on login page again after logout
        String title = loginPage.getTitle();
        assertTrue(title.contains("OrangeHRM"), "Should be on login page after logout");
    }
}
