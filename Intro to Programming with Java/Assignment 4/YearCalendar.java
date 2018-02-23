/**
 * This program is my response to Assignment 4 for the
 * class 605.201.81 Intro to Programming Using Java at
 * the JHU EPP CS program.
 *
 * This is a calendar program. Simply enter the year when prompted and
 * the monthly calendars for the year will be displayed.
 *
 * @author: Sean Connor
 */

import java.util.Scanner;

public class YearCalendar
{

    public static void main(String [] args)
    {
        // initialize variables
        int year = 0;
        int startDay = 0;
        String monthString = " ";

        // get user input
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter a year: ");
        year = input.nextInt();
        System.out.println();

        // print calendar
        for (int month = 1; month <= 12; month++)
        {
            System.out.println();
            printMonthCalendar(month, year);
            System.out.println();
        }

    }



    /**
     * The method printMonthCalendar() simply combines the printMonthHeader() and
     * printMonthBody() methods into a single method, effectively creating the
     * entire monthly calendar when called.
     *
     * Pre-Conditions: The month value, m,  is 1-12
     *                 The year value, y, is the full year (e.g., 2012)
     *
     * Post-Conditions: Prints a complete monthly calendar.
     */

    public static void printMonthCalendar(int month, int year)
    {
        printMonthHeader(month, year);
        printMonthBody(month, year);
    }



    /**
     * The method printMonthHeader() outputs the header of a monthly calendar, given
     * the month and year as input.
     *
     * Pre-Conditions: The month value, m,  is 1-12
     *                 The year value, y, is the full year (e.g., 2012)
     *
     * Post-Conditions: Prints the header of a monthly calendar, to include the title
     * (e.g., 'January 2018'), appropriately underlined by dashes, which lie above the
     * three-letter abbreviations for days of the week.
     */

    public static void printMonthHeader(int month, int year)
    {
        System.out.println("         " + getMonthName(month) + " " + year);
        System.out.println("-----------------------------");
        System.out.println(" Sun Mon Tue Wed Thu Fri Sat ");
    }



    /**
     * The method printMonthBody() outputs the body of a monthly calender, given the
     * desired month and year as input. The days of the month align with the days of
     * the week.
     *
     * Pre-Conditions: The month value, m,  is 1-12
     *                 The year value, y, is the full year (e.g., 2012)
     *
     * Post-Conditions: Prints the body of a monthly calendar, to include days of the
     * month that correspond to the days of the week.
     */

   /*
    It is necessary to adjust the ISO day-of-the-week convention (1 = Monday, ... ,
    7 = Sunday) to the standard calendar setup (1 = Sunday, ... , 7 = Saturday). To
    do this, two separate but slightly different blocks are needed.
    */

    public static void printMonthBody(int month, int year)
    {
        if (getStartDay(month, year) == 7)  // This block is for Sunday, which requires no spacing.
        {
            for (int i = 1; i <= getNumDaysInMonth(month, year); i++)
            {
                if (i < 10)
                {
                    System.out.print("   " + i);    // 3 spaces, for single-digit dates
                }
                else
                {
                    System.out.print("  " + i);     // 2 spaces, for double-digit dates
                }

                if ((i+getStartDay(month, year)) % 7 == 0)
                {
                    System.out.println();
                }
            }
        }

        else    // This block is for Monday - Saturday, which DO require spacing.
        {
            for (int i = 1; i < getStartDay(month, year) + 1; i++)
            {
                System.out.print("    ");
            }
            for (int i = 1; i <= getNumDaysInMonth(month, year); i++)
            {
                if (i < 10)
                {
                    System.out.print("   " + i);    // 3 spaces, for single-digit dates
                }
                else
                {
                    System.out.print("  " + i);     // 2 spaces, for double-digit dates
                }

                if ((i+getStartDay(month, year)) % 7 == 0)
                {
                    System.out.println();
                }
            }
        }
    }



    /**
     * The method getMonthName() returns the String representation of the
     * month input given by an integer (e.g., 1 = January, ..., 12 = December)
     *
     * Pre-Conditions: The month value, m,  is 1-12
     *
     * Post-Conditions: A string is returned, giving the month corresponding to
     * the input integer value.
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

        firstDay = (day + (13 * (month + 1) / 5) + yearInCent + (yearInCent / 4) + (century / 4) + (5 * century)) % 7;

        // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
        int dayNum = ((firstDay + 5) % 7) + 1;

        return dayNum;
    }



    /**
     * The method getNumnDaysInMonth() returns an integer representing the number of
     * days of the month, given the month and year entered. This method accounts for
     * expanded February during leap years.
     *
     * Pre-Conditions: The month value, m,  is 1-12
     *                 The year value, y, is the full year (e.g., 2012)
     *
     * Post-Conditions: An integer value is returned, representing the number of days
     * in the month.
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
     * The method isLeapYear() returns a boolean which indicates whether the given
     * year is a leap year or not. The method determines leap year by the following:
     *
     * Is year evenly divisible by 400? If yes --> leap year
     * Is year evenly divisible by 4 but NOT by 100? If yes --> leap year
     * Else, not a leap year.
     *
     * Pre-Conditions: The year value, y, is the full year (e.g., 2012)
     *
     * Post-Conditions: A boolean is returned, where true = leap year and false = not
     * leap year.
     */

    public static boolean isLeapYear(int year)
    {
        boolean leap = false;
        int criteria1 = year % 4;
        int criteria2 = year % 100;
        int criteria3 = year % 400;

        if (criteria3 == 0 || (criteria1 == 0 && criteria2 != 0))
        {
            leap = true;
        }

        return leap;
    }

}
