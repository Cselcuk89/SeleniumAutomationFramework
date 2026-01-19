package com.selcuk.tests;

/**
 * Test data constants for SauceDemo tests.
 * Centralizes test data to improve maintainability.
 */
public final class TestData {
    
    private TestData() {
        // Utility class - prevent instantiation
    }
    
    // ==================== Customer Information ====================
    
    public static final String CHECKOUT_FIRST_NAME = "John";
    public static final String CHECKOUT_LAST_NAME = "Doe";
    public static final String CHECKOUT_POSTAL_CODE = "12345";
    
    // ==================== Product Names ====================
    
    public static final String PRODUCT_BACKPACK = "Sauce Labs Backpack";
    public static final String PRODUCT_BIKE_LIGHT = "Sauce Labs Bike Light";
    public static final String PRODUCT_BOLT_TSHIRT = "Sauce Labs Bolt T-Shirt";
    public static final String PRODUCT_FLEECE_JACKET = "Sauce Labs Fleece Jacket";
    public static final String PRODUCT_ONESIE = "Sauce Labs Onesie";
    public static final String PRODUCT_TEST_TSHIRT = "Test.allTheThings() T-Shirt (Red)";
    
    // ==================== Error Messages ====================
    
    public static final String ERROR_USERNAME_REQUIRED = "Username is required";
    public static final String ERROR_PASSWORD_REQUIRED = "Password is required";
    public static final String ERROR_LOCKED_OUT = "locked out";
    public static final String ERROR_INVALID_CREDENTIALS = "Username and password do not match";
    public static final String ERROR_FIRST_NAME_REQUIRED = "First Name is required";
    public static final String ERROR_LAST_NAME_REQUIRED = "Last Name is required";
    public static final String ERROR_POSTAL_CODE_REQUIRED = "Postal Code is required";
    
    // ==================== Page Titles ====================
    
    public static final String PAGE_TITLE_PRODUCTS = "Products";
    public static final String PAGE_TITLE_CART = "Your Cart";
    
    // ==================== Order Completion ====================
    
    public static final String ORDER_COMPLETE_MESSAGE = "Thank you";
    
    // ==================== Invalid Credentials ====================
    
    public static final String INVALID_USERNAME = "invalid_user";
    public static final String INVALID_PASSWORD = "invalid_password";
}
