/**
 * This program is part of my response to Project 1 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * This program will evaluate a file containing a number of lines of strings to
 * see if each line conforms to a certain language (described in attached PDF
 * file outlining the project). For each line, the program will determine what
 * language, if any, applies to it and then will write this information to a new
 * file of the specified name.
 *
 * This program requires two arguments - input filename and output filename. To
 * run the program, enter:  java StringEvalRec [inputFile] [outputfile].
 *
 * Note: This program is identical to StringEval except that this contains
 * the recursive type 1 method.
 *
 * Requirement - You need to read the characters in and parse them as you read
 * them. The stack is not intended to hold the input, it is to be used to
 * facilitate the parsing.
 *
 * @author Sean Connor
 * @date 30 June 2018
 */

import java.io.*;
import java.util.*;

public class StringEvalRec {

    public static void main(String[] args) {
        // Set start time
        final long startTime = System.currentTimeMillis();

        // create object of StringEval in order to use non-static methods
        StringEvalRec se = new StringEvalRec();

        // read filename from args if valid
        String[] data = se.readArgs(args);
        String filename = data[0];
        String output_filename = data[1];
        System.out.println("\nFilename: " + filename);

        // perform all language checks and write information to output_filename
        se.writeData(filename, output_filename);

        // Set finish time and display difference
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time (ms): " + (endTime - startTime));
    }


