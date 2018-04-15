/**
 * This program is part of my response to Assignment 10 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This program requires one or two arguments - (1) the data source file path,
 * and (2) the number of records in the data file to display (optional).
 *
 * The program requires data source file to be a text file output of the
 * FormatData program (this can be txt, csv, xls, doc, etc).
 *
 * This program will output data read from the input file to the terminal.
 *
 * @author: Sean Connor
 */

import java.io.*;

class ProduceReport
{
    public static void main(String args[])
    {
        ProduceReport test = new ProduceReport();

        // initialize variables
        String filename = null;
        String temp_number_records = null;
        int number_records = 0;
        int width = 0;

        //Ensure that the required number of arguments are provided
        if (args.length == 2)
        {
            filename = args[0];
            temp_number_records = args[1];
            number_records = Integer.parseInt(temp_number_records);
        }

        else if (args.length == 1)
        {
            filename = args[0];
            number_records = test.countLines(filename);
        }

        else
        {
            System.out.println("\nMissing required arguments. " +
                    "Please try again.\n");
        }

        width = test.countItemsInLine(filename);

        String[][] data = test.parseCSV(filename,number_records,width);

        test.printHeaders();
        test.printStringArray(data);


    }



    /**
     *
     *
     * @param filename
     * @return
     */
    private int countLines(String filename)
    {
        int lines = 0;

        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename))) )
        {
            //Use while-loop to count the number of lines in

            while (br.readLine() != null)
            {
                lines++;
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lines;
    }



    /**
     *
     *
     * @param filename
     * @return
     */
    private int countItemsInLine(String filename)
    {
        int width = 0;

        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename))) )
        {
            String line = br.readLine();
            String[] values = line.split(",");
            width = values.length;
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

        return width;
    }



    /**
     *
     *
     * @param filename
     * @param length
     * @param width
     * @return
     */
    private String[][] parseCSV(String filename,
                                int length,
                                int width)
    {
        String[][] data_array = new String[length][width];

        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename))) )
        {
            String line;
            String[] values;

            for (int i = 0; i < length; i++)
            {
                line = br.readLine();
                values = line.split(",");

                for (int j = 0; j < width; j++)
                {
                    data_array[i][j] = values[j];
                }
            }
        }

        catch(IOException e)
        {
            e.printStackTrace();
        }

        return data_array;
    }



    /**
     *
     *
     * @param data
     */
    private void printStringArray(String[][] data)
    {
        for (int i = 0; i < data.length; i++)
        {
            System.out.printf("%1$5s", data[i][0]);
            System.out.printf("%1$15s", data[i][1]);
            System.out.printf("%1$20s", data[i][2]);
            System.out.printf("%1$30s", data[i][3]);
            System.out.printf("%1$20s", data[i][4]);

            System.out.println();
        }
    }



    /**
     *
     *
     */
    private void printHeaders()
    {
        System.out.printf("%1$5s", "State");
        System.out.printf("%1$15s", "Population");
        System.out.printf("%1$20s", "Child Population");
        System.out.printf("%1$30s", "Child Poverty Population");
        System.out.printf("%1$20s", "% Child Poverty");

        System.out.println();

        System.out.printf("%1$5s", "-----");
        System.out.printf("%1$15s", "----------");
        System.out.printf("%1$20s", "----------------");
        System.out.printf("%1$30s", "------------------------");
        System.out.printf("%1$20s", "---------------");

        System.out.println();
    }


}