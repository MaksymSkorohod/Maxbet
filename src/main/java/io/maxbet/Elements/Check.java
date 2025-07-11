package io.maxbet.Elements;
import org.openqa.selenium.By;

public class Check extends BaseElement{
    public Check(By locator, String description) {
        super(locator, description);
    }

    public boolean isSelected(){
        return get().isSelected();
    }

    public void select(){
        if (!isSelected())
            get().click();
    }
    public void unSelect(){
        if (isSelected())
            get().click();
    }
}
