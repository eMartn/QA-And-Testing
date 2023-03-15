import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class HelloSelenium
    {

        private static WebDriver driver;

        @BeforeClass
        public static void setUp()
        {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            //driver = new ChromeDriver();
            driver = new FirefoxDriver();
        }

        @Test
        public void testGoogle()
        {
            driver.get("https://www.google.com");
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("GGC");
            searchBox.submit();
            Assert.assertTrue(driver.getTitle().contains("GGC"));

        }

        @Test
        public void testRedditLinks()
        {
            driver.get("https://www.reddit.com");
            List<WebElement> links = driver.findElements(By.tagName("a"));
            for (int i = 0; i < links.size(); i++)
            {
                System.out.println(links.get(i).getAttribute("href"));
            }
            System.out.println(links.size());
        }

    }
