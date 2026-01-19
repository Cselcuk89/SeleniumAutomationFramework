package com.selcuk.projectFactories;

import com.selcuk.constants.ProjectConstants;
import com.selcuk.driver.DriverManager;
import com.selcuk.enums.WaitStrategy;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Factory for creating explicit waits using functional programming patterns.
 * Supports various wait strategies with modern Selenium 4 Duration API.
 */
@Slf4j
public final class ExplicitWaitFactory {
    
    private ExplicitWaitFactory() {
        // Utility class - prevent instantiation
    }
    
    /**
     * Map of wait strategies to their corresponding wait functions.
     * Uses functional programming for clean strategy pattern implementation.
     */
    private static final Map<WaitStrategy, Function<By, WebElement>> WAIT_STRATEGIES = new EnumMap<>(WaitStrategy.class);
    
    static {
        WAIT_STRATEGIES.put(WaitStrategy.CLICKABLE, by -> 
            createWait().until(ExpectedConditions.elementToBeClickable(by)));
        
        WAIT_STRATEGIES.put(WaitStrategy.PRESENCE, by -> 
            createWait().until(ExpectedConditions.presenceOfElementLocated(by)));
        
        WAIT_STRATEGIES.put(WaitStrategy.VISIBLE, by -> 
            createWait().until(ExpectedConditions.visibilityOfElementLocated(by)));
        
        WAIT_STRATEGIES.put(WaitStrategy.NONE, by -> 
            DriverManager.getDriver().findElement(by));
    }
    
    /**
     * Creates a new WebDriverWait with configured timeout.
     *
     * @return WebDriverWait instance
     */
    private static WebDriverWait createWait() {
        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(ProjectConstants.getExplicitwait())
        );
    }
    
    /**
     * Creates a new WebDriverWait with custom timeout.
     *
     * @param timeoutSeconds Custom timeout in seconds
     * @return WebDriverWait instance
     */
    private static WebDriverWait createWait(int timeoutSeconds) {
        return new WebDriverWait(
                DriverManager.getDriver(),
                Duration.ofSeconds(timeoutSeconds)
        );
    }
    
    /**
     * Performs explicit wait based on the given strategy.
     *
     * @param waitStrategy Strategy to use
     * @param by           Element locator
     * @return WebElement after wait completes
     */
    public static WebElement performExplicitWaitMethod(WaitStrategy waitStrategy, By by) {
        log.trace("Waiting for element with strategy: {} - {}", waitStrategy, by);
        return WAIT_STRATEGIES
                .getOrDefault(waitStrategy, WAIT_STRATEGIES.get(WaitStrategy.PRESENCE))
                .apply(by);
    }
    
    /**
     * Waits for element to be clickable.
     *
     * @param by Element locator
     * @return WebElement when clickable
     */
    public static WebElement waitForClickable(By by) {
        return performExplicitWaitMethod(WaitStrategy.CLICKABLE, by);
    }
    
    /**
     * Waits for element to be present.
     *
     * @param by Element locator
     * @return WebElement when present
     */
    public static WebElement waitForPresence(By by) {
        return performExplicitWaitMethod(WaitStrategy.PRESENCE, by);
    }
    
    /**
     * Waits for element to be visible.
     *
     * @param by Element locator
     * @return WebElement when visible
     */
    public static WebElement waitForVisible(By by) {
        return performExplicitWaitMethod(WaitStrategy.VISIBLE, by);
    }
    
    /**
     * Waits for element to be invisible/disappear.
     *
     * @param by Element locator
     * @return true when element is invisible
     */
    public static boolean waitForInvisible(By by) {
        log.trace("Waiting for element to be invisible: {}", by);
        return createWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    
    /**
     * Waits for text to be present in element.
     *
     * @param by   Element locator
     * @param text Text to wait for
     * @return true when text is present
     */
    public static boolean waitForTextPresent(By by, String text) {
        log.trace("Waiting for text '{}' in element: {}", text, by);
        return createWait().until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }
    
    /**
     * Waits for URL to contain specific text.
     *
     * @param urlFragment URL fragment to wait for
     * @return true when URL contains text
     */
    public static boolean waitForUrlContains(String urlFragment) {
        log.trace("Waiting for URL to contain: {}", urlFragment);
        return createWait().until(ExpectedConditions.urlContains(urlFragment));
    }
    
    /**
     * Waits for title to contain specific text.
     *
     * @param titleFragment Title fragment to wait for
     * @return true when title contains text
     */
    public static boolean waitForTitleContains(String titleFragment) {
        log.trace("Waiting for title to contain: {}", titleFragment);
        return createWait().until(ExpectedConditions.titleContains(titleFragment));
    }
    
    /**
     * Waits with custom timeout for element to be clickable.
     *
     * @param by             Element locator
     * @param timeoutSeconds Custom timeout
     * @return WebElement when clickable
     */
    public static WebElement waitForClickable(By by, int timeoutSeconds) {
        return createWait(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(by));
    }
    
    /**
     * Waits for alert to be present.
     *
     * @return Alert instance
     */
    public static org.openqa.selenium.Alert waitForAlert() {
        log.trace("Waiting for alert");
        return createWait().until(ExpectedConditions.alertIsPresent());
    }
    
    /**
     * Waits for frame to be available and switches to it.
     *
     * @param frameLocator Frame locator
     * @return WebDriver focused on frame
     */
    public static org.openqa.selenium.WebDriver waitForFrameAndSwitch(By frameLocator) {
        log.trace("Waiting for frame: {}", frameLocator);
        return createWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }
}
