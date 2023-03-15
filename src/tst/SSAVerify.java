import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.Select;

public class SSAVerify
	{
	
		public static final String SSA_ADDRESS = "https://www.ssa.gov/OACT/population/longevity.html";
		public static WebDriver driver;
		private static ChromeOptions options;

		@BeforeClass
		public static void setupClass()
		{
			WebDriverManager.chromedriver().setup();
			options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
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
		public void testFemaleLifeExpectancy()
			{
				driver.get(SSA_ADDRESS);

				Select selectGender = new Select(driver.findElement(By.cssSelector("#sex")));
				selectGender.selectByVisibleText("Female");

				Select selectMonth = new Select(driver.findElement(By.cssSelector("#monthofbirth")));
				selectMonth.selectByVisibleText("January");

				Select selectDay = new Select(driver.findElement(By.cssSelector("#dayofbirth")));
				selectDay.selectByVisibleText("01");

				Select selectYear = new Select(driver.findElement(By.cssSelector("#yearofbirth")));
				selectYear.selectByVisibleText("1996");
				
				WebElement submitButton = driver.findElement(By.cssSelector("input.fs1"));
				submitButton.click();
				
				WebElement ageReached = driver.findElement(By.cssSelector(".pa3 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1)"));
				int age1 = Integer.parseInt(ageReached.getText());
			
				Assert.assertEquals(62, age1);
				
				WebElement lifeExpectancy = driver.findElement(By.cssSelector(".pa3 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3)"));
				Double age2 = Double.parseDouble(lifeExpectancy.getText());
				
				Assert.assertEquals(88.4, age2, 0.1);
			}
	}
