package com.company;

import java.io.File;
import java.io.PrintStream;

public class PrintToFile
{

    public static void main(String[] args) throws Exception
    {
        System.setOut(new PrintStream(new File("out.txt.")));
        System.out.println("hello world!");
    }
}
