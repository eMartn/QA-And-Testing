package assignment1;

public class Truck extends Vehicle{

    private static final long serialVersionUID = 2756933546416194356L;
    private boolean hasSideStep;
private double towCapacity;

    public Truck()
    {

    }

    public Truck(String vin, String make, String model, int year, double retailPrice, double mpg, boolean is4Wheel,
                 boolean hasSideStep, double towCapacity) {
        super(vin, make, model, year, retailPrice, mpg, is4Wheel);
        this.hasSideStep = hasSideStep;
        this.towCapacity = towCapacity;
    }

    public boolean isHasSideStep() {
        return hasSideStep;
    }

    public void setHasSideStep(boolean hasSideStep) {
        this.hasSideStep = hasSideStep;
    }

    public double getTowCapacity() {
        return towCapacity;
    }

    public void setTowCapacity(int towCapacity) {
        this.towCapacity = towCapacity;
    }

    @Override
    public String toString() {
        String h = hasSideStep ? "Has side step" : "Does not have side step";
        return super.toString() + "\n" +  h + "\n" + towCapacity;
    }

    // Keyword this refer to?
    // refers to current object
    // print out current object calls out current object or toString
    public void printVehicle()
    {
        System.out.println(this);
    }
}
