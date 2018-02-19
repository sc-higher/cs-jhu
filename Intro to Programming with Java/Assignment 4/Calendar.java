/**
 * This program is my response to Assignment 4 for the
 * class 605.201.81 Intro to Programming Using Java at
 * the JHU EPP CS program.
 *
 * NOTES
 * NOTES
 *
 * @author: Sean Connor
 */

import java.util.Scanner;

public class Calendar
{
    
	public static void main(String [] args)
    {
        
		// initialize variables
        int month = 0;
        int year = 0;
		int startDay = 0;
		
		// get user input
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter a month (1-12): ");
        month = input.nextInt();
        System.out.println();
        System.out.print("Please enter a year: ");
        year = input.nextInt();
        System.out.println();
		
		// text
		startDay = getStartDay(month, year);
		System.out.println("Start Day is " + startDay);
		
    }
	
	
	
	/**
	 *
	 *
	 *
	 *
	 *
	 */
	
	public static void printMonthCalendar(int month, int year)
	{
		
	}
	
	
	
	/**
	 *
	 *
	 *
	 *
	 *
	 */
	
	public static void printMonthHeader(int month, int year)
	{
		
	}
	
	
	
	/**
	 *
	 *
	 *
	 *
	 *
	 */
	
	public static void printMonthBody(int month, int year)
	{
		
	}
	
	
	
	/**
	 *
	 *
	 *
	 *
	 *
	 */
	
	public static String getMonthName(int month)
	{}
	
	
	
	/**
     * The method getStartDay() implements Zeller's Algorithm for determining the
     * day of the week the first day of a month is. The method adjusts Zeller's
     * numbering scheme for day of week ( 0=Saturday, ..., 6=Friday ) to conform
     * to a day of week number that corresponds to ISO conventions (i.e.,
     * 1=Monday, ..., 7=Sunday)
     *
     * Pre-Conditions: The month value, m,  is 1-12
     *                 The year value, y, is the full year (e.g., 2012)
     *             
     * Post-Conditions: A value of 1-7 is returned, representing the first day of
     * the month: 1 = Monday, ..., 7 = Sunday
     */
	
	public static int getStartDay(int month, int year)
    {
        final int day = 1; // Must be set to day 1 for this to work.  JRD.

        // Adjust month number & year to fit Zeller's numbering system
        if ( month < 3 ) 
        {
            month = month + 12;
            year = year - 1;
        }
        
        int yearInCent = year % 100;      // k Calculate year within century
        int century = year / 100;      // j Calculate century term
        int firstDay  = 0;            // h Day number of first day in month 'm'
        
        firstDay = (day + (13 * (month + 1) / 5) + yearInCent +
            (yearInCent / 4) + (century / 4) + (5 * century)) % 7;
        
        // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
        int dayNum = ((firstDay + 5) % 7) + 1;     
        
        return dayNum;
    }
	
	
	
	/**
	 *
	 *
	 *
	 *
	 *
	 */
	
	public static int getNumDaysInMonth(int month, int year)
	{}
	
	
	
	/**
	 *
	 *
	 *
	 *
	 *
	 */
	
	public static boolean isLeapYear(int year)
	{}
	
	
	
}
