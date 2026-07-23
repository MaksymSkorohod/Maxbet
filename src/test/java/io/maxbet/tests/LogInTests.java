package io.maxbet.tests;

import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

import static io.maxbet.DriverManager.getDriver;

public class LogInTests extends TestBase {

    @Override
    public void logIn() {
        lobbyPage = new LobbyPage();

    }
    @Test(description = "Open the Login modal")
    public void openLoginModal() {
        getDriver().get("https://dev.maxbet.ro/en");
        new LoginPage()
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        new LoginPage()
                .clickOnLoginButton();
        Assert.assertTrue(
                new LoginPage().getLogoOnLoginModal().isExists(15),
                "The Login modal is not displayed"
        );
    }
    @Test(description = "Successful Login")
    public void loginSuccess() {
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
        Assert.assertTrue(
                new LoginPage().getUserInfo().isExists(15),
                "User info is not visible after successful login"
        );
    }
    @Test(description = "Open the Forgot password")
    public void openForgotPassword() {
        getDriver().get("https://dev.maxbet.ro/en");
        new LoginPage()
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        new LoginPage()
                .clickOnLoginButton()
                .getLoginRegisterModal().verify();
        new LoginPage()
                .clickOnForgotPasswordLink();
        Assert.assertTrue(
                new LoginPage().getForgotPasswordTitle().isExists(10),
                "The Forgot password modal is not visible"
        );
    }
    @Test(description = "Enter an email address on the Forgot password")
    public void enterEmailOnForgotPasswordModal() {
        getDriver().get("https://dev.maxbet.ro/en");
        new LoginPage()
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        new LoginPage()
                .clickOnLoginButton()
                .getLoginRegisterModal().verify();
        new LoginPage()
                .clickOnForgotPasswordLink();
        Assert.assertTrue(
                new LoginPage().getForgotPasswordTitle().isExists(10),
                "The Forgot password modal is not visible"
        );
        new LoginPage()
                .enterEmailIntoEmailInput("sometest1233@mail.com");
    }
    @Test(description = "Open register modal")
    public void openRegisterModal(){
        getDriver().get("https://dev.maxbet.ro/en");
        new LoginPage()
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        new LoginPage()
                .clickOnRegisterButton();
        Assert.assertTrue(
                new LoginPage().getRegisterTabLocator().isExists(15), "Register modal is not displayed"
        );
    }
    @Test(description = "Complete Step 1 for Registration")
    public void completeStep1(){
        getDriver().get("https://dev.maxbet.ro/en");
        new LoginPage()
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        new LoginPage()
                .clickOnRegisterButton()
                .getRegisterTabLocator().click();
        new LoginPage()
                .enterFirstName("Test")
                .enterLastName("User")
                .enterCnpCode("1991117416834")
                .enterCityTown("Bucharest")
                .clickOnCityTownOptions()
                .enterAddress("Test street")
                .clickOnTermsAndConditionCheckbox()
                .clickOnContinueButton()
                .getRegisterTabLocator().verify();
    }
    @Test(description = "Complete Registration")
    public void completeRegistration(){
        int randomNumber = new Random().nextInt(100000);
        getDriver().get("https://dev.maxbet.ro/en");
        new LoginPage()
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        new LoginPage()
                .clickOnRegisterButton()
                .getRegisterTabLocator().click();
        new LoginPage()
                .enterFirstName("Test")
                .enterLastName("User")
                .enterCnpCode("1991117416834")
                .enterCityTown("Bucharest")
                .clickOnCityTownOptions()
                .enterAddress("Test street")
                .clickOnTermsAndConditionCheckbox()
                .clickOnContinueButton()
                .getRegisterTabLocator().verify();
        new LoginPage()
                .enterEmailAddress("testuseremail" + randomNumber + "@mail.com")
                .enterPhoneNumber("0711000879")
                .enterUserName("TestUser" + randomNumber)
                .setPassword("Qwerty123");
    }
    @Test(description = "Verify Required Field Validation Message On Registration Step 1")
    public void verifyRequiredFieldValidationMessagesOnRegistrationStep1(){
        getDriver().get("https://dev.maxbet.ro/en");
        new LoginPage()
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        new LoginPage()
                .clickOnRegisterButton() .
                getRegisterTabLocator().click();
        new LoginPage()
                .clickOnContinueButton();
        Assert.assertTrue( new LoginPage().getFirstNameRequired().isExists(10),
                "First Name validation message is not displayed" );
        Assert.assertTrue( new LoginPage().getLastNameRequired().isExists(5),
                "Last Name validation message is not displayed" );
        Assert.assertTrue( new LoginPage().getCnpRequired().isExists(5),
                "CNP validation message is not displayed" );
        Assert.assertTrue( new LoginPage().getCityTownRequired().isExists(5),
                "City/Town validation message is not displayed" );
        Assert.assertTrue( new LoginPage().getAddressRequired().isExists(5),
                "Address validation message is not displayed" );
        Assert.assertTrue( new LoginPage().getTermsAndConditionRequired().isExists(5),
                "Terms & Conditions validation message is not displayed" );
    }
    @Test(description = "Verify Required Field Validation Message On Registration Step 2")
    public void verifyRequiredFieldValidationMessagesOnRegistrationStep2() {
        int randomNumber = new Random().nextInt(100000);
        getDriver().get("https://dev.maxbet.ro/en");
        new LoginPage()
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        new LoginPage()
                .clickOnRegisterButton()
                .getRegisterTabLocator().click();
        new LoginPage()
                .enterFirstName("Test")
                .enterLastName("User")
                .enterCnpCode("1991117416834")
                .enterCityTown("Bucharest")
                .clickOnCityTownOptions()
                .enterAddress("Test street")
                .clickOnTermsAndConditionCheckbox()
                .clickOnContinueButton()
                .getRegisterTabLocator().verify();
        new LoginPage()
                .clickOnSignupButton();
        Assert.assertTrue( new LoginPage().getEmailRequired().isExists(10),
                "Email validation message is not displayed" );
        Assert.assertTrue( new LoginPage().getPhoneNumber().isExists(5),
                "Phone number validation message is not displayed" );
        Assert.assertTrue( new LoginPage().getUserNameRequired().isExists(5),
                "Username validation message is not displayed" );
        Assert.assertTrue( new LoginPage().getPasswordRequired().isExists(5),
                "Password validation message is not displayed" );
    }
}


