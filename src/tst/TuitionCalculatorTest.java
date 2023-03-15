import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class TuitionCalculatorTest
    {


        private static WebDriver driver;
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

        private final String USNEWS_URL = "https://www.usnews.com/best-colleges/georgia-gwinnett-college-41429";
        private final String GGC_TUITION_CALC = "https://www.ggc.edu/admissions/tuition-and-financial-aid-calculators/index.html";
        private static final String GGC_BANNER = "https://ggc.gabest.usg.edu/pls/B400/twbkwbis.P_WWWLogin";

        @Test
        public void inState()
        {
            // Selects the proper choices to calculate in-state tuition using GGC tuition calculator
            driver.get(GGC_TUITION_CALC);
            WebElement inOrOut = driver.findElement(By.cssSelector("#inorout1"));

            inOrOut.click();

            Select creditHours = new Select(driver.findElement(By.cssSelector("#creditHOURS")));
            creditHours.selectByIndex(0);

            // Getting semester in-state tuition from GGC tuition calculator
            WebElement totalCost = driver.findElement(By.cssSelector("#totalcost"));
            String costString = totalCost.getAttribute("value");
            System.out.println(costString);
            double tCost = Double.parseDouble(costString.replace("$", ""));
            System.out.println(tCost);

            // Getting yearly in-state tuition from USNews
            driver.get(USNEWS_URL);
            WebElement usCost = driver.findElement(By.cssSelector("#app > div:nth-child(3) > div.Content-sc-837ada-0.kkKJUL.content > div > div.Cell-sc-1abjmm4-0.idEUgs.mb0 > div > div > div:nth-child(10) > div:nth-child(1) > div:nth-child(2) > p.Paragraph-sc-1iyax29-0.kGlRjY"));
            String usCostString = usCost.getText();
            System.out.println(usCostString);
            double uCost = Double.parseDouble(usCostString.replace("$", "").replace(",", ""));
            System.out.println(uCost);

            // Testing to see if the in-state tuition gotten from the GGC calculator more or less matches the one from USNews.
            // The calculation multiplies the GGC tuition by 2 because it is the semester cost as opposed to USNews' yearly cost.
            Assert.assertEquals(uCost, 2 * tCost, 10);

        }

        @Test
        public void outOfState()
        {
            // Selects the proper choices to calculate out-of-state tuition using GGC tuition calculator
            driver.get(GGC_TUITION_CALC);
            WebElement inOrOut = driver.findElement(By.cssSelector("#inorout0"));
            inOrOut.click();
            //Select creditHours = new Select(driver.findElement(By.cssSelector("#creditHOURS")));
            //creditHours.selectByIndex(0);

            // Getting semester out-of-state tuition from GGC tuition calculator
            WebElement totalCost = driver.findElement(By.cssSelector("#totalcost"));
            String costString = totalCost.getAttribute("value");
            System.out.println(costString);
            double tCost = Double.parseDouble(costString.replace("$", ""));
            System.out.println(tCost);

            // Getting yearly out-of-state tuition from USNews
            driver.get(USNEWS_URL);
            WebElement usCost = driver.findElement(By.cssSelector("#app > div:nth-child(3) > div.Content-sc-837ada-0.kkKJUL.content > div > div.Cell-sc-1abjmm4-0.idEUgs.mb0 > div > div > div:nth-child(10) > div:nth-child(1) > div:nth-child(1) > p.Paragraph-sc-1iyax29-0.kGlRjY"));
            String usCostString = usCost.getText();
            System.out.println(usCostString);
            double uCost = Double.parseDouble(usCostString.replace("$", "").replace(",", ""));
            System.out.println(uCost);

            // Testing to see if the out-of-state tuition gotten from the GGC calculator more or less matches the one from USNews.
            // The calculation multiplies the GGC tuition by 2 because it is the semester cost as opposed to USNews' yearly cost.
            Assert.assertEquals(uCost, 2 * tCost, 10);
        }

        @Test
        public void billTest()
        {
            // Selects the proper choices to calculate in-state tuition using GGC tuition calculator
            driver.get(GGC_TUITION_CALC);
            WebElement inOrOut = driver.findElement(By.cssSelector("#inorout1"));
            inOrOut.click();
            Select creditHours = new Select(driver.findElement(By.cssSelector("#creditHOURS")));
            creditHours.selectByIndex(1);

            // Getting semester in-state tuition from GGC tuition calculator
            WebElement totalCost = driver.findElement(By.cssSelector("#totalcost"));
            String costString = totalCost.getAttribute("value");
            System.out.println(costString);
            double tCost = Double.parseDouble(costString.replace("$", ""));
            System.out.println(tCost);

            // Pulls up banner log-in site.
            driver.get(GGC_BANNER);
            WebElement userBox = driver.findElement(By.cssSelector("#UserID"));
            WebElement passwordBox = driver.findElement(By.cssSelector("#PIN"));
            WebElement loginButton = driver.findElement(By.cssSelector("body > div.pagebodydiv > form > p > input[type=submit]:nth-child(1)"));

            // This is where I would put my username, if I wanted to get hacked.
            userBox.sendKeys("");
            // This is where I would put my password, if I wanted to get hacked.
            passwordBox.sendKeys("");
            loginButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

            WebElement acButton = driver.findElement(By.cssSelector("body > div.pagebodydiv > table.menuplaintable > tbody > tr:nth-child(7) > td:nth-child(2) > a"));
            acButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

            WebElement termSumm = driver.findElement(By.cssSelector("body > div.pagebodydiv > div > table > tbody > tr > td > span > table > tbody > tr:nth-child(3) > td > font > table.menuplaintable > tbody > tr:nth-child(1) > td:nth-child(2) > a > font > b"));
            termSumm.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

            Select term = new Select(driver.findElement(By.cssSelector("#term_id")));
            term.selectByIndex(1);

            WebElement submitButton = driver.findElement(By.cssSelector("body > div.pagebodydiv > form > input[type=submit]"));
            submitButton.click();
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

            WebElement fee;
            String totalFee;
            double totalFeeD;
            double fee2Compare = 0;

            //loop that parses all the fees + the tuition cost and adds the sum.
            for (int i = 3; i < 15; i++)
            {
                fee = driver.findElement(By.cssSelector("body > div.pagebodydiv > form > table:nth-child(5) > tbody > tr:nth-child(" + i + ") > td:nth-child(2) > p"));
                totalFee = fee.getText();
                System.out.println(totalFee);
                if (i == 14)
                {
                    totalFeeD = Double.parseDouble(totalFee.replace("$", "").replace(",", ""));
                    fee2Compare += totalFeeD;
                    System.out.println(totalFeeD);
                }
                else
                {
                    totalFeeD = Double.parseDouble(totalFee.replace("$", ""));
                    fee2Compare += totalFeeD;
                    System.out.println(totalFeeD);
                }

            }

            // Result showed an actual value of 2792.02 opposed to an expected value of 2742.02.
            // The test failed with a discrepancy of $50.
            Assert.assertEquals(tCost,  fee2Compare, 10);


        }
    }
