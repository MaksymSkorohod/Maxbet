package io.maxbet.Elements;

import io.maxbet.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PopupHandler {
    public static void acceptCookiesIfPresent() {

        WebDriver driver = DriverManager.getDriver();

        if (driver == null) {
            return;
        }

        try {

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(5));

            WebElement host =
                    wait.until(d ->
                            d.findElement(By.id("usercentrics-cmp-ui"))
                    );

            SearchContext shadowRoot =
                    host.getShadowRoot();

            WebElement button =
                    shadowRoot.findElement(By.id("accept"));

            if (button.isDisplayed()) {

                ((JavascriptExecutor) driver)
                        .executeScript(
                                "arguments[0].click();",
                                button
                        );
            }

        } catch (Exception ignored) {

        }
    }
}
