/**
 * This program is part of my response to Assignment 10 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This program requires two or three arguments - (1) the data source file path,
 * (2) the destination file path, and (3) the number of records in the data file
 * (optional).
 *
 * The program requires data source file to be a text file of specific format.
 * The program will output a csv-style file to the same directory.
 *
 * @author: Sean Connor
 */

import java.io.*;
import java.lang.StringBuilder;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class FormatData
{
    public static void main(String args[])
    {
        FormatData test = new FormatData();

        String filename = null;
        String output_filename = null;
        int number_records = 0;

        //Ensure that the required number of arguments are provided
        if (args.length == 3)
        {
            filename = args[0];
            output_filename = args[1];
            String temp_number_records = args[2];
            number_records = Integer.parseInt(temp_number_records);
        }

        else if (args.length == 2)
        {
            filename = args[0];
            output_filename = args[1];
            number_records = test.countLines(filename);
        }

        else
        {
            System.out.println("\nMissing required arguments. " +
                    "Please try again.\n");
        }
        

        //Parse all required data from input file and store type (i.e. state,
        //population, etc) in separate String arrays.
        String[] state =
                test.toStringArray(filename,number_records,0,2);
        String[] population =
                test.toStringArray(filename,number_records,82,90);
        String[] child_population =
                test.toStringArray(filename,number_records,91,99);
        String[] child_poverty_population =
                test.toStringArray(filename,number_records,100,108);

        //Create array of unique state code identifiers and sum data
        //(population, etc) by state. Store in separate long[].
        long[] distinct_state_codes = test.distinctStateCodeArray(
                state,number_records);
        long[] summed_population = test.sumStateData(
                state,population,number_records);
        long[] summed_child_population = test.sumStateData(
                state,child_population,number_records);
        long[] summed_child_poverty_population = test.sumStateData(
                state,child_poverty_population,number_records);

        double[] summed_percent_child_poverty = new double[
                summed_population.length];

        String[] percent_child_poverty = new String[
                summed_child_population.length];

        DecimalFormat df = new DecimalFormat("#.00");

        for (int i = 0; i < summed_population.length; i++)
        {
            summed_percent_child_poverty[i] =
                    (( (double) summed_child_poverty_population[i] /
                            (double) summed_child_population[i]) * 100);

            percent_child_poverty[i] = df.format(
                    summed_percent_child_poverty[i]);
        }

        //Write all data csv-style to output file
        test.writeData(output_filename,
                distinct_state_codes,
                summed_population,
                summed_child_population,
                summed_child_poverty_population,
                percent_child_poverty);

    }


    /**
     * Method to accept a txt file as input and return an int value
     * representing the number of lines in the file.
     *
     * @param filename   A text-style file (i.e .txt .csv)
     * @return   int variable representing number of lines the the file
     */
    private int countLines(String filename)
    {
        int lines = 0;

        try ( BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                    new FileInputStream(filename))) )
        {
            //Use while-loop to count the number of lines in

            while (br.readLine() != null)
            {
                lines++;
            }
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lines;
    }


    /**
     * Method parses data line by line from text-style file 'filename' by
     * adding text from substring_start to substring_stop to String[]. Returns
     * the String[].
     *
     * @param filename
     * @param number_records
     * @param substring_start
     * @param substring_stop
     * @return
     */
    private String[] toStringArray(String filename,
                                int number_records,
                                int substring_start,
                                int substring_stop)
    {
        String[] data_array = new String[number_records];

        try ( BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                    new FileInputStream(filename))) )
        {
            String line;

            for (int i = 0; i < number_records; i++)
            {
                line = br.readLine();
                data_array[i] = line.substring(substring_start, substring_stop);
            }

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return data_array;
    }


    /**
     * Method will receives two arrays of equal length, state[] and interest[],
     * with state[i] corresponding to data from interest[i]. The method will
     * iterate through a for-loop of length number_records and check if value of
     * current state index is equal to value of previous state index. If so,
     * the value of interest[i] will be added to a summation array summed_data
     * with index values corresponding to individual states. If not, then the
     * summed_data index will be incremented by one to indicate a different
     * state and the tally process will resume again.
     *
     * @param state
     * @param interest
     * @param number_records
     * @return
     */
    private long[] sumStateData(String[] state,
                                  String[] interest,
                                  int number_records)
    {
        int state_count = 0;
        long[] summed_data = new long[51];

        summed_data[state_count] = Long.parseLong(interest[0].trim());

        for (int i = 1; i < number_records; i++)
        {
            if ( state[i].equals(state[i-1]) )
            {
                summed_data[state_count] = summed_data[state_count] +
                        Long.parseLong(interest[i].trim());

            }

            else
            {
                state_count++;
                summed_data[state_count] = Long.parseLong(interest[i].trim());
            }
        }

        return summed_data;
    }


    /**
     * Method returns a long[] of unique state code identifiers
     *
     * @param state
     * @param number_records
     * @return
     */
    private long[] distinctStateCodeArray(String[] state,
                                      int number_records)
    {
        int state_count = 0;
        long[] summed_data = new long[51];

        summed_data[state_count] = Long.parseLong(state[0]);

        for (int i = 1; i < number_records; i++)
        {
            if ( state[i].equals(state[i-1]) )
            {

            }

            else
            {
                state_count++;
                summed_data[state_count] = Long.parseLong(state[i]);
            }
        }

        return summed_data;
    }


    /**
     * Method writes data in csv-style to specified output file
     *
     * @param output_filename
     * @param field1
     * @param field2
     * @param field3
     * @param field4
     * @param field5
     */
    private void writeData(String output_filename,
                           long[] field1,
                           long[] field2,
                           long[] field3,
                           long[] field4,
                           String[] field5)
    {
        try ( BufferedWriter bw = new BufferedWriter(
                                    new OutputStreamWriter(
                                    new FileOutputStream(output_filename))) )
        {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < field2.length; i++)
            {
                sb.append(field1[i]);
                sb.append(",");
                sb.append(field2[i]);
                sb.append(",");
                sb.append(field3[i]);
                sb.append(",");
                sb.append(field4[i]);
                sb.append(",");
                sb.append(field5[i]);
                sb.append("\n");
            }
            bw.write(sb.toString());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}