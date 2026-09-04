package io.maxbet.tests;
import io.maxbet.listeners.TestGroups;

import io.maxbet.Units.CnpGenerator;
import io.maxbet.Units.EmailGenerator;
import io.maxbet.Units.PhoneNumberGenerator;
import io.maxbet.Units.UsernameGenerator;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.maxbet.DriverManager.getDriver;

public class LogInTests extends TestBase {
    LoginPage loginPage = new LoginPage();
    @Override
    public void logIn() {
        lobbyPage = new LobbyPage();

    }
    @Test(description = "Open the Login modal")
    public void openLoginModal() {
        getDriver().get("https://dev.maxbet.ro/en");
       loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        loginPage
                .clickOnLoginButton();
        Assert.assertTrue(
                new LoginPage().getLogoOnLoginModal().isExists(15),
                "The Login modal is not displayed"
        );
    }
    @Test(groups = {TestGroups.SMOKE}, description = "Successful Login")
    public void loginSuccess() {
        getDriver().get("https://dev.maxbet.ro/en");
      loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
       loginPage
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
    @Test(description = "Login with a non-existing username")
    public void loginWithIncorrectUsername() {
        getDriver().get("https://dev.maxbet.ro/en");
        loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        loginPage
                .clickOnLoginButton()
                .enterUsername(UsernameGenerator.generate())
                .enterPassword("Vfrcbv82")
                .clickOnLogin();
        Assert.assertTrue(
                new LoginPage().getWrongUsernameModal().isExists(15),
                "The 'Wrong Username' warning modal is not displayed for a non-existing username"
        );
        Assert.assertFalse(
                new LoginPage().getUserInfo().isExists(3),
                "The user was signed in with a non-existing username"
        );
    }
    @Test(description = "Login with an incorrect password")
    public void loginWithIncorrectPassword() {
        getDriver().get("https://dev.maxbet.ro/en");
        loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        loginPage
                .clickOnLoginButton()
                .enterUsername("James_Bond")
                .enterPassword("WrongPassword123")
                .clickOnLogin();
        Assert.assertTrue(
                new LoginPage().getWrongPasswordWarning().isExists(15),
                "The 'Wrong Password' warning is not displayed for an incorrect password"
        );
        Assert.assertFalse(
                new LoginPage().getUserInfo().isExists(3),
                "The user was signed in with an incorrect password"
        );
    }
    @Test(description = "Open the Forgot password")
    public void openForgotPassword() {
        getDriver().get("https://dev.maxbet.ro/en");
        loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
       loginPage
                .clickOnLoginButton()
                .getLoginRegisterModal().verify();
        loginPage
                .clickOnForgotPasswordLink();
        Assert.assertTrue(
                new LoginPage().getForgotPasswordTitle().isExists(10),
                "The Forgot password modal is not visible"
        );
    }
    @Test(description = "Enter an email address on the Forgot password")
    public void enterEmailOnForgotPasswordModal() {
        getDriver().get("https://dev.maxbet.ro/en");
       loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        loginPage
                .clickOnLoginButton()
                .getLoginRegisterModal().verify();
        loginPage
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
        loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        loginPage
                .clickOnRegisterButton();
        Assert.assertTrue(
                new LoginPage().getRegisterTabLocator().isExists(15), "Register modal is not displayed"
        );
    }
    @Test(description = "Complete Step 1 for Registration")
    public void completeStep1(){
        getDriver().get("https://dev.maxbet.ro/en");
       loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        loginPage
                .clickOnRegisterButton()
                .getRegisterTabLocator().click();
       loginPage
                .enterFirstName("Test")
                .enterLastName("User")
                .enterCnpCode(CnpGenerator.generate())
                .enterCityTown("Bucharest")
                .clickOnCityTownOptions()
                .enterAddress("Test street")
                .clickOnTermsAndConditionCheckbox()
                .clickOnContinueButton()
                .getRegisterTabLocator().verify();
    }
    @Test(description = "Complete Registration")
    public void completeRegistration() throws InterruptedException {
        getDriver().get("https://dev.maxbet.ro/en");
        loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        loginPage
                .clickOnRegisterButton()
                .getRegisterTabLocator().click();
        loginPage
                .enterFirstName("Test")
                .enterLastName("User")
                .enterCnpCode(CnpGenerator.generate())
                .enterCityTown("Bucharest")
                .clickOnCityTownOptions()
                .enterAddress("Test street")
                .clickOnTermsAndConditionCheckbox()
                .clickOnContinueButton()
                .getRegisterTabStep2().verify();
        loginPage
                .enterEmailAddress(EmailGenerator.generate())
                .enterPhoneNumber(PhoneNumberGenerator.generate())
                .enterUserName(UsernameGenerator.generate())
                .setPassword("Qwerty123");
        Thread.sleep(1000);
        loginPage
                .clickOnSignupButton();
        loginPage
                .waitUntilSuccessfulRegistrationDisplayed();
        loginPage
                .getSuccessfulRegistrationTitle().verify();
        loginPage
                .clickOnPage()
                .clickCloseBtnSuccessfulRegistrationModal();
    }
    @Test(description = "Verify Required Field Validation Message On Registration Step 1")
    public void verifyRequiredFieldValidationMessagesOnRegistrationStep1(){
        getDriver().get("https://dev.maxbet.ro/en");
        loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        loginPage
                .clickOnRegisterButton() .
                getRegisterTabLocator().click();
        loginPage
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
        getDriver().get("https://dev.maxbet.ro/en");
        loginPage
                .acceptCookiesIfPresent()
                .waitUntilMaskDisappears();
        loginPage
                .clickOnRegisterButton()
                .getRegisterTabLocator().click();
        loginPage
                .enterFirstName("Test")
                .enterLastName("User")
                .enterCnpCode(CnpGenerator.generate())
                .enterCityTown("Bucharest")
                .clickOnCityTownOptions()
                .enterAddress("Test street")
                .clickOnTermsAndConditionCheckbox()
                .clickOnContinueButton()
                .getRegisterTabLocator().verify();
        loginPage
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

