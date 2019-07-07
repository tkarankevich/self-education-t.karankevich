import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomeTask2 {

    private WebDriver driver;
    public static final String URL = "https://www.tut.by/";

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @DataProvider(name = "Authentication")
    public static Object[][] credentials() {
        return new Object[][]{{"a1qatest","Qwerty123","Tester Testov"}};
    }

   @Test(dataProvider = "Authentication")
   public void verifyUserName(String uLogin, String uPassword, String userName){
       driver.findElement(By.xpath("//a[@data-target-popup='authorize-form']")).click();
       driver.findElement(By.name("login")).sendKeys(uLogin);
       driver.findElement(By.name("password")).sendKeys(uPassword);
       driver.findElement(By.cssSelector("input.button.auth__enter")).click();
       String username = driver.findElement(By.cssSelector("span.uname")).getText();

       Assert.assertEquals(username, userName);
   }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
