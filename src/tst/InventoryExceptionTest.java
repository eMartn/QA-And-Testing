import Assignment4.VehicleTooCheapException;
import Assignment4.VehicleTooOldException;
import assignment1.Vehicle;
import assignment2.Inventory;
import org.junit.Test;

public class InventoryExceptionTest
{
    /** Method: testVehicleTooOld()
     * tests whether a vehicle throws a VehicleTooOldException when age is under a certain threshold.
     */
    @Test(expected = VehicleTooOldException.class)
    public void testVehicleTooOld()
    {
        Vehicle car = new Vehicle("XYZ", "Kia", "Rio", 2005, 5000, 40, false);
        Inventory inv = new Inventory();
        inv.add(car);
    }

    /** Method: testVehicleTooCheap()
     * tests whether a vehicle throws a VehicleTooCheapException when price is under a certain threshold.
     */
    @Test(expected = VehicleTooCheapException.class)
    public void testVehicleTooCheap()
    {
        Vehicle car = new Vehicle("ABC", "Toyota", "Supra", 1999, 300, 30, false);
        Inventory inv = new Inventory();
        inv.add(car);
    }
}
