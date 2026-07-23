package io.maxbet.pageObjects;

import io.maxbet.DriverManager;
import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DepositPage extends BaseElement {
    private final By depositPageTitle = By.cssSelector("h2[class='content']");
    private final By backBtnDeposit = By.cssSelector(".mb-nav-back__link");
    private final By backButton = By.cssSelector(".mb-nav-back__link");
    private final By bankCardPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge\"}']");
    private final By abonPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_Abon\"}']");
    private final By paySafePM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_PaySafeCard\"}']");
    private final By externalCashierPM = By.cssSelector("li[data-fs-properties='{\"method\":\"ExternalCashier\"}']");
    private final By googlePayPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_GooglePay\"}']");
    private final By oktoCashPM = By.cssSelector("li[data-fs-properties='{\"method\":\"OktoCash\"}']");
    private final By airCashPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_AirCash\"}']");
    private final By airCashMarketPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_AirCash_App\"}']");
    private final By skrillPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_Skrill\"}']");
    private final By smithPM = By.cssSelector("li[data-fs-properties='{\"method\":\"Smith&Smith\"}']");
    private final By netellerPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_Neteller\"}']");
    private final By pageTitleOfPM = By.cssSelector(".mb-page-title");

    @Getter
    TextField DepositPageTitle = new TextField(depositPageTitle, "The 'Deposit' page title");
    @Getter
    Button BackBtnDeposit = new Button(backBtnDeposit, "The 'Back' button");
    @Getter
    Button BackButton = new Button(backButton, "The 'Back' button");
    @Getter
    Button BankCardPM = new Button(bankCardPM, "The 'Visa & MasterCard & Maestro' payment method");
    @Getter
    Button AbonPM = new Button(abonPM, "The 'Abon' payment method");
    @Getter
    Button PaySafePM = new Button(paySafePM, "The 'PaySafeCard' payment method");
    @Getter
    Button ExternalCashierPM = new Button(externalCashierPM, "The 'ExternalCashier' payment method");
    @Getter
    Button GooglePayPM = new Button(googlePayPM, "The 'GooglePay' payment method");
    @Getter
    Button OktoCashPM = new Button(oktoCashPM, "The 'OktoCash' payment method");
    @Getter
    Button AirCashPM = new Button(airCashPM, "The 'AirCash' payment method");
    @Getter
    Button AirCashMarketPM = new Button(airCashMarketPM, "The 'AirCashMarket' payment method");
    @Getter
    Button SkrillPM = new Button(skrillPM, "The 'Skrill' payment method");
    @Getter
    Button SmithPM = new Button(smithPM, "The 'Smith&Smith' payment method");
    @Getter
    Button NetellerPM = new Button(netellerPM, "The 'Neteller' payment method");
    @Getter
    TextField PageTitleOfPM = new TextField(pageTitleOfPM, "The 'Page Title' of the payment method");

    @Step("Click on the 'Back' button if present")
    public void clickOnBackBtnDeposit() {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(5)
            );
            WebElement button = wait.until(
                    ExpectedConditions.elementToBeClickable(backBtnDeposit)
            );
            button.click();
            Assert.assertTrue(
                    DriverManager.getDriver()
                            .getCurrentUrl()
                            .contains("profile/deposit"),
                    "User was not redirected to deposit page"
            );
        } catch (TimeoutException e) {
            System.out.println(
                    "Back button is not displayed. Step skipped."
            );
        }
    }
    @Step("Click on the 'Visa & MasterCard & Maestro' button")
    public void clickOnBankCardPM() {
        getBankCardPM().clickButton();
    }
    @Step("Click on the 'Abon' button")
    public void clickOnAbonPM() {
        getAbonPM().clickButton();
    }
    @Step("Click on the 'PaySafeCard' button")
    public DepositPage clickOnPaySafePM() {
        getPaySafePM().clickButton();
        return this;
    }
    @Step("Click on the 'ExternalCashier' button")
    public void clickOnExternalCashierPM() {
        getExternalCashierPM().clickButton();
    }
    @Step("Click on the 'GooglePay' button")
    public void clickOnGooglePayPM() {
        getGooglePayPM().clickButton();
    }
    @Step("Click on the 'OktoCash' button")
    public void clickOnOktoCashPM() {
        getOktoCashPM().clickButton();
    }
    @Step("Click on the 'AirCash' button")
    public void clickOnAirCashPM() {
        getAirCashPM().clickButton();
    }
    @Step("Click on the 'AirCashMarket' button")
    public void clickOnAirCashMarketPM() {
        getAirCashMarketPM().clickButton();
    }
    @Step("Click on the 'Skrill' button")
    public void clickOnSkrillPM() {
        getSkrillPM().clickButton();
    }
    @Step("Click on the 'Smith&Smith' button")
    public void clickOnSmithPM() {
        getSmithPM().clickButton();
    }
    @Step("Click on the 'Neteller' button")
    public void clickOnNetellerPM() {
        getNetellerPM().clickButton();
    }

    public boolean isDepositPageOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(depositPageTitle));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
