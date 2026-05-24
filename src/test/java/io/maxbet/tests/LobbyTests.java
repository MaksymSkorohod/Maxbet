package io.maxbet.tests;

import io.maxbet.pageObjects.LobbyPage;
import org.testng.annotations.Test;

public class LobbyTests extends TestBase{
    @Test(description = "Open Profile page")
    public void openProfilePage(){
        new LobbyPage()
                .clickOnUserInfo();
    }
    @Test(description = "Open Search page")
    public void openSearchPage(){
        new LobbyPage()
                .clickOnSearch()
                .getSearchModalTitle().verify();
    }
    @Test(description = "Close Search modal")
    public void closeSearchModal(){
        new LobbyPage()
                .clickOnSearch()
                .getSearchModalTitle().verify();
        new LobbyPage()
                .clickToCloseSearchModal()
                .getUserInfo().verify();
    }
    @Test(description = "Enter the text in the Search modal")
    public void enterGameIntoSearchModal(){
        new LobbyPage()
                .clickOnSearch()
                .getSearchModalTitle().verify();
        new LobbyPage()
                .enterSearchText("Mad Cars");
    }
    @Test(description = "Open the 'Promotions' page")
    public void openPromotionsPage(){
        new LobbyPage()
                .clickOnPromotions()
                .getWelcomeOffersBtn().verify();
    }
    @Test(description = "Open the 'Tournaments' page")
    public void openTournamentsPage(){
        new LobbyPage()
                .clickOnTournaments()
                .getTournamentsPageNavList().verify();
    }
}
