import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class ActionsQuizTest {
    WebDriver driver;
    WebElement element;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void actionQuiz() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        element = driver.findElement(By.cssSelector("#dropdown"));
        element.click();
        WebElement option1 = element.findElement(By.cssSelector("option[value='1']"));
        WebElement option2 = element.findElement(By.cssSelector("option[value='2']"));
        option1.click();
        assertTrue(option1.isSelected());
        assertFalse(option2.isSelected());

    }

    @Test
    public void hover() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        element = driver.findElement(By.className("figure"));
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();

        element = driver.findElement(By.xpath("//*[contains(text(), 'name: user1')]"));
        assertTrue(element.isDisplayed(), "user1 should appear because we hovered over that image");
    }

    @Test
    public void rightClick() {
        driver.navigate().to("https://the-internet.herokuapp.com/context_menu");

        element = driver.findElement(By.cssSelector("#hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        driver.switchTo().alert().accept();
    }

    @Test
    public void keyPresses() {
        driver.navigate().to("https://the-internet.herokuapp.com/key_presses");

        element = driver.findElement(By.id("target"));
        element.click();

        Actions actions = new Actions(driver);
        actions.
                sendKeys(Keys.ARROW_RIGHT).
                pause(1000).
                perform();
        element = driver.findElement(By.id("result"));
        assertTrue(element.isDisplayed(), "You entered: RIGHT");
    }

    @Test
    public void getCssValue() {
        driver.navigate().to("https://ultimateqa.com/simple-html-elements-for-automation");
        element = driver.findElement(By.linkText("Clickable Icon"));
        String link = element.getAttribute("href");
        assertEquals("https://ultimateqa.com/link-success/", link);
    }
}
