import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

/**
 * Little program that showcases how to automatically retrieve links from web pages using selenium.
 */
public class HelloSelenium
    {

        private static WebDriver driver;

        private static ChromeOptions options;

        @BeforeClass
        public static void setupClass()
        {
            WebDriverManager.chromedriver().setup();
            options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("headless");
        }

        @Before
        public void setupTest()
        {
            driver = new ChromeDriver(options);
        }

        @After
        public void teardown()
        {
            driver.quit();
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
