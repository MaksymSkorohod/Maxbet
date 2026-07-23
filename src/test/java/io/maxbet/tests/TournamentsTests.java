package io.maxbet.tests;

import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.TournamentsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TournamentsTests extends TestBase{
    private TournamentsPage tournamentsPage;

    @BeforeMethod
    public void tournamentsPageIsOpen(){
        tournamentsPage = new LobbyPage().clickOnTournaments();

    }
    @Test(description = "Open the 'Tournaments' page")
    public void openTournamentsPage(){
        Assert.assertTrue(tournamentsPage.isTournamentPageOpened(), "The Tournaments page nav list is not displaying");
    }
    @Test(description = "Open the 'Upcoming' from the 'Tournaments' page")
    public void openUpcomingTournaments(){
        tournamentsPage
                .getTournamentsPageNavList().verify();
        tournamentsPage
                .clickOnUpcomingTournaments();
    }
    @Test(description = "Open the 'Finished' from the 'Tournaments' page")
    public void openFinishedTournaments(){
        tournamentsPage
                .getTournamentsPageNavList().verify();
        tournamentsPage
                .clickOnFinishedTournaments();
    }
}
