package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static io.maxbet.DriverManager.getDriver;

/**
 * The page a game is played on. The page carries the title and the 'Back' button of the wrapper
 * that the site draws around the game, and the controls of the game itself, which live two iframes
 * deep - the EveryMatrix loader carries the Amusnet frame that holds the game. Every step that
 * touches a control of the game enters the frame of the game on its own and leaves the driver in the
 * top document afterwards, so no test has to keep track of the frame the driver was left in and a
 * step that fails midway cannot carry a switched frame over to the step that follows it.
 * <p>
 * The page is built around the name of a game, so the same page serves any game of the provider
 * instead of a set of members per game.
 */
public class GamePage extends BaseElement {
    private static final String SPIN_BUTTON_ID = "spin-button";
    private final By gameLoaderFrame = By.cssSelector("iframe[src*='gamelaunch']");
    private final By gameFrame = By.cssSelector("iframe#ifmGame");
    private final By spinButton = By.id(SPIN_BUTTON_ID);
    private final By backGameBtn = By.cssSelector("div[aria-label='nav-back.back-btn']");
    private final By gamePageTitle;
    private final String gameName;
    private final String gamePath;

    @Getter
    private final TextField GamePageTitle;
    @Getter
    private final Button SpinButton;
    @Getter
    private final Button BackGameBtn;

    /**
     * The address the card of the game links to is taken along with the name, because it is the
     * address, not the heading, that tells which game was opened - the heading the page prints
     * follows no single template, the same game is headed '40 Super Hot Online' on one load and
     * '40 Super Hot by Amusnet' on the next, which is why the heading is only matched by the name it
     * opens with, while the address of a game is the one its card carries and is the same every time.
     */
    public GamePage(String gameName, String gamePath) {
        this.gameName = gameName;
        this.gamePath = gamePath;
        gamePageTitle = By.xpath(
                "//h1[starts-with(normalize-space(), '" + gameName + "')]");
        GamePageTitle = new TextField(gamePageTitle, "The title of the '" + gameName + "' game page");
        SpinButton = new Button(spinButton, "The 'Spin' button of the '" + gameName + "' game");
        BackGameBtn = new Button(backGameBtn, "The 'Back' button on the game page");
    }

    @Step("Verify the game page title is shown")
    public GamePage verifyPageTitle() {
        getGamePageTitle().verify();
        return this;
    }

    /**
     * Verifies that the game of the card that was clicked is the game that is open, without leaning
     * on the title the page prints. The address of the page tells which game was opened, and the
     * loader frame the site wraps every game in along with the 'Back' button of that wrapper tell
     * that the game itself was reached rather than the page around it alone. All three hold whatever
     * provider the game belongs to, which a game taken from the 'Recently played' section - the
     * section holds whatever the account has played - has to.
     */
    @Step("Verify that the game is opened")
    public GamePage verifyGameOpened() {
        WebDriverWait gameWait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        gameWait.withMessage("The '" + gameName + "' game did not open '" + gamePath
                        + "', the page stayed on '" + getDriver().getCurrentUrl() + "'")
                .until(ExpectedConditions.urlContains(gamePath));
        gameWait.withMessage("The '" + gameName
                        + "' game was not opened, the game loader frame did not appear")
                .until(ExpectedConditions.presenceOfElementLocated(gameLoaderFrame));
        getBackGameBtn().verify();
        return this;
    }
    /**
     * The driver is moved from the top document down to the frame of the game, which makes the step
     * safe to call whatever frame the driver was left in.
     */
    @Step("Switch to the frame of the game")
    public GamePage switchToGameFrame() {
        getDriver().switchTo().defaultContent();
        WebDriverWait frameWait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        frameWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(gameLoaderFrame));
        frameWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(gameFrame));
        return this;
    }
    @Step("Switch back to the page out of the frame of the game")
    public GamePage switchToPageOutOfGameFrame() {
        getDriver().switchTo().defaultContent();
        return this;
    }
    /**
     * The game covers its controls with a loading screen for about forty seconds, and that screen
     * keeps receiving pointer events while it fades, so a click on the 'Spin' button lands on the
     * loading screen instead. The overlay is also absent for the first moments after the frame is
     * entered, which lets a plain invisibility wait pass before the loading has even begun. Waiting
     * until the centre of the 'Spin' button is the topmost element covers both cases: it holds while
     * the button is missing, while it is covered, and passes only once the click can reach it.
     * <p>
     * The step expects the driver to sit in the frame of the game already.
     */
    @Step("Wait until the game is loaded and the 'Spin' button can be clicked")
    public GamePage waitUntilGameIsReady() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(120))
                .withMessage("The game did not load, the 'Spin' button stayed covered")
                .until(driver -> Boolean.TRUE.equals(((JavascriptExecutor) driver).executeScript(
                        "const spin = document.getElementById(arguments[0]);" +
                                "if (!spin) return false;" +
                                "const box = spin.getBoundingClientRect();" +
                                "const top = document.elementFromPoint(" +
                                "        box.left + box.width / 2, box.top + box.height / 2);" +
                                "return !!top && spin.contains(top);",
                        SPIN_BUTTON_ID)));
        return this;
    }
    /**
     * The round is played out inside the frame of the game, so the click and the wait for the round
     * to finish are taken together - the label of the 'Spin' button, which the game empties while
     * the reels turn and writes back once the round is paid out, is the only state of a played round
     * that is readable from the DOM. The balance is drawn as strips of digits whose text carries
     * every digit at once and never changes, which leaves nothing to compare.
     * <p>
     * Both edges of the label are awaited, so the step passes only on a round that started and
     * finished rather than on a click that was swallowed - a click that is intercepted by the
     * loading screen is retried through JavaScript by {@link Button#clickButton()} and would
     * otherwise leave no trace.
     */
    @Step("Play a round of the game with the 'Spin' button")
    public GamePage playRound() {
        switchToGameFrame();
        try {
            waitUntilGameIsReady();
            String idleLabel = getSpinButtonLabel();
            Assert.assertFalse(idleLabel.isEmpty(),
                    "The 'Spin' button carried no label before the click, the game was not idle");
            getSpinButton().clickButton();
            new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                    .withMessage("The round did not start, the label of the 'Spin' button was kept")
                    .until(driver -> getSpinButtonLabel().isEmpty());
            new WebDriverWait(getDriver(), Duration.ofSeconds(90))
                    .withMessage("The round did not finish, the 'Spin' button kept an empty label")
                    .until(driver -> !getSpinButtonLabel().isEmpty());
        } finally {
            switchToPageOutOfGameFrame();
        }
        return this;
    }
    private String getSpinButtonLabel() {
        try {
            return getDriver().findElement(spinButton).getText().trim();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return "";
        }
    }
    /**
     * The header of the game page lays itself out while the game is still being loaded, and the logo
     * of the site takes the place of the 'Back' button for as long as it does, which makes a plain
     * click land on the logo. {@link Button#clickButton()} retries the click for the window the
     * header needs and clicks through JavaScript if the logo is still in the way, so the step holds
     * whether it is reached the moment the game opened or long after it.
     */
    @Step("Click on the 'Back' button on the game page")
    public LobbyPage clickOnBackGameBtnAndVerifyLobbyPage() {
        getBackGameBtn().clickButton();
        wait.until(ExpectedConditions.urlContains(LobbyPage.LOBBY_URL));
        Assert.assertTrue(
                getDriver().getCurrentUrl().contains(LobbyPage.LOBBY_URL),
                "Lobby Page URL is incorrect. Actual URL: " + getDriver().getCurrentUrl());
        return new LobbyPage();
    }
}
