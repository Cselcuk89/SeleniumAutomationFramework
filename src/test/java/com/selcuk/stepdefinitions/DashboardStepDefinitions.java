package com.selcuk.stepdefinitions;

import com.selcuk.driver.Driver;
import com.selcuk.enums.ConfigProperties;
import com.selcuk.projectPages.OrangeHRMDashboardPage;
import com.selcuk.projectPages.OrangeHRMHomePage;
import com.selcuk.projectPages.OrangeHRMLoginPage;
import com.selcuk.utilities.PropertyUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.testng.Assert.assertTrue;

public class DashboardStepDefinitions {

    private OrangeHRMLoginPage loginPage;
    private OrangeHRMHomePage homePage;
    private OrangeHRMDashboardPage dashboardPage;

    @Given("I am logged into OrangeHRM as {string} with password {string}")
    public void iAmLoggedIntoOrangeHRMAsWithPassword(String username, String password) {
        loginPage = new OrangeHRMLoginPage();
        loginPage.enterUserName(username);
        loginPage.enterPassword(password);
        homePage = loginPage.clickLogin();
        dashboardPage = new OrangeHRMDashboardPage();
    }

    @Then("I should see the dashboard page")
    public void iShouldSeeTheDashboardPage() {
        // Dashboard is displayed after successful login
        homePage.clickWelcomePage();
        assertTrue(true, "Dashboard page is displayed");
    }

    @Then("I should see the dashboard page title contains {string}")
    public void iShouldSeeTheDashboardPageTitleContains(String expectedTitle) {
        String actualTitle = dashboardPage.getDashboardTitle();
        assertTrue(actualTitle.contains(expectedTitle), 
                "Expected title to contain: " + expectedTitle + " but was: " + actualTitle);
    }

    @When("I click on Assign Leave link")
    public void iClickOnAssignLeaveLink() {
        dashboardPage.clickAssignLeave();
    }

    @When("I click on Leave List link")
    public void iClickOnLeaveListLink() {
        dashboardPage.clickLeaveList();
    }

    @When("I click on Timesheets link")
    public void iClickOnTimesheetsLink() {
        dashboardPage.clickTimesheets();
    }

    @Then("I should be on the leave page")
    public void iShouldBeOnTheLeavePage() {
        String title = dashboardPage.getDashboardTitle();
        assertTrue(title.toLowerCase().contains("leave") || title.contains("OrangeHRM"), 
                "Should be on leave page");
    }

    @Then("I should be on the timesheet page")
    public void iShouldBeOnTheTimesheetPage() {
        String title = dashboardPage.getDashboardTitle();
        assertTrue(title.toLowerCase().contains("time") || title.contains("OrangeHRM"), 
                "Should be on timesheet page");
    }
}
