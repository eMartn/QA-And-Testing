package assignment1;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        Vehicle myCar = new Vehicle("A12345678", "Genesis", "GV90", 2020, 80000,
                15, false);
        myCar.printVehicle();

        Car niceCar = new Car("X123567", "Bugatti", "Veyron", 2020, 3000000, 5,
                                false, false);
        niceCar.printVehicle();
        Truck raptor = new Truck("Y12567", "Ford", "Raptor", 2020, 50000, 10,
                                true, true, 1500);
        raptor.printVehicle();

        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            vehicles.add(generateCar());
        }
        for (int i = 0; i < 10; i++)
        {
            vehicles.get(i).printVehicle();
        }

    }

    public static Car generateCar()
    {
        String vin = RandomStringUtils.randomAlphabetic(17).toUpperCase();
        String make = RandomStringUtils.randomAlphabetic(10);
        String model = RandomStringUtils.randomAlphabetic(10);
        int year = RandomUtils.nextInt(1990, 2021);
        boolean is4wheel = RandomUtils.nextBoolean();
        double price = RandomUtils.nextDouble(10000, 3000000);
        double mpg = RandomUtils.nextDouble(5, 50);
        boolean isConvert = RandomUtils.nextBoolean();
        return new Car(vin, make, model, year, price, mpg, is4wheel, isConvert);
    }

    public static Truck generateTruck()
    {
        String vin = RandomStringUtils.randomAlphabetic(17);
        String make = RandomStringUtils.randomAlphabetic(10);
        String model = RandomStringUtils.randomAlphabetic(10);
        int year = RandomUtils.nextInt(1990, 2021);
        boolean is4wheel = RandomUtils.nextBoolean();
        double retailPrice = RandomUtils.nextDouble(10000, 3000000);
        double mpg = RandomUtils.nextDouble(5, 50);
        boolean hasSideStep = RandomUtils.nextBoolean();
        double towCapacity = RandomUtils.nextDouble(1, 10);

        return new Truck(vin, make, model, year, retailPrice, mpg, is4wheel, hasSideStep, towCapacity);
    }
}
