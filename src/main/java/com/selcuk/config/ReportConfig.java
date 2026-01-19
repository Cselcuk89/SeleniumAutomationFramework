package com.selcuk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for reporting settings.
 * These properties are loaded from application.properties with prefix 'reports'.
 */
@Data
@Component
@ConfigurationProperties(prefix = "reports")
public class ReportConfig {
    
    private Extent extent = new Extent();
    private Allure allure = new Allure();
    private Screenshot screenshot = new Screenshot();
    
    @Data
    public static class Extent {
        private boolean enabled = true;
        private String path = "extent-test-output/";
        private boolean override = true;
    }
    
    @Data
    public static class Allure {
        private boolean enabled = true;
    }
    
    @Data
    public static class Screenshot {
        private On on = new On();
    }
    
    @Data
    public static class On {
        private boolean failure = true;
        private boolean pass = false;
    }
    
    public boolean isScreenshotOnFailure() {
        return screenshot.getOn().isFailure();
    }
    
    public boolean isScreenshotOnPass() {
        return screenshot.getOn().isPass();
    }
}
