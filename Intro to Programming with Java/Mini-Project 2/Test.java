/**
 * This program is my response to Assignment 7 for the class 605.201.81 Intro
 * to Programming Using Java at the JHU EPP CS program.
 *
 * <TEXT HERE>
 *
 * @author: Sean Connor
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Test
{
    public static void main(String[] args)
    {
        String filename = "product-list-1.csv";

        ReadCSV productList = new ReadCSV(filename);

        productList.toArray();

        productList.print();



    }

}