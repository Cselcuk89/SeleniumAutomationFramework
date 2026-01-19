package com.selcuk.tests.saucedemo;

import com.selcuk.annotations.FrameworkAnnotation;
import com.selcuk.enums.CategoryType;
import com.selcuk.projectPages.saucedemo.CartPage;
import com.selcuk.projectPages.saucedemo.InventoryPage;
import com.selcuk.projectPages.saucedemo.LoginPage;
import com.selcuk.tests.BaseTest;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for SauceDemo Cart functionality.
 * Covers cart operations and navigation scenarios.
 */
@Slf4j
@Epic("SauceDemo E-Commerce Platform")
@Feature("Shopping Cart")
public class CartTest extends BaseTest {
    
    private InventoryPage inventoryPage;
    
    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void loginAndNavigate() {
        log.info("Logging in and navigating to inventory page");
        LoginPage loginPage = new LoginPage();
        inventoryPage = loginPage.login(getStandardUser(), getPassword());
    }
    
    @Test(description = "Verify cart page displays correctly")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.SMOKE, CategoryType.REGRESSION})
    @Story("Cart Display")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test that verifies cart page displays correctly with added items")
    public void testCartPageDisplay() {
        log.info("Starting cart page display test");
        
        String productName = "Sauce Labs Backpack";
        
        // Add product and navigate to cart
        inventoryPage.addProductToCart(productName);
        CartPage cartPage = inventoryPage.openCart();
        
        // Verify on cart page
        assertThat(cartPage.isOnCartPage())
                .as("Should be on cart page")
                .isTrue();
        
        assertThat(cartPage.getCartTitle())
                .as("Cart title should be displayed")
                .isEqualTo("Your Cart");
        
        log.info("Cart page display test completed");
    }
    
    @Test(description = "Verify cart contains added products")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Cart Contents")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test that verifies cart contains the products that were added")
    public void testCartContainsAddedProducts() {
        log.info("Starting cart contains added products test");
        
        String product1 = "Sauce Labs Backpack";
        String product2 = "Sauce Labs Bike Light";
        
        // Add products
        inventoryPage
                .addProductToCart(product1)
                .addProductToCart(product2);
        
        // Navigate to cart
        CartPage cartPage = inventoryPage.openCart();
        
        // Verify products in cart
        List<String> cartProducts = cartPage.getCartProductNames();
        assertThat(cartProducts)
                .as("Cart should contain added products")
                .containsExactlyInAnyOrder(product1, product2);
        
        assertThat(cartPage.getCartItemCount())
                .as("Cart should have 2 items")
                .isEqualTo(2);
        
        log.info("Cart contains added products test completed");
    }
    
    @Test(description = "Verify removing item from cart")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Remove from Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies user can remove items from cart")
    public void testRemoveItemFromCart() {
        log.info("Starting remove item from cart test");
        
        String product1 = "Sauce Labs Backpack";
        String product2 = "Sauce Labs Bike Light";
        
        // Add products and navigate to cart
        inventoryPage
                .addProductToCart(product1)
                .addProductToCart(product2);
        CartPage cartPage = inventoryPage.openCart();
        
        // Remove one product
        cartPage.removeItem(product1);
        
        // Verify cart
        assertThat(cartPage.getCartItemCount())
                .as("Cart should have 1 item")
                .isEqualTo(1);
        
        assertThat(cartPage.isProductInCart(product1))
                .as("Removed product should not be in cart")
                .isFalse();
        
        assertThat(cartPage.isProductInCart(product2))
                .as("Other product should still be in cart")
                .isTrue();
        
        log.info("Remove item from cart test completed");
    }
    
    @Test(description = "Verify removing all items from cart")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Remove from Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies user can remove all items from cart")
    public void testRemoveAllItemsFromCart() {
        log.info("Starting remove all items from cart test");
        
        // Add products and navigate to cart
        inventoryPage
                .addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Bike Light");
        CartPage cartPage = inventoryPage.openCart();
        
        // Remove all items
        cartPage.removeAllItems();
        
        // Verify cart is empty
        assertThat(cartPage.isCartEmpty())
                .as("Cart should be empty")
                .isTrue();
        
        log.info("Remove all items from cart test completed");
    }
    
    @Test(description = "Verify continue shopping button")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Cart Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies continue shopping button returns to inventory page")
    public void testContinueShopping() {
        log.info("Starting continue shopping test");
        
        // Navigate to cart
        CartPage cartPage = inventoryPage.openCart();
        
        // Click continue shopping
        InventoryPage returnedPage = cartPage.continueShopping();
        
        // Verify on inventory page
        assertThat(returnedPage.isOnInventoryPage())
                .as("Should return to inventory page")
                .isTrue();
        
        log.info("Continue shopping test completed");
    }
    
    @Test(description = "Verify cart total calculation")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Cart Calculations")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies cart total is calculated correctly")
    public void testCartTotalCalculation() {
        log.info("Starting cart total calculation test");
        
        // Add products and navigate to cart
        inventoryPage
                .addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Bike Light");
        CartPage cartPage = inventoryPage.openCart();
        
        // Get prices and calculate expected total
        List<String> prices = cartPage.getCartProductPrices();
        double expectedTotal = prices.stream()
                .map(p -> p.replace("$", ""))
                .mapToDouble(Double::parseDouble)
                .sum();
        
        // Verify total
        assertThat(cartPage.getCartTotal())
                .as("Cart total should be calculated correctly")
                .isEqualTo(expectedTotal);
        
        log.info("Cart total: ${}", expectedTotal);
        log.info("Cart total calculation test completed");
    }
    
    @Test(description = "Verify empty cart display")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Cart Display")
    @Severity(SeverityLevel.MINOR)
    @Description("Test that verifies empty cart is displayed correctly")
    public void testEmptyCartDisplay() {
        log.info("Starting empty cart display test");
        
        // Navigate to cart without adding items
        CartPage cartPage = inventoryPage.openCart();
        
        // Verify empty cart
        assertThat(cartPage.isCartEmpty())
                .as("Cart should be empty")
                .isTrue();
        
        assertThat(cartPage.getCartItemCount())
                .as("Cart item count should be 0")
                .isEqualTo(0);
        
        log.info("Empty cart display test completed");
    }
}
