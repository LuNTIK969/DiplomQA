package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.netology.web.data.DataHandler;
import ru.netology.web.page.MainPage;
import ru.netology.web.data.Sql;
import ru.netology.web.data.DataHandler.CardInfo;
import ru.netology.web.page.BillingPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {

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
    public void performCreditCardPaymentWithApprovedStatus() throws SQLException {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo validCardApproved = DataHandler.getApprovedCard();

        billingPage.completePaymentForm(validCardApproved);
        billingPage.validateSuccess();

        String billingStatus = Sql.getPaymentStatus();
        assertEquals("APPROVED", billingStatus);
    }


    @Test
    public void avoidPaymentCreditDeclinStatus() throws SQLException {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo validCardDeclined = DataHandler.getDeclinedCard();

        billingPage.completePaymentForm(validCardDeclined);
        billingPage.errorVerify();

        String billingStatus = Sql.getPaymentStatus();
        assertEquals("DECLINED", billingStatus);
    }

    @Test
    public void avoidPaymentCreditCardInvalidCardNumber() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo invalidStatusCard = DataHandler.getCardAllZero();

        billingPage.completePaymentForm(invalidStatusCard);
        billingPage.errorVerify();
    }

    @Test
    public void avoidPaymentUsingCardNumberDifferentBankCard() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo extraCard = DataHandler.getCardAnother();

        billingPage.completePaymentForm(extraCard);
        billingPage.errorVerify();
    }

    @Test
    public void avoidPaymentCreditOneNumber() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardWithOneNumber = DataHandler.getCardOneNumber();

        billingPage.completePaymentForm(cardWithOneNumber);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditFifteenCardNumber() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardFifteenNumber = DataHandler.getCardFifteenNumber();

        billingPage.completePaymentForm(cardFifteenNumber);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditEmptyCardNumber() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardEmpty = DataHandler.getCardEmpty();

        billingPage.completePaymentForm(cardEmpty);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditInvalidMonth() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardDataContainInvalidMonth = DataHandler.getMoreThanMonth();

        billingPage.completePaymentForm(cardDataContainInvalidMonth);
        billingPage.invalidDateValidate();
    }

    @Test
    public void avoidPaymentCreditCardZeroMonth() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo zeroMonthCard = DataHandler.getZeroMonth();

        billingPage.completePaymentForm(zeroMonthCard);
        billingPage.invalidDateValidate();
    }

    @Test
    public void avoidPaymentCreditCardExpMonthForCurrentYear() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardDataExpiredMonth = DataHandler.getMonthInCurrentYear();

        billingPage.completePaymentForm(cardDataExpiredMonth);
        billingPage.validateCard();
    }

    @Test
    public void avoidPaymentCreditCardOneMonth() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardDataOneNumberMonth = DataHandler.getOneDigitMonth();

        billingPage.completePaymentForm(cardDataOneNumberMonth);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditCardEmptyMonth() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardEmptyMonth = DataHandler.getEmptyMonth();

        billingPage.completePaymentForm(cardEmptyMonth);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditCardExpYear() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardYearExp = DataHandler.getYearExp();

        billingPage.completePaymentForm(cardYearExp);
        billingPage.validateCard();
    }

    @Test
    public void avoidPaymentCreditInvalidYearExp() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo yearGreaterThanExp = DataHandler.getYearGreaterThanDateExp();

        billingPage.completePaymentForm(yearGreaterThanExp);
        billingPage.invalidDateValidate();
    }

    @Test
    public void avoidPaymentCreditYearOneNumber() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardOneNumberInYear = DataHandler.getOneNumberInYear();

        billingPage.completePaymentForm(cardOneNumberInYear);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditEmptyYear() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo yearEmptyCard = DataHandler.getEmptyYear();

        billingPage.completePaymentForm(yearEmptyCard);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditZeroYear() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardZeroYear = DataHandler.getZeroYear();

        billingPage.completePaymentForm(cardZeroYear);
        billingPage.invalidDateValidate();
    }

    @Test
    public void avoidPaymentCreditCyrillicOwner() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardCyrillicInOwner = DataHandler.getCyrillicInOwner();

        billingPage.completePaymentForm(cardCyrillicInOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditOneWordOwner() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardOneWordOwner = DataHandler.getOneWordOwner();

        billingPage.completePaymentForm(cardOneWordOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditThreeWordsOwner() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardThreeWordsOwner = DataHandler.getThreeWordsOwner();

        billingPage.completePaymentForm(cardThreeWordsOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditOwnerSingleLetter() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardOneLetterOwner = DataHandler.getOneLetterOwner();

        billingPage.completePaymentForm(cardOneLetterOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditOwnerWithLargeName() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardManyLetterOwner = DataHandler.getManyLetterOwner();

        billingPage.completePaymentForm(cardManyLetterOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditOwnerNumber() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardNumbersInOwner = DataHandler.getNumbersInOwner();

        billingPage.completePaymentForm(cardNumbersInOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditSpecSymbolsInOwner() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardSpecSymbolsInOwner = DataHandler.getSpecSymbolsInOwner();

        billingPage.completePaymentForm(cardSpecSymbolsInOwner);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditEmptyOwner() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardEmptyOwner = DataHandler.getEmptyOwner();

        billingPage.completePaymentForm(cardEmptyOwner);
        billingPage.emptyCheck();
    }

    @Test
    public void avoidPaymentCreditOneNumberCvc() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardOneNumberCvc = DataHandler.getOneNumberCvc();

        billingPage.completePaymentForm(cardOneNumberCvc);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditTwoNumbersCvc() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardTwoNumbersCvc = DataHandler.getTwoNumbersCvc();

        billingPage.completePaymentForm(cardTwoNumbersCvc);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditZeroCvc() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardZeroCvc = DataHandler.getZeroCvc();

        billingPage.completePaymentForm(cardZeroCvc);
        billingPage.wrongCheck();
    }

    @Test
    public void avoidPaymentCreditCvcEmpty() {
        BillingPage billingPage = mainPage.getCardCreditPayment();
        CardInfo cardCvcEmpty = DataHandler.getCvcEmpty();

        billingPage.completePaymentForm(cardCvcEmpty);
        billingPage.wrongCheck();
    }

}