/**
 *
 *
 * @author: Sean Connor
 */

import java.io.*;
import java.util.*;

public class ReadCSV
{
    // initialize variables
    private String filename;
    private String line;
    private String[] values;
    private int[] csvDimensions = new int[2]; // csvDimensions[rows,columns]
    private String[][] itemStringArray;


    // constructor
    /**
     * This constructor requires the filename of the CSV-style file to be
     * read.
     *
     * @param filename
     */
    public ReadCSV(String filename)
    {
        this.filename = filename;

        File csvFile = new File(filename);

        Scanner input;

        try
        {
            input = new Scanner(csvFile);

            while( input.hasNextLine() )
            {
                csvDimensions[0]++;  // determine CSV rows (height)

                line = input.nextLine();
                values = line.split(",");
            }

            csvDimensions[1] = values.length;  // determine CSV columns (width)

            input.close();
        }

        catch(FileNotFoundException e)
        {
            System.out.println("\nFile not found. Please try again.\n");
            System.exit(1);
        }
    }


    /**
     * This method parses the CSV-style file given and adds each item to a
     * new String[row][column].
     */
    public void toArray()
    {
        int rows = csvDimensions[0];
        int columns = csvDimensions[1];
        itemStringArray = new String[rows][columns];

        File csvFile = new File(filename);
        Scanner input;

        try
        {
            input = new Scanner(csvFile);

            for (int i = 0; i < rows; i++)
            {
                line = input.nextLine();
                values = line.split(",");

                for (int j = 0; j < columns; j++)
                {
                    itemStringArray[i][j] = values[j];
                }
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * This is a getter method that returns the parsed String[][].
     *
     * @return String[row][column] containing the parsed items
     */
    public String[][] getStringArray()
    {
        return itemStringArray;
    }


    /**
     * This method prints each item in the String[][] to the terminal
     */
    public void printStringArray()
    {
        for (int i = 0; i < itemStringArray.length; i++)
        {
            for (int j = 0; j < itemStringArray[i].length; j++)
            {
                System.out.printf("%1$15s", itemStringArray[i][j]);
            }
            System.out.println();
        }
    }


    /**
     * This method returns the value of a specific index in the String[][].
     *
     * @param row
     * @param column
     * @return the item of the specified row and column
     */
    public String getStringValue(int row, int column)
    {
        return itemStringArray[row][column];
    }



    /**
     *
     * @param filename
     * @return
     */
    public ArrayList<Song> toArrayList()
    {
        ArrayList<Song> database = new ArrayList<Song>();

        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename))) )
        {
            String line;
            String key;
            String[] values;

            br.readLine(); // skip csv headers

            while ( ( line = br.readLine() ) != null )
            {
                values = line.split(",");

                key = values[1];

                database.add(new Song(values[0],values[1],values[2],
                        values[3],values[4],values[5]));
            }
        }

        catch (Exception e)
        {
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }

        return database;
    }

}