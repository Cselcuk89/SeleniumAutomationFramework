package com.selcuk.tests.saucedemo;

import com.selcuk.annotations.FrameworkAnnotation;
import com.selcuk.enums.CategoryType;
import com.selcuk.projectPages.saucedemo.*;
import com.selcuk.tests.BaseTest;
import com.selcuk.tests.TestData;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for SauceDemo Checkout functionality.
 * Covers the complete checkout process including E2E scenarios.
 */
@Slf4j
@Epic("SauceDemo E-Commerce Platform")
@Feature("Checkout Process")
public class CheckoutTest extends BaseTest {
    
    private InventoryPage inventoryPage;
    
    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void loginAndNavigate() {
        log.info("Logging in and navigating to inventory page");
        LoginPage loginPage = new LoginPage();
        inventoryPage = loginPage.login(getStandardUser(), getPassword());
    }
    
    @Test(description = "Verify complete checkout flow - E2E")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.SMOKE, CategoryType.REGRESSION})
    @Story("Complete Purchase")
    @Severity(SeverityLevel.BLOCKER)
    @Description("End-to-end test verifying complete checkout flow from product selection to order confirmation")
    public void testCompleteCheckoutFlow() {
        log.info("Starting complete checkout flow test");
        
        // Add product to cart
        inventoryPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        
        // Navigate to cart
        CartPage cartPage = inventoryPage.openCart();
        assertThat(cartPage.getCartItemCount()).isEqualTo(1);
        
        // Proceed to checkout
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        assertThat(checkoutPage.isOnCheckoutStepOne())
                .as("Should be on checkout step one")
                .isTrue();
        
        // Fill checkout info
        checkoutPage.completeStepOne(
                TestData.CHECKOUT_FIRST_NAME, 
                TestData.CHECKOUT_LAST_NAME, 
                TestData.CHECKOUT_POSTAL_CODE);
        assertThat(checkoutPage.isOnCheckoutStepTwo())
                .as("Should be on checkout step two")
                .isTrue();
        
        // Verify order summary
        assertThat(checkoutPage.getTotal())
                .as("Total should be displayed")
                .isNotEmpty();
        
        // Finish checkout
        checkoutPage.finishCheckout();
        
        // Verify order completion
        assertThat(checkoutPage.isOnCheckoutComplete())
                .as("Should be on checkout complete page")
                .isTrue();
        
        assertThat(checkoutPage.isOrderComplete())
                .as("Order should be complete")
                .isTrue();
        
        assertThat(checkoutPage.getCompleteHeader())
                .as("Should display thank you message")
                .contains(TestData.ORDER_COMPLETE_MESSAGE);
        
        log.info("Complete checkout flow test completed");
    }
    
    @Test(description = "Verify checkout step one validation - missing first name")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Checkout Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies first name is required on checkout step one")
    public void testCheckoutMissingFirstName() {
        log.info("Starting checkout missing first name test");
        
        // Add product and proceed to checkout
        inventoryPage.addFirstProductToCart();
        CartPage cartPage = inventoryPage.openCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        
        // Try to continue without first name
        checkoutPage
                .enterLastName(TestData.CHECKOUT_LAST_NAME)
                .enterPostalCode(TestData.CHECKOUT_POSTAL_CODE)
                .clickContinueExpectingError();
        
        // Verify error
        assertThat(checkoutPage.isErrorDisplayed())
                .as("Error should be displayed")
                .isTrue();
        
        assertThat(checkoutPage.getErrorMessage())
                .as("Error should indicate first name is required")
                .contains(TestData.ERROR_FIRST_NAME_REQUIRED);
        
        log.info("Checkout missing first name test completed");
    }
    
    @Test(description = "Verify checkout step one validation - missing last name")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Checkout Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies last name is required on checkout step one")
    public void testCheckoutMissingLastName() {
        log.info("Starting checkout missing last name test");
        
        // Add product and proceed to checkout
        inventoryPage.addFirstProductToCart();
        CartPage cartPage = inventoryPage.openCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        
        // Try to continue without last name
        checkoutPage
                .enterFirstName(TestData.CHECKOUT_FIRST_NAME)
                .enterPostalCode(TestData.CHECKOUT_POSTAL_CODE)
                .clickContinueExpectingError();
        
        // Verify error
        assertThat(checkoutPage.isErrorDisplayed())
                .as("Error should be displayed")
                .isTrue();
        
        assertThat(checkoutPage.getErrorMessage())
                .as("Error should indicate last name is required")
                .contains(TestData.ERROR_LAST_NAME_REQUIRED);
        
        log.info("Checkout missing last name test completed");
    }
    
    @Test(description = "Verify checkout step one validation - missing postal code")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Checkout Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies postal code is required on checkout step one")
    public void testCheckoutMissingPostalCode() {
        log.info("Starting checkout missing postal code test");
        
        // Add product and proceed to checkout
        inventoryPage.addFirstProductToCart();
        CartPage cartPage = inventoryPage.openCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        
        // Try to continue without postal code
        checkoutPage
                .enterFirstName(TestData.CHECKOUT_FIRST_NAME)
                .enterLastName(TestData.CHECKOUT_LAST_NAME)
                .clickContinueExpectingError();
        
        // Verify error
        assertThat(checkoutPage.isErrorDisplayed())
                .as("Error should be displayed")
                .isTrue();
        
        assertThat(checkoutPage.getErrorMessage())
                .as("Error should indicate postal code is required")
                .contains(TestData.ERROR_POSTAL_CODE_REQUIRED);
        
        log.info("Checkout missing postal code test completed");
    }
    
    @Test(description = "Verify cancel checkout returns to cart")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Checkout Navigation")
    @Severity(SeverityLevel.MINOR)
    @Description("Test that verifies canceling checkout returns to cart page")
    public void testCancelCheckout() {
        log.info("Starting cancel checkout test");
        
        // Add product and proceed to checkout
        inventoryPage.addFirstProductToCart();
        CartPage cartPage = inventoryPage.openCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        
        // Cancel checkout
        CartPage returnedCartPage = checkoutPage.cancelCheckout();
        
        // Verify on cart page
        assertThat(returnedCartPage.isOnCartPage())
                .as("Should return to cart page")
                .isTrue();
        
        log.info("Cancel checkout test completed");
    }
    
    @Test(description = "Verify checkout with multiple products")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Complete Purchase")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test that verifies checkout with multiple products works correctly")
    public void testCheckoutMultipleProducts() {
        log.info("Starting checkout multiple products test");
        
        // Add multiple products
        inventoryPage
                .addProductToCart(TestData.PRODUCT_BACKPACK)
                .addProductToCart(TestData.PRODUCT_BIKE_LIGHT)
                .addProductToCart(TestData.PRODUCT_BOLT_TSHIRT);
        
        // Navigate to cart
        CartPage cartPage = inventoryPage.openCart();
        assertThat(cartPage.getCartItemCount()).isEqualTo(3);
        
        // Calculate expected total from cart
        double cartTotal = cartPage.getCartTotal();
        
        // Proceed to checkout
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.completeStepOne(
                TestData.CHECKOUT_FIRST_NAME, 
                TestData.CHECKOUT_LAST_NAME, 
                TestData.CHECKOUT_POSTAL_CODE);
        
        // Verify total on checkout page (should include tax)
        double checkoutTotal = checkoutPage.getTotalAsDouble();
        assertThat(checkoutTotal)
                .as("Checkout total should be greater than or equal to cart subtotal")
                .isGreaterThanOrEqualTo(cartTotal);
        
        // Complete checkout
        checkoutPage.finishCheckout();
        
        // Verify completion
        assertThat(checkoutPage.isOrderComplete())
                .as("Order should be complete")
                .isTrue();
        
        log.info("Checkout multiple products test completed");
    }
    
    @Test(description = "Verify return to products after checkout completion")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Checkout Navigation")
    @Severity(SeverityLevel.MINOR)
    @Description("Test that verifies user can return to products after completing checkout")
    public void testReturnToProductsAfterCheckout() {
        log.info("Starting return to products after checkout test");
        
        // Complete a checkout
        inventoryPage.addFirstProductToCart();
        CartPage cartPage = inventoryPage.openCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage.completeStepOne(
                TestData.CHECKOUT_FIRST_NAME, 
                TestData.CHECKOUT_LAST_NAME, 
                TestData.CHECKOUT_POSTAL_CODE);
        checkoutPage.finishCheckout();
        
        // Return to products
        InventoryPage returnedInventoryPage = checkoutPage.backToProducts();
        
        // Verify on inventory page
        assertThat(returnedInventoryPage.isOnInventoryPage())
                .as("Should be on inventory page")
                .isTrue();
        
        // Verify cart is empty
        assertThat(returnedInventoryPage.isCartBadgeVisible())
                .as("Cart should be empty after checkout")
                .isFalse();
        
        log.info("Return to products after checkout test completed");
    }
}
