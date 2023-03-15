package assignment1;


import java.io.Serializable;

public class Vehicle implements Serializable
{

    private static final long serialVersionUID = 5270009650621411114L;
    private String vin;
    private String make;
    private String model;
    private int year;
    private boolean is4Wheel;
    private double retailPrice;
    private double mpg;

    public Vehicle()
    {

    }

    public Vehicle(String vin, String make, String model, int year, double retailPrice, double mpg, boolean is4Wheel) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.is4Wheel = is4Wheel;
        this.retailPrice = retailPrice;
        this.mpg = mpg;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isIs4Wheel() {
        return is4Wheel;
    }

    public void setIs4Wheel(boolean is4Wheel) {
        this.is4Wheel = is4Wheel;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getMpg() {
        return mpg;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    /**
     * 2015 Ford F-150
     * 4WD
     * $35,000
     * 17MPG
     * No Side Step
     * Tow up to 2 tons
     * @return string with the characteristics of the truck
     */
    @Override
    public String toString() {
        String has4W = is4Wheel ? "4WD" : "No 4WD";
        return year + " " + make + " " + model + "\n" + has4W + "\n" + "$" + retailPrice + "\n"+
        +  mpg + " MPG";
    }

    // Keyword this refer to?
    // refers to current object
    // print out current object calls out current object or toString
    public void printVehicle()
        {
            System.out.println(this);
        }

}
