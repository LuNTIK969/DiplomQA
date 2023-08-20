package ru.netology.web.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataBuilder {

    private static final Faker faker = new Faker(new Locale("en"));
    private static final Faker fakerRU = new Faker(new Locale("ru"));

    public static String getApprovedCard() { return "4444 4444 4444 4441"; }
    public static String getDeclinedCard() { return "4444 4444 4444 4442"; }
    public static String getInvalidCardOne() { return "4"; }
    public static String getEmptyCard() { return ""; }
    public static String getCardFifteen() { return "4444 4444 4444 444"; }
    public static String getCardAllZero() { return "0000 0000 0000 0000"; }
    public static String getCardNotDeclineAndApproved() { return "4444 4444 4444 4443"; }

    public static String getMonthShift(int monthShift) { return LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM")); }
    public static String getMounthMore() { return "13"; }
    public static String getMonthZero() { return "00"; }
    public static String getMonthOne() { return "1"; }
    public static String getMounthEmpty() { return ""; }

    public static String getYearShift(int yearShift) { return LocalDate.now().plusYears(yearShift).format(DateTimeFormatter.ofPattern("yy")); }
    public static String getYearZero() { return "00"; }
    public static String getYearOne() { return "0"; }
    public static String getYearEmpty() { return ""; }

    public static String getValidOwner() { return faker.name().firstName() + " " + faker.name().lastName(); }
    public static String getOwnerCyrillic() { return fakerRU.name().firstName() + " " + fakerRU.name().lastName(); }
    public static String getOwnerOne() { return faker.name().firstName(); }
    public static String getOwnerThree() { return "Sydorov KONSTANTIN Sergeevich"; }
    public static String getOwnerLower() { return "Sydorov KONSTANTIN"; }
    public static String getOwnerUpper() { return "KONSTANTIN IVANOV"; }
    public static String getOwnerOneLetter() { return "A"; }
    public static String getOwnerManyLetter() { return "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; }
    public static String getOwnerNumber() { return "12345"; }
    public static String getOwnerSpecialSymbols() { return "!@#$%^&?*"; }
    public static String getOwnerEmpty() { return ""; }

    public static String getValidCvc() { return faker.numerify("###"); }
    public static String getInvalidCvc() { return "000"; }
    public static String getCvcTwo() { return "12"; }
    public static String getCvcOne() { return "1"; }
    public static String getCvcEmpty() { return ""; }
}