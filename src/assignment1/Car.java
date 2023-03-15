package assignment1;

public class Car extends Vehicle  {

    private static final long serialVersionUID = -4262105874357283581L;
    private boolean convertible;

    public Car()
    {

    }
 // super must come first
    public Car(String vin, String make, String model, int year, double retailPrice, double mpg, boolean is4Wheel, boolean isConvertible) {
        super(vin, make, model, year, retailPrice, mpg, is4Wheel);
        this.convertible = isConvertible;
    }

    public boolean isConvertible() {
        return convertible;
    }

    public void setConvertible(boolean convertible) {
        this.convertible = convertible;
    }


    /*
    Method that uses a ternary way of deciding if the vehicle is convertible or not
    Ternary is essentially a if else
    */
    @Override
    public String toString() {
        String c = convertible ? "It is convertible" : "Not convertible";
       return super.toString() + "\n" + c;

    }

    // Keyword this refer to?
    // refers to current object
    // print out current object calls out current object or toString
    public void printVehicle()
    {
        System.out.println(this);
    }
}
