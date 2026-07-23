package io.maxbet.tests;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.maxbet.DriverManager.getDriver;
import static io.maxbet.DriverManager.killDriver;

public class TestBase {
    protected LobbyPage lobbyPage;

    @BeforeMethod
    public void logIn(){
        getDriver().get("https://dev.maxbet.ro/en");
     new LoginPage()
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
     new LoginPage()
                .clickOnLoginButton()
                .enterUsername("James_Bond")
                .enterPassword("Vfrcbv82")
                .clickOnLogin()
                .clickOnPage()
                .clickOnAcceptNotification();
    }

//    @AfterMethod
//    public void close(){killDriver();}
}
