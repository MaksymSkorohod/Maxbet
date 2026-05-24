package io.maxbet.pageObjects;

import io.maxbet.Elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;

public class LegalPage extends AbstractPage{
    private final By legalPage = By.cssSelector(".mb-content-layout.context-profile.mobile-header-fixed");

    @Getter
    TextField LegalPage = new TextField(legalPage, "The 'Legal' page");
}
