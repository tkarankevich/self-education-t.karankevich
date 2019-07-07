import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomeTask3_2 {

    private WebDriver driver;
    public static final String BROWSER_NAME = "chromedriver.exe";
    public static final String URL_DYNAMIC_CONTROLS = "http://the-internet.herokuapp.com/dynamic_controls";

    @BeforeClass
    public void setup() {

        System.setProperty("webdriver.chrome.driver", BROWSER_NAME);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL_DYNAMIC_CONTROLS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void removeCheckBox() {

        WebElement buttonRemove = driver.findElement(By.xpath("//form[@id='checkbox-example']//button"));
        buttonRemove.click();
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(200))
                .withMessage("NoSuchElementException")
                .until(driver -> driver.findElement(By.xpath("//form[@id='checkbox-example']//p")).isDisplayed());
        Assert.assertTrue(driver.findElements(By.xpath("//form[@id='checkbox-example']//input[@type='checkbox']")).isEmpty());
    }

    @Test(dependsOnMethods="removeCheckBox")
    public void addCheckBox(){
        WebElement button = driver.findElement(By.xpath("//form[@id='checkbox-example']//button"));
        button.click();

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(200))
                .withMessage("NoSuchElementException")
                .until(driver -> driver.findElement(By.xpath("//form[@id='checkbox-example']//input[@type='checkbox']")).isDisplayed());
        Assert.assertFalse(driver.findElements(By.xpath("//form[@id='checkbox-example']//input[@type='checkbox']")).isEmpty());
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

}
