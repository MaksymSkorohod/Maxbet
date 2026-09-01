package io.maxbet.pageObjects;
import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import static io.maxbet.DriverManager.getDriver;

public class LoginPage extends BaseElement {

    private static final Logger log = LogManager.getLogger(LoginPage.class);

    private final By cookieBannerShadowHost = By.cssSelector("#usercentrics-root");
    private final Button acceptAllButton = new Button(By.id("accept"), "Accept All button");
    private final By loginBtn = By.cssSelector(".btn-secondary.auth.xs-sm");
    private final By registerButton = By.cssSelector(".btn-primary.auth.xs-sm");
    private final By authContainer = By.cssSelector(".auth-container");
    private final By logoOnLoginModal = By.cssSelector("div[class='header'] mb-logo");
    private final By userNameField = By.id("usernameOrEmail");
    private final By passwordField = By.id("password");
    private final By connectionBtn = By.cssSelector("#auth-modal .btn-primary.lg");
    private final By wrongUsernameModal = By.cssSelector(".default-dialog.warning");
    private final By wrongPasswordWarning = By.cssSelector("#auth-modal mb-sign-in form > div > div");
    private final By mask = By.cssSelector(".mask");
    private final By notificationDialog = By.cssSelector(".mat-mdc-dialog-surface.mdc-dialog__surface");
    private final By notificationAccept = By.cssSelector("button[class='mb-button btn btn-primary lg ng-star-inserted']");
    private final By notificationDecline = By.cssSelector("button[class='btn btn-transparent lg ng-star-inserted']");
    private final By userInfoLocator = By.cssSelector("div.right-block.notific-padding mb-header-user-info.user-info");
    private final By loginTab = By.cssSelector("div[class='tab-item']");
    private final By forgotPasswordLink = By.cssSelector(".only-text-white");
    private final By forgotPasswordTitle = By.cssSelector("div[class='title']");
    private final By emailInputForRestorePassword = By.id("email");
    private final By reCaptchaBtn = By.cssSelector(".cdk-overlay-container");
    private final By requestPasswordBtn = By.cssSelector(".mb-button.btn-primary.lg");
    //Step1
    private final By registerTab = By.cssSelector(".tab-item.active");
    private final By registerTabLocator = By.cssSelector("div[class='top-container'] div[class='title']");
    private final By firstName = By.cssSelector("input[name='firstName']");
    private final By lastName = By.cssSelector("input[name='lastName']");
    private final By cnpCode = By.cssSelector(".input-number.ng-untouched.ng-pristine.ng-invalid");
    private final By cityTown = By.cssSelector("input[role='combobox']");
    private final By cityTownOptions = By.cssSelector("#mat-option-1");
    private final By address = By.cssSelector("input[name='street-address']");
    private final By termsAndConditionCheckbox = By.cssSelector("label[for='terms_and_conditions-control']");
    private final By marketingAllCheckbox = By.cssSelector("label[for='marketing_all-control']");
    private final By continueBtn = By.cssSelector(".btn-primary.sm[mbfullstorytrackedelement='Auth.Registration.BTN_Continue']");
    //Step2
    private final By registerTabStep2 = By.cssSelector(".tab-item.active");
    private final By emailAddress = By.cssSelector("input[name='email']");
    private final By phoneNumber = By.cssSelector("input[placeholder='07XX XXX XXX']");
    private final By userName = By.cssSelector("input[name='username']");
    private final By passwordInput = By.cssSelector("#password");
    private final By hideModePasswordBtn = By.cssSelector(".inside-content");
    private final By currencyRonBtn = By.cssSelector("label[for='RON']");
    private final By currencyEurBtn = By.cssSelector("label[for='EUR']");
    private final By signupBtn = By.cssSelector(".mb-button.btn-primary.sm[mbbutton='primary'][mbfullstorytrackedelement='Auth.Registration.BTN_Signup']");
    private final By loader = By.cssSelector("#mb-root mb-busy-indicator .loader");

