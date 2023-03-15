package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class CompanyMain
{

    public static int getMin(int[] nums)
    {
        if (nums.length == 0)
        {
            throw new IllegalArgumentException("Empty Array! No values in it!");
        }

        int min = Integer.MAX_VALUE;
        for (int num : nums)
        {
            if (num < min)
            {
                min = num;
            }
        }
        return min;
    }


    public static void main(String[] args)  throws IOException
    {
        //small script to check if 'Fall Guys' is on sale
	    URL url = new URL("https://www.isthereanydeal.com");
	    InputStream is = url.openStream();
	    assert is != null;
	    InputStreamReader isr = new InputStreamReader(is);
	    BufferedReader br = new BufferedReader(isr);
	    assert br != null;
	    StringBuilder sb = new StringBuilder();
	    for(String line = br.readLine(); line != null; line = br.readLine())
        {
            sb.append(line);
        }
	    String content = sb.toString();
	    assert content.length() > 0;
        System.out.println(content);

        boolean onSale = content.contains("Fall Guys");
        System.out.println("Is the game 'Fall Guys' on sale? " + onSale);
    }

}
