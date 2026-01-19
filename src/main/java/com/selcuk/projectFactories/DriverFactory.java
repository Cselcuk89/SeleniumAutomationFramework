package com.selcuk.projectFactories;

import com.selcuk.enums.ConfigProperties;
import com.selcuk.utilities.PropertyUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Legacy WebDriver factory for backward compatibility.
 * For new projects, use the Spring-managed WebDriverFactory instead.
 */
@Slf4j
public final class DriverFactory {
    
    private DriverFactory() {
        // Utility class - prevent instantiation
    }
    
    // Default to headless mode but can be overridden
    private static boolean headless = true;
    
    private static final Map<String, Supplier<WebDriver>> LOCAL_DRIVERS = new HashMap<>();
    
    static {
        LOCAL_DRIVERS.put("chrome", DriverFactory::createChromeDriver);
        LOCAL_DRIVERS.put("firefox", DriverFactory::createFirefoxDriver);
    }
    
    /**
     * Sets whether to run in headless mode.
     *
     * @param isHeadless true for headless mode
     */
    public static void setHeadless(boolean isHeadless) {
        headless = isHeadless;
    }
    
    /**
     * Creates a WebDriver instance based on browser and run mode.
     *
     * @param browser Browser name
     * @param version Browser version (for remote)
     * @return WebDriver instance
     * @throws MalformedURLException if grid URL is invalid
     */
    public static WebDriver getDriver(String browser, String version) throws MalformedURLException {
        String runmode = PropertyUtils.get(ConfigProperties.RUNMODE);
        log.info("Creating {} driver in {} mode (headless: {})", browser, runmode, headless);
        
        if (runmode.equalsIgnoreCase("remote")) {
            return createRemoteDriver(browser, version);
        }
        
        return LOCAL_DRIVERS
                .getOrDefault(browser.toLowerCase(), DriverFactory::createChromeDriver)
                .get();
    }
    
    private static WebDriver createRemoteDriver(String browser, String version) throws MalformedURLException {
        URL gridUrl = new URL(PropertyUtils.get(ConfigProperties.SELENIUMGRIDURL));
        log.info("Connecting to Selenium Grid at: {}", gridUrl);
        
        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = getFirefoxOptions();
                firefoxOptions.setBrowserVersion(version);
                return new RemoteWebDriver(gridUrl, firefoxOptions);
            case "chrome":
            default:
                ChromeOptions chromeOptions = getChromeOptions();
                chromeOptions.setBrowserVersion(version);
                return new RemoteWebDriver(gridUrl, chromeOptions);
        }
    }
    
    private static WebDriver createChromeDriver() {
        log.debug("Setting up Chrome driver");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getChromeOptions());
    }
    
    private static WebDriver createFirefoxDriver() {
        log.debug("Setting up Firefox driver");
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(getFirefoxOptions());
    }
    
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }
        
        return options;
    }
    
    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        
        if (headless) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }
        
        return options;
    }
}
