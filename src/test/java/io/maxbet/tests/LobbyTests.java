package io.maxbet.tests;

import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.TournamentsPage;
import io.maxbet.pageObjects.VipPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LobbyTests extends TestBase{

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
    @Test(description = "Open the game from the Search modal")
    public void openGameFromSearchModal(){
        lobbyPage
                .clickOnSearch()
                .getSearchModalTitle().verify();
        lobbyPage
                .enterSearchText("40 Super Hot");
        lobbyPage
                .playSearchedGame()
                .getGamePageTitle2().verify();
    }
    @Test(description = "Close the game from the game page and return to the 'Lobby' page")
    public void closeGame(){
        lobbyPage
                .clickOnSearch()
                .getSearchModalTitle().verify();
        lobbyPage
                .enterSearchText("Mad Cars");
        lobbyPage
                .playSearchedGame()
                .getGamePageTitle1().verify();
        lobbyPage
                .clickOnBackGameBtnAndVerifyLobbyPage();
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
}
