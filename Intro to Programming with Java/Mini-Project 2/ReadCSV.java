/**
 * This program is my response to <INSERT> for the class 605.201.81 Intro
 * to Programming Using Java at the JHU EPP CS program.
 *
 * <TEXT HERE>
 *
 * @author: Sean Connor
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadCSV
{
    // initialize variables
    private String filename;
    private String line;
    private String[] values;
    private int[] csvDimensions = new int[2]; // csvDimensions[rows,columns]
    private String[][] itemStringArray;
    private Item[] itemArray;


    // ReadCSV constructor
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
            e.printStackTrace();
        }
    }


    /**
     *
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

                itemStringArray[i][0] = values[0];
                itemStringArray[i][1] = values[1];
                itemStringArray[i][2] = values[2];
                itemStringArray[i][3] = values[3];
            }

            input.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    /**
     *
     * @return
     */
    public String[][] getStringArray()
    {
        return itemStringArray;
    }


    /**
     *
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
     *
     * @param row
     * @param column
     * @return
     */
    public String getStringValue(int row, int column)
    {
        return itemStringArray[row][column];
    }


    /**
     *
     * @param itemFromCSV
     * @param itemList
     */
    public Item[] getItemArray()
    {
        itemArray = new Item[itemStringArray.length - 1];

        for (int i = 1; i < itemStringArray.length; i++)
        {
            String id = itemStringArray[i][0];
            String name = itemStringArray[i][1];
            int price = Integer.parseInt(itemStringArray[i][2]);
            int quantity = Integer.parseInt(itemStringArray[i][3]);

            itemArray[i-1] = new Item(id,name,price,quantity);
        }

        return itemArray;
    }

}