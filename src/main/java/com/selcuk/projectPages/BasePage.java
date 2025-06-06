package com.selcuk.projectPages;

import com.selcuk.driver.DriverManager;
import com.selcuk.enums.LogType;
import com.selcuk.enums.WaitStrategy;
import com.selcuk.projectFactories.ExplicitWaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.selcuk.enums.LogType.PASS;
import static com.selcuk.projectReports.FrameworkLogger.log;

public class BasePage {
    /**
     * Locates element by given wait strategy, performs the clicking operation on webelement and
     * writes the pass even to the extent report.
     * @param by By Locator of the webelement
     * @param waitstrategy Strategy to find webelement. Known  strategies {@link com.tmb.enums.WaitStrategy}
     * @param elementname Name of the element that needs to be logged in the report.
     */
    /**
     * Locates element by given wait strategy, sends the value to located webelement and
     * writes the pass even to the extent report.
     * @param by By Locator of the webelement
     * @param value value to be send the text box
     * @param waitstrategy Strategy to find webelement. Known  strategies {@link com.tmb.enums.WaitStrategy}
     * @param elementname Name of the element that needs to be logged in the report.
     */
    protected void click(By by, WaitStrategy waitStrategy,String elementname){
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(waitStrategy,by);
        element.click();
        log(PASS,elementname + " is clicked");
    }
    protected void sendKeys(By by,String value,WaitStrategy waitStrategy,String elementname){
        WebElement element = ExplicitWaitFactory.performExplicitWaitMethod(waitStrategy, by);
        element.sendKeys(value);
        log(PASS,value + " is entered with success" + elementname);
    }
    protected String getPageTitle() {
        return DriverManager.getDriver().getTitle();
    }

}
