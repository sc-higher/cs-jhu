/**
 * This program is part of my response to Project 4 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * This program accepts a text file input containing lines of integers to be
 * sorted. The lines from the file are read to an array and sorted using
 * Heap Sort.
 *
 * @author Sean Connor
 * @date 13 August 2018
 */

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class HeapSort {
    
    public static void main(String[] args) {
        
        // Create object of ShellSort in order to use non-static methods
        HeapSort hs = new HeapSort();
        
        // Read filename from args if valid
        String[] input = hs.readArgs(args);
        String filename = input[0];
        String output_filename = input[1];
        System.out.println("\nFilename: " + filename);
        
        int numLines = hs.fileSize(filename);        
        int[] data = hs.readFile(filename,numLines);
        
        long startTime = System.nanoTime();
        hs.sort(data);
        long endTime = System.nanoTime();
        long deltaTime = endTime-startTime;

        for ( int i = 0; i < data.length; i++ ) {
            System.out.println(data[i]);
        }

        System.out.println("Heap Sort Time (ns): " + deltaTime + "\n");
        System.out.println("----------------------------");


        StringBuilder output = hs.arrayToFile(data, deltaTime);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("../output/"+output_filename)))) {
                // new FileOutputStream(output_filename)))) {

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
     * Primary method to heap sort the data. First creates a max heap. Then
     * iterates through the array swapping max value to back of array and then
     * heapifying to get a new max.
     *
     * @param data
     */
    private void sort(int[] data) {

        toMaxHeap(data);

        int i = data.length - 1;
        while ( i > 0 ) {

            swap(data,0,i);
            heapify(data,0,i);
            i--;

        }

    }



    /**
     * Creates a max heap by iterating through all nodes with children,
     * starting with the bottom-most, right-most node, and heapifying.
     *
     * @param data
     */
    private void toMaxHeap(int[] data) {
        
        int start = data.length/2;

        while ( start >= 0 ) {

            heapify(data,start,data.length);
            start--;

        }
        
    }



    /**
     * Heapifies the subtree with root node "index". If one child is larger,
     * swaps with that child and continue down. If both children are larger,
     * swap with larger child and continue down. If neither are larger, or
     * if children don't exist, return.
     *
     * @param data
     * @param index
     * @param end
     */
    private void heapify(int[] data, int index, int end) {

        int size = end;
        int temp;
        int left;
        int right;

        while ( index < size ) {

            temp = index;
            left = 2*index;
            right = left + 1;


            if ( left < size && data[left] > data[temp] ) {

                temp = left;

            }

            if ( right < size && data[right] > data[temp] ) {

                temp = right;

            }

            if ( temp == index ) {

                return;

            }

            swap(data,temp,index);

            index = temp;

        }
        
    }



    /**
     * Swap two values in array.
     *
     * @param data
     * @param item1
     * @param item2
     */
    private void swap(int[] data, int item1, int item2) {
        
        int temp = data[item2];
        data[item2] = data[item1];
        data[item1] = temp;
        
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
        output.append("Heap Sort");
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