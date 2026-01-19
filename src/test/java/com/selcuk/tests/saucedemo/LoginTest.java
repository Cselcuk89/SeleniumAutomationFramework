package com.selcuk.tests.saucedemo;

import com.selcuk.annotations.FrameworkAnnotation;
import com.selcuk.enums.CategoryType;
import com.selcuk.projectPages.saucedemo.InventoryPage;
import com.selcuk.projectPages.saucedemo.LoginPage;
import com.selcuk.tests.BaseTest;
import com.selcuk.tests.TestData;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for SauceDemo Login functionality.
 * Covers positive and negative login scenarios.
 */
@Slf4j
@Epic("SauceDemo E-Commerce Platform")
@Feature("User Authentication")
public class LoginTest extends BaseTest {
    
    @Test(description = "Verify successful login with valid credentials")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.SMOKE, CategoryType.REGRESSION})
    @Story("User Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test that verifies user can login with valid standard_user credentials")
    public void testSuccessfulLogin() {
        log.info("Starting successful login test");
        
        LoginPage loginPage = new LoginPage();
        
        // Verify we're on the login page
        assertThat(loginPage.isOnLoginPage())
                .as("Should be on login page")
                .isTrue();
        
        // Perform login
        InventoryPage inventoryPage = loginPage.login(getStandardUser(), getPassword());
        
        // Verify successful login
        assertThat(inventoryPage.isOnInventoryPage())
                .as("Should be on inventory page after successful login")
                .isTrue();
        
        assertThat(inventoryPage.getProductsTitle())
                .as("Products title should be displayed")
                .isEqualTo(TestData.PAGE_TITLE_PRODUCTS);
        
        log.info("Successful login test completed");
    }
    
    @Test(description = "Verify login fails with locked out user")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("User Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test that verifies locked_out_user cannot login and receives appropriate error")
    public void testLockedOutUserLogin() {
        log.info("Starting locked out user login test");
        
        LoginPage loginPage = new LoginPage();
        
        // Attempt login with locked user
        loginPage.loginExpectingError(getLockedUser(), getPassword());
        
        // Verify error message
        assertThat(loginPage.isErrorDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        
        assertThat(loginPage.getErrorMessage())
                .as("Error message should indicate user is locked out")
                .contains(TestData.ERROR_LOCKED_OUT);
        
        log.info("Locked out user login test completed");
    }
    
    @Test(description = "Verify login fails with invalid credentials")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("User Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test that verifies login fails with invalid username and password")
    public void testInvalidCredentialsLogin() {
        log.info("Starting invalid credentials login test");
        
        LoginPage loginPage = new LoginPage();
        
        // Attempt login with invalid credentials
        loginPage.loginExpectingError(TestData.INVALID_USERNAME, TestData.INVALID_PASSWORD);
        
        // Verify error message
        assertThat(loginPage.isErrorDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        
        assertThat(loginPage.getErrorMessage())
                .as("Error message should indicate invalid credentials")
                .contains(TestData.ERROR_INVALID_CREDENTIALS);
        
        log.info("Invalid credentials login test completed");
    }
    
    @Test(description = "Verify login fails with empty username")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("User Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies login fails when username is empty")
    public void testEmptyUsernameLogin() {
        log.info("Starting empty username login test");
        
        LoginPage loginPage = new LoginPage();
        
        // Attempt login with empty username
        loginPage.enterPassword(getPassword())
                 .clickLoginButtonExpectingError();
        
        // Verify error message
        assertThat(loginPage.isErrorDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        
        assertThat(loginPage.getErrorMessage())
                .as("Error message should indicate username is required")
                .contains(TestData.ERROR_USERNAME_REQUIRED);
        
        log.info("Empty username login test completed");
    }
    
    @Test(description = "Verify login fails with empty password")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("User Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies login fails when password is empty")
    public void testEmptyPasswordLogin() {
        log.info("Starting empty password login test");
        
        LoginPage loginPage = new LoginPage();
        
        // Attempt login with empty password
        loginPage.enterUsername(getStandardUser())
                 .clickLoginButtonExpectingError();
        
        // Verify error message
        assertThat(loginPage.isErrorDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        
        assertThat(loginPage.getErrorMessage())
                .as("Error message should indicate password is required")
                .contains(TestData.ERROR_PASSWORD_REQUIRED);
        
        log.info("Empty password login test completed");
    }
    
    @Test(description = "Verify user can dismiss error message")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("User Login")
    @Severity(SeverityLevel.MINOR)
    @Description("Test that verifies user can dismiss the error message on login page")
    public void testDismissErrorMessage() {
        log.info("Starting dismiss error message test");
        
        LoginPage loginPage = new LoginPage();
        
        // Trigger error
        loginPage.clickLoginButtonExpectingError();
        
        // Verify error is displayed
        assertThat(loginPage.isErrorDisplayed())
                .as("Error message should be displayed")
                .isTrue();
        
        // Dismiss error
        loginPage.dismissError();
        
        // Verify error is no longer displayed
        assertThat(loginPage.isErrorDisplayed())
                .as("Error message should be dismissed")
                .isFalse();
        
        log.info("Dismiss error message test completed");
    }
}
