package io.maxbet.pageObjects;

import io.maxbet.Elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;

public class AccountSecurityPage extends AbstractPage{
    private final By accountSecurityPageTitle = By.xpath("//app-root/mb-root[@id='mb-root']/div[@class='body-wrapper']/div[@class='main-content-wrapper']/mb-profile[@class='ng-star-inserted']/div[@class='profile-container']/div[@class='content']/mb-account-security[@class='ng-star-inserted']/mb-content-layout[@class='mb-content-layout context-profile mobile-header-fixed']/section[1]/div[1]");

    @Getter
    TextField AccountSecurityPageTitle = new TextField(accountSecurityPageTitle, "The 'Account Security' page title");
}
