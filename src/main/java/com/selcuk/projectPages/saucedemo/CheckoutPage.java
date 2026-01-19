package com.selcuk.projectPages.saucedemo;

import com.selcuk.enums.WaitStrategy;
import com.selcuk.projectPages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

/**
 * Page Object for SauceDemo Checkout Pages.
 * Handles checkout step one, step two, and completion pages.
 */
@Slf4j
public class CheckoutPage extends BasePage<CheckoutPage> {
    
    // ==================== Locators - Step One ====================
    
    private static final By FIRST_NAME_INPUT = By.id("first-name");
    private static final By LAST_NAME_INPUT = By.id("last-name");
    private static final By POSTAL_CODE_INPUT = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By CANCEL_BUTTON = By.id("cancel");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    
    // ==================== Locators - Step Two ====================
    
    private static final By FINISH_BUTTON = By.id("finish");
    private static final By SUMMARY_INFO = By.className("summary_info");
    private static final By CART_ITEM = By.className("cart_item");
    private static final By ITEM_TOTAL = By.className("summary_subtotal_label");
    private static final By TAX = By.className("summary_tax_label");
    private static final By TOTAL = By.className("summary_total_label");
    private static final By PAYMENT_INFO = By.cssSelector(".summary_value_label");
    
    // ==================== Locators - Complete ====================
    
    private static final By COMPLETE_HEADER = By.className("complete-header");
    private static final By COMPLETE_TEXT = By.className("complete-text");
    private static final By PONY_EXPRESS_IMAGE = By.className("pony_express");
    private static final By BACK_HOME_BUTTON = By.id("back-to-products");
    
    // ==================== Step One Actions ====================
    
    /**
     * Enters first name.
     *
     * @param firstName First name to enter
     * @return Current page instance
     */
    @Step("Enter first name: {firstName}")
    public CheckoutPage enterFirstName(String firstName) {
        log.info("Entering first name: {}", firstName);
        return sendKeys(FIRST_NAME_INPUT, firstName, WaitStrategy.VISIBLE, "First Name");
    }
    
    /**
     * Enters last name.
     *
     * @param lastName Last name to enter
     * @return Current page instance
     */
    @Step("Enter last name: {lastName}")
    public CheckoutPage enterLastName(String lastName) {
        log.info("Entering last name: {}", lastName);
        return sendKeys(LAST_NAME_INPUT, lastName, WaitStrategy.VISIBLE, "Last Name");
    }
    
    /**
     * Enters postal code.
     *
     * @param postalCode Postal code to enter
     * @return Current page instance
     */
    @Step("Enter postal code: {postalCode}")
    public CheckoutPage enterPostalCode(String postalCode) {
        log.info("Entering postal code: {}", postalCode);
        return sendKeys(POSTAL_CODE_INPUT, postalCode, WaitStrategy.VISIBLE, "Postal Code");
    }
    
