package Assignment4;

public class VehicleTooCheapException extends RuntimeException
{
    private static final long serialVersionUID = 7593858646441896916L;

    public VehicleTooCheapException()
    {
    }

    public VehicleTooCheapException(String message)
    {
        super(message);
    }

    public VehicleTooCheapException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public VehicleTooCheapException(Throwable cause)
    {
        super(cause);
    }
}
