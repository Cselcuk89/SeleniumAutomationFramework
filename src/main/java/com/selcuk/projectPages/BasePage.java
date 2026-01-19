package com.selcuk.projectPages;

import com.selcuk.driver.DriverManager;
import com.selcuk.enums.WaitStrategy;
import com.selcuk.projectFactories.ExplicitWaitFactory;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.selcuk.enums.LogType.INFO;
import static com.selcuk.enums.LogType.PASS;
import static com.selcuk.projectReports.FrameworkLogger.log;

/**
 * Enhanced base page class with fluent API and functional programming patterns.
 * Provides reusable methods for common web interactions with proper logging.
 *
 * @param <T> The concrete page type for fluent method chaining
 */
@Slf4j
@SuppressWarnings("unchecked")
public abstract class BasePage<T extends BasePage<T>> {

    /**
     * Gets the current page instance for fluent chaining.
     *
     * @return Current page instance
     */
    protected T self() {
        return (T) this;
    }

    /**
     * Gets the WebDriver instance.
     *
     * @return WebDriver instance
     */
    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    // ==================== Click Operations ====================

    /**
     * Clicks on an element with wait strategy.
     *
     * @param by           Element locator
     * @param waitStrategy Wait strategy to use
     * @param elementName  Name for logging
     * @return Current page instance
     */
    @Step("Click on {elementName}")
    protected T click(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(waitStrategy, by);
        element.click();
        log(PASS, elementName + " is clicked");
        log.info("Clicked on element: {}", elementName);
        return self();
    }

