package io.maxbet.tests;

import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.LoginPage;
import org.testng.annotations.Test;

public class LogInTests extends TestBase{
    @Override
    public void logIn(){lobbyPage =  new LobbyPage();}
    @Test(description = "Successfull Login")
    public void loginSuccess(){
        new LobbyPage()
                .clickOnLoginButton()
                .getLoginRegisterModal().isExists();
        new LobbyPage()
                .enterUsername("James_Bond")
                .enterPassword("Vfrcbv82")
                .clickLoginButton();
    }
}
