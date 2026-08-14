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

    /**
     * Waits for the button to be clickable and clicks it. The element is looked up again on
     * every poll, so a re-render between the lookup and the click is retried with a fresh
     * reference instead of throwing StaleElementReferenceException. Angular replaces nodes
     * while the page settles, which makes a reference held across two statements unsafe.
     */
    public void clickButton(){
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until(driver -> {
                    WebElement element = ExpectedConditions
                            .elementToBeClickable(getLocator())
                            .apply(driver);
                    if (element == null) {
                        return false;
                    }
                    element.click();
                    return true;
                });
    }
    public void clickAnywhereOnPage() {

        Actions actions = new Actions(getDriver());

        actions
                .moveByOffset(10, 10)
                .click()
                .perform();
    }

    public void click(By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        }
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
    public boolean clickButtonInShadowRootByJsIfPresent(
            String shadowHostId,
            String buttonId,
            long timeoutSeconds) {

        try {

            WebDriverWait wait = new WebDriverWait(
                    getDriver(),
                    Duration.ofSeconds(timeoutSeconds));

            return wait.until(driver -> {

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

        } catch (TimeoutException e) {
            return false;
        }
    }


}
