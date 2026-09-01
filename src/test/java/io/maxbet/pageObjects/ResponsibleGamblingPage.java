package io.maxbet.pageObjects;

import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static io.maxbet.DriverManager.getDriver;

public class ResponsibleGamblingPage extends AbstractPage{
    private final By responsibleGamblingPageTitle = By.xpath("//h2[normalize-space()='Deposit limits EN']");
    //Limits
    private final By dailyLimitTitle = By.cssSelector("mb-deposit-limits ul li:first-child h3");
    private final By dailyLimitSetupButton = By.cssSelector("button[data-fs-element='Profile.ResponsibleGambling.BTN_EditDaily']");
    private final By weeklyLimitTitle = By.cssSelector("mb-deposit-limits ul li:nth-child(2) h3");
    private final By weeklyLimitSetupButton = By.cssSelector("button[data-fs-element='Profile.ResponsibleGambling.BTN_EditWeekly']");
    private final By monthlyLimitTitle = By.cssSelector("mb-deposit-limits ul li:nth-child(3) h3");
    private final By monthlyLimitSetupButton = By.cssSelector("button[data-fs-element='Profile.ResponsibleGambling.BTN_EditMonthly']");
    private final By limitsModal = By.cssSelector(".deposit-limits");
    private final By limitsInput = By.id("limit");
    private final By limitsCancelBtn = By.xpath("//button[normalize-space()='Cancel']");
    private final By limitsSaveBtn = By.xpath("//button[normalize-space()='Save']");
    private final By limitsDialogModal = By.cssSelector(".mat-mdc-dialog-surface.mdc-dialog__surface");
    private final By limitsDialogCancelBtn = By.cssSelector(".mb-button.btn-transparent.lg");
    private final By limitsDialogSaveBtn = By.cssSelector(".mb-button.btn-primary.lg");
    private final By successfulLimitsDialogModal = By.cssSelector(".default-dialog.success");
    private final By dailyLimitEditButton = By.cssSelector("button[data-fs-element='Profile.ResponsibleGambling.BTN_EditDaily']");
    private final By weeklyLimitEditButton = By.cssSelector("button[data-fs-element='Profile.ResponsibleGambling.BTN_EditWeekly']");
    private final By monthlyLimitEditButton = By.cssSelector("button[data-fs-element='Profile.ResponsibleGambling.BTN_EditMonthly']");


    @Getter
    TextField ResponsibleGamblingPageTitle = new TextField(responsibleGamblingPageTitle, "The 'Responsible Gambling' page title");
    //Limits
    @Getter
    TextField DailyLimitTitle = new TextField(dailyLimitTitle, "The 'Daily Limit' title");
    @Getter
    Button DailyLimitSetupButton = new Button(dailyLimitSetupButton, "The 'Daily Limit' setup button");
    @Getter
    TextField WeeklyLimitTitle = new TextField(weeklyLimitTitle, "The 'Weekly Limit' title");
    @Getter
    Button WeeklyLimitSetupButton = new Button(weeklyLimitSetupButton, "The 'Weekly Limit' setup button");
    @Getter
    TextField MonthlyLimitTitle = new TextField(monthlyLimitTitle, "The 'Monthly Limit' title");
    @Getter
    Button MonthlyLimitSetupButton = new Button(monthlyLimitSetupButton, "The 'Monthly Limit' setup button");
    @Getter
    TextField LimitsModal = new TextField(limitsModal, "The 'Limits' modal");
    @Getter
    InputField LimitsInput = new InputField(limitsInput, "The 'Limits' input");
    @Getter
    Button LimitsCancelBtn = new Button(limitsCancelBtn, "The 'Limits' cancel button");
    @Getter
    Button LimitsSaveBtn = new Button(limitsSaveBtn, "The 'Limits' save button");
    @Getter
    TextField LimitsDialogModal = new TextField(limitsDialogModal, "The 'Limits' dialog modal");
    @Getter
    Button LimitsDialogCancelBtn = new Button(limitsDialogCancelBtn, "The 'Limits' dialog cancel button");
    @Getter
    Button LimitsDialogSaveBtn = new Button(limitsDialogSaveBtn, "The 'Limits' dialog save button");
    @Getter
    TextField SuccessfulLimitsDialogModal = new TextField(successfulLimitsDialogModal, "The 'Successful Limits' dialog modal");
    @Getter
    Button DailyLimitEditButton = new Button(dailyLimitEditButton, "The 'Daily Limit' edit button");
    @Getter
    Button WeeklyLimitEditButton = new Button(weeklyLimitEditButton, "The 'Weekly Limit' edit button");
    @Getter
    Button MonthlyLimitEditButton = new Button(monthlyLimitEditButton, "The 'Monthly Limit' edit button");

