package com.selcuk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for the application under test.
 * These properties are loaded from application.properties with prefix 'app'.
 */
@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    
    private Base base = new Base();
    
    @Data
    public static class Base {
        private String url = "https://www.saucedemo.com/";
    }
    
    public String getBaseUrl() {
        return base.getUrl();
    }
}
