import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.Set;
import java.util.concurrent.TimeUnit;


import static org.testng.Assert.assertEquals;

public class WebDriverMethodsTest {

    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void tearsDown() {
        driver.quit();
    }

    @Test
    public void windowFrames() {
        driver.navigate().to("https://the-internet.herokuapp.com/windows");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://the-internet.herokuapp.com/windows/new')");

        String originalWindow = driver.getWindowHandle();
        Set handles = driver.getWindowHandles();
        handles.remove(originalWindow);

        String nextWindow = String.valueOf(handles.iterator().next());

        driver.switchTo().window(nextWindow);
        assertEquals("New Window", driver.getTitle());

        driver.close();
        driver.switchTo().window(originalWindow);

        assertEquals("The Internet", driver.getTitle());
    }

    @Test
    public void alerts() {
        driver.navigate().to("https://the-internet.herokuapp.com/javascript_alerts");

        WebElement alertButton = driver.findElement(By.xpath("//*[contains(normalize-space(text()),'Click for JS Alert')]"));
        alertButton.click();
        driver.switchTo().alert().dismiss();

        WebElement confirmButton = driver.findElement(By.xpath("//*[contains(normalize-space(text()),'Click for JS Confirm')]"));
        confirmButton.click();
        driver.switchTo().alert().accept();

        WebElement promptButton = driver.findElement(By.xpath("//*[contains(normalize-space(text()),'Click for JS Prompt')]"));
        promptButton.click();

        Alert inputAlert = driver.switchTo().alert();
        inputAlert.sendKeys("Look Mom! I can automate alerts :)");
        inputAlert.accept();
    }
}

