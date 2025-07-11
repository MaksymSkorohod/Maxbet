package io.maxbet.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static io.maxbet.DriverManager.getDriver;

public class BaseElement {
    private By locator;
    private String description;
    private String lastBorder;
    private WebElement lastElement;

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
        lastBorder = (String) ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 2px solid yellow;");
    }

    private void unhighlight() {
        if (lastElement != null) {
            try {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('style', arguments[1]);", lastElement, lastBorder);
            } finally {
                lastElement = null;
            }
        }
    }

    public boolean isExists(long...timeout){
        long currentTimeout = 5;
        if(timeout.length > 0) {
            currentTimeout = timeout[0];
        }
        WebDriverWait wait = new WebDriverWait(getDriver(),currentTimeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            get();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnable(long... timeout) {
        if(isExists(timeout)){
            return get().isEnabled();
        }
        return false;
    }

    public void verify(){
        Assert.assertTrue(isExists());
    }


    private void highlightElement(WebElement element){
        unhighlightLast();
        lastElement = element;
        lastBorder = (String)((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('style, arguments[1]);",element, "color:yellow; border: 2px solid yellow;");
    }

    private void unhighlightLast(){
        if (lastElement != null){
            try {
                ((JavascriptExecutor) getDriver()). executeScript("arguments[0].setAttribute('style, arguments[1]);", lastElement, lastBorder);
            } finally{
                lastElement = null;
            }
        }
    }

    private void waitUntil(){

    }
}
