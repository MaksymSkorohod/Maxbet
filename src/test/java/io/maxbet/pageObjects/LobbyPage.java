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
    private final By headerCasinoBtn = By.cssSelector("a[data-fs-element='Nav.Global.NAV_Casino']");
    private final By headerLiveCasinoBtn = By.cssSelector("a[data-fs-element='Nav.Global.NAV_LiveCasino']");
    private final By headerLottoBtn = By.cssSelector("a[data-fs-element='Nav.Global.NAV_Lotto']");
    private final By headerBettingBtn = By.xpath("//a[contains(text(),'Betting')]");
    private final By depositBtn = By.cssSelector(".btn-primary.xs.deposit");
    private final By depositModal = By.cssSelector(".deposit-dialog");
    private final By depositModalTitle = By.cssSelector(".heading");
    private final By closeDepositModalBtn = By.cssSelector(".btn-transparent.xs.close");
    private final By backBtnDeposit = By.cssSelector(".mb-nav-back__link");
    private final By bankCardPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge\"}']");
    private final By abonPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_Abon\"}']");
    private final By paySafePM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_PaySafeCard\"}']");
    private final By externalCashierPM = By.cssSelector("li[data-fs-properties='{\"method\":\"ExternalCashier\"}']");
    private final By googlePayPM = By.cssSelector("li[data-fs-properties='{\"method\":\"SafeCharge_GooglePay\"}']");
    private final By oktoCashPM = By.cssSelector("li[data-fs-properties='{\"method\":\"OktoCash\"}']");
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
    Button HeaderCasinoBtn = new Button(headerCasinoBtn, "The 'Casino' button");
    @Getter
    Button HeaderLiveCasinoBtn = new Button(headerLiveCasinoBtn, "The 'Live Casino' button");
    @Getter
    Button HeaderLottoBtn = new Button(headerLottoBtn, "The 'Lotto' button");
    @Getter
    Button HeaderBettingBtn = new Button(headerBettingBtn, "The 'Betting' button");
    @Getter
    Button DepositButton = new Button(depositBtn, "The 'Deposit' button");
    @Getter
    TextField DepositModal = new TextField(depositModal, "The Deposit modal");
    @Getter
    TextField DepositModalTitle = new TextField(depositModalTitle, "The Deposit modal title");
    @Getter
    Button CloseDepositModalBtn = new Button(closeDepositModalBtn, "The 'Close' button in the Deposit modal");
    @Getter
    private final TextField UserName = new TextField(userNameBlock, "The User Name");
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
    @Step("Open the 'Casino' page")
    public LobbyPage openCasinoPage() {
        waitUntilMaskDisappears();
        getHeaderCasinoBtn().clickButton();
        return this;
    }
    @Step("Open the 'Live Casino' page")
    public LiveCasinoPage openLiveCasinoPage() {
        waitUntilMaskDisappears();
        getHeaderLiveCasinoBtn().clickButton();
        return new LiveCasinoPage();
    }
    @Step("Open the 'Lotto' page")
    public LottoPage openLottoPage() {
        waitUntilMaskDisappears();
        getHeaderLottoBtn().clickButton();
        return new LottoPage();
    }
    @Step("Open the 'Betting' page")
    public BettingPage openBettingPage() {
        waitUntilMaskDisappears();
        getHeaderBettingBtn().clickButton();
        return new BettingPage();
    }
    @Step("Click on the 'Deposit' button")
    public DepositPage clickOnDeposit() {
        waitUntilMaskDisappears();
        getDepositButton().clickButton();
        return new DepositPage();
    }
    @Step("Click on the 'Close' button in the Deposit modal")
    public LobbyPage clickOnCloseDepositModal() {
        getCloseDepositModalBtn().clickButton();
        return this;
    }
    @Step("Wait until page mask disappears")
    public void waitUntilMaskDisappears() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));//15
        wait.until(ExpectedConditions.invisibilityOfElementLocated(mask));
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
    public void enterSearchText(String text) {
        getSearchForGame().setText(text);
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
    public LiveCasinoPage clickOnLiveCasino() {
        waitUntilMaskDisappears();
        getLiveCasinoBtn().click();
        return new LiveCasinoPage();
    }
    @Step("Click on the 'Tournaments' button")
    public TournamentsPage clickOnTournaments() {
        getTournamentsBtn().clickButton();
        return new TournamentsPage();
    }
    @Step("Click on the 'Promotions' button")
    public PromoPage clickOnPromotions() {
        getPromotionsBtn().clickButton();
        return new PromoPage();
    }
    @Step("Click on the 'VIP' button")
    public VipPage clickOnVip() {
        getVipBtn().clickButton();
        return new VipPage();
    }

}