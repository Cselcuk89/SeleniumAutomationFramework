package com.selcuk.projectPages;

import com.selcuk.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class OrangeHRMHomePage extends BasePage {
    private final By linkWelcome = By.id("welcome");
    private final By linkLogout = By.xpath("//a[text()='Logout']");
    public OrangeHRMHomePage clickWelcomePage(){
        click(linkWelcome, WaitStrategy.PRESENCE,"Welcome link");
        return this;
    }
    public OrangeHRMLoginPage clickLogout(){
        click(linkLogout,WaitStrategy.CLICKABLE,"Logout button");
        return new OrangeHRMLoginPage();
    }

}
