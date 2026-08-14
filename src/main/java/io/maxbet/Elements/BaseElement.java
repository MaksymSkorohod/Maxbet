package io.maxbet.Elements;
import io.maxbet.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import static io.maxbet.DriverManager.getDriver;

public class BaseElement {
    private By locator;
    private String description;
    private String lastBorder;
    private WebElement lastElement;
    private By element;

    public BaseElement(By locator, String description){
        this.locator = locator;
        this.description = description;
    }
    protected By getLocator(){
        return locator;
    }

    protected WebElement get(){
        WebElement element = getDriver().findElement(locator);
        highlight(element);
        return element;
    }
    private void highlight(WebElement element) {
        unhighlight();

        lastElement = element;
        lastBorder = (String) ((JavascriptExecutor) getDriver()).executeScript(
                "const previousStyle = arguments[0].getAttribute('style');" +
                        "arguments[0].setAttribute('style', arguments[1]);" +
                        "return previousStyle;",
                element,
                "color: red; border: 2px solid yellow;"
        );

    }
    private void unhighlight() {
        if (lastElement != null) {
            try {
                ((JavascriptExecutor) getDriver()).executeScript(
                        "arguments[0].setAttribute('style', arguments[1] || '');",
                        lastElement,
                        lastBorder
                );
            } catch (StaleElementReferenceException | NoSuchElementException ignored) {
                // Element is gone, nothing to restore.
            } finally {
                lastElement = null;
                lastBorder = null;
            }
        }
    }
    public boolean isExists(long...timeout){
        long currentTimeout = timeout.length > 0 ? timeout[0] : 10;

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(currentTimeout));
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            get();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    public boolean invisibilityOfElementLocated(long... timeout) {
        long currentTimeout = timeout.length > 0 ? timeout[0] : 15;

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(currentTimeout));
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
    }
    public void waitPageStability() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("mask")));
         wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner")));
    }
    public void waitUntilInvisibilityOfElementLocated(By elementLocator, long... timeout) {
        long currentTimeout = timeout.length > 0 ? timeout[0] : 10;

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(currentTimeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }
    public void verify(){
        Assert.assertTrue(
                isExists(), "Element is not visible: " + description + ". Locator: " + locator);
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public void printCurrentUrl() {
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }
    public boolean isUrlPathContains(String expectedPart) {
        return driver.getCurrentUrl().contains(expectedPart);
    }
    public boolean isUrlContains(String text) {
        return driver.getCurrentUrl().contains(text);
    }

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final By mask =
            By.cssSelector(".mask");
    public BaseElement() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private static final long DEFAULT_CLICK_TIMEOUT_SECONDS = 10;

    public void click() {
        WebElement clickableElement = waitUntilClickable(DEFAULT_CLICK_TIMEOUT_SECONDS);
        try {clickableElement.click();}
        catch (ElementClickInterceptedException e) {
            clickWithJavaScript(clickableElement);
        }
    }
    private WebElement waitUntilClickable(long timeoutSeconds) {
        WebElement clickableElement = new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(timeoutSeconds)
        ).until(ExpectedConditions.elementToBeClickable(getLocator()));

        highlight(clickableElement);
        return clickableElement;
    }
    private void clickWithJavaScript(WebElement clickableElement) {
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].click();",
                clickableElement
        );
    }
    public void waitUntilUrlContains(String expectedPart) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains(expectedPart));
    }
    public void clickElement(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(mask));
        WebElement element =
                wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }
    public void doubleClickButton() {
        WebElement element = new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(getLocator()));

        new Actions(getDriver())
                .doubleClick(element)
                .perform();
    }
    public void clickRegButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(getLocator()));

        // Даємо формі завершити валідацію
        new Actions(getDriver())
                .moveToElement(element)
                .pause(Duration.ofMillis(500))
                .click()
                .perform();
    }
}
