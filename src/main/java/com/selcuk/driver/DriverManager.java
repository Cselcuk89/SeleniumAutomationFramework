package com.selcuk.driver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Thread-safe WebDriver manager using ThreadLocal pattern.
 * Provides functional-style operations on the driver instance.
 */
@Slf4j
public final class DriverManager {
    
    private DriverManager() {
        // Utility class - prevent instantiation
    }
    
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    
    /**
     * Gets the current thread's WebDriver instance.
     *
     * @return WebDriver instance for current thread
     */
    public static WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }
    
    /**
     * Gets an Optional wrapper around the current driver.
     * Enables functional-style operations.
     *
     * @return Optional containing driver if present
     */
    public static Optional<WebDriver> getDriverOptional() {
        return Optional.ofNullable(DRIVER_THREAD_LOCAL.get());
    }
    
    /**
     * Sets the WebDriver instance for current thread.
     *
     * @param driverRef WebDriver instance to set
     */
    public static void setDriver(WebDriver driverRef) {
        if (Objects.nonNull(driverRef)) {
            log.debug("Setting WebDriver for thread: {}", Thread.currentThread().getName());
            DRIVER_THREAD_LOCAL.set(driverRef);
        }
    }
    
    /**
     * Removes the WebDriver instance from current thread.
     * Should be called after quitting the driver.
     */
    public static void unload() {
        log.debug("Unloading WebDriver from thread: {}", Thread.currentThread().getName());
        DRIVER_THREAD_LOCAL.remove();
    }
    
    /**
     * Checks if a driver is available for current thread.
     *
     * @return true if driver is present
     */
    public static boolean hasDriver() {
        return Objects.nonNull(DRIVER_THREAD_LOCAL.get());
    }
    
    /**
     * Executes an action if driver is present.
     * Functional alternative to null checks.
     *
     * @param action Consumer action to execute
     */
    public static void ifPresent(Consumer<WebDriver> action) {
        getDriverOptional().ifPresent(action);
    }
    
    /**
     * Maps the driver to a result if present.
     *
     * @param mapper Function to apply to driver
     * @param <T> Return type
     * @return Optional containing mapped result
     */
    public static <T> Optional<T> map(Function<WebDriver, T> mapper) {
        return getDriverOptional().map(mapper);
    }
    
    /**
     * Executes an action on the driver, handling null safely.
     *
     * @param action Action to execute
     * @throws IllegalStateException if no driver is available
     */
    public static void executeOnDriver(Consumer<WebDriver> action) {
        if (!hasDriver()) {
            throw new IllegalStateException("No WebDriver instance available for current thread");
        }
        action.accept(getDriver());
    }
}
