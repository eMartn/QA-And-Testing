import io.github.bonigarcia.wdm.WebDriverManager;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class ParametrizedGoogleSearch
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
	@Parameters({"Covid-19","2020 Election","California Fires"})
	public void testGoogle(String search)
		{
			driver.get("https://www.google.com");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys(search); // Enters the current parameter into the Google search box.
			searchBox.submit();

			// Finds all links on the first page and places them in a list that is then parsed to print each one to console.
			List<WebElement> links = driver.findElements(By.tagName("a"));
				for (WebElement link : links)
				{
					System.out.println(link.getAttribute("href"));
				}
		
		}
	}
