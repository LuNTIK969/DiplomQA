package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHandler;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BillingPage {

    private final SelenideElement entryYear = $("[placeholder='22']");
    private final SelenideElement button = $$(".button").find(exactText("Продолжить"));
    private final SelenideElement entryOwner = $$(".input").find(exactText("Владелец")).$(".input__control");
    private final SelenideElement cvcCvv = $("[placeholder='999']");
    private final SelenideElement entryCard = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement entryMonth = $("[placeholder='08']");

    private final SelenideElement formatWrong = $(byText("Неверный формат"));
    private final SelenideElement error = $(".notification_status_error");
    private final SelenideElement dateInvalidInput = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement okStatus = $(".notification_status_ok");
    private final SelenideElement empty = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement outdatedCard = $(byText("Истёк срок действия карты"));

public void completePaymentForm(DataHandler.CardInfo info) {
    String card = info.getCardNumber();
    String month = info.getMonth();
    String year = info.getYear();
    String owner = info.getOwner();
    String cvc = info.getCvc();
    
    entryCard.setValue(card);
    entryMonth.setValue(month);
    entryYear.setValue(year);
    entryOwner.setValue(owner);
    cvcCvv.setValue(cvc);
    
    button.click();
}

    public void validateSuccess() {
        okStatus.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void errorVerify() {
        error.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    
    public void emptyCheck() {
        empty.shouldBe(Condition.visible);
    }

    public void wrongCheck() {
        formatWrong.shouldBe(Condition.visible);
    }
    
    public void validateCard() {
        outdatedCard.shouldBe(Condition.visible);
    }

    public void invalidDateValidate() {
        dateInvalidInput.shouldBe(Condition.visible);
    }
}