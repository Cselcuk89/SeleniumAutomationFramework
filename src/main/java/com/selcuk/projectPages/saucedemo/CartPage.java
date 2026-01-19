package com.selcuk.projectPages.saucedemo;

import com.selcuk.enums.WaitStrategy;
import com.selcuk.projectPages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.List;

/**
 * Page Object for SauceDemo Cart Page.
 * URL: https://www.saucedemo.com/cart.html
 */
@Slf4j
public class CartPage extends BasePage<CartPage> {
    
    // ==================== Locators ====================
    
    private static final By PAGE_TITLE = By.className("title");
    private static final By CART_LIST = By.className("cart_list");
    private static final By CART_ITEM = By.className("cart_item");
    private static final By ITEM_NAME = By.className("inventory_item_name");
    private static final By ITEM_DESCRIPTION = By.className("inventory_item_desc");
    private static final By ITEM_PRICE = By.className("inventory_item_price");
    private static final By ITEM_QUANTITY = By.className("cart_quantity");
    private static final By REMOVE_BUTTON = By.cssSelector("button[data-test^='remove']");
    private static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private static final By CHECKOUT_BUTTON = By.id("checkout");
    
    // Dynamic locators
    private static final String REMOVE_BY_NAME = "//div[@class='inventory_item_name' and text()='%s']/ancestor::div[@class='cart_item']//button[contains(@data-test,'remove')]";
    
    // ==================== Navigation ====================
    
    /**
     * Continues shopping - returns to inventory page.
     *
     * @return InventoryPage instance
     */
    @Step("Continue shopping")
    public InventoryPage continueShopping() {
        log.info("Continuing shopping");
        click(CONTINUE_SHOPPING_BUTTON, WaitStrategy.CLICKABLE, "Continue Shopping");
        return new InventoryPage();
    }
    
    /**
     * Proceeds to checkout.
     *
     * @return CheckoutPage instance
     */
    @Step("Proceed to checkout")
    public CheckoutPage proceedToCheckout() {
        log.info("Proceeding to checkout");
        click(CHECKOUT_BUTTON, WaitStrategy.CLICKABLE, "Checkout");
        return new CheckoutPage();
    }
    
    // ==================== Cart Actions ====================
    
    /**
     * Removes item from cart by name.
     *
     * @param productName Name of the product
     * @return Current page instance
     */
    @Step("Remove item from cart: {productName}")
    public CartPage removeItem(String productName) {
        log.info("Removing item from cart: {}", productName);
        By removeButton = By.xpath(String.format(REMOVE_BY_NAME, productName));
        return click(removeButton, WaitStrategy.CLICKABLE, "Remove - " + productName);
    }
    
    /**
     * Removes first item from cart.
     *
     * @return Current page instance
     */
    @Step("Remove first item from cart")
    public CartPage removeFirstItem() {
        log.info("Removing first item from cart");
        return click(REMOVE_BUTTON, WaitStrategy.CLICKABLE, "Remove First Item");
    }
    
    /**
     * Removes all items from cart.
     *
     * @return Current page instance
     */
    @Step("Remove all items from cart")
    public CartPage removeAllItems() {
        log.info("Removing all items from cart");
        int itemCount = getCartItemCount();
        for (int i = 0; i < itemCount; i++) {
            removeFirstItem();
        }
        return this;
    }
    
    // ==================== Getters ====================
    
    /**
     * Gets the page title.
     *
     * @return Page title text
     */
    @Step("Get cart page title")
    public String getCartTitle() {
        return getText(PAGE_TITLE, WaitStrategy.VISIBLE);
    }
    
    /**
     * Gets count of items in cart.
     *
     * @return Number of cart items
     */
    @Step("Get cart item count")
    public int getCartItemCount() {
        int count = getElementCount(CART_ITEM);
        log.info("Cart item count: {}", count);
        return count;
    }
    
    /**
     * Gets list of product names in cart.
     *
     * @return List of product names
     */
    @Step("Get cart product names")
    public List<String> getCartProductNames() {
        List<String> names = getTexts(ITEM_NAME);
        log.info("Cart products: {}", names);
        return names;
    }
    
    /**
     * Gets list of product prices in cart.
     *
     * @return List of product prices
     */
    @Step("Get cart product prices")
    public List<String> getCartProductPrices() {
        return getTexts(ITEM_PRICE);
    }
    
    /**
     * Gets total price of all items in cart.
     *
     * @return Total price
     */
    @Step("Get cart total")
    public double getCartTotal() {
        return getCartProductPrices().stream()
                .map(price -> price.replace("$", ""))
                .mapToDouble(Double::parseDouble)
                .sum();
    }
    
    // ==================== Verifications ====================
    
    /**
     * Checks if on cart page.
     *
     * @return true if on cart page
     */
    @Step("Verify on cart page")
    public boolean isOnCartPage() {
        boolean result = isDisplayed(CART_LIST) && 
                        getCurrentUrl().contains("cart.html");
        log.info("On cart page: {}", result);
        return result;
    }
    
    /**
     * Checks if cart is empty.
     *
     * @return true if cart is empty
     */
    @Step("Check if cart is empty")
    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }
    
    /**
     * Checks if product is in cart.
     *
     * @param productName Name of the product
     * @return true if product is in cart
     */
    @Step("Check if product is in cart: {productName}")
    public boolean isProductInCart(String productName) {
        return getCartProductNames().contains(productName);
    }
}
