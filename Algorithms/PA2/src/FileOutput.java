/**
 * This program is part of my response to PA 2 for the class 605.621
 * Foundations of Algorithms at the JHU EPP CS program.
 *
 * @author Sean Connor
 * @date 28 October 2018
 */

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FileOutput {

    private String output_filename;

    public FileOutput(String output_filename){
        this.output_filename = output_filename;
    }

    StringBuilder output = new StringBuilder();


    /**
     * Appends an object (i.e. String) to the StringBuilder object.
     *
     * @param object
     */
    public void append(Object object) {
        output.append(object);
        output.append(System.getProperty("line.separator"));
    }


    /**
     * Appends an int[] array to the Stringbuilder object line by line.
     *
     * @param array
     */
    public void appendArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            output.append(array[i]);
            output.append(System.getProperty("line.separator"));
        }
    }


    /**
     * Appends a header to the StrignBuilder object.
     *
     * @param title
     */
    public void appendHeader(String title) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        output.append(title);
        output.append(System.getProperty("line.separator"));
        output.append("@author Sean Connor");
        output.append(System.getProperty("line.separator"));
        output.append("@date " + dateFormat.format(date));
        output.append(System.getProperty("line.separator"));
        output.append(System.getProperty("line.separator"));
    }


    /**
     * Calling this method simply writes the constructed StringBuilder
     * object to a text file.
     */
    public void write() {

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("../output/"+output_filename)))) {
            bw.write(output.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }

    }

}
