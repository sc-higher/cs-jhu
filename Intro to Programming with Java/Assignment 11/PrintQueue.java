/**
 * This program is part of my response to Assignment 11 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This program will simulate a print queue using LinkedList.
 *
 * @author: Sean Connor
 */

import java.util.*;

public class PrintQueue
{
    static LinkedList<Job> printQueue = new LinkedList<Job>();
    static int currentID = 0;

    public static void main(String[] args)
    {
        UserInput main_menu = new UserInput(1,2);
        runSim(main_menu);
    }


    /**
     *
     */
    static void printMenuOptions()
    {
        System.out.println("\nPlease make a selection from the following:");
        System.out.println("(1) - Add a print job");
        System.out.println("(2) - Quit Print Queue Simulator");
    }


    /**
     *
     */
    static void addPrintJob()
    {
        printQueue.add( new Job(currentID) );
        currentID++;
    }


    /**
     *
     */
    static void printHeader()
    {
        System.out.printf("\n%1$5s", "ID");
        System.out.printf("%1$30s", "Estimated Print Time");
        System.out.printf("\n%1$5s", "----");
        System.out.printf("%1$30s", "----------------------");
    }


    /**
     *
     */
    static void printAll()
    {
        printHeader();
        for (Job job : printQueue)
        {
            job.printJobInfo();
        }
    }


    /**
     *
     * @param main_menu
     */
    static void runSim(UserInput main_menu)
    {
        int run = -1;

        while (run != 2)
        {
            printMenuOptions();
            run = main_menu.getUserSelection();

            switch (run)
            {
                case 1:
                    addPrintJob();
                    break;
                case 2:
                    printAll();
                    System.out.println("\n\nGoodbye!\n");
                    break;
                default:
                    printAll();
            }
        }
    }

}