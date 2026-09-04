package io.maxbet.tests;
import io.maxbet.listeners.TestGroups;

import io.maxbet.pageObjects.GamePage;
import io.maxbet.pageObjects.LobbyPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The games opened from the 'Recently played' section of the lobby. The section is ordered by the last
 * time a game was played, so the game a position of it holds changes from run to run: a test that only
 * opens a game takes the game the section offers and verifies the page against the card it clicked,
 * while the test that plays a round names the game it opens, because a round is played on the controls
 * of the provider of the game.
 */
public class GameTests extends TestBase {
    /** The game the controls of which {@link GamePage#playRound()} drives. */
    private static final String PLAYABLE_GAME = "40 Super Hot";

    @BeforeMethod
    public void setUp() {
        lobbyPage = new LobbyPage();
    }

    @Test(groups = {TestGroups.SMOKE}, description = "Open a game from the 'Recently played' section of the 'Lobby' page")
    public void openGameFromRecentlyPlayed() {
        openRecentlyPlayedGame()
                .verifyGameOpened();
    }

    @Test(description = "Open the '40 Super Hot' game from the 'Recently played' section and play a round of it")
    public void playGameFromRecentlyPlayed() {
        lobbyPage
                .getRecentlyPlayedSectionTitle().verify();
        lobbyPage
                .playRecentlyPlayedGame(PLAYABLE_GAME)
                .verifyGameOpened()
                .verifyPageTitle()
                .playRound();
    }

    @Test(description = "Open a game from the 'Recently played' section and return to the 'Lobby' page")
    public void closeGameOpenedFromRecentlyPlayed() {
        openRecentlyPlayedGame()
                .verifyGameOpened()
                .clickOnBackGameBtnAndVerifyLobbyPage();
    }

    private GamePage openRecentlyPlayedGame() {
        lobbyPage
                .getRecentlyPlayedSectionTitle().verify();
        return lobbyPage
                .playRecentlyPlayedGame();
    }
}
