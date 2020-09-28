package Bitrix;

import Utilities.WebDriverFactory;
import Utilities.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AC_5_Messages {

    WebDriver driver;
    String browserType = "chrome";
    String URL = "https://login2.nextbasecrm.com";
    String username = "helpdesk28@cybertekschool.com";
    String password = "UserUser";

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void messageQuote() {
        //Login
        WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"login-popup\"]/form/div[1]/div[1]/input"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"login-popup\"]/form/div[1]/div[2]/input"));
        passwordField.sendKeys(password);
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='login-btn']"));
        loginButton.click();

        //Clicking Message Field
        WebElement messageField = driver.findElement(By.xpath("//span[@class='feed-add-post-micro-title']"));
        messageField.click();

        //Clicking Quote Button
        WebElement quoteButton = driver.findElement(By.xpath("//*[@id=\"bx-b-quote-blogPostForm\"]/span/i"));
        quoteButton.click();
        util.Wait(2);

        //Confirming Created Quote Box
        WebElement quoteBox = driver.findElement(By.xpath("//body["));
      //  driver.switchTo().frame(quoteBox);
        util.Wait(2);
        quoteBox.sendKeys("Test");



    }
}

