/**
 * This program is part of my response to Project 2 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 *
 * @author Sean Connor
 * @date 15 July 2018
 */

import java.io.*;
import java.util.*;

public class MatrixDeterminant {

    public static void main(String[] args) {
        // Set start time
        final long startTime = System.currentTimeMillis();

        // create object of MatrixDeterminant in order to use non-static methods
        MatrixDeterminant md = new MatrixDeterminant();

        // read filename from args if valid
        String[] data = md.readArgs(args);
        String filename = data[0];
        String output_filename = data[1];
        System.out.println("\nFilename: " + filename + "\n");

        //
        md.evaluateStrings(filename);

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
                // lazy file exists test
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
     *
     * @param filename
     */
    private void evaluateStrings(String filename) {
        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename))) ) {

            String line;
            int k = 0;
            int size;
            String[] elements;
            int[][] matrix;
            int result;

            while ( ( line = br.readLine() ) != null ) {
                size = Integer.parseInt(line);
                matrix = new int[size][size];
                for (int i = 0; i < size; i++) {
                    line = br.readLine();
                    elements = line.split("\\s+");
                    for (int j = 0; j < size; j++) {
                        matrix[i][j] = Integer.parseInt(elements[j]);
                        System.out.print(elements[j] + " ");
                    }
                    System.out.println();
                }
                result = det(matrix);
                System.out.println();
                System.out.println("Determinant = " + result);
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }
    }



    /**
     *
     * @param matrix
     * @return
     */
    private int det(int[][] matrix) {
        int size = matrix[0].length;
        int sum = 0;
        int[][] sub;
        int sign;

        // recursive base case
        if ( size == 1 ){
            return matrix[0][0];
        }

        // For a matrix of size n, there will be n submatrices. Iterate through
        // all of these.
        for(int k=0; k<size; k++) {
            sub = new int[size - 1][size - 1];

            // Submatrices are formed from elements below the first row.
            // For this reason, start at i = 1.
            for (int i = 1; i < size; i++) {

                // Move through all columns of input matrix
                for (int j = 0; j < size; j++) {

                    if ( j < k ) {
                        sub[i-1][j] = matrix[i][j];
                    }
                    else if ( j > k ) {
                        sub[i-1][j-1] = matrix[i][j];
                    }
                    // if ( j == k ) --> do nothing
                }
            }

            // Calculate sign for each matrix to follow the order
            // [1] - [2] + [3] - [4] ...
            if (k % 2 == 0) {
                sign = 1;
            } else {
                sign = -1;
            }

            // Increases the running total
            sum = sum + sign * matrix[0][k] * (det(sub));

        }

        // Returns the sum. When recursion is finished, returns final determinant.
        return sum;

    }

}