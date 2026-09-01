package io.maxbet.pageObjects;

import io.maxbet.Elements.Button;
import io.maxbet.Elements.InputField;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.maxbet.DriverManager.getDriver;

public class TransactionsPage extends AbstractPage{
    /** The page opens on a one month range, so a single step is enough, with room to spare. */
    private static final int MAX_MONTHS_TO_ADVANCE = 24;
    private static final int ESCAPE_ATTEMPTS = 3;
    private static final int PICK_ATTEMPTS = 3;

    private final By transactionsPageTitle = By.xpath("//h2[normalize-space()='Transactions']");
    private final By dateFrom = By.cssSelector("[mbfullstorytrackedelement='Profile.Transactions.INP_DateFrom']");
    private final By dateTo = By.cssSelector("[mbfullstorytrackedelement='Profile.Transactions.INP_DateTo']");
    /**
     * The calendar is projected into a CDK overlay on the body instead of into the
     * {@code <mat-datepicker>} host, and only one calendar is open at a time, so the 'Date From'
     * and the 'Date To' popup share this locator. Which field is being edited follows from the
     * input that was clicked to open it.
     */
    private final By calendarPanel = By.cssSelector(".mat-datepicker-content");
    /** Today carries its own class, but on the cell content span rather than on the cell button. */
    private final By calendarTodayCell = By.cssSelector(".mat-datepicker-content .mat-calendar-body-today");
    private final By calendarDayCells = By.cssSelector(".mat-datepicker-content .mat-calendar-body-cell");
    private final By calendarDisabledDayCells = By.cssSelector(".mat-datepicker-content .mat-calendar-body-disabled");
    private final By calendarMonthLabel = By.cssSelector(".datepicker-header-label");
    /**
     * The datepicker runs a custom {@code <mb-datepicker-header>}, so Material's own
     * '.mat-calendar-previous-button' and '.mat-calendar-next-button' are not in the markup. The
     * two arrows are the only buttons of the header controls, previous first, in template order.
     * The month label next to them holds a third arrow, which is why the scope matters.
     */
    private final By calendarPreviousMonthBtn = By.cssSelector(".datepicker-header-controls button:nth-of-type(1)");
    private final By calendarNextMonthBtn = By.cssSelector(".datepicker-header-controls button:nth-of-type(2)");
    private final By transactionsTypeDropdown = By.cssSelector("[mbfullstorytrackedelement='Profile.Transactions.DDL_Type']");
    /** The trigger shows the type the filter currently runs with. */
    private final By selectedTransactionsType = By.cssSelector("mb-trigger");
    private final By depositOption = By.xpath("//mb-option[normalize-space()='Deposit']");
    private final By withdrawalOption = By.xpath("//mb-option[normalize-space()='Withdraw']");
    private final By maxWalletOption = By.xpath("//mb-option[normalize-space()='MaxWallet']");
    private final By gamblingOption = By.xpath("//mb-option[normalize-space()='Gambling']");
    private final By transactionsOptions = By.cssSelector("mb-option");
    private final By transactionsPageNavList = By.cssSelector(".transactions.ng-star-inserted");
    /** A filter that matches nothing takes the list off the page and puts this message in its place. */
    private final By noTransactionsMessage = By.cssSelector(".no-data-title");
    private final By filterButton = By.cssSelector(".mb-button.btn-primary.md");

