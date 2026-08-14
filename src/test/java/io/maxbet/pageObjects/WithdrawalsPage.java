package io.maxbet.pageObjects;
import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.maxbet.DriverManager.getDriver;

public class WithdrawalsPage extends BaseElement {
    private final By withdrawalPageTitle = By.xpath("//h2[normalize-space()='Payment methods']");
    private final By withdrawalMethod1 = By.cssSelector("div[class='main-content-wrapper'] li:nth-child(1)");
    private final By withdrawalMethod2 = By.cssSelector("div[class='main-content-wrapper'] li:nth-child(2)");
    private final By withdrawalMethod3 = By.cssSelector("div[class='main-content-wrapper'] li:nth-child(3)");
    private final By withdrawalMethod4 = By.cssSelector("div[class='main-content-wrapper'] li:nth-child(4)");

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

    @Step("Wait until Deposit page is opened")
    public void waitUntilPageOpened() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/withdrawals"));
    }
    @Step("Click on the first payment method for withdrawals on the page")
    public void openFirstWithdrawalMethod(){
        getWithdrawalMethod1().clickButton();
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
}
