package io.maxbet.tests;

import io.maxbet.Elements.Button;
import io.maxbet.pageObjects.GamePage;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.TournamentsPage;
import io.maxbet.pageObjects.VipPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LobbyTests extends TestBase{
    private static final String GAME_NAME = "40 Super Hot";

    @BeforeMethod
    public void setUp() {
        lobbyPage = new LobbyPage();
    }
    @Test(description = "Open Profile page")
    public void openProfilePage(){
        lobbyPage
                .clickOnUserInfo()
                .getProfileMenu().verify();
    }
    @Test(description = "Enter the text in the Search modal")
    public void enterGameIntoSearchModal(){
        lobbyPage
                .clickOnSearch()
                .getSearchModalTitle().verify();
        lobbyPage
                .enterSearchText("Mad Cars");
        lobbyPage
                .clickToCloseSearchModal();
    }
    @Test(description = "Open the game from the Search modal")
    public void openGameFromSearchModal() {
        openGame()
                .verifyPageTitle();
    }
    @Test(description = "Open the game from the Search modal and play a round of it")
    public void playGameFromSearchModal() {
        openGame()
                .verifyPageTitle()
                .playRound();
    }
    @Test(description = "Close the game from the game page and return to the 'Lobby' page")
    public void closeGame() {
        openGame()
                .verifyPageTitle()
                .clickOnBackGameBtnAndVerifyLobbyPage();
    }
    @Test(description = "Open the 'Promotions' page")
    public void openPromotionsPage(){
        lobbyPage
                .clickOnPromotions()
                .getPromotionCategories().verify();
    }
    @Test(description = "Open the 'Tournaments' page")
    public void openTournamentsPage(){
        TournamentsPage tournamentsPage = lobbyPage.clickOnTournaments();
        tournamentsPage.getTournamentsPageNavList().verify();
    }
    @Test(description = "Open the 'VIP' page")
    public void openVipPage(){
        VipPage vipPage = lobbyPage.clickOnVip();
                vipPage.getVipPageTitle().verify();
    }

    @Test(description = "Open the 'Deposit' modal from the 'Lobby' page")
    public void openDepositModal(){
        lobbyPage
                .clickOnDeposit();
        lobbyPage
                .getDepositModal().verify();
        lobbyPage
                .clickOnCloseDepositModal();
    }

    @Test(description = "Open the 'Live Casino' page")
    public void openLiveCasinoPage(){
        lobbyPage
                .getHeaderLiveCasinoBtn().verify();
        lobbyPage
                .openLiveCasinoPage();
    }

    @Test(description = "Open the 'Live Chat' modal and minimize it")
    public void openAndMinimizeLiveChatModal(){
        lobbyPage
                .clickOnLiveChat()
                .verifyLiveChatIsOpened()
                .clickOnMinimizeButton()
                .verifyLiveChatIsMinimized();
    }
        @Test(description = "Open the 'Providers' filter and verify the vendors container")
        public void openProvidersFilterAndVerifyVendorsContainer(){
            lobbyPage
                    .clickOnProviderFilter()
                    .getVendorsContainer().verify();
        }
    @DataProvider(name = "barSections")
    public Object[][] barSections() {
        return new Object[][]{
                {"Bar Section 1", (SectionOf) LobbyPage::getBarSection1},
                {"Bar Section 2", (SectionOf) LobbyPage::getBarSection2},
                {"Bar Section 3", (SectionOf) LobbyPage::getBarSection3},
                {"Bar Section 4", (SectionOf) LobbyPage::getBarSection4},
                {"Bar Section 5", (SectionOf) LobbyPage::getBarSection5},
                {"Bar Section 6", (SectionOf) LobbyPage::getBarSection6},
                {"Bar Section 7", (SectionOf) LobbyPage::getBarSection7},
                {"Bar Section 8", (SectionOf) LobbyPage::getBarSection8},
                {"Bar Section 9", (SectionOf) LobbyPage::getBarSection9},
                {"Bar Section 10", (SectionOf) LobbyPage::getBarSection10},
                {"Bar Section 11", (SectionOf) LobbyPage::getBarSection11},
                {"Bar Section 12", (SectionOf) LobbyPage::getBarSection12},
        };
    }

    @Test(description = "The categories bar carries its sections on the scrollable strip")
    public void theCategoriesBarCarriesItsSections(){
        lobbyPage
                .getSectionDragScrollBar().verify();
        for (Object[] section : barSections()) {
            ((SectionOf) section[1]).of(lobbyPage).verify();
        }
    }

    @Test(dataProvider = "barSections",
            description = "A section of the categories bar opens the URL it links to")
    public void openingABarSectionOpensItsUrl(String name, SectionOf sectionOf){
        Button section = sectionOf.of(lobbyPage);
        section.verify();
        String path = lobbyPage.getBarSectionPath(section);
        Assert.assertTrue(path.startsWith("/"),
                "The '" + name + "' section of the categories bar links nowhere: '" + path + "'");

        lobbyPage
                .openBarSection(section);

        Assert.assertTrue(lobbyPage.waitUntilSectionIsOpened(path),
                "The '" + name + "' section did not open '" + path + "', the page stayed on '"
                        + lobbyPage.getCurrentUrl() + "'");
        assertUrlOpensSection(path, name);
    }
    @Test(description = "The first section of the categories bar opens again after another one")
    public void theFirstBarSectionOpensAgainAfterAnotherOne(){
        Button firstSection = lobbyPage.getBarSection1();
        Button secondSection = lobbyPage.getBarSection2();
        String firstPath = lobbyPage.getBarSectionPath(firstSection);
        String secondPath = lobbyPage.getBarSectionPath(secondSection);
        Assert.assertNotEquals(secondPath, firstPath,
                "The first two sections of the categories bar link to the same address");

        lobbyPage
                .openBarSection(secondSection);
        Assert.assertTrue(lobbyPage.waitUntilSectionIsOpened(secondPath),
                "The 'Bar Section 2' did not open '" + secondPath + "', the page stayed on '"
                        + lobbyPage.getCurrentUrl() + "'");

        lobbyPage
                .openBarSection(firstSection);

        Assert.assertTrue(lobbyPage.waitUntilSectionIsOpened(firstPath),
                "The 'Bar Section 1' did not open '" + firstPath + "' again, the page stayed on '"
                        + lobbyPage.getCurrentUrl() + "'");
        assertUrlOpensSection(firstPath, "Bar Section 1");
    }

    private GamePage openGame() {
        lobbyPage
                .clickOnSearch()
                .getSearchModalTitle().verify();
        lobbyPage
                .enterSearchText(GAME_NAME);
        return lobbyPage
                .playSearchedGame(GAME_NAME);
    }

    private void assertUrlOpensSection(String path, String name){
        String url = lobbyPage.getCurrentUrl().split("[?#]")[0];
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        Assert.assertTrue(url.endsWith(path),
                "The URL of the opened '" + name + "' section is '" + url
                        + "', it does not end with the address of the section, '" + path + "'");
    }

    private interface SectionOf {
        Button of(LobbyPage page);
    }
}
