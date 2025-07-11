package io.maxbet.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class DropDownList extends TextField {
    public DropDownList(By locator, String description) {
        super(locator, description);
    }

    public void select(int id){
        getSelectElement().selectByIndex(id);
    }

    public void SelectOption(String text){
        getSelectElement().selectByVisibleText(text);
    }

    private Select getSelectElement(){
        return new Select(get());
    }

    public void click(){
        get().click();
    }
}
