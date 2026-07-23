package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;

public class VipPage extends BaseElement {

    private final By vipPageTitle = By.cssSelector(".title");

    @Getter
    TextField VipPageTitle = new TextField(vipPageTitle, "The 'VIP' page title");

}
