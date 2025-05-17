package com.selcuk.driver;

import com.selcuk.enums.ConfigProperties;
import com.selcuk.frameworkExceptions.BrowserInvocationFailedException;
import com.selcuk.projectFactories.DriverFactory;
import com.selcuk.utilities.PropertyUtils;

import java.net.MalformedURLException;
import java.util.Objects;

public class Driver {
    private Driver(){

    }
    public static void initializeDriver(String browser,String version){
        if (Objects.isNull(DriverManager.getDriver())){
            try{
                DriverManager.setDriver(DriverFactory.getDriver(browser,version));
            } catch (MalformedURLException e) {
                throw new BrowserInvocationFailedException("Please check the capabilities of browser");
            }
            DriverManager.getDriver().get(PropertyUtils.get(ConfigProperties.URL));
        }
    }
    public static void quitDriver() {
        if(Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
