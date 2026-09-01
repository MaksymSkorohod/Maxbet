package io.maxbet.tests;

import io.maxbet.pageObjects.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WithdrawalsTests extends TestBase {
    WithdrawalsPage withdrawalsPage = new WithdrawalsPage();
    ProfilePage profilePage = new ProfilePage();
    PendingWdPage pendingWdPage = new PendingWdPage();
    @BeforeMethod
    public void openWithdrawPage() {
        ProfilePage profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
    }
    @Test(description = "Open first payment method the 'Withdraw' page")
    public void openFirstWithdrawMethod(){
        profilePage
                .clickOnWithdrawalBtnOnProfilePage();
        withdrawalsPage
                .waitUntilPageOpened();
        withdrawalsPage
                .openFirstWithdrawalMethod();
    }
    @Test(description = "Check warning message for minimum amount of WD")
    public void checkMinimumWdAmountMessage(){
        profilePage
                .clickOnWithdrawalBtnOnProfilePage();
        withdrawalsPage
                .waitUntilPageOpened();
        withdrawalsPage
                .openFirstWithdrawalMethod()
                .enterAmountOfWdForBankCard("4")
                .clickCardSwitch()
                .verifyWithdrawErrorMessage("Number should be 5 minimum");
    }
    @Test(description = "Check warning message for maximum amount of WD")
    public void checkMaximumWdAmountMessage(){
        profilePage
                .clickOnWithdrawalBtnOnProfilePage();
        withdrawalsPage
                .waitUntilPageOpened();
        withdrawalsPage
                .openThirdWithdrawalMethod()
                .enterAmountOfWdForBankCard("15000")
                .clickCardSwitch()
                .clickContinueBtnWd()
                .verifyWithdrawErrorMessage("Number should be 10500 maximum");
    }
    @Test(description = "Make successful WD")
    public void makeSuccessfulWd(){
        profilePage
                .clickOnWithdrawalBtnOnProfilePage();
        withdrawalsPage
                .waitUntilPageOpened();
        withdrawalsPage
                .openThirdWithdrawalMethod()
                .enterAmountOfWdForBankCard("50")
                .clickCardSwitch()
                .clickContinueBtnWd()
                .verifySuccessWdModal();
    }
    @Test(description = "Open the 'Pending withdrawals' page")
    public void openPendingWd(){
        profilePage
                .clickOnPendingWd();
        new PendingWdPage()
                .waitUntilPendingWdPageOpened();
    }
    @Test(description = "Open the 'Pending withdrawals' page")
    public void removePendingWd(){
        profilePage
                .clickOnPendingWd();
        pendingWdPage
                .waitUntilPendingWdPageOpened();
        pendingWdPage
                .clickOnRemovePendingWdBtn();
        pendingWdPage
                .getPendingWdNotification().verify();
    }
}
