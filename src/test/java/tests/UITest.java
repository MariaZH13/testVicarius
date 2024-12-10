package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UITest extends TestBase{
    @Test
    public void searchingRepository(){
        searchElement(By.cssSelector("[data-target='qbsearch-input.inputButtonText']"));
        type(By.id("query-builder-test"),"SeleniumHQ");
        Assert.assertTrue(isElementPresent(By.xpath("//a[contains(text(), 'SeleniumHQ')]")),
                "The expected repository isn't find");
        System.out.println("You have found the necessary repository!");

        boolean matches = isProfilePicturesMatches();
        pause();
        Assert.assertTrue(matches,"The number of profile pictures in the results" +
                " doesn't match the total results");
        System.out.println("The number of profile pictures in the results " +
                " matches the total results!");

    }

}
