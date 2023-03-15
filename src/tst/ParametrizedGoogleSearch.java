import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class ParametrizedGoogleSearch
	{
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp()
		{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		//driver = new ChromeDriver();
		}
	
	@Test
	@Parameters({"Covid-19","2020 Election","California Fires"})
	public void testGoogle(String search)
		{
			driver.get("https://www.google.com");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys(search);
			searchBox.submit();
		
			List<WebElement> links = driver.findElements(By.tagName("a"));
				for (WebElement link : links)
				{
					System.out.println(link.getAttribute("href"));
				}
		
		}
	}
