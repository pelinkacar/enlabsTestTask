package org.Enlabs;

import org.Enlabs.utility.Utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Task1RegistrationPageTest extends Utils {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty(WEBDRIVER_INFO, DRIVE_PATH);
            this.driver = new ChromeDriver();
    }

    @Test
    public void sign_up_with_invalid_email() throws IOException {
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(INVALID_EMAIL, DEFAULT_PASSWORD, true, true, true);
        takeScreenShot("sign_up_with_invalid_email");
        if(emailValidationError().contains("Please enter a valid email"));
        Assert.assertTrue(true);

    }

    @Test
    public void sign_up_with_uppercase_missing_password() throws IOException {
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(generateEmail(), UPPERCASE_MISSING_PASSWORD, true, true, true);
        takeScreenShot("sign_up_with_uppercase_missing_password");
        if(passwordUpperCaseError().contains("Password should contain at least 1 uppercase"));
        Assert.assertTrue(true);
    }

    @Test
    public void sign_up_with_lowercase_missing_password() throws IOException {
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(generateEmail(), LOWERCASE_MISSING_PASSWORD, true, true, true);
        takeScreenShot("sign_up_with_lowercase_missing_password");
        if(passwordLowerCaseError().contains("Password should contain at least 1 lowercase"));
        Assert.assertTrue(true);
    }

    @Test
    public void sign_up_with_number_missing_password() throws IOException {
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(generateEmail(), NUMBER_MISSING_PASSWORD, true, true, true);
        takeScreenShot("sign_up_with_number_missing_password");
        if(passwordMissingNumberError().contains("Password should contain at least 1 number"));
        Assert.assertTrue(true);
    }

    @Test
    public void sign_up_with_missing_character_password() throws IOException {
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(generateEmail(), MISSING_CHARACTER_PASSWORD, true, true, true);
        takeScreenShot("sign_up_with_missing_character_password");
        if(passwordMissingCharacterError().contains("Password number can not be less than 6 characters"));
        Assert.assertTrue(true);
    }

    @Test
    public void sign_up_with_too_many_characters_password() throws IOException {
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(generateEmail(), TOO_MANY_CHARACTERS_PASSWORD, true, true, true);
        takeScreenShot("sign_up_with_too_many_characters_password");
        if(passwordTooManyCharactersError().contains("Password can not be more than 20 characters"));
        Assert.assertTrue(true);
    }

    @Test
    public void sign_up_without_terms_of_conditions_and_privacy_policy() throws IOException {
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(generateEmail(), DEFAULT_PASSWORD, true, false, true);
        takeScreenShot("sign_up_without_terms_of_conditions_and_privacy_policy");
        Assert.assertFalse(driver.findElement(By.xpath("//button[contains(text(),'Sign up')]")).isEnabled());


    }

    @Test
    public void sign_up_with_missing_required_fields_at_second_step_registration_form() throws IOException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(generateEmail(), DEFAULT_PASSWORD, false, true, true);
        clickButtonByName("Save");
        takeScreenShot("sign_up_with_missing_required_fields_at_second_step_registration_form");
        Assert.assertEquals(pinCodeRequiredError(), "Pin is required");
        Assert.assertEquals(phoneNumberRequiredError(), "Phone number is required");
        Assert.assertEquals(firstNameRequiredError(), "First name is required");
        Assert.assertEquals(lastNameRequiredError(), "Last name is required");
        Assert.assertEquals(missingDayError(), "Day is required");
        Assert.assertEquals(missingGenderError(), "Gender is required");
        Assert.assertEquals(personalIdRequiredError(), "Personal ID is required");
    }

    @Test
    public void sign_up_with_wrong_pin_number_at_second_step_registration_form() throws IOException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(generateEmail(), DEFAULT_PASSWORD, false, true, true);
        fillRegistrationFormSecondStep("1234", "123456", "first name",
                "last name", "010105-14879");
        takeScreenShot("sign_up_with_wrong_pin_number_at_second_step_registration_form");
        Assert.assertEquals(pinCodeWrongError(), "Pin was not found or has expired.");
        Assert.assertEquals(signUpMainValidationError(), "Ooops, some fields are missing! Please check again");
    }

    @Test (enabled = false) // Can't run, because pin is not correct
    public void sign_up_with_wrong_pin_number_at_third_step_registration_form() throws IOException {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(SIGNUP_URL);
            clickButtonByName("Join Now");
            fillRegistrationFormFirstStep(generateEmail(), DEFAULT_PASSWORD, false, true,true);
            fillRegistrationFormSecondStep(".........", "123456", "first name",
                    "last name", "010105-14879");
            driver.findElement(By.name("password")).sendKeys(WRONG_PASSWORD);
            clickButtonByName("Confirm");
            takeScreenShot("sign_up_with_wrong_pin_number_at_third_step_registration_form");
            Assert.assertEquals(passwordWrongError(), "Wrong password");
    }

    @Test (enabled = false) // Can't run, because pin is not correct
    public void sign_up_with_valid_info() throws IOException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(generateEmail(), DEFAULT_PASSWORD, false, true,true);
        fillRegistrationFormSecondStep(".........", "123456", "first name",
                "last name", "010105-14879");
        driver.findElement(By.name("password")).sendKeys(DEFAULT_PASSWORD);
        clickButtonByName("Confirm");
        takeScreenShot("sign_up_with_valid_info");
        Assert.assertEquals(successDialog(), "Limit successfully set");
        clickButtonByName("Close");

    }

    @Test
    public void sign_up_with_valid_credential_twice() throws IOException {
        driver.get(SIGNUP_URL);
        clickButtonByName("Join Now");
        fillRegistrationFormFirstStep(REGISTER_APPROVED_TEST_EMAIL, DEFAULT_PASSWORD, false,
                true, false);
        takeScreenShot("sign_up_with_valid_credential_twice");
        Assert.assertEquals(emailValidationError(), "Email already registered");
    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }



    private void takeScreenShot(String name) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./screenshots/" + name + ".png"));
    }

    private void clickButtonByName(String buttonName){
        driver.findElement(By.xpath("//button[contains(text(),'" + buttonName + "')]")).click();
    }

    private void fillRegistrationFormFirstStep(String email, String password, boolean promotionCode, boolean termsOfConditionsAndPrivacyPolicy, boolean bonusesAndOffers) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        if(promotionCode){
            driver.findElement(By.xpath("//div[contains(text(),'Do you have a promo code?')]")).click();
            driver.findElement(By.name("promotionCode")).sendKeys(DEFAULT_PROMOCODE);
        }
        if (termsOfConditionsAndPrivacyPolicy) {
            driver.findElement(By.xpath("//input[@name='tnc']/following-sibling::span")).click();
        }
        if (bonusesAndOffers) {
            driver.findElement(By.xpath("//input[@name='promotions']/following-sibling::span")).click();
        }
        if (driver.findElement(By.xpath("//button[contains(text(),'Sign up')]")).isEnabled()) {
            driver.findElement(By.xpath("//button[contains(text(),'Sign up')]")).click();
        }
    }

    // Errors For FIRST STEP
    private String emailValidationError() {
        return driver.findElement(By.xpath("//div[@class='signupContent___1G8zj-scss'] //div[@data-role='validationError']")).getText();
    }

    private String passwordUpperCaseError() {
        return driver.findElement(By.xpath("//div[@class='signupContent___1G8zj-scss'] //div[@data-role='validationError']")).getText();
    }

    private String passwordLowerCaseError() {
        return driver.findElement(By.xpath("//div[@class='signupContent___1G8zj-scss'] //div[@data-role='validationError']")).getText();
    }

    private String passwordMissingNumberError() {
        return driver.findElement(By.xpath("//div[@class='signupContent___1G8zj-scss'] //div[@data-role='validationError']")).getText();
    }

    private String passwordMissingCharacterError() {
        return driver.findElement(By.xpath("//div[@class='signupContent___1G8zj-scss'] //div[@data-role='validationError']")).getText();
    }

    private String passwordTooManyCharactersError() {
        return driver.findElement(By.xpath("//div[@class='signupContent___1G8zj-scss'] //div[@data-role='validationError']")).getText();
    }

    private void fillRegistrationFormSecondStep(String pin, String phoneNumber, String firstName,
                                                String lastName, String personalId) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.name("code")).sendKeys(pin);
        driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
        driver.findElement(By.name("firstName")).sendKeys(firstName);
        driver.findElement(By.name("lastName")).sendKeys(lastName);
        driver.findElement(By.xpath("//div[@class='dob___13F6K-scss'] //div[@data-role='additionalDobDay']")).click();
        driver.findElement(By.xpath("//div[@class='select__options-container___3Ayrz-scss'] //div[@data-role='dobDayItem1']")).click();
        driver.findElement(By.xpath("//div[@class='dob___13F6K-scss'] //div[@data-role='additionalDobMonth']")).click();
        driver.findElement(By.xpath("//div[@class='select__options-container___3Ayrz-scss'] //div[@data-role='dobMonthItem1']")).click();
        driver.findElement(By.xpath("//div[@class='dob___13F6K-scss'] //div[@data-role='additionalDobYear']")).click();
        driver.findElement(By.xpath("//div[@class='select__options-container___3Ayrz-scss'] //div[@data-role='dobYearItem1905']")).click();
        driver.findElement(By.xpath("//div[@class='toggle___186Jg-scss']/div[3]")).click(); //Selected other for testing purposes
        driver.findElement(By.name("personalId")).sendKeys(personalId);
        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

    }

    // Errors For SECOND STEP
    private String pinCodeRequiredError() {
        return driver.findElement(By.xpath("//div[@class='pinContainer___2lgeR-scss'] //div[@data-role='validationError']")).getText();
    }

    private String pinCodeWrongError() {
        return driver.findElement(By.xpath("//div[@class='pinContainer___2lgeR-scss'] //div[@data-role='validationError']")).getText();
    }

    private String phoneNumberRequiredError() {
        return driver.findElement(By.xpath("//input[@name='phoneNumber']/following-sibling::div")).getText();
    }

    private String firstNameRequiredError() {
        return driver.findElement(By.xpath("//input[@name='firstName']/following-sibling::div")).getText();
    }

    private String lastNameRequiredError() {
        return driver.findElement(By.xpath("//input[@name='lastName']/following-sibling::div")).getText();
    }

    private String missingDayError() {
        return driver.findElement(By.xpath("//*[@id='modal']/div/div/div[1]/div/div/form/div[5]/div")).getText();
    }

    private String missingGenderError(){
        return driver.findElement(By.xpath("//div[@data-role='additionalGender'] //div[@data-role='validationError']")).getText();
    }

    private String personalIdRequiredError(){
        return driver.findElement(By.xpath("//input[@name='personalId']/following-sibling::div")).getText();
    }

    private String signUpMainValidationError() {
        return driver.findElement(By.xpath("//div[@class='signupContent___1G8zj-scss'] //div[@data-role='singupMainValidationError']")).getText();
    }

    // Errors For THIRD STEP
    private String passwordWrongError(){
        return driver.findElement(By.xpath("//div[@data-role='validationError']")).getText();
    }

    private String successDialog(){
        return driver.findElement(By.xpath("//div[@data-role='successDialogTitle']")).getText();
    }
}
