package io.maxbet.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextField extends BaseElement{
    public TextField(By locator, String description) {
        super(locator, description);
    }

    public String getText(){
        return get().getText();
    }

    /**
     * Reads the current value of an input. {@link #getText()} returns an empty string for
     * {@code <input>} elements because they have no text node, and the value a datepicker or a
     * script writes lives on the DOM property rather than on the original HTML attribute.
     */
    public String getValue(){
        String value = get().getDomProperty("value");
        return value == null ? "" : value;
    }

    public boolean isDisabled(){
        WebElement element = get();
        return !element.isEnabled() || "true".equals(element.getDomAttribute("aria-disabled"));
    }
}
