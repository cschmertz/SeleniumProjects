package SmartBear;

import Utilities.WebDriverFactory;
import Utilities.util;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Order {

    WebDriver driver;
    String browserType = "chrome";
    String URL = "http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx";

    //Order page content
    String productXpath="//select";
    String quantity="//input[@id='ctl00_MainContent_fmwOrder_txtQuantity']";
    String unitPrice="//input[@id='ctl00_MainContent_fmwOrder_txtUnitPrice']";
    String discount="//input[@id='ctl00_MainContent_fmwOrder_txtDiscount']";
    String total="//input[@id='ctl00_MainContent_fmwOrder_txtTotal']";
    String calculate="//*[@type='submit']";

    //Credit cards
    String visa ="//input[@id='ctl00_MainContent_fmwOrder_cardList_0']";
    String masterCard ="//input[@id='ctl00_MainContent_fmwOrder_cardList_1']";
    String amex ="//input[@id='ctl00_MainContent_fmwOrder_cardList_2']";

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void testLogin(){
        util.login(driver);
        util.Wait(3);
        String actual = driver.getTitle();
        String expected = "Web Orders";
        Assert.assertEquals(actual,expected,"page title does not match");
    }

    @Test
    public void process_order() {
        util.login(driver);
        util.Wait(1);
        driver.findElement(By.xpath("//li[3]/a")).click();

        //Product Selection
        WebElement product = driver.findElement(By.xpath("//select"));
        Select select = new Select(product);
        select.selectByValue("ScreenSaver");

        //Quantity Box
        WebElement quantityBox = driver.findElement(By.xpath(quantity));
        util.Wait(1);
        quantityBox.sendKeys(Keys.BACK_SPACE);
        util.Wait(1);
        quantityBox.sendKeys("5");

        //Calculate
        driver.findElement(By.xpath(calculate)).click();
        String actual = driver.findElement(By.xpath(total)).getAttribute("value");
        String expected = "100";
        Assert.assertEquals(actual, expected, "total does not match");

        //Customer (Faker)
        Faker faker = new Faker();
        WebElement customerName = driver.findElement(By.xpath("//input[@name='ctl00$MainContent$fmwOrder$txtName']"));
        customerName.sendKeys(faker.name().fullName());

        //Street (Faker)
        WebElement street = driver.findElement(By.xpath("//input[@name='ctl00$MainContent$fmwOrder$TextBox2']"));
        street.sendKeys(faker.address().streetAddress());

        //City (Faker)
        WebElement city = driver.findElement(By.xpath("//input[@name='ctl00$MainContent$fmwOrder$TextBox3']"));
        city.sendKeys(faker.address().city());

        //State (Faker)
        WebElement state = driver.findElement(By.xpath("//input[@name='ctl00$MainContent$fmwOrder$TextBox4']"));
        state.sendKeys(faker.address().state());

        //Zip Code (Faker)
        WebElement zip = driver.findElement(By.xpath("//input[@name='ctl00$MainContent$fmwOrder$TextBox5']"));
        zip.sendKeys(faker.address().zipCode().replaceAll("-", ""));

        //Radio Button Selection (Randomized)
        Random rand = new Random();
        int[] arr = {1, 2, 3};
        int creditCardRandomizer = rand.nextInt(arr.length);
        String creditCardChoice = "";
        if (creditCardRandomizer == 0) {
            creditCardChoice = amex;
        } else if (creditCardRandomizer == 1) {
            creditCardChoice = visa;
        } else {
            creditCardChoice = masterCard;
        }
        WebElement radioChoice = driver.findElement(By.xpath(String.valueOf(creditCardChoice)));
        radioChoice.click();

        //Credit Card Number (Randomized)
        WebElement cardNumber= driver.findElement(By.xpath("//input[@name='ctl00$MainContent$fmwOrder$TextBox6']"));
        cardNumber.sendKeys(faker.finance().creditCard().replaceAll("-",""));

        //Credit Card Expiration Date (Randomized)
        WebElement expirationDate = driver.findElement(By.xpath("//input[@name='ctl00$MainContent$fmwOrder$TextBox1']"));
        Random rand2 = new Random();
        int[] arr2 = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        int month = rand2.nextInt(arr2.length+1);
        Random rand3 = new Random();
        int[] arr3 = {1,2,3,4,5,6,7};
        int year = rand3.nextInt(arr3.length)+20;
        if(month <= 9){
            expirationDate.sendKeys(  "0"+month + "/" + year);
        }else
            expirationDate.sendKeys(  month + "/" + year);

        //Process Order
        WebElement processButton = driver.findElement(By.xpath("//a[@id='ctl00_MainContent_fmwOrder_InsertButton']"));
        processButton.click();
    }

    @AfterMethod
    public void tearDown(){
        util.Wait(5);
        driver.quit();
    }

}
