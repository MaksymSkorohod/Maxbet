package io.maxbet.tests;

import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.ProfilePage;
import io.maxbet.pageObjects.ResponsibleGamblingPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResponsibleGamblingTests extends TestBase {
    private static final String LIMIT_AMOUNT = "500";
    private static final String EDITED_LIMIT_AMOUNT = "400";

    private ResponsibleGamblingPage responsibleGamblingPage;

    @BeforeMethod
    public void openResponsibleGamblingPage() {
        ProfilePage profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
        responsibleGamblingPage = profilePage
                .clickOnResponsibleGambling()
                .waitUntilPageOpened()
                .verifyPageTitle();
    }

    @Test(description = "Check the deposit limits section of the 'Responsible Gambling' page")
    public void checkDepositLimitsSection() {
        responsibleGamblingPage
                .verifyDepositLimitsSection();
    }

    @Test(description = "Open the 'Daily limit' setup modal")
    public void openDailyLimitModal() {
        responsibleGamblingPage
                .clickOnDailyLimitSetup()
                .verifyLimitsModal();
    }

    @Test(description = "Open the 'Weekly limit' setup modal")
    public void openWeeklyLimitModal() {
        responsibleGamblingPage
                .clickOnWeeklyLimitSetup()
                .verifyLimitsModal();
    }

    @Test(description = "Open the 'Monthly limit' setup modal")
    public void openMonthlyLimitModal() {
        responsibleGamblingPage
                .clickOnMonthlyLimitSetup()
                .verifyLimitsModal();
    }

    @Test(description = "Check that the limits input keeps the entered amount")
    public void checkEnteredLimitAmount() {
        responsibleGamblingPage
                .clickOnDailyLimitSetup()
                .verifyLimitsModal()
                .enterLimitAmount(LIMIT_AMOUNT)
                .verifyEnteredLimitAmount(LIMIT_AMOUNT);
    }

    @Test(description = "Close the 'Daily limit' setup modal by the 'Cancel' button")
    public void closeDailyLimitModalByCancel() {
        responsibleGamblingPage
                .clickOnDailyLimitSetup()
                .verifyLimitsModal()
                .clickOnCancelLimit()
                .verifyLimitsModalClosed();
    }

    @Test(description = "Open the confirmation dialog when a new daily limit is saved")
    public void openDailyLimitConfirmationDialog() {
        responsibleGamblingPage
                .clickOnDailyLimitSetup()
                .verifyLimitsModal()
                .enterLimitAmount(LIMIT_AMOUNT)
                .clickOnSaveLimit()
                .verifyLimitsConfirmationDialog();
    }

    @Test(description = "Decline the new daily limit in the confirmation dialog")
    public void declineDailyLimitInConfirmationDialog() {
        responsibleGamblingPage
                .clickOnDailyLimitSetup()
                .verifyLimitsModal()
                .enterLimitAmount(LIMIT_AMOUNT)
                .clickOnSaveLimit()
                .verifyLimitsConfirmationDialog()
                .declineLimitInDialog()
                .verifyLimitsConfirmationDialogClosed();
    }

    @Test(description = "Set a new daily deposit limit")
    public void setDailyLimit() {
        responsibleGamblingPage
                .clickOnDailyLimitSetup()
                .verifyLimitsModal()
                .enterLimitAmount(LIMIT_AMOUNT)
                .clickOnSaveLimit()
                .verifyLimitsConfirmationDialog()
                .confirmLimitInDialog()
                .verifySuccessfulLimitsDialog();
    }

    @Test(description = "Set a new weekly deposit limit")
    public void setWeeklyLimit() {
        responsibleGamblingPage
                .clickOnWeeklyLimitSetup()
                .verifyLimitsModal()
                .enterLimitAmount(LIMIT_AMOUNT)
                .clickOnSaveLimit()
                .verifyLimitsConfirmationDialog()
                .confirmLimitInDialog()
                .verifySuccessfulLimitsDialog();
    }

    @Test(description = "Set a new monthly deposit limit")
    public void setMonthlyLimit() {
        responsibleGamblingPage
                .clickOnMonthlyLimitSetup()
                .verifyLimitsModal()
                .enterLimitAmount(LIMIT_AMOUNT)
                .clickOnSaveLimit()
                .verifyLimitsConfirmationDialog()
                .confirmLimitInDialog()
                .verifySuccessfulLimitsDialog();
    }

    @Test(description = "Check the edit buttons of the deposit limits are shown")
    public void checkDepositLimitsEditButtons() {
        responsibleGamblingPage
                .verifyDepositLimitsEditButtons();
    }

    @Test(description = "Open the edit modal of the daily limit")
    public void openDailyLimitEditModal() {
        responsibleGamblingPage
                .clickOnDailyLimitEdit()
                .verifyLimitsModal();
    }

    @Test(description = "Open the edit modal of the weekly limit")
    public void openWeeklyLimitEditModal() {
        responsibleGamblingPage
                .clickOnWeeklyLimitEdit()
                .verifyLimitsModal();
    }

    @Test(description = "Open the edit modal of the monthly limit")
    public void openMonthlyLimitEditModal() {
        responsibleGamblingPage
                .clickOnMonthlyLimitEdit()
                .verifyLimitsModal();
    }

    @Test(description = "Leave the daily limit unchanged when the edit is cancelled")
    public void cancelDailyLimitEdit() {
        responsibleGamblingPage
                .clickOnDailyLimitEdit()
                .verifyLimitsModal()
                .enterLimitAmount(EDITED_LIMIT_AMOUNT)
                .verifyEnteredLimitAmount(EDITED_LIMIT_AMOUNT)
                .clickOnCancelLimit()
                .verifyLimitsModalClosed();
    }

    @Test(description = "Edit the daily deposit limit to a new amount")
    public void editDailyLimit() {
        responsibleGamblingPage
                .clickOnDailyLimitEdit()
                .verifyLimitsModal()
                .enterLimitAmount(EDITED_LIMIT_AMOUNT)
                .clickOnSaveLimit()
                .verifyLimitsConfirmationDialog()
                .confirmLimitInDialog()
                .verifySuccessfulLimitsDialog();
    }
}
