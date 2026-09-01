package io.maxbet.tests;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.maxbet.DriverManager.getDriver;
import static io.maxbet.DriverManager.killDriver;

public class TestBase {
    protected static final String BASE_URL = "https://dev.maxbet.ro/en";
    protected static final String USERNAME = "auto_user1";
    protected static final String PASSWORD = "Qwerty123";

    protected LobbyPage lobbyPage;

    @BeforeMethod(alwaysRun = true)
    public void logIn(){
        getDriver().get(BASE_URL);
        LoginPage loginPage = new LoginPage();
        loginPage.acceptCookiesIfPresent();
        loginPage.waitUntilMaskDisappears();
        if (loginPage.isUserLoggedIn()) {
            return;
        }
        loginPage
                .clickOnLoginButton()
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .clickOnLogin()
                .clickOnPage()
                .clickOnAcceptNotification();
        Assert.assertTrue(new LoginPage().isUserLoggedIn(),
                "Login with '" + USERNAME + "' did not sign the user in");
    }
   @AfterMethod(alwaysRun = true)
   public void close(){killDriver();}
}