    //Successful registration modal
    private final By successfulRegistrationModal = By.cssSelector(".auth-container");
    private final By successfulRegistrationModalCloseBtn = By.cssSelector(".close.desktop");
    private final By successfulRegistrationTitle = By.cssSelector("div[class='registration-success'] div[class='title']");
    private final By codeInputCell0 = By.cssSelector("input[id='0']");
    private final By codeInputCell1 = By.cssSelector("input[id='1']");
    private final By codeInputCell2 = By.cssSelector("input[id='2']");
    private final By codeInputCell3 = By.cssSelector("input[id='3']");
    private final By continueActivationBtn = By.cssSelector("div[class='registration-success'] div[class='title']");
    //Warning messages for Step 1
    private final By firstNameRequired = By.xpath("//span[text()=\"First name is required\"]");
    private final By firstNameNoNumbers = By.xpath("//span[text()=\"First name cannot include numbers\"]");
    private final By lastNameRequired = By.xpath("//span[text()=\"Last name is required\"]");
    private final By lastNameNoNumbers = By.xpath("//span[text()=\"Last name cannot include numbers\"]");
    private final By cnpRequired = By.xpath("//span[text()=\"Cnp is required\"]");
    private final By cnpInvalid = By.xpath("//span[text()=\"Cnp length should be 13\"]");
    private final By cityTownRequired = By.xpath("//span[text()=\"This field is required\"]");
    private final By cityTownInvalid = By.xpath("//span[text()=\"Field is invalid\"]");
    private final By addressRequired = By.xpath("//span[text()=\"Address is required\"]");
    private final By termsAndConditionRequired = By.cssSelector("div[class='error ng-star-inserted']");
    //Warning messages for Step 2
    private final By emailRequired = By.xpath("//span[text()=\"Email address is required\"]");
    private final By emailInvalid = By.xpath("//span[text()=\"Please enter correct email address\"]");
    private final By phoneNumberRequired = By.xpath("//span[text()=\"Mobile number is required\"]");
    private final By phoneNumberInvalid = By.xpath("//span[text()=\"Please input the mobile number in the correct format (ex: 07xxxxxxxx)\"]");
    private final By userNameRequired = By.xpath("//span[text()=\"Username is required\"]");
    private final By passwordRequired = By.xpath("//span[text()=\"Password is required\"]");

    @Getter
    TextField PrivacySettings = new TextField(cookieBannerShadowHost,"Title of Privacy Settings popup");
    @Getter
    Button LoginButton = new Button(loginBtn,"The 'Login' button on the Lobby page");
    @Getter
    Button RegisterButton = new Button(registerButton,"The 'Register button'");
    @Getter
    TextField LoginRegisterModal = new TextField(authContainer, "The Login/Register modal");
    @Getter
    TextField LogoOnLoginModal = new TextField(logoOnLoginModal, "The Logo on the Login/Register modal");
    @Getter
    InputField UserNameField = new InputField(userNameField,"The Username/Email input field");
    @Getter
    InputField PasswordField = new InputField(passwordField,"The Password input field");
    @Getter
    Button ConnectionButton =  new Button(connectionBtn,"The 'Login button'");
    @Getter
    TextField WrongUsernameModal = new TextField(wrongUsernameModal,"The 'Wrong Username' modal");
    @Getter
    TextField WrongPasswordWarning = new TextField(wrongPasswordWarning,"The 'Wrong Password' warning");
    @Getter
    Button NotificationDialog = new Button(notificationDialog,"The Notification Dialog");
    @Getter
    Button NotificationAccept = new Button(notificationAccept,"The 'Accept' button in the Notification Dialog");
    @Getter
    Button NotificationDecline = new Button(notificationDecline,"The 'Decline' button in the Notification Dialog");
    @Getter
    Button UserInfo = new Button(userInfoLocator, "The User Info button");
    @Getter
    Button ForgotPasswordLink = new Button(forgotPasswordLink, "The Forgot Password link");
    @Getter
    TextField ForgotPasswordTitle = new TextField(forgotPasswordTitle, "The Forgot Password title");
    @Getter
    InputField EmailInputForRestorePassword = new InputField(emailInputForRestorePassword, "The Email input field for Restore Password");
    @Getter
    Button ReCaptchaBtn = new Button(reCaptchaBtn, "The ReCaptcha button");
    @Getter
    Button RequestPasswordBtn = new Button(requestPasswordBtn, "The Request Password button");

