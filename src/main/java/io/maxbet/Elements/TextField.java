package io.maxbet.Elements;

import org.openqa.selenium.By;

public class TextField extends BaseElement{
    public TextField(By locator, String description) {
        super(locator, description);
    }

    public String getText(){
        return get().getText();
    }
}
