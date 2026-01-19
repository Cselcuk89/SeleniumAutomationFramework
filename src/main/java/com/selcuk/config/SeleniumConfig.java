package com.selcuk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for Selenium WebDriver settings.
 * These properties are loaded from application.properties with prefix 'selenium'.
 */
@Data
@Component
@ConfigurationProperties(prefix = "selenium")
public class SeleniumConfig {
    
    private String browser = "chrome";
    private boolean headless = false;
    private boolean remote = false;
    private Grid grid = new Grid();
    private Wait implicit = new Wait();
    private Wait explicit = new Wait();
    private Wait pageLoad = new Wait();
    
    @Data
    public static class Grid {
        private String url = "http://localhost:4444/wd/hub";
    }
    
    @Data
    public static class Wait {
        private int wait = 10;
        private int timeout = 30;
    }
    
    public int getImplicitWait() {
        return implicit.getWait();
    }
    
    public int getExplicitWait() {
        return explicit.getWait();
    }
    
    public int getPageLoadTimeout() {
        return pageLoad.getTimeout();
    }
}
