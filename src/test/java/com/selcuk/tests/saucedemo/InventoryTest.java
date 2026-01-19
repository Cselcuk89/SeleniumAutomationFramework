package com.selcuk.tests.saucedemo;

import com.selcuk.annotations.FrameworkAnnotation;
import com.selcuk.enums.CategoryType;
import com.selcuk.projectPages.saucedemo.InventoryPage;
import com.selcuk.projectPages.saucedemo.LoginPage;
import com.selcuk.projectPages.saucedemo.ProductDetailPage;
import com.selcuk.tests.BaseTest;
import com.selcuk.tests.TestData;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for SauceDemo Inventory/Products functionality.
 * Covers product display, sorting, and navigation scenarios.
 */
@Slf4j
@Epic("SauceDemo E-Commerce Platform")
@Feature("Product Inventory")
public class InventoryTest extends BaseTest {
    
    private InventoryPage inventoryPage;
    
    @BeforeMethod(alwaysRun = true, dependsOnMethods = "setUp")
    public void loginAndNavigate() {
        log.info("Logging in and navigating to inventory page");
        LoginPage loginPage = new LoginPage();
        inventoryPage = loginPage.login(getStandardUser(), getPassword());
    }
    
    @Test(description = "Verify products are displayed on inventory page")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.SMOKE, CategoryType.REGRESSION})
    @Story("Product Display")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test that verifies products are displayed after successful login")
    public void testProductsDisplayed() {
        log.info("Starting products displayed test");
        
        // Verify on inventory page
        assertThat(inventoryPage.isOnInventoryPage())
                .as("Should be on inventory page")
                .isTrue();
        
        // Verify products are displayed
        int productCount = inventoryPage.getProductCount();
        assertThat(productCount)
                .as("Should display products")
                .isGreaterThan(0);
        
        log.info("Found {} products on inventory page", productCount);
        
        // Verify product names
        List<String> productNames = inventoryPage.getProductNames();
        assertThat(productNames)
                .as("Product names should not be empty")
                .isNotEmpty();
        
        log.info("Products displayed test completed");
    }
    
    @Test(description = "Verify sorting products by price low to high")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Product Sorting")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies products can be sorted by price from low to high")
    public void testSortByPriceLowToHigh() {
        log.info("Starting sort by price low to high test");
        
        // Sort by price low to high
        inventoryPage.sortByPriceLowToHigh();
        
        // Verify sorting
        assertThat(inventoryPage.isProductsSortedByPriceAsc())
                .as("Products should be sorted by price ascending")
                .isTrue();
        
        log.info("Sort by price low to high test completed");
    }
    
    @Test(description = "Verify sorting products by price high to low")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Product Sorting")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies products can be sorted by price from high to low")
    public void testSortByPriceHighToLow() {
        log.info("Starting sort by price high to low test");
        
        // Sort by price high to low
        inventoryPage.sortByPriceHighToLow();
        
        // Verify sorting
        assertThat(inventoryPage.isProductsSortedByPriceDesc())
                .as("Products should be sorted by price descending")
                .isTrue();
        
        log.info("Sort by price high to low test completed");
    }
    
    @Test(description = "Verify sorting products by name A to Z")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Product Sorting")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies products can be sorted alphabetically A to Z")
    public void testSortByNameAtoZ() {
        log.info("Starting sort by name A to Z test");
        
        // First sort differently, then sort A to Z
        inventoryPage.sortByNameZtoA();
        inventoryPage.sortByNameAtoZ();
        
        // Get product names
        List<String> productNames = inventoryPage.getProductNames();
        
        // Verify alphabetical order
        for (int i = 0; i < productNames.size() - 1; i++) {
            assertThat(productNames.get(i).compareToIgnoreCase(productNames.get(i + 1)))
                    .as("Products should be in alphabetical order")
                    .isLessThanOrEqualTo(0);
        }
        
        log.info("Sort by name A to Z test completed");
    }
    
    @Test(description = "Verify adding product to cart from inventory page")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.SMOKE, CategoryType.REGRESSION})
    @Story("Add to Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test that verifies user can add a product to cart from inventory page")
    public void testAddProductToCart() {
        log.info("Starting add product to cart test");
        
        // Get initial cart count
        int initialCount = inventoryPage.getCartItemCount();
        
        // Add first product
        inventoryPage.addFirstProductToCart();
        
        // Verify cart count increased
        assertThat(inventoryPage.getCartItemCount())
                .as("Cart count should increase by 1")
                .isEqualTo(initialCount + 1);
        
        assertThat(inventoryPage.isCartBadgeVisible())
                .as("Cart badge should be visible")
                .isTrue();
        
        log.info("Add product to cart test completed");
    }
    
    @Test(description = "Verify adding specific product to cart by name")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Add to Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies user can add a specific product to cart using product name")
    public void testAddSpecificProductToCart() {
        log.info("Starting add specific product to cart test");
        
        // Add specific product
        inventoryPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        
        // Verify cart count
        assertThat(inventoryPage.getCartItemCount())
                .as("Cart should have 1 item")
                .isEqualTo(1);
        
        log.info("Add specific product to cart test completed");
    }
    
    @Test(description = "Verify removing product from cart on inventory page")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Remove from Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies user can remove a product from cart on inventory page")
    public void testRemoveProductFromCart() {
        log.info("Starting remove product from cart test");
        
        // Add product first
        inventoryPage.addProductToCart(TestData.PRODUCT_BACKPACK);
        assertThat(inventoryPage.getCartItemCount()).isEqualTo(1);
        
        // Remove product
        inventoryPage.removeProductFromCart(TestData.PRODUCT_BACKPACK);
        
        // Verify cart is empty
        assertThat(inventoryPage.isCartBadgeVisible())
                .as("Cart badge should not be visible")
                .isFalse();
        
        log.info("Remove product from cart test completed");
    }
    
    @Test(description = "Verify navigating to product detail page")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Product Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies user can navigate to product detail page")
    public void testNavigateToProductDetail() {
        log.info("Starting navigate to product detail test");
        
        // Navigate to product detail
        ProductDetailPage detailPage = inventoryPage.openProductDetails(TestData.PRODUCT_BACKPACK);
        
        // Verify on detail page
        assertThat(detailPage.isOnProductDetailPage())
                .as("Should be on product detail page")
                .isTrue();
        
        assertThat(detailPage.getProductName())
                .as("Product name should match")
                .isEqualTo(TestData.PRODUCT_BACKPACK);
        
        log.info("Navigate to product detail test completed");
    }
    
    @Test(description = "Verify adding multiple products to cart")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("Add to Cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test that verifies user can add multiple products to cart")
    public void testAddMultipleProductsToCart() {
        log.info("Starting add multiple products to cart test");
        
        // Add multiple products
        inventoryPage
                .addProductToCart(TestData.PRODUCT_BACKPACK)
                .addProductToCart(TestData.PRODUCT_BIKE_LIGHT)
                .addProductToCart(TestData.PRODUCT_BOLT_TSHIRT);
        
        // Verify cart count
        assertThat(inventoryPage.getCartItemCount())
                .as("Cart should have 3 items")
                .isEqualTo(3);
        
        log.info("Add multiple products to cart test completed");
    }
    
    @Test(description = "Verify user can logout")
    @FrameworkAnnotation(author = {"Selcuk"}, category = {CategoryType.REGRESSION})
    @Story("User Logout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test that verifies user can logout from the application")
    public void testLogout() {
        log.info("Starting logout test");
        
        // Logout
        LoginPage loginPage = inventoryPage.logout();
        
        // Verify on login page
        assertThat(loginPage.isOnLoginPage())
                .as("Should be on login page after logout")
                .isTrue();
        
        log.info("Logout test completed");
    }
}
