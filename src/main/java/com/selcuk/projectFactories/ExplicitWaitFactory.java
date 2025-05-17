package com.selcuk.projectFactories;

import com.selcuk.constants.ProjectConstants;
import com.selcuk.driver.DriverManager;
import com.selcuk.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitFactory {
    private ExplicitWaitFactory(){}
    public static WebElement performExplicitWaitMethod(WaitStrategy waitStrategy, By by){
        WebElement element = null;
        if (waitStrategy == WaitStrategy.CLICKABLE) {
            element = new WebDriverWait(DriverManager.getDriver(), ProjectConstants.getExplicitwait())
                    .until(ExpectedConditions.elementToBeClickable(by));
        }else if(waitStrategy == WaitStrategy.PRESENCE) {
                element =	new WebDriverWait(DriverManager.getDriver(), ProjectConstants.getExplicitwait())
                        .until(ExpectedConditions.presenceOfElementLocated(by));
            }
            else if(waitStrategy == WaitStrategy.VISIBLE) {
                element =new WebDriverWait(DriverManager.getDriver(), ProjectConstants.getExplicitwait())
                        .until(ExpectedConditions.visibilityOfElementLocated(by));
            }
            else if(waitStrategy == WaitStrategy.NONE) {
                element = DriverManager.getDriver().findElement(by);
            }
            return element;
        }

}
