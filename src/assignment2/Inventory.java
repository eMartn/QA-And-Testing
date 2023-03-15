package assignment2;

import Assignment4.VehicleTooCheapException;
import Assignment4.VehicleTooOldException;
import assignment1.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;

public class Inventory
{

    private ArrayList<Vehicle> vehicles;

    public ArrayList<Vehicle> getVehicles()
    {
        return vehicles;
    }

    public Inventory()
    {
        vehicles = new ArrayList<>();

    }

    public void add(Vehicle v)
    {
        if (v.getRetailPrice() < 500)
        {
            throw new VehicleTooCheapException("Vehicle is less than $500.");
        }
        int currentYear = LocalDate.now().getYear();

        if (currentYear - v.getYear() > 8)
        {
            int years = currentYear - v.getYear();
            throw new VehicleTooOldException("Your vehicle is " + years + " years old.");
        }

        if (containsVehicle(v))
        {
            throw new IllegalArgumentException("Duplicate vehicle found in inventory.");
        }

        vehicles.add(v);
    }

    public boolean containsVehicle(Vehicle v)
    {
        String vin2check = v.getVin();
        String vin;

        for (Vehicle vehicle : vehicles)
        {
            vin = vehicle.getVin();
            if (vin.equals(vin2check))
            {
                return true;
            }
        }
        return false;
    }


    public int getSize()
    {
         return vehicles.size();
    }

    public void remove(Vehicle v)
    {
        for (int i = 0; i < vehicles.size(); i++)
        {
            if (v.getVin().equals(vehicles.get(i).getVin()))
            {
                vehicles.remove(i);
                break;
            }
        }
    }

    /**
     *  Get the cheapest vehicle in terms of price
     * @return vehicle with lowest price in inventory
     */
    public Vehicle findCheapestVehicle()
    {
        if (vehicles.size() == 0)
        {
            throw new IllegalStateException("No vehicles are in inventory");
        }
        double min = Double.MAX_VALUE;
        double cP;
        Vehicle cheapestV = vehicles.get(0);
        for (Vehicle vehicle : vehicles)
        {
            cP = vehicle.getRetailPrice();
            if (cP < min)
            {
                min = cP;
                cheapestV = vehicle;
            }
        }
        return cheapestV;
    }

    public Vehicle findMostExpensiveVehicle()
    {
        if (vehicles.size() == 0)
        {
            throw new IllegalStateException("No vehicles are in inventory");
        }

        double max = Double.MIN_VALUE;
        double mE;
        Vehicle mExpensiveVehicle = vehicles.get(0);

        for (Vehicle vehicle : vehicles)
        {
            mE = vehicle.getRetailPrice();
            if (mE > max)
            {
                max = mE;
                mExpensiveVehicle = vehicle;
            }
        }

        return mExpensiveVehicle;
    }


    //helper
    public double getAveragePriceOfVehicles()
    {
        double avg = 0;

        for (Vehicle vehicle : vehicles)
        {
            avg = vehicle.getRetailPrice() + avg;
        }
        return avg / vehicles.size();
    }

    public void printAveragePriceOfAllVehicles()
    {
        System.out.println(getAveragePriceOfVehicles());
    }

}
