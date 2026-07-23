package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.testng.Assert;

public class PromoPage extends BaseElement {

    private final By promotionCategories = By.cssSelector("body app-root mb-nav-list-item:nth-child(1)");
    private final By koratestPromo = By.cssSelector("a[href='/en/promotions/koratest']");

    @Getter
    Button PromotionCategories = new Button(promotionCategories, "The 'Welcome Offers' button");
    @Getter
    Button KoratestPromo = new Button(koratestPromo, "The 'Koratest Promo' button");

    @Step("Click on the 'Koratest Promo' button")
    public PromoPage clickOnKoratestPromoBtn() {
        getKoratestPromo().clickButton();
        return this;
    }
    public void verifyCurrentUrl(String expectedUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl,
                "URL does not match expected value");
    }
}
