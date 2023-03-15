import MidTerm.Lawyer;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeTest
{

    @Test
    public void getVacationDaysTest()
    {
        Lawyer l = new Lawyer("Smith");
        Assert.assertEquals(1, l.getVacationDays());
        System.out.println(l.getName() + " has the correct vacation days on file.");
    }
}
