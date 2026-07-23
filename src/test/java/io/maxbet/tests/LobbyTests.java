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
    @Test(description = "Open the 'Betting' page")
    public void openBettingPageFromHeader(){
        lobbyPage
                .getHeaderLiveCasinoBtn().verify();
        lobbyPage
                .openBettingPage();
    }
}