    @Step("Wait until the 'Responsible Gambling' page is opened")
    public ResponsibleGamblingPage waitUntilPageOpened() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("/responsible-gambling"));
        return this;
    }
    @Step("Verify the 'Responsible Gambling' page title is shown")
    public ResponsibleGamblingPage verifyPageTitle() {
        getResponsibleGamblingPageTitle().verify();
        return this;
    }
    @Step("Verify the deposit limits section is shown")
    public ResponsibleGamblingPage verifyDepositLimitsSection() {
        getDailyLimitTitle().verify();
        getDailyLimitSetupButton().verify();
        getWeeklyLimitTitle().verify();
        getWeeklyLimitSetupButton().verify();
        getMonthlyLimitTitle().verify();
        getMonthlyLimitSetupButton().verify();
        return this;
    }
    @Step("Click on the 'Daily limit' setup button")
    public ResponsibleGamblingPage clickOnDailyLimitSetup() {
        ResponsibleGamblingPageTitle.waitPageStability();
        getDailyLimitSetupButton().clickButton();
        return this;
    }
    @Step("Click on the 'Weekly limit' setup button")
    public ResponsibleGamblingPage clickOnWeeklyLimitSetup() {
        ResponsibleGamblingPageTitle.waitPageStability();
        getWeeklyLimitSetupButton().clickButton();
        return this;
    }
    @Step("Click on the 'Monthly limit' setup button")
    public ResponsibleGamblingPage clickOnMonthlyLimitSetup() {
        ResponsibleGamblingPageTitle.waitPageStability();
        getMonthlyLimitSetupButton().clickButton();
        return this;
    }
    @Step("Verify the limits modal is shown")
    public ResponsibleGamblingPage verifyLimitsModal() {
        getLimitsModal().verify();
        getLimitsInput().verify();
        getLimitsCancelBtn().verify();
        getLimitsSaveBtn().verify();
        return this;
    }
    @Step("Enter '{amount}' into the limits input")
    public ResponsibleGamblingPage enterLimitAmount(String amount) {
        getLimitsInput().clear();
        getLimitsInput().setText(amount);
        return this;
    }
    @Step("Verify the limits input keeps the amount '{expectedAmount}'")
    public ResponsibleGamblingPage verifyEnteredLimitAmount(String expectedAmount) {
        Assert.assertEquals(getLimitsInput().getValue(), expectedAmount,
                "The limits input does not keep the entered amount");
        return this;
    }
    @Step("Click on the 'Save' button in the limits modal")
    public ResponsibleGamblingPage clickOnSaveLimit() {
        getLimitsSaveBtn().clickButton();
        return this;
    }
    @Step("Click on the 'Cancel' button in the limits modal")
    public ResponsibleGamblingPage clickOnCancelLimit() {
        getLimitsCancelBtn().clickButton();
        return this;
    }
    @Step("Verify the limits modal is closed")
    public ResponsibleGamblingPage verifyLimitsModalClosed() {
        Assert.assertTrue(getLimitsModal().invisibilityOfElementLocated(),
                "The 'Limits' modal is still shown");
        return this;
    }
    @Step("Verify the limits confirmation dialog is shown")
    public ResponsibleGamblingPage verifyLimitsConfirmationDialog() {
        getLimitsDialogModal().verify();
        getLimitsDialogCancelBtn().verify();
        getLimitsDialogSaveBtn().verify();
        return this;
    }
    @Step("Confirm the new limit in the confirmation dialog")
    public ResponsibleGamblingPage confirmLimitInDialog() {
        getLimitsDialogSaveBtn().clickButton();
        return this;
    }
    @Step("Decline the new limit in the confirmation dialog")
    public ResponsibleGamblingPage declineLimitInDialog() {
        getLimitsDialogCancelBtn().clickButton();
        return this;
    }
    @Step("Verify the limits confirmation dialog is closed")
    public ResponsibleGamblingPage verifyLimitsConfirmationDialogClosed() {
        Assert.assertTrue(getLimitsDialogModal().invisibilityOfElementLocated(),
                "The 'Limits' dialog modal is still shown");
        return this;
    }
    @Step("Verify the successful limits dialog is shown")
    public ResponsibleGamblingPage verifySuccessfulLimitsDialog() {
        getSuccessfulLimitsDialogModal().verify();
        return this;
    }
    /*
     * Editing an already set limit. The three buttons below carry the same 'data-fs-element' as the
     * setup buttons above - the page renders one button per limit whose label reads 'Edit' once an
     * amount is stored - so these steps drive the same node under the name the UI shows.
     */
    @Step("Verify the edit buttons of the deposit limits are shown")
    public ResponsibleGamblingPage verifyDepositLimitsEditButtons() {
        getDailyLimitEditButton().verify();
        getWeeklyLimitEditButton().verify();
        getMonthlyLimitEditButton().verify();
        return this;
    }
    @Step("Click on the 'Edit' button of the daily limit")
    public ResponsibleGamblingPage clickOnDailyLimitEdit() {
        ResponsibleGamblingPageTitle.waitPageStability();
        getDailyLimitEditButton().clickButton();
        return this;
    }
    @Step("Click on the 'Edit' button of the weekly limit")
    public ResponsibleGamblingPage clickOnWeeklyLimitEdit() {
        ResponsibleGamblingPageTitle.waitPageStability();
        getWeeklyLimitEditButton().clickButton();
        return this;
    }
    @Step("Click on the 'Edit' button of the monthly limit")
    public ResponsibleGamblingPage clickOnMonthlyLimitEdit() {
        ResponsibleGamblingPageTitle.waitPageStability();
        getMonthlyLimitEditButton().clickButton();
        return this;
    }
}
