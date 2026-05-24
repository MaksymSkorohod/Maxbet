package io.maxbet.pageObjects;

import io.maxbet.Elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;

public class ResponsibleGamblingPage extends AbstractPage{
    private final By responsibleGamblingPageTitle = By.xpath("//h2[normalize-space()='Deposit limits EN']");

    @Getter
    TextField ResponsibleGamblingPageTitle = new TextField(responsibleGamblingPageTitle, "The 'Responsible Gambling' page title");
}
