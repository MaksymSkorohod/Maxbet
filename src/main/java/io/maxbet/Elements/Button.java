package io.maxbet.Elements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

import static io.maxbet.DriverManager.getDriver;

public class Button extends TextField {
    public Button(By locator, String description) {
        super(locator, description);
    }

    public void clickButton(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(getLocator()));
        element.click();
    }
    public void clickButtonJs(){
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", get());
    }
    public void clickAnywhereOnPage() {

        Actions actions = new Actions(getDriver());

        actions
                .moveByOffset(10, 10)
                .click()
                .perform();
    }
    public void clickButtonInShadowRoot(By shadowHostLocator) {
        clickButtonInShadowRoot(shadowHostLocator, 10);
    }

    public void clickButtonInShadowRoot(By shadowHostLocator, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));

        WebElement element = wait.until(driver -> {
            try {
                WebElement shadowHost = driver.findElement(shadowHostLocator);
                SearchContext shadowRoot = shadowHost.getShadowRoot();

                WebElement button = shadowRoot.findElement(getLocator());

                if (button.isDisplayed() && button.isEnabled()) {
                    return button;
                }

                return null;
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                return null;
            }
        });

        element.click();
    }

    public void clickButtonInShadowRootByJs(String shadowHostId, String buttonId, long timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSeconds));

        wait.until(driver -> {
            Object result = ((JavascriptExecutor) driver).executeScript(
                    "const host = document.getElementById(arguments[0]);" +
                            "if (!host || !host.shadowRoot) return false;" +
                            "const button = host.shadowRoot.getElementById(arguments[1]);" +
                            "if (!button) return false;" +
                            "button.click();" +
                            "return true;",
                    shadowHostId,
                    buttonId
            );
            return Boolean.TRUE.equals(result);
        });
    }


}
