package io.maxbet.tests;

import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.PendingWdPage;
import io.maxbet.pageObjects.ProfilePage;
import io.maxbet.pageObjects.WithdrawalsPage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PendingWithdrawalsTests extends TestBase {

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
        skipIfNoPendingWithdrawal();
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
        skipIfNoPendingWithdrawal();
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
       skipIfNoPendingWithdrawal();
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
    /**
     * The remove-flow tests only make sense when the shared account actually has a withdrawal waiting.
     * An empty pending list is a valid state of the account, not a defect, so the test is skipped
     * rather than failed.
     */
    private void skipIfNoPendingWithdrawal() {
        if (!pendingWdPage.hasPendingWithdrawal()) {
            throw new SkipException(
                    "No pending withdrawal is waiting in the list, so the remove flow cannot be exercised");
        }
    }
}
