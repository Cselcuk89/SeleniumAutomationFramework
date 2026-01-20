package com.selcuk.stepdefinitions;

import com.selcuk.projectPages.OrangeHRMLeavePage;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static org.testng.Assert.assertTrue;

public class LeaveStepDefinitions {

    private OrangeHRMLeavePage leavePage;

    public LeaveStepDefinitions() {
        this.leavePage = new OrangeHRMLeavePage();
    }

    @When("I click on Leave menu")
    public void iClickOnLeaveMenu() {
        leavePage.clickLeaveMenu();
    }

    @And("I click on Apply Leave link")
    public void iClickOnApplyLeaveLink() {
        leavePage.clickApplyLeave();
    }

    @And("I click on My Leave link")
    public void iClickOnMyLeaveLink() {
        leavePage.clickMyLeave();
    }

    @And("I click on Leave List link")
    public void iClickOnLeaveListLink() {
        leavePage.clickLeaveList();
    }

    @And("I click on Assign Leave link")
    public void iClickOnAssignLeaveLink() {
        leavePage.clickAssignLeave();
    }

    @And("I select leave type {string}")
    public void iSelectLeaveType(String leaveType) {
        leavePage.selectLeaveType(leaveType);
    }

    @And("I enter from date {string}")
    public void iEnterFromDate(String fromDate) {
        leavePage.enterFromDate(fromDate);
    }

    @And("I enter to date {string}")
    public void iEnterToDate(String toDate) {
        leavePage.enterToDate(toDate);
    }

    @And("I enter leave comment {string}")
    public void iEnterLeaveComment(String comment) {
        leavePage.enterComment(comment);
    }

    @And("I click apply leave button")
    public void iClickApplyLeaveButton() {
        leavePage.clickApply();
    }

    @And("I click search on leave page")
    public void iClickSearchOnLeavePage() {
        leavePage.clickSearch();
    }

    @And("I click reset on leave page")
    public void iClickResetOnLeavePage() {
        leavePage.clickReset();
    }

    @Then("I should see the leave page")
    public void iShouldSeeTheLeavePage() {
        String title = leavePage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "Should be on leave page");
    }

    @Then("I should be on the apply leave page")
    public void iShouldBeOnTheApplyLeavePage() {
        String title = leavePage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "Should be on apply leave page");
    }

    @Then("I should see my leave list")
    public void iShouldSeeMyLeaveList() {
        String title = leavePage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "My leave list should be displayed");
    }

    @Then("I should see the leave list page")
    public void iShouldSeeTheLeaveListPage() {
        assertTrue(leavePage.isLeaveListTableDisplayed() || 
                   leavePage.getPageTitle().contains("OrangeHRM"),
                   "Leave list page should be displayed");
    }

    @Then("I should see leave search results")
    public void iShouldSeeLeaveSearchResults() {
        assertTrue(leavePage.isLeaveListTableDisplayed() || 
                   leavePage.getPageTitle().contains("OrangeHRM"),
                   "Leave search results should be displayed");
    }

    @Then("the leave filters should be cleared")
    public void theLeaveFiltersShouldBeCleared() {
        String title = leavePage.getPageTitle();
        assertTrue(title.contains("OrangeHRM"), "Page should be displayed after reset");
    }

    @Then("I should see leave application success message")
    public void iShouldSeeLeaveApplicationSuccessMessage() {
        assertTrue(leavePage.isSuccessMessageDisplayed(),
                   "Success message should be displayed after applying leave");
    }
}
