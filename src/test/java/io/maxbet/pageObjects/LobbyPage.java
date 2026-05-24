package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static io.maxbet.DriverManager.getDriver;

public class LobbyPage extends BaseElement {
    private final By mask = By.cssSelector(".mask");
    private final By userInfoLocator = By.cssSelector(
            "div[class='right-block notific-padding'] mb-header-user-info[class='user-info']"
    );
    private final By userNameBlock = By.cssSelector("div[class='user-name-block'] div[class='name']");
    private final By logoutBtn = By.cssSelector("div[class='menu'] mb-logout-menu mb-menu-container");
    private final By searchField = By.xpath("(//mb-lobby-search)[2]");
    private final By searchModal = By.cssSelector(".mat-mdc-dialog-component-host.ng-star-inserted");
    private final By searchModalTitle = By.cssSelector("section[class='search-action'] div[class='title']");
    private final By searchForGameInput = By.cssSelector("input[placeholder='Search for games']");
    private final By lobbyMenu = By.cssSelector(".menu-items-groups");
    private final By popularGamesBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/casino/populargames']");
    private final By allGamesBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/casino/allgames']");
    private final By liveCasinoBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/live-casino']");
    private final By tournamentsBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/tournaments']");
    private final By promotionsBtn = By.cssSelector(".mb-menu-item[label='menu.promotions']");
    private final By maxWheelBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/maxwheel']");
    private final By vipBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/vip']");

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
    TextField SearchModalTitle = new TextField(searchModalTitle, "The Search modal title");
    @Getter
    InputField SearchForGame = new InputField(searchForGameInput, "The Search for game input field");
    @Getter
    Button LobbyMenu = new Button(lobbyMenu, "The Lobby Menu");
    @Getter
    Button PopularGamesBtn = new Button(popularGamesBtn, "The 'For you' button");
    @Getter
    Button AllGamesBtn = new Button(allGamesBtn, "The 'All slots' button");
    @Getter
    Button LiveCasinoBtn = new Button(liveCasinoBtn, "The 'Live Casino' button");
    @Getter
    Button TournamentsBtn = new Button(tournamentsBtn, "The 'Tournaments' button");
    @Getter
    Button PromotionsBtn = new Button(promotionsBtn, "The 'Promotions' button");
    @Getter
    Button MaxWheelBtn = new Button(maxWheelBtn, "The 'Max Wheel' button");
    @Getter
    Button VipBtn = new Button(vipBtn, "The 'VIP' button");

    @Step("Click on the 'User Info' button")
    public ProfilePage clickOnUserInfo() {
        waitUntilMaskDisappears();
        getUserInfo().clickButton();
        return new ProfilePage();
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
    @Step("Click on the 'For you' button")
    public LobbyPage clickOnPopularGames() {
        getPopularGamesBtn().clickButton();
        return this;
    }
    @Step("Click on the 'All slots' button")
    public LobbyPage clickOnAllGames() {
        getAllGamesBtn().clickButton();
        return this;
    }
    @Step("Click on the 'Live Casino' button")
    public LobbyPage clickOnLiveCasino() {
        getLiveCasinoBtn().clickButton();
        return this;
    }
    @Step("Click on the 'Tournaments' button")
    public TournamentsPage clickOnTournaments() {
        getTournamentsBtn().clickButton();
        return new TournamentsPage();
    }
    @Step("Click on the 'Promotions' button")
    public PromotionsPage clickOnPromotions() {
        getPromotionsBtn().clickButton();
        return new PromotionsPage();
    }
    @Step("Click on the 'VIP' button")
    public VipPage clickOnVip() {
        getVipBtn().clickButton();
        return new VipPage();
    }

}