package org.Enlabs.utility;

import java.util.Random;

public class Utils {

    public static final String WEBDRIVER_INFO = "webdriver.chrome.driver";
    public static final String DRIVE_PATH = "./src/test/resources/chromedriver";
    public static final String SIGNUP_URL = "https://www.optibet.lv/en/";
    public static final String INVALID_EMAIL = "Hey, this is invalid email dude!";
    public static final String REGISTER_APPROVED_TEST_EMAIL = "mobiltestxsw@gmail.com";
    public static final String DEFAULT_PASSWORD = "123Aa?";
    public static final String DEFAULT_PROMOCODE = "Test Promocode";
    public static final String UPPERCASE_MISSING_PASSWORD = "12";
    public static final String LOWERCASE_MISSING_PASSWORD = "12A";
    public static final String NUMBER_MISSING_PASSWORD = "Aa";
    public static final String MISSING_CHARACTER_PASSWORD = "123Aa";
    public static final String TOO_MANY_CHARACTERS_PASSWORD = "123456789Aa?2345hujkl";
    public static final String WRONG_PASSWORD = "123A";


    public static String generateEmail(){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100000);
        return "test-user-"+ randomInt +"@enlabstest.com";
    }

}
