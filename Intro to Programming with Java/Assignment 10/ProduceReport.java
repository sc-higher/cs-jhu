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
import java.text.DecimalFormat;

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

            try
            {
                number_records = Integer.parseInt(temp_number_records);
            }

            catch (java.lang.Exception exception)
            {
                System.out.println("\nInvalid input. Argument 2 (number " +
                        "of records) input must be integer between 0 " +
                        "and 51, inclusive.\n");
                exception.printStackTrace();
                System.exit(1);
            }

            if (number_records > 51 || number_records < 0)
            {
                System.out.println("\nArgument 2 (number of records) must " +
                        "be between 0 and 51 inclusive.\n");
                System.exit(1);
            }
        }

        else if (args.length == 1)
        {
            filename = args[0];
            number_records = test.countLines(filename);
        }

        else
        {
            System.out.println("\nInvalid number of arguments. " +
                    "Please try again.\n");
            System.exit(1);
        }

        //Find the file path
        File file = new File(filename);
        String path = file.getAbsolutePath();
        System.out.println("\nFile: " + path + "\n");


        width = test.countItemsInLine(filename);

        String[][] data = test.parseCSV(filename,number_records,width);


        //Calculate percent child poverty
        for (int i = 0; i < data.length; i++)
        {
            data[i][width] = String.valueOf(
                    ( ( Double.valueOf(data[i][width-1]) /
                            Double.valueOf(data[i][width-2])) * 100 ) );
        }


        //Format all values
        DecimalFormat df1 = new DecimalFormat("#,#00");
        DecimalFormat df2 = new DecimalFormat("#,###.00");

        int[][] temp_data = new int[data.length][width+1];

        for (int i = 0; i < data.length; i++)
        {
            for (int j = 0; j < (width); j++)
            {
                data[i][j] = df1.format(Double.valueOf(data[i][j]));
            }

            data[i][width] = df2.format(Double.valueOf(data[i][width]));

        }

        //Print all data to terminal
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
            System.out.println("\nFile not found. Please try again.\n");
            e.printStackTrace();
            System.exit(1);
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
            System.out.println("\nFile not found. Please try again.\n");
            e.printStackTrace();
            System.exit(1);
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
        String[][] data_array = new String[length][width+1];

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
            System.out.println("\nFile not found. Please try again.\n");
            e.printStackTrace();
            System.exit(1);
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