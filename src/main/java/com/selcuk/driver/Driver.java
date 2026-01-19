package com.selcuk.driver;

import com.selcuk.config.AppConfig;
import com.selcuk.config.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Driver lifecycle manager with Spring Boot integration.
 * Handles driver initialization, navigation, and cleanup.
 */
@Slf4j
@Component
public class Driver {
    
    private static WebDriverFactory webDriverFactory;
    private static AppConfig appConfig;
    
    @Autowired
    public void setWebDriverFactory(WebDriverFactory factory) {
        Driver.webDriverFactory = factory;
    }
    
    @Autowired
    public void setAppConfig(AppConfig config) {
        Driver.appConfig = config;
    }
    
    /**
     * Initializes WebDriver for the current thread.
     * Uses the configured browser and navigates to base URL.
     */
    public static void initializeDriver() {
        if (!DriverManager.hasDriver()) {
            log.info("Initializing WebDriver");
            WebDriver driver = webDriverFactory.createDriver();
            DriverManager.setDriver(driver);
            navigateToBaseUrl();
        }
    }
    
    /**
     * Initializes WebDriver with custom browser.
     * For backward compatibility.
     *
     * @param browser Browser name
     * @param version Browser version (for remote)
     */
    public static void initializeDriver(String browser, String version) {
        if (!DriverManager.hasDriver()) {
            log.info("Initializing WebDriver: {} (version: {})", browser, version);
            WebDriver driver = webDriverFactory.createDriver();
            DriverManager.setDriver(driver);
            navigateToBaseUrl();
        }
    }
    
    /**
     * Navigates to a specific URL.
     *
     * @param url URL to navigate to
     */
    public static void navigateTo(String url) {
        DriverManager.ifPresent(driver -> {
            log.info("Navigating to: {}", url);
            driver.get(url);
        });
    }
    
    /**
     * Navigates to the configured base URL.
     */
    public static void navigateToBaseUrl() {
        if (Objects.nonNull(appConfig)) {
            navigateTo(appConfig.getBaseUrl());
        }
    }
    
    /**
     * Quits the driver and cleans up resources.
     */
    public static void quitDriver() {
        DriverManager.ifPresent(driver -> {
            log.info("Quitting WebDriver");
            driver.quit();
        });
        DriverManager.unload();
    }
    
    /**
     * Executes an action on the current driver.
     *
     * @param action Action to execute
     */
    public static void execute(Consumer<WebDriver> action) {
        DriverManager.executeOnDriver(action);
    }
    
    /**
     * Refreshes the current page.
     */
    public static void refresh() {
        DriverManager.ifPresent(driver -> {
            log.debug("Refreshing page");
            driver.navigate().refresh();
        });
    }
    
    /**
     * Navigates back in browser history.
     */
    public static void navigateBack() {
        DriverManager.ifPresent(driver -> {
            log.debug("Navigating back");
            driver.navigate().back();
        });
    }
    
    /**
     * Navigates forward in browser history.
     */
    public static void navigateForward() {
        DriverManager.ifPresent(driver -> {
            log.debug("Navigating forward");
            driver.navigate().forward();
        });
    }
}
