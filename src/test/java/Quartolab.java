import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Quartolab {

    public static WebDriver driver;
    static  String BaseURL = "https://quartolab.com/";

    @BeforeTest
    public static void WebSetup(){
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, System.getProperty("user.dir") + "/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get((BaseURL));
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //Assert.assertTrue(driver.getTitle().contains("Quarto"));
    }
    @Test(priority = 0)
    public static void Feature() throws Exception {
        driver.findElement(By.xpath("//a[contains(text(),'features')]")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.getPageSource().contains("Employee Recognition"));
    }
    @Test(priority = 1)
     public static void FailedLogin() throws Exception {
         driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
         driver.findElement(By.xpath("//input[@id='email']")).sendKeys("zihad@gmail.com");
         Thread.sleep(1000);
         driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
         Assert.assertTrue(driver.getPageSource().contains("Couldn't find your quarto account."));
     }
    @Test(priority = 2)
    public static void SuccessLogin() throws Exception {
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("tlive5@yopmail.com");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[contains(text(),'Next')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='password']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("t12345678");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html[1]/body[1]/root[1]/login[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[3]/div[1]/button[1]")).click();
        Assert.assertTrue(driver.getPageSource().contains("Kudos"));
    }
    @AfterTest
    public static void TestClosure(){
        driver.quit();
    }

}
