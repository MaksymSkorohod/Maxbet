package io.maxbet.tests;

import io.maxbet.pageObjects.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProfileTests extends TestBase{
    private ProfilePage profilePage;

    @BeforeMethod
    public void openProfileMenu() {
        profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
    }
    @Test(description = "Open the 'Bonuses' page from the 'Profile' page")
    public void openBonusesPageFromProfile(){
        profilePage
                .getProfileMenu().verify();
        profilePage
                .clickOnBonuses()
                .getBonusesPageTitle().verify();
    }
    @Test(description = "Open the 'Pending Withdrawals' page from the 'Profile' page")
    public void openPendingWdPageFromProfile(){
        profilePage
                .getProfileMenu().verify();
        profilePage
                .clickOnPendingWd();
        new PendingWdPage()
                .getRequestWdBtn().verify();
    }
    @Test(description = "Open the 'Transactions' page from the 'Profile' page")
    public void openTransactionsPageFromProfile(){
        profilePage
                .getProfileMenu().verify();
        profilePage
                .clickOnTransactions();
        new TransactionsPage()
                .getTransactionsPageTitle().verify();
    }
    @Test(description = "Open the 'Account Security' page from the 'Profile' page")
    public void openAccountSecurityPageFromProfile(){
        profilePage
                .getProfileMenu().verify();
        profilePage
                .clickOnAccountSecurity();
        new AccountSecurityPage()
                .getAccountSecurityPageTitle().verify();
    }
    @Test(description = "Open the 'Authentication' page from the 'Profile' page")
    public void openAuthenticationPageFromProfile(){
        profilePage
                .getProfileMenu().verify();
        profilePage
                .clickOnAuthentication();
        new AuthenticationPage()
                .getAuthenticationPageTitle().verify();
    }
    @Test(description = "Open the 'My Details' page from the 'Profile' page")
    public void openMyDetailsPageFromProfile(){
        profilePage
                .getProfileMenu().verify();
        profilePage
                .clickOnMyDetails();
        new MyDetailsPage()
                .getMyDetailsSection().verify();
    }
    @Test(description = "Open the 'Responsible Gambling' page from the 'Profile' page")
    public void openResponsibleGamblingPageFromProfile(){
        profilePage
                .getProfileMenu().verify();
        profilePage
                .clickOnResponsibleGambling();
        new ResponsibleGamblingPage()
                .getResponsibleGamblingPageTitle().verify();
    }
    @Test(description = "Open the 'Legal' page from the 'Profile' page")
    public void openLegalPageFromProfile(){
        profilePage
                .getProfileMenu().verify();
        profilePage
                .clickOnLegal();
        new LegalPage()
                .getLegalPage().verify();
    }
    @Test(description = "Logout from the portal")
    public void logout(){
        profilePage
                .getProfileMenu().verify();
        profilePage
                .clickOnLogout();
    }
}
