import assignment1.Main;
import assignment1.Vehicle;
import com.company.CompanyMain;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

public class MainTest
{

    ObjectMapper mapper = new ObjectMapper();

    //Returning stock example for project.
    @Test
    public void testFBStockPrice() throws IOException
    {
        Stock fbStock = YahooFinance.get("GOOG");
        BigDecimal fbPrice = fbStock.getQuote().getPrice();
        System.out.println(fbPrice.toString());
    }

    @Test
    public void readJSONFile() throws IOException
    {
        String link = "https://api.darksky.net/forecast/3c5084c558861c1610447b49a45f4eb4/37.8267,-122.4233";
        String json = IOUtils.toString(new URL(link).openStream(),"UTF-8");
        JsonNode node = mapper.readTree(json);
        String temp = node.get("currently").get("temperature").asText();
        System.out.println("Currently in Los Angeles: " + temp + "°F");
        Assert.assertNotNull(temp);
    }

    /** Method: testWriteJSON()
     * Serializes an object into a JSON string.
     * @throws IOException
     */
    @Test
    public void testWriteJSON() throws IOException
    {
        Vehicle c1 = Main.generateCar();
        String json = mapper.writeValueAsString(c1);
        System.out.println(json);
    }

    // AAA Style
    // arrange, act, assert
    @Test
    public void testSmallestElement()
    {
        // arrange
        int[] nums = {1, 5, 8, -10, 6, 7};
        // act
        int min = CompanyMain.getMin(nums);
        //assert
        Assert.assertEquals(-10, min); // expected, actual

        // arrange
        int[] nums2 = {7};
        int min2 = CompanyMain.getMin(nums2);
        // assert
        Assert.assertEquals(7, min2);
        // check that the exception is thrown

    }

    @Test
    public void testWriteToFile() throws IOException {
        String line1 = "this is line1 ";
        String line2 = "{ \"name\": \"dr.im\" }";
        String line3 = "this is line3";
        FileUtils.writeStringToFile(new File("lines.txt"),line1 + "\n","UTF-8");
        FileUtils.writeStringToFile(new File("lines.txt"),line2 + "\n", "UTF-8",true);

    }

    @Test
    public void testSelenium()
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.ggc.edu");
    }

}
