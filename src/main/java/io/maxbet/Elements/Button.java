package io.maxbet.Elements;

import org.openqa.selenium.By;

import java.awt.*;

public class Button extends TextField {
    public Button(By locator, String description) {
        super(locator, description);
    }

    public void clickButton(){
        get().click();
    }
}
