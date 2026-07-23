package io.maxbet.tests;

import io.maxbet.pageObjects.DepositPage;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DepositPageTests extends TestBase{
    private DepositPage depositPage;

    @BeforeMethod
    public void openDepositPage() {
        ProfilePage profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
        profilePage.clickOnDepositBtnOnProfPage();
        depositPage = new DepositPage();
        depositPage.clickOnBackBtnDeposit();
        Assert.assertTrue(depositPage.isDepositPageOpened(), "Deposit page title is not displayed");
    }
    @Test(description = "Open the 'Deposit' page from the 'Profile' page")
    public void openDepositPageFromProfile(){
        Assert.assertTrue(depositPage.isDepositPageOpened(), "Deposit page title is not displayed");
    }
    @Test(description = "Open the 'Visa & MasterCard & Maestro' payment method")
    public void openVisaCardPM(){
        depositPage
                .clickOnBankCardPM();
        depositPage
                .getPageTitleOfPM().verify();
    }
    @Test(description = "Open the 'Abon' payment method")
    public void openAbonPM(){
        depositPage
                .clickOnAbonPM();
        depositPage
                .getPageTitleOfPM().verify();
    }
    @Test(description = "Open the 'PaySafe' payment method")
    public void openPaySafePM(){
        depositPage
                .clickOnPaySafePM();
        depositPage
                .getPageTitleOfPM().verify();
    }
    @Test(description = "Open the 'External Cashier' payment method")
    public void openExternalCashierPM(){
        depositPage
                .clickOnExternalCashierPM();
        depositPage
                .getPageTitleOfPM().verify();
    }
    @Test(description = "Open the 'Google Pay' payment method")
    public void openGooglePayPM(){
        depositPage
                .clickOnGooglePayPM();
        depositPage
                .getPageTitleOfPM().verify();
    }
    @Test(description = "Open the 'Okto Cash' payment method")
    public void openOktoCashPM(){
        depositPage
                .clickOnOktoCashPM();
        depositPage
                .getPageTitleOfPM().verify();
    }
    @Test(description = "Open the 'Air Cash' payment method")
    public void openAirCashAppPM(){
        depositPage
                .clickOnAirCashPM();
        depositPage
                .getPageTitleOfPM().verify();
    }
    @Test(description = "Open the 'Air Cash Market' payment method")
    public void openAirCashMarketPM(){
        depositPage
                .clickOnAirCashMarketPM();
        depositPage
                .getPageTitleOfPM().verify();
    }
    @Test(description = "Open the 'Skrill' payment method")
    public void openSkrillPM(){
        depositPage
                .clickOnSkrillPM();
        depositPage
                .getPageTitleOfPM().verify();
    }
    @Test(description = "Open the 'Smith&Smith' payment method")
    public void openSmithPM(){
        depositPage
                .clickOnSmithPM();
    }
    @Test(description = "Open the 'Neteller' payment method")
    public void openNetellerPM(){
        depositPage
                .clickOnNetellerPM();
        depositPage
                .getPageTitleOfPM().verify();
    }
}
