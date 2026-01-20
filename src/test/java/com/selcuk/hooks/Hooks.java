package com.selcuk.hooks;

import com.selcuk.driver.Driver;
import com.selcuk.driver.DriverManager;
import com.selcuk.enums.ConfigProperties;
import com.selcuk.projectReports.ExtentReport;
import com.selcuk.utilities.PropertyUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        ExtentReport.initReports();
        ExtentReport.createTest(scenario.getName());
        String browser = PropertyUtils.get(ConfigProperties.BROWSER);
        Driver.initializeDriver(browser, "");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Screenshot is already taken by the FrameworkLogger on failure
        }
        Driver.quitDriver();
        ExtentReport.flushReports();
    }
}
