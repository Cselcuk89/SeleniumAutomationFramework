package com.selcuk.projectPages;

import com.selcuk.driver.DriverManager;
import com.selcuk.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class OrangeHRMEmployeePage extends BasePage {
    
    private final By menuPIM = By.id("menu_pim_viewPimModule");
    private final By addEmployeeLink = By.id("menu_pim_addEmployee");
    private final By employeeListLink = By.id("menu_pim_viewEmployeeList");
    private final By employeeSearchField = By.id("empsearch_employee_name_empName");
    private final By searchButton = By.id("searchBtn");
    private final By resetButton = By.id("resetBtn");
    
    // Add Employee Form fields
    private final By firstNameField = By.id("firstName");
    private final By middleNameField = By.id("middleName");
    private final By lastNameField = By.id("lastName");
    private final By employeeIdField = By.id("employeeId");
    private final By saveButton = By.id("btnSave");
    private final By createLoginDetailsCheckbox = By.id("chkLogin");
    private final By usernameField = By.id("user_name");
    private final By passwordField = By.id("user_password");
    private final By confirmPasswordField = By.id("re_password");
    
    private final By successMessage = By.xpath("//div[contains(@class,'message success')]");
    private final By employeeTable = By.id("resultTable");
    
    public OrangeHRMEmployeePage clickPIMMenu() {
        click(menuPIM, WaitStrategy.CLICKABLE, "PIM Menu");
        return this;
    }
    
    public OrangeHRMEmployeePage clickAddEmployee() {
        click(addEmployeeLink, WaitStrategy.CLICKABLE, "Add Employee link");
        return this;
    }
    
    public OrangeHRMEmployeePage clickEmployeeList() {
        click(employeeListLink, WaitStrategy.CLICKABLE, "Employee List link");
        return this;
    }
    
    public OrangeHRMEmployeePage enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName, WaitStrategy.PRESENCE, "First Name");
        return this;
    }
    
    public OrangeHRMEmployeePage enterMiddleName(String middleName) {
        sendKeys(middleNameField, middleName, WaitStrategy.PRESENCE, "Middle Name");
        return this;
    }
    
    public OrangeHRMEmployeePage enterLastName(String lastName) {
        sendKeys(lastNameField, lastName, WaitStrategy.PRESENCE, "Last Name");
        return this;
    }
    
    public OrangeHRMEmployeePage enterEmployeeId(String employeeId) {
        sendKeys(employeeIdField, employeeId, WaitStrategy.PRESENCE, "Employee ID");
        return this;
    }
    
    public OrangeHRMEmployeePage clickSave() {
        click(saveButton, WaitStrategy.CLICKABLE, "Save button");
        return this;
    }
    
    public OrangeHRMEmployeePage searchEmployee(String employeeName) {
        sendKeys(employeeSearchField, employeeName, WaitStrategy.PRESENCE, "Employee Search");
        return this;
    }
    
    public OrangeHRMEmployeePage clickSearch() {
        click(searchButton, WaitStrategy.CLICKABLE, "Search button");
        return this;
    }
    
    public OrangeHRMEmployeePage clickReset() {
        click(resetButton, WaitStrategy.CLICKABLE, "Reset button");
        return this;
    }
    
    public boolean isEmployeeTableDisplayed() {
        try {
            return DriverManager.getDriver().findElement(employeeTable).isDisplayed();
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
