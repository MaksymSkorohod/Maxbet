package io.maxbet.pageObjects;

import io.maxbet.Elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;

public class AuthenticationPage extends AbstractPage{
    private final By authenticationPageTitle = By.cssSelector(".mb-menu-item.variant--compact[link='/profile/socials']");

    @Getter
    TextField AuthenticationPageTitle = new TextField(authenticationPageTitle, "The 'Authentication' page title");
}
