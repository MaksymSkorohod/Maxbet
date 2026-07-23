package io.maxbet.tests;

import io.maxbet.DriverManager;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.PromoPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PromoTest extends TestBase{
    private PromoPage promoPage;

    @BeforeMethod
    public void tournamentsPageIsOpen(){
        promoPage = new LobbyPage().clickOnPromotions();
    }
    @Test(description = "Open the 'Promotions' page")
    public void openPromoPage(){
        System.out.println("Navigate to: " + DriverManager.getDriver().getCurrentUrl());
        promoPage
                .verifyCurrentUrl("https://dev.maxbet.ro/en/promotions/like-prod");
        Assert.assertTrue(promoPage.isUrlContains("/like-prod"));
    }
    @Test(description = "Open the 'Promotions' page")
    public void openSectionPromoPage(){
        promoPage
                .printCurrentUrl();
        System.out.println(promoPage.getCurrentUrl());
        promoPage
                .clickOnKoratestPromoBtn();
        Assert.assertTrue(promoPage.isUrlContains("/koratest"));
    }


}
