import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeTask3_3 {

    private WebDriver driver;
    public static final String BROWSER_NAME = "chromedriver.exe";
    public static final String URL_DYNAMIC_LOADING = "http://the-internet.herokuapp.com/dynamic_loading/1 ";

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", BROWSER_NAME);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL_DYNAMIC_LOADING);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void checkHelloWord() {

       WebElement text = driver.findElement(By.xpath("//div[@id='finish']"));
       //Assert.assertTrue(text.size() ==1);

       if(!text.isDisplayed()) {
            WebElement button = driver.findElement(By.xpath("//div[@id='start']//button"));
            button.click();
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(200))
                    .withMessage("NoSuchElementException")
                    .until(driver -> driver.findElement(By.xpath("//div[@id='finish']//button")).isDisplayed());
        }
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']")).getText(), "Hello World!");

    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}