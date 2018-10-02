/**
 * This program is part of my response to PA 1 for the class 605.621
 * Foundations of Algorithms at the JHU EPP CS program.
 *
 * @author Sean Connor
 * @date 30 September 2018
 */

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class Driver {

    public static void main(String[] args){

        // Create instance of Driver in order to use instance methods
        Driver d = new Driver();

        // Read filename from args if valid
        String[] input = d.readArgs(args);
        String filename = input[0];
        String output_filename = input[1];
        System.out.println("\nFilename: " + filename);

        // Extract data from input file
        int numLines = d.fileSize(filename);
        int[] data = d.readFile(filename,numLines);


        // All data (large array, small array, and counter) is stored
        // in the 2D int[][] 'set'.
        // set[0] = counter
        // set[1] = large array
        // set[2] = small array
        int size = 1;
        int[][] set = new int[3][];
        set[0] = new int[1];
        set[1] = new int[size];
        set[2] = new int[(int)Math.floor(Math.sqrt(size))];

        // Insert values into data structure.
        long startTime = System.nanoTime();
        for (int i = 0; i < data.length; i++){
            d.insert(data[i],set);
        }
        long endTime = System.nanoTime();
        long deltaTime1 = endTime - startTime;

        // Perform 1 binary search using random value between 0 and n.
        // Calculate time to complete.
        Random rand = new Random();
        int rand_int;
        startTime = System.nanoTime();
        rand_int = rand.nextInt(data.length);
        int index = d.binarySearch(rand_int,set[1],set[2]);
        endTime = System.nanoTime();
        long deltaTime2 = endTime-startTime;

        // Write data to output file.
        StringBuilder output = d.arrayToFile(set[1], deltaTime1, deltaTime2, filename, rand_int, index);
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


        /*
        // Test cases for sortedInsert() and mergeArrays()
        d.sortedInsert(5,set[2]);
        System.out.println(Arrays.toString(set[2]));
        d.sortedInsert(1,set[2]);
        System.out.println(Arrays.toString(set[2]));
        d.sortedInsert(2,set[2]);
        System.out.println(Arrays.toString(set[2]));
        d.sortedInsert(7,set[2]);
        System.out.println(Arrays.toString(set[2]));
        d.sortedInsert(6,set[2]);
        System.out.println(Arrays.toString(set[2]));

        set[1] = d.mergeArrays(set[2],set[1]);
        System.out.println(Arrays.toString(set[1]));
        */

        /*
        // Test cases for insert()
        d.insert(5,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(1,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(2,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(7,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(6,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(9,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(5,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(1,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(2,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(7,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(6,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(9,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(14,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(0,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(1,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        d.insert(27,set);
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));
        */

        /*
        // Test cases for binary search
        System.out.println(Arrays.toString(set[2])+" ; "+Arrays.toString(set[1]));

        System.out.println("Index of 0: " + d.binarySearch(0,set[1],set[2]));
        System.out.println("Index of 49: " + d.binarySearch(49,set[1],set[2]));
        System.out.println("Index of 51: " + d.binarySearch(51,set[1],set[2]));
        */

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
     * Create a string builder object with headers and relevant data appended.
     * Can be used by BufferedWriter to create output file.
     *
     * @param array
     * @param time
     * @return
     */
    private StringBuilder arrayToFile(int[] array, long time1, long time2, String filename, int term, int index) {

        StringBuilder output = new StringBuilder();
        output.append("Programming Assignment 1 - Output");
        output.append(System.getProperty("line.separator"));
        output.append("@author Sean Connor");
        output.append(System.getProperty("line.separator"));
        output.append("@date 1 October 2018");
        output.append(System.getProperty("line.separator"));
        output.append(System.getProperty("line.separator"));
        output.append("Filename: " + filename);
        output.append(System.getProperty("line.separator"));
        output.append("Calculation Date: " + LocalDateTime.now());
        output.append(System.getProperty("line.separator"));
        output.append("Insert Time (ns): " + time1);
        output.append(System.getProperty("line.separator"));
        output.append("Search Time (ns): " + time2);
        output.append(System.getProperty("line.separator"));
        output.append(System.getProperty("line.separator"));
        output.append("Searched for: " + term);
        output.append(System.getProperty("line.separator"));
        output.append("Found at index: " + index);
        output.append(System.getProperty("line.separator"));
        output.append(System.getProperty("line.separator"));

        for (int i = 0; i < array.length; i++) {

            output.append(array[i]);
            output.append(System.getProperty("line.separator"));

        }

        return output;

    }


    /**
     * This method inserts an integer value into a sorted integer array and
     * maintains the sorted status
     *
     * @param value
     * @param arr
     */
    private void sortedInsert(int value, int[] arr){
        for (int i = 0; i < arr.length; i++){
            if (value < arr[i]){
                for(int j = 1; j <= i; j++){
                    arr[j-1] = arr[j];
                }
                arr[i-1] = value;
                break;
            }

            else if (i == arr.length-1){
                for(int j = 1; j <= i; j++){
                    arr[j-1] = arr[j];
                }
                arr[i] = value;
            }
        }
    }


    /**
     * This method merges two sorted arrays into a third array of size m+n and
     * returns it.
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private int[] mergeArrays(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length+arr2.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < arr1.length && j < arr2.length){
            if (arr1[i] < arr2[j]){
                result[k] = arr1[i];
                i++;
                k++;
            }
            else {
                result[k] = arr2[j];
                j++;
                k++;
            }
        }

        while (i < arr1.length){
            result[k] = arr1[i];
            i++;
            k++;
        }

        while (j < arr2.length){
            result[k] = arr2[j];
            j++;
            k++;
        }

        return result;
    }


    /**
     * This is the primary insert method that utilizes sortedInsert() and
     * merge(). It relies on a counter to determine when to merge. When the
     * smaller array is filled, merge() will be called. The new larger array
     * will be the combined sorted array and the smalled array will be an empty
     * array with a size equal to the sqaure root of the larger array.
     *
     * @param value
     * @param set
     */
    private void insert(int value, int[][] set){
        // Insert value into small array and increment counter
        sortedInsert(value,set[2]);
        set[0][0]++;

        // If counter (set[0][0]) equals size of small array, small array is
        // full and need to merge arrays, reallocate, and reset counter.
        if (set[0][0] == set[2].length){
            set[0][0] = 0;
            set[1] = mergeArrays(set[1],set[2]);
            set[2] = new int[(int)Math.floor(Math.sqrt(set[1].length))];
        }
    }


    /**
     * This is a binary search method. It performs binary search on the larger
     * array first and the smaller array second. If the key is not found, the
     * method returns -1.
     *
     * @param value
     * @param data
     * @return
     */
    private int binarySearch(int value, int[] large, int[] small){
        // Check large array first
        int low = 0;
        int high = large.length-1;
        int mid;
        while (low <= high){
            mid = (int) Math.floor((low+high)/2);
            if (value < large[mid]){
                high = mid - 1;
            }
            else if (value > large[mid]){
                low = mid + 1;
            }
            else {
                return mid;
            }
        }

        // Check small array second
        low = 0;
        high = small.length-1;
        while (low <= high){
            mid = (int) Math.floor((low+high)/2);
            if (value < small[mid]){
                high = mid - 1;
            }
            else if (value > small[mid]){
                low = mid + 1;
            }
            else {
                return mid;
            }
        }

        // If not found, return -1
        return -1;
    }


}
