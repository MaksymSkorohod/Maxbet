package io.maxbet.pageObjects;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import static io.maxbet.DriverManager.getDriver;

public class LoginPage {
    private By cookieBannerShadowHost = By.cssSelector("#usercentrics-root");
    private final Button acceptAllButton = new Button(By.id("accept"), "Accept All button");
    private By loginBtn = By.cssSelector(".btn-secondary.auth.xs-sm");
    private By registerButton = By.xpath("//button[@class='btn-primary auth xs-sm']");
    private By authContainer = By.cssSelector(".auth-container");
    private By userNameField = By.id("usernameOrEmail");
    private By passwordField = By.id("password");
    private By conectionBtn = By.cssSelector("#auth-modal .btn-primary.lg");
    private By mask = By.cssSelector(".mask");
    private By notificationDialog = By.cssSelector(".mat-mdc-dialog-surface.mdc-dialog__surface");
    private By notificationAccept = By.cssSelector(".mat-mdc-dialog-surface button.btn-primary.lg");
    private By notificationDecline = By.cssSelector("button[class='btn btn-transparent lg ng-star-inserted']");
    private final By userInfoLocator = By.cssSelector(
            "div[class='right-block notific-padding'] mb-header-user-info[class='user-info']"
    );


    @Getter
    TextField PrivacySettings = new TextField(cookieBannerShadowHost,"Title of Privacy Settings popup");
    @Getter
    Button LoginButton = new Button(loginBtn,"The 'Login' button on the Lobby page");
    @Getter
    Button RegisterButton = new Button(registerButton,"The 'Register button'");
    @Getter
    TextField LoginRegisterModal = new TextField(authContainer, "The Login/Register modal");
    @Getter
    InputField UserNameField = new InputField(userNameField,"The Username/Email input field");
    @Getter
    InputField PasswordField = new InputField(passwordField,"The Password input field");
    @Getter
    Button ConnectionButton =  new Button(conectionBtn,"The 'Login button'");
    @Getter
    Button NotificationDialog = new Button(notificationDialog,"The Notification Dialog");
    @Getter
    Button NotificationAccept = new Button(notificationAccept,"The 'Accept' button in the Notification Dialog");
    @Getter
    Button NotificationDecline = new Button(notificationDecline,"The 'Decline' button in the Notification Dialog");
    @Getter
    private final Button userInfo = new Button(userInfoLocator, "The User Info button");

    @Step("Click Accept All button")
    public void clickAcceptCookies() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(driver -> {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;

                WebElement button = (WebElement) js.executeScript(
                        "const host = document.querySelector('usercentrics-cmp-ui');" +
                                "if (!host) return null;" +

                                "const shadow1 = host.shadowRoot;" +
                                "if (!shadow1) return null;" +

                                "const innerHost = shadow1.querySelector('#usercentrics-root');" +
                                "if (!innerHost) return null;" +

                                "const shadow2 = innerHost.shadowRoot;" +
                                "if (!shadow2) return null;" +

                                "return shadow2.querySelector('button[data-testid=\"uc-accept-all-button\"]');"
                );
                if (button != null) {
                    button.click();
                    return true;
                }
                return false;
            } catch (Exception e) {
                return false;
            }
        });
    }

    @Step("Click Accept All button with JS")
    public LoginPage clickAcceptButtonJs(){
        acceptAllButton.clickButtonInShadowRootByJs("usercentrics-cmp-ui", "accept", 15);
        return this;
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
        getPasswordField().clear();
        getPasswordField().setText(password);
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
    public LobbyPage clickOnAcceptNotification() {
        waitUntilMaskDisappears();
        getNotificationAccept().clickButton();
        Assert.assertTrue(
                getNotificationDialog().invisibilityOfElementLocated(20),
                "Notification dialog is still visible after clicking Accept"
        );
        return new LobbyPage();
    }
    @Step("Click on the 'Decline' button in the Notification Dialog")
    public LoginPage clickOnDeclineNotification() {
        getNotificationDecline().clickButton();
        return this;
    }

}
