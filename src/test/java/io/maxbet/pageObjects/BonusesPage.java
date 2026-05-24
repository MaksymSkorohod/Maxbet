package io.maxbet.pageObjects;

import io.maxbet.Elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;

public class BonusesPage extends AbstractPage{
    private final By bonusesPageTitle = By.xpath("//h2[normalize-space()='Bonus History']");

    @Getter
    TextField BonusesPageTitle = new TextField(bonusesPageTitle, "The 'Bonuses' page title");
}