    /**
     * Fills checkout information form.
     *
     * @param firstName  First name
     * @param lastName   Last name
     * @param postalCode Postal code
     * @return Current page instance
     */
    @Step("Fill checkout information")
    public CheckoutPage fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        log.info("Filling checkout info: {} {} {}", firstName, lastName, postalCode);
        return enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostalCode(postalCode);
    }
    
    /**
     * Clicks continue button to proceed to step two.
     *
     * @return Current page instance (on step two)
     */
    @Step("Click continue button")
    public CheckoutPage clickContinue() {
        log.info("Clicking continue button");
        return click(CONTINUE_BUTTON, WaitStrategy.CLICKABLE, "Continue");
    }
    
    /**
     * Clicks continue expecting an error.
     *
     * @return Current page instance
     */
    @Step("Click continue (expecting error)")
    public CheckoutPage clickContinueExpectingError() {
        log.info("Clicking continue button (expecting error)");
        return click(CONTINUE_BUTTON, WaitStrategy.CLICKABLE, "Continue");
    }
    
    /**
     * Cancels checkout and returns to cart.
     *
     * @return CartPage instance
     */
    @Step("Cancel checkout")
    public CartPage cancelCheckout() {
        log.info("Cancelling checkout");
        click(CANCEL_BUTTON, WaitStrategy.CLICKABLE, "Cancel");
        return new CartPage();
    }
    
    /**
     * Completes step one with provided information.
     *
     * @param firstName  First name
     * @param lastName   Last name
     * @param postalCode Postal code
     * @return Current page instance (on step two)
     */
    @Step("Complete checkout step one")
    public CheckoutPage completeStepOne(String firstName, String lastName, String postalCode) {
        return fillCheckoutInfo(firstName, lastName, postalCode)
                .clickContinue();
    }
    
    // ==================== Step Two Actions ====================
    
    /**
     * Finishes the checkout process.
     *
     * @return Current page instance (on complete page)
     */
    @Step("Finish checkout")
    public CheckoutPage finishCheckout() {
        log.info("Finishing checkout");
        return click(FINISH_BUTTON, WaitStrategy.CLICKABLE, "Finish");
    }
    
    /**
     * Gets the item subtotal.
     *
     * @return Item total text
     */
    @Step("Get item subtotal")
    public String getItemTotal() {
        String total = getText(ITEM_TOTAL, WaitStrategy.VISIBLE);
        log.info("Item total: {}", total);
        return total;
    }
    
    /**
     * Gets the tax amount.
     *
     * @return Tax text
     */
    @Step("Get tax amount")
    public String getTax() {
        String tax = getText(TAX, WaitStrategy.VISIBLE);
        log.info("Tax: {}", tax);
        return tax;
    }
    
    /**
     * Gets the total amount.
     *
     * @return Total text
     */
    @Step("Get total amount")
    public String getTotal() {
        String total = getText(TOTAL, WaitStrategy.VISIBLE);
        log.info("Total: {}", total);
        return total;
    }
    
    /**
     * Gets total as a double value.
     *
     * @return Total amount as double
     */
    @Step("Get total as number")
    public double getTotalAsDouble() {
        String totalText = getTotal();
        return Double.parseDouble(
                totalText.replace("Total: $", ""));
    }
    
    // ==================== Complete Page Actions ====================
    
    /**
     * Gets the completion header text.
     *
     * @return Header text
     */
    @Step("Get completion header")
    public String getCompleteHeader() {
        String header = getText(COMPLETE_HEADER, WaitStrategy.VISIBLE);
        log.info("Complete header: {}", header);
        return header;
    }
    
    /**
     * Gets the completion message text.
     *
     * @return Completion message
     */
    @Step("Get completion message")
    public String getCompleteText() {
        String text = getText(COMPLETE_TEXT, WaitStrategy.VISIBLE);
        log.info("Complete text: {}", text);
        return text;
    }
    
    /**
     * Returns to home/inventory page.
     *
     * @return InventoryPage instance
     */
    @Step("Return to home")
    public InventoryPage backToProducts() {
        log.info("Returning to products");
        click(BACK_HOME_BUTTON, WaitStrategy.CLICKABLE, "Back Home");
        return new InventoryPage();
    }
    
    // ==================== Verifications ====================
    
    /**
     * Checks if on checkout step one page.
     *
     * @return true if on step one
     */
    @Step("Verify on checkout step one")
    public boolean isOnCheckoutStepOne() {
        return isDisplayed(FIRST_NAME_INPUT) &&
               getCurrentUrl().contains("checkout-step-one");
    }
    
    /**
     * Checks if on checkout step two page.
     *
     * @return true if on step two
     */
    @Step("Verify on checkout step two")
    public boolean isOnCheckoutStepTwo() {
        return isDisplayed(SUMMARY_INFO) &&
               getCurrentUrl().contains("checkout-step-two");
    }
    
    /**
     * Checks if on checkout complete page.
     *
     * @return true if on complete page
     */
    @Step("Verify on checkout complete")
    public boolean isOnCheckoutComplete() {
        return isDisplayed(COMPLETE_HEADER) &&
               getCurrentUrl().contains("checkout-complete");
    }
    
    /**
     * Checks if order is complete.
     *
     * @return true if order is complete
     */
    @Step("Verify order is complete")
    public boolean isOrderComplete() {
        return getCompleteHeader().contains("Thank you");
    }
    
    /**
     * Gets error message text.
     *
     * @return Error message
     */
    @Step("Get error message")
    public String getErrorMessage() {
        String error = getText(ERROR_MESSAGE, WaitStrategy.VISIBLE);
        log.info("Error message: {}", error);
        return error;
    }
    
    /**
     * Checks if error is displayed.
     *
     * @return true if error is displayed
     */
    @Step("Check if error is displayed")
    public boolean isErrorDisplayed() {
        return isDisplayed(ERROR_MESSAGE);
    }
}
