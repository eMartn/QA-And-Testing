import assignment1.Car;
import assignment1.Main;
import assignment1.Vehicle;
import assignment2.Inventory;
import org.junit.Assert;
import org.junit.Test;

public class InventoryTest
{
        @Test
        public void testInventory()
        {
            Inventory i = new Inventory();
            Assert.assertNotNull(i);
            // ttd in a nutshell
            // 1. write test first
            // 2. make the test fail
            // 3. write just enough code to pass the test
            // repeat until you finish project
        }

        @Test
        public void testAddToInventory()
        {
            Inventory inv = new Inventory();
            Vehicle v = Main.generateTruck();

            inv.add(v);
            Assert.assertEquals(1, inv.getSize());
            //inv.add(v);
            //Assert.assertEquals(1, inv.getSize());
            Vehicle t = Main.generateTruck();
            inv.add(t);
            Assert.assertEquals(2, inv.getSize());

        }

        @Test
        public void testContains()
        {
            Inventory inv = new Inventory();
            Vehicle v = Main.generateCar();
            inv.add(v);
            Assert.assertTrue(inv.containsVehicle(v));
            Vehicle t = Main.generateTruck();
            Assert.assertFalse(inv.containsVehicle(t));

        }

        @Test
        public void testRemove()
        {
            Inventory inv = new Inventory();
            Vehicle v = Main.generateCar();
            inv.add(v);
            inv.remove(v);
            Assert.assertEquals(0, inv.getSize());
        }

        @Test
        public void testCheapest()
        {
            Inventory inv = new Inventory();
            Vehicle c1 = Main.generateCar();
            Vehicle c2 = Main.generateCar();
            Vehicle t1 = Main.generateTruck();
            inv.add(c1);
            inv.add(c2);
            inv.add(t1);
            //testing remove
            inv.remove(t1);
            //testing removing a value that has already been removed
            inv.remove(t1);
//            double cheapest = Math.min(Math.min(c1.getRetailPrice(), c2.getRetailPrice()), t1.getRetailPrice());
//            Assert.assertEquals(cheapest, inv.findCheapestVehicle().getRetailPrice(), 0.1);


        }

        @Test
        public void testMostExpensive()
        {
            Inventory inv = new Inventory();
            Vehicle c1 = Main.generateCar();
            Vehicle c2 = Main.generateCar();
            Vehicle t1 = Main.generateTruck();
            inv.add(c1);
            inv.add(c2);
            inv.add(t1);
            double expensive = Math.max(Math.max(c1.getRetailPrice(), c2.getRetailPrice()), t1.getRetailPrice());
            Assert.assertEquals(expensive, inv.findMostExpensiveVehicle().getRetailPrice(), 0.1);

        }

        @Test
        public void testAveragePrice()
        {
            Inventory inv = new Inventory();
            Vehicle c1 = Main.generateCar();
            Vehicle c2 = Main.generateCar();
            Vehicle t1 = Main.generateTruck();
            inv.add(c1);
            inv.add(c2);
            inv.add(t1);
            double average = (c1.getRetailPrice() + c2.getRetailPrice() + t1.getRetailPrice()) / 3.0;
            //Assert.assertEquals(average, inv.getAveragePriceOfVehicles(), 0.1);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testDuplicateVehicle()
        {
            Inventory inv = new Inventory();
            Car c = (Car) Main.generateCar();
            Car duplicate = new Car(c.getVin(), c.getMake(), c. getModel(), c.getYear(), c.getRetailPrice(), c.getMpg(),
                                    c.isIs4Wheel(), c.isConvertible());
            inv.add(c);
            inv.add(duplicate);
        }
}
