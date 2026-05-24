package io.maxbet.pageObjects;

import io.maxbet.Elements.TextField;
import lombok.Getter;
import org.openqa.selenium.By;

public class TransactionsPage extends AbstractPage{
    private final By transactionsPageTitle = By.xpath("//h2[normalize-space()='Transactions']");

    @Getter
    TextField TransactionsPageTitle = new TextField(transactionsPageTitle, "The 'Transactions' page title");
}
