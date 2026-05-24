package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

public class PromotionsPage extends BaseElement {
    private final By welcomeOffersBtn = By.cssSelector("body app-root mb-nav-list-item:nth-child(1)");

    @Getter
    Button WelcomeOffersBtn = new Button(welcomeOffersBtn, "The 'Welcome Offers' button");

    @Step("Click on the 'Welcome Offers' button")
    public PromotionsPage clickOnWelcomeOffersBtn() {
        getWelcomeOffersBtn().clickButton();
        return this;
    }
}
