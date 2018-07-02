/*
 *  $Id:  Project0.java $
 *
 *  Copyright 2011, The Johns Hopkins University Whiting School of Engineering
 *      All rights reserved.
 *      This material may be used, modified and reproduced by faculty,
 *      staff, and students of The Johns Hopkins University for instruction, 
 *      evaluation, and grading purposes.  For any other permission, please 
 *      contact The Johns Hopkins University Whiting School of Engineering.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  Project 0 is the sample project for the Data Structures class.
 *  <p>It provides an example for students to follow in building their
 *  own programming projects during the semester.
 *  @version    1.0     2011-05-08
 *  @author     W.T. Door
 */
public class Project0 {

    private RuntimeMetric[] metrics;
    private int metricsIndex;

    /**
     *  The constructor initializes internal metrics variables.
     */
    public Project0() {
    
        metrics = new RuntimeMetric[100];
        metricsIndex   = 0;
    }

    /**
     *  Main entry point for the program.
     *  @param args[]   Holds two command line arguments:  the input filename
     *                  and the output filename.
     */
    public static void main (String args[]) {
    
        int     conversions;
        Long    valueObj;
        
        String          binaryText;
        BufferedReader  input;
        BufferedWriter  output;
        String          valueText;
        String          report;
        String          errorMsg;
        Project0        project;
        
        project = new Project0();
        
        //  Check for command line arguments.
        if (args.length != 2) {
            System.out.println("Usage:  java Project0 [input file pathname]" +
                " [output file pathname]");
            System.exit(1);
        }
        
        //  Open the files that will be used for input and output.
        try {
            input = new BufferedReader(new FileReader(args[0]));
            output = new BufferedWriter(new FileWriter(args[1]));
        } catch (Exception ioe) {
            System.err.println(ioe.toString());
            return;
        }

        //  Read, convert, and write the numbers to the outputfile.
        conversions = 0;
        valueObj = null;
        binaryText = "";
        valueText = project.readDecimalNumber(input);
        while (valueText != null) {
            errorMsg = "";
            valueObj = project.parseNumber(valueText);
            if (valueObj == null)
                errorMsg = "Bad format";
            else if (valueObj.longValue() < 0)
                errorMsg = "Negative number";
            else if (valueObj.longValue() > 4294967296L)
                errorMsg = "Over 32 bits";
            else
                binaryText = project.convertNumber(valueObj.longValue());
            if (errorMsg.equals("")) {
                project.writeResult(binaryText, output);
                conversions++;
            } else
                project.writeResult(errorMsg, output);
            valueText = project.readDecimalNumber(input);
        } 
        
        //  End with the results of automated analysis.
        if (conversions == 1)
            report = "Completed " + conversions + " conversion.";
        else
            report = "Completed " + conversions + " conversions.";
        project.writeResult(project.getMetrics(), output);
        
        //  Clean up and return to the operating system.
        try {
            input.close();
            output.close();
        } catch (Exception x) {
            System.err.println(x.toString());
        }
        return;
    }
    
    /**
     *  Reads the next decimal number from the input file.
     *  @param  input A buffered stream from a file that contains one decimal
     *                integer per line.
     *  @return The next decimal integer from the input file.
     */
    private String readDecimalNumber(BufferedReader input) {
    
        String text = "";
        
        try {
            text = input.readLine();
        } catch (IOException iox) {
            System.err.println(iox.toString());
            System.exit(2);
        }
        
        return text;
    }

    /**
     *  Create a long integer object from the input text.
     *  @param text Input string representing a long integer.
     *  @return The number represented by the string or <code>null</code>
     *          if the text cannot be parsed as a long integer.
     */
    private Long parseNumber(String text) {
    
        Long result;

        try {
            result = new Long(text);
        } catch (NumberFormatException nfx) {
            result = null;
        }
        
        return result;
    }
    
    /**
     *  Write a string to the output stream.
     *  @param text   The text to write.
     *  @param output The output stream to write the text to.
     */
    private void writeResult(String text, BufferedWriter output) {
    
        try {
            output.write(text, 0, text.length());  
            output.newLine();
        } catch (IOException iox) {
            System.err.println(iox.toString());
            System.exit(3);
        }
        
        return;
    }
    
    /**
     *  Create and save a calculation metric for later analysis.
     *  @param n           The size of the problem.
     *  @param timeElapsed How long it took to solve the problem.
     */
    private void storeMetrics(long n, long timeElapsed) {
    
        RuntimeMetric item = new RuntimeMetric(n, timeElapsed);
        metrics[metricsIndex] = item;
        metricsIndex++;
        return;
        
    }
    
    /**
     *  Retrieve the stored metrics.
     *  @return A string that contains each metric on a separate line.
     */
    private String getMetrics() {
    
        StringBuilder results;
        
        results = new StringBuilder();
        
        results.append("\n");
        for (int i = 0; i < metricsIndex; i++)
            results.append(metrics[i].getSize() + " = " + 
                metrics[i].getRuntime() + "\n");
        results.append("\n");    
        return results.toString() ;
        
    }
    
    /**
     *  Convert the number while gathering metrics.
     *  @param number The decimal number to convert
     *  @return A 32-bit string representation of the input decimal number.
     *          The string is separated at hexidecimal digits by spaces.
     */
    private String convertNumber(long number) {
    
        long    start;
        long    end;
        long    totalTime;
        int     bits;
        
        String        binaryText;
        BaseConverter bc;
        
        bc = new BaseConverter(32);
        
        start = System.nanoTime();
        binaryText = bc.decToBin(number);
        end = System.nanoTime();
        totalTime = end - start;
        storeMetrics(number, totalTime);
        
        StringBuilder sb = new StringBuilder();
        bits = 0;
        for (int i = 0; i < 32 - binaryText.length(); i++) {
            sb.append("0");
            bits = (bits + 1) % 4;
            if (bits == 0)
            sb.append(" ");
        }
        for (int i = 0; i < binaryText.length(); i++) {
            sb.append(binaryText.substring(i, i+1)); 
            bits = (bits + 1) % 4;
            if (bits == 0)
            sb.append(" ");
        }
        
        return  sb.toString();
                                           
                                           
                                           
    }
}
