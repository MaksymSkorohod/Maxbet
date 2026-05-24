package io.maxbet.pageObjects;

import io.maxbet.Elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;

public class MyDetailsPage extends AbstractPage{
    private final By myDetailsSection = By.cssSelector(".mb-menu-item.variant--compact[link='/profile/my-details']");

    @Getter
    TextField MyDetailsSection = new TextField(myDetailsSection, "The 'My Details' section");
}