    @Getter
    TextField TransactionsPageTitle = new TextField(transactionsPageTitle, "The 'Transactions' page title");
    @Getter
    InputField DateFrom = new InputField(dateFrom, "The 'Date From' input field");
    @Getter
    InputField DateTo = new InputField(dateTo, "The 'Date To' input field");
    @Getter
    TextField CalendarPanel = new TextField(calendarPanel, "The datepicker calendar");
    @Getter
    Button CalendarTodayCell = new Button(calendarTodayCell, "Today's cell in the calendar");
    @Getter
    TextField CalendarMonthLabel = new TextField(calendarMonthLabel, "The month shown by the calendar");
    @Getter
    Button CalendarNextMonthBtn = new Button(calendarNextMonthBtn, "The 'Next month' arrow of the calendar");
    @Getter
    Button CalendarPreviousMonthBtn = new Button(calendarPreviousMonthBtn, "The 'Previous month' arrow of the calendar");
    @Getter
    Button TransactionsTypeDropdown = new Button(transactionsTypeDropdown, "The 'Transactions Type' dropdown");
    @Getter
    TextField SelectedTransactionsType = new TextField(selectedTransactionsType, "The selected transaction type");
    @Getter
    Button DepositOption = new Button(depositOption, "The 'Deposit' option in the 'Transactions Type' dropdown");
    @Getter
    Button WithdrawalOption = new Button(withdrawalOption, "The 'Withdraw' option in the 'Transactions Type' dropdown");
    @Getter
    Button MaxWalletOption = new Button(maxWalletOption, "The 'MaxWallet' option in the 'Transactions Type' dropdown");
    @Getter
    Button GamblingOption = new Button(gamblingOption, "The 'Gambling' option in the 'Transactions Type' dropdown");
    @Getter
    Button FilterButton = new Button(filterButton, "The 'Filter' button");
    @Getter
    TextField TransactionsPageNavList = new TextField(transactionsPageNavList, "The 'Transactions' page navigation list");
    @Getter
    TextField NoTransactionsMessage = new TextField(noTransactionsMessage, "The 'no transactions' message");

