package com.selcuk.tests;

import com.selcuk.config.AppConfig;
import com.selcuk.config.SeleniumConfig;
import com.selcuk.config.TestConfig;
import com.selcuk.config.WebDriverFactory;
import com.selcuk.driver.Driver;
import com.selcuk.driver.DriverManager;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;

/**
 * Base test class providing Spring Boot integration and common test functionality.
 * All test classes should extend this class for consistent behavior.
 */
@Slf4j
@SpringBootTest
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    protected WebDriverFactory webDriverFactory;
    
    @Autowired
    protected AppConfig appConfig;
    
    @Autowired
    protected SeleniumConfig seleniumConfig;
    
    @Autowired
    protected TestConfig testConfig;
    
    /**
     * Initializes WebDriver before each test method.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        log.info("========== Starting Test Setup ==========");
        Driver.initializeDriver();
        log.info("WebDriver initialized successfully");
    }
    
    /**
     * Cleans up WebDriver after each test method.
     * Takes screenshot on failure and attaches to Allure report.
     *
     * @param result Test result
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        log.info("========== Test Cleanup ==========");
        
        if (result.getStatus() == ITestResult.FAILURE) {
            log.error("Test failed: {}", result.getName());
            captureScreenshotForAllure("Failure Screenshot");
        }
        
        Driver.quitDriver();
        log.info("WebDriver closed successfully");
    }
    
    /**
     * Captures screenshot and attaches to Allure report.
     *
     * @param name Screenshot name
     */
    protected void captureScreenshotForAllure(String name) {
        try {
            if (DriverManager.hasDriver()) {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(name, "image/png", 
                        new ByteArrayInputStream(screenshot), "png");
                log.debug("Screenshot captured: {}", name);
            }
        } catch (Exception e) {
            log.warn("Failed to capture screenshot: {}", e.getMessage());
        }
    }
    
    /**
     * Adds step to Allure report.
     *
     * @param stepName Step description
     */
    protected void allureStep(String stepName) {
        Allure.step(stepName);
    }
    
    /**
     * Gets the standard user credentials.
     *
     * @return Standard username
     */
    protected String getStandardUser() {
        return testConfig.getStandardUser();
    }
    
    /**
     * Gets the password for all users.
     *
     * @return Password
     */
    protected String getPassword() {
        return testConfig.getPassword();
    }
    
    /**
     * Gets the locked user credentials.
     *
     * @return Locked username
     */
    protected String getLockedUser() {
        return testConfig.getLockedUser();
    }
    
    /**
     * Gets the problem user credentials.
     *
     * @return Problem username
     */
    protected String getProblemUser() {
        return testConfig.getProblemUser();
    }
    
    /**
     * Gets the performance user credentials.
     *
     * @return Performance username
     */
    protected String getPerformanceUser() {
        return testConfig.getPerformanceUser();
    }
}
