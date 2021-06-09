import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class PragmaticHomeworkTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void tearsDown() {
        driver.quit();
    }

    @Test
    public void homeWork() throws InterruptedException {
        driver.navigate().to("http://shop.pragmatic.bg/admin/");

        WebElement userNameField = driver.findElement(By.cssSelector(" #input-username"));
        userNameField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.cssSelector("#input-password"));
        passwordField.sendKeys("parola123!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        assertEquals("Dashboard", driver.getTitle());
    }

    @Test
    public void Orders() throws InterruptedException {
        driver.navigate().to("http://shop.pragmatic.bg/admin/");

        WebElement userNameField = driver.findElement(By.cssSelector(" #input-username"));
        userNameField.sendKeys("admin");
        WebElement passwordField = driver.findElement(By.cssSelector("#input-password"));
        passwordField.sendKeys("parola123!");
        driver.findElement(By.cssSelector(".btn.btn-primary")).click();

        assertEquals("Dashboard", driver.getTitle());

        driver.findElement(By.cssSelector("#menu-sale")).click();

        driver.findElement(By.xpath("//a[contains(text(),'Orders')]")).click();
        assertEquals("Orders", driver.getTitle());

        WebElement dropDownMenu = driver.findElement(By.id("input-order-status"));
        Select orderStatus = new Select(dropDownMenu);

        assertFalse(orderStatus.isMultiple());
        assertEquals(orderStatus.getOptions().size(), 16);
        List<String> exp_options = Arrays.asList("",
                "Missing Orders",
                "Canceled",
                "Canceled Reversal",
                "Chargeback",
                "Complete",
                "Denied",
                "Expired",
                "Failed",
                "Pending",
                "Processed",
                "Processing",
                "Refunded",
                "Reversed",
                "Shipped",
                "Voided");
        List<String> act_options = new ArrayList<>();
        for (WebElement option : orderStatus.getOptions()) act_options.add(option.getText());
        assertArrayEquals(act_options.toArray(), exp_options.toArray());
    }

    @Test

    public void yourDreamCar() {
        driver.navigate().to("http://pragmatic.bg/automation/lecture13/Config.html");

        Actions action = new Actions(driver);

        WebElement element1 = driver.findElement(By.name("airbags"));
        WebElement element2 = driver.findElement(By.name("parksensor"));

        action.click(element1).perform();
        action.click(element2).perform();

        assertTrue(element1.isSelected());
        assertTrue(element2.isSelected());
    }

    @Test
    public void takeAwayRestaurant() {
        driver.navigate().to("https://www.takeaway.com/bg");

        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 3);

        WebElement searchField = driver.findElement(By.id("imysearchstring"));
        searchField.clear();
        action.sendKeys(searchField, "улица Симеонов Век 2").perform();

        WebElement proposedAddress = driver.findElement(By.xpath("//*[@data-name='улица „Симеонов век“ 2, София']"));
        action.click(proposedAddress).perform();

        WebElement submitButton = driver.findElement(By.id("submit_deliveryarea"));
        action.click(submitButton).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-qa='restaurant-list-search-element']")));
        WebElement searchFieldRestaurants = driver.findElement(By.xpath("//*[@data-qa='restaurant-list-search-element']"));
        action.sendKeys(searchFieldRestaurants, "Raffy").perform();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='_63-j _1rimQ _3zJ3o'][data-qa='search-result-card-title']")));
        WebElement suggestedRestaurant = driver.findElement(By.cssSelector("[class='_63-j _1rimQ _3zJ3o'][data-qa='search-result-card-title']"));
        action.click(suggestedRestaurant).perform();
        assertEquals("Raffy Bar & Gelato|Рафи Бар & Джелато бул. Цариградско шосе - Храна за вкъщи | Takeaway.com",driver.getTitle());
    }
}
