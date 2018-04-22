/**
 * This program is part of my response to Assignment 11 for the class 605.201.81
 * Intro to Programming Using Java at the JHU EPP CS program.
 *
 * This class models a printer job and contains variables representing ID
 * and estimated time to completion.
 *
 * @author: Sean Connor
 */

import java.util.Random;

public class Job
{
    // Initialize variables
    Random random = new Random();
    int ID;
    int printTime = random.nextInt(991) + 10;

    // Constructor
    public Job(int ID)
    {
        this.ID = ID;
    }


    /**
     *
     * @return
     */
    public int getID()
    {
        return ID;
    }


    /**
     *
     * @return
     */
    public int getPrintTime()
    {
        return printTime;
    }


    /**
     *
     */
    public void printJobInfo()
    {
        System.out.printf("\n%1$5d", ID);
        System.out.printf("%1$30d", printTime);
    }
}