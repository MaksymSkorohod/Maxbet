package io.maxbet.tests;

import io.maxbet.pageObjects.DepositPage;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.ProfilePage;
import io.maxbet.pageObjects.WithdrawalsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WithdrawalsTests extends TestBase {
    WithdrawalsPage withdrawalsPage = new WithdrawalsPage();
    @BeforeMethod
    public void openWithdrawPage() {
        ProfilePage profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
        profilePage.clickOnWithdrawalBtnOnProfilePage();
        withdrawalsPage.waitUntilPageOpened();
    }

    @Test(description = "Open first paymant method the 'Withdraw' page")
    public void openFirstWithdrawMethod(){
        withdrawalsPage
                .openFirstWithdrawalMethod();
    }
}
