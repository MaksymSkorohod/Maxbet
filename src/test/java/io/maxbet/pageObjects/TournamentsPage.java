package io.maxbet.pageObjects;

import io.maxbet.DriverManager;
import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TournamentsPage extends BaseElement {

    private final By tournamentsPageNavList = By.cssSelector(".mb-nav-list__container.hidden-scroll");
    private final By liveTournamentsBtn = By.cssSelector(".nav-list-item.active.ng-star-inserted");
    private final By upcomingTournamentsBtn = By.cssSelector("a[data-fs-properties='{\"status\":\"upcoming\"}']");
    private final By finishedTournamentsBtn = By.cssSelector("a[data-fs-properties='{\"status\":\"ended\"}']");

    @Getter
    Button TournamentsPageNavList = new Button(tournamentsPageNavList, "The 'Tournaments' page navigation list");
    @Getter
    Button LiveTournamentsBtn = new Button(liveTournamentsBtn, "The 'Live Tournaments' button");
    @Getter
    Button UpcomingTournamentsBtn = new Button(upcomingTournamentsBtn, "The 'Upcoming Tournaments' button");
    @Getter
    Button FinishedTournamentsBtn = new Button(finishedTournamentsBtn, "The 'Finished Tournaments' button");

    @Step("Click on the 'Live Tournaments' button")
    public void clickOnLiveTournaments() {
        TournamentsPageNavList.waitPageStability();
        getLiveTournamentsBtn().clickButton();
    }
    @Step("Click on the 'Upcoming Tournaments' button")
    public void clickOnUpcomingTournaments() {
        TournamentsPageNavList.waitPageStability();
        getUpcomingTournamentsBtn().clickButton();
    }
    @Step("Click on the 'Finished Tournaments' button")
    public void clickOnFinishedTournaments() {
        TournamentsPageNavList.waitPageStability();
        getFinishedTournamentsBtn().clickButton();
    }

    public boolean isTournamentPageOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(tournamentsPageNavList));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
