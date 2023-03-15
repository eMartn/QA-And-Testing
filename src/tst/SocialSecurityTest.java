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

import java.util.Calendar;

@RunWith(JUnitParamsRunner.class)
public class SocialSecurityTest
    {

        public static final String SSA_ADDRESS = "https://www.ssa.gov/OACT/quickcalc/";
        public static WebDriver driver;
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
        @Parameters({"1,1,1990,55000,2044.0",
                     "1,1,1980,65000,2255.0",
                     "1,1,1970,75000,2357.0"})
        public void testSocialSecurity(int month, int day, int year, int salary, double benefit)
        {
            double monthlyBenefits = getBenefitAmountFromCalculator(month, day, year, salary, benefit);
            Assert.assertEquals(benefit, monthlyBenefits, 0);
        }

        /**
           Maximum taxable earnings: $137,700
           FRA (67 years old): $3,113 per month
           The maximum amount of benefit goes slightly above $3,113 ($3,279).
           Doesn't matter if you earn the maximum amount every year from the beginning.
           It is important to reach the max at some point in your career. */
        @Test
        @Parameters({"1, 1, 1960, 137700, 3113",
                     "1, 1, 1970, 110000, 3113",
                     "1, 1, 1970, 120000, 3113"})
        public void taxMaxBenefitsAmount(int month, int day, int year, int salary, double benefit)
        {
            double monthlyBenefits = getBenefitAmountFromCalculator(month, day, year, salary, benefit);
            System.out.println("Monthly payout: " + monthlyBenefits);
            //Assert.assertEquals(benefit, monthlyBenefits, 0);

            WebElement earningButton = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(3) > td > form > input[type=submit]:nth-child(10)"));
            earningButton.click();

            //year user begins working is 17 years old according to SSN
            int startYear = year + 17;
            //Social security calculator only goes up to current year
            int endYear = Calendar.getInstance().get(Calendar.YEAR);

            for (int i = startYear; i < endYear; i++)
            {
                WebElement inputTextBox = driver.findElement(By.name("" + i));
                inputTextBox.clear();
                inputTextBox.sendKeys(salary + "");
            }

            WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(1) > td:nth-child(1) > form > input[type=submit]:nth-child(7)"));
            submit.click();


            WebElement benefitAmount = driver.findElement(By.cssSelector("#est_fra"));

            // Parses text into double.
            String ba = benefitAmount.getText().replace("$", "").replace(",", "");
            System.out.println(ba);
        }


        // 10 year difference
        // When you start 10 years later, you earn $390, or 18.106% more ( 1764 vs 2154 ) on your benefits.
        // 1/1/1970 50000 - (1,202, 1,764, 2,233)
        @Test
        @Parameters({"1, 1, 1970, 50000, 3113"})
        public void testAgeDiffBenefitAmount(int month, int day, int year, int salary, double benefit)
        {
            double monthlyBenefits = getBenefitAmountFromCalculator(month, day, year, salary, benefit);
            System.out.println("Monthly payout: " + monthlyBenefits);
            //Assert.assertEquals(benefit, monthlyBenefits, 0);

            WebElement earningButton = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(3) > td > form > input[type=submit]:nth-child(10)"));
            earningButton.click();

            int startYear = 17 + year;
            int endYear = Calendar.getInstance().get(Calendar.YEAR);

            for (int i = startYear; i < 13 + startYear; i++)
            {
                WebElement inputTextBox = driver.findElement(By.name("" + i));
                inputTextBox.clear();
                inputTextBox.sendKeys("0");
            }

            for (int i = 13 + startYear; i < endYear; i++)
            {
                WebElement inputTextBox = driver.findElement(By.name("" + i));
                inputTextBox.clear();
                inputTextBox.sendKeys("" + salary);
            }
            WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(3) > tbody > tr:nth-child(1) > td:nth-child(1) > form > input[type=submit]:nth-child(7)"));
            submit.click();

            WebElement benefitAmount = driver.findElement(By.cssSelector("#est_fra"));
            //parse text into double
            String ba = benefitAmount.getText().replace("$", "").replace(",", "");
            System.out.println(ba);
        }


        private double getBenefitAmountFromCalculator(int month, int day, int year, int salary, double benefit)
        {

            driver.get(SSA_ADDRESS);
            //System.out.println(month + " " + day + " " + year + " " + salary + " " + benefit);

            // This block of code enters the month
            WebElement monthInput = driver.findElement(By.cssSelector("#month"));
            //clear default value
            monthInput.clear();
            monthInput.sendKeys(month + "");

            // This block of code enters the day
            WebElement dayInput = driver.findElement(By.cssSelector("#day"));
            //clear default value
            dayInput.clear();
            dayInput.sendKeys(day + "");

            // This block of code enters the year
            WebElement yearInput = driver.findElement(By.cssSelector("#year"));
            //clear default value
            yearInput.clear();
            yearInput.sendKeys(year + "");

            // This block of code enters the earnings in the current year
            WebElement salaryInput = driver.findElement(By.cssSelector("#earnings"));
            //clear default value
            salaryInput.clear();
            salaryInput.sendKeys(salary + "");

            // Clicks the submit button to go to the next page
            WebElement submit = driver.findElement(By.cssSelector("body > table:nth-child(6) > tbody > tr:nth-child(2) > td:nth-child(2) > form > table > tbody > tr:nth-child(5) > td > input[type=submit]"));
            submit.click();

            WebElement benefitAmount = driver.findElement(By.cssSelector("#est_fra"));

            //parse text into double
            String ba = benefitAmount.getText().replace("$", "").replace(",", "");
            double monthlyBenefits = Double.parseDouble(ba);


            return  monthlyBenefits;
        }


    }
