package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Generated;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import static io.maxbet.DriverManager.getDriver;

public class LobbyPage extends BaseElement {
    static final String LOBBY_URL = "/casino-online/populargames_prod";
    private final By mask = By.cssSelector(".mask");
    private final By userInfoLocator = By.cssSelector("div[class='right-block notific-padding'] mb-header-user-info[class='user-info']");
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
    private final By searchedGame = By.cssSelector("mb-game-card[mbfullstorytrackedelement='Casino.Search.CRD_GameResult'] button.favorite-btn");
    private final By searchedGameCard = By.cssSelector("mb-games-search-dialog mb-game-card");
    private final By gameCardImage = By.cssSelector(":scope > a");
    private final By gameCardPlayBtn = By.cssSelector(":scope > div > div > button");
    private final By recentlyPlayedSectionTitle = By.cssSelector("mb-section-title[iconname='recently-played-games-widget.title'] h2");
    private final By recentlyPlayedGameCard = By.cssSelector("mb-recently-played-games-widget mb-game-card:nth-child(3)");
    private final By recentlyPlayedGameCards = By.cssSelector("mb-recently-played-games-widget mb-game-card");
    private final By gameCardName = By.cssSelector(":scope .game-name");
    private final By providerFilter = By.cssSelector("mb-providers-filter > div");
    private final By vendorsContainer = By.cssSelector(".vendors-container");
    private final By sectionDragScrollBar = By.cssSelector(" .mb-nav-list__container.hidden-scroll");
    private final By barSection1 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(1) a");
    private final By barSection2 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(2) a");
    private final By barSection3 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(3) a");
    private final By barSection4 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(4) a");
    private final By barSection5 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(5) a");
    private final By barSection6 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(6) a");
    private final By barSection7 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(7) a");
    private final By barSection8 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(8) a");
    private final By barSection9 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(9) a");
    private final By barSection10 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(10) a");
    private final By barSection11 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(11) a");
    private final By barSection12 = By.cssSelector("section.categories-bar mb-nav-list-item:nth-child(12) a");
    private final By lobbyMenu = By.cssSelector(".menu-items-groups");
    private final By popularGamesBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/casino/populargames']");
    private final By allGamesBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/casino/allgames']");
    private final By liveCasinoBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/live-casino']");
    private final By tournamentsBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/tournaments']");
    private final By promotionsBtn = By.cssSelector(".mb-menu-item[label='menu.promotions']");
    private final By maxWheelBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/maxwheel']");
    private final By vipBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/vip']");
    private final By liveChatBtn = By.cssSelector("mb-menu-item[label='menu.liveSupport']");
    private final By liveChatModal = By.cssSelector("#chat-widget-container");
    private final By liveChatFrame = By.cssSelector("iframe#chat-widget");
    private final By minimizedLiveChatFrame = By.cssSelector("iframe#chat-widget-minimized");
    private final By minimizeButton = By.id("minimize");


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
    Button SearchedGame = new Button(searchedGame, "The searched game in the Search modal");
    @Getter
    TextField RecentlyPlayedSectionTitle = new TextField(recentlyPlayedSectionTitle, "The 'Recently played' section title");
    @Getter
    Button ProviderFilter = new Button(providerFilter, "The 'Provider' filter");
    @Getter
    TextField VendorsContainer = new TextField(vendorsContainer, "The 'Vendors' container");
    @Getter
    TextField SectionDragScrollBar = new TextField(sectionDragScrollBar, "The section drag scroll bar");
    @Getter
    Button BarSection1 = new Button(barSection1, "The section 1 in the categories bar");
    @Getter
    Button BarSection2 = new Button(barSection2, "The section 2 in the categories bar");
    @Getter
    Button BarSection3 = new Button(barSection3, "The section 3 in the categories bar");
    @Getter
    Button BarSection4 = new Button(barSection4, "The section 4 in the categories bar");
    @Getter
    Button BarSection5 = new Button(barSection5, "The section 5 in the categories bar");
    @Getter
    Button BarSection6 = new Button(barSection6, "The section 6 in the categories bar");
    @Getter
    Button BarSection7 = new Button(barSection7, "The section 7 in the categories bar");
    @Getter
    Button BarSection8 = new Button(barSection8, "The section 8 in the categories bar");
    @Getter
    Button BarSection9 = new Button(barSection9, "The section 9 in the categories bar");
    @Getter
    Button BarSection10 = new Button(barSection10, "The section 10 in the categories bar");
    @Getter
    Button BarSection11 = new Button(barSection11, "The section 11 in the categories bar");
    @Getter
    Button BarSection12 = new Button(barSection12, "The section 12 in the categories bar");

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
    @Getter
    Button LiveChatBtn = new Button(liveChatBtn, "The 'Live Chat' button");


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
    @Step("Click on the game from the Search modal")
    public void clickOnSearchedGame() {
        getDriver().findElements(searchedGame).get(0).click();
    }
    /**
     * The game the name of which is expected on the title of the page that is opened is taken as an
     * argument, because the search modal is left behind by the click and the page that follows it is
     * a page of a game rather than a page of the lobby.
     */
    @Step("Hover over the searched game and click the 'Play' button")
    public GamePage playSearchedGame(String gameName) {
        return playSearchedGame(0, gameName);
    }
    @Step("Hover over the searched game #{index} and click the 'Play' button")
    public GamePage playSearchedGame(int index, String gameName) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        WebElement card = wait.until(driver -> {
            List<WebElement> cards = driver.findElements(searchedGameCard);
            return cards.size() > index && cards.get(index).isDisplayed() ? cards.get(index) : null;
        });

