/**
 * This program is my response to Mini-Project 2 for the class 605.201.81 Intro
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

        System.out.println();
        productList.print();

        char usd_symbol = 36;
        char eur_symbol = 8364;
        char jpy_symbol = 165;

        System.out.println("\nValue of quarter: " + usd_symbol +
                (double) USD.QUARTER.getValue()/100);

        System.out.println("\nFive EUR: " + eur_symbol +
                (double) EUR.FIVEN.getValue()/100);

        System.out.println("\nOne hundred JPY: " + jpy_symbol +
                JPY.HUNDRED.getValue());

    }



}