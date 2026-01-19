package com.selcuk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for test users.
 * These properties are loaded from application.properties with prefix 'test'.
 */
@Data
@Component
@ConfigurationProperties(prefix = "test")
public class TestConfig {
    
    private Users users = new Users();
    private String password = "secret_sauce";
    private Retry retry = new Retry();
    private Parallel parallel = new Parallel();
    private Data data = new Data();
    
    @lombok.Data
    public static class Users {
        private String standard = "standard_user";
        private String locked = "locked_out_user";
        private String problem = "problem_user";
        private String performance = "performance_glitch_user";
        private String error = "error_user";
        private String visual = "visual_user";
    }
    
    @lombok.Data
    public static class Retry {
        private int count = 1;
        private boolean enabled = false;
    }
    
    @lombok.Data
    public static class Parallel {
        private boolean enabled = false;
        private Thread thread = new Thread();
    }
    
    @lombok.Data
    public static class Thread {
        private int count = 3;
    }
    
    @lombok.Data
    public static class Data {
        private String path = "src/test/resources/testdata/";
    }
    
    public String getStandardUser() {
        return users.getStandard();
    }
    
    public String getLockedUser() {
        return users.getLocked();
    }
    
    public String getProblemUser() {
        return users.getProblem();
    }
    
    public String getPerformanceUser() {
        return users.getPerformance();
    }
    
    public String getErrorUser() {
        return users.getError();
    }
    
    public String getVisualUser() {
        return users.getVisual();
    }
}
