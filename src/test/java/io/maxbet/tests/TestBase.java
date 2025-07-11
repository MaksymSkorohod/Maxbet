package io.maxbet.tests;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.LoginPage;
import org.testng.annotations.*;

import static io.maxbet.DriverManager.getDriver;
import static io.maxbet.DriverManager.killDriver;

public class TestBase {
    protected LobbyPage lobbyPage;

    @BeforeMethod
    public void logIn(){
        lobbyPage = new LobbyPage()
                .clickOnLoginButton()
                .enterUsername("James_Bond")
                .enterPassword("Vfrcbv82")
                .clickLoginButton();
    }

    @BeforeMethod
    public void openLobbyPage(){getDriver().get("https://dev.maxbet.ro/");
    }
    @AfterMethod
    public void close(){killDriver();}

}
