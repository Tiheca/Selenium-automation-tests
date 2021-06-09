import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;
import java.util.concurrent.TimeUnit;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class IphoneOrder {

    WebDriver driver;

    @BeforeMethod
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void successfulPlacementOfGuestOrderTest() {
        driver.navigate().to("http://shop.pragmatic.bg/index.php?route=product/product&product_id=40");

        WebDriverWait wait = new WebDriverWait(driver, 3);

        WebElement addToCartButton = driver.findElement(By.id("button-cart"));
        addToCartButton.click();

        WebElement cartDropdownButton = driver.findElement(By.xpath("//span[@id='cart-total']/.."));
        cartDropdownButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='dropdown-menu pull-right']//*[@class='fa fa-share']/..")));
        WebElement checkoutButton = driver.findElement(By.xpath("//*[@class='dropdown-menu pull-right']//*[@class='fa fa-share']/.."));
        checkoutButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[value='guest']")));
        WebElement guestCheckoutRadioButton = driver.findElement(By.cssSelector("[value='guest']"));
        guestCheckoutRadioButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-account")));
        WebElement continueButton = driver.findElement(By.id("button-account"));
        continueButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname")));
        WebElement firstNameInputField = driver.findElement(By.id("input-payment-firstname"));
        firstNameInputField.sendKeys("Pragmatic");

        WebElement lastNameInputField = driver.findElement(By.id("input-payment-lastname"));
        lastNameInputField.sendKeys("QAComplete");

        String randomEmail = RandomStringUtils.randomAlphabetic(7) + "@mail.com";

        WebElement emailInputField = driver.findElement(By.id("input-payment-email"));
        emailInputField.sendKeys(randomEmail);

        WebElement phoneNumberInputField = driver.findElement(By.id("input-payment-telephone"));
        phoneNumberInputField.sendKeys("0885948567");

        WebElement addressOneInputField = driver.findElement(By.id("input-payment-address-1"));
        addressOneInputField.sendKeys("Lyulin, Sofia");

        WebElement cityInputField = driver.findElement(By.id("input-payment-city"));
        cityInputField.sendKeys("Sofia");

        WebElement postcodeInputField = driver.findElement(By.id("input-payment-postcode"));
        postcodeInputField.sendKeys("1324");

        Select selectCountryDropdown = new Select(driver.findElement(By.id("input-payment-country")));
        selectCountryDropdown.selectByValue("33");

        Select selectPaymentZoneDropdown = new Select(driver.findElement(By.id("input-payment-zone")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#input-payment-zone > [value='505']")));

        selectPaymentZoneDropdown.selectByValue("491");


        WebElement billingDetailsContinueButton = driver.findElement(By.id("button-guest"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-guest")));
        billingDetailsContinueButton.click();


        WebElement deliveryDetailsButton = driver.findElement(By.cssSelector("#button-shipping-method"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#button-shipping-method")));
        deliveryDetailsButton.click();


        WebElement termsCheckbox = driver.findElement(By.cssSelector("input[type='checkbox'][name='agree']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='checkbox'][name='agree']")));
        termsCheckbox.click();


        WebElement deliveryMethodContinueButton = driver.findElement(By.cssSelector("#button-payment-method"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#button-payment-method")));
        deliveryMethodContinueButton.click();


        WebElement paymentContinueButton = driver.findElement(By.id("button-payment-method"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-payment-method")));
        paymentContinueButton.click();


        WebElement confirmButton = driver.findElement(By.cssSelector("#button-confirm"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#button-confirm")));
        confirmButton.click();


        String actualBodyText = driver.findElement(By.xpath("//h1[contains(text(),'Your order has been placed!')]")).getText();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Your order has been placed!')]")));
        assertEquals("Your order has been placed!", actualBodyText);
        System.out.println("Your order has been placed!");
    }
}


