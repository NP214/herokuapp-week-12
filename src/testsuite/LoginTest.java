package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login ";

    @Before
    public void openBrowser(){
        setUpBrowser(baseUrl);}


    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        // Enter username
        sendTextToElement((By.xpath("//input[@type='text']")),"tomsmith");
        // Enter password
       sendTextToElement((By.id("password")),"SuperSecretPassword!");
        // click on login button
       clickOnElement(By.xpath("//button[@type='submit']"));
        // verify text "Secure Area"
       verifyTextWithAssert("Secure Area",((By.xpath("//h2[contains(text(),' Secure Area')]"))),"Error message is not visible");
    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        // Enter invalid username
        sendTextToElement((By.xpath("//input[@type='text']")), "tomsmith1");
        // Enter valid password
        sendTextToElement((By.id("password")), "SuperSecretPassword!");
        // click on login button
        clickOnElement(By.xpath("//button[@type='submit']"));
        // verify text "Secure Area"
        String expectedText = "Your username is invalid!";
        String actualText = getTextFromElement(By.xpath("//div[@class='flash error'] ")).substring(0, 25);
        Assert.assertEquals("Error message not visible", expectedText, actualText);
    }
    @Test
    public void verifyThePasswordErrorMessage() {
        // Enter invalid username
        sendTextToElement((By.xpath("//input[@type='text']")),"tomsmith");
        // Enter valid password
        sendTextToElement((By.id("password")),"SuperSecretPassword");
        // click on login button
        clickOnElement(By.xpath("//button[@type='submit']"));
        // verify text "Secure Area"
        String expectedText = "Your password is invalid!";
        String actualText = getTextFromElement(By.xpath("//div[@class='flash error'] ")).substring(0,25);
        Assert.assertEquals("Error message not visible",expectedText, actualText);

    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}
