package io.maxbet.tests;

import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.PendingWdPage;
import io.maxbet.pageObjects.ProfilePage;
import io.maxbet.pageObjects.WithdrawalsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PendingWithdrawalsTests extends TestBase {
    private static final String WD_AMOUNT = "50";

    private PendingWdPage pendingWdPage;

    @BeforeMethod(alwaysRun = true)
    public void openPendingWithdrawalsPage() {
        ProfilePage profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
        profilePage.clickOnPendingWd();
        pendingWdPage = new PendingWdPage();
        pendingWdPage.waitUntilPendingWdPageOpened();
    }

    @Test(description = "Open the 'Pending withdrawals' page from the 'Profile' page")
    public void pendingWithdrawalsPageIsOpened() {
        pendingWdPage
            .getRequestWdBtn().verify();
    }

    @Test(description = "The 'Request Withdrawal' button opens the withdrawal payment methods")
    public void requestWithdrawalButtonOpensThePaymentMethods() {
        pendingWdPage.clickOnRequestWd();
        WithdrawalsPage withdrawalsPage = new WithdrawalsPage();
        withdrawalsPage.waitUntilPageOpened();
        withdrawalsPage.getWithdrawalPageTitle().verify();
    }

    @Test(description = "The 'Remove' button of a pending withdrawal asks for a confirmation")
    public void removeButtonOpensTheConfirmationDialog() {
        ensurePendingWithdrawalExists();
        pendingWdPage
            .clickOnRemovePendingWdBtn();
        pendingWdPage
            .getPendingWdNotification().verify();
        pendingWdPage
            .getPendingWdNotificationContinue().verify();
        pendingWdPage
            .getPendingWdNotificationCancel().verify();
    }

    @Test(description = "Cancelling the confirmation keeps the pending withdrawal in the list")
    public void cancelKeepsThePendingWithdrawal() {
        ensurePendingWithdrawalExists();
        int pendingBefore = pendingWdPage.getPendingWithdrawalsCount();

        pendingWdPage
            .clickOnRemovePendingWdBtn();
        pendingWdPage
            .getPendingWdNotification().verify();
        pendingWdPage
            .clickCancelBtnForPendingWdDialog();

        Assert.assertTrue(pendingWdPage.getPendingWdNotification().invisibilityOfElementLocated(10),
                "The confirmation dialog is still shown after 'Cancel'");
        Assert.assertEquals(pendingWdPage.getPendingWithdrawalsCount(), pendingBefore,
                "The withdrawal was removed even though the confirmation was cancelled");
    }

    @Test(description = "Confirming the removal takes the withdrawal out of the list")
    public void continueRemovesThePendingWithdrawal() {
        ensurePendingWithdrawalExists();
        int pendingBefore = pendingWdPage.getPendingWithdrawalsCount();

        pendingWdPage
        .clickOnRemovePendingWdBtn();
        pendingWdPage
            .getPendingWdNotification().verify();
        pendingWdPage
            .clickContinueBtnForPendingWdDialog();
        Assert.assertTrue(pendingWdPage.getPendingWdNotification().invisibilityOfElementLocated(15),
                "The confirmation dialog is still shown after 'Continue'");
        Assert.assertTrue(pendingWdPage.waitUntilPendingWithdrawalsCountIs(pendingBefore - 1),
                "The confirmed withdrawal is still in the pending list: expected " + (pendingBefore - 1)
                        + " withdrawal(s) but found " + pendingWdPage.getPendingWithdrawalsCount());
    }

    private void 
    ensurePendingWithdrawalExists() {
        if (pendingWdPage.hasPendingWithdrawal()) {
            return;
        }
        pendingWdPage.clickOnRequestWd();
        WithdrawalsPage withdrawalsPage = new WithdrawalsPage();
        withdrawalsPage.waitUntilPageOpened();
        withdrawalsPage
                .openThirdWithdrawalMethod()
                .enterAmountOfWdForBankCard(WD_AMOUNT)
                .clickCardSwitch()
                .clickContinueBtnWd()
                .verifySuccessWdModal()
                .clickToTheLobbyBtn();
        openPendingWithdrawalsPage();
        Assert.assertTrue(pendingWdPage.hasPendingWithdrawal(),
                "A withdrawal of " + WD_AMOUNT + " was requested but nothing is waiting in the pending list");
    }
}