        new Actions(getDriver())
                .moveToElement(card.findElement(gameCardImage))
                .pause(Duration.ofMillis(300))
                .perform();

        WebElement playBtn = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(driver -> {
                    WebElement button = card.findElement(gameCardPlayBtn);
                    return button.isDisplayed() && button.isEnabled() ? button : null;
                });
        playBtn.click();
        return new GamePage(gameName, getGameCardPath(card));
    }
    /**
     * Opens a game from the 'Recently played' section of the lobby. The card holds its thumbnail and
     * the overlay it lays over the thumbnail on hover as two siblings, and the 'Play' button sits in
     * that overlay, so the card is scrolled into view and hovered before the button is awaited and
     * clicked - the same shape a card of the search modal has, which is why the locators of that
     * card are reused here.
     * <p>
     * The name the card carries is read off the card before the click, so the page of the game that
     * follows can be verified by the title of the game the section offered rather than by the
     * wrapper alone - the section holds whatever the account has played and no name can be hard
     * coded into the test.
     */
    @Step("Hover over the recently played game and click the 'Play' button")
    public GamePage playRecentlyPlayedGame() {
        waitUntilMaskDisappears();
        WebElement card = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(recentlyPlayedGameCard));
        return openGameCard(card);
    }
    /**
     * Opens the game of the given name out of the 'Recently played' section. The section is ordered by
     * the last time a game was played, so the game a position holds changes from run to run, while a
     * round is played on the controls of the provider of the game - a test that plays a round has to
     * name the game it opens rather than take the one a position happens to hold.
     */
    @Step("Hover over the recently played '{gameName}' game and click the 'Play' button")
    public GamePage playRecentlyPlayedGame(String gameName) {
        waitUntilMaskDisappears();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(recentlyPlayedGameCards));

        WebElement card = getDriver().findElements(recentlyPlayedGameCards).stream()
                .filter(gameCard -> gameName.equals(
                        gameCard.findElement(gameCardName).getText().trim()))
                .findFirst()
                .orElseThrow(() -> new AssertionError(
                        "The 'Recently played' section holds no '" + gameName + "' game, it holds "
                                + getRecentlyPlayedGameNames()));
        return openGameCard(card);
    }
    @Step("Read the names of the games the 'Recently played' section holds")
    public List<String> getRecentlyPlayedGameNames() {
        return getDriver().findElements(recentlyPlayedGameCards).stream()
                .map(card -> card.findElement(gameCardName).getText().trim())
                .collect(Collectors.toList());
    }
    /**
     * The card lays the overlay that holds the 'Play' button over its thumbnail only while the
     * pointer rests on it, so the card is scrolled into view and hovered before the button is awaited
     * and clicked. The name and the address the card carries are read off it before the click, so the
     * page of the game that follows can be verified against the game the card offered - a card of the
     * 'Recently played' section holds whatever the account has played and carries no name a test could
     * have hard coded.
     */
    private GamePage openGameCard(WebElement card) {
        String gameName = card.findElement(gameCardName).getText().trim();
        String gamePath = getGameCardPath(card);

        new Actions(getDriver())
                .scrollToElement(card)
                .moveToElement(card.findElement(gameCardImage))
                .pause(Duration.ofMillis(300))
                .perform();

        WebElement playBtn = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
                .until(driver -> {
                    WebElement button = card.findElement(gameCardPlayBtn);
                    return button.isDisplayed() && button.isEnabled() ? button : null;
                });
        playBtn.click();
        return new GamePage(gameName, gamePath);
    }
    /**
     * Reads the address of the game off its card. The thumbnail of a card is the link to the game, so
     * the address a click opens is known before the click and does not have to be guessed from the
     * name of the game.
     */
    private String getGameCardPath(WebElement card) {
        String path = card.findElement(gameCardImage).getDomAttribute("href");
        Assert.assertNotNull(path, "The card of the game carries no address to open");
        return path;
    }
    @Step("Click on the 'Providers' filter")
    public LobbyPage clickOnProviderFilter() {
        getProviderFilter().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 1'")
    public LobbyPage clickOnBarSection1() {
        getBarSection1().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 2'")
    public LobbyPage clickOnBarSection2() {
        getBarSection2().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 3'")
    public LobbyPage clickOnBarSection3() {
        getBarSection3().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 4'")
    public LobbyPage clickOnBarSection4() {
        getBarSection4().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 5'")
    public LobbyPage clickOnBarSection5() {
        getBarSection5().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 6'")
    public LobbyPage clickOnBarSection6() {
        getBarSection6().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 7'")
    public LobbyPage clickOnBarSection7() {
        getBarSection7().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 8'")
    public LobbyPage clickOnBarSection8() {
        getBarSection8().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 9'")
    public LobbyPage clickOnBarSection9() {
        getBarSection9().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 10'")
    public LobbyPage clickOnBarSection10() {
        getBarSection10().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 11'")
    public LobbyPage clickOnBarSection11() {
        getBarSection11().clickButton();
        return this;
    }
    @Step("Click on the 'Bar Section 12'")
    public LobbyPage clickOnBarSection12() {
        getBarSection12().clickButton();
        return this;
    }
    @Step("Read the address the section of the categories bar links to")
    public String getBarSectionPath(Button barSection) {
        return barSection.getAttributeValue("href");
    }
    @Step("Open the section of the categories bar")
    public LobbyPage openBarSection(Button barSection) {
        waitUntilMaskDisappears();
        barSection.clickButton();
        return this;
    }
    @Step("Wait until the opened section is the one at '{path}'")
    public boolean waitUntilSectionIsOpened(String path) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.urlContains(path));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
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
    @Step("Click on the 'Live Chat' button")
    public LobbyPage clickOnLiveChat() {
        waitUntilMaskDisappears();
        wait.until(ExpectedConditions.elementToBeClickable(liveChatBtn)).click();
        chatWait().until(ExpectedConditions.visibilityOfElementLocated(liveChatFrame));
        return this;
    }
    @Step("Verify that the 'Live Chat' modal is opened")
    public LobbyPage verifyLiveChatIsOpened() {
        Assert.assertTrue(
                isVisible(liveChatModal),
                "The 'Live Chat' modal is not visible. Locator: " + liveChatModal);
        Assert.assertTrue(
                isVisible(liveChatFrame),
                "The 'Live Chat' widget frame is not visible. Locator: " + liveChatFrame);
        return this;
    }
    @Step("Click on the 'Minimize' button in the Live Chat modal")
    public LobbyPage clickOnMinimizeButton() {
        chatWait().until(driver -> {
            driver.switchTo().defaultContent();
            try {
                driver.switchTo().frame(driver.findElement(liveChatFrame));
                WebElement button = driver.findElement(minimizeButton);
                if (!button.isDisplayed() || !button.isEnabled()) {
                    return false;
                }
                button.click();
                return true;
            } catch (NoSuchFrameException | NoSuchElementException
                     | StaleElementReferenceException | ElementNotInteractableException e) {
                return false;
            }
        });
        getDriver().switchTo().defaultContent();
        return this;
    }
    @Step("Verify that the 'Live Chat' modal is minimized")
    public LobbyPage verifyLiveChatIsMinimized() {
        Assert.assertTrue(
                chatWait().until(ExpectedConditions.invisibilityOfElementLocated(liveChatFrame)),
                "The 'Live Chat' modal is still visible after clicking the 'Minimize' button");
        Assert.assertFalse(
                getDriver().findElements(minimizedLiveChatFrame).isEmpty(),
                "The minimized 'Live Chat' widget is not present on the page");
        return this;
    }
    private boolean isVisible(By locator) {
        try {
            return chatWait().until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }
    private WebDriverWait chatWait() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(30));
    }
}