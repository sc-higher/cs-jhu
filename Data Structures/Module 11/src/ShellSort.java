/**
 * This program is part of my response to Project 4 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * This program accepts a text file input containing lines of integers to be
 * sorted. The lines from the file are read to an array and sorted using
 * Shell Sort. The gap sequence can be changed by altering the argument in
 * Line 40.
 *
 * @author Sean Connor
 * @date 13 August 2018
 */

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class ShellSort {
    
    public static void main(String[] args) {
        
        // Create object of ShellSort in order to use non-static methods
        ShellSort ss = new ShellSort();
        
        // Read filename from args if valid
        String[] input = ss.readArgs(args);
        String filename = input[0];
        String output_filename = input[1];
        System.out.println("\nFilename: " + filename);
        
        int numLines = ss.fileSize(filename);        
        int[] data = ss.readFile(filename,numLines);

        int[] sequence1 = new int[] {1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524};
        int[] sequence2 = new int[] {1, 5, 17, 53, 149, 373, 1123, 3371, 10111, 30341};
        int[] sequence3 = new int[] {1, 10, 30, 60, 120, 360, 1080, 3240, 9720, 29160};
        int[] sequence4 = new int[] {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096};
        int[] sequence5 = new int[] {1};

        long startTime = System.nanoTime();
        ss.sort(data,sequence5);
        long endTime = System.nanoTime();
        long deltaTime = endTime-startTime;

        for ( int i = 0; i < data.length; i++ ) {
            System.out.println(data[i]);
        }

        System.out.println("Shell Sort Time (ns): " + deltaTime + "\n");
        System.out.println("----------------------------");

        StringBuilder output = ss.arrayToFile(data, deltaTime);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("../output/"+output_filename)))) {
                //new FileOutputStream(output_filename)))) {

            bw.write(output.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }
        
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
     * Runs through the file line by line and parses integer values to an
     * int[] array. Returns int[].
     *
     * @param filename
     * @param size
     * @return
     */
    private int[] readFile(String filename, int size) {
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename))) ) {

            int counter = 0;
            String line;
            int[] data = new int[size];
            
            // Move through input file to parse int to array
            while ( ( line = br.readLine() ) != null ) {

                // Check for empty line
                if ( line.equals("") ) {
                    System.out.println("\nError: Empty line found. Please check input.");
                    System.exit(1);
                }
                
                // Parse int
                data[counter] = Integer.parseInt(line);
                
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
     * Performs the shell sort by iterating through gap values in gap sequence
     * and calling insertion sort at each gap value.
     *
     * @param data
     * @param sequence
     */
    private void sort(int[] data, int[] sequence) {
        
        int size = data.length;
        int gap;
        
        for ( int i = sequence.length - 1; i >= 0; i-- ) {
            
            gap = sequence[i];
            
            if ( gap <= size/2 ) {
                
                insertionSort(data,gap);
                
            }
            
        }
        
    }



    /**
     * Starting at data[gap] and iterating through to end, perform insertion
     * sort using input gap value. Gap value of 1 is a standard insertion
     * sort. If data[current] is less than the preceding (by gap value)
     * value, swap. Else, move on to next value.
     *
     * @param data
     * @param gap
     */
    private void insertionSort(int[] data, int gap) {
        
        int j;
        
        for ( int i = gap; i < data.length; i++ ) {
            
            int temp = data[i];
            
            for (j = i; j >= gap && data[j-gap] > temp; j -= gap) {
                
                data[j] = data[j-gap];

            }
            
            data[j] = temp;
            
        }        
        
    }



    /**
     * Create a string builder object with headers and relevant data appended.
     * Can be used by BufferedWriter to create output file.
     *
     * @param array
     * @param time
     * @return
     */
    private StringBuilder arrayToFile(int[] array, long time) {

        StringBuilder output = new StringBuilder();
        output.append("Shell Sort");
        output.append(System.getProperty("line.separator"));
        output.append("@author Sean Connor");
        output.append(System.getProperty("line.separator"));
        output.append("@date 11 August 2018");
        output.append(System.getProperty("line.separator"));
        output.append(System.getProperty("line.separator"));
        output.append("Calculation Date: " + LocalDateTime.now());
        output.append(System.getProperty("line.separator"));
        output.append("Execution Time (ns): " + time);
        output.append(System.getProperty("line.separator"));
        output.append(System.getProperty("line.separator"));

        for (int i = 0; i < array.length; i++) {

            output.append(array[i]);
            output.append(System.getProperty("line.separator"));

        }

        return output;

    }
    
    
}