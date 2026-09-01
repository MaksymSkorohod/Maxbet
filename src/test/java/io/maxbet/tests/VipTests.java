package io.maxbet.tests;

import io.maxbet.Elements.Button;
import io.maxbet.Elements.TextField;
import io.maxbet.pageObjects.LobbyPage;
import io.maxbet.pageObjects.VipPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VipTests extends TestBase {

    private VipPage vipPage;

    @BeforeMethod(alwaysRun = true)
    public void openVipPage() {
        vipPage = new LobbyPage().clickOnVip();
        vipPage.waitUntilVipPageOpened();
    }

    @Test(description = "Open the 'VIP' page from the 'Lobby' page")
    public void vipPageIsOpened() {
        vipPage
                .getVipPageTitle().verify();
        vipPage
                .getAboutMaxVipClub().verify();
        vipPage
                .getHowBecomeVip().verify();
        vipPage
                .getWantToBecomeVip().verify();
        vipPage
                .getVipBenefits().verify();
        vipPage
                .getWhatMaxBetVipClub().verify();
    }

    @Test(description = "The 'VIP' page tells the account which VIP level it is on")
    public void theVipLevelIsShown() {
        String level = vipPage.getVipLevel();

        Assert.assertTrue(level.toUpperCase().contains("VIP"),
                "The title of the 'VIP' page does not name a VIP level: '" + level + "'");
    }

    @Test(description = "The 'VIP' page carries the whole set of questions about the VIP club")
    public void allQuestionsAreOnThePage() {
        Assert.assertEquals(vipPage.getQuestionCount(), vipPage.getExpectedQuestionCount(),
                "The 'VIP' page does not carry the expected number of questions");
    }

    @Test(description = "The 'VIP' page opens with every answer hidden")
    public void theAnswersStartHidden() {
        Assert.assertEquals(vipPage.getOpenedAnswerCount(), 0,
                "The 'VIP' page opens with an answer already shown");
    }

    @DataProvider(name = "vipQuestions")
    public Object[][] vipQuestions() {
        return new Object[][]{
                {"About Max VIP Club", (HeaderOf) VipPage::getAboutMaxVipClub,
                        (ContentOf) VipPage::getAboutMaxVipClubAccordionContent},
                {"How to Become a VIP", (HeaderOf) VipPage::getHowBecomeVip,
                        (ContentOf) VipPage::getHowBecomeVipAccordionContent},
                {"Want to Become a VIP", (HeaderOf) VipPage::getWantToBecomeVip,
                        (ContentOf) VipPage::getWantToBecomeVipAccordionContent},
                {"VIP Benefits", (HeaderOf) VipPage::getVipBenefits,
                        (ContentOf) VipPage::getVipBenefitsAccordionContent},
                {"What is Max VIP Club", (HeaderOf) VipPage::getWhatMaxBetVipClub,
                        (ContentOf) VipPage::getWhatMaxBetVipClubAccordionContent},
        };
    }

    @Test(dataProvider = "vipQuestions",
            description = "An accordion of the 'VIP' page shows its answer when it is clicked")
    public void anAccordionShowsItsAnswer(String name, HeaderOf header, ContentOf content) {
        Button accordion = header.of(vipPage);
        TextField answer = content.of(vipPage);

        Assert.assertNotEquals(vipPage.getQuestion(accordion).trim(), "",
                "The '" + name + "' accordion carries no question");
        Assert.assertTrue(vipPage.isAccordionClosed(answer),
                "The '" + name + "' accordion shows its answer before it was clicked");

        accordion.click();

        Assert.assertTrue(vipPage.isAccordionOpened(answer),
                "The '" + name + "' accordion did not show its answer after it was clicked");
        Assert.assertNotEquals(answer.getText().trim(), "",
                "The '" + name + "' accordion opened on an empty answer");
    }

    @Test(dataProvider = "vipQuestions",
            description = "An accordion of the 'VIP' page hides its answer again on a second click")
    public void anAccordionHidesItsAnswerOnASecondClick(String name, HeaderOf header, ContentOf content) {
        Button accordion = header.of(vipPage);
        TextField answer = content.of(vipPage);

        accordion.click();
        Assert.assertTrue(vipPage.isAccordionOpened(answer),
                "The '" + name + "' accordion did not show its answer after it was clicked");

        accordion.click();

        Assert.assertTrue(vipPage.isAccordionClosed(answer),
                "The '" + name + "' accordion still shows its answer after a second click");
    }

    @Test(description = "Opening an answer on the 'VIP' page hides the one that was open")
    public void openingAnAnswerHidesThePreviousOne() {
        vipPage.clickOnAboutMaxVipClub();
        Assert.assertTrue(vipPage.isAccordionOpened(vipPage.getAboutMaxVipClubAccordionContent()),
                "The 'About Max VIP Club' accordion did not show its answer");

        vipPage.clickOnHowBecomeVip();

        Assert.assertTrue(vipPage.isAccordionOpened(vipPage.getHowBecomeVipAccordionContent()),
                "The 'How to Become a VIP' accordion did not show its answer");
        Assert.assertTrue(vipPage.isAccordionClosed(vipPage.getAboutMaxVipClubAccordionContent()),
                "The 'About Max VIP Club' answer stayed open while another one was opened");
        Assert.assertEquals(vipPage.getOpenedAnswerCount(), 1,
                "The 'VIP' page shows more than one answer at a time");
    }

    /** Picks a question header off the page, so a data provider can name the five questions. */
    private interface HeaderOf {
        Button of(VipPage page);
    }

    /** Picks the answer that belongs to a question header. */
    private interface ContentOf {
        TextField of(VipPage page);
    }
}
