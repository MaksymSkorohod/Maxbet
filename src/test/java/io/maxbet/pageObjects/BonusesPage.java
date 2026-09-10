package io.maxbet.pageObjects;

import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.maxbet.DriverManager.getDriver;

public class BonusesPage extends AbstractPage{
    private final By activeBonusesTitle = By.xpath("//h2[normalize-space()='Active bonuses']");
    // Located by its label: the page also carries other transparent buttons, so a class selector picks
    // the wrong one and the code input never opens.
    private final By enterBonusCodeBtn = By.xpath("//button[normalize-space()='Enter a bonus code']");
    private final By bonusCodeInputField = By.cssSelector("input[formcontrolname='bonusCode']");
    private final By bonusCodeSubmitBtn = By.cssSelector("button[data-fs-element='Profile.Overview.BTN_EnterBonusCode']");
    private final By activeBonusItem = By.cssSelector(".active-bonus-item");
    private final By deleteBonusBtn = By.cssSelector(".active-bonus-item .image-container");
    private final By warningDialogContainer = By.cssSelector("mb-default-dialog");
    // The warning dialog offers 'Keep Bonus' (btn-transparent) and 'Cancel Bonus' (the plain primary
    // button that confirms the removal); the :not(.btn-transparent) is what tells the two apart.
    private final By cancelBonusBtn = By.cssSelector("mb-default-dialog button.btn-primary:not(.btn-transparent)");
    private final By bonusesHistoryTitle = By.xpath("//h2[normalize-space()='Bonus History']");
    private final By bonusesHistoryTable = By.cssSelector(".bonus-history-section");
    private final By seeAllBonusesHistoryBtn = By.cssSelector(".bonuses-see-all.ng-star-inserted");
    private final By bonusHistoryItem = By.cssSelector(".bonus-history-item");

    @Getter
    TextField ActiveBonusesTitle = new TextField(activeBonusesTitle, "The 'Active Bonuses' title");
    @Getter
    Button EnterBonusCodeBtn = new Button(enterBonusCodeBtn, "The 'Enter Bonus Code' button");
    @Getter
    InputField BonusCodeInputField = new InputField(bonusCodeInputField, "The 'Bonus Code' input field");
    @Getter
    Button BonusCodeSubmitBtn = new Button(bonusCodeSubmitBtn, "The 'Submit Bonus Code' button");
    @Getter
    TextField ActiveBonusItem = new TextField(activeBonusItem, "The 'Active Bonus' item");
    @Getter
    Button DeleteBonusBtn = new Button(deleteBonusBtn, "The 'Delete Bonus' button");
    @Getter
    TextField WarningDialogContainer = new TextField(warningDialogContainer, "The 'Warning' dialog container");
    @Getter
    Button CancelBonusBtn = new Button(cancelBonusBtn, "The 'Cancel Bonus' (confirm removal) button");
    @Getter
    TextField BonusesHistoryTitle = new TextField(bonusesHistoryTitle, "The 'Bonus History' title");
    @Getter
    TextField BonusesHistoryTable = new TextField(bonusesHistoryTable, "The 'Bonus History' section");
    @Getter
    Button SeeAllBonusesHistoryBtn = new Button(seeAllBonusesHistoryBtn, "The 'See more bonuses' button");
    @Getter
    TextField BonusHistoryItem = new TextField(bonusHistoryItem, "A 'Bonus History' item");

    @Step("Wait until the 'Bonuses' page is opened")
    public BonusesPage waitUntilPageOpened() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("/profile/bonuses"));
        getActiveBonusesTitle().verify();
        return this;
    }

    @Step("Count the bonuses in the 'Active bonuses' list")
    public int getActiveBonusesCount() {
        return getDriver().findElements(activeBonusItem).size();
    }

    @Step("Check at least one bonus is active")
    public boolean hasActiveBonus() {
        return getActiveBonusItem().isExists(5);
    }

    @Step("Wait until {expectedCount} active bonus(es) are listed")
    public boolean waitUntilActiveBonusesCountIs(int expectedCount) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                    .until(driver -> driver.findElements(activeBonusItem).size() == expectedCount);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Open the bonus code input")
    public BonusesPage clickOnEnterBonusCode() {
        getEnterBonusCodeBtn().clickButton();
        // The input opens with an animation; fail here with a clear message if it never shows.
        getBonusCodeInputField().verify();
        return this;
    }

    @Step("Enter the bonus code '{code}'")
    public BonusesPage enterBonusCode(String code) {
        getBonusCodeInputField().setText(code);
        return this;
    }

    @Step("Submit the bonus code")
    public BonusesPage submitBonusCode() {
        getBonusCodeSubmitBtn().clickButton();
        return this;
    }

    @Step("Add the bonus with code '{code}'")
    public BonusesPage addBonus(String code) {
        clickOnEnterBonusCode();
        enterBonusCode(code);
        submitBonusCode();
        return this;
    }

    @Step("Remove the first active bonus")
    public BonusesPage removeFirstActiveBonus() {
        getDriver().findElement(deleteBonusBtn).click();
        getWarningDialogContainer().verify();
        settle();
        getDriver().findElement(cancelBonusBtn).click();
        getWarningDialogContainer().invisibilityOfElementLocated(10);
        return this;
    }

    private void settle() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Remove every active bonus")
    public BonusesPage removeAllActiveBonuses() {
        while (hasActiveBonus()) {
            int before = getActiveBonusesCount();
            removeFirstActiveBonus();
            if (!waitUntilActiveBonusesCountIs(before - 1)) {
                break;
            }
        }
        return this;
    }

    @Step("Count the entries in the 'Bonus History'")
    public int getBonusHistoryCount() {
        return getDriver().findElements(bonusHistoryItem).size();
    }

    @Step("Check the 'Bonus History' has at least one entry")
    public boolean hasBonusHistory() {
        return getBonusHistoryItem().isExists(5);
    }
}
