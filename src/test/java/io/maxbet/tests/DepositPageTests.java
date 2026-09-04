package io.maxbet.tests;
import io.maxbet.listeners.TestGroups;

import io.maxbet.Elements.Button;
import io.maxbet.pageObjects.DepositPage;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.ProfilePage;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class DepositPageTests extends TestBase {
    private static final String MAX_DEPOSIT = "500000";
    private static final String OVER_MAX_DEPOSIT = "500001";
    private static final String VALID_DEPOSIT = "50";
    private static final String VALID_CVV = "111";
    private static final long BALANCE_SETTLE_TIMEOUT = 30;
    /**
     * Romania taxes player deposits at 2%, so a deposit of 50 credits 49. Kept as the worst case
     * the test tolerates rather than an exact rate: a smaller cut - or none - is not a defect.
     */
    private static final BigDecimal MAX_DEPOSIT_TAX_RATE = new BigDecimal("0.02");

    private static final List<PaymentMethod> PAYMENT_METHODS = Arrays.asList(
            new PaymentMethod("Visa & MasterCard & Maestro", DepositPage::clickOnBankCardPM, DepositPage::getBankCardPM, "/bank-card"),
            new PaymentMethod("Abon", DepositPage::clickOnAbonPM, DepositPage::getAbonPM, "/abon"),
            new PaymentMethod("PaySafeCard", DepositPage::clickOnPaySafePM, DepositPage::getPaySafePM, "/paysafe"),
            new PaymentMethod("External Cashier", DepositPage::clickOnExternalCashierPM, DepositPage::getExternalCashierPM, "/external-cashier"),
            new PaymentMethod("Google Pay", DepositPage::clickOnGooglePayPM, DepositPage::getGooglePayPM, "/google-pay"),
            new PaymentMethod("Okto Cash", DepositPage::clickOnOktoCashPM, DepositPage::getOktoCashPM, "/oktocash"),
            new PaymentMethod("Air Cash", DepositPage::clickOnAirCashPM, DepositPage::getAirCashPM, "/aircash"),
            new PaymentMethod("Skrill", DepositPage::clickOnSkrillPM, DepositPage::getSkrillPM, "/skrill"),
            new PaymentMethod("Neteller", DepositPage::clickOnNetellerPM, DepositPage::getNetellerPM, "/neteller")
    );
    private static final class PaymentMethod {
        private final String name;
        private final Function<DepositPage, DepositPage> open;
        private final Function<DepositPage, Button> tile;
        private final String urlPath;

        private PaymentMethod(String name,
                              Function<DepositPage, DepositPage> open,
                              Function<DepositPage, Button> tile,
                              String urlPath) {
            this.name = name;
            this.open = open;
            this.tile = tile;
            this.urlPath = urlPath;
        }
    }

    private DepositPage depositPage;

    @BeforeMethod(alwaysRun = true)
    public void openDepositPage() {
        ProfilePage profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
        depositPage = profilePage
                .clickOnDepositBtnOnProfPage()
                .openPaymentMethodsList();
    }

    @Test(description = "Open the 'Deposit' page from the 'Profile' page")
    public void openDepositPageFromProfile() {
        Assert.assertTrue(depositPage.isDepositPageOpened(), "Deposit page title is not displayed");
        Assert.assertTrue(depositPage.isOnPaymentMethodsList(),
                "The 'Deposit' page does not show the payment methods list. Current URL: "
                        + depositPage.getCurrentUrl());
    }

    @Test(groups = {TestGroups.SMOKE}, description = "Every payment method is listed on the 'Deposit' page")
    public void allPaymentMethodsAreListed() {
        SoftAssert softly = new SoftAssert();
        for (PaymentMethod paymentMethod : PAYMENT_METHODS) {
            softly.assertTrue(paymentMethod.tile.apply(depositPage).isExists(5),
                    "The '" + paymentMethod.name + "' payment method is missing from the list");
        }
        softly.assertAll();
    }

    @Test(description = "Open every payment method from the 'Deposit' page")
    public void openEachPaymentMethod() {
        SoftAssert softly = new SoftAssert();
        Assert.assertTrue(depositPage.isOnPaymentMethodsList(),
                "Not on the payment methods list, so no payment method can be opened");

        for (PaymentMethod paymentMethod : PAYMENT_METHODS) {
            boolean backOnTheList = Allure.step("Payment method: " + paymentMethod.name, () -> {
                try {
                    paymentMethod.open.apply(depositPage);
                    softly.assertTrue(depositPage.isPmPageOpened(paymentMethod.urlPath),
                            "'" + paymentMethod.name + "' did not open. Expected the URL to end with '"
                                    + paymentMethod.urlPath + "' but it was '"
                                    + depositPage.getCurrentUrl() + "'");
                } catch (RuntimeException e) {
                    softly.fail("'" + paymentMethod.name + "' could not be opened: " + e);
                }
                boolean returned = depositPage.returnToPaymentMethodsList();
                softly.assertTrue(returned,
                        "Could not get back to the payment methods list after '"
                                + paymentMethod.name + "'");
                return returned;
            });
        
            if (!backOnTheList) {
                break;
            }
        }
        softly.assertAll();
    }

    @Test(description = "The 'Back' button returns from a payment method to the payment methods list")
    public void backButtonReturnsToPaymentMethodsList() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .clickOnBackBtnDeposit();
        Assert.assertTrue(depositPage.isOnPaymentMethodsList(),
                "The 'Back' button did not return to the payment methods list. Current URL: "
                        + depositPage.getCurrentUrl());
    }

    @Test(description = "Each quick-amount selector fills the amount input with its own value")
    public void amountSelectorsFillTheAmountInput() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card");

        SoftAssert softly = new SoftAssert();
        for (int index = 1; index <= DepositPage.AMOUNT_SELECTOR_COUNT; index++) {
            int selectorIndex = index;
            Allure.step("Amount selector #" + selectorIndex, () -> {
                String label = depositPage.getAmountSelectorLabel(selectorIndex);
                String expectedAmount = digitsOf(label);
                softly.assertFalse(expectedAmount.isEmpty(),
                        "Amount selector #" + selectorIndex + " carries no amount, its label is '" + label + "'");
                if (expectedAmount.isEmpty()) {
                    return;
                }
                depositPage.clickOnAmountSelector(selectorIndex);
                String enteredAmount = digitsOf(depositPage.getEnteredAmount());
                if (!enteredAmount.equals(expectedAmount)) {
                    // The offered amounts are loaded asynchronously and can change under the
                    // cursor, so one mismatch is retried against the label as it reads now.
                    expectedAmount = digitsOf(depositPage.getAmountSelectorLabel(selectorIndex));
                    depositPage.clickOnAmountSelector(selectorIndex);
                    enteredAmount = digitsOf(depositPage.getEnteredAmount());
                }
                softly.assertEquals(enteredAmount, expectedAmount,
                        "Amount selector #" + selectorIndex + " did not fill the amount input with its own value");
            });
        }
        softly.assertAll();
    }

    @Test(description = "The deposit cannot be submitted with an empty amount")
    public void depositIsBlockedWithoutAmount() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card");
        Assert.assertFalse(depositPage.isMakeDepositBtnEnabled(),
                "The 'Deposit' button is enabled although no amount was entered");
    }

    @DataProvider(name = "invalidAmounts")
    public Object[][] invalidAmounts() {
        return new Object[][]{
                {"zero", "0"},
                {"above the maximum", OVER_MAX_DEPOSIT},
                {"not a number", "abc"}
        };
    }

    @Test(dataProvider = "invalidAmounts",
            description = "The deposit cannot be submitted with an invalid amount")
    public void depositIsBlockedWithInvalidAmount(String caseName, String amount) {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .enterTheSum(amount);
        Assert.assertTrue(depositPage.isDepositBlocked(),
                "The deposit is not blocked for an amount that is " + caseName + " ('" + amount
                        + "'): no warning is shown and the 'Deposit' button is enabled");
    }

    @Test(description = "Warning message for an amount above the deposit limit")
    public void depositAboveMaximumShowsWarning() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .enterTheSum(OVER_MAX_DEPOSIT)
                .verifyAmountWarningContains(MAX_DEPOSIT);
    }

    @Test(description = "The maximum allowed amount is accepted")
    public void depositAtMaximumIsAccepted() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .enterTheSum(MAX_DEPOSIT);
        if (depositPage.isAmountWarningShown(3)) {
            String warning = depositPage.getAmountWarningText();
            Assert.assertFalse(warning.toLowerCase().contains("maximum"),
                    "The maximum allowed amount " + MAX_DEPOSIT
                            + " is rejected as too big: '" + warning + "'");
        }
    }

    @Test(description = "The deposit cannot be submitted without a selected card")
    public void depositIsBlockedWithoutSelectedCard() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .enterTheSum(VALID_DEPOSIT);
        Assert.assertFalse(depositPage.isMakeDepositBtnEnabled(),
                "The 'Deposit' button is enabled although no card was selected");
    }

    @Test(description = "The deposit cannot be submitted without a CVV code")
    public void depositIsBlockedWithoutCvv() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .enterTheSum(VALID_DEPOSIT)
                .clickOnFirstCardToggle();
        Assert.assertTrue(depositPage.isFirstCardSelected(),
                "The first saved card is not selected, so the empty CVV is not what blocks the deposit");
        Assert.assertFalse(depositPage.isMakeDepositBtnEnabled(),
                "The 'Deposit' button is enabled although the CVV code is empty");
    }

    @Test(description = "The deposit cannot be submitted with an incomplete CVV code")
    public void depositIsBlockedWithIncompleteCvv() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .enterTheSum(VALID_DEPOSIT)
                .clickOnFirstCardToggle()
                .enterCvvCode("1");
        Assert.assertTrue(depositPage.isFirstCardSelected(),
                "The first saved card is not selected, so the incomplete CVV is not what blocks the deposit");
        Assert.assertFalse(depositPage.isMakeDepositBtnEnabled(),
                "The 'Deposit' button is enabled although the CVV code has a single digit");
    }

    @Test(description = "Deposit with visa")
    public void depositWithVisa() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .clickOnFirstAmountSelector()
                .clickOnFirstCardToggle()
                .enterCvvCode(VALID_CVV);
        depositPage
                .clickOnMakeDepositBtn()
                .verifySuccessDepositPopUp();
    }
    /**
     * The balance does not grow by the full deposit: a deposit of 50 was observed to credit 49,
     * which matches the 2% tax Romania levies on player deposits. Asserting the credited band
     * rather than an exact figure is what keeps this stable - it still catches a deposit that
     * credits nothing, credits more than was paid in, or is cut by more than the expected tax,
     * without pinning the test to a rate that is the product's to decide.
     */
    @Test(description = "The balance grows by the credited part of the deposit")
    public void balanceGrowsByTheDepositedAmount() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .enterTheSum(VALID_DEPOSIT)
                .clickOnFirstCardToggle()
                .enterCvvCode(VALID_CVV);

        BigDecimal submittedAmount = depositPage.getEnteredAmountAsNumber();
        BigDecimal balanceBeforeDeposit = depositPage.waitForBalanceToSettle(BALANCE_SETTLE_TIMEOUT);

        depositPage
                .clickOnMakeDepositBtn()
                .verifySuccessDepositPopUp()
                .clickToTheLobbyBtn()
                .getUserInfo().verify();

        BigDecimal balanceAfterDeposit =
                depositPage.waitForBalanceToSettle(balanceBeforeDeposit, BALANCE_SETTLE_TIMEOUT);
        BigDecimal credited = balanceAfterDeposit.subtract(balanceBeforeDeposit);
        BigDecimal minimumCredited = submittedAmount
                .multiply(BigDecimal.ONE.subtract(MAX_DEPOSIT_TAX_RATE));
        String context = "Deposited " + submittedAmount + ", the balance went from "
                + balanceBeforeDeposit + " to " + balanceAfterDeposit + " (credited " + credited
                + "). Header balance nodes: '" + depositPage.describeBalanceNodes() + "'";
        // compareTo throughout, never equals: the header switches between '60906.7' and
        // '60906,70' scales, and BigDecimal.equals() calls those two different numbers.
        Assert.assertTrue(credited.compareTo(BigDecimal.ZERO) > 0,
                "The deposit credited nothing to the balance. " + context);
        Assert.assertTrue(credited.compareTo(submittedAmount) <= 0,
                "The balance grew by more than was deposited. " + context);
        Assert.assertTrue(credited.compareTo(minimumCredited) >= 0,
                "The deposit was taxed harder than the expected "
                        + MAX_DEPOSIT_TAX_RATE.movePointRight(2) + "%, so at least "
                        + minimumCredited + " was expected. " + context);
    }

    @Test(description = "The 'To the Lobby' button of the success pop-up leads back to the lobby")
    public void successPopUpLeadsToTheLobby() {
        depositPage
                .clickOnBankCardPM()
                .verifyPmPageOpened("/bank-card")
                .enterTheSum(VALID_DEPOSIT)
                .clickOnFirstCardToggle()
                .enterCvvCode(VALID_CVV);
        LobbyPage lobbyPage = depositPage
                .clickOnMakeDepositBtn()
                .verifySuccessDepositPopUp()
                .clickToTheLobbyBtn();
        lobbyPage.getUserInfo().verify();
    }

    private static String digitsOf(String text) {
        return text == null ? "" : text.replaceAll("\\D", "");
    }
}
