package com.selcuk.constants;

import com.selcuk.enums.ConfigProperties;
import com.selcuk.utilities.PropertyUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * Framework constants and path configurations.
 * Provides centralized access to all framework-related constants.
 */
@Slf4j
public final class ProjectConstants {
    
    private ProjectConstants() {
        // Utility class - prevent instantiation
    }

    private static final int EXPLICITWAIT = 15;
    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
    private static final String CHROMEDRIVERPATH = RESOURCESPATH + "/executables/chromedriver";
    private static final String GECKODRIVERPATH = RESOURCESPATH + "/executables/geckodriver";
    private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";
    private static final String JSONCONFIGFILEPATH = RESOURCESPATH + "/config/config.json";
    private static final String EXCELPATH = RESOURCESPATH + "/excel/testdata.xlsx";
    private static final String RUNMANGERSHEET = "RUNMANAGER";
    private static final String ITERATIONDATASHEET = "DATA";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
    private static final String ALLUREREPORTFOLDERPATH = System.getProperty("user.dir") + "/target/allure-results/";
    private static final String SCREENSHOTPATH = System.getProperty("user.dir") + "/screenshots/";
    private static final String LOGPATH = System.getProperty("user.dir") + "/logs/";
    
    private static String extentReportFilePath = "";
    
    /**
     * Gets the extent report file path.
     * Creates timestamped path if override is disabled.
     *
     * @return Extent report file path
     */
    public static String getExtentReportFilePath() {
        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }
    
    private static String createReportPath() {
        // Create directory if it doesn't exist
        new File(EXTENTREPORTFOLDERPATH).mkdirs();
        
        try {
            if (PropertyUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")) {
                String path = EXTENTREPORTFOLDERPATH + System.currentTimeMillis() + "/index.html";
                new File(EXTENTREPORTFOLDERPATH + System.currentTimeMillis()).mkdirs();
                return path;
            }
        } catch (Exception e) {
            log.warn("Could not read override reports config, using default path");
        }
        return EXTENTREPORTFOLDERPATH + "index.html";
    }
    
    public static String getGeckoDriverPath() {
        return GECKODRIVERPATH;
    }

    public static String getExcelpath() {
        return EXCELPATH;
    }

    public static String getJsonconfigfilepath() {
        return JSONCONFIGFILEPATH;
    }

    public static int getExplicitwait() {
        return EXPLICITWAIT;
    }

    public static String getRunmangerDatasheet() {
        return RUNMANGERSHEET;
    }

    public static String getIterationDatasheet() {
        return ITERATIONDATASHEET;
    }

    public static String getConfigFilePath() {
        return CONFIGFILEPATH;
    }

    public static String getChromeDriverPath() {
        return CHROMEDRIVERPATH;
    }
    
    public static String getAllureReportFolderPath() {
        return ALLUREREPORTFOLDERPATH;
    }
    
    public static String getScreenshotPath() {
        new File(SCREENSHOTPATH).mkdirs();
        return SCREENSHOTPATH;
    }
    
    public static String getLogPath() {
        new File(LOGPATH).mkdirs();
        return LOGPATH;
    }
    
    public static String getResourcesPath() {
        return RESOURCESPATH;
    }
}
