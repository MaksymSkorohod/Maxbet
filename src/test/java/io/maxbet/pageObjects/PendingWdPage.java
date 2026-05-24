package io.maxbet.pageObjects;

import io.maxbet.Elements.Button;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

public class PendingWdPage extends AbstractPage{
    private final By requestWdBtn = By.cssSelector(".btn-tertiary.md");

    @Getter
    Button RequestWdBtn = new Button(requestWdBtn, "The 'Request Withdrawal' button");

    @Step("Click on the 'Request Withdrawal' button")
    public PendingWdPage clickOnRequestWd() {
        getRequestWdBtn().clickButton();
        return this;
    }
}
