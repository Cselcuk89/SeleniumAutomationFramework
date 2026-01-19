# Selenium Automation Framework

A comprehensive, industry-level UI automation framework built with Selenium WebDriver, Spring Boot integration, and modern Java practices.

## 🚀 Features

- **Spring Boot Integration**: Dependency injection, configuration management, and easy test setup
- **Modern Selenium 4**: Latest WebDriver capabilities with improved wait strategies
- **Dual Reporting**: Allure Reports and ExtentReports for comprehensive test reporting
- **Fluent API**: Page Object Model with method chaining for readable test code
- **Functional Programming**: Leverages Java functional interfaces and streams
- **Parallel Execution**: Support for parallel test execution with TestNG
- **Retry Mechanism**: Configurable test retry on failure
- **Screenshot Capture**: Automatic screenshots on test failure
- **Comprehensive Logging**: SLF4J with Logback for structured logging

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/selcuk/
│   │   ├── annotations/        # Custom annotations
│   │   ├── config/             # Spring Boot configuration
│   │   ├── constants/          # Framework constants
│   │   ├── driver/             # WebDriver management
│   │   ├── enums/              # Enumerations
│   │   ├── frameworkExceptions/# Custom exceptions
│   │   ├── projectFactories/   # Factory classes
│   │   ├── projectListeners/   # TestNG listeners
│   │   ├── projectPages/       # Page Objects
│   │   │   └── saucedemo/      # SauceDemo page objects
│   │   ├── projectReports/     # Reporting utilities
│   │   └── utilities/          # Helper utilities
│   └── resources/
│       ├── application.properties  # Spring Boot config
│       └── logback.xml            # Logging config
└── test/
    ├── java/com/selcuk/tests/
    │   ├── BaseTest.java           # Base test class
    │   └── saucedemo/              # SauceDemo tests
    └── resources/
        ├── config/                 # Test configuration
        └── allure.properties       # Allure config
```

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 11 | Programming Language |
| Spring Boot | 2.7.18 | Dependency Injection & Configuration |
| Selenium | 4.15.0 | Browser Automation |
| TestNG | 7.8.0 | Test Framework |
| Allure | 2.24.0 | Test Reporting |
| ExtentReports | 5.1.1 | HTML Reporting |
| Lombok | 1.18.30 | Boilerplate Reduction |
| WebDriverManager | 5.6.2 | Driver Management |
| AssertJ | 3.x | Fluent Assertions |

## ⚙️ Configuration

### application.properties

```properties
# Browser Configuration
selenium.browser=chrome
selenium.headless=true
selenium.remote=false

# Application Under Test
app.base.url=https://www.saucedemo.com/

# Reporting
reports.extent.enabled=true
reports.allure.enabled=true
reports.screenshot.on.failure=true
```

## 🏃 Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=LoginTest
```

### Run with Specific Browser
```bash
mvn test -Dselenium.browser=firefox
```

### Generate Allure Report
```bash
mvn allure:serve
```

## 📝 Writing Tests

### Example Test
```java
@Test(description = "Verify successful login")
@FrameworkAnnotation(author = {"Developer"}, category = {CategoryType.SMOKE})
@Story("User Login")
@Severity(SeverityLevel.CRITICAL)
public void testSuccessfulLogin() {
    LoginPage loginPage = new LoginPage();
    
    InventoryPage inventoryPage = loginPage
            .enterUsername("standard_user")
            .enterPassword("secret_sauce")
            .clickLoginButton();
    
    assertThat(inventoryPage.isOnInventoryPage()).isTrue();
}
```

### Page Object Pattern
```java
public class LoginPage extends BasePage<LoginPage> {
    
    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    
    public LoginPage enterUsername(String username) {
        return sendKeys(USERNAME_INPUT, username, WaitStrategy.VISIBLE, "Username");
    }
    
    public LoginPage enterPassword(String password) {
        return sendKeys(PASSWORD_INPUT, password, WaitStrategy.VISIBLE, "Password");
    }
    
    public InventoryPage clickLoginButton() {
        click(LOGIN_BUTTON, WaitStrategy.CLICKABLE, "Login Button");
        return new InventoryPage();
    }
}
```

## 🔧 Key Components

### BasePage
- Fluent API with method chaining
- Common web interactions (click, sendKeys, select, hover, scroll)
- Built-in wait strategies
- Functional programming methods for element collections

### BaseTest
- Spring Boot context initialization
- WebDriver lifecycle management
- Automatic screenshot on failure
- Allure integration

### WebDriverFactory
- Supports Chrome, Firefox, Edge browsers
- Local and Remote (Selenium Grid) execution
- Configurable browser options
- Headless mode support

## 📊 Reports

### Allure Reports
- Rich interactive reports
- Test history and trends
- Attachments (screenshots, logs)
- Categories and severity levels

### ExtentReports
- HTML dashboard
- Author and category tracking
- Pass/Fail/Skip statistics

## 🧪 Test Categories

- **SMOKE**: Critical path tests
- **REGRESSION**: Full regression suite
- **SANITY**: Quick sanity checks
- **MINIREGRESSION**: Subset of regression

## 📌 Best Practices

1. **Page Object Model**: All page interactions in page classes
2. **Fluent API**: Chain methods for readability
3. **Explicit Waits**: Use wait strategies, avoid Thread.sleep
4. **Assertions**: Use AssertJ for fluent assertions
5. **Logging**: Use SLF4J for all logging
6. **Configuration**: Externalize all configurations

## 📄 License

This project is for educational and demonstration purposes.

## 👥 Contributors

- Selcuk
