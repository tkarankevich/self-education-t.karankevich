import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomeTask3_1 {

    private WebDriver driver;
    public static final String BROWSER_NAME = "chromedriver.exe";
    public static final String URL_TUT_BY = "http://www.tut.by";

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", BROWSER_NAME);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL_TUT_BY);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @DataProvider(name = "addon_menu_items")
    public static Object[][] titleMI_resourceN() {
        return new Object[][]{{"42", "42"}};
    }

    @Test(dataProvider = "addon_menu_items")
    public void openItem(String titleMI, String resourceN) {
        WebElement addonMenuItems = driver.findElement(By.xpath("//div[@class = 'topbarmore-c']"));

        if(!addonMenuItems.isDisplayed()) {
            WebElement addonMenu = driver.findElement(By.xpath("//a[@class='topbar-burger' and @data-target-popup='addon-menu-items']"));
            addonMenu.click();
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofSeconds(2))
                    .withMessage("Element not interactable")
                    .until(driver -> addonMenuItems.isDisplayed());
        }

        WebElement menuItem = driver.findElement(By.xpath("//div[@class='topbarmore-c']//a[@class='topbar__link' and @title='" + titleMI + "']"));
        menuItem.click();
        String resourceName = driver.findElement(By.cssSelector("a.name-resource")).getText();
        Assert.assertEquals(resourceName, resourceN);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
