package io.maxbet.tests;

import io.maxbet.pageObjects.DepositPage;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.LoginPage;
import io.maxbet.pageObjects.ProfilePage;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class DepositPageTests extends TestBase{
    private DepositPage depositPage;


    @BeforeMethod
    public void openDepositPage() {
        ProfilePage profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
        profilePage.clickOnDepositBtnOnProfPage();
        depositPage = new DepositPage();
        depositPage.clickOnBackBtnDeposit();
    }

    @Test(description = "Open the 'Deposit' page from the 'Profile' page")
    public void openDepositPageFromProfile(){
        Assert.assertTrue(depositPage.isDepositPageOpened(), "Deposit page title is not displayed");
    }

    private static final List<PaymentMethod> PAYMENT_METHODS = Arrays.asList(
            new PaymentMethod("Visa & MasterCard & Maestro", DepositPage::clickOnBankCardPM, "/bank-card"),
            new PaymentMethod("Abon", DepositPage::clickOnAbonPM, "/abon"),
            new PaymentMethod("PaySafeCard", DepositPage::clickOnPaySafePM, "/paysafe"),
            new PaymentMethod("External Cashier", DepositPage::clickOnExternalCashierPM, "/external-cashier"),
            new PaymentMethod("Google Pay", DepositPage::clickOnGooglePayPM, "/google-pay"),
            new PaymentMethod("Okto Cash", DepositPage::clickOnOktoCashPM, "/oktocash"),
            new PaymentMethod("Air Cash", DepositPage::clickOnAirCashPM, "/aircash"),
            new PaymentMethod("Skrill", DepositPage::clickOnSkrillPM, "/skrill"),
            new PaymentMethod("Neteller", DepositPage::clickOnNetellerPM, "/neteller")
    );
    private static final class PaymentMethod {
        private final String name;
        private final Function<DepositPage, DepositPage> open;
        private final String urlPath;

        private PaymentMethod(String name, Function<DepositPage, DepositPage> open, String urlPath) {
            this.name = name;
            this.open = open;
            this.urlPath = urlPath;
        }
    }

    @Test(description = "Open every payment method from the 'Deposit' page")
    public void openEachPaymentMethod() {
        SoftAssert softly = new SoftAssert();
        Assert.assertTrue(depositPage.isOnPaymentMethodsList(),
                "Not on the payment methods list, so no payment method can be opened");

        for (PaymentMethod paymentMethod : PAYMENT_METHODS) {
            Allure.step("Payment method: " + paymentMethod.name, () -> {
                try {
                    paymentMethod.open.apply(depositPage);
                    softly.assertTrue(depositPage.isPmPageOpened(paymentMethod.urlPath),
                            "'" + paymentMethod.name + "' did not open. Expected the URL to end with '"
                                    + paymentMethod.urlPath + "' but it was '"
                                    + depositPage.getCurrentUrl() + "'");
                } catch (RuntimeException e) {
                    softly.fail("'" + paymentMethod.name + "' could not be opened: " + e);
                }
                softly.assertTrue(depositPage.returnToPaymentMethodsList(),
                        "Could not get back to the payment methods list after '"
                                + paymentMethod.name + "'");
            });
        }
        softly.assertAll();
    }

    @Test(description = "Deposit with visa")
    public void depositWithVisa(){
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card");
        depositPage
                .clickOnFirstAmountSelector();
        depositPage
                .clickOnFirstCardToggle()
                .enterCvvCode("111");
        depositPage
                .clickOnMakeDepositBtn();
        Assert.assertTrue(
                new DepositPage().getSuccessDepositPopUp().isExists(10),
                "The Success Deposit pop-up is not visible"
        );
    }
    @Test(description = "Deposit limit with visa")
    public void depositLimitWithVisa(){
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card");
        depositPage
                .enterTheSum("500001");
        Assert.assertTrue(
                new DepositPage().getAmountWarningMessage().isExists(10),
                "The 'Maximum deposit should be 500000 RON ' warning message is not visible"
        );
    }
}
