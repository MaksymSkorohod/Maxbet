package io.maxbet.tests;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.LoginPage;
import org.testng.annotations.*;

import static io.maxbet.DriverManager.getDriver;
import static io.maxbet.DriverManager.killDriver;

public class TestBase {
    // The environment and the account are read from system properties so a CI run can point the
    // suite at another environment and pass the credentials in without a code change, and so the
    // password is not the one hard coded in source. The defaults keep a local run working as before.
    protected static final String BASE_URL = System.getProperty("maxbet.baseUrl", "https://dev.maxbet.ro/en");
    protected static final String USERNAME = System.getProperty("maxbet.user", "auto_user1");
    protected static final String PASSWORD = System.getProperty("maxbet.password", "Qwerty123");

    protected LobbyPage lobbyPage;

    @BeforeMethod(alwaysRun = true)
    public void logIn(){
        getDriver().get(BASE_URL);
        new LoginPage().login(USERNAME, PASSWORD);
    }
  // @AfterMethod(alwaysRun = true)
  // public void close(){killDriver();}
}
