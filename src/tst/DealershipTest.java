import Assignment4.VehicleTooCheapException;
import Assignment4.VehicleTooOldException;
import assignment1.Car;
import Assignment3.Dealership;
import assignment1.Truck;
import assignment1.Vehicle;
import assignment2.Inventory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Date;

public class DealershipTest
{
    private Dealership d;
    private static final String CSV_LINK =
            "https://gist.githubusercontent.com/tacksoo/260dc1d2e6c3bf11186aa02cde72693a/raw/faa2ca7d3c73febaf5ff62b3feece23a90d1b4c2/vehicles.csv";
    private static PrintStream stdOut;

    // Only does it once
    // why? Sometimes we only want to do something  once
    // rely on file/database or something but only want to do it once
    // HAS TO BE STATIC
    //  @BeforeClass
    @BeforeClass
    public static void prepareFixture() throws IOException
    {
        stdOut = System.out;
        System.setOut(new PrintStream("log.json"));
    }

    // Prints before every test
    @Before
    public void setUp()
    {
        d = new Dealership();
    }

    @Test
    public void emptyDealerTest()
    {
        Assert.assertEquals(0, d.getSize());
    }

    @Test
    public void loadFromWebTest()
    {
        d.loadInventoryFromWeb(CSV_LINK);
        Assert.assertEquals(4, d.getSize());
        System.out.println("testing loadFromWeb");
    }

    @Test
    public void testCheapestVehicleInDealership()
    {
        d.loadInventoryFromWeb(CSV_LINK);
        Vehicle cheap = d.getCheapestVehicle();
        Assert.assertEquals("Viper", cheap.getModel());
        System.out.println("testCheapestVehicle");
    }

    @Test
    public void testMostExpensiveVehicleInDealership()
    {
        d.loadInventoryFromWeb(CSV_LINK);
        Vehicle expensive = d.getMostExpensiveVehicle();
        Assert.assertEquals("F-150", expensive.getModel());
    }

    /** Method: testAveragePriceOfDealership()
     *  Tests whether the average price of the cars in the dealer's inventory match an expected value.
     */
    @Test
    public void testAveragePriceOfDealership()
    {
        d.loadInventoryFromWeb(CSV_LINK);
        Assert.assertEquals(52500, d.getInventory().getAveragePriceOfVehicles(), 0.1);
        // ( 50000 + 90000 + 20000 + 50000 ) / 4.0
    }

    @Test
    public void testCurrentYear()
    {
        Date now = new Date();
        now.toString();
        System.out.println(now);
        String year = now.toString().split(" ")[5];
        System.out.println(year);
        //Assert.assertEquals(Integer.parseInt(year), 2020);

        LocalDate date = LocalDate.now();
        System.out.println(date.getYear());
    }

    @Test
    public void testCreateCarWithLine()
    {
        String line = "Car,A12345,Kia,Stinger,2020,true,50000,15,true\n";
        Car car = (Car) d.createVehicleWithLine(line);
        Assert.assertEquals("A12345", car.getVin());
        Assert.assertEquals("Stinger", car.getModel());
        Assert.assertEquals(false, car.isConvertible());
    }

    @Test
    public void testCreateTruckWithLine()
    {
        String line = "Truck,N12345,Tesla,Cybertruck,2022,false,50000,80,true,10\n";
        Truck truck = (Truck) d.createVehicleWithLine(line);
        Assert.assertEquals("N12345", truck.getVin());
        Assert.assertEquals(10, truck.getTowCapacity(), 0);
        Assert.assertEquals(true, truck.isHasSideStep());

    }

    @Test(expected = ArithmeticException.class, timeout = 10)
    public void testException()
    {
        for (int i = 0; i < 1000; i++)
        {
            System.out.println("hello world!");
        }
        System.out.println(0 / 0);
    }

    @Test(timeout = 5000)
    public void testLoop()
    {
        while(true) {}
    }

    @AfterClass
    public static void cleanUpFixture()
    {
        System.setOut(stdOut);
        System.out.println("Cleaned up!");
    }

    @Test
    public void testStoreToJSON() throws JsonProcessingException
    {
        String link = "https://gist.githubusercontent.com/tacksoo/4b67ad84945d36415b62cf35a3fd1b61/raw/199e1caabd5dc04273962e2677c45d5a75f4b279/cars.csv";
        Dealership dealer = new Dealership();
        dealer.loadInventoryFromWeb(link);
        Assert.assertEquals(3, dealer.getSize());
        ObjectMapper mapper = new ObjectMapper();
        Inventory inv = dealer.getInventory();
        String json = mapper.writeValueAsString(inv);
        System.out.println(json);
    }

    @Test
    public void storeInventoryToJSONFileTest() throws IOException
    {
        d.storeInventoryToJSONFile("Cars.json");
        String json = FileUtils.readFileToString(new File("Cars.json"), "UTF-8");
        String[] cars = json.split("\n");
        Assert.assertTrue(new File("Cars.json").exists());
        Assert.assertTrue(new File("Cars.json").length() != 0);
        Assert.assertEquals(3, cars.length);
    }

    //FileUtils to read/write from a file
    //ObjectMapper to serialize/deserialize
    @Test
    public void loadInventoryFromJSONFileTest() throws IOException
    {
        d.loadInventoryFromJSONFile("Cars.json");
        Assert.assertTrue(new File("Cars.json").exists());
        Assert.assertTrue(new File("Cars.json").length() != 0);
        Assert.assertEquals(3, d.getInventory().getSize());
    }

}
