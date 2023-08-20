package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHandler;
import ru.netology.web.data.Sql;
import ru.netology.web.page.MainPage;
import ru.netology.web.page.BillingPage;
import ru.netology.web.data.DataHandler.CardInfo;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

public class DebitTest {

    static MainPage mainPage;

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        mainPage = open(System.getProperty("sut.url"), MainPage.class);
    }

    @AfterAll
    public static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        Sql.cleanDb();
    }

    @Test
    public void performDebitCardPaymentWithApprovedStatus() throws SQLException {
        CardInfo validCardApproved = DataHandler.getApprovedCard();
        BillingPage billingPage = mainPage.getCardDebitPayment();

        billingPage.completePaymentForm(validCardApproved);
        billingPage.validateSuccess();

        String billingStatus = Sql.getRequestStatus();
        assertEquals("APPROVED", billingStatus);
    }

    @Test
    public void avoidPaymentDebitDeclinStatus() throws SQLException {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo validCardDeclined = DataHandler.getDeclinedCard();

        billingPage.completePaymentForm(validCardDeclined);
        billingPage.errorVerify();

        String billingStatus = Sql.getRequestStatus();
        assertEquals("DECLINED", billingStatus);
    }

    @Test
    public void avoidPaymentDebitInvalidCardNumber() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo invalidStatusCard = DataHandler.getCardAllZero();

        billingPage.completePaymentForm(invalidStatusCard);
        billingPage.errorVerify();
    }

    @Test
    public void avoidPaymentUsingDebitNumberDifferentBankCard() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo extraCard = DataHandler.getCardAnother();

        billingPage.completePaymentForm(extraCard);
        billingPage.errorVerify();
    }


    @Test
    public void avoidPaymentDebitOneNumber() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardFifteenNumber = DataHandler.getCardOneNumber();

        billingPage.completePaymentForm(cardFifteenNumber);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitFifteenCardNumber() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardFifteenNumber = DataHandler.getCardFifteenNumber();

        billingPage.completePaymentForm(cardFifteenNumber);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitEmptyCardNumber() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardEmpty = DataHandler.getCardEmpty();

        billingPage.completePaymentForm(cardEmpty);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitInvalidMonth() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardDataContainInvalidMonth = DataHandler.getMoreThanMonth();

        billingPage.completePaymentForm(cardDataContainInvalidMonth);
        billingPage.invalidDateValidate();
    }

    @Test
    public void avoidPaymentDebitCardZeroMonth() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo zeroMonthCard = DataHandler.getZeroMonth();

        billingPage.completePaymentForm(zeroMonthCard);
        billingPage.invalidDateValidate();
    }

    @Test
    public void avoidPaymentDebitZeroYear() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardZeroYear = DataHandler.getZeroYear();

        billingPage.completePaymentForm(cardZeroYear);
        billingPage.invalidDateValidate();
    }

    @Test
    public void avoidPaymentDebitCardExpMonthForCurrentYear() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardDataExpiredMonth = DataHandler.getMonthInCurrentYear();

        billingPage.completePaymentForm(cardDataExpiredMonth);
        billingPage.validateCard();
    }

    @Test
    public void avoidPaymentDebitCardOneMonth() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardDataOneNumberMonth = DataHandler.getOneDigitMonth();

        billingPage.completePaymentForm(cardDataOneNumberMonth);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitCardEmptyMonth() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardEmptyMonth = DataHandler.getEmptyMonth();

        billingPage.completePaymentForm(cardEmptyMonth);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitCardExpYear() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardYearExp = DataHandler.getYearExp();

        billingPage.completePaymentForm(cardYearExp);
        billingPage.validateCard();
    }

    @Test
    public void avoidPaymentDebitInvalidYearExp() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo yearGreaterThanExp = DataHandler.getYearGreaterThanDateExp();

        billingPage.completePaymentForm(yearGreaterThanExp);
        billingPage.invalidDateValidate();
    }

    @Test
    public void avoidPaymentDebitYearOneNumber() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardOneNumberInYear = DataHandler.getOneNumberInYear();

        billingPage.completePaymentForm(cardOneNumberInYear);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditEmptyYear() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo yearEmptyCard = DataHandler.getEmptyYear();

        billingPage.completePaymentForm(yearEmptyCard);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitCyrillicOwner() {
        BillingPage paymentPage = mainPage.getCardDebitPayment();
        CardInfo info = DataHandler.getCyrillicInOwner();

        paymentPage.completePaymentForm(info);
        paymentPage.wrongCheck();
    }

    @Test
    public void shouldNotDoPaymentByDebitCardWhenOwnerFieldWithOneLatinWord() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardCyrillicInOwner = DataHandler.getOneWordOwner();

        billingPage.completePaymentForm(cardCyrillicInOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitThreeWordsOwner() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardThreeWordsOwner = DataHandler.getThreeWordsOwner();

        billingPage.completePaymentForm(cardThreeWordsOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitOwnerSingleLetter() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardOneLetterOwner = DataHandler.getOneLetterOwner();

        billingPage.completePaymentForm(cardOneLetterOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitOwnerWithLargeName() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardManyLetterOwner = DataHandler.getManyLetterOwner();

        billingPage.completePaymentForm(cardManyLetterOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditOwnerNumber() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardNumbersInOwner = DataHandler.getNumbersInOwner();

        billingPage.completePaymentForm(cardNumbersInOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitSpecSymbolsInOwner() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardSpecSymbolsInOwner = DataHandler.getSpecSymbolsInOwner();

        billingPage.completePaymentForm(cardSpecSymbolsInOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitEmptyOwner() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardEmptyOwner = DataHandler.getEmptyOwner();

        billingPage.completePaymentForm(cardEmptyOwner);
        billingPage.emptyCheck();
    }

    @Test
    public void avoidPaymentDebitOneNumberCvc() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardOneNumberCvc = DataHandler.getOneNumberCvc();

        billingPage.completePaymentForm(cardOneNumberCvc);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitTwoNumbersCvc() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardTwoNumbersCvc = DataHandler.getTwoNumbersCvc();

        billingPage.completePaymentForm(cardTwoNumbersCvc);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitZeroCvc() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardZeroCvc = DataHandler.getZeroCvc();

        billingPage.completePaymentForm(cardZeroCvc);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentDebitCvcEmpty() {
        BillingPage billingPage = mainPage.getCardDebitPayment();
        CardInfo cardCvcEmpty = DataHandler.getCvcEmpty();

        billingPage.completePaymentForm(cardCvcEmpty);
        billingPage.wrongCheck();
    }
}