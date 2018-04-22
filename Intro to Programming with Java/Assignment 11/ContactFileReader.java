/**
 * This program is part of my response to Assignment 11 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This class contains methods which allow a csv-style contacts list of
 * appropriate format to be imported, with each contact being stored as a
 * separate Contact object. Object references are stored as key-value pair
 * in a TreeMap.
 *
 * @author: Sean Connor
 */

import java.io.*;
import java.util.*;

public class ContactFileReader
{
    String filename;

    public ContactFileReader(String filename)
    {
        this.filename = filename;
    }


    /**
     *
     * @param filename
     * @return
     */
    public TreeMap<String,Contact> toTreeMap()
    {
        TreeMap<String,Contact> contactList = new TreeMap<>();

        try ( BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                      new FileInputStream(filename))) )
        {
            String line;
            String key;
            String[] values;

            while ( ( line = br.readLine() ) != null )
            {
                values = line.split(",");

                key = (values[0] + values[1]).toLowerCase();

                contactList.put(key, new Contact(values[0],values[1],values[2],
                                                 values[3],values[4]));
            }
        }

        catch (Exception e)
        {
            System.out.println("\nFile not found. Please try again.\n");
            System.out.println();
            System.exit(1);
        }

        return contactList;
    }

}