package MidTerm;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class Greeting
{

    private int id;
    private String content;
    ArrayList<Greeting> greetings;

    public Greeting()
    {

    }

    public Greeting(int id, String content)
    {
        this. id = id;
        this.content = content;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void populateList()
    {
        greetings = new ArrayList<Greeting>();
        int randomInt;
        Greeting greeting2Add;
        for (int i = 0; i < 10; i++)
        {
            randomInt = RandomUtils.nextInt(1, 100);
            greeting2Add = new Greeting();
            greeting2Add.setId(randomInt);
            greetings.add(greeting2Add);
        }
    }

    public Greeting getHighestGreeting(List<Greeting> list)
    {
        if (list.size() == 0)
        {
            return null;
        }

        int max = Integer.MIN_VALUE;
        Greeting highestId = null;
        for (Greeting greeting : list)
        {
            if (greeting.getId() > max)
            {
                max = greeting.getId();
                highestId = greeting;
            }
        }
        return highestId;
    }

    public static void main(String[] args)
    {
        Greeting g = new Greeting();
        g.populateList();
        for (Greeting i : g.greetings)
        {
            System.out.print(i.getId() + ", ");
        }

        System.out.println(g.getHighestGreeting(g.greetings).getId());
    }

}