    /**
     * Clicks on an element using JavaScript executor.
     *
     * @param by          Element locator
     * @param elementName Name for logging
     * @return Current page instance
     */
    @Step("JS Click on {elementName}")
    protected T jsClick(By by, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.PRESENCE, by);
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", element);
        log(PASS, elementName + " is clicked using JavaScript");
        log.info("JS clicked on element: {}", elementName);
        return self();
    }

    /**
     * Double clicks on an element.
     *
     * @param by           Element locator
     * @param waitStrategy Wait strategy
     * @param elementName  Name for logging
     * @return Current page instance
     */
    @Step("Double click on {elementName}")
    protected T doubleClick(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(waitStrategy, by);
        new Actions(getDriver()).doubleClick(element).perform();
        log(PASS, elementName + " is double clicked");
        log.info("Double clicked on element: {}", elementName);
        return self();
    }

    /**
     * Right clicks on an element.
     *
     * @param by           Element locator
     * @param waitStrategy Wait strategy
     * @param elementName  Name for logging
     * @return Current page instance
     */
    @Step("Right click on {elementName}")
    protected T rightClick(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(waitStrategy, by);
        new Actions(getDriver()).contextClick(element).perform();
        log(PASS, elementName + " is right clicked");
        log.info("Right clicked on element: {}", elementName);
        return self();
    }

    // ==================== Input Operations ====================

    /**
     * Enters text into an element.
     *
     * @param by           Element locator
     * @param value        Text to enter
     * @param waitStrategy Wait strategy
     * @param elementName  Name for logging
     * @return Current page instance
     */
    @Step("Enter '{value}' in {elementName}")
    protected T sendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(waitStrategy, by);
        element.clear();
        element.sendKeys(value);
        log(PASS, "'" + value + "' is entered in " + elementName);
        log.info("Entered '{}' in element: {}", value, elementName);
        return self();
    }

    /**
     * Clears an input field.
     *
     * @param by           Element locator
     * @param waitStrategy Wait strategy
     * @param elementName  Name for logging
     * @return Current page instance
     */
    @Step("Clear {elementName}")
    protected T clear(By by, WaitStrategy waitStrategy, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(waitStrategy, by);
        element.clear();
        log(INFO, elementName + " is cleared");
        log.info("Cleared element: {}", elementName);
        return self();
    }

    /**
     * Enters text using JavaScript.
     *
     * @param by          Element locator
     * @param value       Text to enter
     * @param elementName Name for logging
     * @return Current page instance
     */
    @Step("JS Enter '{value}' in {elementName}")
    protected T jsSetValue(By by, String value, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.PRESENCE, by);
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].value=arguments[1];", element, value);
        log(PASS, "'" + value + "' is set in " + elementName + " using JavaScript");
        log.info("JS set value '{}' in element: {}", value, elementName);
        return self();
    }

    // ==================== Select Operations ====================

    /**
     * Selects option by visible text.
     *
     * @param by          Element locator
     * @param text        Text to select
     * @param elementName Name for logging
     * @return Current page instance
     */
    @Step("Select '{text}' from {elementName}")
    protected T selectByText(By by, String text, String elementName) {
        Select select = new Select(
                ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.PRESENCE, by));
        select.selectByVisibleText(text);
        log(PASS, "'" + text + "' is selected from " + elementName);
        log.info("Selected '{}' from dropdown: {}", text, elementName);
        return self();
    }

    /**
     * Selects option by value.
     *
     * @param by          Element locator
     * @param value       Value to select
     * @param elementName Name for logging
     * @return Current page instance
     */
    @Step("Select value '{value}' from {elementName}")
    protected T selectByValue(By by, String value, String elementName) {
        Select select = new Select(
                ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.PRESENCE, by));
        select.selectByValue(value);
        log(PASS, "Value '" + value + "' is selected from " + elementName);
        log.info("Selected value '{}' from dropdown: {}", value, elementName);
        return self();
    }

    /**
     * Selects option by index.
     *
     * @param by          Element locator
     * @param index       Index to select
     * @param elementName Name for logging
     * @return Current page instance
     */
    @Step("Select index {index} from {elementName}")
    protected T selectByIndex(By by, int index, String elementName) {
        Select select = new Select(
                ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.PRESENCE, by));
        select.selectByIndex(index);
        log(PASS, "Index " + index + " is selected from " + elementName);
        log.info("Selected index {} from dropdown: {}", index, elementName);
        return self();
    }

    // ==================== Hover and Scroll Operations ====================

    /**
     * Hovers over an element.
     *
     * @param by          Element locator
     * @param elementName Name for logging
     * @return Current page instance
     */
    @Step("Hover over {elementName}")
    protected T hover(By by, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.VISIBLE, by);
        new Actions(getDriver()).moveToElement(element).perform();
        log(INFO, "Hovered over " + elementName);
        log.info("Hovered over element: {}", elementName);
        return self();
    }

    /**
     * Scrolls element into view.
     *
     * @param by          Element locator
     * @param elementName Name for logging
     * @return Current page instance
     */
    @Step("Scroll to {elementName}")
    protected T scrollToElement(By by, String elementName) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.PRESENCE, by);
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        log(INFO, "Scrolled to " + elementName);
        log.info("Scrolled to element: {}", elementName);
        return self();
    }

    /**
     * Scrolls to top of page.
     *
     * @return Current page instance
     */
    @Step("Scroll to top of page")
    protected T scrollToTop() {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, 0);");
        log.info("Scrolled to top of page");
        return self();
    }

    /**
     * Scrolls to bottom of page.
     *
     * @return Current page instance
     */
    @Step("Scroll to bottom of page")
    protected T scrollToBottom() {
        ((JavascriptExecutor) getDriver()).executeScript(
                "window.scrollTo(0, document.body.scrollHeight);");
        log.info("Scrolled to bottom of page");
        return self();
    }

    // ==================== Get Operations ====================

    /**
     * Gets text from element.
     *
     * @param by           Element locator
     * @param waitStrategy Wait strategy
     * @return Element text
     */
    protected String getText(By by, WaitStrategy waitStrategy) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(waitStrategy, by);
        String text = element.getText();
        log.debug("Got text '{}' from element", text);
        return text;
    }

    /**
     * Gets attribute value from element.
     *
     * @param by            Element locator
     * @param attributeName Attribute name
     * @param waitStrategy  Wait strategy
     * @return Attribute value
     */
    protected String getAttribute(By by, String attributeName, WaitStrategy waitStrategy) {
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(waitStrategy, by);
        String value = element.getAttribute(attributeName);
        log.debug("Got attribute '{}' = '{}' from element", attributeName, value);
        return value;
    }

    /**
     * Gets page title.
     *
     * @return Page title
     */
    protected String getPageTitle() {
        String title = getDriver().getTitle();
        log.debug("Page title: {}", title);
        return title;
    }

    /**
     * Gets current URL.
     *
     * @return Current URL
     */
    protected String getCurrentUrl() {
        String url = getDriver().getCurrentUrl();
        log.debug("Current URL: {}", url);
        return url;
    }

    // ==================== Element State Operations ====================

    /**
     * Checks if element is displayed.
     *
     * @param by Element locator
     * @return true if displayed
     */
    protected boolean isDisplayed(By by) {
        try {
            return getDriver().findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if element is enabled.
     *
     * @param by Element locator
     * @return true if enabled
     */
    protected boolean isEnabled(By by) {
        try {
            return getDriver().findElement(by).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if element is selected.
     *
     * @param by Element locator
     * @return true if selected
     */
    protected boolean isSelected(By by) {
        try {
            return getDriver().findElement(by).isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if element exists.
     *
     * @param by Element locator
     * @return true if exists
     */
    protected boolean elementExists(By by) {
        return !getDriver().findElements(by).isEmpty();
    }

    // ==================== Functional Operations ====================

    /**
     * Gets list of elements matching locator.
     *
     * @param by Element locator
     * @return List of web elements
     */
    protected List<WebElement> getElements(By by) {
        return getDriver().findElements(by);
    }

    /**
     * Gets list of texts from matching elements.
     *
     * @param by Element locator
     * @return List of text strings
     */
    protected List<String> getTexts(By by) {
        return getElements(by).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Finds first element matching predicate.
     *
     * @param by        Element locator
     * @param predicate Filter predicate
     * @return Optional containing matching element
     */
    protected Optional<WebElement> findFirst(By by, Predicate<WebElement> predicate) {
        return getElements(by).stream()
                .filter(predicate)
                .findFirst();
    }

    /**
     * Applies function to all matching elements.
     *
     * @param by     Element locator
     * @param mapper Mapping function
     * @param <R>    Result type
     * @return List of mapped results
     */
    protected <R> List<R> mapElements(By by, Function<WebElement, R> mapper) {
        return getElements(by).stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    /**
     * Executes action on all matching elements.
     *
     * @param by     Element locator
     * @param action Action to execute
     * @return Current page instance
     */
    protected T forEachElement(By by, Consumer<WebElement> action) {
        getElements(by).forEach(action);
        return self();
    }

    /**
     * Filters elements and executes action on matches.
     *
     * @param by        Element locator
     * @param predicate Filter predicate
     * @param action    Action to execute
     * @return Current page instance
     */
    protected T filterAndAct(By by, Predicate<WebElement> predicate, Consumer<WebElement> action) {
        getElements(by).stream()
                .filter(predicate)
                .forEach(action);
        return self();
    }

    /**
     * Clicks on element with matching text.
     *
     * @param by          Element locator
     * @param text        Text to match
     * @param elementName Name for logging
     * @return Current page instance
     */
    @Step("Click on element with text '{text}'")
    protected T clickByText(By by, String text, String elementName) {
        findFirst(by, element -> element.getText().equals(text))
                .ifPresent(element -> {
                    element.click();
                    log(PASS, "Clicked on " + elementName + " with text: " + text);
                    log.info("Clicked on element with text '{}': {}", text, elementName);
                });
        return self();
    }

    /**
     * Gets count of matching elements.
     *
     * @param by Element locator
     * @return Element count
     */
    protected int getElementCount(By by) {
        int count = getElements(by).size();
        log.debug("Found {} elements matching locator", count);
        return count;
    }

    // ==================== Wait Operations ====================

    /**
     * Waits for element to be displayed.
     *
     * @param by Element locator
     * @return Current page instance
     */
    protected T waitForVisible(By by) {
        ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.VISIBLE, by);
        return self();
    }

    /**
     * Waits for element to be clickable.
     *
     * @param by Element locator
     * @return Current page instance
     */
    protected T waitForClickable(By by) {
        ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.CLICKABLE, by);
        return self();
    }

    /**
     * Waits for element to be present.
     *
     * @param by Element locator
     * @return Current page instance
     */
    protected T waitForPresence(By by) {
        ExplicitWaitFactory.performExplicitWaitMethod(WaitStrategy.PRESENCE, by);
        return self();
    }
}
