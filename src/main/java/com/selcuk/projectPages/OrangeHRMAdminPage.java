package com.selcuk.projectPages;

import com.selcuk.driver.DriverManager;
import com.selcuk.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class OrangeHRMAdminPage extends BasePage {
    
    private final By menuAdmin = By.id("menu_admin_viewAdminModule");
    private final By userManagementLink = By.id("menu_admin_UserManagement");
    private final By usersLink = By.id("menu_admin_viewSystemUsers");
    private final By jobLink = By.id("menu_admin_Job");
    private final By jobTitlesLink = By.id("menu_admin_viewJobTitleList");
    private final By organizationLink = By.id("menu_admin_Organization");
    private final By generalInfoLink = By.id("menu_admin_viewOrganizationGeneralInformation");
    private final By locationsLink = By.id("menu_admin_viewLocations");
    private final By qualificationsLink = By.id("menu_admin_Qualifications");
    private final By skillsLink = By.id("menu_admin_viewSkills");
    
    // System Users page
    private final By usernameSearchField = By.id("searchSystemUser_userName");
    private final By userRoleDropdown = By.id("searchSystemUser_userType");
    private final By statusDropdown = By.id("searchSystemUser_status");
    private final By searchButton = By.id("searchBtn");
    private final By resetButton = By.id("resetBtn");
    private final By addUserButton = By.id("btnAdd");
    private final By usersTable = By.id("resultTable");
    
    // Add User Form
    private final By userRoleAddDropdown = By.id("systemUser_userType");
    private final By employeeNameField = By.id("systemUser_employeeName_empName");
    private final By usernameField = By.id("systemUser_userName");
    private final By statusAddDropdown = By.id("systemUser_status");
    private final By passwordField = By.id("systemUser_password");
    private final By confirmPasswordField = By.id("systemUser_confirmPassword");
    private final By saveButton = By.id("btnSave");
    private final By cancelButton = By.id("btnCancel");
    
    private final By successMessage = By.xpath("//div[contains(@class,'message success')]");
    
    public OrangeHRMAdminPage clickAdminMenu() {
        click(menuAdmin, WaitStrategy.CLICKABLE, "Admin Menu");
        return this;
    }
    
    public OrangeHRMAdminPage clickUserManagement() {
        click(userManagementLink, WaitStrategy.CLICKABLE, "User Management");
        return this;
    }
    
    public OrangeHRMAdminPage clickUsers() {
        click(usersLink, WaitStrategy.CLICKABLE, "Users link");
        return this;
    }
    
    public OrangeHRMAdminPage clickJobTitles() {
        click(jobLink, WaitStrategy.CLICKABLE, "Job link");
        click(jobTitlesLink, WaitStrategy.CLICKABLE, "Job Titles link");
        return this;
    }
    
    public OrangeHRMAdminPage clickOrganization() {
        click(organizationLink, WaitStrategy.CLICKABLE, "Organization link");
        return this;
    }
    
    public OrangeHRMAdminPage clickGeneralInfo() {
        click(generalInfoLink, WaitStrategy.CLICKABLE, "General Information link");
        return this;
    }
    
    public OrangeHRMAdminPage clickLocations() {
        click(locationsLink, WaitStrategy.CLICKABLE, "Locations link");
        return this;
    }
    
    public OrangeHRMAdminPage searchUsername(String username) {
        sendKeys(usernameSearchField, username, WaitStrategy.PRESENCE, "Username Search");
        return this;
    }
    
    public OrangeHRMAdminPage clickSearch() {
        click(searchButton, WaitStrategy.CLICKABLE, "Search button");
        return this;
    }
    
    public OrangeHRMAdminPage clickReset() {
        click(resetButton, WaitStrategy.CLICKABLE, "Reset button");
        return this;
    }
    
    public OrangeHRMAdminPage clickAddUser() {
        click(addUserButton, WaitStrategy.CLICKABLE, "Add User button");
        return this;
    }
    
    public OrangeHRMAdminPage enterEmployeeName(String employeeName) {
        sendKeys(employeeNameField, employeeName, WaitStrategy.PRESENCE, "Employee Name");
        return this;
    }
    
    public OrangeHRMAdminPage enterUsername(String username) {
        sendKeys(usernameField, username, WaitStrategy.PRESENCE, "Username");
        return this;
    }
    
    public OrangeHRMAdminPage enterPassword(String password) {
        sendKeys(passwordField, password, WaitStrategy.PRESENCE, "Password");
        return this;
    }
    
    public OrangeHRMAdminPage enterConfirmPassword(String password) {
        sendKeys(confirmPasswordField, password, WaitStrategy.PRESENCE, "Confirm Password");
        return this;
    }
    
    public OrangeHRMAdminPage clickSave() {
        click(saveButton, WaitStrategy.CLICKABLE, "Save button");
        return this;
    }
    
    public OrangeHRMAdminPage clickCancel() {
        click(cancelButton, WaitStrategy.CLICKABLE, "Cancel button");
        return this;
    }
    
    public boolean isUsersTableDisplayed() {
        try {
            return DriverManager.getDriver().findElement(usersTable).isDisplayed();
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
