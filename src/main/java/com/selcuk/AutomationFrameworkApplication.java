package com.selcuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Spring Boot Application for Selenium Automation Framework.
 * This class initializes the Spring context and enables configuration property scanning.
 */
@SpringBootApplication
@ConfigurationPropertiesScan("com.selcuk.config")
public class AutomationFrameworkApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AutomationFrameworkApplication.class, args);
    }
}
