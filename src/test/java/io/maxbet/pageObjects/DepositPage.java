package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.math.BigDecimal;
import java.time.Duration;
import static io.maxbet.DriverManager.getDriver;

public class DepositPage extends BaseElement {
    private final By depositPageTitle = By.cssSelector("h2[class='content']");
    private final By amountBalance = By.cssSelector("div.right-block.notific-padding span.amount");
    private final By backBtn = By.cssSelector(".mb-nav-back__link");
    private final By bankCardPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge\"}']");
    private static final String AMOUNT_SELECTOR_CSS = "mb-number-input > div > div > div:nth-child(%d)";
    public static final int AMOUNT_SELECTOR_COUNT = 3;
    private static final int CARD_TOGGLE_ATTEMPTS = 2;
    private static final long BALANCE_POLL_MILLIS = 500;
    private static final int BALANCE_STABLE_READS = 3;
    private final By cardAmountInput = By.cssSelector("mb-number-input input");
    private final By amountWarningMessage = By.cssSelector(".error-message.ng-star-inserted");
    private final By firstCardToggle = By.cssSelector("mb-user-cards-list mb-bank-card:first-child mb-switch label.switch-control");
    private final By firstCardCheckbox = By.cssSelector("mb-user-cards-list mb-bank-card:first-child mb-switch input.hidden-input");
    private final By cvvInput = By.cssSelector("#_cvc");
    private final By makeDepositBtn = By.cssSelector("[data-fs-element='Deposit.Card.BTN_Submit']");
    private final By successDepositPopUp = By.cssSelector("[id^='mat-mdc-dialog-']");
    private final By toTheLobbyBtn = By.cssSelector("[id^='mat-mdc-dialog-'] button[type='submit']");
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
    TextField AmountBalance = new TextField(amountBalance, "The account balance in the header");
    @Getter
    Button BackBtn = new Button(backBtn, "The 'Back' button");
    @Getter
    Button BankCardPM = new Button(bankCardPM, "The 'Visa & MasterCard & Maestro' payment method");
    @Getter
    InputField CardAmountInput = new InputField(cardAmountInput,"Input field for the sum amount");
    @Getter
    TextField AmountWarningMessage = new TextField(amountWarningMessage,"Warning message for the deposit sum limit ' Maximum deposit should be 500000 RON '");
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

    private static By amountSelectorLocator(int index) {
        return By.cssSelector(String.format(AMOUNT_SELECTOR_CSS, index));
    }

    /**
     * The deposit page opens on the payment methods list, but it re-opens the last used payment
     * method when the account has one, so the entry point has to be normalised before a test can
     * pick a method. Fails loudly when the list cannot be reached - every test in the class needs
     * it, so swallowing that would only turn one clear failure into a pile of confusing ones.
     */
    @Step("Open the payment methods list of the 'Deposit' page")
    public DepositPage openPaymentMethodsList() {
        waitUntilUrlContains("profile/deposit");
        waitPageStability();
        if (currentPathEndsWith("/deposit")) {
            getDepositPageTitle().verify();
            return this;
        }
        Assert.assertTrue(
                returnToPaymentMethodsList(),
                "The 'Deposit' page opened on '" + getCurrentUrl()
                        + "' and the payment methods list could not be reached from there"
        );
        return this;
    }
    @Step("Click on the 'Back' button")
    public DepositPage clickOnBackBtnDeposit() {
        waitPageStability();
        getBackBtn().clickButton();
        return this;
    }
    @Step("Click on the 'Visa & MasterCard & Maestro' button")
    public DepositPage clickOnBankCardPM() {
        getBankCardPM().clickButton();
        return this;
    }
    @Step ("Click on the first sum selector on the Deposit page")
    public DepositPage clickOnFirstAmountSelector(){
        return clickOnAmountSelector(1);
    }
    @Step("Click on the sum selector #{index} on the Deposit page")
    public DepositPage clickOnAmountSelector(int index){
        amountSelector(index).click();
        return this;
    }
    @Step("Read the label of the sum selector #{index}")
    public String getAmountSelectorLabel(int index){
        return amountSelector(index).getText().trim();
    }
    private Button amountSelector(int index) {
        if (index < 1 || index > AMOUNT_SELECTOR_COUNT) {
            throw new IllegalArgumentException(
                    "There are only " + AMOUNT_SELECTOR_COUNT + " amount selectors, asked for #" + index);
        }
        return new Button(amountSelectorLocator(index), "Amount selector #" + index + " on the Deposit page");
    }
    /**
     * Selects the first saved card and waits until the form agrees that it is selected. Picking an
     * amount re-renders the card list, and a click that lands on the old nodes is simply lost -
     * which used to surface much later as a missing CVV field - so the click is repeated until the
     * hidden checkbox reports the card as chosen.
     */
    @Step("Click on the toggle for the first card")
    public DepositPage clickOnFirstCardToggle(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        for (int attempt = 1; attempt <= CARD_TOGGLE_ATTEMPTS; attempt++) {
            WebElement toggle = wait.until(ExpectedConditions.visibilityOfElementLocated(firstCardToggle));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", toggle);
            try {
                wait.until(ExpectedConditions.elementSelectionStateToBe(firstCardCheckbox, true));
                return this;
            } catch (TimeoutException | StaleElementReferenceException e) {
                if (attempt == CARD_TOGGLE_ATTEMPTS) {
                    throw new AssertionError(
                            "The first saved card stayed unselected after " + CARD_TOGGLE_ATTEMPTS
                                    + " clicks on its toggle", e);
                }
            }
        }
        return this;
    }
    @Step("Check the first saved card is selected")
    public boolean isFirstCardSelected() {
        try {
            return getDriver().findElement(firstCardCheckbox).isSelected();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    /**
     * Replaces whatever the amount input holds instead of appending to it: the field is
     * pre-filled once an amount selector is used, and sendKeys alone would concatenate the
     * old and the new amount.
     */
    @Step("Enter the sum '{amount}' into amount input")
    public DepositPage enterTheSum(String amount){
        InputField input = getCardAmountInput();
        input.clear();
        input.setText(amount);
        return this;
    }
    @Step("Read the entered deposit amount")
    public String getEnteredAmount(){
        return getDriver().findElement(cardAmountInput).getAttribute("value");
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
    @Step("Check the 'Deposit' button can be pressed")
    public boolean isMakeDepositBtnEnabled() {
        try {
            WebElement button = getDriver().findElement(makeDepositBtn);
            String disabledAttribute = button.getAttribute("disabled");
            String classes = button.getAttribute("class");
            return button.isEnabled()
                    && disabledAttribute == null
                    && (classes == null || !classes.contains("disabled"));
        } catch (NoSuchElementException e) {
            // No submit button rendered yet - the deposit cannot be sent either way.
            return false;
        }
    }
    /**
     * The form rejects an invalid amount in one of two ways depending on the field and the
     * amount: it either shows the inline error or just keeps the submit button disabled. Both
     * mean "this deposit cannot be sent", which is what a negative test asserts.
     */
    @Step("Check the deposit cannot be submitted")
    public boolean isDepositBlocked() {
        return getAmountWarningMessage().isExists(5) || !isMakeDepositBtnEnabled();
    }
    @Step("Verify the amount warning message contains '{expectedFragment}'")
    public DepositPage verifyAmountWarningContains(String expectedFragment) {
        String actualMessage = getAmountWarningText();
        Assert.assertTrue(
                actualMessage.contains(expectedFragment),
                "Expected the amount warning message to contain '" + expectedFragment
                        + "' but it was '" + actualMessage + "'"
        );
        return this;
    }
    @Step("Read the amount warning message")
    public String getAmountWarningText() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(amountWarningMessage))
                .getText()
                .trim();
    }
    @Step("Check the amount warning message is shown")
    public boolean isAmountWarningShown(long... timeout) {
        return getAmountWarningMessage().isExists(timeout);
    }
    @Step("Verify the success deposit pop-up is shown")
    public DepositPage verifySuccessDepositPopUp() {
        Assert.assertTrue(
                getSuccessDepositPopUp().isExists(20),
                "The success deposit pop-up is not visible after submitting the deposit"
        );
        return this;
    }
    @Step("Click on the 'To the Lobby' button")
    public LobbyPage clickToTheLobbyBtn(){
        getToTheLobbyBtn().clickButton();
        getSuccessDepositPopUp().invisibilityOfElementLocated();
        return new LobbyPage();
    }
    @Step("Read the account balance from the header")
    public BigDecimal getBalance() {
        try {
            return new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                    .ignoring(StaleElementReferenceException.class)
                    .until(driver -> parseAmount(driver.findElement(amountBalance).getText()));
        } catch (TimeoutException e) {
            throw new AssertionError(
                    "The account balance could not be read from the header, it reads '"
                            + describeBalanceNodes() + "'", e);
        }
    }
    /**
     * Waits until the header balance both moved away from {@code previousBalance} and stopped
     * moving. Returning on the first change was not enough: the balance arrives as a server push
     * and more than one can land around a deposit, so the first new figure read can be an
     * intermediate one. On a timeout it returns the last figure read instead of throwing - the
     * caller compares it against what it expected and reports both, which says far more than a
     * bare timeout would.
     */
    @Step("Wait until the account balance settles after changing from '{previousBalance}'")
    public BigDecimal waitForBalanceToSettle(BigDecimal previousBalance, long timeoutSeconds) {
        long deadline = System.currentTimeMillis() + Duration.ofSeconds(timeoutSeconds).toMillis();
        BigDecimal lastReading = previousBalance;
        int stableReads = 0;
        while (System.currentTimeMillis() < deadline) {
            BigDecimal reading = getBalance();
            if (reading.compareTo(lastReading) != 0) {
                lastReading = reading;
                stableReads = 0;
            } else if (reading.compareTo(previousBalance) != 0
                    && ++stableReads >= BALANCE_STABLE_READS) {
                return reading;
            }
            pause(BALANCE_POLL_MILLIS);
        }
        return lastReading;
    }
    /**
     * Waits until the header balance stops moving, whatever it reads. Used before a deposit: a
     * balance that is still settling from earlier activity would otherwise be captured mid-flight
     * and every later comparison against it would be off.
     */
    @Step("Wait until the account balance settles")
    public BigDecimal waitForBalanceToSettle(long timeoutSeconds) {
        long deadline = System.currentTimeMillis() + Duration.ofSeconds(timeoutSeconds).toMillis();
        BigDecimal lastReading = getBalance();
        int stableReads = 1;
        while (System.currentTimeMillis() < deadline) {
            pause(BALANCE_POLL_MILLIS);
            BigDecimal reading = getBalance();
            if (reading.compareTo(lastReading) == 0) {
                if (++stableReads >= BALANCE_STABLE_READS) {
                    return reading;
                }
            } else {
                lastReading = reading;
                stableReads = 1;
            }
        }
        return lastReading;
    }
    @Step("Read the amount the deposit form is about to submit")
    public BigDecimal getEnteredAmountAsNumber() {
        String enteredAmount = getEnteredAmount();
        BigDecimal amount = parseAmount(enteredAmount);
        Assert.assertNotNull(
                amount,
                "The amount input holds no number, it reads '" + enteredAmount + "'"
        );
        return amount;
    }
    /**
     * Lists every node the balance locator matches, for a failure message. The header holds more
     * than one figure on some accounts - real money, bonus, total - and a comparison that silently
     * read a different node before and after a deposit is worth spotting straight away.
     */
    public String describeBalanceNodes() {
        StringBuilder nodes = new StringBuilder();
        for (WebElement node : getDriver().findElements(amountBalance)) {
            try {
                nodes.append(nodes.length() == 0 ? "" : " | ").append(node.getText());
            } catch (StaleElementReferenceException e) {
                nodes.append(nodes.length() == 0 ? "" : " | ").append("<stale>");
            }
        }
        return nodes.length() == 0 ? "<no matching node>" : nodes.toString();
    }
    /**
     * Reads an amount like '1.234,56 RON' or '1,234.56 RON' without guessing the site locale: the
     * last separator is the decimal one only when one or two digits follow it, every other
     * separator groups thousands. Returns null when the text carries no number at all, so a
     * caller polling a header that renders empty for a moment can simply read again.
     */
    static BigDecimal parseAmount(String amountText) {
        String figure = amountText == null ? "" : amountText.replaceAll("[^0-9.,]", "");
        if (figure.isEmpty() || figure.replaceAll("[.,]", "").isEmpty()) {
            return null;
        }
        int lastSeparator = Math.max(figure.lastIndexOf('.'), figure.lastIndexOf(','));
        int decimals = figure.length() - lastSeparator - 1;
        if (lastSeparator < 0 || decimals < 1 || decimals > 2) {
            return new BigDecimal(figure.replaceAll("[.,]", ""));
        }
        return new BigDecimal(figure.substring(0, lastSeparator).replaceAll("[.,]", "")
                + "." + figure.substring(lastSeparator + 1));
    }
    private static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for the balance", e);
        }
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

    private boolean currentPathEndsWith(String expectedUrlPath) {
        return stripQueryAndTrailingSlash(getDriver().getCurrentUrl()).endsWith(expectedUrlPath);
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
