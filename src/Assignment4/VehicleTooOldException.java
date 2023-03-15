package Assignment4;

public class VehicleTooOldException extends RuntimeException
{
    private static final long serialVersionUID = -4855629815581631102L;

    public VehicleTooOldException()
    {
    }

    public VehicleTooOldException(String message)
    {
        super(message);
    }

    public VehicleTooOldException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public VehicleTooOldException(Throwable cause)
    {
        super(cause);
    }
}
