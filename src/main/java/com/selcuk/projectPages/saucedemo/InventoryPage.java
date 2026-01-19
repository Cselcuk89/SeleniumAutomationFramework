package com.selcuk.projectPages.saucedemo;

import com.selcuk.enums.WaitStrategy;
import com.selcuk.projectPages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Page Object for SauceDemo Inventory (Products) Page.
 * URL: https://www.saucedemo.com/inventory.html
 */
@Slf4j
public class InventoryPage extends BasePage<InventoryPage> {
    
    // ==================== Locators ====================
    
    private static final By PRODUCT_TITLE = By.className("title");
    private static final By INVENTORY_LIST = By.className("inventory_list");
    private static final By INVENTORY_ITEM = By.className("inventory_item");
    private static final By ITEM_NAME = By.className("inventory_item_name");
    private static final By ITEM_DESCRIPTION = By.className("inventory_item_desc");
    private static final By ITEM_PRICE = By.className("inventory_item_price");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button[data-test^='add-to-cart']");
    private static final By REMOVE_BUTTON = By.cssSelector("button[data-test^='remove']");
    private static final By SHOPPING_CART_BADGE = By.className("shopping_cart_badge");
    private static final By SHOPPING_CART_LINK = By.className("shopping_cart_link");
    private static final By BURGER_MENU_BUTTON = By.id("react-burger-menu-btn");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private static final By CLOSE_MENU_BUTTON = By.id("react-burger-cross-btn");
    private static final By SORT_DROPDOWN = By.className("product_sort_container");
    private static final By MENU_ITEMS = By.cssSelector(".bm-item-list a");
    
    // Dynamic locators
    private static final String ADD_TO_CART_BY_NAME = "//div[@class='inventory_item_name ' and text()='%s']/ancestor::div[@class='inventory_item']//button[contains(@data-test,'add-to-cart')]";
    private static final String REMOVE_BY_NAME = "//div[@class='inventory_item_name ' and text()='%s']/ancestor::div[@class='inventory_item']//button[contains(@data-test,'remove')]";
    private static final String ITEM_LINK_BY_NAME = "//div[@class='inventory_item_name ' and text()='%s']";
    
    // ==================== Navigation ====================
    
    /**
     * Opens the shopping cart.
     *
     * @return CartPage instance
     */
    @Step("Open shopping cart")
    public CartPage openCart() {
        log.info("Opening shopping cart");
        click(SHOPPING_CART_LINK, WaitStrategy.CLICKABLE, "Shopping Cart");
        return new CartPage();
    }
    
    /**
     * Opens the burger menu.
     *
     * @return Current page instance
     */
    @Step("Open burger menu")
    public InventoryPage openBurgerMenu() {
        log.info("Opening burger menu");
        return click(BURGER_MENU_BUTTON, WaitStrategy.CLICKABLE, "Burger Menu");
    }
    
    /**
     * Closes the burger menu.
     *
     * @return Current page instance
     */
    @Step("Close burger menu")
    public InventoryPage closeBurgerMenu() {
        log.info("Closing burger menu");
        return click(CLOSE_MENU_BUTTON, WaitStrategy.CLICKABLE, "Close Menu");
    }
    
    /**
     * Logs out of the application.
     *
     * @return LoginPage instance
     */
    @Step("Logout")
    public LoginPage logout() {
        log.info("Logging out");
        openBurgerMenu();
        waitForClickable(LOGOUT_LINK);
        click(LOGOUT_LINK, WaitStrategy.CLICKABLE, "Logout Link");
        return new LoginPage();
    }
    
    // ==================== Product Actions ====================
    
    /**
     * Adds a product to cart by name.
     *
     * @param productName Name of the product
     * @return Current page instance
     */
    @Step("Add product to cart: {productName}")
    public InventoryPage addProductToCart(String productName) {
        log.info("Adding product to cart: {}", productName);
        By addButton = By.xpath(String.format(ADD_TO_CART_BY_NAME, productName));
        return click(addButton, WaitStrategy.CLICKABLE, "Add to Cart - " + productName);
    }
    
    /**
     * Removes a product from cart by name.
     *
     * @param productName Name of the product
     * @return Current page instance
     */
    @Step("Remove product from cart: {productName}")
    public InventoryPage removeProductFromCart(String productName) {
        log.info("Removing product from cart: {}", productName);
        By removeButton = By.xpath(String.format(REMOVE_BY_NAME, productName));
        return click(removeButton, WaitStrategy.CLICKABLE, "Remove - " + productName);
    }
    
    /**
     * Adds first available product to cart.
     *
     * @return Current page instance
     */
    @Step("Add first product to cart")
    public InventoryPage addFirstProductToCart() {
        log.info("Adding first product to cart");
        return click(ADD_TO_CART_BUTTON, WaitStrategy.CLICKABLE, "Add to Cart (First Product)");
    }
    
    /**
     * Opens product detail page.
     *
     * @param productName Name of the product
     * @return ProductDetailPage instance
     */
    @Step("Open product details: {productName}")
    public ProductDetailPage openProductDetails(String productName) {
        log.info("Opening product details: {}", productName);
        By itemLink = By.xpath(String.format(ITEM_LINK_BY_NAME, productName));
        click(itemLink, WaitStrategy.CLICKABLE, "Product Link - " + productName);
        return new ProductDetailPage();
    }
    
