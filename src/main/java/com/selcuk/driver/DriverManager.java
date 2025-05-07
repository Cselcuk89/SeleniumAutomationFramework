package com.selcuk.driver;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class DriverManager {
    private DriverManager(){}
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static WebDriver getDriver(){
        return driver.get();
    }
    public static void setDriver(WebDriver driverRef){
        if (Objects.nonNull(driverRef)){
            driver.set(driverRef);
        }
    }
    static void unload(){
        driver.remove();
    }
}
