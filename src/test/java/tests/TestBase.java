package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver wd;

    @BeforeSuite
    public void init(){
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        pause();
        wd.get("https://github.com/");

    }

    @AfterSuite
    public void tearDown(){
        wd.quit();
    }

    public void searchElement(By locator){
        wd.findElement(locator).click();
    }

    public void pause(){
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.sendKeys(text);
        element.submit();
    }

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size() > 0;
    }
    public boolean isProfilePicturesMatches(){
        List<WebElement> elements = wd.findElements(By.className("Box-sc-g0xbh4-0 gZKkEq"));
        int totalSumElements = elements.size();
        List<WebElement> profilePictures = wd.findElements(By.cssSelector("[data-component='Avatar']"));
        int profilePicturesCount = 0;
        for(WebElement img : profilePictures){
            if(img.getAttribute("class").contains("avatar")){
                profilePicturesCount++;
            }
        }
        return profilePicturesCount == totalSumElements;
    }
}
