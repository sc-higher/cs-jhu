/**
 * This program is part of my response to PA 3 for the class 605.621
 * Foundations of Algorithms at the JHU EPP CS program.
 *
 * The main driver program for this assignment.
 *
 * @author Sean Connor
 * @date 2 December 2018
 */

import java.util.Arrays;

public class Driver{

    public static void main(String[] args){

        // Read the input file and store data in int[] array
        FileInput file = new FileInput(args);
        String[] data = file.getData();

        // Create instance of the Interleaving class, used to generate soln
        Interleaving soln = new Interleaving(data);

        // Display x, y, and s on console
        String origX = soln.getX();
        String origY = soln.getY();

        System.out.println("\nInput strings");
        System.out.println("x: " + origX);
        System.out.println("y: " + origY);
        System.out.println("s: " + soln.getS());

        // Calculate solution
        ResultCounter result = soln.calculate();
        String[][] sa = soln.toStringArray(result.getResult());

        // Display s and extended x, y on console
        System.out.println("\nExtended strings");
        System.out.println("x: " + soln.getX());
        System.out.println("y: " + soln.getY());
        System.out.println("s: " + soln.getS());

        // Print relevant portion of array (upper left diagonal)
        String strArr = "\n"+Arrays.deepToString(sa).replace("], ", "]\n").replace("[","").replace("]","").replace(", "," ");
        System.out.println(strArr);

        // Produce boolean decision as to whether interleaving
        boolean dec = soln.decision(result.getResult());
        System.out.println("\nFinal Result: " + dec);

        // Get the output filename
        String output_filename = file.getOutputFilename();

        // Create a FileOutput object to handle output tasks
        FileOutput out = new FileOutput(file.getOutputFilename());

        // Fill the FileOutput StringBuilder object
        out.appendHeader("JHU 605.621 Programming Assignment 3");
        out.append("Input Filename: " + file.getInputFilename());
        out.append("");

        out.append("Input Strings");
        out.append("x: " + origX);
        out.append("y: " + origY);
        out.append("s: " + soln.getS());
        out.append("");
        out.append("Extended Strings");
        out.append("x: " + soln.getX());
        out.append("y: " + soln.getY());
        out.append("s: " + soln.getS());
        out.append("");

        out.append("# of comparisons made: " + result.getCounter());
        out.append("");

        if (soln.getS().length() <= 20) {
            out.append("Dynamic Programming Array (shown only if s <= 20)");
            out.append("");
            for (int i = 0; i <= soln.getS().length()+2; i++) {
                out.append("\n"+Arrays.deepToString(sa[i]).replace("], ", "]\n").replace("[","").replace("]","").replace(", "," "));
            }
        }

        out.append("");
        out.append("Is s and interleaving of x and y? " + dec);

        // Write the output file
        out.write();

    }

}