    //Registration Step 1
    @Getter
    Button LoginTab = new Button(loginTab, "The 'Login' tab");
    @Getter
    Button RegisterTab = new Button(registerTab, "The 'Register' tab");
    @Getter
    TextField RegisterTabLocator = new TextField(registerTabLocator, "The 'Register' tab locator");
    @Getter
    InputField FirstName = new InputField(firstName, "The First Name input field");
    @Getter
    InputField LastName = new InputField(lastName, "The Last Name input field");
    @Getter
    InputField CnpCode = new InputField(cnpCode, "The Cnp Code input field");
    @Getter
    InputField CityTown = new InputField(cityTown, "The City Town input field");
    @Getter
    Button CityTownOptions = new Button(cityTownOptions, "The City Town options");
    @Getter
    InputField Address = new InputField(address, "The Address input field");
    @Getter
    Button TermsAndConditionCheckbox = new Button(termsAndConditionCheckbox, "The Terms and Condition Checkbox");
    @Getter
    Button MarketingAllCheckbox = new Button(marketingAllCheckbox, "The Marketing All Checkbox");
    @Getter
    Button ContinueBtn = new Button(continueBtn, "The Continue button");

    //Registration Step 2
    @Getter
    TextField RegisterTabStep2 = new TextField(registerTabStep2, "The Register Tab Step 2");
    @Getter
    InputField EmailAddress = new InputField(emailAddress, "The Email Address input field");
    @Getter
    InputField PhoneNumber = new InputField(phoneNumber, "The Phone Number input field");
    @Getter
    InputField UserNameInput = new InputField(userName, "The User Name input field");
    @Getter
    InputField PasswordInput = new InputField(passwordInput, "The Password input field");
    @Getter
    Button HideModePasswordBtn = new Button(hideModePasswordBtn, "The Hide Mode Password Button");
    @Getter
    Button CurrencyRonBtn = new Button(currencyRonBtn, "The Currency Ron Button");
    @Getter
    Button CurrencyEurBtn = new Button(currencyEurBtn, "The Currency Eur Button");
    @Getter
    Button SignupBtn = new Button(signupBtn, "The Signup Button");
    @Getter
    TextField Loader = new TextField(loader, "The Loader");

    //Activation code
    @Getter
    TextField SuccessfulRegistrationModal = new TextField(successfulRegistrationModal, "The Successful Registration Modal");
    @Getter
    TextField SuccessfulRegistrationTitle = new TextField(successfulRegistrationTitle, "The Successful Registration Title");
    @Getter
    Button SuccessfulRegistrationModalCloseBtn = new Button(successfulRegistrationModalCloseBtn, "The Successful Registration Modal Close Button");
    @Getter
    InputField CodeInputCell0 = new InputField(codeInputCell0, "The Activation Code Input Cell 0");
    @Getter
    InputField CodeInputCell1 = new InputField(codeInputCell1, "The Activation Code Input Cell 1");
    @Getter
    InputField CodeInputCell2 = new InputField(codeInputCell2, "The Activation Code Input Cell 2");
    @Getter
    InputField CodeInputCell3 = new InputField(codeInputCell3, "The Activation Code Input Cell 3");
    @Getter
    Button ContinueActivationBtn = new Button(continueActivationBtn, "The Continue Code Activation Button");

    //Warnings
    @Getter
    TextField FirstNameRequired = new TextField(firstNameRequired, "The First Name Required Warning");
    @Getter
    TextField FirstNameNoNumbers = new TextField(firstNameNoNumbers, "The First Name No Numbers Warning");
    @Getter
    TextField LastNameRequired = new TextField(lastNameRequired, "The Last Name Required Warning");
    @Getter
    TextField LastNameNoNumbers = new TextField(lastNameNoNumbers, "The Last Name No Numbers Warning");
    @Getter
    TextField CnpRequired = new TextField(cnpRequired, "The Cnp Required Warning");
    @Getter
    TextField CnpInvalid = new TextField(cnpInvalid, "The Cnp Invalid Warning");
    @Getter
    TextField CityTownRequired = new TextField(cityTownRequired, "The City Town Required Warning");
    @Getter
    TextField CityTownInvalid = new TextField(cityTownInvalid, "The City Town Invalid Warning");
    @Getter
    TextField AddressRequired = new TextField(addressRequired, "The Address Required Warning");
    @Getter
    TextField TermsAndConditionRequired = new TextField(termsAndConditionRequired, "The Terms and Condition Required Warning");
    @Getter
    TextField EmailRequired = new TextField(emailRequired, "The Email Required Warning");
    @Getter
    TextField EmailInvalid = new TextField(emailInvalid, "The Email Invalid Warning");
    @Getter
    TextField PhoneNumberRequired = new TextField(phoneNumberRequired, "The Phone Number Required Warning");
    @Getter
    TextField PhoneNumberInvalid = new TextField(phoneNumberInvalid, "The Phone Number Invalid Warning");
    @Getter
    TextField UserNameRequired = new TextField(userNameRequired, "The User Name Required Warning");
    @Getter
    TextField PasswordRequired = new TextField(passwordRequired, "The Password Required Warning");

