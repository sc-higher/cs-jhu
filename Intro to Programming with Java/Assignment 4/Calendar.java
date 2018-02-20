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
        String monthString = " ";

		    // get user input
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter a month (1-12): ");
        month = input.nextInt();
        System.out.println();
        System.out.print("Please enter a year: ");
        year = input.nextInt();
        System.out.println();

		    // get start day
        // returns value between 1 and 7 (1 = monday, 7 = sunday)
		    startDay = getStartDay(month, year);
        System.out.println("Start Day is " + startDay);
        System.out.println("Month is " + getMonthName(month));
        printMonthHeader(month, year);
        System.out.println();
        System.out.println(isLeapYear(year));
        System.out.println();
        System.out.println("Number of days in month is: " + getNumDaysInMonth(month, year));

        // TEXT

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
            System.out.println("           " + getMonthName(month) + " " + year + "           ");
            System.out.println("-----------------------------------");
            System.out.println(" Sun  Mon  Tue  Wed  Thu  Fri  Sat ");
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
        {
            String monthString = " ";
            switch(month)
            {
                case 1:  monthString = "January";
                    return monthString;
                case 2:  monthString = "February";
                    return monthString;
                case 3:  monthString = "March";
                    return monthString;
                case 4:  monthString = "April";
                    return monthString;
                case 5:  monthString = "May";
                    return monthString;
                case 6:  monthString = "June";
                    return monthString;
                case 7:  monthString = "July";
                    return monthString;
                case 8:  monthString = "August";
                    return monthString;
                case 9:  monthString = "September";
                    return monthString;
                case 10: monthString = "October";
                    return monthString;
                case 11: monthString = "November";
                    return monthString;
                case 12: monthString = "December";
                    return monthString;
                default: monthString = "Invalid month";
                    return monthString;
            }
        }



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
        {
            int numDays = 0;

            switch(month)
            {
                case 1:  numDays = 31;
                    return numDays;
                case 2:
                    if (isLeapYear(year))
                    {
                        numDays = 29;
                    }
                    else
                    {
                        numDays = 28;
                    }
                    return numDays;
                case 3:  numDays = 31;
                    return numDays;
                case 4:  numDays = 30;
                    return numDays;
                case 5:  numDays = 31;
                    return numDays;
                case 6:  numDays = 30;
                    return numDays;
                case 7:  numDays = 31;
                    return numDays;
                case 8:  numDays = 31;
                    return numDays;
                case 9:  numDays = 30;
                    return numDays;
                case 10: numDays = 31;
                    return numDays;
                case 11: numDays = 30;
                    return numDays;
                case 12: numDays = 31;
                    return numDays;
                default: numDays = 0;
                    return numDays;
            }
        }



       /**
	      *
	      *
	      *
	      *
	      *
	      */

        public static boolean isLeapYear(int year)
        {
            boolean leap = false;
            int criteria1 = year % 4;
            int criteria2 = year % 100;
            int criteria3 = year % 400;

            if (criteria1 == 0)
            {

                if (criteria2 == 0 & criteria3 != 0)
                {
                    leap = false;
                }

                else
                {
                    leap = true;
                }
            }

            else
            {
                leap = false;
            }

            return leap;

        }

}
