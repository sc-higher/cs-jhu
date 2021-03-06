/**
 * This program is part of my response to Project 3 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * This program accepts a text file input containing square matrices of
 * integers and integer values indicating the size of each matrix. The
 * program will calculate the determinant of each matrix. Finally, the
 * program will output each matrix and its determinant into a new text file.
 *
 * @author Sean Connor
 * @date 28 July 2018
 */

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class MatrixDeterminant {

    @SuppressWarnings("unchecked")
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
                // new FileOutputStream("../output/"+output_filename)))) {
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
     * convert String values to Integer values stored in a LinkedList<T>.
     *
     * It will then call the det() method with LinkedList<T> matrix
     * as an argument to compute the determinant.
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
            LinkedList<Double> matrix;
            double result;
            long startTime;
            long endTime;

            // Header for StringBuilder output to be written to txt
            StringBuilder output = new StringBuilder();
            output.append("Matrix Determinant Program");
            output.append(System.getProperty("line.separator"));
            output.append("@author Sean Connor");
            output.append(System.getProperty("line.separator"));
            output.append("@date 28 July 2018");
            output.append(System.getProperty("line.separator"));
            output.append(System.getProperty("line.separator"));
            output.append("Calculation Date: " + LocalDateTime.now());
            output.append(System.getProperty("line.separator"));
            output.append(System.getProperty("line.separator"));

            // Move through input file to identify matrix and pass to det()
            while ( ( line = br.readLine() ) != null ) {

                // Check for empty line
                if ( line.equals("") ) {
                    System.out.println("\nERROR: Empty line found. Please check input.");
                    output.append("\nERROR: Empty line found. Please check input.");
                    return output;
                }

                // Check size specifier
                try {
                    size = Integer.parseInt(line);
                } catch (Exception e) {
                    System.out.println("\nERROR: Invalid size specifier. Please check input.");
                    output.append("\nERROR: Invalid size specifier. Please check input.");
                    return output;
                }

                // Continue evaluation
                matrix = new LinkedList<Double>();
                for (int i = 0; i < size; i++) {
                    line = br.readLine();
                    elements = line.split("\\s+");
                    // Check for square matrix (rows)
                    if ( elements.length != size ) {
                        System.out.println("\nERROR: Matrix not square. Please check input.");
                        output.append("\nERROR: Matrix not square. Please check input.");
                        return output;
                    }
                    // Iterate through elements in row
                    for (int j = 0; j < size; j++) {
                        matrix.insert(Double.parseDouble(elements[j]));
                        System.out.print(elements[j] + " ");
                        output.append(elements[j] + " ");
                    }
                    System.out.println();
                    output.append(System.getProperty("line.separator"));
                }

                // Calculate determinant and time to calculate
                startTime = System.nanoTime();
                result = det1(matrix);
                endTime = System.nanoTime();

                // Output stuff
                System.out.println("Determinant = " + result);
                output.append("Determinant = " + result);
                System.out.println("Time to Result (ns): " + (endTime - startTime));
                output.append(System.getProperty("line.separator"));
                output.append("Time to Result (ns): " + (endTime - startTime));
                output.append(System.getProperty("line.separator"));
                output.append(System.getProperty("line.separator"));
                System.out.println();
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
     * This method utilizes Gaussian Elimination with partial pivoting 
     * to calculate the determinant of the input square matrix.
     *
     * @param matrix A LinkedList of type Double representing a square matrix
     * @return An int value representing the matrix determinant
     */
    private double det1(LinkedList<Double> matrix) {

        int size = (int) Math.sqrt(matrix.size());
        double epsilon = 1e-10; // This is necessary due to way numbers are stored and rounding errors
        double result = 1;
        double temp;

        // Reduce matrix to upper triangular system.
        for ( int i = 0; i < size-1; i++ ) {

            // This implements a partial pivot solution for Naive Gaussian Elimination.
            // Necessary if matrix(i,i) == 0 to avoid division by zero.
            if ( matrix.getValue(i,i) == 0 ) {
                int p;
                for ( p = i + 1; p < size; p++ ) {
                    if ( Math.abs(matrix.getValue(p,i)) > epsilon ) {
                        break;
                    }
                }
                if ( p >= size ) {
                    return 0.0;
                }
                matrix.swapRows(i,p);
                result *= -1;
            }
            
            // "Naive" Gaussian Elimination Method
            for (int j = i + 1; j < size; j++) {

                if ( Math.abs(matrix.getValue(j,i)) > epsilon ) {
                    temp = matrix.getValue(j, i) / matrix.getValue(i, i);

                    for (int k = 0; k < size; k++) {
                        matrix.setValue(j, k, matrix.getValue(j, k) - (temp*matrix.getValue(i,k)));
                    }
                }

            }

        }

        // Determinant is product of diagonal.
        for ( int i = 0; i < size; i++ ) {
            result *= matrix.getValue(i,i);
        }

        // Avoid outputting -0.0 due to way numbers are stored.
        if ( Math.abs(result) < epsilon ) {
            result = Math.abs(result);
        }
        
        return result;

    }

}