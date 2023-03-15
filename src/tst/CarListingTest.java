import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class CarListingTest
    {

        public static final String CL_URL = "https://atlanta.craigslist.org/d/cars-trucks/search/cta";
        public static final String DB_CONNECTION_URL = "jdbc:sqlite:test.db";
        public static Connection connection;
        public static WebDriver driver;

        @BeforeClass
        public static void setUp() throws SQLException {
            System.setProperty("webdriver.chrome.driver","chromedriver.exe");
            driver = new ChromeDriver();
            connection = DriverManager.getConnection(DB_CONNECTION_URL);

        }

        @AfterClass
        public static void cleanUp() throws SQLException {
            driver.close();
            connection.close();
        }

        @Test
        public void testNumOfListings()
        {
            driver.get(CL_URL);
            List<WebElement> vehiclesList = driver.findElements(By.className("result-row"));
            Assert.assertEquals(120, vehiclesList.size());
        }

        @Test
        public void testListingData() throws SQLException {
            driver.get(CL_URL);
            List<WebElement> vehicles = driver.findElements(By.className("result-row"));
            int beforeCount = getVehicleTableSize();
            for (WebElement vehicle : vehicles)
            {

//            System.out.println(temp.getAttribute("innerHTML"));
                // result-title hdrlnk, class result-price
                WebElement anchor = vehicle.findElement(By.xpath(".//a[@class = 'result-title hdrlnk']"));
                WebElement span = vehicle.findElement(By.xpath(".//span[@class = 'result-price']"));

                String title = anchor.getText();
                String url = anchor.getAttribute("href");
                String price = span.getText();
                String date = new Date().toString();

                System.out.println("Title: " + title + " URL: " + url + " Price: " + price + "Date: " + date + "\n");
//                System.out.println("Title: " + title);
//                System.out.println("URL: " + url);
//                System.out.println("Price: " + price);
//                System.out.println("Date: " + date + "\n");

                Assert.assertFalse(title.isEmpty());
                Assert.assertFalse(url.isEmpty());
                Assert.assertFalse(price.isEmpty());
                Assert.assertFalse(date.isEmpty());
                int intPrice = Integer.parseInt(price.replace("$", "").replace(",", ""));
                addVehicleToDB(title, intPrice, url, date);
            }
            int afterCount = getVehicleTableSize();
            System.out.println(vehicles.size());

            // Checks to see if the test adds 120 rows to the existing table
            Assert.assertEquals(beforeCount + 120, afterCount);
        }


        public void addVehicleToDB(String title, int price, String URL, String timestamp) throws SQLException
        {
            String sql = "insert into vehicles values(null, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setInt(2, price);
            ps.setString(3, URL);
            ps.setString(4, timestamp);
            ps.executeUpdate();
            ps.close();
        }

        @Test
        @Ignore
        public void testAddDB() throws SQLException
        {
            addVehicleToDB("cheap Tesla", 20000, "https://Tesla.com", "Oct 8th, 2020");
            Assert.assertEquals(2, getVehicleTableSize());
        }


        public int getVehicleTableSize() throws SQLException
        {
            String sql = "select count(*) from vehicles";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            int count = rs.getInt("count(*)");
            return count;
        }
    }
