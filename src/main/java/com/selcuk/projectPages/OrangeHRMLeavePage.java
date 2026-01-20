package com.selcuk.projectPages;

import com.selcuk.driver.DriverManager;
import com.selcuk.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class OrangeHRMLeavePage extends BasePage {
    
    private final By menuLeave = By.id("menu_leave_viewLeaveModule");
    private final By applyLeaveLink = By.id("menu_leave_applyLeave");
    private final By myLeaveLink = By.id("menu_leave_viewMyLeaveList");
    private final By leaveListLink = By.id("menu_leave_viewLeaveList");
    private final By assignLeaveLink = By.id("menu_leave_assignLeave");
    
    // Apply Leave Form
    private final By leaveTypeDropdown = By.id("applyleave_txtLeaveType");
    private final By fromDateField = By.id("applyleave_txtFromDate");
    private final By toDateField = By.id("applyleave_txtToDate");
    private final By commentField = By.id("applyleave_txtComment");
    private final By applyButton = By.id("applyBtn");
    
    // Leave Balance
    private final By leaveBalanceTable = By.id("leaveBalanceTable");
    
    // My Leave List
    private final By leaveListTable = By.id("resultTable");
    private final By fromDateFilter = By.id("leaveList_txtFromDate");
    private final By toDateFilter = By.id("leaveList_txtToDate");
    private final By searchButton = By.id("btnSearch");
    private final By resetButton = By.id("btnReset");
    
    private final By successMessage = By.xpath("//div[contains(@class,'message success')]");
    
    public OrangeHRMLeavePage clickLeaveMenu() {
        click(menuLeave, WaitStrategy.CLICKABLE, "Leave Menu");
        return this;
    }
    
    public OrangeHRMLeavePage clickApplyLeave() {
        click(applyLeaveLink, WaitStrategy.CLICKABLE, "Apply Leave link");
        return this;
    }
    
    public OrangeHRMLeavePage clickMyLeave() {
        click(myLeaveLink, WaitStrategy.CLICKABLE, "My Leave link");
        return this;
    }
    
    public OrangeHRMLeavePage clickLeaveList() {
        click(leaveListLink, WaitStrategy.CLICKABLE, "Leave List link");
        return this;
    }
    
    public OrangeHRMLeavePage clickAssignLeave() {
        click(assignLeaveLink, WaitStrategy.CLICKABLE, "Assign Leave link");
        return this;
    }
    
    public OrangeHRMLeavePage selectLeaveType(String leaveType) {
        click(leaveTypeDropdown, WaitStrategy.CLICKABLE, "Leave Type dropdown");
        // Sanitize input to prevent XPath injection
        String sanitizedLeaveType = leaveType.replaceAll("[^a-zA-Z0-9\\s-]", "");
        By leaveTypeOption = By.xpath("//option[contains(text(),'" + sanitizedLeaveType + "')]");
        click(leaveTypeOption, WaitStrategy.CLICKABLE, "Leave Type: " + sanitizedLeaveType);
        return this;
    }
    
    public OrangeHRMLeavePage enterFromDate(String fromDate) {
        sendKeys(fromDateField, fromDate, WaitStrategy.PRESENCE, "From Date");
        return this;
    }
    
    public OrangeHRMLeavePage enterToDate(String toDate) {
        sendKeys(toDateField, toDate, WaitStrategy.PRESENCE, "To Date");
        return this;
    }
    
    public OrangeHRMLeavePage enterComment(String comment) {
        sendKeys(commentField, comment, WaitStrategy.PRESENCE, "Comment");
        return this;
    }
    
    public OrangeHRMLeavePage clickApply() {
        click(applyButton, WaitStrategy.CLICKABLE, "Apply button");
        return this;
    }
    
    public OrangeHRMLeavePage clickSearch() {
        click(searchButton, WaitStrategy.CLICKABLE, "Search button");
        return this;
    }
    
    public OrangeHRMLeavePage clickReset() {
        click(resetButton, WaitStrategy.CLICKABLE, "Reset button");
        return this;
    }
    
    public boolean isLeaveListTableDisplayed() {
        try {
            return DriverManager.getDriver().findElement(leaveListTable).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean isSuccessMessageDisplayed() {
        try {
            return DriverManager.getDriver().findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getPageTitle() {
        return super.getPageTitle();
    }
}
