import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class HelloSeleniumTest {

    @Test
    public void w3SchoolTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://w3Schools.com/");
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void firstTest() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.findElement(By.tagName("input")).sendKeys("Selenium");
        driver.quit();
    }
    @Test
    public void findingElement() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://address-book-example.herokuapp.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement singIn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("sign-in")));
        singIn.click();
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void findingElements() throws InterruptedException {
        //Set location of chromedriver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        //1.Instantiate the driver
        WebDriver driver = new ChromeDriver();

        //2.Navigate to URL
        driver.get("https://ultimateqa.com/simple-html-elements-for-automation/");

        //3.Ensure browser in correct state when finding element
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement clickMe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("button2")));

        //4.Take action on element
        clickMe.click();

        //5. Quit sesion
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void locatingElements() {
        //Telling the system where to find chromedriver.On Windows you also need to add .exe!
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        //1.Instantiate the driver
        WebDriver driver = new ChromeDriver();

        //2. Navigate to the URL
        driver.get("https://saucedemo.com/");

        //3.Find Element//4.Check the state
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));

        //5.take action//6.record the result
        assertTrue(element.isDisplayed());

        //Quit driver (closes the browser)
        driver.quit();
    }
}
