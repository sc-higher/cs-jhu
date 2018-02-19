
 /****
    The method getStartDay() implements Zeller's Algorithm for determining the
    day of the week the first day of a month is. The method adjusts Zeller's
    numbering scheme for day of week ( 0=Saturday, ..., 6=Friday ) to conform
    to a day of week number that corresponds to ISO conventions (i.e.,
    1=Monday, ..., 7=Sunday)
  
  Pre-Conditions: The month value, m,  is 1-12
                  The year value, y, is the full year (e.g., 2012)
                  
  Post-Conditions: A value of 1-7 is returned, representing the first day of
  the month: 1 = Monday, ..., 7 = Sunday
****/

    public static int getStartDay( int month,  int year )
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
