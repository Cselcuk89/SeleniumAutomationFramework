package com.selcuk.projectPages.saucedemo;

import com.selcuk.enums.WaitStrategy;
import com.selcuk.projectPages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

/**
 * Page Object for SauceDemo Product Detail Page.
 * URL: https://www.saucedemo.com/inventory-item.html?id=X
 */
@Slf4j
public class ProductDetailPage extends BasePage<ProductDetailPage> {
    
    // ==================== Locators ====================
    
    private static final By PRODUCT_NAME = By.className("inventory_details_name");
    private static final By PRODUCT_DESCRIPTION = By.className("inventory_details_desc");
    private static final By PRODUCT_PRICE = By.className("inventory_details_price");
    private static final By PRODUCT_IMAGE = By.className("inventory_details_img");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button[data-test^='add-to-cart']");
    private static final By REMOVE_BUTTON = By.cssSelector("button[data-test^='remove']");
    private static final By BACK_BUTTON = By.id("back-to-products");
    private static final By SHOPPING_CART_LINK = By.className("shopping_cart_link");
    private static final By SHOPPING_CART_BADGE = By.className("shopping_cart_badge");
    
    // ==================== Navigation ====================
    
    /**
     * Goes back to inventory page.
     *
     * @return InventoryPage instance
     */
    @Step("Go back to products")
    public InventoryPage backToProducts() {
        log.info("Going back to products");
        click(BACK_BUTTON, WaitStrategy.CLICKABLE, "Back to Products");
        return new InventoryPage();
    }
    
    /**
     * Opens shopping cart.
     *
     * @return CartPage instance
     */
    @Step("Open shopping cart")
    public CartPage openCart() {
        log.info("Opening shopping cart");
        click(SHOPPING_CART_LINK, WaitStrategy.CLICKABLE, "Shopping Cart");
        return new CartPage();
    }
    
    // ==================== Product Actions ====================
    
    /**
     * Adds product to cart.
     *
     * @return Current page instance
     */
    @Step("Add product to cart")
    public ProductDetailPage addToCart() {
        log.info("Adding product to cart");
        return click(ADD_TO_CART_BUTTON, WaitStrategy.CLICKABLE, "Add to Cart");
    }
    
    /**
     * Removes product from cart.
     *
     * @return Current page instance
     */
    @Step("Remove product from cart")
    public ProductDetailPage removeFromCart() {
        log.info("Removing product from cart");
        return click(REMOVE_BUTTON, WaitStrategy.CLICKABLE, "Remove");
    }
    
    // ==================== Getters ====================
    
    /**
     * Gets product name.
     *
     * @return Product name
     */
    @Step("Get product name")
    public String getProductName() {
        String name = getText(PRODUCT_NAME, WaitStrategy.VISIBLE);
        log.info("Product name: {}", name);
        return name;
    }
    
    /**
     * Gets product description.
     *
     * @return Product description
     */
    @Step("Get product description")
    public String getProductDescription() {
        return getText(PRODUCT_DESCRIPTION, WaitStrategy.VISIBLE);
    }
    
    /**
     * Gets product price.
     *
     * @return Product price
     */
    @Step("Get product price")
    public String getProductPrice() {
        String price = getText(PRODUCT_PRICE, WaitStrategy.VISIBLE);
        log.info("Product price: {}", price);
        return price;
    }
    
    /**
     * Gets product price as double.
     *
     * @return Product price as double
     */
    @Step("Get product price as number")
    public double getProductPriceAsDouble() {
        return Double.parseDouble(
                getProductPrice().replace("$", ""));
    }
    
    /**
     * Gets cart item count.
     *
     * @return Cart item count, 0 if badge not visible
     */
    @Step("Get cart item count")
    public int getCartItemCount() {
        if (isDisplayed(SHOPPING_CART_BADGE)) {
            return Integer.parseInt(getText(SHOPPING_CART_BADGE, WaitStrategy.VISIBLE));
        }
        return 0;
    }
    
    // ==================== Verifications ====================
    
    /**
     * Checks if on product detail page.
     *
     * @return true if on detail page
     */
    @Step("Verify on product detail page")
    public boolean isOnProductDetailPage() {
        return isDisplayed(PRODUCT_NAME) &&
               getCurrentUrl().contains("inventory-item.html");
    }
    
    /**
     * Checks if add to cart button is displayed.
     *
     * @return true if add button visible
     */
    @Step("Check if add to cart button is displayed")
    public boolean isAddToCartDisplayed() {
        return isDisplayed(ADD_TO_CART_BUTTON);
    }
    
    /**
     * Checks if remove button is displayed.
     *
     * @return true if remove button visible
     */
    @Step("Check if remove button is displayed")
    public boolean isRemoveDisplayed() {
        return isDisplayed(REMOVE_BUTTON);
    }
    
    /**
     * Checks if product is added to cart.
     *
     * @return true if product is in cart
     */
    @Step("Check if product is added to cart")
    public boolean isProductInCart() {
        return isRemoveDisplayed();
    }
}