    /**
     * Method to ensure the proper number of arguments is given, and that the
     * filename given as an argument actually exists.
     *
     * @param args
     * @return   A string representing the filename to be read     *
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
     * Old method that is not used. Originally intended to view results in
     * console, before a method was created to output results to file and to
     * console. Calls all evaluation methods (type1, type2, etc) and prints
     * type if true for each line in test file (each string).
     *
     * @param filename
     */
    private void evaluateStrings(String filename) {
        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename))) ) {
            String line;
            int i = 0;

            while ( ( line = br.readLine() ) != null ) {
                System.out.println("\nLine " + i + ": " + line);
                i++;
                if (type1(line) || type2(line) || type3(line) || type4(line) ||
                        type5(line)) {
                    if (type1(line)) {
                        System.out.println("Type 1");
                    }
                    if (type2(line)) {
                        System.out.println("Type 2");
                    }
                    if (type3(line)) {
                        System.out.println("Type 3");
                    }
                    if (type4(line)) {
                        System.out.println("Type 4");
                    }
                    if (type5(line)) {
                        System.out.println("Type 5");
                    }
                }
                else {
                    System.out.println("No type");
                }
            }
            System.out.println("\nEnd");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }
    }


    /**
     * Method to check for language type 1.
     *
     * @param line   A string to be checked if posseses language type
     * @return   boolean value true if type 1, false otherwise
     */
    private boolean type1(String line) {
        //
        if (line.isEmpty()) {
            return true;
        }

        //
        String lc;
        Stack stack = new Stack(500);

        // Iterate through string
        for (int i = 0; i < line.length(); i++) {
            lc = String.valueOf(line.charAt(i));

            if (!lc.equals("A") && !lc.equals("B")) {
                return false;
            }

            else if ( stack.isEmpty() ) {
                stack.push(lc);
            }

            else {
                if (lc.equals(stack.peek())) {
                    stack.push(lc);
                }
                else {
                    stack.pop();
                }
            }
        }

        //
        if ( stack.isEmpty() ) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /**
     * Recursive method to check for language type 1.
     *
     * @param line   A string to be checked if posseses language type
     * @return   boolean value true if type 1, false otherwise
     */
    private boolean type1rec(String line, Stack stack, String lc) {
        // check if string is empty
        // redundant recursively
        if (line.isEmpty()) {
            return true;
        }

        // check if there are any characters beside A and B in string
        // again, redundant recursively
        for (int i = 0; i < line.length(); i++) {
            lc = String.valueOf(line.charAt(i));

            if (!lc.equals("A") && !lc.equals("B")) {
                return false;
            }
        }

        // set lc to first char in string
        lc = String.valueOf(line.charAt(0));

        // push if stack empty or if same as top of stack
        // pop otherwise
        if (stack.isEmpty()) {
            stack.push(lc);
        } else {
            if (stack.peek().equals(lc)) {
                stack.push(lc);
            } else {
                stack.pop();
            }
        }

        // recursively iterate through string by using substring to 'remove'
        // first character
        if (line.length() > 1) {
            line = line.substring(1,line.length());
            type1rec(line, stack, lc);
        }

        // at this point, all stack operations will have been performed after
        // iterating through entire string
        // redundant for all but first case
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method to check for language type 2.
     *
     * @param line   A string to be checked if posseses language type
     * @return   boolean value true if type 2, false otherwise
     */
    private boolean type2(String line) {
        // Make sure string is not empty
        if (line.isEmpty()) {
            return false;
        }

        // Make sure first character in string is "A"
        if(!String.valueOf(line.charAt(0)).equals("A")) {
            return false;
        }

        // Make sure last character in string is "B"
        int end = line.length()-1;
        if(!String.valueOf(line.charAt(end)).equals("B")) {
            return false;
        }

        // Once past initial tests, declare objects
        String lc;
        Stack stack = new Stack(500);

        // Push first character ("A") to stack
        lc = String.valueOf(line.charAt(0));
        stack.push(lc);

        // Iterate through string
        for (int i = 1; i < line.length(); i++) {
            lc = String.valueOf(line.charAt(i));

            if (!lc.equals("A") && !lc.equals("B")) {
                return false;
            }

            else if (stack.isEmpty()) {
                return false;
            }

            else {
                if (lc.equals(stack.peek())) {
                    stack.push(lc);
                }
                else {
                    stack.pop();
                }
            }
        }

        //
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method to check for language type 3.
     *
     * @param line   A string to be checked if posseses language type
     * @return   boolean value true if type 3, false otherwise
     */
    private boolean type3(String line) {
        // Make sure string is not empty
        if (line.isEmpty()) {
            return false;
        }

        // Make sure first character in string is "A"
        if(!String.valueOf(line.charAt(0)).equals("A")) {
            return false;
        }

        // Make sure last character in string is "B"
        int end = line.length()-1;
        if(!String.valueOf(line.charAt(end)).equals("B")) {
            return false;
        }

        // Once past initial tests, declare objects
        String lc;
        Stack stack = new Stack(500);

        // Push first character ("A") to stack
        lc = String.valueOf(line.charAt(0));
        stack.push(lc);
        stack.push(lc);

        // Iterate through string
        for (int i = 1; i < line.length(); i++) {
            lc = String.valueOf(line.charAt(i));

            if (!lc.equals("A") && !lc.equals("B")) {
                return false;
            }

            else if (stack.isEmpty()) {
                return false;
            }

            else {
                if (lc.equals(stack.peek())) {
                    stack.push(lc);
                    stack.push(lc);
                }
                else {
                    stack.pop();
                }
            }
        }

        //
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * Method to check for language type 4.
     *
     * @param line   A string to be checked if posseses language type
     * @return   boolean value true if type 4, false otherwise
     */
    private boolean type4(String line) {
        // Make sure string is not empty
        if (line.isEmpty()) {
            return false;
        }

        // Make sure first character in string is "A"
        if(!String.valueOf(line.charAt(0)).equals("A")) {
            return false;
        }

        // Make sure last character in string is "B"
        int end = line.length()-1;
        if(!String.valueOf(line.charAt(end)).equals("B")) {
            return false;
        }

        // Once past initial tests, declare objects
        String lc;
        Stack stack1 = new Stack(500);
        Stack stack2 = new Stack(500);

        // Set first grouping pattern and initial fill stack2
        for (int i = 0; i < line.length(); i++) {
            lc = String.valueOf(line.charAt(i));

            if ("B".equals(stack1.peek()) && lc.equals("A")) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                break;
            } else {
                stack1.push(lc);
            }
        }

        if (!stack1.isEmpty() && stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        // Iterate through string, pushing/popping back and forth between
        // stack1 and stack2
        for (int i = 0; i < line.length(); i++) {
            lc = String.valueOf(line.charAt(i));

            if (!lc.equals("A") && !lc.equals("B")) {
                return false;
            }

            if ("B".equals(stack1.peek()) && lc.equals("A")) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }

            if (lc.equals(stack2.peek())) {
                stack1.push(stack2.pop());
            } else {
                return false;
            }
        }

        //
        if (stack2.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Method to check for language type 5.
     * Language type 5 is a non-trivial language of my choice
     * L5 = {w: w is of the form AnBmAn for some n,m > 0}
     *
     * @param line   A string to be checked if posseses language type
     * @return   boolean value true if type 5, false otherwise
     */
    private boolean type5(String line) {
        // Make sure string is not empty
        if (line.isEmpty()) {
            return false;
        }

        // Once past initial tests, declare objects
        String lc;
        Stack stack1 = new Stack(500);
        Stack stack2 = new Stack(500);
        Stack stackB = new Stack(500);
        Stack stackM = new Stack(500);

        // Make sure all characters in string are either A or B
        for (int i = 0; i < line.length(); i++) {
            lc = String.valueOf(line.charAt(i));
            if (!lc.equals("A") && !lc.equals("B")) {
                return false;
            }
        }

        // Make sure there is at least one B
        for (int i = 0; i < line.length(); i++) {
            lc = String.valueOf(line.charAt(i));
            if (lc.equals("B")) {
                stackB.push(lc);
            }
        }
        if (stackB.isEmpty()) {
            return false;
        }

        //
        int i = 0;
        int j = line.length()-1;
        String lci = String.valueOf(line.charAt(i));
        String lcj = String.valueOf(line.charAt(j));

        if (lci.equals("B") || lcj.equals("B")) {
            return false;
        }

        while (!lci.equals("B")) {
            stack1.push(lci);
            i++;
            lci = String.valueOf(line.charAt(i));
        }

        while (!lcj.equals("B")) {
            stack2.push(lcj);
            j--;
            lcj = String.valueOf(line.charAt(j));
        }

        while (i <= j) {
            lc = String.valueOf(line.charAt(i));
            if (!lc.equals("B")) {
                return false;
            }
            i++;
        }

        while (!stack1.isEmpty()) {
            if (stack2.isEmpty()) {
                return false;
            } else {
                stack1.pop();
                stack2.pop();
            }
        }

        //
        if (stack2.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * This method will perform two primary actions. First, it will write all
     * information to the terminal for quick and easy viewing. Second, it will
     * use BufferedWriter in conjunction with StringBuilder to create an output
     * file with the same information (language classification, etc).
     *
     * @param input_filename
     * @param output_filename
     */
    private void writeData(String input_filename, String output_filename)
    {
        // First, write all information to terminal
        try ( BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(input_filename))) ) {
            String line;
            int i = 0;

            while ( ( line = br.readLine() ) != null ) {
                System.out.println("\nLine " + i + ": " + line);
                i++;

                Stack stack = new Stack(500);
                String lc = "";
                if (type1(line) || type1rec(line,stack,lc) || type2(line) ||
                        type3(line) || type4(line) || type5(line)) {
                    if (type1(line)) {
                        System.out.println("Type 1");
                    }
                    if (type1rec(line,stack,lc)) {
                        System.out.println("Type 1 RECURSIVE");
                    }
                    if (type2(line)) {
                        System.out.println("Type 2");
                    }
                    if (type3(line)) {
                        System.out.println("Type 3");
                    }
                    if (type4(line)) {
                        System.out.println("Type 4");
                    }
                    if (type5(line)) {
                        System.out.println("Type 5");
                    }
                }
                else {
                    System.out.println("No type");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }

        // Second, write all information to the specified filename
        try ( BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("output/"+output_filename))) )
        {
            StringBuilder sb = new StringBuilder();

            sb.append("@author Sean Connor");
            sb.append(System.getProperty("line.separator"));
            sb.append("@date 30 June 2018");
            sb.append(System.getProperty("line.separator"));
            sb.append(System.getProperty("line.separator"));
            sb.append("L1 = { w: w contains equal numbers of A's and B's " +
                    "(in any order) and no other characters}");
            sb.append(System.getProperty("line.separator"));
            sb.append("L2 = { w: w is of the form AnBn, for some n > 0 }");
            sb.append(System.getProperty("line.separator"));
            sb.append("L3 = { w: w is of the form AnB2n, for some n > 0 }");
            sb.append(System.getProperty("line.separator"));
            sb.append("L4 = { w: w is of the form (AnBm)p, for some m,n,p > 0 }");
            sb.append(System.getProperty("line.separator"));
            sb.append("L5 = { w: w is of the form AnBmAn for some n,m > 0 }");
            sb.append(System.getProperty("line.separator"));
            sb.append("L6 = { w: w is of the form ... }");
            sb.append(System.getProperty("line.separator"));
            sb.append(System.getProperty("line.separator"));

            try ( BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(input_filename))) ) {
                String line;
                int i = 0;

                while ( ( line = br.readLine() ) != null ) {
                    sb.append("Line " + i + ": " + line);
                    i++;
                    
                    Stack stack = new Stack(500);
                    String lc = "";
                    if (type1(line) || type1rec(line,stack,lc) || type2(line) ||
                            type3(line) || type4(line) || type5(line)) {
                        if (type1(line)) {
                            sb.append(System.getProperty("line.separator"));
                            sb.append("Type 1");
                        }
                        if (type1rec(line,stack,lc)) {
                            sb.append(System.getProperty("line.separator"));
                            sb.append("Type 1 Recursive");
                        }
                        if (type2(line)) {
                            sb.append(System.getProperty("line.separator"));
                            sb.append("Type 2");
                        }
                        if (type3(line)) {
                            sb.append(System.getProperty("line.separator"));
                            sb.append("Type 3");
                        }
                        if (type4(line)) {
                            sb.append(System.getProperty("line.separator"));
                            sb.append("Type 4");
                        }
                        if (type5(line)) {
                            sb.append(System.getProperty("line.separator"));
                            sb.append("Type 5");
                        }
                    }
                    else {
                        sb.append(System.getProperty("line.separator"));
                        sb.append("No type");
                    }
                    sb.append(System.getProperty("line.separator"));
                    sb.append(System.getProperty("line.separator"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("\n\nFile not found. Please try again.\n");
                System.out.println();
                System.exit(1);
            }

            System.out.println("\nWriting to output file...");
            bw.write(sb.toString());
            System.out.println("Done.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}