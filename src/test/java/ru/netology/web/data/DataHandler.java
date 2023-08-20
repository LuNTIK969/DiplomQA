package ru.netology.web.data;

import lombok.Value;

public class DataHandler {

    // private static DataBuilder DataBuilder = new DataBuilder();

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    public static CardInfo getEmptyMonth() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMounthEmpty(), DataBuilder.getYearShift(1), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getZeroMonth() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthZero(), DataBuilder.getYearShift(2), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getMoreThanMonth() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMounthMore(), DataBuilder.getYearShift(3), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getMonthInCurrentYear() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(-1), DataBuilder.getYearShift(0), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getOneDigitMonth() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthOne(), DataBuilder.getYearShift(1), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(DataBuilder.getDeclinedCard(), DataBuilder.getMonthShift(2), DataBuilder.getYearShift(2), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getCardEmpty() {
        return new CardInfo(DataBuilder.getEmptyCard(), DataBuilder.getMonthShift(3), DataBuilder.getYearShift(3), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(1), DataBuilder.getYearShift(1), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getCardFifteenNumber() {
        return new CardInfo(DataBuilder.getCardFifteen(), DataBuilder.getMonthShift(2), DataBuilder.getYearShift(2), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getCardOneNumber() {
        return new CardInfo(DataBuilder.getInvalidCardOne(), DataBuilder.getMonthShift(1), DataBuilder.getYearShift(1), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getCardAllZero() {
        return new CardInfo(DataBuilder.getCardAllZero(), DataBuilder.getMonthShift(3), DataBuilder.getYearShift(3), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getCardAnother() {
        return new CardInfo(DataBuilder.getCardNotDeclineAndApproved(), DataBuilder.getMonthShift(1), DataBuilder.getYearShift(1), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getYearGreaterThanDateExp() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(1), DataBuilder.getYearShift(6), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getYearExp() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(1), DataBuilder.getYearShift(-1), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getEmptyYear() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(1), DataBuilder.getYearEmpty(), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getZeroYear() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(3), DataBuilder.getYearZero(), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getOneNumberInYear() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(2), DataBuilder.getYearOne(), DataBuilder.getValidOwner(), DataBuilder.getValidCvc());
    }

    public static CardInfo getCvcEmpty() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(3), DataBuilder.getYearShift(1), DataBuilder.getValidOwner(), DataBuilder.getCvcEmpty());
    }

    public static CardInfo getOneNumberCvc() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(2), DataBuilder.getYearShift(2), DataBuilder.getValidOwner(), DataBuilder.getCvcOne());
    }

    public static CardInfo getTwoNumbersCvc() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(1), DataBuilder.getYearShift(3), DataBuilder.getValidOwner(), DataBuilder.getCvcTwo());
    }

    public static CardInfo getZeroCvc() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(2), DataBuilder.getYearShift(1), DataBuilder.getValidOwner(), DataBuilder.getInvalidCvc());
    }

    public static CardInfo getOneLetterOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(2), DataBuilder.getYearShift(2), DataBuilder.getOwnerOneLetter(), DataBuilder.getValidCvc());
    }

    public static CardInfo getManyLetterOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(2), DataBuilder.getYearShift(2), DataBuilder.getOwnerManyLetter(), DataBuilder.getValidCvc());
    }

    public static CardInfo getThreeWordsOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(3), DataBuilder.getYearShift(1), DataBuilder.getOwnerThree(), DataBuilder.getValidCvc());
    }

    public static CardInfo getOneWordOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(1), DataBuilder.getYearShift(3), DataBuilder.getOwnerOne(), DataBuilder.getValidCvc());
    }

    public static CardInfo getCyrillicInOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(2), DataBuilder.getYearShift(2), DataBuilder.getOwnerCyrillic(), DataBuilder.getValidCvc());
    }

    public static CardInfo getEmptyOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(3), DataBuilder.getYearShift(1), DataBuilder.getOwnerEmpty(), DataBuilder.getValidCvc());
    }

    public static CardInfo getNumbersInOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(1), DataBuilder.getYearShift(3), DataBuilder.getOwnerNumber(), DataBuilder.getValidCvc());
    }

    public static CardInfo getSpecSymbolsInOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(2), DataBuilder.getYearShift(1), DataBuilder.getOwnerSpecialSymbols(), DataBuilder.getValidCvc());
    }

    public static CardInfo getUpperOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(3), DataBuilder.getYearShift(1), DataBuilder.getOwnerUpper(), DataBuilder.getValidCvc());
    }

    public static CardInfo getLowerOwner() {
        return new CardInfo(DataBuilder.getApprovedCard(), DataBuilder.getMonthShift(1), DataBuilder.getYearShift(3), DataBuilder.getOwnerLower(), DataBuilder.getValidCvc());
    }

}