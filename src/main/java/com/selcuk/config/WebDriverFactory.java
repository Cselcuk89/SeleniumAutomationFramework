package com.selcuk.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Spring-managed WebDriver factory using functional programming patterns.
 * Supports Chrome, Firefox, and Edge browsers in local and remote modes.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WebDriverFactory {
    
    private final SeleniumConfig seleniumConfig;
    
    private final Map<String, Supplier<WebDriver>> localDriverSuppliers = new HashMap<>();
    private final Map<String, Supplier<WebDriver>> remoteDriverSuppliers = new HashMap<>();
    
    {
        // Local driver suppliers
        localDriverSuppliers.put("chrome", this::createChromeDriver);
        localDriverSuppliers.put("firefox", this::createFirefoxDriver);
        localDriverSuppliers.put("edge", this::createEdgeDriver);
    }
    
    /**
     * Creates a WebDriver instance based on configuration.
     * Uses functional programming with Supplier pattern for browser creation.
     *
     * @return configured WebDriver instance
     */
    public WebDriver createDriver() {
        String browser = seleniumConfig.getBrowser().toLowerCase();
        log.info("Creating {} driver in {} mode", browser, 
                seleniumConfig.isRemote() ? "remote" : "local");
        
        WebDriver driver = seleniumConfig.isRemote() 
                ? createRemoteDriver(browser)
                : createLocalDriver(browser);
        
        configureDriver(driver);
        return driver;
    }
    
    private WebDriver createLocalDriver(String browser) {
        return localDriverSuppliers
                .getOrDefault(browser, this::createChromeDriver)
                .get();
    }
    
    private WebDriver createRemoteDriver(String browser) {
        try {
            URL gridUrl = new URL(seleniumConfig.getGrid().getUrl());
            log.info("Connecting to Selenium Grid at: {}", gridUrl);
            
            switch (browser) {
                case "firefox":
                    return new RemoteWebDriver(gridUrl, getFirefoxOptions());
                case "edge":
                    return new RemoteWebDriver(gridUrl, getEdgeOptions());
                case "chrome":
                default:
                    return new RemoteWebDriver(gridUrl, getChromeOptions());
            }
        } catch (MalformedURLException e) {
            log.error("Invalid Selenium Grid URL: {}", seleniumConfig.getGrid().getUrl());
            throw new RuntimeException("Failed to create remote driver", e);
        }
    }
    
    private WebDriver createChromeDriver() {
        log.debug("Setting up Chrome driver");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getChromeOptions());
    }
    
    private WebDriver createFirefoxDriver() {
        log.debug("Setting up Firefox driver");
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(getFirefoxOptions());
    }
    
    private WebDriver createEdgeDriver() {
        log.debug("Setting up Edge driver");
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(getEdgeOptions());
    }
    
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        
        if (seleniumConfig.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }
        
        // Performance optimizations
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-software-rasterizer");
        
        return options;
    }
    
    private FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        
        if (seleniumConfig.isHeadless()) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }
        
        return options;
    }
    
    private EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        
        if (seleniumConfig.isHeadless()) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }
        
        return options;
    }
    
    private void configureDriver(WebDriver driver) {
        log.debug("Configuring driver timeouts");
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(seleniumConfig.getImplicitWait()));
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(seleniumConfig.getPageLoadTimeout()));
        driver.manage().window().maximize();
    }
}
