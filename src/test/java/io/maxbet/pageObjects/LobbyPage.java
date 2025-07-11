package io.maxbet.pageObjects;

import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

public class LobbyPage {
    private By logInButton = By.xpath("//button[@class='btn-secondary auth xs-sm ng-star-inserted']");
    private By registerButton = By.xpath("//button[@class='btn-primary auth xs-sm ng-star-inserted']");
    private By loginRegisterModal = By.xpath("//div[@class='auth-component']");
    private By userNameField = By.xpath("//div[@class='input']");
    private By passwordField = By.id("password");
    private By login = By.xpath(" //button[normalize-space()='Login']");


    @Getter
    Button LogInButton = new Button(logInButton,"The 'Login button'");
    @Getter
    Button RegisterButton = new Button(registerButton,"The 'Register button'");
    @Getter
    TextField LoginRegisterModal = new TextField(loginRegisterModal, "The Login/Register modal");
    @Getter
    InputField UserNameField = new InputField(userNameField,"The Username/Email input field");
    @Getter
    InputField PasswordField = new InputField(passwordField,"The Password input field");
    @Getter
    Button Login =  new Button(login,"The 'Login button'");

    @Step("Click on the 'Login' button")
    public LobbyPage clickOnLoginButton() {
        getLogInButton().clickButton();
        return this;
    }
    @Step("Click on the 'Register' button from the 'Lobby' page")
    public LobbyPage clickOnRegisterButton() {
        getRegisterButton().clickButton();
        return this;
    }
    @Step("Enter username")
    public LobbyPage enterUsername(String username) {
        getUserNameField().clear();
        getUserNameField().setText(username);
        System.out.println(username);
        return this;
    }
    @Step("Enter the password")
    public LobbyPage enterPassword(String password) {
        getPasswordField().clear();
        getPasswordField().setText(password);
        return this;
    }
    @Step("Click on the Login button")
    public LobbyPage clickLoginButton() {
        getLogin().clickButton();
        return this;
    }
}
