package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.TextField;
import io.maxbet.tests.DepositPageTests;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

public class ProfilePage extends BaseElement {
    private final By mask = By.cssSelector(".mask");
    private final By accountInfoContainer = By.cssSelector(".account-info-container");
    private final By profileMenuContainer = By.cssSelector("div[class='menu'] mb-profile-menu div[class='menu-container']");
    private final By depositBtnOnProfPage = By.cssSelector(".btn-secondary.link-item[iconposition='prefix'][iconpath='/assets/images/profile/user-account/deposit-icon-sm.svg']");
    private final By withdrawBtnOnProfPage = By.cssSelector(".btn-secondary.link-item.withdrawals");
    private final By myAccountBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/profile']");
    private final By bonusesBtn = By.cssSelector("div[class='menu'] mb-menu-item:nth-child(2) a:nth-child(1)");
    private final By pendingWdBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/profile/pending-withdrawals']");
    private final By transactionsBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/profile/transactions']");
    private final By accountSecurityBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/profile/account-security']");
    private final By authenticationBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/profile/socials']");
    private final By myDetailsBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/profile/my-details']");
    private final By responsibleGamblingBtn = By.cssSelector(".mb-menu-item.variant--compact[link='/profile/responsible-gambling']");
    private final By legalBtn = By.cssSelector(".mb-menu-item.legal.variant--compact");
    private final By logoutBtn = By.cssSelector("mb-menu-item[label='profile.menu.logout']");


    @Getter
    TextField AccountInfo = new TextField(accountInfoContainer, "The Account Info");
    @Getter
    TextField ProfileMenu = new TextField(profileMenuContainer, "The Profile Menu");
    @Getter
    Button DepositBtnOnProfPage = new Button(depositBtnOnProfPage, "The 'Deposit' button on the Profile page");
    @Getter
    Button WithdrawBtnOnProfPage = new Button(withdrawBtnOnProfPage, "The 'Withdraw' button on the Profile page");
    @Getter
    Button MyAccountBtn = new Button(myAccountBtn, "The 'My Account' button");
    @Getter
    Button BonusesBtn = new Button(bonusesBtn, "The 'Bonuses' button");
    @Getter
    Button PendingWdBtn = new Button(pendingWdBtn, "The 'Pending Withdrawals' button");
    @Getter
    Button TransactionsBtn = new Button(transactionsBtn, "The 'Transactions' button");
    @Getter
    Button AccountSecurityBtn = new Button(accountSecurityBtn, "The 'Account Security' button");
    @Getter
    Button AuthenticationBtn = new Button(authenticationBtn, "The 'Authentication' button");
    @Getter
    Button MyDetailsBtn = new Button(myDetailsBtn, "The 'My Details' button");
    @Getter
    Button ResponsibleGamblingBtn = new Button(responsibleGamblingBtn, "The 'Responsible Gambling' button");
    @Getter
    Button LegalBtn = new Button(legalBtn, "The 'Legal' button");
    @Getter
    Button Logout = new Button(logoutBtn, "The 'Logout' button");

    @Step("Click on the Deposit button from the Profile page")
    public DepositPageTests clickOnDepositBtnOnProfPage() {
        AccountInfo.waitPageStability();
        getDepositBtnOnProfPage().clickButton();
        return new DepositPageTests();
    }
    @Step("Click on the 'My Account' button")
    public ProfilePage clickOnMyAccount() {
        getMyAccountBtn().clickButton();
        return this;
    }
    @Step("Click on the 'Bonuses' button")
    public BonusesPage clickOnBonuses() {
        AccountInfo.waitPageStability();
        getBonusesBtn().clickButton();
        return new BonusesPage();
    }
    @Step("Click on the 'Pending Withdrawals' button")
    public PendingWdPage clickOnPendingWd() {
        AccountInfo.waitPageStability();
        getPendingWdBtn().clickButton();
        return new PendingWdPage();
    }
    @Step("Click on the 'Transactions' button")
    public TransactionsPage clickOnTransactions() {
        AccountInfo.waitPageStability();
        getTransactionsBtn().clickButton();
        return new TransactionsPage();
    }
    @Step("Click on the 'Account Security' button")
    public AccountSecurityPage clickOnAccountSecurity() {
        AccountInfo.waitPageStability();
        getAccountSecurityBtn().clickButton();
        return new AccountSecurityPage();
    }
    @Step("Click on the 'Authentication' button")
    public AuthenticationPage clickOnAuthentication() {
        AccountInfo.waitPageStability();
        getAuthenticationBtn().clickButton();
        return new AuthenticationPage();
    }
    @Step("Click on the 'My Details' button")
    public MyDetailsPage clickOnMyDetails() {
        AccountInfo.waitPageStability();
        getMyDetailsBtn().clickButton();
        return new MyDetailsPage();
    }
    @Step("Click on the 'Responsible Gambling' button")
    public ResponsibleGamblingPage clickOnResponsibleGambling() {
        AccountInfo.waitPageStability();
        getResponsibleGamblingBtn().clickButton();
        return new ResponsibleGamblingPage();
    }
    @Step("Click on the 'Legal' button")
    public LegalPage clickOnLegal() {
        AccountInfo.waitPageStability();
        getLegalBtn().clickButton();
        return new LegalPage();
    }
    @Step("Click on the 'Logout' button")
    public LoginPage clickOnLogout() {
        clickElement(logoutBtn);
        return new LoginPage();
    }
}
