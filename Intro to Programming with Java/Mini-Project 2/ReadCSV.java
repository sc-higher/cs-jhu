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

public class ReadCSV
{
    // initialize variables
    private String fileName;
    private List<List<String>> lines = new ArrayList<>();
    private int numRows;
    private int numCols;


    // ReadCSV constructor
    public ReadCSV(String fileName)
    {
        this.fileName = fileName;
    }


    /**
     *
     */
    public void toArray()
    {
        File csvFile = new File(fileName);

        // this gives you a 2-dimensional array of strings
        Scanner input;

        try
        {
            input = new Scanner(csvFile);

            while (input.hasNextLine())
            {
                String line = input.nextLine();
                String[] values = line.split(",");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            input.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


    /**
     *
     */
    public void print()
    {
        for(List<String> line : lines)
        {
            for (String value : line)
            {
                System.out.printf("%1$15s", value);
            }
            System.out.println();
        }

        System.out.println("lines[0][0] = " + lines.get(0).get(0));
        System.out.println("lines[1][0] = " + lines.get(1).get(0));
        System.out.println("lines[0][1] = " + lines.get(0).get(1));

        System.out.println("\nlines length (# rows) = " + lines.size());
        System.out.println("lines[0] = " + lines.get(0));
        System.out.println("lines[0] length (# columns) = " +
                lines.get(0).size());
    }

    public int numRows()
    {
        numRows = lines.size();
        return numRows;
    }

    public int numCols()
    {
        numCols = lines.get(0).size();
        return numCols;
    }

    public String getValue(int rowIndex, int colIndex)
    {
        return lines.get(rowIndex).get(colIndex);
    }





}