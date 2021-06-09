import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;


public class examTest {

    @Test
    public void locatorExam() throws InterruptedException {
        //.Telling the system where to find chromedriver.
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        //.Instantiate the driver
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //.Navigate to the URL
        driver.get("https://www.saucedemo.com/");

        //Use cssSelectors
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        //Use Css/Xpath
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();

        //Use the best locators
        driver.findElement(By.cssSelector("#first-name")).sendKeys("first name");
        driver.findElement(By.cssSelector("#last-name")).sendKeys("last name");
        driver.findElement(By.cssSelector("#postal-code")).sendKeys("zip");
        driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();

        //By link text
        driver.findElement(By.id("finish")).click();
        assertTrue(driver.findElement(By.cssSelector("#checkout_complete_container")).isDisplayed());
        Thread.sleep(3000);
        driver.quit();
    }
}
