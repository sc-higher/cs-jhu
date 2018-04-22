/**
 * This program is part of my response to Assignment 11 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This program will allow the user to import a csv-style contact list, add or
 * remove contacts to the list, and export the list as a csv-style file.
 *
 * @author: Sean Connor
 */

import java.util.*;
import java.io.*;
import java.lang.StringBuilder;

public class ContactList
{
    public static void main(String[] args)
    {
        ContactList l = new ContactList();

        String filename = l.getFileName();

        ContactFileReader reader = new ContactFileReader(filename);
        TreeMap<String,Contact> contactList = reader.toTreeMap();


        l.runContactList(contactList);
    }


    /**
     *
     * @return
     */
    private String getFileName()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("\nPlease enter filename:");
        String filename = input.nextLine();
        return filename;
    }


    /**
     *
     * @param contactList
     */
    private void addContact(TreeMap<String,Contact> contactList)
    {
        String lastName = getInfo("Last Name");
        String firstName = getInfo("First Name");
        String companyName = getInfo("Company Name");
        String phoneNumber = getInfo("Phone Number");
        String emailAddress = getInfo("Email Address");

        String key = (lastName+firstName).toLowerCase();

        contactList.put(key, new Contact(lastName, firstName,companyName,
                                         phoneNumber,emailAddress));

    }


    /**
     *
     * @param contactList
     */
    private void removeContact(TreeMap<String,Contact> contactList)
    {
        String key = getInfo("key (example: John Smith --> smithjohn)");
        contactList.remove(key);
    }


    /**
     *
     * @param field
     * @return
     */
    private String getInfo(String field)
    {
        UserInput correctInput = new UserInput(1,2);
        Scanner input = new Scanner(System.in);
        String info = null;
        int correct = -1;

        while (correct != 1)
        {
            System.out.print("\nPlease enter " + field + ": ");
            info = input.nextLine();
            System.out.println("\nIs this correct? (yes=1 no=2)");
            correct = correctInput.getUserSelection();
        }

        return info;
    }


    /**
     *
     */
    private void printMainMenu()
    {
        System.out.println("\nPlease make a selection from the following:");
        System.out.println("(1) - View contact list");
        System.out.println("(2) - Add a contact");
        System.out.println("(3) - Delete a contact");
        System.out.println("(4) - Save contact list");
        System.out.println("(5) - Quit");
    }


    /**
     *
     * @param contactList
     */
    private void printContactList(TreeMap<String,Contact> contactList)
    {
        System.out.printf("\n%1$15s", "Last Name");
        System.out.printf("%1$15s", "First Name");
        System.out.printf("%1$32s", "Company Name");
        System.out.printf("%1$15s", "Phone Number");
        System.out.printf("%1$32s", "Email Address");

        System.out.printf("\n%1$15s", "------------");
        System.out.printf("%1$15s", "------------");
        System.out.printf("%1$32s", "--------------------");
        System.out.printf("%1$15s", "------------");
        System.out.printf("%1$32s", "--------------------");


        for(Contact t : contactList.values())
        {
            System.out.printf("\n%1$15s", t.getLastName());
            System.out.printf("%1$15s", t.getFirstName());
            System.out.printf("%1$32s", t.getCompanyName());
            System.out.printf("%1$15s", t.getPhoneNumber());
            System.out.printf("%1$32s", t.getEmailAddress());
        }

        System.out.println();
    }


    /**
     *
     * @param contactList
     */
    private void writeData(TreeMap<String,Contact> contactList)
    {
        String output_filename = getInfo("output filename");

        try ( BufferedWriter bw = new BufferedWriter(
                                    new OutputStreamWriter(
                                      new FileOutputStream(output_filename))) )
        {
            StringBuilder sb = new StringBuilder();

            for(Contact t : contactList.values())
            {
                sb.append(t.getLastName());
                sb.append(",");
                sb.append(t.getFirstName());
                sb.append(",");
                sb.append(t.getCompanyName());
                sb.append(",");
                sb.append(t.getPhoneNumber());
                sb.append(",");
                sb.append(t.getEmailAddress());
                sb.append("\n");
            }

            bw.write(sb.toString());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param contactList
     */
    private void runContactList(TreeMap<String,Contact> contactList)
    {
        UserInput mainMenu = new UserInput(1,5);
        int selection = -1;

        while ( selection != 5 )
        {
            printMainMenu();
            selection = mainMenu.getUserSelection();

            switch ( selection )
            {
                case 1:
                    printContactList(contactList);
                    break;
                case 2:
                    addContact(contactList);
                    break;
                case 3:
                    removeContact(contactList);
                    break;
                case 4:
                    writeData(contactList);
                    break;
                case 5:
                    System.out.println("\nGoodbye!\n");
                    break;
                default:
                    System.out.println("Under construction");
            }

        }

    }

}