    /**
     * Sorts products by selected option.
     *
     * @param sortOption Sort option text
     * @return Current page instance
     */
    @Step("Sort products by: {sortOption}")
    public InventoryPage sortBy(String sortOption) {
        log.info("Sorting products by: {}", sortOption);
        return selectByText(SORT_DROPDOWN, sortOption, "Sort Dropdown");
    }
    
    /**
     * Sorts products by price low to high.
     *
     * @return Current page instance
     */
    @Step("Sort by price: Low to High")
    public InventoryPage sortByPriceLowToHigh() {
        return sortBy("Price (low to high)");
    }
    
    /**
     * Sorts products by price high to low.
     *
     * @return Current page instance
     */
    @Step("Sort by price: High to Low")
    public InventoryPage sortByPriceHighToLow() {
        return sortBy("Price (high to low)");
    }
    
    /**
     * Sorts products by name A to Z.
     *
     * @return Current page instance
     */
    @Step("Sort by name: A to Z")
    public InventoryPage sortByNameAtoZ() {
        return sortBy("Name (A to Z)");
    }
    
    /**
     * Sorts products by name Z to A.
     *
     * @return Current page instance
     */
    @Step("Sort by name: Z to A")
    public InventoryPage sortByNameZtoA() {
        return sortBy("Name (Z to A)");
    }
    
    // ==================== Getters ====================
    
    /**
     * Gets the page title text.
     *
     * @return Title text
     */
    @Step("Get products page title")
    public String getProductsTitle() {
        return getText(PRODUCT_TITLE, WaitStrategy.VISIBLE);
    }
    
    /**
     * Gets the cart badge count.
     *
     * @return Cart item count, 0 if badge not visible
     */
    @Step("Get cart item count")
    public int getCartItemCount() {
        if (isDisplayed(SHOPPING_CART_BADGE)) {
            String badgeText = getText(SHOPPING_CART_BADGE, WaitStrategy.VISIBLE);
            int count = Integer.parseInt(badgeText);
            log.info("Cart item count: {}", count);
            return count;
        }
        return 0;
    }
    
    /**
     * Gets list of all product names.
     *
     * @return List of product names
     */
    @Step("Get all product names")
    public List<String> getProductNames() {
        List<String> names = getTexts(ITEM_NAME);
        log.info("Found {} products", names.size());
        return names;
    }
    
    /**
     * Gets list of all product prices.
     *
     * @return List of product prices
     */
    @Step("Get all product prices")
    public List<String> getProductPrices() {
        return getTexts(ITEM_PRICE);
    }
    
    /**
     * Gets product prices as doubles.
     *
     * @return List of prices as doubles
     */
    @Step("Get all product prices as numbers")
    public List<Double> getProductPricesAsDouble() {
        return getProductPrices().stream()
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
    
    /**
     * Gets the product count.
     *
     * @return Number of products
     */
    @Step("Get product count")
    public int getProductCount() {
        int count = getElementCount(INVENTORY_ITEM);
        log.info("Product count: {}", count);
        return count;
    }
    
    /**
     * Gets price of a specific product.
     *
     * @param productName Name of the product
     * @return Product price or empty
     */
    @Step("Get price of product: {productName}")
    public Optional<String> getProductPrice(String productName) {
        return getElements(INVENTORY_ITEM).stream()
                .filter(item -> item.findElement(ITEM_NAME).getText().equals(productName))
                .map(item -> item.findElement(ITEM_PRICE).getText())
                .findFirst();
    }
    
    // ==================== Verifications ====================
    
    /**
     * Checks if on inventory page.
     *
     * @return true if on inventory page
     */
    @Step("Verify on inventory page")
    public boolean isOnInventoryPage() {
        boolean result = isDisplayed(INVENTORY_LIST) && 
                        getCurrentUrl().contains("inventory.html");
        log.info("On inventory page: {}", result);
        return result;
    }
    
    /**
     * Checks if product is displayed.
     *
     * @param productName Name of the product
     * @return true if product is displayed
     */
    @Step("Check if product is displayed: {productName}")
    public boolean isProductDisplayed(String productName) {
        return getProductNames().contains(productName);
    }
    
    /**
     * Checks if cart badge is visible.
     *
     * @return true if badge is visible
     */
    @Step("Check if cart badge is visible")
    public boolean isCartBadgeVisible() {
        return isDisplayed(SHOPPING_CART_BADGE);
    }
    
    /**
     * Checks if products are sorted by price low to high.
     *
     * @return true if sorted correctly
     */
    @Step("Verify products sorted by price ascending")
    public boolean isProductsSortedByPriceAsc() {
        List<Double> prices = getProductPricesAsDouble();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if products are sorted by price high to low.
     *
     * @return true if sorted correctly
     */
    @Step("Verify products sorted by price descending")
    public boolean isProductsSortedByPriceDesc() {
        List<Double> prices = getProductPricesAsDouble();
        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
