package io.maxbet.pageObjects;

import io.maxbet.Elements.Button;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.maxbet.DriverManager.getDriver;

public class PendingWdPage extends AbstractPage{
    private final By requestWdBtn = By.cssSelector(".btn-tertiary.md");
    private final By pendingWdRequest = By.cssSelector("mb-pending-withdrawal:nth-of-type(1)");
    private final By pendingWdRequests = By.cssSelector("mb-pending-withdrawal");
    private final By removePendingWdBtn = By.cssSelector("mb-pending-withdrawal:first-child button");
    private final By pendingWdNotification = By.cssSelector(".default-dialog.notification");
    private final By pendingWdNotificationContinue = By.cssSelector("button[data-fs-element='Profile.Withdrawals.BTN_RollbackConfirm']");
    private final By pendingWdNotificationCancel = By.cssSelector("mb-default-dialog .dialog-btns button.btn-transparent");

    @Getter
    Button RequestWdBtn = new Button(requestWdBtn, "The 'Request Withdrawal' button");
    @Getter
    TextField PendingWdRequest = new TextField(pendingWdRequest,"The withdrawal in pending");
    @Getter
    Button RemovePendingWdBtn = new Button(removePendingWdBtn, "The Remove button for pending withdrawal");
    @Getter
    TextField PendingWdNotification = new TextField(pendingWdNotification, "Pending WD notification dialog");
    @Getter
    Button PendingWdNotificationContinue = new Button(pendingWdNotificationContinue,"The Continue btn in the Pending WD notification dialog");
    @Getter
    Button PendingWdNotificationCancel = new Button(pendingWdNotificationCancel,"The Cancel btn in the Pending WD notification dialog");

    @Step("Wait until Wihtdraw page is opened")
    public void waitUntilPendingWdPageOpened() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("/pending-withdrawals"));
    }
    @Step("Count the withdrawals waiting in the list")
    public int getPendingWithdrawalsCount() {
        return getDriver().findElements(pendingWdRequests).size();
    }
    @Step("Wait until {expectedCount} withdrawal(s) are waiting in the list")
    public boolean waitUntilPendingWithdrawalsCountIs(int expectedCount) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                    .until(driver -> driver.findElements(pendingWdRequests).size() == expectedCount);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    @Step("Check at least one withdrawal is waiting in the list")
    public boolean hasPendingWithdrawal() {
        return getPendingWdRequest().isExists(5);
    }
    @Step("Click on the 'Request Withdrawal' button")
    public PendingWdPage clickOnRequestWd() {
        getRequestWdBtn().clickButton();
        return this;
    }
    @Step("Click on the 'Remove' button for pending WD")
    public void clickOnRemovePendingWdBtn(){
        getRemovePendingWdBtn().clickButton();
    }
    @Step("Click on the Continue btn in the Pending WD notification dialog")
    public void clickContinueBtnForPendingWdDialog(){
        getPendingWdNotificationContinue().clickButton();
    }
    @Step("Click on the Continue btn in the Pending WD notification dialog")
    public void clickCancelBtnForPendingWdDialog(){
        getPendingWdNotificationCancel().clickButton();
    }
}
