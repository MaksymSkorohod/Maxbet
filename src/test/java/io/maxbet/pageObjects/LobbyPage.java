package io.maxbet.pageObjects;

import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import static io.maxbet.DriverManager.getDriver;

public class LobbyPage {
    private By mask = By.cssSelector(".mask");
    private final By userInfoLocator = By.cssSelector(
            "div[class='right-block notific-padding'] mb-header-user-info[class='user-info']"
    );
    private final By userNameBlock = By.cssSelector("div[class='user-name-block'] div[class='name']");
    private By logoutBtn = By.cssSelector("div[class='menu'] mb-logout-menu mb-menu-container");
    private By searchField = By.xpath("(//mb-lobby-search)[2]");
    private By searchModal = By.cssSelector(".mat-mdc-dialog-component-host.ng-star-inserted");
    private By searchModalTitlte = By.cssSelector("section[class='search-action'] div[class='title']");
    private By searchForGameInput = By.cssSelector("input[placeholder='Search for games']");

    @Getter
    private final Button UserInfo = new Button(userInfoLocator, "The User Info button");
    @Getter
    private final TextField UserName = new TextField(userNameBlock, "The User Name");
    @Getter
    private final Button Logout = new Button(logoutBtn, "The Logout button");
    @Getter
    Button SearchButton = new Button(searchField, "The Search button");
    @Getter
    Button SearchModal = new Button(searchModal, "The Search modal");
    @Getter
    TextField SearchModalTitle = new TextField(searchModalTitlte, "The Search modal title");
    @Getter
    InputField SearchForGame = new InputField(searchForGameInput, "The Search for game input field");

    @Step("Click on the 'User Info' button")
    public LobbyPage clickOnUserInfo() {
        waitUntilMaskDisappears();
        getUserInfo().clickButton();
        return this;
    }
    @Step("Wait until page mask disappears")
    public void waitUntilMaskDisappears() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(mask));
    }
    @Step("Click on the 'Logout' button")
    public LobbyPage clickOnLogout() {
        getLogout().clickButton();
        return this;
    }
    @Step("Click on the 'Search' button")
    public LobbyPage clickOnSearch() {
        waitUntilMaskDisappears();
        getSearchButton().clickButton();
        return this;
    }
    @Step("Click on the 'Search' button from the Search modal")
    public LobbyPage clickToCloseSearchModal() {
        getSearchModal().clickAnywhereOnPage();
        return this;
    }
    @Step("Enter search text")
    public LobbyPage enterSearchText(String text) {
        getSearchForGame().setText(text);
        return this;
    }

}