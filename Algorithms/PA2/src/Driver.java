/**
 * This program is part of my response to PA 2 for the class 605.621
 * Foundations of Algorithms at the JHU EPP CS program.
 *
 * The main driver program for this assignment.
 *
 * @author Sean Connor
 * @date 28 October 2018
 */

import java.util.Arrays;

public class Driver{

    public static void main(String[] args){

        // Read the input file and store data in int[] array
        FileInput file = new FileInput(args);
        int[] array = file.getData();

        // Create a Quicksort object and perform quicksort on the input array
        Quicksort qs = new Quicksort();
        qs.sort(array);

        // Get the output filename
        String output_filename = file.getOutputFilename();

        // Create a FileOutput object to handle output tasks
        FileOutput out = new FileOutput(file.getOutputFilename());

        // Fill the FileOutput StringBuilder object
        out.appendHeader("JHU 605.621 Programming Assignment 2");
        out.append("Input Filename: " + file.getInputFilename());
        out.append("");
        out.append("# calls of quicksort(): " + qs.count);
        out.append("# calls of exchange(): " + qs.ops);
        out.append("");
        out.append("Sorted Array:");
        out.appendArray(array);

        // Write the output file
        out.write();

    }

}