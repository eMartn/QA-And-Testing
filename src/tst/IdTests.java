import MidTerm.Greeting;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class IdTests
{

    @Test
    public void highestIdTest()
    {
        Greeting g = new Greeting();
        List<Greeting> greetings = new ArrayList<>();
        Assert.assertNull(g.getHighestGreeting(greetings));
        greetings.add(new Greeting(7, "hello"));
        greetings.add(new Greeting(10, "hi"));
        greetings.add(new Greeting(678, "yay"));
        greetings.add(new Greeting(3, "hola"));
        greetings.add(new Greeting(0, "hihi"));
        Assert.assertEquals(678, g.getHighestGreeting(greetings).getId());
    }
}
