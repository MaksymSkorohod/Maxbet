package io.maxbet.tests;

import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.maxbet.DriverManager.getDriver;

public class LogInTests extends TestBase {

    @Override
    public void logIn() {
        lobbyPage = new LobbyPage();
    }
    @Test(description = "Successful Login")
    public void loginSuccess() {
        getDriver().get("https://dev.maxbet.ro/en");
        new LoginPage()
                .clickAcceptButtonJs()
                .clickOnLoginButton()
                .enterUsername("James_Bond")
                .enterPassword("Vfrcbv82")
                .clickOnLogin()
                .clickOnPage()
                .clickOnAcceptNotification();
        Assert.assertTrue(
                new LoginPage().getUserInfo().isExists(15),//15
                "User info is not visible after successful login"
        );
    }
}


