package io.maxbet.pageObjects;
import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static io.maxbet.DriverManager.getDriver;

public class WithdrawalsPage extends BaseElement {
    private final By withdrawalPageTitle = By.xpath("//h2[normalize-space()='Payment methods']");
    private final By withdrawalMethod1 = By.cssSelector("div[class='main-content-wrapper'] li:nth-child(1)");
    private final By withdrawalMethod2 = By.cssSelector("div[class='main-content-wrapper'] li:nth-child(2)");
    private final By withdrawalMethod3 = By.xpath("//div[normalize-space()='Visa & MasterCard & Maestro EN']");//div[class='main-content-wrapper'] li:nth-child(3)
    private final By withdrawalMethod4 = By.cssSelector("div[class='main-content-wrapper'] li:nth-child(4)");
    private final By inputForWdAmountBankCard = By.cssSelector("#amount");
    private final By amountWarningWdBankCardMessage = By.cssSelector("mb-formcontrol-error .error span");
    private final By firstBankCardSwitch = By.cssSelector("mb-withdraw-card:first-child .card");
    private final By firstBankCardCheckbox = By.cssSelector("mb-withdraw-card:first-child mb-switch input");
    // 'ng-star-inserted' is an Angular bookkeeping class, not part of the component's styling:
    // it is dropped from the button once the form turns valid, which is exactly the moment the
    // button has to be found. Matched on the component's own classes instead.
    private final By continueWdBtn = By.cssSelector("button.mb-button.form-btn.btn-primary.lg");
    private final By successWdModal = By.cssSelector(".default-dialog.success");
    private final By successWdModalToTheLobbyBtn = By.cssSelector("button.mb-button.btn.btn-primary.lg");

    @Getter
    TextField WithdrawalPageTitle = new TextField(withdrawalPageTitle, "The withdrawal page title 'Payment methods'");
    @Getter
    Button WithdrawalMethod1 = new Button(withdrawalMethod1,"The first payment method for withdrawals in the page");
    @Getter
    Button WithdrawalMethod2 = new Button(withdrawalMethod2,"The second payment method for withdrawals in the page");
    @Getter
    Button WithdrawalMethod3 = new Button(withdrawalMethod3,"The third payment method for withdrawals in the page");
    @Getter
    Button WithdrawalMethod4 = new Button(withdrawalMethod4,"The fourth payment method for withdrawals in the page");
    @Getter
    InputField InputForWdAmountBankCard = new InputField(inputForWdAmountBankCard, "Input field for amount of WD for the bank card");
    @Getter
    TextField AmountWarningWdBankCardMessage = new TextField(amountWarningWdBankCardMessage, "Warning message for the Input field for amount of WD for the bank card");
    @Getter
    Button  FirstBankCardSwitch = new Button( firstBankCardSwitch,"");
    @Getter
    Button ContinueWdBtn = new Button(continueWdBtn,"The 'Continue' button");
    @Getter
    TextField SuccessWdModal = new TextField(successWdModal,"The 'Success' modal for WD");
    @Getter
    Button SuccessWdModalToTheLobbyBtn = new Button(successWdModalToTheLobbyBtn,"The 'To The Lobby' button");

    @Step("Wait until Wihtdraw page is opened")
    public void waitUntilPageOpened() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/withdrawals"));
    }
    @Step("Click on the first payment method for withdrawals on the page")
    public WithdrawalsPage openFirstWithdrawalMethod(){
        getWithdrawalMethod1().clickButton();
        return this;
    }
    @Step("Click on the second payment method for withdrawals on the page")
    public WithdrawalsPage openSecondWithdrawalMethod(){
        getWithdrawalMethod2().clickButton();
        return this;
    }
    @Step("Click on the third payment method for withdrawals on the page")
    public WithdrawalsPage openThirdWithdrawalMethod(){
        getWithdrawalMethod3().clickButton();
        return this;
    }
    @Step("Click on the fourth payment method for withdrawals on the page")
    public WithdrawalsPage openFourthWithdrawalMethod(){
        getWithdrawalMethod4().clickButton();
        return this;
    }
    @Step("Enter amount of WD into input field")
    public WithdrawalsPage enterAmountOfWdForBankCard(String wdAmount){
        getInputForWdAmountBankCard().setText(wdAmount);
        return this;
    }
    @Step("Verify withdraw error message: '{expectedMessage}'")
    public void verifyWithdrawErrorMessage(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        WebElement errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(amountWarningWdBankCardMessage)
        );
        Assert.assertEquals(
                errorMessage.getText().trim(),
                expectedMessage,
                "Unexpected withdraw error message"
        );
    }
    @Step("Select the first bank card for the withdrawal")
    public WithdrawalsPage clickCardSwitch() {
        WebElement element = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(firstBankCardSwitch));
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].click();", element);
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementSelectionStateToBe(firstBankCardCheckbox, true));
        return this;
    }
    @Step("Click on 'Continue' button for withdrawal")
    public WithdrawalsPage clickContinueBtnWd(){
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(continueWdBtn));
        } catch (TimeoutException e) {
            throw new AssertionError(
                    "The 'Continue' button never became clickable - the withdrawal form is "
                            + "probably still invalid. Amount: '" + getEnteredWdAmount()
                            + "', first card selected: " + isFirstCardSelected(), e);
        }
        getContinueWdBtn().clickButton();
        return this;
    }
    @Step("Check the first bank card is selected")
    public boolean isFirstCardSelected() {
        return getDriver().findElement(firstBankCardCheckbox).isSelected();
    }
    private String getEnteredWdAmount() {
        return getDriver().findElement(inputForWdAmountBankCard).getAttribute("value");
    }
    @Step("Verify the withdrawal success modal is shown")
    public WithdrawalsPage verifySuccessWdModal() {
        getSuccessWdModal().verify();
        return this;
    }
    @Step("Click on the 'To The Lobby' button")
    public LobbyPage clickToTheLobbyBtn(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(successWdModalToTheLobbyBtn))
                .click();
        return new LobbyPage();
    }
}