    @Step("Wait until the 'Transactions' page is opened")
    public TransactionsPage waitUntilTransactionsPageOpened() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("/transactions"));
        return this;
    }
    @Step("Read the URL of the current page")
    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
    @Step("Check the 'Transactions' page is still the open one")
    public boolean isTransactionsPageOpened() {
        return getCurrentUrl().contains("/transactions");
    }
    @Step("Open the calendar of the 'Date From' field")
    public TransactionsPage openDateFromCalendar() {
        getDateFrom().click();
        isCalendarOpened();
        return this;
    }
    @Step("Open the calendar of the 'Date To' field")
    public TransactionsPage openDateToCalendar() {
        getDateTo().click();
        isCalendarOpened();
        return this;
    }
    /**
     * The panel is in the DOM before the popup animation ends, and a click or an Escape sent in
     * that window is swallowed: the calendar stays open with nothing picked. The datepicker moves
     * the focus onto the active day once the animation is done, so a focused day inside the panel
     * is the signal that the calendar takes input.
     */
    @Step("Check the calendar is open and takes input")
    public boolean isCalendarOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(calendarPanel));
            wait.until(driver -> Boolean.TRUE.equals(((JavascriptExecutor) driver).executeScript(
                    "const active = document.activeElement;"
                            + "return !!active && !!active.closest('.mat-datepicker-content');")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    @Step("Check the calendar is closed")
    public boolean isCalendarClosed() {
        return getCalendarPanel().invisibilityOfElementLocated(10);
    }
    @Step("Read the month shown by the open calendar")
    public String getCalendarMonth() {
        return getCalendarMonthLabel().getText();
    }
    @Step("Move the open calendar to the next month")
    public TransactionsPage clickCalendarNextMonth() {
        getCalendarNextMonthBtn().clickButton();
        return this;
    }
    @Step("Move the open calendar to the previous month")
    public TransactionsPage clickCalendarPreviousMonth() {
        getCalendarPreviousMonthBtn().clickButton();
        return this;
    }
    @Step("Count the days offered by the open calendar")
    public int getCalendarDayCount() {
        return getDriver().findElements(calendarDayCells).size();
    }
    @Step("Count the days the open calendar does not let the user pick")
    public int getDisabledCalendarDayCount() {
        return getDriver().findElements(calendarDisabledDayCells).size();
    }
    /**
     * The calendar opens on the month of the date the field already holds, and the page pre-fills a
     * range that starts a month back, so today's cell is not in the DOM until the view is stepped
     * forward to the current month. The pick itself is retried for the same reason Escape is: the
     * popup swallows what arrives while it is still animating, and picking the same day twice is
     * the same date either way.
     */
    @Step("Pick today in the open calendar")
    public TransactionsPage pickToday() {
        isCalendarOpened();
        for (int month = 0; month < MAX_MONTHS_TO_ADVANCE; month++) {
            if (!getDriver().findElements(calendarTodayCell).isEmpty()
                    || getCalendarNextMonthBtn().isDisabled()) {
                break;
            }
            clickCalendarNextMonth();
        }
        for (int attempt = 0; attempt < PICK_ATTEMPTS; attempt++) {
            getCalendarTodayCell().clickButton();
            if (getCalendarPanel().invisibilityOfElementLocated(3)) {
                return this;
            }
        }
        return this;
    }
    /**
     * Sends Escape more than once when the calendar does not react. The popup keeps eating keys for
     * a short while after it appears, and the moment it stops cannot be read off the markup, so a
     * single key press is lost often enough to make the check flaky. A calendar that stays open
     * through every attempt still fails the assertion that follows.
     */
    @Step("Close the open calendar with Escape")
    public TransactionsPage closeCalendarWithEscape() {
        for (int attempt = 0; attempt < ESCAPE_ATTEMPTS; attempt++) {
            new Actions(getDriver())
                    .sendKeys(Keys.ESCAPE)
                    .perform();
            if (getCalendarPanel().invisibilityOfElementLocated(3)) {
                return this;
            }
        }
        return this;
    }
    @Step("Read the value of the 'Date From' field")
    public String getDateFromValue() {
        return getDateFrom().getValue();
    }
    @Step("Read the value of the 'Date To' field")
    public String getDateToValue() {
        return getDateTo().getValue();
    }
    @Step("Set the date range to today in both fields")
    public TransactionsPage setDateRangeToToday() {
        openDateFromCalendar();
        pickToday();
        isCalendarClosed();
        openDateToCalendar();
        pickToday();
        isCalendarClosed();
        return this;
    }
    @Step("Open the 'Transactions Type' dropdown")
    public TransactionsPage openTransactionsTypeDropdown() {
        getTransactionsTypeDropdown().clickButton();
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(transactionsOptions));
        return this;
    }
    @Step("Select the transaction type in the open dropdown")
    public TransactionsPage selectTransactionsType(Button option) {
        option.clickButton();
        return this;
    }
    @Step("Read the transaction type the filter runs with")
    public String getSelectedTypeName() {
        return getSelectedTransactionsType().getText();
    }
    @Step("Check the options of the 'Transactions Type' dropdown are gone")
    public boolean areTransactionsTypeOptionsClosed() {
        return getDepositOption().invisibilityOfElementLocated(10);
    }
    @Step("Click on the 'Filter' button")
    public TransactionsPage clickOnFilter() {
        getFilterButton().clickButton();
        return this;
    }
    @Step("Wait until the transactions list is reloaded")
    public boolean waitUntilTransactionsListIsShown() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(transactionsPageNavList));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    /**
     * A filter is answered either by a list of transactions or by the message that there are none,
     * and both are a result: the account simply has nothing of that type in that range.
     */
    @Step("Wait until the filter is answered with a list or with the 'no transactions' message")
    public boolean waitUntilFilterIsAnswered() {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                    .until(ExpectedConditions.or(
                            ExpectedConditions.visibilityOfElementLocated(transactionsPageNavList),
                            ExpectedConditions.visibilityOfElementLocated(noTransactionsMessage)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    @Step("Check the filter came back with at least one transaction")
    public boolean hasTransactions() {
        return !getDriver().findElements(transactionsPageNavList).isEmpty();
    }
}
