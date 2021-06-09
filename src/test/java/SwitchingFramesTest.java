import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SwitchingFramesTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearsDown() {
        driver.quit();
    }

    @Test
    public void frames() {
        //navigate to the URL
        driver.navigate().to("https://the-internet.herokuapp.com/nested_frames");
        //"frame-top" is the top level. default view of the frames
        WebElement defaultFrame = driver.findElement(By.name("frame-top"));
        //we can switch frames by index.0 is first, 1 is second...
        driver.switchTo().frame(1);
        //assert that we switched to the bottom frame.
        //if we didn`t, NoSuchElementExeption would be thrown
        assertEquals("BOTTOM", driver.findElement(By.tagName("body")).getText());

        //switchTo().parentFrame() moves focus to the parent frame
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        //assert that we switched to the left frame
        assertEquals("LEFT", driver.findElement(By.tagName("body")).getText());

        //switchTo().defaultContent() will move to the top most/default frame
        driver.switchTo().defaultContent();
        //Get the element`s height with name frame-top. If we weren`t focused on this
        //element , findElement would throw a NoSuchElementExeption
        assertTrue(driver.findElement(By.name("frame-top")).getSize().height > 0);
    }
}
