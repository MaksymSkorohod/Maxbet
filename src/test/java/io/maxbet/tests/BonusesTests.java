package io.maxbet.tests;

import io.maxbet.pageObjects.BonusesPage;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.ProfilePage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BonusesTests extends TestBase {
    /** The test bonus code the environment accepts. */
    private static final String BONUS_CODE = "joker";

    private BonusesPage bonusesPage;

    @BeforeMethod(alwaysRun = true)
    public void openBonusesPage() {
        ProfilePage profilePage = new LobbyPage().clickOnUserInfo();
        profilePage.getProfileMenu().verify();
        bonusesPage = profilePage.clickOnBonuses();
        bonusesPage.getActiveBonusesTitle().verify();
    }

    @Test(priority = 1, description = "A bonus added by its code appears in the 'Active bonuses' list")
    public void addBonusByCodeAddsAnActiveBonus() {
        int before = bonusesPage.getActiveBonusesCount();

        bonusesPage.addBonus(BONUS_CODE);
        Assert.assertTrue(bonusesPage.waitUntilActiveBonusesCountIs(before + 1),
                "The bonus '" + BONUS_CODE + "' was not added: expected " + (before + 1)
                        + " active bonus(es) but found " + bonusesPage.getActiveBonusesCount());
    }

    @Test(priority = 2, description = "An active bonus can be removed from the 'Active bonuses' list")
    public void removeBonusTakesItOutOfTheList() {
        if (!bonusesPage.hasActiveBonus()) {
            throw new SkipException(
                    "No active bonus is present, so the remove flow cannot be exercised");
        }
        int before = bonusesPage.getActiveBonusesCount();
        bonusesPage.removeFirstActiveBonus();
        Assert.assertTrue(bonusesPage.waitUntilActiveBonusesCountIs(before - 1),
                "The bonus is still active after removal: expected " + (before - 1)
                        + " active bonus(es) but found " + bonusesPage.getActiveBonusesCount());
    }

    @Test(priority = 3, description = "The 'Bonus History' section is shown and lists the account's past bonuses")
    public void bonusHistoryListsPastBonuses() {
        bonusesPage.getBonusesHistoryTitle().verify();
        bonusesPage.getBonusesHistoryTable().verify();

        Assert.assertTrue(bonusesPage.getBonusHistoryCount() > 0,
                "Expected at least one entry in the 'Bonus History', but the list was empty");
    }
}
