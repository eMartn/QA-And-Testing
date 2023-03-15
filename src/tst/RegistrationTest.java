import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RegistrationTest
    {
        private final String LINK = "https://ihgrewardsclubdining.rewardsnetwork.com/Join";
        private static WebDriver driver;

        @BeforeClass
        public static void setUp()
        {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
           // driver = new ChromeDriver();
        }

        @Test
        public void testForm()
        {
            driver.get(LINK);

            // Filling in information fields for sign-up.
            WebElement fnBox = driver.findElement(By.name("firstName"));
            fnBox.sendKeys("Roger");
            WebElement lnBox = driver.findElement(By.name("lastName"));
            lnBox.sendKeys("Rabbit");
            WebElement zipBox = driver.findElement(By.name("zipCode"));
            zipBox.sendKeys("44444");
            WebElement ihgBox = driver.findElement(By.name("partnerProgramNumber"));
            ihgBox.sendKeys("12345");
            WebElement email = driver.findElement(By.name("email"));
            email.sendKeys("rogerf1234567@ggc.edu");
            WebElement password = driver.findElement(By.name("password"));
            password.sendKeys("Bugsnax1");
            WebElement passwordConfirm = driver.findElement(By.name("passwordConfirm"));
            passwordConfirm.sendKeys("Bugsnax1");

            // Unclicking the sign-up for newsletter checkbox.
            WebElement newsletter = driver.findElement(By.cssSelector("body > div.body-wrap > div.content-wrap > div > rn-enrollment-view > div > div > div:nth-child(2) > rn-enrollment-main-step-user-info > form-builder > form > div:nth-child(12) > rn-formbuilder-checkbox-single > label > input"));
            newsletter.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

            // Clicking out of the annoying pop-up asking if we're sure (Of course we are).
            WebElement noSu = driver.findElement(By.cssSelector("#email-opt-out-confirmation-popup > div:nth-child(2) > div > div > div.actions.ng-scope > a"));
            noSu.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

            // Accepting terms of service and privacy policy by clicking the checkbox for it.
            WebElement tos = driver.findElement(By.cssSelector("body > div.body-wrap > div.content-wrap > div > rn-enrollment-view > div > div > div:nth-child(2) > rn-enrollment-main-step-user-info > form-builder > form > div:nth-child(13) > rn-formbuilder-checkbox-single > label > input"));
            tos.click();

            // Clicking submit registration.
            WebElement submitButton = driver.findElement(By.cssSelector("body > div.body-wrap > div.content-wrap > div > rn-enrollment-view > div > div > div:nth-child(2) > rn-enrollment-main-step-user-info > form-builder > form > div.actions.ng-scope > button"));
            submitButton.click();

            // Getting error strings declared.
            WebElement invalidFormatError = driver.findElement(By.cssSelector("body > div.body-wrap > div.content-wrap > div > rn-enrollment-view > div > div > div:nth-child(2) > rn-enrollment-main-step-user-info > form-builder > form > div:nth-child(5) > rn-formbuilder-text-input > rn-formbuilder-formfield-error > div > div > div.error__custom.ng-binding.ng-scope"));
            String invalidFormatString = invalidFormatError.getText();
            WebElement errorText = driver.findElement(By.cssSelector("body > div.body-wrap > div.content-wrap > div > rn-enrollment-view > div > div > div:nth-child(2) > rn-enrollment-main-step-user-info > form-builder > form > div.form-error-msg.ng-scope"));
            String errorTextString = errorText.getText();

            // Checks to see that the error message that happens when IHG number is entered incorrectly is on the page.
            Assert.assertEquals("Invalid format for IHG® Rewards Club number.", invalidFormatString);

            // Checks to see that there was an error upon registering, verifying that we are still on the register page.
            // This error message is referring to the IGH error above.
            Assert.assertEquals("Please check the errors above before continuing.", errorTextString);

        }
    }
