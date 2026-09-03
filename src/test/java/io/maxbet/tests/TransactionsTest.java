package io.maxbet.tests;

import io.maxbet.Elements.Button;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.ProfilePage;
import io.maxbet.pageObjects.TransactionsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TransactionsTest extends TestBase {
    /** The date fields carry the format of their own placeholder, 'MMMM DD, YYYY'. */
    private static final DateTimeFormatter FIELD_FORMAT =
            DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter FIELD_FORMAT_PADDED_DAY =
            DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH);

    private TransactionsPage transactionsPage;

    @BeforeMethod(alwaysRun = true)
    public void openTransactionsPage() {
        ProfilePage profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
        profilePage.clickOnTransactions();
        transactionsPage = new TransactionsPage();
        transactionsPage.waitUntilTransactionsPageOpened();
    }

    @Test(description = "Open the 'Transactions' page from the 'Profile' page")
    public void transactionsPageIsOpened() {
        transactionsPage
                .getTransactionsPageTitle().verify();
        transactionsPage
                .getDateFrom().verify();
        transactionsPage
                .getDateTo().verify();
        transactionsPage
                .getTransactionsTypeDropdown().verify();
        transactionsPage
                .getFilterButton().verify();
    }

    @Test(description = "The 'Transactions' page shows the list of transactions")
    public void transactionsListIsShown() {
        Assert.assertTrue(transactionsPage.waitUntilTransactionsListIsShown(),
                "The transactions list is not shown on the opened 'Transactions' page");
    }

    @Test(description = "The 'Transactions' page opens on a date range that ends today")
    public void theDefaultDateRangeEndsToday() {
        Assert.assertEquals(transactionsPage.getSelectedTypeName().trim(), "Deposit",
                "The type filter does not open on 'Deposit'");
        assertFieldHoldsToday(transactionsPage.getDateToValue(), "Date To");
        Assert.assertNotEquals(transactionsPage.getDateFromValue().trim(), "",
                "The 'Date From' field is empty, the page does not open on a default range");
    }

    @Test(description = "The 'Date From' field opens the calendar")
    public void dateFromOpensTheCalendar() {
        transactionsPage.openDateFromCalendar();
        Assert.assertTrue(transactionsPage.isCalendarOpened(),
                "Clicking the 'Date From' field did not open the calendar");
    }

    @Test(description = "The 'Date To' field opens the calendar")
    public void dateToOpensTheCalendar() {
        transactionsPage.openDateToCalendar();
        Assert.assertTrue(transactionsPage.isCalendarOpened(),
                "Clicking the 'Date To' field did not open the calendar");
    }

    @Test(description = "Escape closes the open calendar")
    public void escapeClosesTheCalendar() {
        transactionsPage.openDateFromCalendar();
        Assert.assertTrue(transactionsPage.isCalendarOpened(),
                "Clicking the 'Date From' field did not open the calendar");

        transactionsPage.closeCalendarWithEscape();

        Assert.assertTrue(transactionsPage.isCalendarClosed(),
                "The calendar is still open after Escape");
    }

    @Test(description = "The arrows of the calendar walk through the months")
    public void calendarArrowsWalkThroughTheMonths() {
        transactionsPage.openDateFromCalendar();
        Assert.assertTrue(transactionsPage.isCalendarOpened(),
                "Clicking the 'Date From' field did not open the calendar");
        String openedOn = transactionsPage.getCalendarMonth();

        transactionsPage.clickCalendarNextMonth();
        String nextMonth = transactionsPage.getCalendarMonth();
        Assert.assertNotEquals(nextMonth, openedOn,
                "The 'Next month' arrow left the calendar on '" + openedOn + "'");
        Assert.assertTrue(transactionsPage.getCalendarDayCount() > 0,
                "The calendar shows no days after the month was changed to '" + nextMonth + "'");

        transactionsPage.clickCalendarPreviousMonth();
        Assert.assertEquals(transactionsPage.getCalendarMonth(), openedOn,
                "The 'Previous month' arrow did not bring the calendar back to '" + openedOn + "'");
    }

    @Test(description = "Picking a day in the calendar fills the 'Date From' field and closes the calendar")
    public void pickingADayFillsTheDateFromField() {
        transactionsPage
                .openDateFromCalendar()
                .pickToday();

        Assert.assertTrue(transactionsPage.isCalendarClosed(),
                "The calendar is still open after a day was picked");
        assertFieldHoldsToday(transactionsPage.getDateFromValue(), "Date From");
    }

    @Test(description = "Picking a day in the calendar fills the 'Date To' field and closes the calendar")
    public void pickingADayFillsTheDateToField() {
        transactionsPage
                .openDateToCalendar()
                .pickToday();

        Assert.assertTrue(transactionsPage.isCalendarClosed(),
                "The calendar is still open after a day was picked");
        assertFieldHoldsToday(transactionsPage.getDateToValue(), "Date To");
    }

    @Test(description = "Filtering by a date range keeps the user on the 'Transactions' page with a list")
    public void filteringByADateRangeReloadsTheList() {
        transactionsPage
                .setDateRangeToToday()
                .clickOnFilter();

        Assert.assertTrue(transactionsPage.isTransactionsPageOpened(),
                "Filtering navigated away from the 'Transactions' page: " + transactionsPage.getCurrentUrl());
        Assert.assertTrue(transactionsPage.waitUntilFilterIsAnswered(),
                "Filtering by a date range came back with neither a list nor the 'no transactions' message");
        assertFieldHoldsToday(transactionsPage.getDateFromValue(), "Date From");
        assertFieldHoldsToday(transactionsPage.getDateToValue(), "Date To");
    }

    @Test(description = "The 'Transactions Type' dropdown offers all four transaction types")
    public void typeDropdownOffersAllTypes() {
        transactionsPage.openTransactionsTypeDropdown();

        transactionsPage.getDepositOption().verify();
        transactionsPage.getWithdrawalOption().verify();
        transactionsPage.getMaxWalletOption().verify();
        transactionsPage.getGamblingOption().verify();
    }

    @DataProvider(name = "transactionTypes")
    public Object[][] transactionTypes() {
        return new Object[][]{
                {"Deposit", (OptionOf) TransactionsPage::getDepositOption},
                {"Withdraw", (OptionOf) TransactionsPage::getWithdrawalOption},
                {"MaxWallet", (OptionOf) TransactionsPage::getMaxWalletOption},
                {"Gambling", (OptionOf) TransactionsPage::getGamblingOption},
        };
    }

    @Test(dataProvider = "transactionTypes",
            description = "Filtering by a transaction type reloads the list of transactions")
    public void filteringByTypeReloadsTheList(String typeName, OptionOf option) {
        transactionsPage.openTransactionsTypeDropdown();
        transactionsPage.selectTransactionsType(option.of(transactionsPage));

        Assert.assertTrue(transactionsPage.areTransactionsTypeOptionsClosed(),
                "The options are still shown after '" + typeName + "' was selected");
        Assert.assertEquals(transactionsPage.getSelectedTypeName().trim(), typeName,
                "The dropdown does not show the type that was selected");

        transactionsPage.clickOnFilter();

        Assert.assertTrue(transactionsPage.waitUntilFilterIsAnswered(),
                "Filtering by '" + typeName + "' came back with neither a list nor the "
                        + "'no transactions' message");
        if (!transactionsPage.hasTransactions()) {
            Assert.assertEquals(transactionsPage.getNoTransactionsMessage().getText().trim(),
                    "There are no transactions at the moment.",
                    "The account has no '" + typeName + "' transactions, but the empty result is "
                            + "not explained by the expected message");
        }
    }

    private void assertFieldHoldsToday(String value, String fieldName) {
        LocalDate today = LocalDate.now();
        String expected = FIELD_FORMAT.format(today);
        String expectedPaddedDay = FIELD_FORMAT_PADDED_DAY.format(today);

        Assert.assertTrue(value.trim().equals(expected) || value.trim().equals(expectedPaddedDay),
                "The '" + fieldName + "' field does not hold today: expected '" + expected
                        + "' but it holds '" + value + "'");
    }
    private interface OptionOf {
        Button of(TransactionsPage page);
    }
}
