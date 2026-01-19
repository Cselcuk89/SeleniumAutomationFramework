package com.selcuk.projectPages.saucedemo;

import com.selcuk.enums.WaitStrategy;
import com.selcuk.projectPages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

/**
 * Page Object for SauceDemo Login Page.
 * URL: https://www.saucedemo.com/
 */
@Slf4j
public class LoginPage extends BasePage<LoginPage> {
    
    // ==================== Locators ====================
    
    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    private static final By ERROR_BUTTON = By.cssSelector(".error-button");
    private static final By LOGO = By.className("login_logo");
    
    // ==================== Actions ====================
    
    /**
     * Enters username in the username field.
     *
     * @param username Username to enter
     * @return Current page instance
     */
    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        log.info("Entering username: {}", username);
        return sendKeys(USERNAME_INPUT, username, WaitStrategy.VISIBLE, "Username");
    }
    
    /**
     * Enters password in the password field.
     *
     * @param password Password to enter
     * @return Current page instance
     */
    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        log.info("Entering password");
        return sendKeys(PASSWORD_INPUT, password, WaitStrategy.VISIBLE, "Password");
    }
    
    /**
     * Clicks the login button.
     *
     * @return InventoryPage instance on successful login
     */
    @Step("Click login button")
    public InventoryPage clickLoginButton() {
        log.info("Clicking login button");
        click(LOGIN_BUTTON, WaitStrategy.CLICKABLE, "Login Button");
        return new InventoryPage();
    }
    
    /**
     * Clicks login button expecting an error.
     *
     * @return Current page instance
     */
    @Step("Click login button (expecting error)")
    public LoginPage clickLoginButtonExpectingError() {
        log.info("Clicking login button (expecting error)");
        click(LOGIN_BUTTON, WaitStrategy.CLICKABLE, "Login Button");
        return this;
    }
    
    /**
     * Performs complete login flow.
     *
     * @param username Username to enter
     * @param password Password to enter
     * @return InventoryPage instance
     */
    @Step("Login with username: {username}")
    public InventoryPage login(String username, String password) {
        log.info("Performing login with username: {}", username);
        return enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
    }
    
    /**
     * Performs login expecting an error.
     *
     * @param username Username to enter
     * @param password Password to enter
     * @return Current page instance
     */
    @Step("Login with invalid credentials: {username}")
    public LoginPage loginExpectingError(String username, String password) {
        log.info("Performing login with invalid credentials: {}", username);
        return enterUsername(username)
                .enterPassword(password)
                .clickLoginButtonExpectingError();
    }
    
    // ==================== Verifications ====================
    
    /**
     * Gets the error message text.
     *
     * @return Error message text
     */
    @Step("Get error message")
    public String getErrorMessage() {
        String errorText = getText(ERROR_MESSAGE, WaitStrategy.VISIBLE);
        log.info("Error message: {}", errorText);
        return errorText;
    }
    
    /**
     * Checks if error message is displayed.
     *
     * @return true if error is displayed
     */
    @Step("Check if error message is displayed")
    public boolean isErrorDisplayed() {
        return isDisplayed(ERROR_MESSAGE);
    }
    
    /**
     * Dismisses the error message.
     *
     * @return Current page instance
     */
    @Step("Dismiss error message")
    public LoginPage dismissError() {
        log.info("Dismissing error message");
        return click(ERROR_BUTTON, WaitStrategy.CLICKABLE, "Error Dismiss Button");
    }
    
    /**
     * Checks if user is on login page.
     *
     * @return true if on login page
     */
    @Step("Verify on login page")
    public boolean isOnLoginPage() {
        return isDisplayed(LOGIN_BUTTON) && isDisplayed(LOGO);
    }
    
    /**
     * Gets the page title.
     *
     * @return Page title
     */
    public String getTitle() {
        return getPageTitle();
    }
    
    /**
     * Gets the username field value.
     *
     * @return Username field value
     */
    public String getUsernameValue() {
        return getAttribute(USERNAME_INPUT, "value", WaitStrategy.VISIBLE);
    }
    
    /**
     * Clears the username field.
     *
     * @return Current page instance
     */
    @Step("Clear username field")
    public LoginPage clearUsername() {
        return clear(USERNAME_INPUT, WaitStrategy.VISIBLE, "Username");
    }
    
    /**
     * Clears the password field.
     *
     * @return Current page instance
     */
    @Step("Clear password field")
    public LoginPage clearPassword() {
        return clear(PASSWORD_INPUT, WaitStrategy.VISIBLE, "Password");
    }
}
