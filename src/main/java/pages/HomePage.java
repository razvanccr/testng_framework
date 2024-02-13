package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {

    private WebDriver driver;


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    By overlayIframe = By.id("overlay-iframe");
    By loginButton = By.className("LoginView");

    By usernameField = By.id("AuthEmail");
    By passwordField = By.id("AuthPassword");

    By logIn = By.xpath("//button[contains(@class,'btn-primary')]");

    By cookieAccept = By.id("onetrust-accept-btn-handler");

    public void acceptCookie(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cookieAccept));

        driver.findElement(cookieAccept).click();

    }

    private void clickLogin(){

        WebElement iframe = driver.findElement(overlayIframe);

        driver.switchTo().frame(iframe);

        driver.findElement(loginButton).click();

        driver.switchTo().defaultContent();

    }

    public void login(String username, String password){

        String originalWindow = driver.getWindowHandle();
        clickLogin();
        //get window
       for(var windows: driver.getWindowHandles()){
           if(!originalWindow.contentEquals(windows)){
               driver.switchTo().window(windows);
           }
       }
       driver.findElement(usernameField).sendKeys(username);
       driver.findElement(passwordField).sendKeys(password);

       driver.findElement(logIn).click();


    }



}