    @Step("Handle Cookie Popup if present")
    public LoginPage handleCookieConsent() {
        try {
            // Встановлюємо мінімальний implicit wait або використовуємо FluentWait
            acceptAllButton.clickButtonInShadowRootByJs("usercentrics-cmp-ui", "accept", 5);
        } catch (TimeoutException | NoSuchElementException e) {
            // Попап не з'явився — це нормально, ігноруємо
        }
        return this;
    }
    @Step("Click Accept All button with JS")
    public LoginPage clickAcceptButtonJs(){
        acceptAllButton.clickButtonInShadowRootByJs("usercentrics-cmp-ui", "accept", 15);
        return this;
    }
    @Step("Accept cookies if displayed")
    public LoginPage acceptCookiesIfPresent() {
        boolean accepted =
                acceptAllButton.clickButtonInShadowRootByJsIfPresent(
                        "usercentrics-cmp-ui",
                        "accept",
                        10);
        if (accepted) {
            log.info("Cookies accepted");
        }
        return this;
    }
    @Step("Check whether the user is already logged in")
    public boolean isUserLoggedIn() {
        // Short timeout on purpose: this is asked once per test on a page that has already
        // settled, and the answer is "no" on every fresh browser.
        return getUserInfo().isExists(3);
    }
    @Step("Click on the 'Login' button from the Lobby page")
    public LoginPage clickOnLoginButton() {
        getLoginButton().clickButton();
        return this;
    }
    @Step("Click on the 'Register' button from the 'Lobby' page")
    public LoginPage clickOnRegisterButton() {
        getRegisterButton().clickButton();
        return this;
    }
    @Step("Enter username")
    public LoginPage enterUsername(String username) {
        Assert.assertTrue(
                getUserNameField().isExists(10),
                "Username field is not visible"
        );
        getUserNameField().clear();
        getUserNameField().setText(username);
        return this;
    }
    @Step("Enter the password")
    public LoginPage enterPassword(String password) {
        getPasswordField().setText(password);
        return this;
    }
    @Step("Click on the 'Forgot password' link")
        public void clickOnForgotPasswordLink(){
        getForgotPasswordLink().clickButton();
    }
    @Step("Click on the 'Forgot Password' title")
    public void enterEmailIntoEmailInput(String email) {
        getEmailInputForRestorePassword().setText(email);
    }
    @Step("Click on the 'ReCaptcha' button")
    public LoginPage clickOnReCaptchaButton() {
        getReCaptchaBtn().clickButton();
        return this;
    }
    @Step("Click on the 'Request Password' button")
    public LoginPage clickOnRequestPasswordButton() {
        getRequestPasswordBtn().clickButton();
        return this;
    }
    @Step("Click on the 'Login' button from Login modal")
    public LoginPage clickOnLogin() {
        getConnectionButton().clickButton();
        return this;
    }
    @Step("Wait until page mask disappears")
    public void waitUntilMaskDisappears() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(mask));
    }
    @Step
    public LoginPage clickOnPage() {
        getNotificationDialog().clickAnywhereOnPage();
        return this;
    }
    @Step("Click on the 'Accept' button in the Notification Dialog")
    public void clickOnAcceptNotification() {
        waitUntilMaskDisappears();
        getNotificationAccept().clickButton();
        Assert.assertTrue(
                getNotificationAccept().invisibilityOfElementLocated(10),
                "Notification dialog is still visible after clicking Accept"
        );
    }
    @Step("Click on the 'Decline' button in the Notification Dialog")
    public LoginPage clickOnDeclineNotification() {
        getNotificationDecline().clickButton();
        return this;
    }
    //Actions for Registration Step 1
    @Step("Click on the 'Login' tab from the Registration modal")
    public LoginPage clickOnLoginTab() {
        getLoginTab().clickButton();
        return this;
    }
    @Step("Click on the 'Register' tab from the Login modal")
    public LoginPage clickOnRegisterTab() {
        getRegisterTab().clickButton();
        return this;
    }
    @Step("Enter First Name")
    public LoginPage enterFirstName(String firstName) {
        getFirstName().setText(firstName);
        return this;
    }
    @Step("Enter Last Name")
    public LoginPage enterLastName(String lastName) {
        getLastName().setText(lastName);
        return this;
    }
    @Step("Enter Cnp Code")
    public LoginPage enterCnpCode(String cnpCode) {
        getCnpCode().setText(cnpCode);
        return this;
    }
    @Step("Enter City Town")
    public LoginPage enterCityTown(String cityTown) {
        getCityTown().setText(cityTown);
        return this;
    }
    @Step("Click on the City Town options")
    public LoginPage clickOnCityTownOptions() {
        getCityTownOptions().clickButton();
        return this;
    }
    @Step("Enter Address")
    public LoginPage enterAddress(String address) {
        getAddress().setText(address);
        return this;
    }
    @Step("Click on the Terms and Condition Checkbox")
    public LoginPage clickOnTermsAndConditionCheckbox() {
        getTermsAndConditionCheckbox().clickButton();
        return this;
    }
    @Step("Click on the Marketing All Checkbox")
    public LoginPage clickOnMarketingAllCheckbox() {
        getMarketingAllCheckbox().clickButton();
        return this;
    }
    @Step("Click on the Continue button")
    public LoginPage clickOnContinueButton() {
        getContinueBtn().clickButton();
        return this;
    }
    //Actions for Registration Step 2
    @Step("Enter Email Address")
    public LoginPage enterEmailAddress(String emailAddress) {
        getEmailAddress().setText(emailAddress);
        return this;
    }
    @Step("Enter Phone Number")
    public LoginPage enterPhoneNumber(String phoneNumber) {
        getPhoneNumber().setText(phoneNumber);
        return this;
    }
    @Step("Enter User Name")
    public LoginPage enterUserName(String userName) {
        getUserNameInput().setText(userName);
        return this;
    }
    @Step("Enter the Password")
    public LoginPage setPassword(String password){
        getPasswordInput().setText(password);
        // The registration form validates the last field on blur.
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].blur();", getDriver().findElement(passwordInput));
        return this;
    }
    @Step("Click on the Hide Mode Password Button")
    public LoginPage clickOnHideModePasswordButton() {
        getHideModePasswordBtn().clickButton();
        return this;
    }
    @Step("Click on the Currency Ron Button")
    public LoginPage clickOnRonCurrencyButton() {
        getCurrencyRonBtn().clickButton();
        return this;
    }
    @Step("Click on the Currency Eur Button")
    public LoginPage clickOnEurCurrencyButton() {
        getCurrencyEurBtn().clickButton();
        return this;
    }
    @Step("Click on the Signup Button")
    public void clickOnSignupButton() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(mask));
        WebElement signupButton = wait.until(driver -> {
            WebElement element = driver.findElement(signupBtn);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Boolean disabled = (Boolean) js.executeScript(
                    "return arguments[0].disabled === true " +
                            "|| arguments[0].getAttribute('aria-disabled') === 'true' " +
                            "|| arguments[0].classList.contains('disabled');",
                    element);
            return element.isDisplayed() && element.isEnabled() && Boolean.FALSE.equals(disabled) ? element : null;
        });
        signupButton.click();
    }
    @Step("Wait until loader disappears")
    public void waitUntilLoaderDisappears() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loader));
        } catch (TimeoutException ignored) {
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }
    @Step("Wait until registration success modal is displayed")
    public void waitUntilSuccessfulRegistrationDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successfulRegistrationTitle));
    }
    @Step("Click on 'X' button for Activation Modal")
    public void clickCloseBtnSuccessfulRegistrationModal() {
        getSuccessfulRegistrationModalCloseBtn().clickButton();
    }
    @Step("Enter 1 digit for activation code")
    public LoginPage enterFirstDigitForActivationCode(String activationCode) {
        getCodeInputCell0().setText(activationCode);
        return this;
    }
    @Step("Enter 2 digit for activation code")
    public LoginPage enterSecondDigitForActivationCode(String activationCode) {
        getCodeInputCell1().setText(activationCode);
        return this;
    }
    @Step("Enter 3 digit for activation code")
    public LoginPage enterThirdDigitForActivationCode(String activationCode) {
        getCodeInputCell2().setText(activationCode);
        return this;
    }
    @Step("Enter 4 digit for activation code")
    public LoginPage enterForthDigitForActivationCode(String activationCode) {
        getCodeInputCell3().setText(activationCode);
        return this;
    }
    @Step("Click on 'Continue' button for Successful Registration Modal")
    public LoginPage clickContinueBtnActivationModal() {
        getContinueActivationBtn().clickButton();
        return this;
    }
}
