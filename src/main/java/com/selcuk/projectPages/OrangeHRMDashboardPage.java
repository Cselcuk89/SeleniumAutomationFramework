package com.selcuk.projectPages;

import com.selcuk.driver.DriverManager;
import com.selcuk.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class OrangeHRMDashboardPage extends BasePage {
    
    private final By dashboardHeader = By.xpath("//h1[contains(text(),'Dashboard')]");
    private final By quickLaunchPanel = By.id("dashboard-quick-launch-panel-menu_holder");
    private final By assignLeaveLink = By.id("menu_leave_assignLeave");
    private final By leaveListLink = By.id("menu_leave_viewLeaveList");
    private final By timesheetsLink = By.id("menu_time_viewMyTimesheet");
    private final By applyLeaveLink = By.id("menu_leave_applyLeave");
    private final By myLeaveLink = By.id("menu_leave_viewMyLeaveList");
    private final By employeeTimesheetsLink = By.id("menu_time_viewEmployeeTimesheet");
    
    public boolean isDashboardDisplayed() {
        try {
            return DriverManager.getDriver().findElement(dashboardHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public OrangeHRMDashboardPage clickAssignLeave() {
        click(assignLeaveLink, WaitStrategy.CLICKABLE, "Assign Leave link");
        return this;
    }
    
    public OrangeHRMDashboardPage clickLeaveList() {
        click(leaveListLink, WaitStrategy.CLICKABLE, "Leave List link");
        return this;
    }
    
    public OrangeHRMDashboardPage clickTimesheets() {
        click(timesheetsLink, WaitStrategy.CLICKABLE, "Timesheets link");
        return this;
    }
    
    public OrangeHRMDashboardPage clickApplyLeave() {
        click(applyLeaveLink, WaitStrategy.CLICKABLE, "Apply Leave link");
        return this;
    }
    
    public OrangeHRMDashboardPage clickMyLeave() {
        click(myLeaveLink, WaitStrategy.CLICKABLE, "My Leave link");
        return this;
    }
    
    public String getDashboardTitle() {
        return getPageTitle();
    }
}
