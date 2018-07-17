/**
 * This program is part of my response to Project 2 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * This program accepts a text file input containing square matrices of
 * integers and integer values indicating the size of each matrix. The
 * program will calculate the determinant of each matrix. Finally, the
 * program will output each matrix and its determinant into a new text file.
 *
 * @author Sean Connor
 * @date 15 July 2018
 */

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class MatrixDeterminant {

    public static void main(String[] args) {
        // create object of MatrixDeterminant in order to use non-static methods
        MatrixDeterminant md = new MatrixDeterminant();

        // read filename from args if valid
        String[] data = md.readArgs(args);
        String filename = data[0];
        String output_filename = data[1];
        System.out.println("\nFilename: " + filename + "\n");

        // Process input file and calculate matrix determinants
        StringBuilder output = md.evaluateStrings(filename);

        // Create BufferedWriter object and write to txt file
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("../output/"+output_filename)))) {

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
     * This method moves through the input file line by line to read matrix
     * size information and the matrices themselves. For each matrix, it will
     * convert String values to Integer values stored in a 2d int[][] array.
     *
     * It will then call the det(matrix[][]) method with the matrix as an
     * argument to compute the determinant.
     *
     * The matrix information and determinant are printed to console and added
     * to a StringBuilder object, which is returned.
     *
     * @param filename Name of the file to be evaluated
     * @return A StringBuilder object containing the information to be written
     *         to txt file.
     */
    private StringBuilder evaluateStrings(String filename) {
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename))) ) {

            String line;
            int k = 0;
            int size = 0;
            String[] elements;
            int[][] matrix;
            int result;
            long startTime;
            long endTime;

            // Header for StringBuilder output to be written to txt
            StringBuilder output = new StringBuilder();
            output.append("Matrix Determinant Program");
            output.append(System.getProperty("line.separator"));
            output.append("@author Sean Connor");
            output.append(System.getProperty("line.separator"));
            output.append("@date 15 July 2018");
            output.append(System.getProperty("line.separator"));
            output.append(System.getProperty("line.separator"));
            output.append("Calculation Date: " + LocalDateTime.now());
            output.append(System.getProperty("line.separator"));
            output.append(System.getProperty("line.separator"));

            // Move through input file to identify matrix and pass to det()
            while ( ( line = br.readLine() ) != null ) {
                
                // Check for empty line
                if ( line.equals("") ) {
                    System.out.println("\nError: Empty line found. Please check input.");
                    System.exit(1);
                }
                
                // Check size specifier
                try {
                    size = Integer.parseInt(line);
                } catch (Exception e) {
                    System.out.println("\nError: Invalid size specifier. Please check input.");
                    System.exit(1);
                }
                
                // Continue evaluation
                matrix = new int[size][size];
                for (int i = 0; i < size; i++) {
                    line = br.readLine();
                    elements = line.split("\\s+");
                    // Check for square matrix (rows)
                    if ( elements.length != size ) {
                        System.out.println("\nError: Matrix not square. Please check input.");
                        System.exit(1);
                    }
                    // Iterate through elements in row
                    for (int j = 0; j < size; j++) {
                        matrix[i][j] = Integer.parseInt(elements[j]);
                        System.out.print(elements[j] + " ");
                        output.append(elements[j] + " ");
                    }
                    System.out.println();
                    output.append(System.getProperty("line.separator"));
                }

                // Calculate determinant and time to calculate
                startTime = System.nanoTime();
                result = det(matrix);
                endTime = System.nanoTime();                

                // Output stuff
                System.out.println();
                System.out.println("Determinant = " + result);
                output.append("Determinant = " + result);
                System.out.println();
                System.out.println("Time to Result (ms): " + (endTime - startTime));
                output.append(System.getProperty("line.separator"));
                output.append("Time to Result (ns): " + (endTime - startTime));
                output.append(System.getProperty("line.separator"));
                output.append(System.getProperty("line.separator"));
            }

            return output;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }

        return null;

    }



    /**
     * This method recursively calculates the determinant of the input
     * square matrix.
     *
     * @param matrix A 2d int[][] array representing a square matrix
     * @return An int value representing the matrix determinant
     */
    private int det(int[][] matrix) {
        int size = matrix[0].length;
        int sum = 0;
        int[][] sub;
        int sign;

        // Recursive base case
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

            // Increase running sum. Recursively call det().
            sum = sum + sign * matrix[0][k] * (det(sub));
        }

        // Returns the sum. When recursion is finished, returns final determinant.
        return sum;
    }

}