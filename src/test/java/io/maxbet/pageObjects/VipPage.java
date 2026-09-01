package io.maxbet.pageObjects;

import io.maxbet.Elements.BaseElement;
import io.maxbet.Elements.Button;
import io.maxbet.Elements.TextField;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.maxbet.DriverManager.getDriver;

public class VipPage extends BaseElement {
    /**
     * The page carries two accordions, the five questions of the VIP club and the twelve categories
     * of the footer, and the footer ones render without a question at all. Every locator below is
     * scoped to the section of the VIP questions so the footer stays out of the way, and the tabs
     * are addressed by their position because the classes Angular puts on them
     * ('ng-tns-c1793910467-16' and the like) are a build artefact: the component number changes
     * with a rebuild and the instance number with whatever else the page renders.
     */
    private static final String QUESTIONS_SECTION = "section.accordion-section";
    private static final int QUESTION_COUNT = 5;

    /** Holds the VIP level of the account, 'Auto, you are level VIP BRONZE'. */
    private final By vipPageTitle = By.cssSelector(".title");
    private final By questionHeaders = By.cssSelector(QUESTIONS_SECTION + " mb-accordion-header");
    private final By openedAnswers = By.cssSelector(QUESTIONS_SECTION + " mb-accordion-body");
    private final By aboutMaxVipClub = questionHeader(1);
    private final By aboutMaxVipClubAccordionContent = questionAnswer(1);
    private final By howBecomeVip = questionHeader(2);
    private final By howBecomeVipAccordionContent = questionAnswer(2);
    private final By wantToBecomeVip = questionHeader(3);
    private final By wantToBecomeVipAccordionContent = questionAnswer(3);
    private final By vipBenefits = questionHeader(4);
    private final By vipBenefitsAccordionContent = questionAnswer(4);
    private final By whatMaxBetVipClub = questionHeader(5);
    private final By whatMaxBetVipClubAccordionContent = questionAnswer(5);

    @Getter
    TextField VipPageTitle = new TextField(vipPageTitle, "The 'VIP' page title");
    @Getter
    Button AboutMaxVipClub = new Button(aboutMaxVipClub, "The 'About Max VIP Club' accordion");
    @Getter
    TextField AboutMaxVipClubAccordionContent = new TextField(aboutMaxVipClubAccordionContent, "The 'About Max VIP Club' accordion content");
    @Getter
    Button HowBecomeVip = new Button(howBecomeVip, "The 'How to Become a VIP' accordion");
    @Getter
    TextField HowBecomeVipAccordionContent = new TextField(howBecomeVipAccordionContent, "The 'How to Become a VIP' accordion content");
    @Getter
    Button WantToBecomeVip = new Button(wantToBecomeVip, "The 'Want to Become a VIP' accordion");
    @Getter
    TextField WantToBecomeVipAccordionContent = new TextField(wantToBecomeVipAccordionContent, "The 'Want to Become a VIP' accordion content");
    @Getter
    Button VipBenefits = new Button(vipBenefits, "The 'VIP Benefits' accordion");
    @Getter
    TextField VipBenefitsAccordionContent = new TextField(vipBenefitsAccordionContent, "The 'VIP Benefits' accordion content");
    @Getter
    Button WhatMaxBetVipClub = new Button(whatMaxBetVipClub, "The 'What is Max VIP Club' accordion");
    @Getter
    TextField WhatMaxBetVipClubAccordionContent = new TextField(whatMaxBetVipClubAccordionContent, "The 'What is Max VIP Club' accordion content");

    @Step("Click on the 'About Max VIP Club' accordion")
    public VipPage clickOnAboutMaxVipClub() {
        getAboutMaxVipClub().click();
        return this;
    }
    @Step("Click on the 'How to Become a VIP' accordion")
    public VipPage clickOnHowBecomeVip() {
        getHowBecomeVip().click();
        return this;
    }
    @Step("Click on the 'Want to Become a VIP' accordion")
    public VipPage clickOnWantToBecomeVip() {
        getWantToBecomeVip().click();
        return this;
    }
    @Step("Click on the 'VIP Benefits' accordion")
    public VipPage clickOnVipBenefits() {
        getVipBenefits().click();
        return this;
    }
    @Step("Click on the 'What is Max VIP Club' accordion")
    public VipPage clickOnWhatMaxBetVipClub() {
        getWhatMaxBetVipClub().click();
        return this;
    }
    @Step("Wait until the 'VIP' page is opened")
    public VipPage waitUntilVipPageOpened() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("/vip"));
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(questionHeaders));
        return this;
    }
    @Step("Read the VIP level shown by the page")
    public String getVipLevel() {
        return getVipPageTitle().getText();
    }
    @Step("Count the questions of the VIP club")
    public int getQuestionCount() {
        return getDriver().findElements(questionHeaders).size();
    }
    @Step("Read the question of the accordion")
    public String getQuestion(Button accordion) {
        return accordion.getText();
    }
    /**
     * The accordion says nothing about its state through a class or an aria attribute, the answer
     * is simply shown or hidden, so its visibility is the only signal there is.
     */
    @Step("Check the accordion shows its answer")
    public boolean isAccordionOpened(TextField accordionContent) {
        return accordionContent.isExists(10);
    }
    @Step("Check the accordion hides its answer")
    public boolean isAccordionClosed(TextField accordionContent) {
        return accordionContent.invisibilityOfElementLocated(10);
    }
    @Step("Count the answers the page shows at once")
    public int getOpenedAnswerCount() {
        return (int) getDriver().findElements(openedAnswers).stream()
                .filter(answer -> answer.isDisplayed())
                .count();
    }
    /** The expected number of questions, so a test can name it without repeating the number. */
    public int getExpectedQuestionCount() {
        return QUESTION_COUNT;
    }
    private static By questionHeader(int position) {
        return By.cssSelector(QUESTIONS_SECTION + " mb-accordion-tab:nth-of-type("
                + position + ") mb-accordion-header");
    }
    private static By questionAnswer(int position) {
        return By.cssSelector(QUESTIONS_SECTION + " mb-accordion-tab:nth-of-type("
                + position + ") mb-accordion-body");
    }
}
