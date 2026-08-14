package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
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
import static io.maxbet.DriverManager.getDriver;

public class DepositPage extends BaseElement {
    private final By depositPageTitle = By.cssSelector("h2[class='content']");
    private final By backBtn = By.cssSelector(".mb-nav-back__link");
    private final By bankCardPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge\"}']");
    private final By amountSelector1 = By.cssSelector("mb-number-input > div > div > div:first-child");
    private final By amountSelector2 = By.cssSelector("mb-number-input div > div:nth-child(2)");
    private final By amountSelector3 = By.cssSelector("mb-number-input div > div:nth-child(3)");
    private final By cardAmountInput = By.cssSelector("mb-number-input input");
    private final By amountWarningMessage = By.cssSelector(".error-message.ng-star-inserted");
    private final By firstCardToggle = By.cssSelector("mb-user-cards-list mb-bank-card:first-child mb-switch");
    private final By cvvInput = By.cssSelector("#_cvc");
    private final By makeDepositBtn = By.cssSelector("[data-fs-element='Deposit.Card.BTN_Submit']");
    private final By successDepositPopUp = By.cssSelector("[id^='mat-mdc-dialog-']");
    private final By toTheLobbyBtn = By.cssSelector("button[type='submit']");
    private final By abonPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_Abon\"}']");
    private final By paySafePM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_PaySafeCard\"}']");
    private final By externalCashierPM = By.cssSelector("li[data-fs-properties='{\"method\":\"ExternalCashier\"}']");
    private final By googlePayPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_GooglePay\"}']");
    private final By oktoCashPM = By.cssSelector("li[data-fs-properties='{\"method\":\"OktoCash\"}']");
    private final By airCashPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_AirCash\"}']");
//    private final By airCashMarketPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_AirCash_App\"}']");
    private final By skrillPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_Skrill\"}']");
//    private final By smithPM = By.cssSelector("li[data-fs-properties='{\"method\":\"Smith&Smith\"}']");
    private final By netellerPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_Neteller\"}']");
    private final By pageTitleOfPM = By.cssSelector(".mb-page-title");

    @Getter
    TextField DepositPageTitle = new TextField(depositPageTitle, "The 'Deposit' page title");
    @Getter
    Button BackBtn = new Button(backBtn, "The 'Back' button");
    @Getter
    Button BankCardPM = new Button(bankCardPM, "The 'Visa & MasterCard & Maestro' payment method");
    @Getter
    Button AmountSelector1 = new Button(amountSelector1, "First amount selector on the Deposit page");
    @Getter
    Button AmountSelector2 = new Button(amountSelector2,"Second amount selector on the Deposit page");
    @Getter
    Button AmountSelector3 = new Button(amountSelector3, "Third amount selector on the Deposit page");
    @Getter
    InputField CardAmountInput = new InputField(cardAmountInput,"Input field for the sum amount");
    @Getter
    TextField AmountWarningMessage = new TextField(amountWarningMessage,"Warning message for the deposit sum limit ' Maximum deposit should be 500000 RON '");
    @Getter
    Button FirstCardToggle = new Button(firstCardToggle,"Toggle for the first card for the Deposit page via Visa PM");
    @Getter
    InputField CvvInput = new InputField(cvvInput,"The CVV input field");
    @Getter
    Button MakeDepositBtn = new Button(makeDepositBtn,"The 'Make Deposit' button");
    @Getter
    TextField SuccessDepositPopUp = new TextField(successDepositPopUp,"The deposit was successfully completed");
    @Getter
    Button ToTheLobbyBtn = new Button(toTheLobbyBtn, "The 'To the Lobby' button from the success deposit pop up");
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
//    @Getter
//    Button AirCashMarketPM = new Button(airCashMarketPM, "The 'AirCashMarket' payment method");
    @Getter
    Button SkrillPM = new Button(skrillPM, "The 'Skrill' payment method");
