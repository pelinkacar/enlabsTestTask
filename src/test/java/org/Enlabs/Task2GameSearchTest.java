package org.Enlabs;

import org.Enlabs.utility.Utils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Task2GameSearchTest extends Utils {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty(WEBDRIVER_INFO, DRIVE_PATH);
        this.driver = new ChromeDriver();
    }

    @Test
    public void search_the_game_and_click() throws IOException {
        driver.get(SIGNUP_URL);
        driver.manage().window().maximize();
        clickCasinoLink();
        clickAllTab();
        clickAllProvidersTab();
        clickiSoftBet();
        clickFirstGame();
        takeScreenShot("search_the_game_and_click");


    }

    @AfterMethod
    public void teardown() {
        driver.close();
    }


    private void takeScreenShot(String name) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./screenshots/" + name + ".png"));
    }

    private void clickCasinoLink() {
        driver.findElement(By.xpath("//*[@data-role='navMainMenu']//a[contains(text(),'Casino')]")).click();
    }

    private void clickAllTab() {
        driver.findElement(By.xpath("//*[@data-role='categoriesList']//a[contains(text(),'All')]")).click();
    }

    private void clickAllProvidersTab() {
        driver.findElement(By.xpath("//li[contains(text(),'All providers')]")).click();
    }

    private void clickiSoftBet() {
        driver.findElement(By.xpath("//li[contains(text(),'iSoftBet')]")).click();
    }

    private void clickFirstGame() {
        Actions b = new Actions(driver);
        b.moveToElement(driver.findElement(By.cssSelector("div.gridItem___3AoD--scss"))).click().build().perform();
    }
}
