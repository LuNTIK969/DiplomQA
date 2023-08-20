package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    private final SelenideElement head = $("h2.heading");
    private final SelenideElement purchaseButtonForCredit = $$(".button").last();
    private final SelenideElement creditCardDetails = $$("h3.heading").filterBy(Condition.exactText("Кредит по данным карты")).first();
    private final SelenideElement purchaseButton = $$(".button").first();
    private final SelenideElement payWithCard = $$("h3.heading").filterBy(Condition.exactText("Оплата по карте")).first();
    private final BillingPage billingPage = new BillingPage();

    public MainPage() {
        head.shouldBe(Condition.visible);
    }

    public BillingPage getCardCreditPayment() {
        purchaseButtonForCredit.click();
        creditCardDetails.shouldBe(Condition.visible);
        return billingPage;
    }

    public BillingPage getCardDebitPayment() {
        purchaseButton.click();
        payWithCard.shouldBe(Condition.visible);
        return billingPage;
    }
}