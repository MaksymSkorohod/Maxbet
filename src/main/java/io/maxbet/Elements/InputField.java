package io.maxbet.Elements;
import org.openqa.selenium.By;

public class InputField extends TextField{
    public InputField(By locator, String description) {
        super(locator, description);
    }

    public void setText(String text){
        get().sendKeys(text);
    }

    public void clear(){
        get().clear();
    }
}