//    @Getter
//    Button SmithPM = new Button(smithPM, "The 'Smith&Smith' payment method");
    @Getter
    Button NetellerPM = new Button(netellerPM, "The 'Neteller' payment method");
    @Getter
    TextField PageTitleOfPM = new TextField(pageTitleOfPM, "The 'Page Title' of the payment method");

    @Step("Click on the 'Back' button if present")
    public void clickOnBackBtnDeposit() {
        try {
            WebDriverWait wait = new WebDriverWait(
                    getDriver(),
                    Duration.ofSeconds(10)
            );
            // Wait until loading mask disappears
            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.cssSelector(".mask")
                    )
            );
            // Find the button again after the mask disappears
            WebElement button = wait.until(
                    ExpectedConditions.elementToBeClickable(backBtn)
            );
            button.click();
            Assert.assertTrue(
                    getDriver()
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
    public DepositPage clickOnBankCardPM() {
        getBankCardPM().clickButton();
        return this;
    }
    @Step ("Click on the first sum selector on the Deposit page")
    public void clickOnFirstAmountSelector(){
        getAmountSelector1().click();
    }
    @Step("Click on the toggle for the first card")
    public DepositPage clickOnFirstCardToggle(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(firstCardToggle))
                .click();
        return this;
    }
    @Step("Enter the sum into amount input")
    public void enterTheSum(String amount){
        getCardAmountInput().setText(amount);
    }
    @Step("Enter the code into CVV input")
    public void enterCvvCode(String cvvCode){
        WebDriverWait wait = new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(10)
        );
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(cvvInput)
        );
        input.click();
        input.sendKeys(cvvCode);
    }
    @Step("Click on the 'Deposit' button to complete the deposit")
    public DepositPage clickOnMakeDepositBtn() {
        getMakeDepositBtn().clickButton();
        return this;
    }
    @Step("Click on the 'To the Lobby' button")
    public DepositPage clickToTheLobbyBtn(){
        getToTheLobbyBtn().clickButton();
        return this;
    }
    @Step("Click on the 'Abon' button")
    public DepositPage clickOnAbonPM() {
        getAbonPM().clickButton();
        return this;
    }
    @Step("Click on the 'PaySafeCard' button")
    public DepositPage clickOnPaySafePM() {
        getPaySafePM().clickButton();
        return this;
    }
    @Step("Click on the 'ExternalCashier' button")
    public DepositPage clickOnExternalCashierPM() {
        getExternalCashierPM().clickButton();
        return this;
    }
    @Step("Click on the 'GooglePay' button")
    public DepositPage clickOnGooglePayPM() {
        getGooglePayPM().clickButton();
        return this;
    }
    @Step("Click on the 'OktoCash' button")
    public DepositPage clickOnOktoCashPM() {
        getOktoCashPM().clickButton();
        return this;
    }
    @Step("Click on the 'AirCash' button")
    public DepositPage clickOnAirCashPM() {
        getAirCashPM().clickButton();
        return this;
    }
//    @Step("Click on the 'AirCashMarket' button")
//    public DepositPage clickOnAirCashMarketPM() {
//        getAirCashMarketPM().clickButton();
//        return this;
//    }
    @Step("Click on the 'Skrill' button")
    public DepositPage clickOnSkrillPM() {
        getSkrillPM().clickButton();
        return this;
    }
//    @Step("Click on the 'Smith&Smith' button")
//    public DepositPage clickOnSmithPM() {
//        getSmithPM().clickButton();
//        return this;
//    }
    @Step("Click on the 'Neteller' button")
    public DepositPage clickOnNetellerPM() {
        getNetellerPM().clickButton();
        return this;
    }

    @Step("Check the payment method page '{expectedUrlPath}' is opened")
    public boolean isPmPageOpened(String expectedUrlPath) {
        // Routing is async, so the URL has to be waited for rather than read once.
        // Matched on a path boundary, not "contains": "/aircash" must not accept
        // "/aircash-market", otherwise the wrong page would still pass.
        if (!waitForUrlPathEndingWith(expectedUrlPath)) {
            return false;
        }
        return getPageTitleOfPM().isExists();
    }

    @Step("Verify the payment method page '{expectedUrlPath}' is opened")
    public DepositPage verifyPmPageOpened(String expectedUrlPath) {
        Assert.assertTrue(
                isPmPageOpened(expectedUrlPath),
                "Wrong payment method page is opened. Expected the URL to end with '"
                        + expectedUrlPath + "' but it was '" + getDriver().getCurrentUrl() + "'"
        );
        return this;
    }

    /**
     * Clicks the 'Back' link, which returns from a payment method to the list of payment
     * methods. Returns false instead of throwing so that a caller checking every payment
     * method in one test can report this method and carry on with the rest. Falls back to
     * browser history because a failure to go back would otherwise strand every remaining
     * payment method on the wrong page.
     */
    @Step("Return to the payment methods list with the 'Back' button")
    public boolean returnToPaymentMethodsList() {
        try {
            waitPageStability();
            getBackBtn().clickButton();
        } catch (RuntimeException e) {
            // Recovery below decides whether this actually mattered.
        }
        if (isOnPaymentMethodsList()) {
            return true;
        }
        getDriver().navigate().back();
        return isOnPaymentMethodsList();
    }

    @Step("Check the payment methods list is shown")
    public boolean isOnPaymentMethodsList() {
        // A payment method page is "<deposit url>/<method>", so the list is the only
        // page whose path ends with "/deposit".
        return waitForUrlPathEndingWith("/deposit") && getDepositPageTitle().isExists();
    }

    private boolean waitForUrlPathEndingWith(String expectedUrlPath) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                    .until(driver -> stripQueryAndTrailingSlash(driver.getCurrentUrl())
                            .endsWith(expectedUrlPath));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private static String stripQueryAndTrailingSlash(String url) {
        String path = url.split("[?#]")[0];
        return path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
    }

    public boolean isDepositPageOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(depositPageTitle));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
