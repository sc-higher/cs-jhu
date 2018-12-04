/**
 * This program is part of my response to PA 3 for the class 605.621
 * Foundations of Algorithms at the JHU EPP CS program.
 *
 * @author Sean Connor
 * @date 2 December 2018
 */

import java.io.*;
import java.util.*;

public class FileInput {

    private String[] input;
    private String filename;
    private String output_filename;
    private int numLines;
    private String[] data;

    public FileInput(String[] args){
        // Read filename from args if valid
        this.input = readArgs(args);
        this.filename = input[0];
        this.output_filename = input[1];

        // Extract data from input file
        this.numLines = fileSize(filename);
        this.data = readFile(filename,numLines);
    }


    /**
     * Method to ensure the proper number of arguments is given, and that the
     * filename given as an argument actually exists.
     *
     * @param args
     * @return   A string representing the filename to be read
     */
    private String[] readArgs(String[] args) {
        if (args.length == 2) {
            String filename = args[0];
            String output_filename = args[1];
            try {
                // Lazy file exists test
                File exists = new File(filename);
                Scanner exist_test = new Scanner(exists);
            } catch (FileNotFoundException exception) {
                System.out.println("\nFile not found.");
                System.exit(1);
            }
            String[] data = new String[2];
            data[0] = filename;
            data[1] = output_filename;
            return data;
        } else {
            System.out.println("\nInvalid number of arguments.");
            System.exit(1);
            return null;
        }
    }


    /**
     * Determines the number of lines in the input file. Returns in int
     * representing this value.
     *
     * @param filename
     * @return
     */
    private int fileSize(String filename) {

        try ( BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename))) ) {

            int counter = 0;
            String line;

            // Get number of lines in file
            while ( ( line = br.readLine() ) != null ) {
                // Check for empty line
                if ( line.equals("") ) {
                    System.out.println("\nError: Empty line found. Please check input.");
                    System.exit(1);
                }

                // Increment size counter
                counter++;
            }

            return counter;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }

        return 0;

    }


    /**
     * Runs through the file line by line and parses integer values to an
     * String[] array. Returns String[].
     *
     * @param filename
     * @param size
     * @return
     */
    private String[] readFile(String filename, int size) {
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename))) ) {

            int counter = 0;
            String line;
            String[] data = new String[size];

            // Move through input file to parse int to array
            while ( ( line = br.readLine() ) != null ) {

                // Check for empty line
                if ( line.equals("") ) {
                    System.out.println("\nError: Empty line found. Please check input.");
                    System.exit(1);
                }

                // Parse int
                data[counter] = line;

                // Increment counter to place in next array position
                counter++;
            }

            return data;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }

        return null;
    }


    public String[] getData() {
        return data;
    }


    public int getNumLines() {
        return numLines;
    }


    public String getInputFilename() {
        return filename;
    }


    public String getOutputFilename() {
        return output_filename;
    }

}
