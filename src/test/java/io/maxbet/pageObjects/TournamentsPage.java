package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import lombok.Getter;
import org.openqa.selenium.By;

public class TournamentsPage extends BaseElement {

    private final By tournamentsPageNavList = By.cssSelector(".mb-nav-list__container.hidden-scroll");

    @Getter
    Button TournamentsPageNavList = new Button(tournamentsPageNavList, "The 'Tournaments' page navigation list");
}
