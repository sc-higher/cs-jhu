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
        String fileName= "product-list-1.csv";
        File file= new File(fileName);

        // this gives you a 2-dimensional array of strings
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try
        {
            inputStream = new Scanner(file);

            while(inputStream.hasNextLine()){
                String line = inputStream.nextLine();
                String[] values = line.split(",");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        // the following code lets you iterate through the 2-dimensional array
        int lineNo = 1;

        for(List<String> line: lines)
        {
            int columnNo = 1;
            for (String value: line) {
                System.out.println("Line " + lineNo + " Column " + columnNo + ": " + value);
                columnNo++;
            }
            lineNo++;
        }
    }

}