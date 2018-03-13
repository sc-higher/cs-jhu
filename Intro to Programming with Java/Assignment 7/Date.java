/**
 * A date consists of an integer month, day and year.
 * All fields are required to be non-blank.
 * The Date fields should be reasonably valid values (ex. month 1-12,
 * day 1-31, year > 1900 and < 2020).
 * Issue appropriate error messages when incorrect data is entered.
 */

public class Date
{

    // declare date variables
    private int dateMonth;
    private int dateDay;
    private int dateYear;


    /** CONSTRUCTORS */

    // no-argument constructor
    public Date()
    {
        // employee date
        dateMonth = 0;
        dateDay = 0;
        dateYear = 0;
    }

    // constructor for all arguments
    public Date(int month, int day, int year)
    {
        // employee date
        dateMonth = month;
        dateDay = day;
        dateYear = year;
    }


    /** METHODS */

    // date 'set' methods
    public void setMonth(int month)
    {
        dateMonth = month;
    }

    public void setDay(int day)
    {
        dateDay = day;
    }

    public void setYear(int year)
    {
        dateYear = year;
    }

    // date 'get' methods
    public int getMonth()
    {
        return dateMonth;
    }

    public int getDay()
    {
        return dateDay;
    }

    public int getYear()
    {
        return dateYear;
    }

}