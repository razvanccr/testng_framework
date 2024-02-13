package pages;


import com.google.common.io.Files;
import config.PropertiesLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class TestBase {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeClass
    public void oneTimeSetUp(){


        Properties properties = new PropertiesLoader().loadProperties("prod.properties");

        System.out.println(properties.getProperty("username"));


        WebDriverManager.chromedriver().setup();


    }

    @BeforeMethod
    public void setUp(){

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.toJson();
        driver = new ChromeDriver();
        homePage = new HomePage(driver);

        driver.get("https://ubisoft.com");

        homePage.acceptCookie();

        Logs logs = driver.manage().logs();




    }


    @Test
    public void run2() {

        homePage.login("razvan", "password");


        Set<String> mySet = new HashSet<>(List.of("123", "123", "3123"));

        // var something = driver.findElements(By.ByClassName);

        System.out.println("it ran");

        Assert.assertTrue(true);


    }

    @Test
    public void run(){

        homePage.login("razvan","password");


        Set<String > mySet= new HashSet<>(List.of("123","123","3123"));

        // var something = driver.findElements(By.ByClassName);

        System.out.println("it ran");

        Assert.assertTrue(false);


        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.className("aa"))).build();

        actions.perform();
    }

    @AfterMethod
    public void TearDown(ITestResult result) throws IOException {

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);


        Files.copy(file,new File("as"));


    }

    @AfterClass
    public void oneTimeTearDown(){
        driver.quit();
    }

}
