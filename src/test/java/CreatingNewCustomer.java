import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreatingNewCustomer {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearsDown() {
        driver.quit();
    }

    @Test
    public void adminAreaCustomerCreationTest() {
        driver.get("http://shop.pragmatic.bg/admin/");
        WebElement usernameInputField = driver.findElement(By.id("input-username"));
        usernameInputField.sendKeys("admin");

        WebElement passwordInputField = driver.findElement(By.id("input-password"));
        passwordInputField.sendKeys("parola123!");

        WebElement loginButton = driver.findElement(By.cssSelector(".btn-primary"));
        loginButton.click();

        WebElement customersDropdown = driver.findElement(By.xpath("//a[@href='#collapse5']"));
        customersDropdown.click();

        WebElement customersSubLabel = driver.findElement(By.xpath("//ul[@id='collapse5']/li[1]/a"));
        customersSubLabel.click();

        WebElement addButton = driver.findElement(By.cssSelector("a[data-original-title='Add New']"));
        addButton.click();

        WebElement firstNameInputField = driver.findElement(By.id("input-firstname"));
        firstNameInputField.sendKeys("Pragmatic");

        WebElement lastNameInputField = driver.findElement(By.id("input-lastname"));
        lastNameInputField.sendKeys("Selenium");

        String randomEmailPrefix = RandomStringUtils.randomAlphabetic(7);
        String randomEmail = randomEmailPrefix + "@mail.com";

        WebElement emailInputField = driver.findElement(By.id("input-email"));
        emailInputField.sendKeys(randomEmail);

        WebElement phoneNumberInputField = driver.findElement(By.id("input-telephone"));
        phoneNumberInputField.sendKeys("888888888888");

        WebElement customerPasswordInputField = driver.findElement(By.id("input-password"));
        customerPasswordInputField.sendKeys("parola123!");

        WebElement confirmCustomerPasswordInputField = driver.findElement(By.id("input-confirm"));
        confirmCustomerPasswordInputField.sendKeys("parola123!");

        Select newsletterDropdown = new Select(driver.findElement(By.id("input-newsletter")));
        newsletterDropdown.selectByValue("1");

        WebElement saveButton = driver.findElement(By.cssSelector("button[data-original-title=\"Save\"]"));
        saveButton.click();

        WebElement filterEmailInputField = driver.findElement(By.id("input-email"));
        filterEmailInputField.sendKeys(randomEmail);

        WebElement filterButton = driver.findElement(By.id("button-filter"));
        filterButton.click();

        WebElement filteredEmailTableData = driver.findElement(By.xpath("//*[@class='table table-bordered table-hover']/tbody/tr[1]/td[3]"));
        Assert.assertEquals(filteredEmailTableData.getText(), randomEmail);
    }
}
